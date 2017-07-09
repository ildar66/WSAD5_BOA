/**
 * DynMenuSelectAction
 */
package com.boa.eagls.government.controller.action.common;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boa.eagls.government.statemgmt.EAGLSSession;
import com.boa.eagls.government.util.ValidFunctions;
import com.boa.eagls.government.util.DynMenuState;

import java.util.Vector;

import com.boa.eagls.government.controller.action.ActionBase;

/**
 *
 * <p>Title: DynMenuSelectAction class</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class DynMenuSelectAction extends ActionBase
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
        session = new EAGLSSession();

        /* Checking for session expiry */
        /*if (session.getCurrentRole(request) == null)
        {
            return mapping.findForward("sessionExpired");
        }*/

        String roleFunctions = session.getRoleFunctionsStr(request);
        ValidFunctions vf = new ValidFunctions();
        String[][] functions = vf.parseRoleFunctions(roleFunctions);
        String dynMenu = session.getDynamicMenu(request);
        DynMenuState menu = new DynMenuState(functions, dynMenu);
        String name = null;
        String selected = null;

        if (request.getQueryString().length() > 0)
        {
            name = request.getParameter("name").trim();
            selected = request.getParameter("selected").trim();
        }
        if (name != null && selected != null)
        {
            if (selected.equalsIgnoreCase("T"))
            {
                menu.deselect(name);
            }
            else
            {
                menu.select(name);
            }
            session.setDynamicMenu(menu.toString(), request);
        }
        session.setCurrentMenu(menu.generateDisplayList(), request);
        return mapping.findForward("success");
    }

}
