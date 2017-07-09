package com.boa.eagls.government.controller.formbean.authorizationcontrols;

/**
 * @author IldarS
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class AccountInfo {
	private String accountNumber = null;
	private String accountName = null;
	private String agencyName = null;
	private String centralId = null;
	private String creditLimit = null;
	private String guid = null;
	/**
	 * Returns the accountName.
	 * @return String
	 */
	public String getAccountName() {
		return accountName;
	}

	/**
	 * Returns the accountNumber.
	 * @return String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Returns the agencyName.
	 * @return String
	 */
	public String getAgencyName() {
		return agencyName;
	}

	/**
	 * Returns the centralId.
	 * @return String
	 */
	public String getCentralId() {
		return centralId;
	}

	/**
	 * Returns the creditLimit.
	 * @return String
	 */
	public String getCreditLimit() {
		return creditLimit;
	}

	/**
	 * Sets the accountName.
	 * @param accountName The accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Sets the accountNumber.
	 * @param accountNumber The accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Sets the agencyName.
	 * @param agencyName The agencyName to set
	 */
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	/**
	 * Sets the centralId.
	 * @param centralId The centralId to set
	 */
	public void setCentralId(String centralId) {
		this.centralId = centralId;
	}

	/**
	 * Sets the creditLimit.
	 * @param creditLimit The creditLimit to set
	 */
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 * Returns the guid.
	 * @return String
	 */
	public String getGuid() {
		return guid;
	}

	/**
	 * Sets the guid.
	 * @param guid The guid to set
	 */
	public void setGuid(String guid) {
		this.guid = guid;
	}

}
