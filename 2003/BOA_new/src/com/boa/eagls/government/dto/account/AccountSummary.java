package com.boa.eagls.government.dto.account;

import org.apache.log4j.Logger;

import java.io.Serializable;
import com.boa.eagls.government.dto.hierarchy.Hierarchy;
import com.boa.eagls.government.business.BusinessObject;

/**
 * <code>This is the general summary object for all account types.
 * It will for functionalities that need to show general account information  </code>
 * <p><small> VDI Company, 30.07.2003 15:09:14</small></p>
 * @author StanislavS
 */


public class AccountSummary extends BusinessObject implements Serializable {
    private static Logger log4j = Logger.getLogger(AccountSummary.class);

    //static finals for accountType & BillingType
    public static final short CENTRAL = 0;     //used for BillingType
    public static final short INDIVIDUAL = 1;  //used for BillingType
    public static final short MASTERCARD = 2;
    public static final short DIVERSION_ACCT = 3;

    //static finals for status
    public static final short OPEN = 0;
    public static final short CLOSED = 1;


    //The following is used for ProgramType = product description in the database
    public static final short PURCHASE = 0;
    public static final short TRAVEL = 1;
    public static final short FLEET = 2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY = 4;

    // The following were added to accomodate Individual Statement
    private String agencyID;
    private String agencyName;
    private double singlePurchaseLimit;
    private double creditLimit;
    private String centralAcctID;
    private short programType;
    private short billingType;
    private String accountingCode;
    private String accountingCenterID;
    private Hierarchy[] hierarchy;
    private AccountStatus[] accountStatuses;
    //used for summary in other searches
    private String accountNumber;
    private String firstName;
    private String lastName;            //MCVehicle & Central account names use last names.
    private String equipmentID;         //VIN number
    private short accountType;
    private boolean accountActive = true;  //this is a retrofit,only search Account retrieves will fills this field.
    private boolean pastDue61Days;  //this is a retrofit,only search Account retrieves will fills this field.
    private short status;  //this is a retrofit,only search Account retrieves will fills this field.
    private String cenAccountNumber; //used for RapidRecon

    public AccountSummary() {
        accountStatuses = new AccountStatus[1];
        hierarchy = new Hierarchy[HIERARCHY_LIMIT];
    }

    public AccountSummary(AccountSummary tAccountSummary) {
        setAgencyID(tAccountSummary.getAgencyID());
        setAgencyName(tAccountSummary.getAgencyName());
        setSinglePurchaseLimit(tAccountSummary.getSinglePurchaseLimit());
        setCreditLimit(tAccountSummary.getCreditLimit());
        setHierarchy(tAccountSummary.getHierarchy());
        setCentralAcctID(tAccountSummary.getCentralAcctID());
        setProgramType(tAccountSummary.getProgramType());
        setBillingType(tAccountSummary.getBillingType());
        setAccountingCode(tAccountSummary.getAccountingCode());
        setAccountingCenterID(tAccountSummary.getAccountingCenterID());
        setAccountStatuses(tAccountSummary.getAccountStatuses());
        setAccountNumber(tAccountSummary.getAccountNumber());
        setFirstName(tAccountSummary.getFirstName());
        setLastName(tAccountSummary.getLastName());
        setEquipmentID(tAccountSummary.getEquipmentID());
        setAccountType(tAccountSummary.getAccountType());
        setStatus(tAccountSummary.getStatus());
        setAccountActive(tAccountSummary.isAccountActive());
        setPastDue61Days(tAccountSummary.isPastDue61Days());
        setCenAccountNumber(tAccountSummary.getCenAccountNumber());
    }

    public String getAgencyID() {
        return agencyID;
    }

    public void setAgencyID(String agencyID) {
        this.agencyID = agencyID;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public double getSinglePurchaseLimit() {
        return singlePurchaseLimit;
    }

    public void setSinglePurchaseLimit(double singlePurchaseLimit) {
        this.singlePurchaseLimit = singlePurchaseLimit;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getCentralAcctID() {
        return centralAcctID;
    }

    public void setCentralAcctID(String centralAcctID) {
        this.centralAcctID = centralAcctID;
    }

    public short getProgramType() {
        return programType;
    }

    public void setProgramType(short programType) {
        this.programType = programType;
    }

    public short getBillingType() {
        return billingType;
    }

    public void setBillingType(short billingType) {
        this.billingType = billingType;
    }

    public String getAccountingCode() {
        return accountingCode;
    }

    public void setAccountingCode(String accountingCode) {
        this.accountingCode = accountingCode;
    }

    public String getAccountingCenterID() {
        return accountingCenterID;
    }

    public void setAccountingCenterID(String accountingCenterID) {
        this.accountingCenterID = accountingCenterID;
    }

    public Hierarchy[] getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Hierarchy[] hierarchy) {
        this.hierarchy = hierarchy;
    }

    public AccountStatus[] getAccountStatuses() {
        return accountStatuses;
    }

    public void setAccountStatuses(AccountStatus[] accountStatuses) {
        this.accountStatuses = accountStatuses;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEquipmentID() {
        return equipmentID;
    }

    public void setEquipmentID(String equipmentID) {
        this.equipmentID = equipmentID;
    }

    public short getAccountType() {
        return accountType;
    }

    public void setAccountType(short accountType) {
        this.accountType = accountType;
    }

    public boolean isAccountActive() {
        return accountActive;
    }

    public void setAccountActive(boolean accountActive) {
        this.accountActive = accountActive;
    }

    public boolean isPastDue61Days() {
        return pastDue61Days;
    }

    public void setPastDue61Days(boolean pastDue61Days) {
        this.pastDue61Days = pastDue61Days;
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public String getCenAccountNumber() {
        return cenAccountNumber;
    }

    public void setCenAccountNumber(String cenAccountNumber) {
        this.cenAccountNumber = cenAccountNumber;
    }

}
