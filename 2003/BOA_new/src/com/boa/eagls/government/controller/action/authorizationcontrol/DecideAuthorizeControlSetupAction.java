package com.boa.eagls.government.controller.action.authorizationcontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.controller.formbean.authorizationcontrols.*;
/**
 * @version 	1.0
 * @author
 */
public class DecideAuthorizeControlSetupAction extends Action {

	/**
	* Constructor
	*/
	public DecideAuthorizeControlSetupAction() {

		super();

	}
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		//ActionForward forward = new ActionForward();
		ActionForward forwardURL = mapping.findForward("error");
		// return value

		try {
			// do something here
			String btnSelected = request.getParameter("but_submitButton");

			if (btnSelected.equals("Add Account Controls"))
				forwardURL = mapping.findForward("add_Account_Controls");
			else if (btnSelected.equals("Add MCCG Controls"))
				forwardURL = mapping.findForward("add_MCCG_Controls");
			else if (btnSelected.equals("Finish"))
				forwardURL = mapping.findForward("finish");
		} catch (Exception e) {
			// Report the error using the appropriate name and ID.
			errors.add(
				"Exception: ",
				new ActionError("from DecideAuthorizeControlSetupAction"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.empty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			// forward = mapping.findForward("success");

		}

		// Finish with
		return (forwardURL);

	}
}
