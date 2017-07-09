package com.boa.eagls.government.controller.action.user;

/**
 * AddOptionsAction
 */

import com.boa.eagls.government.util.*;
import com.boa.eagls.government.exceptions.*;

import java.util.Vector;
import java.util.Hashtable;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import org.apache.log4j.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.service.userprofile.*;
import com.boa.eagls.government.service.*;
import org.apache.struts.action.DynaActionForm;
import com.boa.eagls.government.controller.action.ActionBase;

/**
 * Action class to add options for user profile <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class AddOptionsAction extends ActionBase
{
    private static final Logger LOGGER =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.user.AddOptionsAction.class");

    /**
     * Action for Assigning Options to a User Profile
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @pre $none
     * @post $none
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            ServletException
    {
        LOGGER.debug("Entering eaglsExecute() of AddOptionsAction class");
        try
        {
            session = new EAGLSSession();

            DynaActionForm upForm =
                    (DynaActionForm) request.getSession()
                    .getAttribute("UserProfileBean");
            DynaActionForm hierarchyForm = (DynaActionForm) form;
            UserProfileService userProfileService =
                    new UserProfileService(null);
            HierarchyService hierarchyService = new HierarchyService(null);
            String btnSelected =
                    ((String) hierarchyForm.get("but_submitButton")).trim();

            // --String btnSelected = request.getParameter("but_submitButton").trim();
            // --String currentRole = request.getParameter("currentRole");
            String currentRole =
                    (String) upForm.get("currentRole");
            String baseRole =
                    userProfileService.getBaseRole(currentRole);
            String[] hCodes =
                    new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
            String[] hDesc =
                    new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];

            for (int i = 0; i < HierarchyDisplay.NUMBER_OF_SEGMENTS; i++)
            {

                // --hCodes[i] = request.getParameter("txt_hl" + i);
                // --hDesc[i] = request.getParameter("txt_hl" + i + "Desc");
                hCodes[i] = (String) hierarchyForm.get("txt_hl" + i);
                hDesc[i] = (String) hierarchyForm.get("txt_hl" + i + "Desc");
            }
            HierarchyDisplay hierarchyDisplay = new HierarchyDisplay(hCodes,
                    hDesc, hDesc);

            /*
             * for (int x = 0; x < HierarchyDisplay.NUMBER_OF_SEGMENTS; x++) {
             * request.setAttribute("txt_desc" + (x + 1),
             * request.getParameter("txt_hl" + (x + 1) + "Desc"));
             * }
             */
            if (baseRole.equals("AH"))
            {

                // String acctNumber = request.getParameter("txt_accountNo").trim();
                String acctNumber =
                        (String) hierarchyForm.get("txt_accountNo");
                UserAccountService usreAccountService =
                        new UserAccountService(null);
                short acctType = -1;

                try
                {
                    acctType =
                            usreAccountService.validateAccountNumber(acctNumber,
                                    session.getUserID(request));
                }
                catch (NBException e)
                {
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING,
                            new ActionError("heading.error.accountnumber.inValid"));
                    errors.add(Constants.ERR_MSG,
                            new ActionError("error.accountnumber.inValid"));
                    saveErrors(request, errors);

//                    request.setAttribute("errmsg","Account Number cannot be added to the User Profile.");
                    return mapping.findForward("error");
                }


                if (!(acctType == Constants.INDIVIDUAL
                        || acctType == Constants.MASTERCARD_VEHICLE))
                {
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING,
                            new ActionError("heading.error.accountnumber.inValid"));
                    errors.add(Constants.ERR_MSG,
                            new ActionError("error.individualaccount.accounttype"));
                    saveErrors(request, errors);

                    //request.setAttribute("errmsg","Cannot assign individualaccount that is not of type Individual.");
                    return mapping.findForward("error");
                }
            }
            else if (baseRole.equals("A_OPC") || baseRole.equals("DBO")
                    || baseRole.equals("TDO") || baseRole.equals("NB_ADM")
                    || baseRole.equals("NB_ACCTG") || baseRole.equals("CL")
                    || baseRole.equals("FMS") || baseRole.equals("GSA")
                    || baseRole.equals("A") || baseRole.equals("TC")
                    || baseRole.equals("NFC"))
            {

                // applicable to A_OPC to capture the hidden hierarchies
                if (session.getCurrentBaseRole(request).equals("A_OPC"))
                {
                    int j = 0;

                    for (int i = 0;
                         i < session.getCurrentHierarchy(request).length;
                         i++)
                    {
                        if (!(String
                                .valueOf(session
                                .getCurrentHierarchy(request)[i]
                                .getNumber()).equals("0")))
                        {
                            j++;
                        }
                    }
                    for (int x = 0; x < j - 1; x++)
                    {
                        request
                                .setAttribute("txt_hl" + x,
                                        HierarchyDisplay
                                .pad7(session
                                .getCurrentHierarchy(request)[x]
                                .getNumber()));
                    }
                }

                String tempo;

                try
                {
                    tempo = hierarchyService.validateHierarchy(currentRole, hierarchyDisplay.getHierarchy());
                }
                catch (NBException nbe)
                {
//                  request.setAttribute("errmsg","Invalid Hierarchy Entered Or Agency is not set up for this hierarchy.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING,
                            new ActionError("heading.error.hierarchy.inValid"));
                    errors.add(Constants.ERR_MSG,
                            new ActionError("error.hierarchy.inValid"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }
                boolean isValid = false;

                if (tempo != null)
                {
                    isValid = true;
                }
            }    // end of else if which checks for valid Hierarchy
            if (btnSelected.equals("End Role"))
            {
                request.getSession().setAttribute("HierarchyBean",
                        hierarchyForm);
                return mapping.findForward("addRoleInfo");
            }
            else
            {    // Start of "Add Hierarchy..."
                request.getSession().setAttribute("HierarchyBean",
                        hierarchyForm);
                return mapping.findForward("addHierarchy");
            }
        }

                /*
                 * catch (NullPointerException e) {
                 * LOGGER.error("Caught NullPointerException in AddOptionsAction");
                 * e.printStackTrace();
                 * request.setAttribute("errmsg", e.getMessage());
                 * return mapping.findForward("error");
                 * }
                 * catch (NBException e) {
                 * LOGGER.error("Caught NBException in AddOptionsAction");
                 * e.printStackTrace();
                 * request.setAttribute("errmsg", e.getMessage());
                 * return mapping.findForward("error");
                 * }
                 */
        catch (NBException e)
        {
            LOGGER.error("Caught Exception in AddOptionsAction");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING,
                    new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG,
                    new ActionError("error.action.failed"));
            saveErrors(request, errors);

//	    request.setAttribute("errmsg", e.getMessage());
            return mapping.findForward("error");
        }
        finally
        {
            LOGGER.debug("Exiting  eaglsExecute() of AddOptionsAction class");
        }
    }

}
