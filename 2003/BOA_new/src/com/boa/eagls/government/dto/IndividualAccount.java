package com.boa.eagls.government.dto;

import com.boa.eagls.government.dto.account.Account;

import java.util.Date;

/**
 * Individual Account value object
 * @author Oleg Klimenko
 * Date: 23.07.2003
 * Time: 17:54:09
 */
public class IndividualAccount extends Account{
    //The attributes otherwise noted map to screen elements described in SDRs.
    private String centralAcctName = STRING_DEFAULT;
    private String centralAcctNo = STRING_DEFAULT;
    private boolean usesTravelersChecks = BOOLEAN_DEFAULT;
    private boolean hasATMAccess = BOOLEAN_DEFAULT;
    private boolean usesDecrementingCard = BOOLEAN_DEFAULT;
    private String cardType = STRING_DEFAULT;          // needs to be a drop down list of choices during setup
    private String cardDesign = STRING_DEFAULT;
    private String ssn = STRING_DEFAULT;
    private String employeeID = STRING_DEFAULT;
    private String homePhone = STRING_DEFAULT;
    private String approvingOfficer = STRING_DEFAULT;
    private boolean cardExists = BOOLEAN_DEFAULT;
    private boolean generatePaperStatement = BOOLEAN_DEFAULT;
    private String grade = STRING_DEFAULT;            // needs to be a drop down list of choices during setup
    private String employmentStatus = STRING_DEFAULT; // needs to be a drop down list of choices during setup
    private boolean accountActive = true;  //this is a retrofit,only IndividualAcctPartial retrieve will fills this field.
    private boolean pastDue61Days = BOOLEAN_DEFAULT;  //this is a retrofit,only IndividualAcctPartial retrieve will fills this field.
    private String accountingCenterName = STRING_DEFAULT;
    private String optionSetID = STRING_DEFAULT;
    private Date batchDate = DATE_DEFAULT;
    private Date closeDate = DATE_DEFAULT;
    private Date openDate = DATE_DEFAULT;
    private String previousAccount = STRING_DEFAULT;
    private double warrantLevelDollarAmount = DOUBLE_DEFAULT;
    private String optionSetDescription = STRING_DEFAULT;
    private String hierarchyTransferPending = STRING_DEFAULT;
    private double currentCreditLimit = DOUBLE_DEFAULT;
    private double cashLimit = DOUBLE_DEFAULT;
    private double tempCashLimit = DOUBLE_DEFAULT;
//    private Date startDate = DATE_DEFAULT;
//    private Date endDate = DATE_DEFAULT;
    private char creditLimitSign = CHAR_DEFAULT;
    private double creditLimitChangeAmt = DOUBLE_DEFAULT;
    private String reasonCode = STRING_DEFAULT;
    private String originalReasonCode = STRING_DEFAULT;

    // Constructor
    public IndividualAccount() {
        super();
    }

    /*public IndividualAccount(IndividualAccount tIndividualAcct) {
        super(tIndividualAcct);

        setCentralAcctName(tIndividualAcct.getCentralAcctName());
        setCentralAcctNo(tIndividualAcct.getCentralAcctNo());
        setUsesTravelersChecks(tIndividualAcct.getUsesTravelersChecks());
        setHasATMAccess(tIndividualAcct.getHasATMAccess());
        setUsesDecrementingCard(tIndividualAcct.getUsesDecrementingCard());
        setCardType(tIndividualAcct.getCardType());          // needs to be a drop down list of choices during setup
        setCardDesign(tIndividualAcct.getCardDesign());
        setSsn(tIndividualAcct.getSSN());
        setEmployeeID(tIndividualAcct.getEmployeeID());
        setHomePhone(tIndividualAcct.getHomePhone());
        setApprovingOfficer(tIndividualAcct.getApprovingOfficer());
        setCardExists(tIndividualAcct.getCardExists());
        setGeneratePaperStatement(tIndividualAcct.getGeneratePaperStatement());
        setGrade(tIndividualAcct.getGrade());            // needs to be a drop down list of choices during setup
        setEmploymentStatus(tIndividualAcct.getEmploymentStatus()); // needs to be a drop down list of choices during setup
        setAcctTransSummary(tIndividualAcct.getAcctTransSummary());
        setAcctControls(tIndividualAcct.getAcctControls());
        setMCCGControls(tIndividualAcct.getMCCGControls());
        setPreviousAccount(tIndividualAcct.getPreviousAccount());
        setOpenDate(tIndividualAcct.getOpenDate());
        setPointOfContacts(tIndividualAcct.getPointOfContacts());
        setCloseDate(tIndividualAcct.getCloseDate());
        setBatchDate(tIndividualAcct.getBatchDate());
        setIndividualStatement(tIndividualAcct.getIndividualStatement());
        setAccountingCenterName(tIndividualAcct.getAccountingCenterName());
        setOptionSetID(tIndividualAcct.getOptionSetID());
        setOptionSetDescription(tIndividualAcct.getOptionSetDescription());
        setAccountActive(tIndividualAcct.getAccountActive());
        setPastDue61Days(tIndividualAcct.getPastDue61Days());
        setHierarchyTransferPending(tIndividualAcct.getHierarchyTransferPending());

    }*/

