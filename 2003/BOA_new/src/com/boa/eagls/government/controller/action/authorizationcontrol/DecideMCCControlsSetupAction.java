package com.boa.eagls.government.controller.action.authorizationcontrol;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.dto.authorizationcontrols.MCCGControls;
import com.boa.eagls.government.dto.authorizationcontrols.Controls;
import com.boa.eagls.government.util.*;
import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.Frm_setupMccgControls;
/**
 * @version 	1.0
 * @author
 */
public class DecideMCCControlsSetupAction extends Action {

	/**
	* Constructor
	*/
	public DecideMCCControlsSetupAction() {

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
		Frm_setupMccgControls frm_setupMccgControls =
			(Frm_setupMccgControls) form;

		try {

			// do something here
			String btnSelected = request.getParameter("but_submitButton");

			//MCCGControls[] mccg = (MCCGControls[]) deserializeObject(valIn.getValString("mccgControl"));
			MCCGControls[] mccg =
				(MCCGControls[]) request.getSession().getAttribute(
					"mccgControl");
			MCCGControls newMCCG = new MCCGControls();

			newMCCG.setMCCGTableName(frm_setupMccgControls.getMccg_mccgTableName());

			String action = frm_setupMccgControls.getMccg_mccgAction();
			if (action.equals("Include")) {
				newMCCG.setMCCGAction(MCCGControls.INCLUDE);
			} else if (action.equals("Exclude")) {
				newMCCG.setMCCGAction(MCCGControls.EXCLUDE);
			} else if (action.equals("Refer")) {
				newMCCG.setMCCGAction(MCCGControls.REFER);
			} else if (action.equals("Divert")) {
				newMCCG.setMCCGAction(MCCGControls.DIVERT);
			} else if (action.equals("Bypass")) {
				newMCCG.setMCCGAction(MCCGControls.BYPASS);
			}

			newMCCG.setMCCGTableNo((short) mccg.length);

			try {
				newMCCG.setSinglePurchaseLimit(
					new Double(frm_setupMccgControls.getMccg_singleLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setSinglePurchaseLimit(0.0);
			}

			try {
				newMCCG.setDailyTransLimit(
					new Integer(frm_setupMccgControls.getMccg_dailyTransLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setDailyTransLimit(0);
			}

			try {
				newMCCG.setDailyAmtLimit(
					new Double(frm_setupMccgControls.getMccg_dailyAmountLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setDailyAmtLimit(0.0);
			}

			try {
				newMCCG.setCycleTransLimit(
					new Integer(frm_setupMccgControls.getMccg_cycleTransLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setCycleTransLimit(0);
			}

			try {
				newMCCG.setCycleAmtLimit(
					new Double(frm_setupMccgControls.getMccg_cycleAmountLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setCycleAmtLimit(0.0);
			}

			try {
				newMCCG.setMonthlyTransLimit(
					new Integer(frm_setupMccgControls.getMccg_monthTransLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setMonthlyTransLimit(0);
			}

			try {
				newMCCG.setMonthlyAmtLimit(
					new Double(frm_setupMccgControls.getMccg_monthAmountLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setMonthlyAmtLimit(0.0);
			}

			newMCCG.setOtherRefreshDate(
				DateUtil.convertStringToDate(
					frm_setupMccgControls.getMccg_otherRefreshDate()));

			try {
				newMCCG.setOtherNoDays(
					new Integer(
						frm_setupMccgControls.getMccg_otherTotalNumberDays()));
			} catch (NumberFormatException nfe) {
				newMCCG.setOtherNoDays(0);
			}

			try {
				newMCCG.setOtherTransLimit(
					new Integer(frm_setupMccgControls.getMccg_otherTransLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setOtherTransLimit(0);
			}

			try {
				newMCCG.setOtherAmtLimit(
					new Double(frm_setupMccgControls.getMccg_otherAmountLimit()));
			} catch (NumberFormatException nfe) {
				newMCCG.setOtherAmtLimit(0.0);
			}

			newMCCG.setVendorCheck(
				frm_setupMccgControls.getMccg_vendorCheck().equals("Yes"));
			newMCCG.setVendorType(
				frm_setupMccgControls.getMccg_vendorType().equals("Include")
					? Controls.INCLUDE
					: Controls.EXCLUDE);
			newMCCG.setVendorAction(
				frm_setupMccgControls.getMccg_vendorAction().equals("Accept")
					? Controls.ACCEPT
					: (frm_setupMccgControls.getMccg_vendorAction().equals("Refer")
						? Controls.REFER
						: Controls.DECLINE));
			newMCCG.setVendorTableName(
				frm_setupMccgControls.getMccg_validPreferredTables());

			MCCGControls[] newMCCGArray = new MCCGControls[mccg.length + 1];
			for (int i = 0; i < mccg.length; i++) {
				newMCCGArray[i] = mccg[i];
			}
			newMCCGArray[mccg.length] = newMCCG;
			//valIn.setValString("mccgControl", serializeObject(newMCCGArray));
			request.getSession().setAttribute("mccgControl", newMCCGArray);

			if (btnSelected.equals("Add MCCG Controls"))
				forwardURL = mapping.findForward("add_MCCG_Controls");
			else if (btnSelected.equals("Submit"))
				forwardURL = mapping.findForward("submit");

		} catch (Exception e) {

			// Report the error using the appropriate name and ID.
			errors.add(
				"Exception: ",
				new ActionError("from DecideMCCControlsSetupAction"));

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
