package com.boa.eagls.government.controller.formbean.authorizationcontrols;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.dto.NameDescTable;
/**
 * Form bean for a Struts application.
 * Users may access 25 fields on this form:
 * <ul>
 * <li>acct_singleLimit - [your comment here]
 * <li>acct_dailyTransLimit - [your comment here]
 * <li>acct_cycleTransLimit - [your comment here]
 * <li>acct_dailyAmountLimit - [your comment here]
 * <li>acct_cycleAmountLimit - [your comment here]
 * <li>acct_monthTransLimit - [your comment here]
 * <li>acct_otherTransLimit - [your comment here]
 * <li>acct_monthAmountLimit - [your comment here]
 * <li>acct_otherAmountLimit - [your comment here]
 * <li>acct_otherTotalNumberDays - [your comment here]
 * <li>acct_otherRefreshDate - [your comment here]
 * <li>acct_foreignCurrRestrictSet - [your comment here]
 * <li>acct_foreignType - [your comment here]
 * <li>acct_foreignAction - [your comment here]
 * <li>acct_vendorCheck - [your comment here]
 * <li>acct_vendorType - [your comment here]
 * <li>acct_vendorAction - [your comment here]
 * <li>acct_validPreferredTables - [your comment here]
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
public class Frm_setupAccountControls extends ActionForm {

	private String acct_singleLimit = null;
	private String acct_dailyTransLimit = null;
	private String acct_cycleTransLimit = null;
	private String acct_dailyAmountLimit = null;
	private String acct_cycleAmountLimit = null;
	private String acct_monthTransLimit = null;
	private String acct_otherTransLimit = null;
	private String acct_monthAmountLimit = null;
	private String acct_otherAmountLimit = null;
	private String acct_otherTotalNumberDays = null;
	private String acct_otherRefreshDate = null;
	private String acct_foreignCurrRestrictSet = null;
	private String acct_foreignType = null;
	private String acct_foreignAction = null;
	private String acct_vendorCheck = null;
	private String acct_vendorType = null;
	private String acct_vendorAction = "Decline";
	private String acct_validPreferredTables = null;
	private NameDescTable[] vendorNDT =
		{ new NameDescTable("theOptionValue", "theOptionDescr")};

	private String mccgControl = null;
	private AccountInfo accountInfo = new AccountInfo();
	private final static String[] acct_foreignCurrRestrictSetValue =
		{ "Yes", "No" };
	private final static String[] acct_foreignTypeValue =
		{ "Include", "Exclude" };
	private final static String[] acct_foreignActionValue =
		{ "Accept", "Refer", "Decline" };
	private final static String[] acct_vendorCheckValue = { "Yes", "No" };
	private final static String[] acct_vendorTypeValue =
		{ "Include", "Exclude" };
	private final static String[] acct_vendorActionValue =
		{ "Accept", "Refer", "Decline" };
	/**
	 * Get acct_singleLimit
	 * @return String
	 */
	public String getAcct_singleLimit() {
		return acct_singleLimit;
	}

	/**
	 * Set acct_singleLimit
	 * @param <code>String</code>
	 */
	public void setAcct_singleLimit(String a) {
		acct_singleLimit = a;
	}
	/**
	 * Get acct_dailyTransLimit
	 * @return String
	 */
	public String getAcct_dailyTransLimit() {
		return acct_dailyTransLimit;
	}

	/**
	 * Set acct_dailyTransLimit
	 * @param <code>String</code>
	 */
	public void setAcct_dailyTransLimit(String a) {
		acct_dailyTransLimit = a;
	}
	/**
	 * Get acct_cycleTransLimit
	 * @return String
	 */
	public String getAcct_cycleTransLimit() {
		return acct_cycleTransLimit;
	}

	/**
	 * Set acct_cycleTransLimit
	 * @param <code>String</code>
	 */
	public void setAcct_cycleTransLimit(String a) {
		acct_cycleTransLimit = a;
	}
	/**
	 * Get acct_dailyAmountLimit
	 * @return String
	 */
	public String getAcct_dailyAmountLimit() {
		return acct_dailyAmountLimit;
	}

	/**
	 * Set acct_dailyAmountLimit
	 * @param <code>String</code>
	 */
	public void setAcct_dailyAmountLimit(String a) {
		acct_dailyAmountLimit = a;
	}
	/**
	 * Get acct_cycleAmountLimit
	 * @return String
	 */
	public String getAcct_cycleAmountLimit() {
		return acct_cycleAmountLimit;
	}

	/**
	 * Set acct_cycleAmountLimit
	 * @param <code>String</code>
	 */
	public void setAcct_cycleAmountLimit(String a) {
		acct_cycleAmountLimit = a;
	}
	/**
	 * Get acct_monthTransLimit
	 * @return String
	 */
	public String getAcct_monthTransLimit() {
		return acct_monthTransLimit;
	}

	/**
	 * Set acct_monthTransLimit
	 * @param <code>String</code>
	 */
	public void setAcct_monthTransLimit(String a) {
		acct_monthTransLimit = a;
	}
	/**
	 * Get acct_otherTransLimit
	 * @return String
	 */
	public String getAcct_otherTransLimit() {
		return acct_otherTransLimit;
	}

	/**
	 * Set acct_otherTransLimit
	 * @param <code>String</code>
	 */
	public void setAcct_otherTransLimit(String a) {
		acct_otherTransLimit = a;
	}
	/**
	 * Get acct_monthAmountLimit
	 * @return String
	 */
	public String getAcct_monthAmountLimit() {
		return acct_monthAmountLimit;
	}

	/**
	 * Set acct_monthAmountLimit
	 * @param <code>String</code>
	 */
	public void setAcct_monthAmountLimit(String a) {
		acct_monthAmountLimit = a;
	}
	/**
	 * Get acct_otherAmountLimit
	 * @return String
	 */
	public String getAcct_otherAmountLimit() {
		return acct_otherAmountLimit;
	}

	/**
	 * Set acct_otherAmountLimit
	 * @param <code>String</code>
	 */
	public void setAcct_otherAmountLimit(String a) {
		acct_otherAmountLimit = a;
	}
	/**
	 * Get acct_otherTotalNumberDays
	 * @return String
	 */
	public String getAcct_otherTotalNumberDays() {
		return acct_otherTotalNumberDays;
	}

	/**
	 * Set acct_otherTotalNumberDays
	 * @param <code>String</code>
	 */
	public void setAcct_otherTotalNumberDays(String a) {
		acct_otherTotalNumberDays = a;
	}
	/**
	 * Get acct_otherRefreshDate
	 * @return String
	 */
	public String getAcct_otherRefreshDate() {
		return acct_otherRefreshDate;
	}

	/**
	 * Set acct_otherRefreshDate
	 * @param <code>String</code>
	 */
	public void setAcct_otherRefreshDate(String a) {
		acct_otherRefreshDate = a;
	}
	/**
	 * Get acct_foreignCurrRestrictSet
	 * @return String
	 */
	public String getAcct_foreignCurrRestrictSet() {
		return acct_foreignCurrRestrictSet;
	}

	/**
	 * Set acct_foreignCurrRestrictSet
	 * @param <code>String</code>
	 */
	public void setAcct_foreignCurrRestrictSet(String a) {
		acct_foreignCurrRestrictSet = a;
	}
	/**
	 * Get acct_foreignType
	 * @return String
	 */
	public String getAcct_foreignType() {
		return acct_foreignType;
	}

	/**
	 * Set acct_foreignType
	 * @param <code>String</code>
	 */
	public void setAcct_foreignType(String a) {
		acct_foreignType = a;
	}
	/**
	 * Get acct_foreignAction
	 * @return String
	 */
	public String getAcct_foreignAction() {
		return acct_foreignAction;
	}

	/**
	 * Set acct_foreignAction
	 * @param <code>String</code>
	 */
	public void setAcct_foreignAction(String a) {
		acct_foreignAction = a;
	}
	/**
	 * Get acct_vendorCheck
	 * @return String
	 */
	public String getAcct_vendorCheck() {
		return acct_vendorCheck;
	}

	/**
	 * Set acct_vendorCheck
	 * @param <code>String</code>
	 */
	public void setAcct_vendorCheck(String a) {
		acct_vendorCheck = a;
	}
	/**
	 * Get acct_vendorType
	 * @return String
	 */
	public String getAcct_vendorType() {
		return acct_vendorType;
	}

	/**
	 * Set acct_vendorType
	 * @param <code>String</code>
	 */
	public void setAcct_vendorType(String a) {
		acct_vendorType = a;
	}
	/**
	 * Get acct_vendorAction
	 * @return String
	 */
	public String getAcct_vendorAction() {
		return acct_vendorAction;
	}

	/**
	 * Set acct_vendorAction
	 * @param <code>String</code>
	 */
	public void setAcct_vendorAction(String a) {
		acct_vendorAction = a;
	}
	/**
	 * Get acct_validPreferredTables
	 * @return String
	 */
	public String getAcct_validPreferredTables() {
		return acct_validPreferredTables;
	}

	/**
	 * Set acct_validPreferredTables
	 * @param <code>String</code>
	 */
	public void setAcct_validPreferredTables(String a) {
		acct_validPreferredTables = a;
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
	public Frm_setupAccountControls() {

		super();
	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// Reset values are provided as samples only. Change as appropriate.
		/**
				acct_singleLimit = null;
				acct_dailyTransLimit = null;
				acct_cycleTransLimit = null;
				acct_dailyAmountLimit = null;
				acct_cycleAmountLimit = null;
				acct_monthTransLimit = null;
				acct_otherTransLimit = null;
				acct_monthAmountLimit = null;
				acct_otherAmountLimit = null;
				acct_otherTotalNumberDays = null;
				acct_otherRefreshDate = null;
				acct_foreignCurrRestrictSet = null;
				acct_foreignType = null;
				acct_foreignAction = null;
				acct_vendorCheck = null;
				acct_vendorType = null;
				acct_vendorAction = "Decline";
				acct_validPreferredTables = null;
				mccgControl = null;
				accountInfo = new AccountInfo();
		*/
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

	/**
	 * Returns the acct_foreignCurrRestrictSetValue.
	 * @return String[]
	 */
	public String[] getAcct_foreignCurrRestrictSetValue() {
		return acct_foreignCurrRestrictSetValue;
	}

	/**
	 * Returns the vendorNDT.
	 * @return NameDescTable[]
	 */
	public NameDescTable[] getVendorNDT() {
		return vendorNDT;
	}

	/**
	 * Sets the vendorNDT.
	 * @param vendorNDT The vendorNDT to set
	 */
	public void setVendorNDT(NameDescTable[] vendorNDT) {
		this.vendorNDT = vendorNDT;
	}

	/**
	 * Returns the acct_foreignTypeValue.
	 * @return String[]
	 */
	public String[] getAcct_foreignTypeValue() {
		return acct_foreignTypeValue;
	}

	/**
	 * Returns the acct_foreignActionValue.
	 * @return String[]
	 */
	public String[] getAcct_foreignActionValue() {
		return acct_foreignActionValue;
	}

	/**
	 * Returns the acct_vendorCheckValue.
	 * @return String[]
	 */
	public String[] getAcct_vendorCheckValue() {
		return acct_vendorCheckValue;
	}

	/**
	 * Returns the acct_vendorTypeValue.
	 * @return String[]
	 */
	public String[] getAcct_vendorTypeValue() {
		return acct_vendorTypeValue;
	}

	/**
	 * Returns the frm_setupAccountControlsValue.
	 * @return String[]
	 */
	public String[] getAcct_vendorActionValue() {
		return acct_vendorActionValue;
	}

}
