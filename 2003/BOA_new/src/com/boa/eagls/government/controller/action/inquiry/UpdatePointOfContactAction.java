package com.boa.eagls.government.controller.action.inquiry;

import com.boa.eagls.government.controller.formbean.account.SearchAccountForm;
import com.boa.eagls.government.controller.formbean.inquiry.IPointOfContact;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.service.pointOfContact.PointOfContactService;
import org.apache.struts.action.*;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 04.07.2003
 * Time: 18:00:41
 * To change this template use Options | File Templates.
 */
public class UpdatePointOfContactAction  extends Action{
/* Session Wrapper */
    protected EAGLSSession session;
    private static final Logger logger= Logger.getLogger(UpdatePointOfContactAction.class);


    /**
     * Constructor
     */
    public UpdatePointOfContactAction() {
        super();
        session = new EAGLSSession();
    }

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        logger.debug("Entering execute");

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        // return value
        IPointOfContact pointOfContactForm = (IPointOfContact) form;
        if (pointOfContactForm  != null) {
            new PointOfContactService().updatePointOfContact(pointOfContactForm);
        } else {
            logger.debug("Form not presents");
            //todo error entering to page
        }
        //Map htUserData = session.getCurrentUserData(request);
        //String userID = session.getUserID(request);
        /*Collection pointsOfContacts = null;
        try {
            pointsOfContacts = (new PointOfContactService()).getPointOfContact(50);

            //request.getSession().setAttribute("centralAcctID",
            //                    searchAccountForm.getTxtCentralAccountID());
            if (pointsOfContacts != null || pointsOfContacts.size() > 0) {
                        request.getSession().setAttribute("searchResult", pointsOfContacts);
                        forward = mapping.findForward("foundPointsOfContacts");
                        logger.debug("REDIRECT: foundPointsOfContacts");
                    } else { // no results found in database => setup new account
                        forward = mapping.findForward("foundPointsOfContacts");
                        logger.debug("REDIRECT: new");
                    }
        } catch (Exception e) {
            // Report the error using the appropriate name and ID.
            forward = mapping.findForward("foundPointsOfContacts");
            logger.error("REDIRECT: not_found", e);
        }

        // If a message is required, save the specified key(s)
        // into the request for use by the <struts:errors> tag.
        //todo check if error handling by server is required
        if (!errors.empty()) {
            saveErrors(request, errors);
            //	forward = mapping.findForward("failure");
        }
        */

        forward = mapping.findForward("pointOfContact");
        return (forward);
    }
}

