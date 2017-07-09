package com.boa.eagls.government.controller.formbean.authorizationcontrols;

/**
 * @author IldarS
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MccgInfo {
	private String mccgTableName = null;
	private String mccgAction = null;
	private double singleLimit;
	private int dailyTransLimit;
	private int cycleTransLimit;
	private double dailyAmountLimit;
	private double cycleAmountLimit;
	private int monthTransLimit;
	private int otherTransLimit;
	private double monthAmountLimit;
	private double otherAmountLimit;
	private int otherTotalNumberDays ;
	private String otherRefreshDate = null;
	private String vendorCheck = null;
	private String vendorType = null;
	private String vendorAction = null;
	private String validPreferredTables = null;
//	private String  = null;


	/**
	 * Returns the mccgAction.
	 * @return String
	 */
	public String getMccgAction() {
		return mccgAction;
	}

	/**
	 * Returns the mccgTableName.
	 * @return String
	 */
	public String getMccgTableName() {
		return mccgTableName;
	}


	/**
	 * Returns the otherRefreshDate.
	 * @return String
	 */
	public String getOtherRefreshDate() {
		return otherRefreshDate;
	}


	/**
	 * Returns the validPreferredTables.
	 * @return String
	 */
	public String getValidPreferredTables() {
		return validPreferredTables;
	}

	/**
	 * Returns the vendorAction.
	 * @return String
	 */
	public String getVendorAction() {
		return vendorAction;
	}

	/**
	 * Returns the vendorCheck.
	 * @return String
	 */
	public String getVendorCheck() {
		return vendorCheck;
	}

	/**
	 * Returns the vendorType.
	 * @return String
	 */
	public String getVendorType() {
		return vendorType;
	}


	/**
	 * Sets the mccgAction.
	 * @param mccgAction The mccgAction to set
	 */
	public void setMccgAction(String mccgAction) {
		this.mccgAction = mccgAction;
	}

	/**
	 * Sets the mccgTableName.
	 * @param mccgTableName The mccgTableName to set
	 */
	public void setMccgTableName(String mccgTableName) {
		this.mccgTableName = mccgTableName;
	}


	/**
	 * Sets the otherRefreshDate.
	 * @param otherRefreshDate The otherRefreshDate to set
	 */
	public void setOtherRefreshDate(String otherRefreshDate) {
		this.otherRefreshDate = otherRefreshDate;
	}

	/**
	 * Sets the validPreferredTables.
	 * @param validPreferredTables The validPreferredTables to set
	 */
	public void setValidPreferredTables(String validPreferredTables) {
		this.validPreferredTables = validPreferredTables;
	}

	/**
	 * Sets the vendorAction.
	 * @param vendorAction The vendorAction to set
	 */
	public void setVendorAction(String vendorAction) {
		this.vendorAction = vendorAction;
	}

	/**
	 * Sets the vendorCheck.
	 * @param vendorCheck The vendorCheck to set
	 */
	public void setVendorCheck(String vendorCheck) {
		this.vendorCheck = vendorCheck;
	}

	/**
	 * Sets the vendorType.
	 * @param vendorType The vendorType to set
	 */
	public void setVendorType(String vendorType) {
		this.vendorType = vendorType;
	}

	/**
	 * Returns the singleLimit.
	 * @return double
	 */
	public double getSingleLimit() {
		return singleLimit;
	}

	/**
	 * Sets the singleLimit.
	 * @param singleLimit The singleLimit to set
	 */
	public void setSingleLimit(double singleLimit) {
		this.singleLimit = singleLimit;
	}

	/**
	 * Returns the cycleTransLimit.
	 * @return int
	 */
	public int getCycleTransLimit() {
		return cycleTransLimit;
	}

	/**
	 * Returns the dailyTransLimit.
	 * @return int
	 */
	public int getDailyTransLimit() {
		return dailyTransLimit;
	}

	/**
	 * Returns the monthTransLimit.
	 * @return int
	 */
	public int getMonthTransLimit() {
		return monthTransLimit;
	}

	/**
	 * Returns the otherTransLimit.
	 * @return int
	 */
	public int getOtherTransLimit() {
		return otherTransLimit;
	}

	/**
	 * Sets the cycleTransLimit.
	 * @param cycleTransLimit The cycleTransLimit to set
	 */
	public void setCycleTransLimit(int cycleTransLimit) {
		this.cycleTransLimit = cycleTransLimit;
	}

	/**
	 * Sets the dailyTransLimit.
	 * @param dailyTransLimit The dailyTransLimit to set
	 */
	public void setDailyTransLimit(int dailyTransLimit) {
		this.dailyTransLimit = dailyTransLimit;
	}

	/**
	 * Sets the monthTransLimit.
	 * @param monthTransLimit The monthTransLimit to set
	 */
	public void setMonthTransLimit(int monthTransLimit) {
		this.monthTransLimit = monthTransLimit;
	}

	/**
	 * Sets the otherTransLimit.
	 * @param otherTransLimit The otherTransLimit to set
	 */
	public void setOtherTransLimit(int otherTransLimit) {
		this.otherTransLimit = otherTransLimit;
	}

	/**
	 * Returns the otherTotalNumberDays.
	 * @return int
	 */
	public int getOtherTotalNumberDays() {
		return otherTotalNumberDays;
	}

	/**
	 * Sets the otherTotalNumberDays.
	 * @param otherTotalNumberDays The otherTotalNumberDays to set
	 */
	public void setOtherTotalNumberDays(int otherTotalNumberDays) {
		this.otherTotalNumberDays = otherTotalNumberDays;
	}

	/**
	 * Returns the cycleAmountLimit.
	 * @return double
	 */
	public double getCycleAmountLimit() {
		return cycleAmountLimit;
	}

	/**
	 * Returns the dailyAmountLimit.
	 * @return double
	 */
	public double getDailyAmountLimit() {
		return dailyAmountLimit;
	}

	/**
	 * Returns the monthAmountLimit.
	 * @return double
	 */
	public double getMonthAmountLimit() {
		return monthAmountLimit;
	}

	/**
	 * Returns the otherAmountLimit.
	 * @return double
	 */
	public double getOtherAmountLimit() {
		return otherAmountLimit;
	}

	/**
	 * Sets the cycleAmountLimit.
	 * @param cycleAmountLimit The cycleAmountLimit to set
	 */
	public void setCycleAmountLimit(double cycleAmountLimit) {
		this.cycleAmountLimit = cycleAmountLimit;
	}

	/**
	 * Sets the dailyAmountLimit.
	 * @param dailyAmountLimit The dailyAmountLimit to set
	 */
	public void setDailyAmountLimit(double dailyAmountLimit) {
		this.dailyAmountLimit = dailyAmountLimit;
	}

	/**
	 * Sets the monthAmountLimit.
	 * @param monthAmountLimit The monthAmountLimit to set
	 */
	public void setMonthAmountLimit(double monthAmountLimit) {
		this.monthAmountLimit = monthAmountLimit;
	}

	/**
	 * Sets the otherAmountLimit.
	 * @param otherAmountLimit The otherAmountLimit to set
	 */
	public void setOtherAmountLimit(double otherAmountLimit) {
		this.otherAmountLimit = otherAmountLimit;
	}

}
