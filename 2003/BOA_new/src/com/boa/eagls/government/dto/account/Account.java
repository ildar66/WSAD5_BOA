package com.boa.eagls.government.dto.account;

import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.dto.ApplicationSystemErrors;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.Profile;
import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;
import java.util.Date;

/**
 * Value Object for Account entity
 * @author Oleg Klimenko
 * Date: 08.07.2003
 * Time: 19:30:11
 */
public class Account extends BusinessObject implements Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

    //The following is used for ProgramType = product description in the database
    public static final short PURCHASE = 0;
    public static final short TRAVEL = 1;
    public static final short FLEET = 2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY = 4;

    //static finals used account status.
    public static final short OPEN = 0;
    public static final short PAST_DUE = 1;
    public static final short OVER_LIMIT = 2;
    public static final short CLOSED = 3;

    //static finals for accountType
    public static final short CENTRAL = 0;     //used for BillingType as well
    public static final short INDIVIDUAL = 1;  //used for BillingType as well
    public static final short MASTERCARD_VEHICLE = 2;
    public static final short DIVERSION_ACCT = 3;

    //static final for changeAccountStatus
    public static final short ADD = 0;
    public static final short DELETE = 1;

    private boolean issueTravelersChecks = BOOLEAN_DEFAULT;
    private boolean hasATMAccess = BOOLEAN_DEFAULT;
    private boolean usesDecrementingCard = BOOLEAN_DEFAULT;   //buisness rules involved see SDRs
    private String acctName = STRING_DEFAULT;
    private String centralAcctID = STRING_DEFAULT;
    private String acctNo = STRING_DEFAULT;
    private String agencyName = STRING_DEFAULT;
    private String accountingCenterID = STRING_DEFAULT;
    private short acctStatus = SHORT_DEFAULT;
    private short programType = SHORT_DEFAULT;            // productType is not needed for inquiry
    private String defaultAccountingCode = STRING_DEFAULT;
    private boolean issueConvenienceChecks = BOOLEAN_DEFAULT;
    private short billingType = SHORT_DEFAULT;
    private String restricted = STRING_DEFAULT;
    private Date accountExpDate = DATE_DEFAULT;
    private double creditLimit = DOUBLE_DEFAULT;
    private short accountType = SHORT_DEFAULT;

// code written below was added by  on 01/14/2003
    private double cashLimit = DOUBLE_DEFAULT;
    private double tempCashLimit = DOUBLE_DEFAULT;
    private String tempCashStartDate = null;
    private String tempCashEndDate = null;
//  code written above was added by  on 01/14/2003 to incorporate Cash Limits

    private String mqErrorMessage = STRING_DEFAULT;
    private double convenienceCheckNTE = DOUBLE_DEFAULT;
    private String optionSetDescription = STRING_DEFAULT;
    private String[] rankGrades = null;
    private String[] employeeStatuses = null;
    private Date activateDate = null;
    private Date deactivateDate = null;

    //The following are object component
    private HierarchyDTO[] hierarchy = new HierarchyDTO[HIERARCHY_LIMIT];
    private Profile profile = new Profile();
    private String[] statusDescriptions;


    public Profile getProfile() {
        return new Profile(profile);
    }

    public Profile getRefProfile() {
        return profile;
    }

    public void setProfile(Profile tProfile) {
        if (tProfile != null) {
            profile = tProfile;
        } else {
            profile = new Profile();
        }
    }

// These methods are used to retrieve data elements from Profile.

    public String getBusinessPhoneExtension() {
        return profile.getBusinessPhoneExtension();
    }

    public String getLastName() {
        return profile.getLastName();
    }

    public String getFirstName() {
        return profile.getFirstName();
    }

    public String getAddress1() {
        return profile.getAddress1();
    }

    public String getAddress2() {
        return profile.getAddress2();
    }

    public String getAddress3() {
        return profile.getAddress3();
    }

    public String getAddress4() {
        return profile.getAddress4();
    }

    public String getCity() {
        return profile.getCity();
    }

    public String getStateOrProvince() {
        return profile.getStateOrProvince();
    }

    public String getCountry() {
        return profile.getCountry();
    }

    public String getZip() {
        return profile.getZip();
    }

    public String getBusinessPhone() {
        return profile.getBusinessPhone();
    }

    public String getFax() {
        return profile.getFax();
    }

    public String getEMail() {
        return profile.getEMail();
    }
