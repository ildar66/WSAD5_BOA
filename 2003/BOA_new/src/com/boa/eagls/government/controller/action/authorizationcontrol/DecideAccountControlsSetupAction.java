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
import com.boa.eagls.government.util.DateUtil;

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
public class DecideAccountControlsSetupAction extends Action {

	/**
	* Constructor
	*/
	public DecideAccountControlsSetupAction() {

		super();

	}
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		ActionForward forwardURL = mapping.findForward("error");
		// return value
		Frm_setupAccountControls frm_setupAccountControls =
			(Frm_setupAccountControls) form;

		try {
			// do something here
			String btnSelected = request.getParameter("but_submitButton");
			AcctControls acctControl = new AcctControls();
			try {
				//acctControl.setSinglePurchaseLimit(new Double(valIn.getValString("acct_singleLimit")));
				acctControl.setSinglePurchaseLimit(
					new Double(frm_setupAccountControls.getAcct_singleLimit()));

			} catch (NumberFormatException nfe) {
				acctControl.setSinglePurchaseLimit(0.0);
			}

			try {
				acctControl.setDailyTransLimit(
					new Integer(
						frm_setupAccountControls.getAcct_dailyTransLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setDailyTransLimit(0);
			}

			try {
				acctControl.setDailyAmtLimit(
					new Double(
						frm_setupAccountControls.getAcct_dailyAmountLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setDailyAmtLimit(0.0);
			}

			try {
				acctControl.setCycleTransLimit(
					new Integer(
						frm_setupAccountControls.getAcct_cycleTransLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setCycleTransLimit(0);
			}

			try {
				acctControl.setCycleAmtLimit(
					new Double(
						frm_setupAccountControls.getAcct_cycleAmountLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setCycleAmtLimit(0.0);
			}

			try {
				acctControl.setMonthlyTransLimit(
					new Integer(
						frm_setupAccountControls.getAcct_monthTransLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setMonthlyTransLimit(0);
			}

			try {
				acctControl.setMonthlyAmtLimit(
					new Double(
						frm_setupAccountControls.getAcct_monthAmountLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setMonthlyAmtLimit(0.0);
			}

			acctControl.setOtherRefreshDate(
				DateUtil.convertStringToDate(
					frm_setupAccountControls.getAcct_otherRefreshDate()));

			try {
				acctControl.setOtherNoDays(
					new Integer(
						frm_setupAccountControls
							.getAcct_otherTotalNumberDays()));
			} catch (NumberFormatException nfe) {
				acctControl.setOtherNoDays(0);
			}

			try {
				acctControl.setOtherTransLimit(
					new Integer(
						frm_setupAccountControls.getAcct_otherTransLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setOtherTransLimit(0);
			}

			try {
				acctControl.setOtherAmtLimit(
					new Double(
						frm_setupAccountControls.getAcct_otherAmountLimit()));
			} catch (NumberFormatException nfe) {
				acctControl.setOtherAmtLimit(0.0);
			}

			acctControl.setVendorCheck(
				frm_setupAccountControls.getAcct_vendorCheck().equals("Yes"));
			acctControl.setVendorType(
				frm_setupAccountControls.getAcct_vendorType().equals("Include")
					? Controls.INCLUDE
					: Controls.EXCLUDE);
			acctControl.setVendorAction(
				frm_setupAccountControls.getAcct_vendorAction().equals(
					"Accept")
					? Controls.ACCEPT
					: (frm_setupAccountControls
						.getAcct_vendorAction()
						.equals("Refer")
						? Controls.REFER
						: Controls.DECLINE));
			acctControl.setVendorTableName(
				frm_setupAccountControls.getAcct_validPreferredTables());
			acctControl.setForeignCurResSet(
				frm_setupAccountControls
					.getAcct_foreignCurrRestrictSet()
					.equals(
					"Yes"));
			acctControl.setForeignType(
				frm_setupAccountControls.getAcct_foreignType().equals(
					"Include")
					? AcctControls.INCLUDE
					: AcctControls.EXCLUDE);
			acctControl.setForeignAction(
				frm_setupAccountControls.getAcct_foreignAction().equals(
					"Accept")
					? AcctControls.ACCEPT
					: (frm_setupAccountControls
						.getAcct_foreignAction()
						.equals("Refer")
						? AcctControls.REFER
						: AcctControls.DECLINE));

			//valIn.setValString("acctControl", serializeObject(acctControl));
			request.getSession().setAttribute("acctControl", acctControl);
			if (btnSelected.equals("Add MCCG Controls")) {
				//AuthControlMCCControlSetupAL acsal = new AuthControlMCCControlSetupAL();
				//return acsal.execute(this, das);
				forwardURL = mapping.findForward("add_MCCG_Controls");
			} else {
				//AuthorizationControlSetupAL acsal = new AuthorizationControlSetupAL();
				//return acsal.execute(this, das);
				forwardURL = mapping.findForward("submit");
			}

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add("name", new ActionError("id"));

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
