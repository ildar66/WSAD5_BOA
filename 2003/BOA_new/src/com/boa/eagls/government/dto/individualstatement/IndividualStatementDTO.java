package com.boa.eagls.government.dto.individualstatement;

import org.apache.log4j.Logger;

import java.io.Serializable;
import java.util.Date;

/**
 * <p><small> VDI Company, 30.07.2003 11:53:24</small></p>
 * @author StanislavS
 */
public class IndividualStatementDTO implements Serializable {
    private static Logger log4j = Logger.getLogger(IndividualStatementDTO.class);
    // Public finals for statement status
    public static final short CERTIFIED = 0;
    public static final short INVOICED = 1;

    //The following is used for ProgramType = product description in the database
    public static final short PURCHASE = 0;
    public static final short TRAVEL = 1;
    public static final short FLEET = 2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY = 4;

    private String agencyName;
    private String indAccountNumber;
    private String cenAccountNumber;
    private short programType;
    private String lastName;
    private String firstName;
    private String statementDate;
    private String accountingCenterID;
    private short accountType;
    private short billingType;
    private short statementStatus;


    // Constructor
    public IndividualStatementDTO() {
        //setStatementDate(new Date((long) 0));
    }

    public IndividualStatementDTO(IndividualStatementDTO statementSummary) {
        setAgencyName(statementSummary.getAgencyName());
        setCenAccountNumber(statementSummary.getCenAccountNumber());
        setIndAccountNumber(statementSummary.getIndAccountNumber());
        setProgramType(statementSummary.getProgramType());
        setLastName(statementSummary.getLastName());
        setFirstName(statementSummary.getFirstName());
        setStatementDate(statementSummary.getStatementDate());
        setAccountingCenterID(statementSummary.getAccountingCenterID());
        setAccountType(statementSummary.getAccountType());
        setBillingType(statementSummary.getBillingType());
        setStatementStatus(statementSummary.getStatementStatus());
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getIndAccountNumber() {
        return indAccountNumber;
    }

    public void setIndAccountNumber(String indAccountNumber) {
        this.indAccountNumber = indAccountNumber;
    }

    public String getCenAccountNumber() {
        return cenAccountNumber;
    }

    public void setCenAccountNumber(String cenAccountNumber) {
        this.cenAccountNumber = cenAccountNumber;
    }

    public short getProgramType() {
        return programType;
    }

    public void setProgramType(short programType) {
        this.programType = programType;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStatementDate() {
        return statementDate;
    }

    public void setStatementDate(String statementDate) {
        this.statementDate = statementDate;
    }

    public String getAccountingCenterID() {
        return accountingCenterID;
    }

    public void setAccountingCenterID(String accountingCenterID) {
        this.accountingCenterID = accountingCenterID;
    }

    public short getAccountType() {
        return accountType;
    }

    public void setAccountType(short accountType) {
        this.accountType = accountType;
    }

    public short getBillingType() {
        return billingType;
    }

    public void setBillingType(short billingType) {
        this.billingType = billingType;
    }

    public short getStatementStatus() {
        return statementStatus;
    }

    public void setStatementStatus(short statementStatus) {
        this.statementStatus = statementStatus;
    }
}