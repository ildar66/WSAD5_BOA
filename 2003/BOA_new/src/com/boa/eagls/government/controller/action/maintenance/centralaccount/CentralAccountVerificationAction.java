package com.boa.eagls.government.controller.action.maintenance.centralaccount;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.NoDataFoundException;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.AccountingService;
import com.boa.eagls.government.controller.formbean.maintenance.centralaccount.CentralAccountForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Map;

/**
 * <p><small> VDI Company, 23.07.2003 21:08:49</small></p>
 * @author AlexanderZe
 */
public class CentralAccountVerificationAction extends Action {
    static final Logger logger = Logger.getLogger(CentralAccountVerificationAction.class);

    /* Session Wrapper */
    protected EAGLSSession session;

    /**
     * Constructor
     */
    public CentralAccountVerificationAction() {
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

        Map htUserData = session.getCurrentUserData(request);
        String userID = session.getUserID(request);
        String currentBaseRole = session.getCurrentBaseRole(request);
        HierarchyDTO[] currentHierarchy = session.getCurrentHierarchy(request);
        CentralAccountForm centralAccountForm = (CentralAccountForm) form;


        // get the Hiearchy Fields
        BrowseHierarchyFields browseHierarchy = session.getBrowseHierarchy(request);

        String[] hier = browseHierarchy.getHlNumber();
        String[] hierDescription = browseHierarchy.getHlDescription();
        HierarchyDisplay hierarchy = new HierarchyDisplay(hier, hierDescription, hierDescription);
//        TemplateMapBasic map = al.getEAGLSTemplateMap("Central Account Maintenance", "Verification", hierarchy, false);

        String centerID = centralAccountForm.getTxt_accountingCenterID();

        try {
            AccountingCenter accountingCenter = null;
            if (Role.A_OPC.equals(currentBaseRole)) {
                accountingCenter = AccountingService.retrieveAopc(centerID, userID, currentHierarchy);
            } else {
                accountingCenter = AccountingService.retrieve(centerID, userID);
            }


            // PCR 324
            if (Role.GCSU.equals(currentBaseRole))
                if (accountingCenter.getHierarchy()[1].getNumber() != hierarchy.getValue(1))
                    throw new NBError("Accounting Center " + centerID + " is not in the same HL1 as the account.");

            String mac = centralAccountForm.getTxt_masterAccountingCode();
            //todo implement mac
//            if ((!mac.equals("")) && mac != null)
//                accountingCenter.parseAccountingCode(mac);

            if (mac == null)
                mac = new String("");

        } catch (Exception e) {
            if (e instanceof NoDataFoundException)
                logger.error("Invalid Accounting Center ID: " + centerID);
            else
                logger.error("can't get AccountingCenter info", e);
        }

//
//
//               String selection = valIn.getValString("rag_programType");
               StringBuffer cPrograms = new StringBuffer("");
//
               String comma = "";
                if ("Integrated".equals(centralAccountForm.getProgramType()))  {

                   if (centralAccountForm.isSelectFleetCheckBox()) {
                       cPrograms.append("Fleet");
                       comma = ",";
                   }
                   if (centralAccountForm.isSelectPurchaseCheckBox()) {
                       cPrograms.append(comma);
                       cPrograms.append("Purchase");
                       comma = ",";
                   }
                   if (centralAccountForm.isSelectTravelCheckBox()) {
                       cPrograms.append(comma);
                       cPrograms.append("Travel");
                       comma = ",";
                   }
                   if (centralAccountForm.isSelectInteragencyCheckBox()) {
                       cPrograms.append(comma);
                       cPrograms.append("Interagency");
                   }
                   centralAccountForm.setCardPrograms(cPrograms.toString());
               }
               //Map the card Programs

               comma = "";
               StringBuffer cChecks = new StringBuffer("");
                if (centralAccountForm.isChk_checkProgram1()) {
                       cChecks.append("3");
                       comma = ",";
                   }
                if (centralAccountForm.isChk_checkProgram2()) {
                        cChecks.append(comma);
                       cChecks.append("20");
                       comma = ",";
                   }
            if (centralAccountForm.isChk_checkProgram3()) {
                        cChecks.append(comma);
                       cChecks.append("50");

                   }
              centralAccountForm.setNumberConvenienceChecks(cChecks.toString());

               StringBuffer invoice = new StringBuffer("EAGLS");
               comma = ",";
        if (centralAccountForm.isPaperYes()) {
               invoice.append(comma);
               invoice.append("Paper");
        }
        if (centralAccountForm.isEDIYes()) {
               invoice.append(comma);
               invoice.append("EDI");
        }
        if (centralAccountForm.isElectronicYes()) {
               invoice.append(comma);
               invoice.append("Electronic");
        }
        centralAccountForm.setInvoiceMedium(invoice.toString());
        request.setAttribute(CentralAccountForm.FORM_BEAN, centralAccountForm);
        ActionForward forward = new ActionForward();
        forward = mapping.findForward("CentralAccountVerification");

        return forward;
    }
}

