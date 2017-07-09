package com.boa.eagls.government.controller.action.account;

import com.boa.eagls.government.constants.web.Eagles;
import com.boa.eagls.government.constants.web.ErrorMessages;
import com.boa.eagls.government.constants.web.Messages;
import com.boa.eagls.government.controller.formbean.account.SearchAccountForm;
import com.boa.eagls.government.controller.action.ActionBaseNew;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.IndividualAccountService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import org.apache.log4j.Logger;
import org.apache.struts.action.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Search Individual Account use case
 * @version 	1.0
 * @author  OlegK
 */
public class SearchAccountAction extends ActionBaseNew {
    private static Logger logger = Logger.getLogger(SearchAccountAction.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public SearchAccountAction() {
        super();
        session = new EAGLSSession();
    }

    /**
     * The method is called by Struts framework after a form has been submited. All of business logic dealing with
     * processing form submission event must be placed here.
     *
     * @param mapping  class containing keys from struts-config.xml for forward mapping
     * @param form     form bean filled in by submitted form values
     * @param request  servlet's request object
     * @param response servlet's response object
     * @return simple object filled in by forward information from <code>mapping</code> object
     */
    public ActionForward eaglsExecute(//todo check for deprecation of the method

            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {

        // todo fill in error depending on the wrong search result
        ActionForward forward = null;
        // return value
        SearchAccountForm searchAccountForm = (SearchAccountForm) form;
        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        ValueListIterator accounts = null;
        request.setAttribute("searchAccountForm", searchAccountForm);
        try {
            accounts = new IndividualAccountService().searchAccount(searchAccountForm, htUserData, userID);
            request.getSession().setAttribute("centralAcctID", searchAccountForm.getTxtCentralAccountID());
            if (accounts != null && accounts.getSize() > 0) {// || accounts.getSize()>0) {
                request.getSession().setAttribute(Eagles.VALUE_LIST_ITERATOR, accounts);
                forward = mapping.findForward(FIND_EXISTING_URL);
            } else { // no results found in database => setup new account
                forward = mapping.findForward(FIND_NEW_URL);
            }
        } catch (EaglsException e) {
            ActionMessages messages = new ActionMessages();
            messages.add(Messages.TITLE, new ActionMessage(Messages.TITLE_NO_DATA_FOUND));
            messages.add(Messages.HEADER, new ActionMessage(Messages.HEADER_SERCH));
            messages.add(Messages.FOOTER, new ActionMessage(Messages.FOOTER_SEARCH));
            messages.add(Messages.BUTTON, new ActionMessage(Messages.BUTTON_SERACH));
            saveMessages(request, messages);

            ActionErrors errors = new ActionErrors();
            errors.add(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                    new ActionError(ErrorMessages.SEARCH_CENTRAL_ACCOUNT_ID,
                            searchAccountForm.getTxtCentralAccountID()));
            saveErrors(request, errors);
            forward = mapping.findForward(Eagles.ERROR_FORWARD);
//            forward = mapping.findForward(NOT_FOUND_URL);
        } catch (EaglsDAOError e) {
            logger.error("Error in action processing", e);
            //todo: some standarg message should be created
            forward = mapping.findForward(Eagles.ERROR_FORWARD);
        }
        request.getSession().setAttribute("lastName", searchAccountForm.getTxtLastName());
        request.getSession().setAttribute("firstName", searchAccountForm.getTxtFirstName());
        request.getSession().setAttribute("ssn", searchAccountForm.getTxtSocialSecurityNumber());

        /*//todo check if error handling by server is required
        if (!errors.empty()) {
            saveErrors(request, errors);
            //	forward = mapping.findForward("failure");
        }*/
        return forward;
    }

    public static final String SEARCH_URL = "setup/individualAccount/search.do";
    public static final String FIND_EXISTING_URL = "setup/individualAccount/found_existing";

    public static final String FIND_NEW_URL = "setup/individualAccount/found_new";
    public static final String NOT_FOUND_URL = "setup/individualAccount/not_found";
}
