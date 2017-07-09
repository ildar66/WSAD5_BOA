/**
 * UserProfileRolesAction
 */
package com.boa.eagls.government.controller.action.user;

import java.util.*;

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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionError;
import com.boa.eagls.government.util.Constants;
import org.apache.log4j.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.service.userprofile.*;
import org.apache.struts.action.DynaActionForm;
import com.boa.eagls.government.controller.action.ActionBase;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class UserProfileRolesAction extends ActionBase
{
    private static final Logger LOGGER =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.user.UserProfileRolesAction.class");

    /**
     * Method declaration
     *
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     *
     * @return
     *
     * @exception IOException
     * @exception ServletException
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            ServletException
    {
        LOGGER
                .debug("Entering eaglsExecute() of UserProfileRolesAction class");


        session = new EAGLSSession();

        /* Checking for session expiry */
        /*if (session.getCurrentRole(request) == null)
        {
            return mapping.findForward("sessionExpired");
        }*/

        HierarchyDisplay hd = null;

        // -- gets current user role.
        String loginRole = session.getCurrentBaseRole(request);
        String[] hl;
        String[] desc;

        // -- checking the role
        // -- Dont know what to do with it starting here ...
        DynaActionForm upForm =
                (DynaActionForm) request.getSession()
                .getAttribute("UserProfileBean");
        DynaActionForm hierarchyForm =
                (DynaActionForm) request.getSession()
                .getAttribute("HierarchyBean");
        HierarchyService hierarchyService = new HierarchyService(null);
        UserAccountService accountService = new UserAccountService(null);
        UserProfileService userProfileService = new UserProfileService(null);

        try
        {


            if (loginRole.equals("A_OPC")
                    && hierarchyForm.get("txt_hl1") == null)
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
                catch (NBException r)
                {
                    //request.setAttribute("errmsg", r.getMessage());
                    ActionErrors errors = new ActionErrors();
                    errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
                    errors.add(Constants.ERR_MSG, new ActionError("error.action.failed"));
                    saveErrors(request, errors);
                    return mapping.findForward("error");
                }
            }
            else
            {
                if (hierarchyForm != null)
                {
                    hl = new String[]
                    {
                        (String) hierarchyForm
                            .get("txt_hl0"), // valIn.getValString("txt_hl0"),
                        (String) hierarchyForm
                            .get("txt_hl1"), // valIn.getValString("txt_hl1"),
                        (String) hierarchyForm
                            .get("txt_hl2"), // valIn.getValString("txt_hl2"),
                        (String) hierarchyForm
                            .get("txt_hl3"), // valIn.getValString("txt_hl3"),
                        (String) hierarchyForm
                            .get("txt_hl4"), // valIn.getValString("txt_hl4"),
                        (String) hierarchyForm
                            .get("txt_hl5"), // valIn.getValString("txt_hl5"),
                        (String) hierarchyForm
                            .get("txt_hl6"), // valIn.getValString("txt_hl6"),
                        (String) hierarchyForm
                            .get("txt_hl7"), // valIn.getValString("txt_hl7"),
                        (String) hierarchyForm
                            .get("txt_hl8")    // valIn.getValString("txt_hl8")};
                    };
                    desc = new String[]
                    {
                        (String) hierarchyForm
                            .get("txt_hl1Desc"), // valIn.getValString("txt_desc1"),
                        (String) hierarchyForm
                            .get("txt_hl2Desc"), // valIn.getValString("txt_desc2"),
                        (String) hierarchyForm
                            .get("txt_hl3Desc"), // valIn.getValString("txt_desc3"),
                        (String) hierarchyForm
                            .get("txt_hl4Desc"), // valIn.getValString("txt_desc4"),
                        (String) hierarchyForm
                            .get("txt_hl5Desc"), // valIn.getValString("txt_desc5"),
                        (String) hierarchyForm
                            .get("txt_hl6Desc"), // valIn.getValString("txt_desc6"),
                        (String) hierarchyForm
                            .get("txt_hl7Desc"), // valIn.getValString("txt_desc7"),
                        (String) hierarchyForm
                            .get("txt_hl8Desc")    // valIn.getValString("txt_desc8")};
                    };
                    hd = new HierarchyDisplay(hl, desc);
                    for (int i = 1; i < 9; i++)
                    {
                        hd.setAgencyName(i, desc[i - 1]);
                    }
                }
            }
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
                }
            }

            // -- getting User id from the request
            String uid = (String) upForm.get("txt_userId");

            // -- User Id is not new means already exists.
            if (!userProfileService.isUserIDNew(uid))
            {
                // -- put the proper error message key=errmsg value=message.
                //request.setAttribute("errmsg","The User ID already exists in the database. Please return to the search screen.");
                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.userid.alreadyexists"));
                saveErrors(request, errors);
                mapping.findForward("error");
            }

            // Read prevroles. compare first hierarchy with txt_hl1 coming in
            // Throw exception if not the same
            String hl1_test = null;

            if ((String) request.getSession().getAttribute("prevRoles")
                    != null)
            {

                // get the HL1 from the first hierarchy
                // associated with this role
                hl1_test =
                        (String) request.getSession()
                        .getAttribute("prevRoles");    // --(String)upForm.get("prevRoles");
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

            if (hierarchyForm != null && hierarchyForm.get("txt_hl1") != null)
            {
                new_HL1 = new String((String) hierarchyForm.get("txt_hl1"));
            }
            if ((hl1_test != null) && (new_HL1 != null)
                    && !(hl1_test.toUpperCase().equals(new_HL1.toUpperCase())))
            {
                //request.setAttribute("errmsg","HL1 Does Not Match User's Profile <BR> Hierarchy Level 1");
                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.hierarchy1.notmatch"));
                saveErrors(request, errors);
                mapping.findForward("error");
            }

            // get values of txt_role and prevRoles to determine current state
            // -- taking value from input request of Role
            String role = request.getParameter("txt_role");
            String step = (String) request.getAttribute("step");

            if (step == null)
            {
                step = "one";

                // set program number to logged-in user's program number if user is A_OPC
                // -- Check to see if login role is A/OPC
            }
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
                String baseRole = userProfileService.getBaseRole(role);

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
            String prevRoles =
                    (String) request.getSession()
                    .getAttribute("prevRoles");    // --(String)upForm.get("prevRoles");

            request.setAttribute("user", loginRole);
            if (!loginRole.equals("GCSU"))
            {
                request
                        .setAttribute("programNumber",
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
                upForm.set("currentRole", role);
            }
            else
            {	     // coming from add attribute page
                String coreRole =
                        userProfileService
                        .getBaseRole((String) upForm.get("currentRole"));

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
                    prevRoles =
                            UserProfileUtil
                            .encodeAccountNumber((String) hierarchyForm
                            .get("txt_accountNo"), prevRoles);
                }

                // -- else if role is GSA or FMS goto Add Hierarchy Screen
                else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                        || coreRole.equals("A") || coreRole.equals("TC")
                        || coreRole.equals("NFC"))
                {
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
                    String[] h = hd.getSegments();
                    String[] d = hd.getDescriptions();

                    prevRoles = UserProfileUtil.encodeHierarchy(h, d,
                            prevRoles);

                    // -- Get Program Types
                    String progTypes[] =
                            request.getParameterValues("programTypes");

                    if (progTypes.length > 0)
                    {
                        String[] pTypes = new String[progTypes.length];

                        System.arraycopy(progTypes, 0, pTypes, 0,
                                progTypes.length);
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
            request.getSession().setAttribute("step", "two");


/* End */

            String coreRole = userProfileService.getBaseRole((String) upForm.get("currentRole"));

            if (coreRole == null)
            {
                //request.setAttribute("errmsg", "Role does not exist.");
                ActionErrors errors = new ActionErrors();
                errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
                errors.add(Constants.ERR_MSG, new ActionError("error.role.notexists"));
                saveErrors(request, errors);
                return mapping.findForward("error");
            }
            else if (coreRole.equals("GCSU"))
            {
                upForm.set("txt_role", (String) upForm.get("currentRole"));

                return mapping.findForward("addRoleInfoAction");
            }
            else if (coreRole.equals("AH"))
            {
                upForm.set("txt_role", (String) upForm.get("currentRole"));
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addAccount");

                return mapping.findForward("addAccountNumber");
            }
            else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                    || coreRole.equals("A") || coreRole.equals("TC")
                    || coreRole.equals("NFC"))
            {
                upForm.set("txt_role", (String) upForm.get("currentRole"));
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addHierarchy");

                return mapping.findForward("addHierarchy");
            }
            else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                    || coreRole.equals("TDO") || coreRole.equals("NB_ADM")
                    || coreRole.equals("NB_ACCTG") || coreRole.equals("CL"))
            {
                upForm.set("txt_role", (String) upForm.get("currentRole"));
                request.setAttribute(com.boa.eagls.government.util.Constants.HEADER_SUBHEADING, "heading.userprofile.addHierarchyPT");

                return mapping.findForward("addHierarchyPT");
            }
            //request.setAttribute("errmsg", "Illegal role.");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
            errors.add(Constants.ERR_MSG, new ActionError("error.role.illegal"));
            saveErrors(request, errors);

            return mapping.findForward("error");
        }
        catch (NBException nbe)
        {
            LOGGER.error("Caught NBException in UserProfileRolesAction");
            //request.setAttribute("errmsg", nbe.getMessage());
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG, new ActionError("error.action.failed"));
            saveErrors(request, errors);
            return mapping.findForward("error");
        }
        catch (Exception e)
        {
            LOGGER.error("Caught Exception in UserProfileRolesAction");
            //request.setAttribute("errmsg", e.getMessage());
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG, new ActionError("error.action.failed"));
            saveErrors(request, errors);
            return mapping.findForward("error");
        }
        finally
        {
            LOGGER
                    .debug("Exiting  eaglsExecute() of UserProfileRolesAction class");
        }
    }

}
