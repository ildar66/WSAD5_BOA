package com.boa.eagls.government.controller.action.account;

import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.controller.formbean.account.ViewAccountForm;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.account.CentralAccount;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Action for view & fill individual account
 * Date: 08.07.2003
 * @author  Oleg Klimenko
 */
public class ViewAccountAction extends ActionBaseNew {

    private static Logger logger = Logger.getLogger(ViewAccountAction.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public ViewAccountAction() {
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
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws IOException, ServletException {

        // todo fill in error depending on the wrong search result
        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        ViewAccountForm viewAccountForm = (ViewAccountForm) form;
        String currentBaseRole = session.getCurrentBaseRole(request);
        String lastName = (String) request.getSession().getAttribute("lastName");
        String firstName = (String) request.getSession().getAttribute("firstName");
        String socialSecurityNumber = (String) request.getSession().getAttribute("ssn");

        //get user-specific information from session
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        int centralAcctID = Integer.parseInt((String) request.getSession().getAttribute("centralAcctID")); //viewAccountForm.getTxtC valIn.getValString("txt_centralAccountID");
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        try {
            AccountService service = new AccountService();
            logger.debug("  BEFORE getting AgencyCore");
            //retrive Agency info from DB
            AgencyCore agency = service.retrieveAgencyCoreByCentralAcctID(centralAcctID, userID);
            logger.debug("  AFTER getting AgencyCore");
            //retrive Central Account info from DB
            CentralAccount centralAcct = service.retrieveAcctID(centralAcctID, userID,
                    agency, currentBaseRole, currentHierarchy, lastName, firstName, socialSecurityNumber);
//            centralAcct.setTxt_acctCenterID(agency.getAccountingCenterID());
            centralAcct.setTxt_acctCenterID(centralAcct.getAccountingCenterID());
            request.getSession().setAttribute("browseHierarchyFields", centralAcct.getBrowseHierarchyFields());
            request.getSession().setAttribute("browseHierarchyFieldsRO", centralAcct.getBrowseHierarchyFieldsReadOnly());
            //check for credit card
            if (agency.getPerformCreditChecks()) {
                if (Role.GCSU.equals(currentBaseRole)) { // bring up account setup page with prepopulated fields
                    request.getSession().setAttribute("frm_accountSetupInputForm", centralAcct);
                    forward = mapping.findForward("setup/individualAccount/found_new");
                } else { // bring up credit check page
                    forward = mapping.findForward("results_accountSetupCreditCheckError");
                }
            } else {
                if (agency.getFleetType() == CentralAccount.MASTERCARD) {
                    //if card is MASTERCARD
                    // bring up account setup page with prepopulated fields
                    request.getSession().setAttribute("frm_accountSetupInputForm", centralAcct);
                    forward = mapping.findForward("setup/individualAccount/accountDetails");
//                    return accountSetupPrepopulated(al, centralAcct, agency);
                } else if (agency.getFleetType() == CentralAccount.VOYAGER) {
                    // if card is VOYAGER
                    // voyager not supported
                    //"Voyage not supported yet."
//                    request.getSession().setAttribute("errmsg", "Voyage not supported yet.");
//                    forward = mapping.findForward("error_page");
                    forward = mapping.findForward("setup/individualAccount/accountDetails");
                } else {
                    // unexpected selection
                    request.getSession().setAttribute("errmsg", "Unexpected fleet type.");
                    forward = mapping.findForward("setup/individualAccount/accountDetails");
//                    forward = mapping.findForward("error_page");
                }
            }
            request.getSession().setAttribute("frm_accountSetupInputForm", centralAcct);
        } catch (EaglsException e) {
            logger.error("error", e);
        }


//        forward = mapping.findForward("setup/individualAccount/accountDetails");
        /*// If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.
        //todo check if error handling by server is required
        if (!errors.empty()) {
            saveErrors(request, errors);
            //	forward = mapping.findForward("failure");
        }*/
        return (forward);
    }

}
