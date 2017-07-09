package com.boa.eagls.government.controller.action.inquiry;

import org.apache.struts.action.*;
import org.apache.log4j.Logger;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.formbean.inquiry.IPointOfContact;
import com.boa.eagls.government.dto.pointOfContact.PointOfContactDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 08.07.2003
 * Time: 14:41:43
 * To change this template use Options | File Templates.
 */
public class ViewPointOfContactAction extends Action{
/* Session Wrapper */
    protected EAGLSSession session;
    private static final Logger logger= Logger.getLogger(ViewPointOfContactAction.class);

    /**
     * Constructor
     */
    public ViewPointOfContactAction() {
        super();
        session = new EAGLSSession();
    }

    public ActionForward execute(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        ActionErrors errors = new ActionErrors();
        ActionForward forward = new ActionForward();
        logger.debug("ViewPointOfContactAction entering");
        IPointOfContact formBean = (IPointOfContact) form;

        Collection pointsOfContacts = (Collection) request.getSession().getAttribute("searchResult");
        String id = request.getParameter("id");
        if (id.length() > 0) {
            logger.debug("ID = " + id);

            int index = Integer.parseInt(id);
            //if (pointsOfContacts != null) {
            //    Iterator it = pointsOfContacts.iterator();
            //    while (it.hasNext()) {
            if (pointsOfContacts.toArray().length > 0) {
                logger.debug("Filling form bean");
                PointOfContactDTO poinOfContact = (PointOfContactDTO) pointsOfContacts.toArray()[index];

                formBean.setPriTDO(poinOfContact.getPriTDO());
                formBean.setAddress1(poinOfContact.getAddress1());
                formBean.setAddress2(poinOfContact.getAddress2());
                formBean.setAddress3(poinOfContact.getAddress3());
                formBean.setAddress4(poinOfContact.getAddress4());
                formBean.setAopc(poinOfContact.getAOPC());
                formBean.setCentralAccountNbr(poinOfContact.getCentralAccountNbr());
                formBean.setCity(poinOfContact.getCity());
                formBean.setCountry(poinOfContact.getCountry());
                formBean.setDbo(poinOfContact.getDBO());
                formBean.setEcedi(poinOfContact.getECEDI());
                formBean.seteMail(poinOfContact.geteMail());
                formBean.setFax(poinOfContact.getFax());
                formBean.setFirstName(poinOfContact.getFirstName());
                formBean.setLastName(poinOfContact.getLastName());
                formBean.setNewsletterMedium(poinOfContact.getNewsletterMedium());
                formBean.setPaymentOffice(poinOfContact.getPaymentOffice());
                formBean.setPriAOPC(poinOfContact.getPriAOPC());
                formBean.setPriDBO(poinOfContact.getPriDBO());
                formBean.setPriECEDI(poinOfContact.getPriECEDI());
                formBean.setPriPaymentOffice(poinOfContact.getPriPaymentOffice());
                formBean.setPriTDO(poinOfContact.getPriTDO());
                formBean.setState(poinOfContact.getState());
                //todo formBean.setTaskOrderNo(poinOfContact.getTaskOrderNo());
                formBean.setTdo(poinOfContact.getTDO());
                formBean.setWorkPhone(poinOfContact.getWorkPhone());
                formBean.setZip(poinOfContact.getZip());
            } else {
                //todo not found (create new?)
            }
        } else {
            // todo formbean already filled with data?
        }

        // return value
        //PointOfContactForm searchAccountForm = (PointOfContactForm) form;
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

