package com.boa.eagls.government.controller.action.authorizationcontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.business.*;
import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.service.centralaccount.*;

import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.Frm_setupAccountControls;

/**
 * @version 	1.0
 * @author
 */
public class AuthAccountControlsSetupAction extends Action {

	/**
	* Constructor
	*/
	public AuthAccountControlsSetupAction() {

		super();

	}
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forward = new ActionForward();
		// return value
		Frm_setupAccountControls setupAccountControlsForm =
			(Frm_setupAccountControls) form;

		try {

			// do something here
			NameDescTable[] vendorNDT =
				AccountService.retrieveVendorNames(
					setupAccountControlsForm.getAccountInfo().getCentralId());
			if (vendorNDT != null  && vendorNDT.length != 0)
				setupAccountControlsForm.setVendorNDT(vendorNDT);

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add(
				"Exception",
				new ActionError("ErrorRetrieveVendorNames"));

		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.empty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			forward = mapping.findForward("error");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			forward = mapping.findForward("success");

		}

		// Finish with
		return (forward);

	}
}