    //*******************************************Individual Account maintainenance methods**************************/

    //************************** Change ATM Pin************************/
    //This function is based on response from MQ msg NB07 from TSYS
    //current pin is not stored on EAGLS

//--------------------------------------get methods-----------------------------------------------

    // These methods are used to retrieve data elements from IndividualAcct.


    public double getCashLimit() {
        return cashLimit;
    }

    public double getTempCashLimit() {
        return tempCashLimit;
    }

//    public Date getStartDate() {
//      return startDate;
//    }

//    public Date getEndDate() {
//      return endDate;
//    }


    public double getCurrentCreditLimit() {
        return currentCreditLimit;
    }

    public char getCreditLimitChangeSign() {
        return creditLimitSign;
    }

    public double getCreditLimitChangeAmt() {
        return creditLimitChangeAmt;
    }

    public String getReasonCode() {
        return reasonCode;
    }

    public String getOriginalReasonCode() {
        return originalReasonCode;
    }

    public String getHierarchyTransferPending() {
        return new String(hierarchyTransferPending);
    }

    public double getWarrantLevelDollarAmount() {
        return warrantLevelDollarAmount;
    }

    public String getOptionSetDescription() {
        return optionSetDescription;
    }

    public String getOptionSetID() {
        return optionSetID;
    }

    public String getAccountingCenterName() {
        return accountingCenterName;
    }