//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in Profile.

    public void setBusinessPhoneExtension(String tBusinessPhoneExtension) {
        profile.setBusinessPhoneExtension(tBusinessPhoneExtension);
    }

    public void setLastName(String tLastName) {
        profile.setLastName(tLastName);
    }

    public void setFirstName(String tFirstName) {
        profile.setFirstName(tFirstName);
    }

    public void setAddress1(String tAddress1) {
        profile.setAddress1(tAddress1);
    }

    public void setAddress2(String tAddress2) {
        profile.setAddress2(tAddress2);
    }

    public void setAddress3(String tAddress3) {
        profile.setAddress3(tAddress3);
    }

    public void setAddress4(String tAddress4) {
        profile.setAddress4(tAddress4);
    }

    public void setCity(String tCity) {
        profile.setCity(tCity);
    }

    public void setStateOrProvince(String tStateOrProvince) {
        profile.setStateOrProvince(tStateOrProvince);
    }

    public void setCountry(String tCountry) {
        profile.setCountry(tCountry);
    }

    public void setZip(String tZip) {
        profile.setZip(tZip);
    }

    public void setBusinessPhone(String tBusinessPhone) {
        profile.setBusinessPhone(tBusinessPhone);
    }

    public void setFax(String tFax) {
        profile.setFax(tFax);
    }

    public void setEMail(String tEMail) {
        profile.setEMail(tEMail);
    }

//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from IndividualAcct.

    public HierarchyDTO[] getHierarchy() {
        return hierarchy;
    }

    public Date getActivateDate() {
        return activateDate;
    }

    public Date getDeactivateDate() {
        return deactivateDate;
    }

    public String[] getEmployeeStatuses() {
        String[] tEmployeeStatuses = new String[employeeStatuses.length];
        for (int i = 0; i < employeeStatuses.length; i++) {
            tEmployeeStatuses[i] = employeeStatuses[i];
        }
        return tEmployeeStatuses;
    }

    public String[] getRankGrades() {
        String[] tRankGrades = new String[rankGrades.length];
        for (int i = 0; i < rankGrades.length; i++) {
            tRankGrades[i] = rankGrades[i];
        }
        return tRankGrades;
    }

    public String getOptionSetDescription() {
        return new String(optionSetDescription);
    }

    public double getConvenienceCheckNTE() {
        return convenienceCheckNTE;
    }

    public String[] getStatusDescriptions() {
        String[] tString = new String[statusDescriptions.length];
        for (int i = 0; i < statusDescriptions.length; i++) {
            tString[i] = statusDescriptions[i];
        }
        return tString;
    }

    public String getCentralAcctID() {
        return new String(centralAcctID);
    }

    public String getAcctNo() {
        return new String(acctNo);
    }

    public String getAgencyName() {
        return new String(agencyName);
    }

    public String getAccountingCenterID() {
        return new String(accountingCenterID);
    }

    public short getAcctStatus() {
        return acctStatus;
    }

    public short getProgramType() {
        return programType;
    }

    public String getProgramTypeDescription() {
        switch (programType) {
            case Account.PURCHASE:
                return "Purchase";
            case Account.TRAVEL:
                return "Travel";
            case Account.FLEET:
                return "Fleet";
            case Account.INTEGRATE:
                return "Integrated";
            case Account.INTERAGENCY:
                return "Interagency";
            default:
                return "";
        }
    }


    public String getDefaultAccountingCode() {
        return new String(defaultAccountingCode);
    }

    public boolean getIssueConvenienceChecks() {
        return issueConvenienceChecks;
    }

    public short getBillingType() {
        return billingType;
    }

    public String getBillingTypeDescription() {
        switch (billingType) {
            case INDIVIDUAL:
                return "Individual";
            case CENTRAL:
                return "Central";
            default:
                return "";
        }
    }

    public String getRestricted() {
        return restricted;
    }

