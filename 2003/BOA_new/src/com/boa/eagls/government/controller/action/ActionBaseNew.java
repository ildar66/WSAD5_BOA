package com.boa.eagls.government.controller.action;

import org.apache.log4j.Logger;
import org.apache.struts.action.*;
import com.boa.eagls.government.statemgmt.EAGLSSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><small> DVI Company, 21.07.2003 12:50:42</small></p>
 * Base action class
 * @author GlebL
 */
public abstract class ActionBaseNew extends Action
{
    private static final Logger logger = Logger.getLogger(ActionBase.class);
    protected static final String ERROR_URL = "error";
    protected EAGLSSession session;

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws Exception
    {
        ActionErrors errors = new ActionErrors();
        session = new EAGLSSession();
        try
        {
            /* Checking for session expiry */
            if (request.getSession().getAttribute("htSessionData") == null)
            {
                return mapping.findForward("sessionExpired");
            }

            return (eaglsExecute(mapping, form, request, response));
        }
        catch (Exception e)
        {
            logger.error("error.unknown", e);
            errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.unknown"));
            saveErrors(request, errors);
            return (mapping.findForward("generalFailure"));
        }
    }

    /**
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public abstract ActionForward eaglsExecute(ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response)
            throws Exception;

}