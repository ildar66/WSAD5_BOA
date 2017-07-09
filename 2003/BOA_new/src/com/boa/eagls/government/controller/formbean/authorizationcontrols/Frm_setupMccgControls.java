package com.boa.eagls.government.controller.formbean.authorizationcontrols;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import com.boa.eagls.government.dto.NameDescTable;

/**
 * Form bean for a Struts application.
 * Users may access 28 fields on this form:
 * <ul>
 * <li>mccg_singleLimit - [your comment here]
 * <li>mccg_dailyTransLimit - [your comment here]
 * <li>mccg_cycleTransLimit - [your comment here]
 * <li>mccg_dailyAmountLimit - [your comment here]
 * <li>mccg_cycleAmountLimit - [your comment here]
 * <li>mccg_monthTransLimit - [your comment here]
 * <li>mccg_otherTransLimit - [your comment here]
 * <li>mccg_monthAmountLimit - [your comment here]
 * <li>mccg_otherAmountLimit - [your comment here]
 * <li>mccg_otherTotalNumberDays - [your comment here]
 * <li>mccg_otherRefreshDate - [your comment here]
 * <li>acctControl - [your comment here]
 * <li>mccgControl - [your comment here]
 * <li>accountInfo.accountNumber - [your comment here]
 * <li>accountInfo.accountName - [your comment here]
 * <li>accountInfo.agencyName - [your comment here]
 * <li>accountInfo.centralId - [your comment here]
 * <li>accountInfo.creditLimit - [your comment here]
 * <li>accountInfo.guid - [your comment here]
 * <li>but_submitButton - [your comment here]
 * <li>but_resetButton - [your comment here]
 * <li>but_cancelButton - [your comment here]
 * </ul>
 * @version 	1.0
 * @author
 */
public class Frm_setupMccgControls extends ActionForm {

	private String mccg_singleLimit = null;
	private String mccg_dailyTransLimit = null;
	private String mccg_cycleTransLimit = null;
	private String mccg_dailyAmountLimit = null;
	private String mccg_cycleAmountLimit = null;
	private String mccg_monthTransLimit = null;
	private String mccg_otherTransLimit = null;
	private String mccg_monthAmountLimit = null;
	private String mccg_otherAmountLimit = null;
	private String mccg_otherTotalNumberDays = null;
	private String mccg_otherRefreshDate = null;
	private String acctControl = null;
	private String mccgControl = null;
	private String mccg_vendorCheck =null;
	private AccountInfo accountInfo = new AccountInfo();
	private NameDescTable[] vendorNDT =
		{ new NameDescTable("preferredOptionName", "preferredOptionDisplay")};
    private NameDescTable[] mccgNDT = null;
    private boolean showAddMCCG;
    private String [] mccgOptionName = {"mccgOptionName"};
    private String [] mccgOptionDisplay = {"mccgOptionDisplay"};
    private String mccg_mccgAction = null;
    private String mccg_mccgTableName =null;
    private String mccg_vendorType = null;
    private String mccg_vendorAction = "Decline";
    private String mccg_validPreferredTables = null;

	/**
	 * Get mccg_singleLimit
	 * @return String
	 */
	public String getMccg_singleLimit() {
		return mccg_singleLimit;
	}

	/**
	 * Set mccg_singleLimit
	 * @param <code>String</code>
	 */
	public void setMccg_singleLimit(String m) {
		mccg_singleLimit = m;
	}
	/**
	 * Get mccg_dailyTransLimit
	 * @return String
	 */
	public String getMccg_dailyTransLimit() {
		return mccg_dailyTransLimit;
	}

	/**
	 * Set mccg_dailyTransLimit
	 * @param <code>String</code>
	 */
	public void setMccg_dailyTransLimit(String m) {
		mccg_dailyTransLimit = m;
	}
	/**
	 * Get mccg_cycleTransLimit
	 * @return String
	 */
	public String getMccg_cycleTransLimit() {
		return mccg_cycleTransLimit;
	}