/*
     D.S., 02/05/2003 10:57

    new Date(accountExpDate.toString()) throws a illegal arguement exception.
    As the accountExpDate is of type java.util.Date, code was written without
    converting the date type to string

*/
    public Date getAccountExpDate() {
        /* modified code  02/05/2003 */
        return accountExpDate;
        /* end of modified code */



        /* original code 02/05/2003
          return new Date(accountExpDate.toString());
        */
    }

    public double getCreditLimit() {
        return creditLimit;
    }

//  added by  on 01/14/2003

    public double getCashLimit() {
        return cashLimit;
    }


    public double getTempCashLimit() {
        return tempCashLimit;
    }

    public String getTempCashStartDate() {
        return tempCashStartDate;
    }


    public boolean isStartDateNull() {

        boolean nullValue = false;
        if (tempCashStartDate == null)
            nullValue = true;
        return nullValue;
    }


    public String getTempCashEndDate() {
        return tempCashEndDate;
    }

    public boolean isEndDateNull() {
        boolean nullValue = false;
        if (tempCashEndDate == null)
            nullValue = true;
        return nullValue;
    }

    public void setAcctName(String tAcctName) {
        acctName = validateString(tAcctName);
    }

    public String getAcctName() {
        return acctName;
    }

    public void setIssueTravelersChecks(boolean tIssueTravelersChecks) {
        issueTravelersChecks = tIssueTravelersChecks;
    }

    public boolean isIssueTravelersChecks() {
        return issueTravelersChecks;
    }

    public void setHasATMAccess(boolean tHasATMAccess) {
        hasATMAccess = tHasATMAccess;
    }

    public boolean isHasATMAccess() {
        return hasATMAccess;
    }

    public void setUsesDecrementingCard(boolean tUsesDecrementingCard) {
        usesDecrementingCard = tUsesDecrementingCard;
    }

    public boolean isUsesDecrementingCard() {
        return usesDecrementingCard;
    }

// code added by  on 01/14/2003 ends here


    public HierarchyDTO[] getAHierarchy() {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[hierarchy.length];
        for (int i = 0; i < hierarchy.length; i++) {
            copyHierarchy[i] = hierarchy[i];
        }
        return copyHierarchy;
    }

    public String getMQErrorMessage() {
        return new String(mqErrorMessage);
    }


