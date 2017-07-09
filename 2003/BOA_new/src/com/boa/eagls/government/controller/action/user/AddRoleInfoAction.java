/**
 * AddRoleInfoAction
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
 * AddRoleInfoAction
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class AddRoleInfoAction extends ActionBase
{

    /**
     * Set Logger
     */
    private static final Logger LOGGER =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.user.AddOptionsAction.class");

    /**
     * Add information about roles for user profile
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws ServletException
     * @pre User is logged in and have sufficient rights
     * @post Information about a users role is enetered
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            ServletException
    {
        try
        {
            LOGGER
                    .debug("Entering eaglsExecute() of AddRoleInfoAction class");

            session = new EAGLSSession();

            String tempHierString = null;
            UserProfileService userProfileService =
                    new UserProfileService(null);

            // --Hashtable map = new Hashtable();
            String programType[] = null;

            // --String prevRoles = request.getParameter("prevRoles");
            // --String coreRole = request.getParameter("currentRole");
            // --String roleName = request.getParameter("currentRole");
            // --String txt_accountNo = request.getParameter("txt_accountNo");
            DynaActionForm upForm =
                    (DynaActionForm) request.getSession()
                    .getAttribute("UserProfileBean");
            DynaActionForm hierarchyForm =
                    (DynaActionForm) request.getSession()
                    .getAttribute("HierarchyBean");
            String prevRoles =
                    (String) request.getSession().getAttribute("prevRoles");

            // --prevRoles = (String)upForm.get("prevRoles");
            String coreRole = (String) upForm.get("currentRole");
            String roleName = (String) upForm.get("currentRole");
            String txt_accountNo = null;

            // Read prevroles. compare first hierarchy with txt_hl1 coming in
            // return error screen if not the same
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

            // --if (request.getAttribute("txt_hl1") != null) {
            if (hierarchyForm != null
                    && (String) hierarchyForm.get("txt_hl1") != null)
            {
                try
                {
                    new_HL1 = (String) hierarchyForm.get("txt_hl1");
                }
                catch (ClassCastException e)
                {
                    LOGGER.error("Caught ClassCastException in AddOptionsAction", e);
                }
            }
            if ((hl1_test != null) && (new_HL1 != null)
                    && !(hl1_test.toUpperCase().equals(new_HL1.toUpperCase())))
            {
                request
                        .setAttribute("errmsg",
                                "HL1 Does Not Match User's Profile <BR> Hierarchy Level 1");
            }

            // set program number to logged-in user's program number if user is A_OPC
            if (session.getCurrentBaseRole(request).equals("A_OPC"))
            {
                request
                        .setAttribute("txt_hl0",
                                String
                        .valueOf(session
                        .getCurrentHierarchy(request)[0]
                        .getNumber()));
            }

            coreRole = userProfileService.getBaseRole(coreRole);
            if (coreRole != null)
            {

                // if (coreRole.equals("GCSU")) { // do nothing - no new data
                // }
                // else
                if (coreRole.equals("AH"))
                {
                    txt_accountNo =
                            (String) hierarchyForm.get("txt_accountNo");
                    prevRoles =
                            UserProfileUtil.encodeAccountNumber(txt_accountNo,
                                    prevRoles);
                }
                else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                        || coreRole.equals("A") || coreRole.equals("TC")
                        || coreRole.equals("NFC"))
                {
                    String[] hl =
                            {
                                (String) hierarchyForm
                            .get("txt_hl0"), // */request.getParameter("txt_hl0"),
                                (String) hierarchyForm
                            .get("txt_hl1"), // */request.getParameter("txt_hl1"),
                                (String) hierarchyForm
                            .get("txt_hl2"), // */request.getParameter("txt_hl2"),
                                (String) hierarchyForm
                            .get("txt_hl3"), // */request.getParameter("txt_hl3"),
                                (String) hierarchyForm
                            .get("txt_hl4"), // */request.getParameter("txt_hl4"),
                                (String) hierarchyForm
                            .get("txt_hl5"), // */request.getParameter("txt_hl5"),
                                (String) hierarchyForm
                            .get("txt_hl6"), // */request.getParameter("txt_hl6"),
                                (String) hierarchyForm
                            .get("txt_hl7"), // */request.getParameter("txt_hl7"),
                                (String) hierarchyForm
                            .get("txt_hl8")    // */request.getParameter("txt_hl8")
                            };
                    String[] desc =
                            {
                                (String) hierarchyForm
                            .get("txt_hl1Desc"), // */(String) request.getAttribute("txt_desc1"),
                                (String) hierarchyForm
                            .get("txt_hl2Desc"), // */(String) request.getAttribute("txt_desc2"),
                                (String) hierarchyForm
                            .get("txt_hl3Desc"), // */(String) request.getAttribute("txt_desc3"),
                                (String) hierarchyForm
                            .get("txt_hl4Desc"), // */(String) request.getAttribute("txt_desc4"),
                                (String) hierarchyForm
                            .get("txt_hl5Desc"), // */(String) request.getAttribute("txt_desc5"),
                                (String) hierarchyForm
                            .get("txt_hl6Desc"), // */(String) request.getAttribute("txt_desc6"),
                                (String) hierarchyForm
                            .get("txt_hl7Desc"), // */(String) request.getAttribute("txt_desc7"),
                                (String) hierarchyForm
                            .get("txt_hl8Desc")    // */(String) request.getAttribute("txt_desc8")
                            };

                    prevRoles = UserProfileUtil.encodeHierarchy(hl, desc,
                            prevRoles);
                }
                else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                        || coreRole.equals("TDO")
                        || coreRole.equals("NB_ADM")
                        || coreRole.equals("NB_ACCTG")
                        || coreRole.equals("CL"))
                {
                    String[] hl =
                            {
                                (String) hierarchyForm
                            .get("txt_hl0"), // */request.getParameter("txt_hl0"),
                                (String) hierarchyForm
                            .get("txt_hl1"), // */request.getParameter("txt_hl1"),
                                (String) hierarchyForm
                            .get("txt_hl2"), // */request.getParameter("txt_hl2"),
                                (String) hierarchyForm
                            .get("txt_hl3"), // */request.getParameter("txt_hl3"),
                                (String) hierarchyForm
                            .get("txt_hl4"), // */request.getParameter("txt_hl4"),
                                (String) hierarchyForm
                            .get("txt_hl5"), // */request.getParameter("txt_hl5"),
                                (String) hierarchyForm
                            .get("txt_hl6"), // */request.getParameter("txt_hl6"),
                                (String) hierarchyForm
                            .get("txt_hl7"), // */request.getParameter("txt_hl7"),
                                (String) hierarchyForm
                            .get("txt_hl8")    // */request.getParameter("txt_hl8")
                            };
                    String[] desc =
                            {
                                (String) hierarchyForm
                            .get("txt_hl1Desc"), // */(String) request.getAttribute("txt_desc1"),
                                (String) hierarchyForm
                            .get("txt_hl2Desc"), // */(String) request.getAttribute("txt_desc2"),
                                (String) hierarchyForm
                            .get("txt_hl3Desc"), // */(String) request.getAttribute("txt_desc3"),
                                (String) hierarchyForm
                            .get("txt_hl4Desc"), // */(String) request.getAttribute("txt_desc4"),
                                (String) hierarchyForm
                            .get("txt_hl5Desc"), // */(String) request.getAttribute("txt_desc5"),
                                (String) hierarchyForm
                            .get("txt_hl6Desc"), // */(String) request.getAttribute("txt_desc6"),
                                (String) hierarchyForm
                            .get("txt_hl7Desc"), // */(String) request.getAttribute("txt_desc7"),
                                (String) hierarchyForm
                            .get("txt_hl8Desc")    // */(String) request.getAttribute("txt_desc8")
                            };

                    prevRoles = UserProfileUtil.encodeHierarchy(hl, desc,
                            prevRoles);
                    String[] progTypes =
                            request.getParameterValues("programTypes");

                    programType = progTypes;
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
            if (!session.getCurrentBaseRole(request).equals("GCSU")
                    && !session.getCurrentBaseRole(request).equals("A_OPC"))
            {
                request.setAttribute("nonGCSU", "");
            }
            else
            {

                // --request.setAttribute("txt_reportsAccess", request.getParameter("txt_reportsAccess"));
                request
                        .setAttribute("txt_reportsAccess",
                                (String) upForm.get("txt_reportsAccess"));
            }
            Vector verificationPage = new Vector();
            Vector results = new Vector();
            Vector tempData1, tempData2, tempData3;

            // decode role information
            RoleUtil[] roles = UserProfileUtil.decodeRole(prevRoles);

            for (int i = 0; i < roles.length; i++)
            {
                results.add(roles[i].roleType);

                coreRole =
                        userProfileService.getBaseRole(roles[i].roleType);

                if (coreRole.equals("GCSU"))
                {    // no additional data
                    tempData1 = new Vector();
                    tempData1.add("none");
                    results.add(tempData1);
                }
                else if (coreRole.equals("AH"))
                {
                    AcctNumberUtil[] acs =
                            UserProfileUtil
                            .decodeAccountNumber(roles[i].roleRemainder);

                    tempData1 = new Vector();
                    tempData1.add("Account Number");
                    tempData2 = new Vector();
                    for (int j = 0; j < acs.length; j++)
                    {

                        // --tempData2.add("name=Account Number;value=" + acs[j].acctNumber);
                        tempData2.add("Account Number");
                        tempData2.add(acs[j].acctNumber);
                    }
                    tempData1.add(tempData2);
                    results.add(tempData1);
                }
                else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                        || coreRole.equals("TDO")
                        || coreRole.equals("NB_ADM")
                        || coreRole.equals("NB_ACCTG")
                        || coreRole.equals("CL"))
                {
                    HierarchyPTUtil[] hierPTs =
                            UserProfileUtil
                            .decodeHierarchyPT(roles[i].roleRemainder);

                    tempData1 = new Vector();
                    tempData1.add("Hierarchy/Program Type");
                    tempData2 = new Vector();
                    for (int j = 0; j < hierPTs.length; j++)
                    {
                        String hierString =
                                "" + hierPTs[j].hierarchy[0].getNumber() + "-"
                                + hierPTs[j].hierarchy[1].getNumber() + "-"
                                + hierPTs[j].hierarchy[2].getNumber() + "-"
                                + hierPTs[j].hierarchy[3].getNumber() + "-"
                                + hierPTs[j].hierarchy[4].getNumber() + "-"
                                + hierPTs[j].hierarchy[5].getNumber() + "-"
                                + hierPTs[j].hierarchy[6].getNumber() + "-"
                                + hierPTs[j].hierarchy[7].getNumber() + "-"
                                + hierPTs[j].hierarchy[8].getNumber();

                        tempHierString = hierString;

                        // --tempData2.add("name=Hierarchy;value=" + hierString);
                        tempData2.add("Hierarchy");
                        tempData2.add(hierString);
                        tempData3 = new Vector();
                        for (int k = 0; k < hierPTs[j].programType.size();
                             k++)
                        {

                            // --tempData3.add("name=Program Type;value=" + hierPTs[j].programType.elementAt(k));
                            tempData3.add("Program Type");
                            tempData3
                                    .add(hierPTs[j].programType.elementAt(k));
                        }
                        tempData2.add(tempData3);
                    }
                    tempData1.add(tempData2);
                    results.add(tempData1);
                }
                else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                        || coreRole.equals("A") || coreRole.equals("TC")
                        || coreRole.equals("NFC"))
                {
                    HierarchyUtil[] hiers =
                            UserProfileUtil
                            .decodeHierarchy(roles[i].roleRemainder);

                    tempData1 = new Vector();
                    tempData1.add("Hierarchy");
                    tempData2 = new Vector();
                    for (int j = 0; j < hiers.length; j++)
                    {
                        String hierString =
                                "" + hiers[j].hierarchy[0].getNumber() + "-"
                                + hiers[j].hierarchy[1].getNumber() + "-"
                                + hiers[j].hierarchy[2].getNumber() + "-"
                                + hiers[j].hierarchy[3].getNumber() + "-"
                                + hiers[j].hierarchy[4].getNumber() + "-"
                                + hiers[j].hierarchy[5].getNumber() + "-"
                                + hiers[j].hierarchy[6].getNumber() + "-"
                                + hiers[j].hierarchy[7].getNumber() + "-"
                                + hiers[j].hierarchy[8].getNumber();

                        tempHierString = hierString;

                        // --tempData2.add("name=Hierarchy;value=" + hierString);
                        tempData2.add("Hierarchy");
                        tempData2.add(hierString);
                    }
                    tempData1.add(tempData2);
                    results.add(tempData1);
                }

                // else { // illegal!
                // }
            }
            verificationPage.add(results);
            request.setAttribute("HierarchyString", tempHierString);
            request.setAttribute("verificationPage", verificationPage);
            request.setAttribute("accountNumber", txt_accountNo);
            request.setAttribute("programType", programType);
            request.setAttribute("roleName", roleName);
            request.setAttribute("clusterName",
                    userProfileService.getClusterName());

            // --request.setAttribute("map", map);
            request.getSession().setAttribute("HierarchyForm", hierarchyForm);
            return mapping.findForward("success");
        }
        catch (NullPointerException e)
        {
            LOGGER.error("Caught NullPointerException in AddOptionsAction");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING,
                    new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG,
                    new ActionError("error.action.failed"));
            saveErrors(request, errors);


            return mapping.findForward("error");
        }
        catch (ClassCastException e)
        {

            LOGGER.error("Caught ClassCastException in AddOptionsAction");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING,
                    new ActionError("heading.error.action.failed"));
            errors.add(Constants.ERR_MSG,
                    new ActionError("error.action.failed"));
            saveErrors(request, errors);


//	    request.setAttribute("errmsg", e.getMessage());
            return mapping.findForward("error");
        }
        catch (Exception e)
        {
            LOGGER.error("Caught Exception in AddOptionsAction " + e);
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
            LOGGER
                    .debug("Exiting  eaglsExecute() of AddRoleInfoAction class");
        }
    }

}
