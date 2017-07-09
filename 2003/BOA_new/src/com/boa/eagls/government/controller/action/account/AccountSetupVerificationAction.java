package com.boa.eagls.government.controller.action.account;

import com.boa.eagls.government.business.BusinessObject;
import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.controller.formbean.account.ViewAccountForm;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.IndividualAccount;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.service.IndividualAccountService;
import com.boa.eagls.government.service.HierarchyBase;
import com.boa.eagls.government.service.userprofile.UserAccountService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.SSNumber;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p><small> VDI Company, 22.07.2003 12:43:44</small></p>
 * Account Setup Verification action
 * Old GUID is {892094D0-1B7C-11D2-96DD-204C4F4F5020}
 * @author OlegK
 */
public class AccountSetupVerificationAction extends ActionBaseNew {
    static final Logger logger = Logger.getLogger(AccountSetupVerificationAction.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public AccountSetupVerificationAction() {
        super();
        session = new EAGLSSession();
    }

    public ActionForward eaglsExecute(//todo check for deprecation of the method

            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        // todo fill in error depending on the wrong search result
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();

        IndividualAccount acct = new IndividualAccount();
        String currentBaseRole = session.getCurrentBaseRole(request);
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        String userID = session.getUserID(request);

        ViewAccountForm viewAccountForm = (ViewAccountForm) form;
//        viewAccountForm.setCentralAcctID(valIn.getValString("hdn_centralAccountID"));
        AgencyCore agency = null;

        String centerID = viewAccountForm.getTxt_acctCenterID();
        String programNumber = viewAccountForm.getTxt_hl0();
        if (programNumber == null || (programNumber.trim()).length() == 0)
            programNumber = viewAccountForm.getHdn_programNumber();

        String[] hier = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];

        int[] acctHierarchy = new int[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        hier[0] = programNumber;
        acctHierarchy[0] = Integer.parseInt(programNumber);
        for (int i = 1; i < 9; i++) {
            hier[i] = viewAccountForm.getTxt_hl(i);//getValString("txt_hl" + i);
            if (hier[i] == null || hier[i].equals("")) {
                acctHierarchy[i] = 0;
            } else {
                acctHierarchy[i] = Integer.parseInt(hier[i]);
            }
        }
        HierarchyDisplay ah = new HierarchyDisplay(hier);

        if (Role.A_OPC.equalsIgnoreCase(currentBaseRole)) {
//            TemplateMapBasic map = getEAGLSTemplateMap("Account Setup", "Verification");
            AccountService accountService = new AccountService();
            if (!accountService.validateHierarchyForAopcSetup(acctHierarchy, currentHierarchy)) {
//                ErrorInfo errorInfo = new ErrorInfo();
//                errorInfo.setErrorMessage("Invalid account hierarchy entered for account");
//                return mapping.findForward(EAGLS_ERROR_URL);
//                return evalTemplate("gsa/eaglserror.html", (com.kivasoft.ITemplateData) null, map);
            }
        }

        //****** VALIDATE ACCOUNTING CENTER ID ***************//*
        // This is validating the hierarchy...
        try {
            String mac = viewAccountForm.getTxt_masterAccountingCode();

            AccountingCenter accountingCenter = null;
            if (Role.A_OPC.equalsIgnoreCase(currentBaseRole)) {
                accountingCenter = AccountingService.retrieveAopc(centerID, userID, currentHierarchy);
            } else {
                accountingCenter = AccountingService.retrieve(centerID, userID);//, getTransport());
            }

            // PCR 324
            if (Role.GCSU.equals(currentBaseRole))
                if (accountingCenter.getHierarchy()[1].getNumber() != acctHierarchy[1])
                    throw new EaglsException("Accounting Center " + centerID + " is not in the same HL1 as the account.", null);

            if ((!mac.equals("")) && mac != null) {
                AccountingService acctService = new AccountingService();
                //mac todo implement mac
//                acctService.parseAccountingCode(accountingCenter, viewAccountForm.getTxt_acctCenterID(), mac);
//                                    AccountingCenter accountingCenter, String acctCodeStr, int validate)
            }
            if (mac == null)
                mac = new String("");

//        } catch (InvalidAccountingCodeError e) {
            //todo replace NoDataFoundException to logic check
//            if (e instanceof NoDataFoundException)
//                return processException(new NBError("Invalid Accounting Center ID: " + centerID));
//            else
//            logger.error("EAGLS has returned an error", e);
//                return processException(e);
        } catch (EaglsException e) {
            logger.error("can't get AccountingCenter info", e);
        }

        //****** VALIDATE ACCOUNTING CENTER ID ***************//
        try {
            AccountService service = new AccountService();
            agency = service.retrieveAgencyCoreByCentralAcctID(viewAccountForm.getHdn_centralAccountID(), userID);
        } catch (EaglsException e) {
            logger.error("EAGLS has returned an error", e);
            //return processException(e);
        }

        acct.setAgencyName(agency.getAgencyName());
        acct.setAccountingCenterID(viewAccountForm.getTxt_acctCenterID());
        acct.setAcctStatus(Account.OPEN);
        if (("Purchase").equals(viewAccountForm.getHdn_programType())) {
            acct.setProgramType(Account.PURCHASE);
        } else if (("Travel").equals(viewAccountForm.getHdn_programType())) {
            acct.setProgramType(Account.TRAVEL);
        } else if (("Fleet").equals(viewAccountForm.getHdn_programType())) {
            acct.setProgramType(Account.FLEET);
            /* CD 9/10/99
               BUG MDS2321
               Description: Change Integrate to Integrated
            */
        } else if (("Integrated").equals(viewAccountForm.getHdn_programType())) {
            acct.setProgramType(Account.INTEGRATE);
        } else if (("Interagency").equals(viewAccountForm.getHdn_programType())) {
            acct.setProgramType(Account.INTERAGENCY);
        }
        acct.setDefaultAccountingCode(viewAccountForm.getTxt_masterAccountingCode());
        //acct.setIssueConvenienceChecks( ("Yes").equals(valIn.getValString("hdn_convenienceCheckFlag")));
        if (("Central").equals(viewAccountForm.getHdn_billingType())) {
            acct.setBillingType(Account.CENTRAL);
        } else if (("Individual").equals(viewAccountForm.getHdn_billingType())) {
            acct.setBillingType(Account.INDIVIDUAL);
        }

//        try {
        //todo implement it
//            acct.validateExpirationDate(DisplayDateUtil.convertStringToExpiry(valIn.getValString("txt_accountExpirationDate")));
//        } catch (EaglsException e) {
//            logger.error("", e);
//            return processException(e);
//        }

        //todo implement it
//        double creditLimit = currencyToDouble(valIn.getValString("txt_creditLimit"));
//        acct.setCreditLimit(creditLimit);

        String[] h = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
        String[] d = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];

        if (Role.A_OPC.equals(currentBaseRole)) {
            HierarchyDisplay hd = new HierarchyDisplay(currentHierarchy);
            h[0] = hd.getProgramNumber();
            d[0] = hd.getProgramNumberDescription();
        } else {
            h[0] = viewAccountForm.getTxt_hl0();
            d[0] = viewAccountForm.getTxt_programDescription();
        }

        String tempHlSegment2;
        HierarchyDTO hierarchytemp[] = new HierarchyDTO[9];

        for (short i = 0; i < HierarchyDisplay.NUMBER_OF_SEGMENTS; i++) {
            HierarchyDTO hLeveltemp = new HierarchyDTO();

            if (i == 0) {
                hLeveltemp.setLevel(i);
                hLeveltemp.setNumber(Integer.parseInt(h[i]));
                hLeveltemp.setAgencyName(d[i]);
            } else {
                h[i] = viewAccountForm.getTxt_hl(i);

                tempHlSegment2 = viewAccountForm.getTxt_hlDescr(i);
                if (tempHlSegment2 == null) {
                    d[i] = tempHlSegment2;
                } else {
                    d[i] = tempHlSegment2.trim();
                }


                hLeveltemp.setLevel(i);


                if (h[i] == null || "".equals(h[i].trim())) {
                    hLeveltemp.setNumber(BusinessObject.INT_DEFAULT);
                } else {
                    hLeveltemp.setNumber(Integer.parseInt(h[i]));
                }


                if (d[i] == null) {
                    hLeveltemp.setAgencyName(d[i]);
                } else {
                    hLeveltemp.setAgencyName(d[i].trim());
                }

            }
            hierarchytemp[i] = hLeveltemp;

        }
        acct.setHierarchy(hierarchytemp);

        HierarchyDisplay hierarchy = new HierarchyDisplay(h, d);

        acct.setCentralAcctID(String.valueOf(viewAccountForm.getHdn_centralAccountID()));
        acct.setCentralAcctName(viewAccountForm.getHdn_centralAccountName());
        acct.setCentralAcctNo(viewAccountForm.getHdn_centralAccountNumber());
        acct.setUsesTravelersChecks(("Yes").equals(viewAccountForm.getHdn_travelersCheckFlag()));
        // acct.setHasATMAccess(("Yes").equals(valIn.getValString("hdn_atmAccessFlag")));
        // acct.setUsesDecrementingCard(("Yes").equals(valIn.getValString("hdn_decrementingCardFlag")));
        acct.setCardType(viewAccountForm.getCmb_cardType());
        // acct.setCardDesign(valIn.getValString("cmb_Card);
        if (!("").equals(viewAccountForm.getTxt_socialSecurityNumber())) {
            acct.setSsn(SSNumber.parseString(viewAccountForm.getTxt_socialSecurityNumber()));
        }
        acct.setEmployeeID(viewAccountForm.getTxt_employeeIdentificationNumber());
        // acct.setHomePhone("No home phone on page");
        // acct.setApprovingOfficer("No approving officer on page");
        acct.setCardExists(("Card").equals(viewAccountForm.getCmb_cardNoncard()));
        acct.setGeneratePaperStatement(("Yes").equals(viewAccountForm.getCmb_generatePaperStatementFlag()));
//        acct.setGrade(viewAccountForm.getCmb_grade()); todo implement it
//        acct.setEmploymentStatus(viewAccountForm.getCmb_status()); todo implement it
        acct.setLastName(viewAccountForm.getTxt_lastName());
        acct.setFirstName(viewAccountForm.getTxt_firstName());
        acct.setAddress1(viewAccountForm.getTxt_addressLine1());
        acct.setAddress2(viewAccountForm.getTxt_addressLine2());
        acct.setAddress3(viewAccountForm.getTxt_addressLine3());
        acct.setAddress4(viewAccountForm.getTxt_addressLine4());
        acct.setCity(viewAccountForm.getTxt_city());
        acct.setStateOrProvince(viewAccountForm.getTxt_state());
        acct.setCountry(viewAccountForm.getTxt_country());
        acct.setZip(viewAccountForm.getTxt_zipCode());
        acct.setBusinessPhone(viewAccountForm.getTxt_businessPhone());
        // acct.setFax("No fax on page");
        acct.setEMail(viewAccountForm.getTxt_emailAddress());

        //todo TSYS stub
        IndividualAccountService individualAccountService = new IndividualAccountService();
        try {
            individualAccountService.createIndividualAcct(acct);
        } catch (EaglsDAOError e) {
            logger.error("EAGLS error", e);
        }

        request.getSession().setAttribute("frm_verif_AccountSetup", viewAccountForm);
        request.getSession().setAttribute("individualAccount", acct);

        forward = mapping.findForward("setup/individualAccount/success");

        request.getSession().setAttribute("browseHierarchyFields",
                request.getSession().getAttribute("browseHierarchyFieldsRO"));


        ////////////
        int[] hierarchyD = new HierarchyDisplay(hier).getValues();
        try {
            HierarchyBase hierarchyBase = new HierarchyBase();
            String hierarchyNbr = hierarchyBase.getHierarchyNumber(hierarchyD);
            UserAccountService userAccountService = new UserAccountService(null);
            //                hDisplay = AccountController.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue(), das);
            HierarchyDisplay hDisplay = userAccountService.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue());

//            BrowseHierarchyFields browseHierarchyFields = viewAccountForm.getBrowseHierarchyFields();
            BrowseHierarchyFields browseHierarchyFields =
                    (BrowseHierarchyFields) request.getSession().getAttribute("browseHierarchyFields");
            browseHierarchyFields.setHlDescription(hDisplay.getAgencyNames());
            browseHierarchyFields.setHlNumber(hDisplay.getSegments());
            viewAccountForm.setBrowseHierarchyFields(browseHierarchyFields);
            request.getSession().setAttribute("browseHierarchyFields", browseHierarchyFields);
            request.getSession().setAttribute("browseHierarchyFieldsRO", browseHierarchyFields);
        } catch (Exception e) {
            logger.error("getting hierarchy", e);
        }

        /*todo check if error handling by server is required
        if (!errors.empty()) {
            saveErrors(request, errors);
            //	forward = mapping.findForward("failure");
        }*/
        return (forward);
    }
}
