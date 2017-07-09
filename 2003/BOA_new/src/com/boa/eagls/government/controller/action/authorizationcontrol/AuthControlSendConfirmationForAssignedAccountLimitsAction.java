package com.boa.eagls.government.controller.action.authorizationcontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.dto.authorizationcontrols.*;
import com.boa.eagls.government.service.AccountService;
import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.Frm_confirmAuthControls;
import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.AccountInfo;
/**
 * @version 	1.0
 * @author
 */
public class AuthControlSendConfirmationForAssignedAccountLimitsAction
	extends Action {

	/**
	* Constructor
	*/
	public AuthControlSendConfirmationForAssignedAccountLimitsAction() {

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
		Frm_confirmAuthControls confirmAuthControlsForm =
			(Frm_confirmAuthControls) form;

		try {

			// do something here
			// retrieve auth values
			//String accountName = valIn.getValString("auth_accountName");
			String accountName =
				confirmAuthControlsForm.getAccountInfo().getAccountName();
			//String accountNumber = valIn.getValString("auth_accountNumber");
			String accountNumber =
				confirmAuthControlsForm.getAccountInfo().getAccountNumber();

			//retrieve accountlimits
			//AcctControls acctControl = (AcctControls) deserializeObject(valIn.getValString("acctControl"));
			AcctControls acctControl =
				(AcctControls) request.getSession().getAttribute("acctControl");

			int requestId;
			try {
				//requestId = AccountController.createAccountControls(acctControl, accountNumber,	getTransport());
				requestId = AccountService.createAccountControls(acctControl, accountNumber);
			} catch (Exception e) {
				//return processException(e);
			}

			//retrieve mccg limits
			/**			
						String serializedMCCG = valIn.getValString("mccgControl");
						if (serializedMCCG != null && !serializedMCCG.equals("")) {
							MCCGControls[] mccgControl =
								(MCCGControls[]) deserializeObject(valIn
									.getValString("mccgControl"));
			
							for (int i = 0; i < mccgControl.length; i++) {
								try {
									MCCGControls.create(
										mccgControl[i],
										accountNumber,
										requestId,
										getTransport());
								} catch (NBException e) {
									return processException(e);
								}
							}
						}
			*/
			MCCGControls[] mccg =
				(MCCGControls[]) request.getSession().getAttribute(
					"mccgControl");

			if (mccg != null) {
				for (int i = 0; i < mccg.length; i++) {
					try {
						//MCCGControls.create(mccg[i], accountNumber,	requestId, getTransport());
					} catch (Exception e) {
						//return processException(e);
					}
				}
			}
			/**
						TemplateMapBasic map =
							getEAGLSTemplateMap(
								"Authorization Controls Setup",
								"Confirmation");
						IValList setupVals = GX.CreateValList();
			
						setupVals.setValString("hlAcctNo", accountNumber);
						String hyperLink =
							createHyperlink(valIn.getValString("auth_guid"), setupVals);
						map.putString("accountInfo.confirmAuthControlsName", accountName);
						map.putString("hlAcctNo", accountNumber);
						map.putString(
							"AuthControlSetupHyperlink",
							valIn.getValString("auth_guid"));
						map.putString("account.number", accountNumber);
						map.putString(
							"homeGuid",
							"/cgi-bin/gx.cgi/GUIDGX-" + valIn.getValString("auth_guid"));
			
						return evalTemplate(
							"gsa/setup/authorization_controls/confirm_authControl.html",
							(com.kivasoft.ITemplateData) null,
							map);
			*/
		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));
			System.out.println(
				"Exception from AuthControlSendConfirmationForAssignedAccountLimitsAction"
					+ e);
		}

		// If a message is required, save the specified key(s)
		// into the request for use by the <struts:errors> tag.

		if (!errors.empty()) {
			saveErrors(request, errors);

			// Forward control to the appropriate 'failure' URI (change name as desired)
			//	forward = mapping.findForward("failure");

		} else {

			// Forward control to the appropriate 'success' URI (change name as desired)
			forwardURL = mapping.findForward("success");

		}

		// Finish with
		return (forwardURL);

	}
}
