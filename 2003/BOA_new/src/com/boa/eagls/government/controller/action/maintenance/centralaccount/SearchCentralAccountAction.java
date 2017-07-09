package com.boa.eagls.government.controller.action.maintenance.centralaccount;

import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.constants.web.ErrorMessages;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm;
import com.boa.eagls.government.controller.formbean.maintenance.centralaccount.SearchCentralAccountForm;
import com.boa.eagls.government.dto.centralaccount.CentralAcct;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.centralaccount.CentralAccountService;
import com.boa.eagls.government.service.centralaccount.CentralAccountValueListHandler;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.DisplayDateUtil;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: IvanK
 * Date: 05.07.2003
 * Time: 18:12:53
 * To change this template use Options | File Templates.
 */
public class SearchCentralAccountAction extends Action {
    private static final Logger logger = Logger.getLogger(SearchCentralAccountAction.class);

//    public static final String SEARCH_URL = "maintenance/centralAccount/search.do";

    /* Session Wrapper */
    protected EAGLSSession session;
    public static final String SINGLE_RESULT_FORWARD = "CentralAccountMaintenanceSearchResult";
    public static final String MULTIPLY_RESULT_FORWARD = "CentralAccountMaintenanceSearchResults";

    /**
     * Constructor
     */
    public SearchCentralAccountAction() {
        super();
        session = new EAGLSSession();
    }


    /**
     * The method is called by Struts framework after a form has been submited.
     * All of business logic dealing with processing form submission event must be
     * placed here.
     * @param mapping class containing keys from struts-config.xml for forward mapping
     * @param form form bean filled in by submitted form values
     * @param request servlet's request object
     * @param response servlet's response object
     * @return simple object filled in by forward information from <code>mapping</code>
     * object
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */

