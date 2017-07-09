package com.boa.eagls.government.controller.formbean.authorizationcontrols;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 8 fields on this form:
 * <ul>
 * <li>acctControl - [your comment here]
 * <li>mccgControl - [your comment here]
 * <li>auth_accountNumber - [your comment here]
 * <li>auth_accountName - [your comment here]
 * <li>auth_agencyName - [your comment here]
 * <li>auth_centralId - [your comment here]
 * <li>auth_creditLimit - [your comment here]
 * <li>auth_guid - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class Frm_authControls extends ActionForm {

	private String acctControl = null;
	private String mccgControl = null;
	private AccountInfo accountInfo = new AccountInfo();

	/**
	 * Get acctControl
	 * @return String
	 */
	public String getAcctControl() {
		return acctControl;
	}

	/**
	 * Set acctControl
	 * @param <code>String</code>
	 */
	public void setAcctControl(String a) {
		acctControl = a;
	}
	/**
	 * Get mccgControl
	 * @return String
	 */
	public String getMccgControl() {
		return mccgControl;
	}

	/**
	 * Set mccgControl
	 * @param <code>String</code>
	 */
	public void setMccgControl(String m) {
		mccgControl = m;
	}
	/**
	* Constructor
	*/
	public Frm_authControls() {

		super();

	}
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

		ActionErrors errors = new ActionErrors();
		// Validate the fields in your form, adding
		// adding each error to this.errors as found, e.g.

		// if ((field == null) || (field.length() == 0)) {
		//   errors.add("field", new ActionError("error.field.required"));
		// }
		return errors;

	}
	/**
	 * Returns the accountInfo.
	 * @return AccountInfo
	 */
	public AccountInfo getAccountInfo() {
		return accountInfo;
	}

	/**
	 * Sets the accountInfo.
	 * @param accountInfo The accountInfo to set
	 */
	public void setAccountInfo(AccountInfo accountInfo) {
		this.accountInfo = accountInfo;
	}

}
