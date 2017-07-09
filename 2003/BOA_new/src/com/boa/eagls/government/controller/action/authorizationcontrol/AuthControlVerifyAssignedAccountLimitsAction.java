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
import com.boa.eagls.government.util.*;

import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.Frm_verifAcctControls;
import com
	.boa
	.eagls
	.government
	.controller
	.formbean
	.authorizationcontrols
	.MccgInfo;
/**
 * @version 	1.0
 * @author
 */
public class AuthControlVerifyAssignedAccountLimitsAction extends Action {

	/**
	* Constructor
	*/
	public AuthControlVerifyAssignedAccountLimitsAction() {

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
		Frm_verifAcctControls verifAcctControlsForm =
			(Frm_verifAcctControls) form;

		try {

			// do something here
			/**			
						valIn = al.valIn;
						valOut = al.valOut;
						das = myDas;
						TemplateMapBasic map = al.getEAGLSTemplateMap("Authorization Controls Setup",					"Verification");
						TemplateDataBasic accountInfo =	new TemplateDataBasic("accountInfo");
						TemplateDataBasic control = new TemplateDataBasic("acct");
						TemplateDataBasic mccgInfo = new TemplateDataBasic("mccgInfo");
			*/
			// auth values
			/**			
						accountInfo.rowAppend(
							"accountNumber="
								+ valIn.getValString("auth_accountNumber")
								+ ";accountName="
								+ valIn.getValString("auth_accountName")
								+ ";agencyName="
								+ valIn.getValString("auth_agencyName")
								+ ";centralId="
								+ valIn.getValString("auth_centralId")
								+ ";creditLimit="
								+ valIn.getValString("auth_creditLimit")
								+ ";guid="
								+ valIn.getValString("auth_guid"));
			*/
			// account limits
			/**			
						String serializedAcct = valIn.getValString("acctControl");
						AcctControls acctControl;
						if (serializedAcct == null || serializedAcct.equals("")) {
							acctControl = new AcctControls();
						} else {
							acctControl =
								(AcctControls) deserializeObject(valIn
									.getValString("acctControl"));
						}
						map.putString("acctControl", serializeObject(acctControl));
			
						control.rowAppend(
							"singleLimit="
								+ acctControl.getSinglePurchaseLimit()
								+ ";dailyTransLimit="
								+ acctControl.getDailyTransLimit()
								+ ";dailyAmountLimit="
								+ acctControl.getDailyAmtLimit()
								+ ";cycleTransLimit="
								+ acctControl.getCycleTransLimit()
								+ ";cycleAmountLimit="
								+ acctControl.getCycleAmtLimit()
								+ ";monthTransLimit="
								+ acctControl.getMonthlyTransLimit()
								+ ";monthAmountLimit="
								+ acctControl.getMonthlyAmtLimit()
								+ ";otherTransLimit="
								+ acctControl.getOtherTransLimit()
								+ ";otherAmountLimit="
								+ acctControl.getOtherAmtLimit()
								+ ";otherTotalNumberDays="
								+ acctControl.getOtherNoDays()
								+ ";otherRefreshDate="
								+ DisplayDateUtil.convertDateToString(
									acctControl.getOtherRefreshDate())
								+ ";foreignCurrRestrictSet="
								+ (acctControl.getForeignCurResSet() ? "Yes" : "No")
								+ ";foreignType="
								+ (acctControl.getForeignType() == AcctControls.INCLUDE
									? "Include"
									: "Exclude")
								+ ";foreignAction="
								+ (acctControl.getForeignAction() == AcctControls.ACCEPT
									? "Accept"
									: (acctControl.getForeignAction() == AcctControls.REFER
										? "Refer"
										: "Decline"))
								+ ";vendorCheck="
								+ (acctControl.getVendorCheck() ? "Yes" : "No")
								+ ";vendorType="
								+ (acctControl.getVendorType() == Controls.INCLUDE
									? "Include"
									: "Exclude")
								+ ";vendorAction="
								+ (acctControl.getVendorAction() == Controls.ACCEPT
									? "Accept"
									: (acctControl.getVendorAction() == Controls.REFER
										? "Refer"
										: "Decline"))
								+ ";validPreferredTables="
								+ acctControl.getVendorTableName());
			*/
			AcctControls acctControl =
				(AcctControls) request.getSession().getAttribute("acctControl");
			if (acctControl == null) {
				acctControl = new AcctControls();
				request.getSession().setAttribute("acctControl", acctControl);
			}

			verifAcctControlsForm.getAcct().setSingleLimit(
				acctControl.getSinglePurchaseLimit());
			verifAcctControlsForm.getAcct().setDailyTransLimit(
				acctControl.getDailyTransLimit());
			verifAcctControlsForm.getAcct().setDailyAmountLimit(
				acctControl.getDailyAmtLimit());
			verifAcctControlsForm.getAcct().setCycleTransLimit(
				acctControl.getCycleTransLimit());
			verifAcctControlsForm.getAcct().setCycleAmountLimit(
				acctControl.getCycleAmtLimit());
			verifAcctControlsForm.getAcct().setMonthTransLimit(
				acctControl.getMonthlyTransLimit());
			verifAcctControlsForm.getAcct().setMonthAmountLimit(
				acctControl.getMonthlyAmtLimit());
			verifAcctControlsForm.getAcct().setOtherTransLimit(
				acctControl.getOtherTransLimit());
			verifAcctControlsForm.getAcct().setOtherAmountLimit(
				acctControl.getOtherAmtLimit());
			verifAcctControlsForm.getAcct().setOtherTotalNumberDays(
				acctControl.getOtherNoDays());
			verifAcctControlsForm.getAcct().setOtherRefreshDate(
				DisplayDateUtil.convertDateToString(
					acctControl.getOtherRefreshDate()));
			verifAcctControlsForm.getAcct().setForeignCurrRestrictSet(
				(acctControl.getForeignCurResSet() ? "Yes" : "No"));
			verifAcctControlsForm.getAcct().setForeignType(
				(acctControl.getForeignType() == AcctControls.INCLUDE
					? "Include"
					: "Exclude"));
			verifAcctControlsForm.getAcct().setForeignAction(
				(acctControl.getForeignAction() == AcctControls.ACCEPT
					? "Accept"
					: (acctControl.getForeignAction() == AcctControls.REFER
						? "Refer"
						: "Decline")));
			verifAcctControlsForm.getAcct().setVendorCheck(
				(acctControl.getVendorCheck() ? "Yes" : "No"));
			verifAcctControlsForm.getAcct().setVendorType(
				(acctControl.getVendorType() == Controls.INCLUDE
					? "Include"
					: "Exclude"));
			verifAcctControlsForm.getAcct().setVendorAction(
				(acctControl.getVendorAction() == Controls.ACCEPT
					? "Accept"
					: (acctControl.getVendorAction() == Controls.REFER
						? "Refer"
						: "Decline")));
			verifAcctControlsForm.getAcct().setValidPreferredTables(
				acctControl.getVendorTableName());

			// mccg controls
			/**			
						String serializedMCCG = valIn.getValString("mccgControl");
						map.putString("mccgControl", serializedMCCG);
			
						if (serializedMCCG == null || serializedMCCG.equals("")) {
							map.putString("showMCCGHeader", "");
						} else {
							MCCGControls[] mccg =
								(MCCGControls[]) deserializeObject(serializedMCCG);
							if (mccg.length == 0) {
								map.putString("showMCCGHeader", "");
							}
			
							for (int i = 0; i < mccg.length; i++) {
								String action;
								switch (mccg[i].getMCCGAction()) {
									case MCCGControls.INCLUDE :
										action = "Include";
										break;
									case MCCGControls.EXCLUDE :
										action = "Exclude";
										break;
									case MCCGControls.REFER :
										action = "Refer";
										break;
									case MCCGControls.DIVERT :
										action = "Divert";
										break;
									case MCCGControls.BYPASS :
									default :
										action = "Bypass";
										break;
								}
			
								mccgInfo.rowAppend(
									"mccgTableName="
										+ mccg[i].getMCCGTableName()
										+ ";"
										+ "mccgAction="
										+ action
										+ ";"
										+ "singleLimit="
										+ mccg[i].getSinglePurchaseLimit()
										+ ";"
										+ "dailyTransLimit="
										+ mccg[i].getDailyTransLimit()
										+ ";"
										+ "cycleTransLimit="
										+ mccg[i].getCycleTransLimit()
										+ ";"
										+ "dailyAmountLimit="
										+ mccg[i].getDailyAmtLimit()
										+ ";"
										+ "cycleAmountLimit="
										+ mccg[i].getCycleAmtLimit()
										+ ";"
										+ "monthTransLimit="
										+ mccg[i].getMonthlyTransLimit()
										+ ";"
										+ "otherTransLimit="
										+ mccg[i].getOtherTransLimit()
										+ ";"
										+ "monthAmountLimit="
										+ mccg[i].getMonthlyAmtLimit()
										+ ";"
										+ "otherAmountLimit="
										+ mccg[i].getOtherAmtLimit()
										+ ";"
										+ "otherTotalNumberDays="
										+ mccg[i].getOtherNoDays()
										+ ";"
										+ "otherRefreshDate="
										+ DisplayDateUtil.convertDateToString(
											mccg[i].getOtherRefreshDate())
										+ ";"
										+ "vendorCheck="
										+ (mccg[i].getVendorCheck() ? "Yes" : "No")
										+ ";"
										+ "vendorType="
										+ (mccg[i].getVendorType() == Controls.INCLUDE
											? "Include"
											: "Exclude")
										+ ";"
										+ "vendorAction="
										+ (mccg[i].getVendorAction() == Controls.ACCEPT
											? "Accept"
											: (mccg[i].getVendorAction() == Controls.REFER
												? "Refer"
												: "Decline"))
										+ ";"
										+ "validPreferredTables="
										+ mccg[i].getVendorTableName());
							}
						}
			
						TemplateDataBasic combined = new TemplateDataBasic("combined");
						combined.rowAppend("");
						combined.groupAppend(accountInfo);
						combined.groupAppend(control);
						combined.groupAppend(mccgInfo);
						return al.evalTemplate(
							"gsa/setup/authorization_controls/verif_authControlsAcct.html",
							combined,
							map);
			*/
			MCCGControls[] mccg =
				(MCCGControls[]) request.getSession().getAttribute(
					"mccgControl");
					
			if (mccg == null) {
				verifAcctControlsForm.setShowMCCGHeader("true");
			} else {
				if (mccg.length == 0) {
					verifAcctControlsForm.setShowMCCGHeader("true");
				}
				
				//System.out.println("mccg.lenght:" + mccg.length);
				
				MccgInfo[] newMccgInfo = new MccgInfo[mccg.length];
				for (int i = 0; i < newMccgInfo.length; i++) {
					newMccgInfo[i] = new MccgInfo();
				}
				verifAcctControlsForm.setMccgInfo(newMccgInfo);

				for (int i = 0; i < mccg.length; i++) {
					String action;
					switch (mccg[i].getMCCGAction()) {
						case MCCGControls.INCLUDE :
							action = "Include";
							break;
						case MCCGControls.EXCLUDE :
							action = "Exclude";
							break;
						case MCCGControls.REFER :
							action = "Refer";
							break;
						case MCCGControls.DIVERT :
							action = "Divert";
							break;
						case MCCGControls.BYPASS :
						default :
							action = "Bypass";
							break;
					}

					newMccgInfo[i].setMccgTableName(mccg[i].getMCCGTableName());
					newMccgInfo[i].setMccgAction(action);
					newMccgInfo[i].setSingleLimit(
						mccg[i].getSinglePurchaseLimit());
					newMccgInfo[i].setDailyTransLimit(
						mccg[i].getDailyTransLimit());
					newMccgInfo[i].setCycleTransLimit(
						mccg[i].getCycleTransLimit());
					newMccgInfo[i].setDailyAmountLimit(
						mccg[i].getDailyAmtLimit());
					newMccgInfo[i].setCycleAmountLimit(
						mccg[i].getCycleAmtLimit());
					newMccgInfo[i].setMonthTransLimit(
						mccg[i].getMonthlyTransLimit());
					newMccgInfo[i].setOtherTransLimit(
						mccg[i].getOtherTransLimit());
					newMccgInfo[i].setMonthAmountLimit(
						mccg[i].getMonthlyAmtLimit());
					newMccgInfo[i].setOtherAmountLimit(
						mccg[i].getOtherAmtLimit());
					newMccgInfo[i].setOtherTotalNumberDays(
						mccg[i].getOtherNoDays());
					newMccgInfo[i].setOtherRefreshDate(
						DisplayDateUtil.convertDateToString(
							mccg[i].getOtherRefreshDate()));
					newMccgInfo[i].setVendorCheck(
						(mccg[i].getVendorCheck() ? "Yes" : "No"));
					newMccgInfo[i].setVendorType(
						(mccg[i].getVendorType() == Controls.INCLUDE
							? "Include"
							: "Exclude"));
					newMccgInfo[i].setVendorAction(
						(mccg[i].getVendorAction() == Controls.ACCEPT
							? "Accept"
							: (mccg[i].getVendorAction() == Controls.REFER
								? "Refer"
								: "Decline")));
					newMccgInfo[i].setValidPreferredTables(
						mccg[i].getVendorTableName());

				}
			}

		} catch (Exception e) {
			System.out.println("exceptions" + e);

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
			forwardURL = mapping.findForward("success");

		}

		// Finish with
		return (forwardURL);

	}
}
