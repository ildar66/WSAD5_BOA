/**
 * UserProfileAction
 */
package com.boa.eagls.government.controller.action.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;

import java.util.*;
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
import com.boa.eagls.government.controller.action.ActionBase;
import org.apache.struts.action.DynaActionForm;

/**
 * Applogic code to bring up appropriate role hierarchy/acct # entry page.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class UserProfileAction extends ActionBase
{
    private static final Logger LOGGER =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.user.UserProfileAction.class");

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @pre $none
     * @post $none
     * @invariant $none
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            ServletException
    {
        LOGGER.debug("Entering eaglsExecute() of UserProfileAction class");
        try
        {
            session = new EAGLSSession();


            UserProfileService userProfileService =
                    new UserProfileService(null);
            HierarchyService hierarchyService = new HierarchyService(null);
            UserAccountService accountService = new UserAccountService(null);
            HierarchyDisplay hd = null;
            String loginRole =
                    session.getCurrentBaseRole(request);
            String[] hl;
            String[] desc;
            DynaActionForm upForm = (DynaActionForm) form;

            request.getSession().setAttribute("UserProfileBean", upForm);

            // -- checking the role
            // -- Dont know what to do with it starting here ...
            if (loginRole.equals("A_OPC")
                    && request.getParameter("txt_hl1") == null)
            {
                try
                {
                    int[] hierarchy =
                            new HierarchyDisplay(session
                            .getCurrentHierarchy(request)).getValues();
                    String hierarchyNbr =
                            hierarchyService.getHierarchyNumber(hierarchy);

                    hd =
                            accountService
                            .getHierarchyAgencyNames(new Integer(hierarchyNbr)
                            .intValue());
                }
                catch (NBException e)
                {
                    LOGGER.error(e.getMessage(), e);
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.action.failed"));
                    saveErrors(request, errors);
                    return mapping.findForward("error");
                }
            }
            else
            {
                hl = new String[]
                {
                    request.getParameter("txt_hl0"),
                    request.getParameter("txt_hl1"),
                    request.getParameter("txt_hl2"),
                    request.getParameter("txt_hl3"),
                    request.getParameter("txt_hl4"),
                    request.getParameter("txt_hl5"),
                    request.getParameter("txt_hl6"),
                    request.getParameter("txt_hl7"),
                    request.getParameter("txt_hl8")
                };
                desc = new String[]
                {
                    request.getParameter("txt_desc1"),
                    request.getParameter("txt_desc2"),
                    request.getParameter("txt_desc3"),
                    request.getParameter("txt_desc4"),
                    request.getParameter("txt_desc5"),
                    request.getParameter("txt_desc6"),
                    request.getParameter("txt_desc7"),
                    request.getParameter("txt_desc8")
                };
                hd = new HierarchyDisplay(hl, desc);
                for (int i = 1; i < 9; i++)
                {
                    hd.setAgencyName(i, desc[i - 1]);
                }
            }

            // --Hashtable map = new Hashtable();
            // Bug Fix for Discrepancy # : LAC3467 - CS
            // String previousRoles = request.getParameter("prevRoles");
            String previousRoles =
                    (String) request.getSession().getAttribute("prevRoles");

            if (previousRoles != null)
            {
                previousRoles = previousRoles.toUpperCase();
                String baseRole =
                        userProfileService.getBaseRole(previousRoles);

                if (baseRole == null || baseRole.equals("NB_ADM")
                        || baseRole.equals("NB_ACCTG")
                        || baseRole.equals("TDO") || baseRole.equals("CL")
                        || baseRole.equals("TC") || baseRole.equals("A")
                        || baseRole.equals("NFC") || baseRole.equals("DBO")
                        || baseRole.equals("A_OPC")
                        || baseRole.equals("GCSU"))
                {

                    // --map = new Hashtable();
                }
            }

            // --String uid = request.getParameter("txt_userId");
            String uid = (String) upForm.get("txt_userId");


            if (!userProfileService.isUserIDNew(uid))
            {
                //request.setAttribute("errmsg","The User ID already exists in the database. Please return to the search screen.");

                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.userid.alreadyexists"));
                saveErrors(request, errors);

                return mapping.findForward("error");
            }

            String hl1_test = null;

            if ((String) request.getSession().getAttribute("prevRoles")
                    != null)
            {
                hl1_test =
                        (String) request.getSession()
                        .getAttribute("prevRoles");    // --request.getParameter("prevRoles");
                int start = 0, finish = 0;

                start = hl1_test.indexOf("-");
                finish = hl1_test.indexOf("-", start + 1);
                if (finish > start)
                {
                    hl1_test = hl1_test.substring(++start, finish);
                }
                else
                {
                    hl1_test = null;
                }
            }
            String new_HL1 = null;

            if (request.getParameter("txt_hl1") != null)
            {
                new_HL1 = request.getParameter("txt_hl1");
            }
            if ((hl1_test != null) && (new_HL1 != null)
                    && !(hl1_test.toUpperCase().equals(new_HL1.toUpperCase())))
            {
                //request.setAttribute("errmsg","HL1 Does Not Match User's Profile <BR> Hierarchy Level 1");

                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.hierarchy1.notmatch"));
                saveErrors(request, errors);

                return mapping.findForward("error");
            }

            // --String role = request.getParameter("txt_role");
            String role = (String) upForm.get("txt_role");

            if (loginRole.equals("A_OPC"))
            {
                request
                        .setAttribute("txt_hl0",
                                String
                        .valueOf(session
                        .getCurrentHierarchy(request)[0]
                        .getNumber()));
                if (role != null)
                {
                    role = role.toUpperCase();
                }
                String baseRole = null;

                try
                {
                    baseRole = userProfileService.getBaseRole(role);
                }
                catch (NBException e)
                {
                    LOGGER.error(e.getMessage(), e);
                }

                // -- If role is GCSU or null return error page
                if (baseRole != null && baseRole.equals("GCSU"))
                {
                    //request.setAttribute("errmsg","An A_OPC cannot assign a GCSU role.  Please re-enter role value.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.assignrole"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }
            }

            // --String prevRoles = request.getParameter("prevRoles");
            String prevRoles =
                    (String) request.getSession().getAttribute("prevRoles");

            // --map.put("user", loginRole);
            if (!loginRole.equals("GCSU"))
            {
                request.setAttribute("programNumber",
                        String
                        .valueOf(session
                        .getCurrentHierarchy(request)[0]
                        .getNumber()));
            }
            else
            {
                request.setAttribute("programNumber", "");
            }
            if (prevRoles == null)
            {	     // first time through
                prevRoles = "";
                role = role.toUpperCase();
                String baseRole = userProfileService.getBaseRole(role);

                if (baseRole == null)
                {
                    //request.setAttribute("errmsg", "Role does not exist.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.notexists"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }
                if (baseRole.equals("FMS") || baseRole.equals("GSA")
                        || baseRole.equals("TC") || baseRole.equals("NFC")
                        || baseRole.equals("A"))
                {
                }
                else if (baseRole.equals("A_OPC") || baseRole.equals("TDO")
                        || baseRole.equals("DBO")
                        || baseRole.equals("NB_ACCTG")
                        || baseRole.equals("NB_ADM")
                        || baseRole.equals("CL"))
                {
                }
                else
                {    // role = "AH"
                }
            }

            // -- Checking for Roles that has been assigned
            if (role != null)
            {	     // coming from user info or add role page
                role = role.toUpperCase();
                String baseRole = userProfileService.getBaseRole(role);

                // --map.put("baseRole", baseRole);
                upForm.set("baseRole", baseRole);
                if (baseRole == null)
                {
                    //request.setAttribute("errmsg", "Role does not exist.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.notexists"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }
                if (baseRole == null
                        || !(baseRole.equals("GSA") || baseRole.equals("FMS")
                        || baseRole.equals("AH")
                        || baseRole.equals("A_OPC")
                        || baseRole.equals("DBO")
                        || baseRole.equals("TDO")
                        || baseRole.equals("NB_ADM")
                        || baseRole.equals("NB_ACCTG")
                        || baseRole.equals("CL")
                        || baseRole.equals("GCSU")
                        || baseRole.equals("A") || baseRole.equals("TC")
                        || baseRole.equals("NFC")))
                {
                    //request.setAttribute("errmsg","You have entered an invalid role.  Please re-enter role value.");

                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.inValid"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }
                if (role.equals("GCSU"))
                {
                    ;
                }
                prevRoles = UserProfileUtil.encodeRole(role, prevRoles);
                request.setAttribute("currentRole", role);
                upForm.set("currentRole", role);
            }
            else
            {	     // coming from add attribute page
                String coreRole = userProfileService.getBaseRole(role);

                if (coreRole == null)
                {
                    //request.setAttribute("errmsg", "Role does not exist.");
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.role.notexists"));
                    saveErrors(request, errors);

                    return mapping.findForward("error");
                }

                // -- if Role is AH then Goto Add Account Number Screen
                if (coreRole.equals("AH"))
                {

                    // --map.put("screenTitle-2", "Add Account Number");
                    // prevRoles = UserProfileUtil.encodeAccountNumber(request.getParameter("txt_accountNo"), prevRoles);
                    prevRoles =
                            UserProfileUtil
                            .encodeAccountNumber((String) upForm
                            .get("txt_accountNo"), prevRoles);
                }

                // -- else if role is GSA or FMS goto Add Hierarchy Screen
                else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                        || coreRole.equals("A") || coreRole.equals("TC")
                        || coreRole.equals("NFC"))
                {

                    // --map.put("screenTitle-2", "Add Hierarchy");
                    String[] h = hd.getSegments();
                    String[] d = hd.getDescriptions();

                    prevRoles = UserProfileUtil.encodeHierarchy(h, d,
                            prevRoles);
                }

                // -- if role is A/OPC or DBO or TDO then goto AddHierarcy and Program Type (PT)
                else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                        || coreRole.equals("TDO")
                        || coreRole.equals("NB_ADM")
                        || coreRole.equals("NB_ACCTG")
                        || coreRole.equals("CL"))
                {

                    // -map.put("screenTitle-2", "Add Hierarchy and Program Type");
                    String[] h = hd.getSegments();
                    String[] d = hd.getDescriptions();

                    prevRoles = UserProfileUtil.encodeHierarchy(h, d,
                            prevRoles);

                    // -- Get Program Types
                    String progTypes = request.getParameter("programType");

                    if (progTypes.length() > 0)
                    {
                        StringTokenizer st = new StringTokenizer(progTypes,
                                ";", false);
                        int tokenCount = st.countTokens();
                        String[] pTypes = new String[tokenCount];

                        for (int i = 0; i < tokenCount; i++)
                        {
                            pTypes[i] = st.nextToken();
                        }
                        prevRoles = UserProfileUtil.encodeProgramType(pTypes,
                                prevRoles);
                    }
                    else
                    {

                        // illegal!
                    }
                }
            }
            request.getSession().setAttribute("prevRoles", prevRoles);
            request.getSession().setAttribute("step", "one");
            String coreRole = userProfileService.getBaseRole(role);

            saveToken(request);


            if (coreRole == null)
            {
                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.role.notexists"));
                saveErrors(request, errors);

                //request.setAttribute("errmsg", "Role does not exist.");
                return mapping.findForward("error");
            }
            else if (coreRole.equals("GCSU"))
            {
                Vector verificationPage = new Vector();
                Vector results = new Vector();

                results.add(role);
                Vector tempData1 = new Vector();

                tempData1.add("none");
                results.add(tempData1);
                verificationPage.add(results);
                request.setAttribute("verificationPage", verificationPage);
                request.setAttribute("roleName", role);
                request.getSession().setAttribute("UserProfileBean", upForm);
                return mapping.findForward("UserProfileConfirmation");
            }
            else if (coreRole.equals("AH"))
            {
                request.getSession().setAttribute("UserProfileBean", upForm);
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addDefaultAccount");
                return mapping.findForward("UserProfileAddAccountNumber");
            }
            else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                    || coreRole.equals("A") || coreRole.equals("TC")
                    || coreRole.equals("NFC"))
            {
                request.getSession().setAttribute("UserProfileBean", upForm);
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addDefaultHierarchy");
                return mapping.findForward("UserProfileAddHierarchy");
            }
            else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                    || coreRole.equals("TDO") || coreRole.equals("NB_ADM")
                    || coreRole.equals("NB_ACCTG") || coreRole.equals("CL"))
            {
                request.getSession().setAttribute("UserProfileBean", upForm);
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addDefaultHierarchyPT");
                return mapping.findForward("UserProfileAddHierarchyPT");
            }
        }
        catch (Exception e)
        {
            LOGGER.error("Caught Exception in UserProfileAction", e);
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG, new ActionError("error.action.failed"));
            saveErrors(request, errors);
            //request.setAttribute("errmsg", e.getMessage());
            mapping.findForward("error");
        }
        finally
        {
            LOGGER.debug("Exiting  eaglsExecute() of UserProfileAction class");
        }

        ActionErrors errors = new ActionErrors();
        errors.add(Constants.ERR_HEADING, new ActionError("heading.error.role.inValid"));
        errors.add(Constants.ERR_MSG, new ActionError("error.role.illegal"));
        saveErrors(request, errors);

        //request.setAttribute("errmsg", "Illegal role.");
        return mapping.findForward("error");

    }

}