	/**
	 * Set mccg_cycleTransLimit
	 * @param <code>String</code>
	 */
	public void setMccg_cycleTransLimit(String m) {
		mccg_cycleTransLimit = m;
	}
	/**
	 * Get mccg_dailyAmountLimit
	 * @return String
	 */
	public String getMccg_dailyAmountLimit() {
		return mccg_dailyAmountLimit;
	}

	/**
	 * Set mccg_dailyAmountLimit
	 * @param <code>String</code>
	 */
	public void setMccg_dailyAmountLimit(String m) {
		mccg_dailyAmountLimit = m;
	}
	/**
	 * Get mccg_cycleAmountLimit
	 * @return String
	 */
	public String getMccg_cycleAmountLimit() {
		return mccg_cycleAmountLimit;
	}

	/**
	 * Set mccg_cycleAmountLimit
	 * @param <code>String</code>
	 */
	public void setMccg_cycleAmountLimit(String m) {
		mccg_cycleAmountLimit = m;
	}
	/**
	 * Get mccg_monthTransLimit
	 * @return String
	 */
	public String getMccg_monthTransLimit() {
		return mccg_monthTransLimit;
	}

	/**
	 * Set mccg_monthTransLimit
	 * @param <code>String</code>
	 */
	public void setMccg_monthTransLimit(String m) {
		mccg_monthTransLimit = m;
	}
	/**
	 * Get mccg_otherTransLimit
	 * @return String
	 */
	public String getMccg_otherTransLimit() {
		return mccg_otherTransLimit;
	}

	/**
	 * Set mccg_otherTransLimit
	 * @param <code>String</code>
	 */
	public void setMccg_otherTransLimit(String m) {
		mccg_otherTransLimit = m;
	}
	/**
	 * Get mccg_monthAmountLimit
	 * @return String
	 */
	public String getMccg_monthAmountLimit() {
		return mccg_monthAmountLimit;
	}

	/**
	 * Set mccg_monthAmountLimit
	 * @param <code>String</code>
	 */
	public void setMccg_monthAmountLimit(String m) {
		mccg_monthAmountLimit = m;
	}
	/**
	 * Get mccg_otherAmountLimit
	 * @return String
	 */
	public String getMccg_otherAmountLimit() {
		return mccg_otherAmountLimit;
	}

	/**
	 * Set mccg_otherAmountLimit
	 * @param <code>String</code>
	 */
	public void setMccg_otherAmountLimit(String m) {
		mccg_otherAmountLimit = m;
	}
	/**
	 * Get mccg_otherTotalNumberDays
	 * @return String
	 */
	public String getMccg_otherTotalNumberDays() {
		return mccg_otherTotalNumberDays;
	}

	/**
	 * Set mccg_otherTotalNumberDays
	 * @param <code>String</code>
	 */
	public void setMccg_otherTotalNumberDays(String m) {
		mccg_otherTotalNumberDays = m;
	}
	/**
	 * Get mccg_otherRefreshDate
	 * @return String
	 */
	public String getMccg_otherRefreshDate() {
		return mccg_otherRefreshDate;
	}

	/**
	 * Set mccg_otherRefreshDate
	 * @param <code>String</code>
	 */
	public void setMccg_otherRefreshDate(String m) {
		mccg_otherRefreshDate = m;
	}
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
	public Frm_setupMccgControls() {

		super();

	}
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		// Reset values are provided as samples only. Change as appropriate.
/**
		mccg_singleLimit = null;
		mccg_dailyTransLimit = null;
		mccg_cycleTransLimit = null;
		mccg_dailyAmountLimit = null;
		mccg_cycleAmountLimit = null;
		mccg_monthTransLimit = null;
		mccg_otherTransLimit = null;
		mccg_monthAmountLimit = null;
		mccg_otherAmountLimit = null;
		mccg_otherTotalNumberDays = null;
		mccg_otherRefreshDate = null;
		acctControl = null;
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
	 * Returns the mccgNDT.
	 * @return NameDescTable[]
	 */
	public NameDescTable[] getMccgNDT() {
		return mccgNDT;
	}

