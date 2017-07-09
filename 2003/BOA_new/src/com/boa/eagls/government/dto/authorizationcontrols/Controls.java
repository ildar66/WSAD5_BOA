package com.boa.eagls.government.dto.authorizationcontrols;
/**
 * <code>Controls is a part of Authroization Controls
 * It is the parent class of the AcctControls and the MCCGControls objects.</code>
 *
 /* 	@version 1.0 $Modtime:   02 Oct 2000 12:04:52  $$Revision: 1.2 $
 /* 	@author  Craig Lavender
 **/
import com.boa.eagls.government.util.DateUtil;
import java.io.Serializable;
import java.util.Date;
import com.boa.eagls.government.business.BusinessObject;

/**
 * @author IldarS
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class Controls extends BusinessObject implements Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

    //static finals for vendorType && foreignType && MCCGAction
    public static final short INCLUDE = 0;
    public static final short EXCLUDE = 1;
    public static final short NULL_VALUE = 3;  //Used for vendorAction as well.

    //static finals for vendorAction && Foreign Action in AcctControls
    public static final short ACCEPT = 0;
    public static final short DECLINE = 1;
    public static final short REFER = 2;  //for mccgAction as well

    private double singlePurchaseLimit = DOUBLE_DEFAULT;
    private int dailyTransLimit = INT_DEFAULT;
    private double dailyAmtLimit = DOUBLE_DEFAULT;
    private int cycleTransLimit = INT_DEFAULT;
    private double cycleAmtLimit = DOUBLE_DEFAULT;
    private int monthlyTransLimit = INT_DEFAULT;
    private double monthlyAmtLimit = DOUBLE_DEFAULT;
    private Date otherRefreshDate = null;
    private int otherNoDays = INT_DEFAULT;
    private int otherTransLimit = INT_DEFAULT;
    private double otherAmtLimit = DOUBLE_DEFAULT;
    private boolean vendorCheck = BOOLEAN_DEFAULT;
    private short vendorType = SHORT_DEFAULT;
    private short vendorAction = SHORT_DEFAULT;
    private String vendorTableName = STRING_DEFAULT;

    public Controls() {
    }

    //copy constructor
    public Controls(Controls tControls) {
        setSinglePurchaseLimit(tControls.getSinglePurchaseLimit());
        setDailyTransLimit(tControls.getDailyTransLimit());
        setDailyAmtLimit(tControls.getDailyAmtLimit());
        setCycleTransLimit(tControls.getCycleTransLimit());
        setCycleAmtLimit(tControls.getCycleAmtLimit());
        setMonthlyTransLimit(tControls.getMonthlyTransLimit());
        setMonthlyAmtLimit(tControls.getMonthlyAmtLimit());
        setOtherRefreshDate(tControls.getOtherRefreshDate());
        setOtherNoDays(tControls.getOtherNoDays());
        setOtherTransLimit(tControls.getOtherTransLimit());
        setOtherAmtLimit(tControls.getOtherAmtLimit());
        setVendorCheck(tControls.getVendorCheck());
        setVendorType(tControls.getVendorType());
        setVendorAction(tControls.getVendorAction());
        setVendorTableName(tControls.getVendorTableName());
    }

//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from Controls.

    public double getSinglePurchaseLimit() {
        return singlePurchaseLimit;
    }

    public int getDailyTransLimit() {
        return dailyTransLimit;
    }

    public double getDailyAmtLimit() {
        return dailyAmtLimit;
    }

    public int getCycleTransLimit() {
        return cycleTransLimit;
    }

    public double getCycleAmtLimit() {
        return cycleAmtLimit;
    }

    public int getMonthlyTransLimit() {
        return monthlyTransLimit;
    }

    public double getMonthlyAmtLimit() {
        return monthlyAmtLimit;
    }

    public Date getOtherRefreshDate() {
        if (otherRefreshDate != null) {
            //return new Date(otherRefreshDate.toString());
            return (Date)otherRefreshDate.clone();
        } else {
            return (Date) null;
        }
    }

    public int getOtherNoDays() {
        return otherNoDays;
    }

    public int getOtherTransLimit() {
        return otherTransLimit;
    }

    public double getOtherAmtLimit() {
        return otherAmtLimit;
    }

    public boolean getVendorCheck() {
        return vendorCheck;
    }

    public short getVendorType() {
        return vendorType;
    }

    public short getVendorAction() {
        return vendorAction;
    }

    public String getVendorTableName() {
        return new String(vendorTableName);
    }

    //--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data element values in Controls.

    public void setSinglePurchaseLimit(double tSinglePurchaseLimit) {
        singlePurchaseLimit = tSinglePurchaseLimit;
    }

    public void setSinglePurchaseLimit(Double tSinglePurchaseLimit) {
        singlePurchaseLimit = validateDouble(tSinglePurchaseLimit);
    }

    public void setDailyTransLimit(int tDailyTransLimit) {
        dailyTransLimit = tDailyTransLimit;
    }

    public void setDailyTransLimit(Integer tDailyTransLimit) {
        dailyTransLimit = validateInteger(tDailyTransLimit);
    }

    public void setDailyAmtLimit(double tDailyAmtLimit) {
        dailyAmtLimit = tDailyAmtLimit;
    }

    public void setDailyAmtLimit(Double tDailyAmtLimit) {
        dailyAmtLimit = validateDouble(tDailyAmtLimit);
    }

    public void setCycleTransLimit(int tCycleTransLimit) {
        cycleTransLimit = tCycleTransLimit;
    }

    public void setCycleTransLimit(Integer tCycleTransLimit) {
        cycleTransLimit = validateInteger(tCycleTransLimit);
    }

    public void setCycleAmtLimit(double tCycleAmtLimit) {
        cycleAmtLimit = tCycleAmtLimit;
    }

    public void setCycleAmtLimit(Double tCycleAmtLimit) {
        cycleAmtLimit = validateDouble(tCycleAmtLimit);
    }

    public void setMonthlyTransLimit(int tMonthlyTransLimit) {
        monthlyTransLimit = tMonthlyTransLimit;
    }

    public void setMonthlyTransLimit(Integer tMonthlyTransLimit) {
        monthlyTransLimit = validateInteger(tMonthlyTransLimit);
    }

    public void setMonthlyAmtLimit(double tMonthlyAmtLimit) {
        monthlyAmtLimit = tMonthlyAmtLimit;
    }

    public void setMonthlyAmtLimit(Double tMonthlyAmtLimit) {
        monthlyAmtLimit = validateDouble(tMonthlyAmtLimit);
    }

    public void setOtherRefreshDate(Date tOtherRefreshDate) {
        otherRefreshDate = tOtherRefreshDate;
    }

    public void setOtherNoDays(int tOtherNoDays) {
        otherNoDays = tOtherNoDays;
    }

    public void setOtherNoDays(Integer tOtherNoDays) {
        otherNoDays = validateInteger(tOtherNoDays);
    }

    public void setOtherTransLimit(int tOtherTransLimit) {
        otherTransLimit = tOtherTransLimit;
    }

    public void setOtherTransLimit(Integer tOtherTransLimit) {
        otherTransLimit = validateInteger(tOtherTransLimit);
    }

    public void setOtherAmtLimit(double tOtherAmtLimit) {
        otherAmtLimit = tOtherAmtLimit;
    }

    public void setOtherAmtLimit(Double tOtherAmtLimit) {
        otherAmtLimit = validateDouble(tOtherAmtLimit);
    }

    public void setVendorCheck(boolean tVendorCheck) {
        vendorCheck = tVendorCheck;
    }

    public void setVendorCheck(Boolean tVendorCheck) {
        vendorCheck = validateBoolean(tVendorCheck);
    }

    public void setVendorType(short tVendorType) {
        vendorType = tVendorType;
    }

    public void setVendorAction(short tVendorAction) {
        vendorAction = tVendorAction;
    }

    public void setVendorTableName(String tVendorTableName) {
        vendorTableName = validateString(tVendorTableName);
    }

//---------------------------------------------------------Action Methods-----------------------

    public boolean equals(Controls aControls) {

        return ((this.singlePurchaseLimit == aControls.singlePurchaseLimit)
                && (this.dailyTransLimit == aControls.dailyTransLimit)
                && (this.dailyAmtLimit == aControls.dailyAmtLimit)
                && (this.cycleTransLimit == aControls.cycleTransLimit)
                && (this.cycleAmtLimit == aControls.cycleAmtLimit)
                && (this.monthlyTransLimit == aControls.monthlyTransLimit)
                && (this.monthlyAmtLimit == aControls.monthlyAmtLimit)
                && (DateUtil.convertDateToSafeString(this.otherRefreshDate).equals((DateUtil.convertDateToString(aControls.otherRefreshDate))))
                && (this.otherNoDays == aControls.otherNoDays)
                && (this.otherTransLimit == aControls.otherTransLimit)
                && (this.otherAmtLimit == aControls.otherAmtLimit)
                && (this.vendorCheck == aControls.vendorCheck)
                && (this.vendorType == aControls.vendorType)
                && (this.vendorAction == aControls.vendorAction)
                && (this.vendorTableName.equals(aControls.vendorTableName))
                );
    }
}
