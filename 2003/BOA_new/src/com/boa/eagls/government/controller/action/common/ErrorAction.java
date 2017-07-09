package com.boa.eagls.government.controller.action.common;


import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.controller.action.ActionBase;

import java.util.Vector;


public class ErrorAction extends ActionBase
{

    /**
     *
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @pre $none
     * @post $none
     */
    public ActionForward eaglsExecute(ActionMapping mapping, ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
    {

        String heading = (String) request.getAttribute(Constants.ERR_HEADING);
        String msg = (String) request.getAttribute(Constants.ERR_MSG);
        ActionErrors errors = new ActionErrors();
        errors.add(Constants.ERR_HEADING, new ActionError(heading));
        errors.add(Constants.ERR_MSG, new ActionError(msg));
        saveErrors(request, errors);
        return mapping.findForward("error");

    }

}