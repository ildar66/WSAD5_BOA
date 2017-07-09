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
import com.boa.eagls.government.service.AccountService;
import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.exceptions.NBException;
import java.sql.*;
import java.util.ArrayList;

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
public class AuthControlMCCControlSetupAction extends Action {

	/**
	* Constructor
	*/
	public AuthControlMCCControlSetupAction() {

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
			/**			
						valIn = al.valIn;
						valOut = al.valOut;
						das = myDas;
						TemplateMapBasic map =
							al.getEAGLSTemplateMap("MCCG Controls Setup", "");
						TemplateDataBasic accountInfo =
							new TemplateDataBasic("accountInfo");
			
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
			
						map.putString("acctControl", valIn.getValString("acctControl"));
			*/
			/**
						String serializedMCCG = valIn.getValString("mccgControl");
						MCCGControls[] mccg = null;
						if (serializedMCCG == null || serializedMCCG.equals("")) {
							mccg = new MCCGControls[0];
							map.putString("mccgControl", serializeObject(mccg));
						} else {
							map.putString("mccgControl", valIn.getValString("mccgControl"));
							mccg = (MCCGControls[]) deserializeObject(serializedMCCG);
							if (mccg.length > 8) {
								map.putString("errmsg", "Maximum of 9 MCCGs defined.");
								return al.evalTemplate(
									"/gsa/eaglserror.html",
									(com.kivasoft.ITemplateData) null,
									map);
							}
						}
			*/
			MCCGControls[] mccg = null;
			mccg =
				(MCCGControls[]) request.getSession().getAttribute(
					"mccgControl");
			if (mccg == null) {
				mccg = new MCCGControls[0];
				request.getSession().setAttribute("mccgControl", mccg);
			}
			if (mccg.length > 8)
				errors.add(
					"errmsg",
					new ActionError("Maximum of 9 MCCGs defined."));
			else {
				/**
							com.nb.gsa.Adapter.DataAccess.core.DASTransport dasT = null;
							NameDescTable[] vendorNDT = null;
							NameDescTable[] mccgNDT = null;
				*/
				NameDescTable[] vendorNDT = null;
				NameDescTable[] mccgNDT = null;
				/**			
							TemplateDataBasic mccgData = new TemplateDataBasic("MCCGTableList");
							TemplateDataBasic vendor = new TemplateDataBasic("validPreferred");
							TemplateDataBasic group = new TemplateDataBasic("group");
				
							group.rowAppend("");
							try {
								vendorNDT =
									AccountController.retrieveVendorNames(
										valIn.getValString("auth_centralId").trim(),
										al.getTransport());
								if (vendorNDT != null) {
									for (int i = 0; i < vendorNDT.length; i++) {
										vendor.rowAppend(
											"preferredOptionName="
												+ vendorNDT[i].getName()
												+ ";preferredOptionDisplay="
												+ vendorNDT[i].getName());
									}
								}
							} catch (NBException e) {
								// don't care if it comes back null - continue with setup
							}
				 */
				vendorNDT =
					AccountService.retrieveVendorNames(
						frm_setupMccgControls
							.getAccountInfo()
							.getCentralId()
							.trim());
				if (vendorNDT != null && vendorNDT.length != 0)
					frm_setupMccgControls.setVendorNDT(vendorNDT);
				/** 			
				
							try {
								mccgNDT =
									AccountController.retrieveMCCGTableNamesByCentralAcctID(
										valIn.getValString("auth_centralId").trim(),
										al.getTransport());
								int entryCount = 0;
				
								if (mccgNDT != null) {
									for (int i = 0; i < mccgNDT.length; i++) {
										boolean used = false;
										for (int j = 0; j < mccg.length && !used; j++) {
											if (mccg[j]
												.getMCCGTableName()
												.equals(mccgNDT[i].getName())) {
												used = true;
											}
										}
				
										if (!used) {
											mccgData.rowAppend(
												"mccgOptionName="
													+ mccgNDT[i].getName()
													+ ";mccgOptionDisplay="
													+ mccgNDT[i].getName());
											entryCount++;
										}
									}
								}
				
								if (entryCount == 1) {
									map.putString("showAddMCCG", "");
								} else if (entryCount == 0) {
									map.putString(
										"errmsg",
										"No unused MCCG table names remain.");
									return al.evalTemplate(
										"/gsa/eaglserror.html",
										(com.kivasoft.ITemplateData) null,
										map);
								}
							} catch (NBException e) {
								return al.processException(e);
							}
				
							group.groupAppend(accountInfo);
							group.groupAppend(vendor);
							group.groupAppend(mccgData);
				
							return al.evalTemplate(
								"gsa/setup/authorization_controls/setup_mccgControl.html",
								group,
								map);
				*/

				mccgNDT =
					AccountService.retrieveMCCGTableNamesByCentralAcctID(
						frm_setupMccgControls
							.getAccountInfo()
							.getCentralId()
							.trim());
				frm_setupMccgControls.setMccgNDT(mccgNDT);
				int entryCount = 0;
				ArrayList mccgOptionName = new ArrayList();
				ArrayList mccgOptionDisplay = new ArrayList();

				if (mccgNDT != null) {
					for (int i = 0; i < mccgNDT.length; i++) {
						boolean used = false;
						for (int j = 0; j < mccg.length && !used; j++) {
							if (mccg[j]
								.getMCCGTableName()
								.equals(mccgNDT[i].getName())) {
								used = true;
							}
						}

						if (!used) {
							//mccgData.rowAppend("mccgOptionName="+ mccgNDT[i].getName()+ ";mccgOptionDisplay="+ mccgNDT[i].getName());
							mccgOptionName.add(mccgNDT[i].getName());
							mccgOptionDisplay.add(mccgNDT[i].getName());
							entryCount++;
						}
					}
				}

				if (entryCount == 1) {
					//map.putString("showAddMCCG", "");
					frm_setupMccgControls.setShowAddMCCG(entryCount == 1);
					String[] mccgOptionNameArr =
						new String[mccgOptionName.size()];
					mccgOptionName.toArray(mccgOptionNameArr);
					frm_setupMccgControls.setMccgOptionDisplay(
						mccgOptionNameArr);
					String[] mccgOptionDisplayArr =
						new String[mccgOptionDisplay.size()];
					mccgOptionDisplay.toArray(mccgOptionDisplayArr);
					frm_setupMccgControls.setMccgOptionName(
						mccgOptionDisplayArr);
				} else if (entryCount == 0) {
					//map.putString("errmsg",	"No unused MCCG table names remain.");
					//errors.add(	"errmsg",new ActionError("No unused MCCG table names remain."));
					//return al.evalTemplate("/gsa/eaglserror.html",	(com.kivasoft.ITemplateData) null,	map);
				}

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
			forwardURL = mapping.findForward("success");
		}

		// Finish with
		return (forwardURL);

	}
}
