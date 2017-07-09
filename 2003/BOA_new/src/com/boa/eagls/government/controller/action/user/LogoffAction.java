/**
 * LogoffAction
 */
package com.boa.eagls.government.controller.action.user;

/**
 * <p>Title: LogoffAction class</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */

import com.boa.eagls.government.statemgmt.EAGLSSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import java.util.*;

import org.apache.log4j.Logger;
import com.boa.eagls.government.controller.action.ActionBase;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class LogoffAction extends ActionBase
{

    // Set Logger
    private static Logger logger =
            Logger
            .getLogger(LogoffAction.class);

    /**
     * Execute is the function called by NAS whenever user attempts to log in.
     * @param        none
     * @exception    none
     * @return       int indicating whether execute was successful
     * (return value of 0) or not (any other value)
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response) throws Exception
    {
        logger.debug("LogoffAction --> execute() --> Start");
        String next = "failure";
        EAGLSSession session = new EAGLSSession();
        try
        {
            /* Checking for session expiry */
            /*if (session.getCurrentRole(request) == null)
            {
            return mapping.findForward("sessionExpired");
            }*/

            Hashtable htUserData = session.getCurrentUserData(request);
            String verifyLogoff = null;

            if (htUserData != null)
            {
                try
                {
                    verifyLogoff =
                            (String) ((DynaActionForm) form)
                            .get("hdn_confirmLogoff");
                }
                catch (NullPointerException e)
                {
                    logger.error("Verify null ", e);
                }
                if (verifyLogoff == null || verifyLogoff.equals(""))
                {
                    next = "VerifyLogoff";
                }
                else
                {
                    session.clearCurrentUserData(request);
                    next = "ConfirmLogoff";
                }
            }
            else
            {
                next = "SessionNotAvailable";
            }
        }
        catch (Exception nbe)
        {
            logger.debug("LogoffAction --> execute() --> Exception " + nbe);
//            session.clearCurrentUserData(request);
            return mapping.findForward("SessionNotAvailable");
        }
        logger.debug("LogoffAction --> execute() --> END");
        return mapping.findForward(next);
    }

}
