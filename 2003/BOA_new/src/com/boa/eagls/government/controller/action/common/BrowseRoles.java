/**
 * BrowseRoles
 */
package com.boa.eagls.government.controller.action.common;

import com.boa.eagls.government.exceptions.*;

import java.util.Vector;
import java.util.Hashtable;
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
import org.apache.log4j.*;
import com.boa.eagls.government.service.userprofile.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.controller.action.ActionBase;

/**
 *
 * <p>Title: BrowseRoles class</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class BrowseRoles extends ActionBase
{
    private static final Logger logger =
            Logger
            .getLogger(BrowseRoles.class);

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
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws IOException,
            ServletException
    {
        logger.debug("Entered BrowseRoles - execute .....");
        try
        {
            session = new EAGLSSession();

            /* Checking for session expiry */
            /*if (session.getCurrentRole(request) == null)
            {
            return mapping.findForward("sessionExpired");
            }*/


            Vector data = new Vector();
            RoleService roleService = new RoleService(null);
            String modifyRole = request.getParameter("modifyRole");

            {
                String[] roles = roleService.getAllRoles();

                if (modifyRole == null)
                {
                    String[] baseRoles = roleService.getAllBaseRoles();

                    roles = concatRoles(roles, baseRoles);
                }
                logger.debug("roles.length "+roles.length);
                sortRoles(roles);
                for (int i = 0; i < roles.length; i++)
                {
                    data.addElement(roles[i]);
                }
                request.setAttribute("rolesData", data);
                return mapping.findForward("browse_roles");
            }
        }
        catch (Exception e)
        {
            logger.error(e.getMessage(), e);
            return mapping.findForward("error");
        }
        finally
        {
            logger.debug("Exiting BrowseRoles - execute .....");
        }
    }

    /**
     * Performs a selection sort on an array of roles.
     * @param	roles	before operation, the unsorted roles list; after
     * operation, the sorted roles list.
     * Roles array must have no null values.
     */
    private void sortRoles(String[] roles)
    {
        String tmp;

        for (int i = 0; i < roles.length - 1; i++)
        {

            // Mark the first slot in this iteration as the least
            int least = i;

            for (int j = i + 1; j < roles.length; j++)
            {

                // If String at slot j is less than that at slot least,
                // mark j as the least
                if (roles[j].compareTo(roles[least]) < 0)
                {
                    least = j;
                }
            }
            if (least != i)
            {
                tmp = roles[i];

                // System.arraycopy(roles, least, roles, i, 1);
                roles[i] = roles[least];
                roles[least] = tmp;
            }
        }
    }

    /**
     * Method declaration
     *
     *
     * @param customRoles
     * @param baseRoles
     *
     * @return
     */
    private String[] concatRoles(String[] customRoles, String[] baseRoles)
    {
        String[] allRoles;
        int tmp = customRoles.length + baseRoles.length;

        allRoles = new String[tmp];

        // System.arraycopy(customRoles,0,allRoles,0,customRoles.length);
        for (int i = 0; i < customRoles.length; i++)
        {
            allRoles[i] = customRoles[i];
        }

        // System.arraycopy(baseRoles,0,allRoles,0,baseRoles.length);
        for (int i = customRoles.length, j = 0; j < baseRoles.length;
             i++, j++)
        {
            allRoles[i] = baseRoles[j];
        }
        return allRoles;
    }

}