    public ActionForward perform(//todo check for deprecation of the method

            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        // todo fill in error depending on the wrong search result
        ActionForward forward = new ActionForward();
        // return value
        SearchCentralAccountForm searchCentralAccountForm = (SearchCentralAccountForm) form;
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        String currentBaseRole = session.getCurrentBaseRole(request);
        CentralAccountValueListHandler accounts = null;
        CentralAccountService centralAccountService = new CentralAccountService();
        if (request.getParameter("Next") == null && request.getParameter("Previous") == null) {
            try {
    //            accounts = searchCentralAccountService.searchAccount(searchCentralAccountForm, htUserData, userID);
                accounts = (CentralAccountValueListHandler) centralAccountService.searchAccount(searchCentralAccountForm, htUserData, userID, currentBaseRole);
                int accountSize = accounts.getSize();
                if (accounts != null && accountSize > 0) {// || accounts.getSize()>0) {
                    if (accountSize == 1) {
                        searchCentralAccountForm.setTxt_centralAccountNumber(accounts.getTxt_centralAccountNumber());
                        CentralAccountForm centralAccountForm = fill(centralAccountService.retrieveCentralAcctMaint(
                                userID, searchCentralAccountForm.getTxt_centralAccountNumber()), currentBaseRole);
                        request.setAttribute(CentralAccountForm.FORM_BEAN, centralAccountForm);
                        request.getSession().setAttribute("browseHierarchyFields", centralAccountForm.getBrowseHierarchyFields());
                        forward = mapping.findForward(SINGLE_RESULT_FORWARD);
                    } else {
                        request.getSession().setAttribute(Eagles.VALUE_LIST_ITERATOR, accounts);
                        forward = mapping.findForward(MULTIPLY_RESULT_FORWARD);
                    }
                } else {
                    ActionErrors errors = new ActionErrors();
                    if (searchCentralAccountForm.isChk_centralAccountNumber()) {
                        errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NUMBER,
                                new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NUMBER,
                                        searchCentralAccountForm.getTxt_centralAccountNumber()));
                    } else {
                        if (searchCentralAccountForm.isChk_centralAccountID())
                            errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                                    new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                                            searchCentralAccountForm.getTxt_centralAccountID()));
                        if (searchCentralAccountForm.isChk_centralAccountName())
                            errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NANE,
                                    new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_NANE,
                                            searchCentralAccountForm.getTxt_centralAccountName()));
                        if (searchCentralAccountForm.isChk_hierLevel()) {
                            String[] hierarhyStr = new String[9];
                            hierarhyStr[0] = searchCentralAccountForm.getTxt_hl0();
                            hierarhyStr[1] = searchCentralAccountForm.getTxt_hl1();
                            hierarhyStr[2] = searchCentralAccountForm.getTxt_hl2();
                            hierarhyStr[3] = searchCentralAccountForm.getTxt_hl3();
                            hierarhyStr[4] = searchCentralAccountForm.getTxt_hl4();
                            hierarhyStr[5] = searchCentralAccountForm.getTxt_hl5();
                            hierarhyStr[6] = searchCentralAccountForm.getTxt_hl6();
                            hierarhyStr[7] = searchCentralAccountForm.getTxt_hl7();
                            hierarhyStr[8] = searchCentralAccountForm.getTxt_hl8();
                            errors.add(ErrorMessages.SEARCH_HIERARCHY,
                                    new ActionError(ErrorMessages.SEARCH_HIERARCHY, hierarhyStr));
                        }
                    }
                    saveErrors(request, errors);

                    ActionMessages messages = new ActionMessages();
                    messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));//Messages.TITLE_NO_DATA_FOUND
                    messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
                    messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
                    messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_SERACH));
                    saveMessages(request, messages);

                    forward = mapping.findForward(Eagles.ERROR_FORWARD);
                }
            } catch (EaglsException e) {
                //todo should be implemented later
                logger.error("REDIRECT: not_found", e);
            } catch (EaglsDAOError e) {
                //todo should be implemented later
                logger.error("ERROR in searchACTION!", e);
            }
        } else {
            forward = mapping.findForward(MULTIPLY_RESULT_FORWARD);
        }
        return forward;
    }

    private final CentralAccountForm fill(CentralAcct results, String currentBaseRole) {
        CentralAccountForm centralAccountForm = new CentralAccountForm();
        if (results != null) {
            centralAccountForm.setCentralAccountNumber(results.getAcctNo());
            centralAccountForm.setTxt_centralAccountName(results.getAcctName());
            centralAccountForm.setAgencyName(results.getAgencyName());
            centralAccountForm.setCentralAccountID(results.getCentralAcctID());
            centralAccountForm.setTxt_masterAccountingCode(results.getDefaultAccountingCode());
            centralAccountForm.setTxt_accountingCenterID(results.getAccountingCenterID());
            centralAccountForm.setAccountType(((results.getAccountType() == (short) 1) ? "Diversion" : "Central"));
            centralAccountForm.setBillingType(((results.getBillingType() == (short) 1) ? "Individual" : "Central"));
            centralAccountForm.setFleetType(((results.getFleetType() == (short) 1) ? "VOYAGER" : "MASTERCARD"));

            Date expDate = results.getAccountExpDate();
            if (expDate == null) {
                centralAccountForm.setTxt_accountExpirationDate("");
            } else {
                centralAccountForm.setTxt_accountExpirationDate(DisplayDateUtil.convertExpiryToString(expDate));
            }

            //       int cl = (int) (results.getCreditLimit());
            //       StringBuffer creditLimit = new StringBuffer(String.valueOf(cl));
            //       StringBuffer creditLimit = new StringBuffer(String.valueOf(results.getCreditLimit()));
            Double credLmt = new Double(results.getCreditLimit());
            //Need to use a DecimalFormat object to format the number.
            DecimalFormat no = new DecimalFormat();
            no.applyPattern("##############");
            String credLmtStr = no.format(credLmt);
            StringBuffer creditLimit = new StringBuffer(credLmtStr);
            int ptr = creditLimit.length();
            while ((ptr) > 3) {
                creditLimit.insert(ptr - 3, ",");
                ptr = ptr - 3;
            }
            centralAccountForm.setCreditLimit("$" + creditLimit);

            centralAccountForm.setCmb_travelerChecks(results.getIssueTravelersChecks() ? "Yes" : "No");
            centralAccountForm.setCmb_atmAccess(results.getHasATMAccess() ? "Yes" : "No");
            centralAccountForm.setCmb_decrementCard(results.getUsesDecrementingCard() ? "Yes" : "No");
            centralAccountForm.setCmb_citypairProgram(results.getCityPairProgram() ? "Yes" : "No");

            centralAccountForm.setProgramType((results.getIntegratedFlag() ? "I" : "N"));
            String nonIntegratedProgram = "";

            if (results.getIntegratedFlag()) {
                centralAccountForm.setIntegratedProgram("");
                centralAccountForm.setIntegratedType("Integrated");
                centralAccountForm.setShowPlainText("&nbsp;");
                centralAccountForm.setSelectTravelCheckBox(results.getTravelFlag());
                centralAccountForm.setSelectFleetCheckBox(results.getFleetFlag());
                centralAccountForm.setSelectPurchaseCheckBox(results.getPurchaseFlag());
                centralAccountForm.setSelectInteragencyCheckBox(results.getInteragencyFlag());

            } else {
                if (results.getTravelFlag()) {
                    nonIntegratedProgram = "Travel";
                }
                if (results.getFleetFlag()) {
                    nonIntegratedProgram = "Fleet";
                }
                if (results.getPurchaseFlag()) {
                    nonIntegratedProgram = "Purchase";
                }
                if (results.getInteragencyFlag()) {
                    nonIntegratedProgram = "Interagency";
                }
                centralAccountForm.setShowCheckBoxes("");
                centralAccountForm.setIntegratedProgram(null);
                centralAccountForm.setIntegratedType(nonIntegratedProgram);
            }

            centralAccountForm.setCmb_convenienceChecks(results.getIssueConvenienceChecks() ? "Yes" : "No");


            int[] cNumbers = results.getConvenienceChecksNumbers();
            int len = cNumbers.length;
            int flag_3 = 0;
            int flag_20 = 0;
            int flag_50 = 0;
            for (int i = 0; i < len; i++) {
                if (cNumbers[i] == 3) {
                    flag_3 = 1;
                } else if (cNumbers[i] == 20) {
                    flag_20 = 1;
                } else if (cNumbers[i] == 50) {
                    flag_50 = 1;
                }
            }
            if (flag_3 == 1) {
                centralAccountForm.setChk_checkProgram1(true);
            } else {
                centralAccountForm.setChk_checkProgram1(false);
            }
            if (flag_20 == 1) {
                centralAccountForm.setChk_checkProgram2(true);
            } else {
                centralAccountForm.setChk_checkProgram2(false);
            }
            if (flag_50 == 1) {
                centralAccountForm.setChk_checkProgram3(true);
            } else {
                centralAccountForm.setChk_checkProgram3(false);
            }
            centralAccountForm.setElectronicYes(results.getInvoiceMediumElectronic());
            centralAccountForm.setEDIYes(results.getInvoiceMediumElectronic());
            centralAccountForm.setPaperYes(results.getInvoiceMediumPaper());
        }

        BrowseHierarchyFields fields =
                AccountService.fillHierarchyBean(results.getHierarchy(), results.getHierarchy(), currentBaseRole, false);

        centralAccountForm.setBrowseHierarchyFields(fields);
        return centralAccountForm;
    }

}
