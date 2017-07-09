/**
 * SetupUserProfileRoleInfoOptionsAction
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
public class SetupUserProfileRoleInfoOptionsAction extends ActionBase
{

    /* private Logger */
    private static final Logger LOGGER =
            Logger
            .getLogger("com.boa.eagls.government.controller.action.user.SetupUserProfileRoleInfoOptionsAction.class");

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
                .debug("Entering eaglsExecute() of SetupUserProfileRoleInfoOptionsAction class");

        session = new EAGLSSession();

        /* Checking for session expiry */
        /*if (session.getCurrentRole(request) == null)
        {
            return mapping.findForward("sessionExpired");
        }*/

        DynaActionForm formBean = (DynaActionForm) form;
        String btnSelected =
                (String) formBean.get("but_submitButton");

        if (btnSelected.equalsIgnoreCase("Add Role"))
        {
            return mapping.findForward("addRole");
        }
        else if (btnSelected.equalsIgnoreCase("Finished"))
        {
            return mapping.findForward("finished");
        }
        else if (btnSelected.equalsIgnoreCase("Cancel"))
        {
            this.removeAttr(request);
            return mapping.findForward("backToSearch");
        }
        else
        {
            //request.setAttribute("errmsg", "Unknown button specified");
            ActionErrors errors = new ActionErrors();
            errors.add(Constants.ERR_HEADING, new ActionError("heading.error.eagls.general"));
            errors.add(Constants.ERR_MSG, new ActionError("error.userprofile.noButtonSpecified"));
            saveErrors(request, errors);

            return mapping.findForward("error");
        }
    }

    /**
     * To remove attributes from session
     * @param request
     */
    private void removeAttr(HttpServletRequest request)
    {
        request.getSession().removeAttribute("prevRoles");
        request.getSession().removeAttribute("UserProfileBean");
        request.getSession().removeAttribute("HierarchyBean");
    }

}