    public String getPreviousAccount() {
        return previousAccount;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public Date getBatchDate() {
        return batchDate;
    }

    public String getCentralAcctName() {
        return centralAcctName;
    }

    public String getCentralAcctNo() {
        return centralAcctNo;
    }

    public boolean getUsesTravelersChecks() {
        return usesTravelersChecks;
    }

    public boolean getHasATMAccess() {
        return hasATMAccess;
    }

    public boolean getUsesDecrementingCard() {
        return usesDecrementingCard;
    }

    public String getCardType() {
        return cardType;
    }

    public String getCardDesign() {
        return cardDesign;
    }

    public String getSSN() {
        return ssn;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getApprovingOfficer() {
        return approvingOfficer;
    }

    public boolean getCardExists() {
        return cardExists;
    }

    public boolean getGeneratePaperStatement() {
        return generatePaperStatement;
    }

    public String getGrade() {
        return grade;
    }

    public String getEmploymentStatus() {
        return employmentStatus;
    }

    //WARNING Retrofit attribute, not filled in all cases.
    public boolean getAccountActive() {
        return accountActive;
    }

    //WARNING Retrofit attribute, not filled in all cases.
    public boolean getPastDue61Days() {
        return pastDue61Days;
    }


//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements of IndividualAcct.


    public void setCashLimit(double tCashLimit) {
        cashLimit = tCashLimit;
    }

    public void setTempCashLimit(double ttempCashLimit) {
        tempCashLimit = ttempCashLimit;
    }


//
//    public void setStartDate(Date tStartDate) {
//      startDate = tStartDate;
//    }

//    public void setEndDate(Date tEndDate) {
//      endDate = tEndDate;
//    }


    public void setCurrentCreditLimit(double tCurrentCreditLimit) {
        currentCreditLimit = tCurrentCreditLimit;
    }

    public void setCreditLimitChangeSign(char tCreditLimitSign) {
        creditLimitSign = tCreditLimitSign;
    }

    public void setCreditLimitChangeAmt(double tCreditLimitChangeAmt) {
        creditLimitChangeAmt = tCreditLimitChangeAmt;
    }

    public void setReasonCode(String tReasonCode) {
        reasonCode = tReasonCode;
    }

    protected void setOriginalReasonCode(String tOriginalReasonCode) {
        originalReasonCode = tOriginalReasonCode;
    }

    public void setHierarchyTransferPending(String tHierarchyTransferPending) {
        hierarchyTransferPending = tHierarchyTransferPending;
    }

    public void setWarrantLevelDollarAmount(Double tWarrantLevelDollarAmount) {
        warrantLevelDollarAmount = tWarrantLevelDollarAmount.doubleValue();
    }

    public void setWarrantLevelDollarAmount(double tWarrantLevelDollarAmount) {
        warrantLevelDollarAmount = tWarrantLevelDollarAmount;
    }

    public void setPreviousAccount(String tPreviousAccount) {
        previousAccount = tPreviousAccount;
    }

    public void setOpenDate(Date tOpenDate) {
        openDate = tOpenDate;
    }


    public void setCloseDate(Date tCloseDate) {
        closeDate = tCloseDate;
    }

    public void setBatchDate(Date tBatchDate) {
        batchDate = tBatchDate;
    }

    public void setAccountingCenterName(String tAccountingCenterName) {
        accountingCenterName = tAccountingCenterName;
    }

    public void setOptionSetDescription(String tOptionSetDescription) {
        optionSetDescription = tOptionSetDescription;
    }

    public void setOptionSetID(String tOptionSetID) {
        optionSetID = tOptionSetID;
    }

    public void setCentralAcctName(String tCentralAcctName) {
        centralAcctName = tCentralAcctName;
    }

    public void setCentralAcctNo(String tCentralAcctNo) {
        centralAcctNo = tCentralAcctNo;
    }

    public void setUsesTravelersChecks(boolean tUsesTravelersChecks) {
        usesTravelersChecks = tUsesTravelersChecks;
    }

    public void setUsesTravelersChecks(Boolean tUsesTravelersChecks) {
        usesTravelersChecks = tUsesTravelersChecks.booleanValue();
    }

    public void setHasATMAccess(boolean tHasATMAccess) {
        hasATMAccess = tHasATMAccess;
    }

    public void setHasATMAccess(Boolean tHasATMAccess) {
        hasATMAccess = tHasATMAccess.booleanValue();
    }

    public void setUsesDecrementingCard(boolean tUsesDecrementingCard) {
        usesDecrementingCard = tUsesDecrementingCard;
    }

    public void setUsesDecrementingCard(Boolean tUsesDecrementingCard) {
        usesDecrementingCard = tUsesDecrementingCard.booleanValue();
    }

    public void setCardType(String tCardType) {
        cardType = tCardType;
    }

    public void setCardDesign(String tCardDesign) {
        cardDesign = tCardDesign;
    }

    public void setSsn(String tSSN) {
        ssn = tSSN;
    }

    public void setEmployeeID(String tEmployeeID) {
        employeeID = tEmployeeID;
    }

    public void setHomePhone(String tHomePhone) {
        homePhone = tHomePhone;
    }

    public void setApprovingOfficer(String tApprovingOfficer) {
        approvingOfficer = tApprovingOfficer;
    }

    public void setCardExists(boolean tCardExists) {
        cardExists = tCardExists;
    }

    public void setCardExists(Boolean tCardExists) {
        cardExists = tCardExists.booleanValue();
    }

    public void setGeneratePaperStatement(boolean tGeneratePaperStatement) {
        generatePaperStatement = tGeneratePaperStatement;
    }

    public void setGeneratePaperStatement(Boolean tGeneratePaperStatement) {
        generatePaperStatement = tGeneratePaperStatement.booleanValue();
    }

    public void setGrade(String tGrade) {
        grade = tGrade;
    }

    public void setEmploymentStatus(String tEmploymentStatus) {
        employmentStatus = tEmploymentStatus;
    }


    public void setAccountActive(boolean tAccountActive) {
        accountActive = tAccountActive;
    }

    private boolean isEqual(Object obj1, Object obj2) {
        if (obj1 == null && obj2 == null) {
            return true;
        } else if (obj1 == null || obj2 == null) {
            return false;
        } else {
            return obj1.equals(obj2);
        }
    }


}
