/* ProfileSearchAction class */
package com.boa.eagls.government.controller.action.common;

import com.boa.eagls.government.dto.HierarchyDTO;
import org.apache.struts.action.*;

import javax.servlet.http.*;
import java.util.*;
import java.io.*;

import com.boa.eagls.government.service.userprofile.SearchUserService;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.action.ActionBase;
import com.boa.eagls.government.util.*;
import com.boa.eagls.government.dto.user.SearchUserDTO;
import org.apache.log4j.*;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.LargeResultSetException;


/**
 * <p>Title: ProfileSearchAction</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * Action class to perform search for a user pofile.
 * @author
 * @version 1.0
 * @invariant $none
 */
public class ProfileSearchAction extends ActionBase
{
    private static final int DEFAULT_LARS_SIZE = 50;
    private static int laRSSize = DEFAULT_LARS_SIZE;

    // Set Logger
    private static Logger logger =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.common.ProfileSearchAction.class");

    /**
     * Method called by the execute() method of super class.
     * @param mapping
     * @param actionForm
     * @param request
     * @param response
     * @pre $none
     * @post $none
     * @return
     */
    public ActionForward eaglsExecute(ActionMapping mapping,
                                      ActionForm actionForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {
        logger.debug("Enetering ProfileSearchAction - eaglsExecute() .....");
        DynaActionForm formBean = null;
        try
        {
            if (laRSSize <= 0)
            {
                loadProperties();
            }

            session = new EAGLSSession();

            /* Checking for session expiry */
            /*if (session.getCurrentRole(request) == null)
            {
                return mapping.findForward("sessionExpired");
            }*/

            boolean searchByAcct = false;
            String fName = request.getParameter("txt_firstName");

            if (fName == null)
            {
                fName = "";
            }
            String lName = request.getParameter("txt_lastName");

            if (lName == null)
            {
                lName = "";
            }
            request.setAttribute("firstName", fName);
            request.setAttribute("lastName", lName);

            formBean = ((DynaActionForm) actionForm);

            if (((String) formBean.get("txt_accountNumber")).length() > 0)
            {
                searchByAcct = true;
                request.setAttribute("searchCriteria", "Account Number");
                request.setAttribute("searchCriteriaValue",
                        formBean.get("txt_accountNumber"));
            }
            else if (((String) formBean.get("txt_lastName")).length() > 0)
            {
                request.setAttribute("searchCriteria", "Name");
                request.setAttribute("searchCriteriaValue",
                        formBean.get("txt_lastName"));
                if (formBean.get("txt_firstName") != null)
                {
                    request.setAttribute("searchCriteriaValue",
                            ((String) formBean.get("txt_firstName")) + " "
                            + ((String) formBean.get("txt_lastName")));
                }
            }
            else
            {
                request.setAttribute("searchCriteria", "Hierarchy");
                String[] hrcy = getHierarchy(formBean);
                StringBuffer str = new StringBuffer("");

                for (int i = 0; i < hrcy.length; i++)
                {
                    str.append(hrcy[i] + " ");
                }
                request.setAttribute("searchCriteriaValue", str.toString());
            }
            Hashtable htUserData = session.getCurrentUserData(request);
            SearchUserDTO dto = populate(formBean);
            Vector searchResult = null;
            searchResult =
                    (new SearchUserService()).SearchUser(dto, htUserData, laRSSize);


            request.setAttribute("searchResult", searchResult);
            logger
                    .debug("Size of result in ProfileSearchAction - populate() ....."
                    + searchResult.size());
            if (searchResult.size() == 0)
            {
                if (searchByAcct)
                {
                    logger
                            .debug("Exiting ProfileSearchAction - eaglsExecute() .....");
                    return mapping.findForward("noResult");
                }
                else
                {
                    logger
                            .debug("Exiting ProfileSearchAction - eaglsExecute() .....");
                    return mapping.findForward("newUser");
                }
            }
        }
        catch (LargeResultSetException e)
        {
            logger.error(e.getMessage(), e);
            request.setAttribute("resultCount", "" + e.getResultSetCount());
            //              String query = "action='profileSearch.do'";
            request.setAttribute("bean", formBean);
            return mapping.findForward("largeResult");
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING,
                    new ActionError("heading.error.invalid.searchoption"));
            errors.add(Constants.ERR_MSG,
                    new ActionError("error.invalid.searchoption"));
            saveErrors(request, errors);
            logger.debug("Exiting ProfileSearchAction - eaglsExecute() .....");
            return mapping.findForward("error");
        }
        logger.debug("Exiting ProfileSearchAction - eaglsExecute() .....");
        return mapping.findForward("success");
    }

    /**
     * Method declaration
     *
     */
    private void loadProperties() throws NBException
    {
        logger.debug("Entering ProfileSearchAction - loadProperties() .....");
        Properties prop = new Properties();

        try
        {
            InputStream is =
                    new FileInputStream(getClass()
                    .getResource("/eagls.properties").getFile());

            prop.load(is);
            laRSSize = Integer.parseInt(prop.getProperty("laRSSize"));
        }
        catch (NumberFormatException ex)
        {
            laRSSize = DEFAULT_LARS_SIZE;
            logger.error("", ex);
            throw new NBError("");
        }
        catch (IOException ex)
        {
            logger.error("", ex);
            throw new NBError("");
        }
        logger.debug("Exiting ProfileSearchAction - loadProperties() .....");
    }

    // Method to populate a DTO from formBean to pass to service class.

    /**
     * Method declaration
     *
     *
     * @param formBean
     *
     * @return
     */
    private SearchUserDTO populate(DynaActionForm formBean)
    {
        logger.debug("Entering ProfileSearchAction - populate() .....");
        SearchUserDTO dto = new SearchUserDTO();

        dto.setAccountNumber((String) formBean.get("txt_accountNumber"));
        dto.setFirstName((String) formBean.get("txt_firstName"));
        dto.setHierarchy(getHierarchy(formBean));
        dto.setHierarchyDepth((String) formBean.get("txt_hierarchyDepth"));
        dto.setLastName((String) formBean.get("txt_lastName"));
//	dto.setProgramNumber(formBean.get("ProgramNumber"));
        String countFlag = (String) formBean.get("countFlag");
        if (countFlag.equalsIgnoreCase("true"))
            dto.setCountFlag(true);
        else
            dto.setCountFlag(false);
        logger.debug("Exiting ProfileSearchAction - populate() .....");
//        System.out.println(dto.isCountFlag());
        return dto;
    }

    private String[] getHierarchy(DynaActionForm form)
    {
        String[] hierarchy = new String[9];
        hierarchy[0] = (String) form.get("txt_hl0");
        hierarchy[1] = (String) form.get("txt_hl1");
        hierarchy[2] = (String) form.get("txt_hl2");
        hierarchy[3] = (String) form.get("txt_hl3");
        hierarchy[4] = (String) form.get("txt_hl4");
        hierarchy[5] = (String) form.get("txt_hl5");
        hierarchy[6] = (String) form.get("txt_hl6");
        hierarchy[7] = (String) form.get("txt_hl7");
        hierarchy[8] = (String) form.get("txt_hl8");
        if (hierarchy[0] == null || hierarchy[0].equals(""))
        {
            hierarchy = null;
        }
        return hierarchy;

    }
}
