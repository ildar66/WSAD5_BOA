package com.boa.eagls.government.controller.action.inquiry;

import org.apache.struts.action.*;
import org.apache.log4j.Logger;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.formbean.account.SearchAccountForm;
import com.boa.eagls.government.controller.formbean.inquiry.PointOfContactForm;
import com.boa.eagls.government.service.pointOfContact.PointOfContactService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Collection;
/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 03.07.2003
 * Time: 14:06:13
 * To change this template use Options | File Templates.
 */
public class SearchPointsOfCotactAction extends Action{
/* Session Wrapper */
    protected EAGLSSession session;
    static final Logger logger = Logger.getLogger(SearchPointsOfCotactAction.class);

    /**
     * Constructor
     */
    public SearchPointsOfCotactAction() {
        super();
        session = new EAGLSSession();
    }


    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        // return value
        PointOfContactForm formBean = (PointOfContactForm) form;

        //Map htUserData = session.getCurrentUserData(request);
        //String userID = session.getUserID(request);
        Collection pointsOfContacts = null;
        try {
            String accNum = request.getParameter(PointOfContactParams.ACCOUNT_NUMBER);
            if (accNum == null) {
                accNum = formBean.getCentralAccountNbr();
            }
            logger.debug("Point of contact " + accNum);
            pointsOfContacts = (new PointOfContactService()).getPointOfContact(accNum);

            //request.getSession().setAttribute("centralAcctID",
            //                    searchAccountForm.getTxtCentralAccountID());
            if (pointsOfContacts.size() > 0) {
                        request.getSession().setAttribute("searchResult", pointsOfContacts);
                        forward = mapping.findForward("foundPointsOfContacts");
                    } else { // no results found in database => setup new account

                        forward = mapping.findForward(PointOfContactActions.NO_RESULTS);
                    }
        } catch (Exception e) {
            // Report the error using the appropriate name and ID.
            forward = mapping.findForward(PointOfContactActions.NO_RESULTS);
            //logger.error("REDIRECT: not_found", e);
        }

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