	/**
	 * Sets the mccgNDT.
	 * @param mccgNDT The mccgNDT to set
	 */
	public void setMccgNDT(NameDescTable[] mccgNDT) {
		this.mccgNDT = mccgNDT;
	}

	/**
	 * Returns the mccg_vendorCheck.
	 * @return String
	 */
	public String getMccg_vendorCheck() {
		return mccg_vendorCheck;
	}

	/**
	 * Sets the mccg_vendorCheck.
	 * @param mccg_vendorCheck The mccg_vendorCheck to set
	 */
	public void setMccg_vendorCheck(String mccg_vendorCheck) {
		this.mccg_vendorCheck = mccg_vendorCheck;
	}

	/**
	 * Returns the showAddMCCG.
	 * @return boolean
	 */
	public boolean isShowAddMCCG() {
		return showAddMCCG;
	}

	/**
	 * Sets the showAddMCCG.
	 * @param showAddMCCG The showAddMCCG to set
	 */
	public void setShowAddMCCG(boolean showAddMCCG) {
		this.showAddMCCG = showAddMCCG;
	}

	/**
	 * Returns the mccgOptionDisplay.
	 * @return String[]
	 */
	public String[] getMccgOptionDisplay() {
		return mccgOptionDisplay;
	}

	/**
	 * Returns the mccgOptionName.
	 * @return String[]
	 */
	public String[] getMccgOptionName() {
		return mccgOptionName;
	}

	/**
	 * Sets the mccgOptionDisplay.
	 * @param mccgOptionDisplay The mccgOptionDisplay to set
	 */
	public void setMccgOptionDisplay(String[] mccgOptionDisplay) {
		this.mccgOptionDisplay = mccgOptionDisplay;
	}

	/**
	 * Sets the mccgOptionName.
	 * @param mccgOptionName The mccgOptionName to set
	 */
	public void setMccgOptionName(String[] mccgOptionName) {
		this.mccgOptionName = mccgOptionName;
	}

	/**
	 * Returns the mccg_mccgAction.
	 * @return String
	 */
	public String getMccg_mccgAction() {
		return mccg_mccgAction;
	}

	/**
	 * Sets the mccg_mccgAction.
	 * @param mccg_mccgAction The mccg_mccgAction to set
	 */
	public void setMccg_mccgAction(String mccg_mccgAction) {
		this.mccg_mccgAction = mccg_mccgAction;
	}

	/**
	 * Returns the mccg_mccgTableName.
	 * @return String
	 */
	public String getMccg_mccgTableName() {
		return mccg_mccgTableName;
	}

	/**
	 * Sets the mccg_mccgTableName.
	 * @param mccg_mccgTableName The mccg_mccgTableName to set
	 */
	public void setMccg_mccgTableName(String mccg_mccgTableName) {
		this.mccg_mccgTableName = mccg_mccgTableName;
	}

	/**
	 * Returns the mccg_vendorType.
	 * @return String
	 */
	public String getMccg_vendorType() {
		return mccg_vendorType;
	}

	/**
	 * Sets the mccg_vendorType.
	 * @param mccg_vendorType The mccg_vendorType to set
	 */
	public void setMccg_vendorType(String mccg_vendorType) {
		this.mccg_vendorType = mccg_vendorType;
	}

	/**
	 * Returns the mccg_vendorAction.
	 * @return String
	 */
	public String getMccg_vendorAction() {
		return mccg_vendorAction;
	}

	/**
	 * Sets the mccg_vendorAction.
	 * @param mccg_vendorAction The mccg_vendorAction to set
	 */
	public void setMccg_vendorAction(String mccg_vendorAction) {
		this.mccg_vendorAction = mccg_vendorAction;
	}

	/**
	 * Returns the mccg_validPreferredTables.
	 * @return String
	 */
	public String getMccg_validPreferredTables() {
		return mccg_validPreferredTables;
	}

	/**
	 * Sets the mccg_validPreferredTables.
	 * @param mccg_validPreferredTables The mccg_validPreferredTables to set
	 */
	public void setMccg_validPreferredTables(String mccg_validPreferredTables) {
		this.mccg_validPreferredTables = mccg_validPreferredTables;
	}

}
