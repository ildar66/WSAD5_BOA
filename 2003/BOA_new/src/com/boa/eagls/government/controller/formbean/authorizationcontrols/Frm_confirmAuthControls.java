package com.boa.eagls.government.controller.formbean.authorizationcontrols;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

/**
 * Form bean for a Struts application.
 * Users may access 4 fields on this form:
 * <ul>
 * <li>hyperlinksOnly - [your comment here]
 * <li>hlAcctNo - [your comment here]
 * <li>hdn_centralAccountNumber - [your comment here]
 * <li>but_submitButton - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class Frm_confirmAuthControls extends ActionForm {

	private String hyperlinksOnly = null;
	private String hlAcctNo = null;
	private String hdn_centralAccountNumber = null;
	private String but_submitButton = null;
    private AccountInfo accountInfo = new AccountInfo();
	/**
	 * Get hyperlinksOnly
	 * @return String
	 */
	public String getHyperlinksOnly() {
		return hyperlinksOnly;
	}

	/**
	 * Set hyperlinksOnly
	 * @param <code>String</code>
	 */
	public void setHyperlinksOnly(String h) {
		hyperlinksOnly = h;
	}
	/**
	 * Get hlAcctNo
	 * @return String
	 */
	public String getHlAcctNo() {
		return hlAcctNo;
	}

	/**
	 * Set hlAcctNo
	 * @param <code>String</code>
	 */
	public void setHlAcctNo(String h) {
		hlAcctNo = h;
	}
	/**
	 * Get hdn_centralAccountNumber
	 * @return String
	 */
	public String getHdn_centralAccountNumber() {
		return hdn_centralAccountNumber;
	}

	/**
	 * Set hdn_centralAccountNumber
	 * @param <code>String</code>
	 */
	public void setHdn_centralAccountNumber(String h) {
		hdn_centralAccountNumber = h;
	}
	/**
	 * Get but_submitButton
	 * @return String
	 */
	public String getBut_submitButton() {
		return but_submitButton;
	}

	/**
	 * Set but_submitButton
	 * @param <code>String</code>
	 */
	public void setBut_submitButton(String b) {
		but_submitButton = b;
	}
	/**
	* Constructor
	*/
	public Frm_confirmAuthControls() {

		super();

	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.

		hyperlinksOnly = null;
		hlAcctNo = null;
		hdn_centralAccountNumber = null;
		but_submitButton = null;

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
