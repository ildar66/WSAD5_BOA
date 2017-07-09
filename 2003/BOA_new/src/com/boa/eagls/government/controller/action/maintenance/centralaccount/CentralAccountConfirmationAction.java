package com.boa.eagls.government.controller.action.maintenance.centralaccount;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.centralaccount.CentralAcct;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.DisplayDateUtil;
import com.boa.eagls.government.service.centralaccount.CentralAccountService;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.constants.web.ErrorMessages;
import com.boa.eagls.government.constants.web.Messages;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;
import java.util.Date;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

/**
 * <p><small> VDI Company, 29.07.2003 15:36:22</small></p>
 * @author AlexanderZe
 */
public class CentralAccountConfirmationAction extends Action {

    static final Logger logger = Logger.getLogger(CentralAccountConfirmationAction.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public CentralAccountConfirmationAction() {
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
    public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {
//todo check for deprecation of the method
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        String currentBaseRole = session.getCurrentBaseRole(request);
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        CentralAccountForm centralAccountForm = (CentralAccountForm) form;


        // get the Hiearchy Fields
        BrowseHierarchyFields browseHierarchy = session.getBrowseHierarchy(request);

//        String[] hier = browseHierarchy.getHlNumber();
//        String[] hierDescription = browseHierarchy.getHlDescription();
//        HierarchyDisplay hierarchy = new HierarchyDisplay(hier, hierDescription, hierDescription);

        CentralAcct centralAccount = new CentralAcct();
        CentralAcct oldCentralAcct = new CentralAcct();

        DecimalFormat no = new DecimalFormat();
        no.applyPattern("¤#,###.00");
        String centralAccountNumber = centralAccountForm.getCentralAccountNumber();
        try {
            oldCentralAcct = new CentralAccountService().retrieveCentralAcctMaint(userID, centralAccountNumber);
        } catch (Exception e) {
            logger.error("Cant't retrive account services with number" + centralAccountNumber);
        }
        double creditLimit = 0.0;
        String centralAccountID = centralAccountForm.getCentralAccountID();
        String centralAccountName = centralAccountForm.getTxt_centralAccountName();
        String agencyName = centralAccountForm.getAgencyName();

//        AccountChangeLog myAccLog = new AccountChangeLog(thisUserID, "Maintain Central Account", centralAccountNumber);
        if (!oldCentralAcct.getAcctName().equals(centralAccountName)) {
//            myAccLog.addFieldChange(FieldChange.FN_LAST_NAME, oldCentralAcct.getAcctName(), centralAccountName);
        }// end if


        //Used later in constructing hyperlink
        String ediStatus = null;
        String showLight = null;
        String showBold = null;
        String showLinks = null;

        //Additional fields required for logging purposes only
        String oldHierarchyNumber = new String();
        String newHierarchyNumber = new String();

        // Only do update and stuff if not told to avoid it
        if ((request.getAttribute("avoidDBCall") == null) ||
                (!(request.getAttribute("avoidDBCall").toString().equalsIgnoreCase("true")))) {

            //Set this from valIn ONLY if it is from the ver
            creditLimit = CentralAccountService.currencyToDouble(centralAccountForm.getCreditLimit());

            String[] hier = browseHierarchy.getHlNumber();
            String[] hDescr = browseHierarchy.getHlDescription();
            HierarchyDTO[] tempHier = oldCentralAcct.getAHierarchy();


            //FieldChange tempFieldChange = new FieldChange(FieldChange.FN_HIERARCHY_NBR, oldHierarchyNumber, newHierarchyNumber);
            //myAccLog.addFieldChange(tempFieldChange);

            HierarchyDisplay hierarchy = new HierarchyDisplay(hier, hDescr, hDescr);


            centralAccount.setHierarchy(hierarchy.getHierarchy());
            centralAccount.setAgencyName(agencyName);
            centralAccount.setCentralAcctID(centralAccountID);
            centralAccount.setAcctNo(centralAccountNumber);
            centralAccount.setAcctName(centralAccountName);
            centralAccount.setDefaultAccountingCode(centralAccountForm.getTxt_masterAccountingCode());

            //LOG MASTER_ACCOUNTING_CODE
            String oldValue = oldCentralAcct.getDefaultAccountingCode();
            String newValue = centralAccountForm.getTxt_masterAccountingCode();
//todo should be implement logger
//            if (!oldValue.equals(newValue)) {
//                FieldChange masterAccountFieldChange = new FieldChange(FieldChange.FN_MASTER_ACCOUNT_CODE, oldValue, newValue);
//                myAccLog.addFieldChange(masterAccountFieldChange);
//            }
            centralAccount.setAccountingCenterID(centralAccountForm.getTxt_accountingCenterID());
            short aType = 0;
            if (((centralAccountForm.getAccountType())).equals("Diversion")) {
                aType = centralAccount.DIVERSION_ACCT;
            }//end if
            centralAccount.setAccountType(aType);
            centralAccount.setBillingType(centralAccount.CENTRAL);
            short fType = 0;
            String fleetType = centralAccountForm.getFleetType();
            if (((fleetType.toUpperCase()).equals("VOYAGER")) ||
                    ((fleetType.toUpperCase()).equals("VISA")) ||
                    ((fleetType.toUpperCase()).equals("VISA VOYAGER"))) {
                fType = 1;
            }//end if
            centralAccount.setFleetType(fType);

            try {
                Date expirationDate = DisplayDateUtil.convertStringToExpiry(centralAccountForm.getTxt_accountExpirationDate());
                centralAccount.setAccountExpDate(expirationDate);
            }//end try
            catch (Exception e) {
            }//end catch

            centralAccount.setCreditLimit(creditLimit);

            centralAccount.setIssueTravelersChecks(CentralAccountService.stringToBoolean(centralAccountForm.getCmb_travelerChecks()));
            String tempTravCheck = (oldCentralAcct.getIssueTravelersChecks() ? "Yes" : "No");
            String travCheckNew = centralAccountForm.getCmb_travelerChecks();
            //todo should be implement logger
//            if (!tempTravCheck.equals(travCheckNew)) {
//                FieldChange travCheckFieldChange = new FieldChange(FieldChange.FN_TRAVELERS_CHECK_FLAG, tempTravCheck, travCheckNew);
//                myAccLog.addFieldChange(travCheckFieldChange);
//            }

            centralAccount.setHasATMAccess(CentralAccountService.stringToBoolean(centralAccountForm.getCmb_atmAccess()));
            oldValue = (oldCentralAcct.getHasATMAccess() ? "Yes" : "No");
            newValue = centralAccountForm.getCmb_atmAccess();
            //todo should be implement logger
//            if (!oldValue.equals(newValue)) {
//                FieldChange ATMFieldChange = new FieldChange(FieldChange.FN_ATM_ACCESS_FLAG, oldValue, newValue);
//                myAccLog.addFieldChange(ATMFieldChange);
//            }

            centralAccount.setUsesDecrementingCard(CentralAccountService.stringToBoolean(centralAccountForm.getCmb_decrementCard()));

            centralAccount.setCityPairProgram(CentralAccountService.stringToBoolean(centralAccountForm.getCmb_citypairProgram()));


            centralAccount.setIntegratedFlag(
                    centralAccountForm.getProgramType().equals("Integrated"));

            StringTokenizer sToken = new StringTokenizer(centralAccountForm.getCardPrograms(), ",", false);
            while (sToken.hasMoreTokens()) {
                String t = sToken.nextToken();
                if (t.equals("Fleet")) {
                    centralAccount.setFleetFlag(true);
                } else if (t.equals("Purchase")) {
                    centralAccount.setPurchaseFlag(true);
                } else if (t.equals("Travel")) {
                    centralAccount.setTravelFlag(true);
                } else if (t.equals("Interagency")) {
                    centralAccount.setInteragencyFlag(true);
                }//end else if
            }//end if

            centralAccount.setIssueConvenienceChecks(CentralAccountService.stringToBoolean(centralAccountForm.getCmb_convenienceChecks()));
            String tempConvChecks = (oldCentralAcct.getIssueConvenienceChecks() ? "Yes" : "No");
            String tempNewConvChecks = centralAccountForm.getCmb_convenienceChecks();
            //todo should be implement logger
//            if (!tempNewConvChecks.equalsIgnoreCase(tempConvChecks)) {
//
//                FieldChange convCheckFieldChange = new FieldChange(FieldChange.FN_CONVENIENCE_CHECK_FLAG, tempConvChecks, tempNewConvChecks);
//                myAccLog.addFieldChange(convCheckFieldChange);
//            }

            StringTokenizer sToken2 = new StringTokenizer(centralAccountForm.getNumberConvenienceChecks(), ",", false);
            int[] convNumbers = new int[sToken2.countTokens()];
            int j = 0;
            while (sToken2.hasMoreTokens()) {
                try {
                    convNumbers[j] = Integer.parseInt(sToken2.nextToken());
                    j++;
                } catch (NumberFormatException e) {
                    convNumbers[j++] = 0;
                }//end catch
            }//end while
            centralAccount.setConvenienceChecksNumbers(convNumbers);

            StringTokenizer sToken3 = new StringTokenizer(centralAccountForm.getInvoiceMedium(), ",", false);
            while (sToken3.hasMoreTokens()) {
                String t = sToken3.nextToken();
                t = t.toUpperCase();
                if (t.equals("PAPER")) {
                    centralAccount.setInvoiceMediumPaper(true);
                } else if (t.equals("ELECTRONIC")) {
                    centralAccount.setInvoiceMediumElectronic(true);
                } else if (t.equals("EDI")) {
                    centralAccount.setInvoiceMediumEDI(true);
                }//end else if
            }//end while

            //This is done for hyperlink generation (ECEDI)
            if (centralAccount.getInvoiceMediumEDI()) {
                ediStatus = "Y";
                showBold = "&nbsp;";
                showLight = "";
            } else {
                ediStatus = "N";
                showBold = "";
                showLight = "&nbsp;";
            }//end else

            //This is done to determine if hyperlinks are shown for diversion accounts
            if (centralAccount.getAccountType() == CentralAcct.DIVERSION_ACCT) {
                showLinks = "";
            }//end if
            //Throw error if no UserID exists
            if (userID == null) {
                logger.error("Error: no userID available");
                ActionErrors errors = new ActionErrors();

//                if (searchCentralAccountForm.isChk_centralAccountID())
//                    errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
//                            new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
//                                    searchCentralAccountForm.getTxt_centralAccountID()));
//
//                saveErrors(request, errors);

                ActionMessages messages = new ActionMessages();
                messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));
                messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
                messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
                messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
                saveMessages(request, messages);

            } else {

                try {
                    new CentralAccountService().updateCentralAccountMain(oldCentralAcct, centralAccount, userID);
                } catch (EaglsException e) {
                    logger.error(e.getMessage(), e);
                    ActionMessages messages = new ActionMessages();
                    messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));
                    messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
                    messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
                    messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_OK));
                    saveMessages(request, messages);
                }
            }
        }
        request.setAttribute(CentralAccountForm.FORM_BEAN, centralAccountForm);
        ActionForward forward = new ActionForward();
        forward = mapping.findForward("CentralAccountConfirmation");
        return forward;
    }

}
