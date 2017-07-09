/**
 * SetupUserProfileSubmitAction
 */
package com.boa.eagls.government.controller.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import java.util.ArrayList;

import com.boa.eagls.government.controller.formbean.user.HierarchyForm;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.service.userprofile.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.service.userprofile.*;
import com.boa.eagls.government.util.*;
import com.boa.eagls.government.service.*;

import java.sql.*;

import com.boa.eagls.government.exceptions.*;
import org.apache.log4j.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import org.apache.struts.action.DynaActionForm;
import com.boa.eagls.government.controller.action.ActionBase;

/**
 * Action that is called at the end of the user case to populate data in
 * database.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class SetupUserProfileSubmitAction extends ActionBase
{

    // Set Logger
    private static Logger logger =Logger.getLogger(SetupUserProfileSubmitAction.class);

    /**
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
        logger
                .debug("Enetering SetupUserProfileSubmitAction - perform() .....");


        String userId = null;
        String firstName = null;
        String lastName = null;
        String status = null;
        String strReportsAccess = null;

        try
        {
            session = new EAGLSSession();
            UserProfileService userProfileService =
                    new UserProfileService(null);
            DynaActionForm upForm =
                    (DynaActionForm) request.getSession()
                    .getAttribute("UserProfileBean");
            userId = (String) upForm.get("txt_userId");
            firstName = (String) upForm.get("txt_firstName");
            lastName = (String) upForm.get("txt_lastName");
            status = (String) upForm.get("status");
            strReportsAccess = (String) upForm.get("txt_reportsAccess");

            logger.debug("strReportsAccess === " + strReportsAccess);
            boolean reportsAccess = false;

            // Setting the reports access
            if ((strReportsAccess == null) || (strReportsAccess.equals("")))
            {
                reportsAccess = false;
            }
            else
            {
                reportsAccess = (strReportsAccess.equalsIgnoreCase("YES"));
            }
            try
            {
                String encodedSentence =
                        (String) request.getSession()
                        .getAttribute("prevRoles");    // (String)upForm.get("prevRoles");
                String strTempCurrentBaseRole =
                        session.getCurrentBaseRole(request);

                if (strTempCurrentBaseRole == null)
                {
                    try
                    {
                        strTempCurrentBaseRole =
                                userProfileService.getBaseRole(session.getCurrentRole(request));
                    }
                    catch (Exception ex)
                    {
                        logger.error(ex.getMessage(), ex);
                        throw new NBError(ex.getMessage());
                    }
                }
                userProfileService.populateUserProfile(userId, firstName,
                        lastName, status,
                        reportsAccess);
                if (strTempCurrentBaseRole.equals("GCSU"))
                {
                    userProfileService.create();
                }
                else if (strTempCurrentBaseRole.equals("A_OPC"))
                {
                    userProfileService.create();
                }
                else
                {
                    // return invalid Role error
                    //request.setAttribute("errmsg", "Invalid role entered.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.inValid"));
                    saveErrors(request, errors);
                    return mapping.findForward("error");
                }
                try
                {
                    userProfileService.setUserRoles(encodedSentence,
                            session.getCurrentRole(request),
                            session.getCurrentHierarchy(request),
                            session.getUserID(request));
                    userProfileService.saveUserProfile(session.getUserID(request));
                }
                catch (NBException ex)
                {
                    logger.error(ex.getMessage(), ex);
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.errorReturned"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.hierarchy.alreadyAssigned.message"));
                    errors.add(Constants.ERR_DESC, new ActionError("error.hierarchy.alreadyAssigned.desc", ex.getDetailedMessage()));
                    errors.add(Constants.ERR_SCODE, new ActionError("error.hierarchy.alreadyAssigned.scode", "2"));
                    saveErrors(request, errors);
                    return mapping.findForward("systemWarning");

                }

                // Getting cluster Name to display if Reports Access exist
                String clusterName = "";		     // Used for stored cluster info
                String reportsMsg = "";    // Used if reports access is allowed

                if (reportsAccess)
                {
                    try
                    {
                        clusterName =
                                userProfileService.retrieveClusterName(userId);
                        reportsMsg = ("<br> The cluster name (APS name) for "
                                + userId + " is <b>" + clusterName
                                + "</b>.");
                    }
                    catch (NBException e)
                    {
                        logger.error(e.getMessage(), e);
                        reportsMsg =
                                ("<br> Could not assign Reports Access to User ID.  Please make sure UserID has a role that has access to reports");
                    }
                }
                else
                {
                    strReportsAccess = "NO";
                }

                // Log REPORT_ACCESS
                if (strReportsAccess != null && !strReportsAccess.equals(""))
                {
                    if (session.getCurrentBaseRole(request).equals("GCSU"))
                    {
                        request
                                .setAttribute("msg",
                                        "You have successfully submitted User Profile data for "
                                + firstName + " " + lastName + ".");
                        request.setAttribute("reportsMsg", reportsMsg);
                        logger
                                .debug("return from SetupUserProfileSubmitAction - perform() to success.....");

                        this.removeAttr(request);
                        return mapping.findForward("success");
                    }
                    else if (session.getCurrentBaseRole(request)
                            .equals("A_OPC"))
                    {
                        request
                                .setAttribute("msg",
                                        "Your User Profile for "
                                + firstName + " " + lastName
                                + " has been submitted to the GCSU Work Queue for approval.");
                        request.setAttribute("reportsMsg", reportsMsg);
                        logger
                                .debug("retutn from SetupUserProfileSubmitAction - perform() to success.....");

                        // -request.getSession().removeAttribute("prevRoles");
                        this.removeAttr(request);
                        return mapping.findForward("success");
                    }
                    else
                    {					     // unexpect role - display error message
                        //request.setAttribute("errmsg", "Unexpected Role.");

                        logger.debug("return from SetupUserProfileSubmitAction - perform() to error.....");

                        ActionErrors errors = new ActionErrors();
                        errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                        errors.add(Constants.ERR_MSG, new ActionError("error.role.unexpectedRole"));
                        saveErrors(request, errors);

                        return mapping.findForward("error");
                    }
                }
            }
            catch (NBException e)
            {
                logger.error(e.getMessage(), e);
                //request.setAttribute("errmsg", e.getMessage());
                logger.debug("return from SetupUserProfileSubmitAction - perform() to error.....");

                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
                errors.add(Constants.ERR_MSG, new ActionError("error.tofind"));
                saveErrors(request, errors);
                return mapping.findForward("error");
            }
            return null;
        }
        catch (NullPointerException e)
        {
            logger.error(e.getMessage(), e);
            //request.setAttribute("errmsg","The information submitted already exists. Please check your data.");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.errorReturned"));
            errors.add(Constants.ERR_MSG, new ActionError("error.userprofile.alreadyExists.message"));
            errors.add(Constants.ERR_DESC, new ActionError("error.userprofile.alreadyExists.desc", "ORA-00001: unique constraint (GSA.XPKUSER_PROFILE) violated (Userid is already in use.)/USER_PROFILE_SETUP.INSERT_NEW_USER"));
            errors.add(Constants.ERR_SCODE, new ActionError("error.userprofile.alreadyExists.scode", "1"));
            saveErrors(request, errors);

            return mapping.findForward("systemWarning");
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            //request.setAttribute("errmsg","An error occured while processing your request.");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.errorReturned"));
            errors.add(Constants.ERR_MSG, new ActionError("error.general"));
            saveErrors(request, errors);

            return mapping.findForward("error");
        }
    }

    /**
     * Removes attributes from session.
     *
     *
     * @param request
     */
    private void removeAttr(HttpServletRequest request)
    {
        request.getSession().removeAttribute("prevRoles");
        request.getSession().removeAttribute("UserProfileBean");
        request.getSession().removeAttribute("HierarchyBean");
    }

}
