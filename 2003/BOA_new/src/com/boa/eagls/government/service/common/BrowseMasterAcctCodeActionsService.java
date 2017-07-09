package com.boa.eagls.government.service.common;

import com.boa.eagls.government.dto.accounting.AccountingCodeSegment;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;
import com.boa.eagls.government.dto.browse.BrowseAccountingCodesDTO;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * <p><small> DVI Company, 24.07.2003 19:19:22</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeActionsService
        extends Service {
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeActionsService.class);
    //static final int RECORDS_PER_PAGE = 50;
    private boolean isAccountingCodeSegmentValidated;

    public boolean isAccountingCodeSegmentValidated() {
        return isAccountingCodeSegmentValidated;
    }

    public BrowseAccountingCodesDTO
            getBrowseAccountingCodesDTO(Map parameterMap,
                                        BrowseMasterAcctCodeActionsParam form,
                                        String userID,
                                        boolean isCodeOptions)
            throws EaglsException, EaglsDAOError {
        AccountingService accountingService = new AccountingService();
        BrowseAccountingCodesDTO dto =
                new BrowseAccountingCodesDTO();
        /*
        AccountingCodeSegmentDescription[] aCSD =
        AccountingService.retrieveReadOnly(acctCenterID, userID).
        getAccountingCodeSegmentDescriptions();
        String segNumStr = form.getSel_availableSegments();
        int segNum = Integer.parseInt(segNumStr);

        ValueListIterator codeOptions =
        accountingService.searchAccountingCodeSegments(
        acctCenterID,
        aCSD[segNum]);
        dto.setCodeOptions(codeOptions);*/
        int startRecord = 0;
        int resultSetID = 0;
        Map prevHLParams = parameterMap;
        Map nextHLParams = parameterMap;
        Map currentParams = new HashMap();

        String startRecordStr = form.getStartRecord();
        String forceUpdate = form.getForceUpdate();

        String browseCriteria = form.getTxt_browseCriteria();

        String acctCenterID = form.getHdn_accountingCenterId();
        dto.setHdn_accountingCenterId(acctCenterID);
        currentParams.put("accountingCenterId", acctCenterID);
        String action = form.getBut_submit().trim();
        dto.setBut_submit(action);
        currentParams.put("but_submit", action);
        AccountingCodeSegmentDescription[] aCSD =
                accountingService.retrieveReadOnly(acctCenterID, userID).
                getAccountingCodeSegmentDescriptions();
        ValueListIterator codeOptions;
        String segNumStr = form.getSel_availableSegments();
        int segNum = Integer.parseInt(segNumStr);

        currentParams.put("sel_availableSegments", String.valueOf(segNum));
        String segName = aCSD[segNum].getName();

        String valueSet = form.getHdn_valueSet();
        String segNumSet = form.getHdn_segNumSet();

        // propagate forward



        dto.setHdn_segmentNumber(segNumStr);
        dto.setHdn_displaySegmentNumber(String.valueOf(segNum + 1));
        currentParams.put("hdn_segmentNumber", segNumStr);

        if (segNumSet != null) {
            dto.setHdn_segNumSet(segNumSet);
            currentParams.put("hdn_segNumSet", segNumSet);
            dto.setHdn_valueSet(valueSet);
            currentParams.put("hdn_valueSet", valueSet);
        }

        dto.setHdn_segmentName(segName);
        currentParams.put("hdn_segmentName", segName);
        //TemplateDataBasic segChoice = new TemplateDataBasic("availableSegmentValues");
        dto.setHideButton(form.getHideButton());
        dto.setSlashes(form.getSlashes());
        if ("Add Segment".equalsIgnoreCase(action)) {
            String value = form.getTxt_segmentValue();
            AccountingCodeSegment validated = accountingService.
                    validateSegmentValue(aCSD[segNum],
                            acctCenterID, value, true);
            if (validated != null) {
                dto.setHdn_segmentValue(value);
                currentParams.put("hdn_segmentValue", value);
                dto.setSegmentName(segName);
                currentParams.put("segmentName", segName);
                isAccountingCodeSegmentValidated = true;
                /*return evalTemplate("gsa/common/verif_accountingCodeSegmentAddition.html",
                (com.kivasoft.ITemplateData) null, map); */
            } else {
                isAccountingCodeSegmentValidated = false;
                /* return evalTemplate("gsa/common/warning_invalidAccountingCode.html",
                (com.kivasoft.ITemplateData) null, map); */
            }
        } else if ("Browse Segment".equalsIgnoreCase(action)) {
            if (/*startRecordStr == null || forceUpdate != null*/!isCodeOptions) { // not nextColumn or prev selection
                if (aCSD != null) {
                    if (startRecordStr == null) {
                        startRecord = 0;
                    } else {
                        startRecord = Integer.parseInt(startRecordStr);
                    }

                    codeOptions =
                            accountingService.searchAccountingCodeSegments(
                                    acctCenterID,
                                    browseCriteria,
                                    aCSD[segNum]);
                    dto.setCodeOptions(codeOptions);
                }
            } else { // coming in from nextColumn or prev selection
                /*resultSetID = Integer.parseInt(valIn.getValString("resultSetID"));

                // get LaRS
                resultSet = getLaRS();

                // verify result set is for inquiry
                if (!resultSet.validate(guid(), resultSetID)) {
                valIn.setValString("forceUpdate", "force");
                return newRequest(guid(), valIn, valOut);
                }

                // retrieve starting record number
                startRecord = Integer.parseInt(startRecordStr);
                if (startRecord > resultSet.numberOfRecords()) {
                    startRecord = 0;
                } */
            } // else from nextColumn or prev

            Object[] records;
            if (/*resultSet.numberOfRecords() > 0*/true) {
                /*mapMultiplePageSymbols(map, guid(), prevHLParams, nextHLParams,
                        startRecord, RECORDS_PER_PAGE, resultSet.numberOfRecords(),
                        "");  */


                //records = resultSet.readRecords(startRecord, RECORDS_PER_PAGE);
                currentParams.put("fromHyperLink", "YES");
                boolean browseFlag = false;
                if (browseCriteria != null) {
                    browseFlag = browseCriteria.length() > 0;
                }
                /*if (!browseFlag) {

                    for (int i = 0; i < records.length; i++) {
                        currentParams.put("hdn_segmentValue",
                                ((AccountingCodeSegment) records[i]).getValue());
                        currentParams.put("hideButton", valIn.getValString("hideButton"));
                        currentParams.put("slashes", valIn.getValString("slashes"));
                        segChoice.rowAppend("segmentLnk=" +
                                createHyperlink("{559DB100-362B-11D2-96E4-204C4F4F5020}", currentParams) +
                                ";segmentValue=" + ((AccountingCodeSegment) records[i]).getValue() +
                                ";segmentDescription=" +
                                ((AccountingCodeSegment) records[i]).getDescription());
                    }
                }*/ /*else { // has browse criteria
                    for (int i = 0; i < records.length; i++) {
                        String temp = ((AccountingCodeSegment) records[i]).getValue();
                        String criteria = browseCriteria.trim();
                        int criteriaLength = criteria.length();

                        if (temp.length() >= criteriaLength) {
                            String matchedPortion = temp.substring(0, criteriaLength);
                            if (matchedPortion.equals(criteria)) {
                                currentParams.put("hdn_segmentValue",
                                        ((AccountingCodeSegment) records[i]).getValue());
                                currentParams.put("hideButton", valIn.getValString("hideButton"));
                                currentParams.put("slashes", valIn.getValString("slashes"));
                                segChoice.rowAppend("segmentLnk=" +
                                        createHyperlink("{559DB100-362B-11D2-96E4-204C4F4F5020}",
                                                currentParams) +
                                        ";segmentValue=" +
                                        ((AccountingCodeSegment) records[i]).getValue() +
                                        ";segmentDescription=" +
                                        ((AccountingCodeSegment) records[i]).getDescription());
                            }
                        }
                    }
                } */
                currentParams.put("hideButton", form.getHideButton());
                currentParams.put("slashes", form.getSlashes());
                dto.setOptionHyperlink(createHyperlink("", currentParams));
            }
        }
        // pross else yet
        return dto;
    }


}