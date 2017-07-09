package com.boa.eagls.government.service.common;

import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegment;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;
import com.boa.eagls.government.dto.browse.BrowseMasterAcctCodeDTO;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.service.Service;
import org.apache.log4j.Logger;

import java.net.URLEncoder;
import java.util.StringTokenizer;

/**
 * <p><small> DVI Company, 23.07.2003 12:05:19</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeService
        extends Service{
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeService.class);
    public AccountingService accountingService;

    public BrowseMasterAcctCodeService() {
        super();
        accountingService = new AccountingService();
    }


    public BrowseMasterAcctCodeDTO getBrowseMasterAcctCodeDTO(String userID,
                                                                     String acctCenterID,
                                                                     String hdn_valueSet,
                                                                     String hdn_segNumSet,
                                                                     String fromHyperLink,
                                                                     String masterAccountingCode,
                                                                     String hdn_segmentNumber,
                                                                     String hdn_segmentValue,
                                                                     String hideButton,
                                                                     String slashes)
            throws EaglsException {
        BrowseMasterAcctCodeDTO browseMasterAcctCodeDTO =
                new BrowseMasterAcctCodeDTO();
        hdn_segNumSet = hdn_segNumSet == null ? "x" : hdn_segNumSet;
        browseMasterAcctCodeDTO.setHdn_segNumSet(hdn_segNumSet);
        AccountingCodeSegmentDescription[] aCSD;
        AccountingCenter tAcctCenter = null;
        tAcctCenter = accountingService.retrieveReadOnly(acctCenterID, userID);
        aCSD = tAcctCenter.getAccountingCodeSegmentDescriptions();
        String[] tempArray = new String[aCSD.length];
        if (fromHyperLink == null) {
            String mac = masterAccountingCode;
            int length = tAcctCenter.getSegmentsTotalLength();
            if ("theMasterAccountingCode".equalsIgnoreCase(mac)) {
                mac = "";
            }

            {
                mac = AccountingCenter.parseSpace(mac, '#', length);
                int macLength = aCSD.length;
                int segStart = 0;
                for (int i = 0; i < macLength; i++) {
                    int segLength = aCSD[i].getLength();
                    int segEnd = segStart + segLength;
                    String segmentValue = null;
                    if (mac.length() > segEnd) {
                        segmentValue = mac.substring(segStart, segEnd);
                    } else if (mac.length() > segStart) {
                        segmentValue = mac.substring(segStart);
                        int gap = segEnd - mac.length();
                        for (int j = 0; j < gap; j++) {
                            segmentValue += "*";
                        }
                    }
                    segStart += segLength;
                    tempArray[i] = segmentValue;
                    //acctCode[i].setDefaultValue(segmentValue);
                }
            }
        }

        String[] valueArray = new String[aCSD.length];

        if (fromHyperLink != null) {
            int segNum = Integer.parseInt(hdn_segmentNumber);
            StringTokenizer st = new StringTokenizer(hdn_valueSet);
            int count = st.countTokens();
            for (int i = 0; i < count; i++) {
                String token = st.nextToken();
                if (i != segNum) {
                    valueArray[i] = (token == null) ? "^^" : token;
                } else {
                    valueArray[i] = hdn_segmentValue;
                }
            }
        } else { // create the array
            for (int i = 0; i < aCSD.length; i++) {
                //String temp = acctCode[i].getDefaultValue();
                String temp = tempArray[i];
                if (temp == null) {
                    temp = "blank";
                } else if (temp.length() == 0) {
                    temp = "blank";
                }
                valueArray[i] = (temp.equals("blank")) ? "^^" : temp;
            }
        }
        String str = "";
        for (int i = 0; i < aCSD.length; i++) {
            valueArray[i] = (valueArray[i] == null) ? "^^" : valueArray[i];
            str += valueArray[i] + " ";
        }
        browseMasterAcctCodeDTO.setHdn_valueSet(str);
        ///////////////////////
        // display segment representation, fill in pull down menu
        String segmentRep = "";
        String segmentDesc = "";
        //TemplateDataBasic segments = new TemplateDataBasic("availableSegments");

        for (int i = 0; i < aCSD.length; i++) {
            String temp = (valueArray[i].equals("^^")) ? "" : valueArray[i];
            segmentRep += temp + (i < aCSD.length - 1 ? "/" : "");
            /*segments.rowAppend("segmentValue=" + i +
                    ";segmentDescription=(" + (i + 1) + ") " + aCSD[i].getDescription());*/
            try {
                AccountingCodeSegment aCS =
                        accountingService.getAccountingCodeSegment(aCSD[i], acctCenterID, temp);
                if (aCS.isActive()) segmentDesc +=
                        aCS.getDescription() +
                        (i < aCSD.length - 1 ? "/" : "");
            } catch (IllegalArgumentException e) {
            }//do nothing for the exception

        }
        browseMasterAcctCodeDTO.setAccountingCodeSegmentDescriptions(aCSD);
        int chopOff = 60;
        browseMasterAcctCodeDTO.setSegmentRepresentation1(segmentRep);
        if (segmentRep.length() > chopOff) {
            String newSegRep = segmentRep.substring(0, (chopOff)) +
                    "<BR>" + segmentRep.substring(chopOff);
            segmentRep = newSegRep;
        }
        browseMasterAcctCodeDTO.setSegmentRepresentation(segmentRep);
        browseMasterAcctCodeDTO.setSegmentDescription(
                URLEncoder.encode(segmentDesc));
        browseMasterAcctCodeDTO.setSegmentDescription1(segmentDesc);
        browseMasterAcctCodeDTO.setHdn_accountingCenterId(acctCenterID);

        // add to favorites button.
        if (("doNotHide").equalsIgnoreCase(hideButton)) { // show the add to favorite button.
            browseMasterAcctCodeDTO.setHideButton("doNotHide");
            browseMasterAcctCodeDTO.setShowButton(null);
        } else { // hide button
            browseMasterAcctCodeDTO.setShowButton("");
        }

        // slashes ?
        slashes = "yes".equalsIgnoreCase(slashes) ? "yes" : "x";
        browseMasterAcctCodeDTO.setSlashes(slashes);
        browseMasterAcctCodeDTO.setHdn_accountingCode("theAccountingCode");
        //////////////////////
        return browseMasterAcctCodeDTO;
    }

}