//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements of IndividualAcct.

    public void setHierarchy(HierarchyDTO[] tHierarchy) {
        hierarchy = validateHierarchy(tHierarchy);
    }


    public void setActivateDate(Date tActivateDate) {
        activateDate = tActivateDate;
    }

    public void setDeactivateDate(Date tDeactivateDate) {
        deactivateDate = tDeactivateDate;
    }

    public void setEmployeeStatuses(String[] tEmployeeStatuses) {
        employeeStatuses = new String[tEmployeeStatuses.length];
        for (int i = 0; i < tEmployeeStatuses.length; i++) {
            employeeStatuses[i] = tEmployeeStatuses[i];
        }
    }

    public void setRankGrades(String[] tRankGrades) {
        rankGrades = new String[tRankGrades.length];
        for (int i = 0; i < tRankGrades.length; i++) {
            rankGrades[i] = tRankGrades[i];
        }
    }

    public void setOptionSetDescription(String tOptionSetDescription) {
        optionSetDescription = tOptionSetDescription;
    }

    public void setConvenienceCheckNTE(Double tConvenienceCheckNTE) {
        convenienceCheckNTE = validateDouble(tConvenienceCheckNTE);
    }

    public void setConvenienceCheckNTE(double tConvenienceCheckNTE) {
        convenienceCheckNTE = tConvenienceCheckNTE;
    }

    public void setStatusDescriptions(String[] tStatusDescriptions) {
        statusDescriptions = new String[tStatusDescriptions.length];
        for (int i = 0; i < statusDescriptions.length; i++) {
            statusDescriptions[i] = tStatusDescriptions[i];
        }
    }

    public void setCentralAcctID(String tCentralAcctID) {
        centralAcctID = validateString(tCentralAcctID);
    }

    public void setAcctNo(String tAcctNo) {
        acctNo = validateString(tAcctNo);
    }

    public void setAgencyName(String tAgencyName) {
        agencyName = validateString(tAgencyName);
    }

    public void setAccountingCenterID(String tAccountingCenterID) {
        accountingCenterID = validateString(tAccountingCenterID);
    }

    public void setAcctStatus(short tAcctStatus) {
        acctStatus = tAcctStatus;
    }

    public void setAcctStatus(String tAcctStatus) throws EaglsException {
        if (tAcctStatus == null || "".equals(tAcctStatus)) {
            throw new EaglsException("SYS_E0002:Invalid Account Status returned from database.");
        }

        if (("C").equals(tAcctStatus)) {
            acctStatus = CLOSED;
        } else {
            acctStatus = OPEN;
        }
    }

    public void setProgramType(short tProgramType) {
        programType = tProgramType;
    }

    public void setProgramType(String tProgramType) throws EaglsException {
        if (tProgramType == null) {
            throw new EaglsException(ApplicationSystemErrors.ERROR_DB_TYPE_MISMATCH);
        }
        tProgramType = tProgramType.trim().toUpperCase();
        if (tProgramType.length() != 1) {
            throw new EaglsException(ApplicationSystemErrors.ERROR_DB_TYPE_MISMATCH);
        }
        switch (tProgramType.charAt(0)) {
            case 'P':
                {
                    setProgramType(Account.PURCHASE);
                    break;
                }
            case 'T':
                {
                    setProgramType(Account.TRAVEL);
                    break;
                }
            case 'F':
                {
                    setProgramType(Account.FLEET);
                    break;
                }
            case 'I':
                {
                    setProgramType(Account.INTEGRATE);
                    break;
                }
            case 'A':
                {
                    setProgramType(Account.INTERAGENCY);
                    break;
                }
            default :
                {
                    throw new EaglsException(ApplicationSystemErrors.ERROR_DB_TYPE_MISMATCH);
                }
        }
    }

    public void setDefaultAccountingCode(String tDefaultAccountingCode) {
        defaultAccountingCode = validateString(tDefaultAccountingCode);
    }

    public void setIssueConvenienceChecks(boolean tIssueConvenienceChecks) {
        issueConvenienceChecks = tIssueConvenienceChecks;
    }

    public void setIssueConvenienceChecks(Boolean tIssueConvenienceChecks) {
        issueConvenienceChecks = validateBoolean(tIssueConvenienceChecks);
    }

    public void setBillingType(short tBillingType) {
        billingType = tBillingType;
    }

    public void setRestricted(String tRestricted) {
        restricted = tRestricted;
    }

    public void setAccountExpDate(Date tAccountExpDate) {
        accountExpDate = validateDate(tAccountExpDate);
    }

    public void setCreditLimit(double tCreditLimit) {
        creditLimit = tCreditLimit;
    }

    public void setCreditLimit(Double tCreditLimit) {
        creditLimit = validateDouble(tCreditLimit);
    }

// Coded by  on 01/14/2003
    public void setCashLimit(double tCashLimit) {
        cashLimit = tCashLimit;
    }

    public void setCashLimit(Double tCashLimit) {
        cashLimit = validateDouble(tCashLimit);
    }


    public void setTempCashLimit(double tTempCashLimit) {
        tempCashLimit = tTempCashLimit;
    }

    public void setTempCashLimit(Double tTempCashLimit) {
        tempCashLimit = validateDouble(tTempCashLimit);
    }

    public void setTempCashStartDate(String tTempCashStartDate) {
        tempCashStartDate = tTempCashStartDate;
    }

    public void setTempCashEndDate(String tTempCashEndDate) {
        tempCashEndDate = tTempCashEndDate;
    }

    public short getAccountType() {
        return accountType;
    }

    public void setAccountType(short accountType) {
        this.accountType = accountType;
    }

}