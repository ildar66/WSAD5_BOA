package com.boa.eagls.government.dto.account;

import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;

import java.io.Serializable;

/**
 * used for sending Struts Form Bean from Web layer to Service layer. Using this interface Web
 * layer hides unnecessary methods and keeps only getters. Used in Veia Individual Account details
 * @author Oleg Klimenko
 */
public interface CentralAccount extends Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

    //static finals for accountType
    public static final short CENTRAL_ACCT = 0;
    public static final short DIVERSION_ACCT = 1;

    //static finals for program type
    public static final short PURCHASE = 0;
    public static final short TRAVEL = 1;
    public static final short FLEET = 2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY = 4;

    //static finals for fleetType
    public static final short MASTERCARD = 0;
    public static final short VOYAGER = 1;

    int getCentralAcctID();

    void setCentralAcctID(int centralAcctID);

    String getCentralAccountName();

    void setCentralAccountName(String centralAccountName);

    String getCentralAccountNumber();

    void setCentralAccountNumber(String centralAccountNumber);

    String getHdn_programNumber();

    void setHdn_programNumber(String hdn_programNumber);

    int getHdn_centralAccountID();

    void setHdn_centralAccountID(int hdn_centralAccountID);

    String getHdn_centralAccountName();

    void setHdn_centralAccountName(String hdn_centralAccountName);

    String getHdn_programType();

    void setHdn_programType(String hdn_programType);

    String getHdn_centralAccountNumber();

    void setHdn_centralAccountNumber(String hdn_centralAccountNumber);

    String getHdn_travelersCheckFlag();

    void setHdn_travelersCheckFlag(String hdn_travelersCheckFlag);

    String getHdn_billingType();

    void setHdn_billingType(String hdn_billingType);

    String getAccountingCenterID();

    void setAccountingCenterID(String accountingCenterID);

    String getTxt_lastName();

    void setTxt_lastName(String txt_lastName);

    String getTxt_firstName();

    void setTxt_firstName(String txt_firstName);

    String getTxt_socialSecurityNumber();

    void setTxt_socialSecurityNumber(String txt_socialSecurityNumber);

    String getTxt_addressLine1();

    void setTxt_addressLine1(String txt_addressLine1);

    String getTxt_addressLine2();

    void setTxt_addressLine2(String txt_addressLine2);

    String getTxt_addressLine3();

    void setTxt_addressLine3(String txt_addressLine3);

    String getTxt_addressLine4();

    void setTxt_addressLine4(String txt_addressLine4);

    String getTxt_city();

    void setTxt_city(String txt_city);

    String getTxt_state();

    void setTxt_state(String txt_state);

    String getTxt_country();

    void setTxt_country(String txt_country);

    String getTxt_zipCode();

    void setTxt_zipCode(String txt_zipCode);

    String getTxt_businessPhone();

    void setTxt_businessPhone(String txt_businessPhone);

    String getTxt_emailAddress();

    void setTxt_emailAddress(String txt_emailAddress);

    String getTxt_masterAccountingCode();

    void setTxt_masterAccountingCode(String txt_masterAccountingCode);

    String getTxt_accountExpirationDate();

    void setTxt_accountExpirationDate(String txt_accountExpirationDate);

    String getTxt_creditLimit();

    void setTxt_creditLimit(String txt_creditLimit);

    void setTxt_employeeIdentificationNumber(String txt_employeeIdentificationNumber);

    String getHdn_crdtLmt();

    void setHdn_crdtLmt(String hdn_crdtLmt);

    String getTxt_programDescription();

    void setTxt_programDescription(String txt_programDescription);

    public String getHdn_hl1();

    public void setHdn_hl1(String hdn_hl1);

    public String getHdn_hl2();

    public void setHdn_hl2(String hdn_hl2);

    public String getHdn_hl3();

    public void setHdn_hl3(String hdn_hl3);

    public String getHdn_hl4();

    public void setHdn_hl4(String hdn_hl4);

    public String getHdn_hl5();

    public void setHdn_hl5(String hdn_hl5);

    public String getHdn_hl6();

    public void setHdn_hl6(String hdn_hl6);

    public String getHdn_hl7();

    public void setHdn_hl7(String hdn_hl7);

    public String getHdn_hl8();

    public void setHdn_hl8(String hdn_hl8);

    public String getTxt_hl1Descr();

    public void setTxt_hl1Descr(String txt_hl1Descr);

    public String getTxt_hl2Descr();

    public void setTxt_hl2Descr(String txt_hl2Descr);

    public String getTxt_hl3Descr();

    public void setTxt_hl3Descr(String txt_hl3Descr);

    public String getTxt_hl4Descr();

    public void setTxt_hl4Descr(String txt_hl4Descr);

    public String getTxt_hl5Descr();

    public void setTxt_hl5Descr(String txt_hl5Descr);

    public String getTxt_hl6Descr();

    public void setTxt_hl6Descr(String txt_hl6Descr);

    public String getTxt_hl7Descr();

    public void setTxt_hl7Descr(String txt_hl7Descr);

    public String getTxt_hl8Descr();

    public void setTxt_hl8Descr(String txt_hl8Descr);

    String getHdn_oldAcct();

    void setHdn_oldAcct(String hdn_oldAcct);

    public String getTxt_acctCenterID();

    public void setTxt_acctCenterID(String txt_acctCenterID);

    public BrowseHierarchyFields getBrowseHierarchyFields();

    public void setBrowseHierarchyFields(BrowseHierarchyFields browseHierarchyFields);

    BrowseHierarchyFields getBrowseHierarchyFieldsReadOnly();

    void setBrowseHierarchyFieldsReadOnly(BrowseHierarchyFields browseHierarchyFieldsReadOnly);

/*    private String acctName = STRING_DEFAULT;
    private int[] hierarchy = INT_ARRAY_DEFAULT;
    private short accountType = SHORT_DEFAULT;
    private int agencyID = INT_DEFAULT;                    //agency ID may not be necessary
    private int[] convenienceChecksNumbers = INT_ARRAY_DEFAULT;
    private boolean issueTravelersChecks = BOOLEAN_DEFAULT;
    private boolean usesDecrementingCard = BOOLEAN_DEFAULT;   //buisness rules involved see SDRs
    private boolean hasATMAccess = BOOLEAN_DEFAULT;
    private boolean cityPairProgram = BOOLEAN_DEFAULT;
    private boolean invoiceMediumPaper = BOOLEAN_DEFAULT;
    private boolean invoiceMediumEDI = BOOLEAN_DEFAULT;
    private boolean invoiceMediumElectronic = BOOLEAN_DEFAULT;
    private String[] cardTypes = STRING_ARRAY_DEFAULT;       //this is NOT the correct display, should be one CMID number, description
    private short fleetType = SHORT_DEFAULT;
    private String mccgTableName = STRING_DEFAULT;               //used to get MCCGTables by name

    private boolean travelFlag = BOOLEAN_DEFAULT;
    private boolean fleetFlag = BOOLEAN_DEFAULT;
    private boolean purchaseFlag = BOOLEAN_DEFAULT;
    private boolean interagencyFlag = BOOLEAN_DEFAULT;
    private boolean integratedFlag = BOOLEAN_DEFAULT;
    private boolean pastDue61Days = BOOLEAN_DEFAULT;  //this is a retrofit,only CentralAcctPartial retrieve will fill this field.

    //The following are component objects as data elements
    private NameDescTable[] mccgTables;
    private NameDescTable[] vendorTables;
//    private CentralAcctTransSummary centralAcctTransSummary;
//    private CentralAcctPOC[] centralAcctPOCs;
//    private DBOffice dbo;
//    private TDOffice tdo;
//    private ECEDIOffice ecedi;
//    private PaymentOffice paymentOffice;
//    private RebateTable rebateTable;
//    private EMall[] eMalls;
//
    // Constructor
    public CentralAccount() {
        super();
        mccgTables = new NameDescTable[0];
        vendorTables = new NameDescTable[0];
//        centralAcctTransSummary = new CentralAcctTransSummary();
//        centralAcctPOCs = new CentralAcctPOC[0];
//        dbo = new DBOffice();
//        tdo = new TDOffice();
//        ecedi = new ECEDIOffice();
//        paymentOffice = new PaymentOffice();
//        rebateTable = new RebateTable();
//        eMalls = new EMall[0];
    }

//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from CentralAcct.

    public String getAcctName() {
        return new String(acctName);
    }

    public int getAgencyID() {
        return agencyID;
    }

    public short getAccountType() {
        return accountType;
    }

    public boolean getIssueTravelersChecks() {
        return issueTravelersChecks;
    }

    public boolean getUsesDecrementingCard() {
        return usesDecrementingCard;
    }

    public boolean getHasATMAccess() {
        return hasATMAccess;
    }

    public boolean getCityPairProgram() {
        return cityPairProgram;
    }

    public boolean getInvoiceMediumPaper() {
        return invoiceMediumPaper;
    }

    public boolean getInvoiceMediumEDI() {
        return invoiceMediumEDI;
    }

    public boolean getInvoiceMediumElectronic() {
        return invoiceMediumElectronic;
    }

    public NameDescTable[] getMCCGTables() {
        NameDescTable[] copyMCCGTables = new NameDescTable[mccgTables.length];
        for (int i = 0; i < mccgTables.length; i++) {
            copyMCCGTables[i] = mccgTables[i];
        }
        return copyMCCGTables;
    }

    public NameDescTable[] getVendorTables() {
        NameDescTable[] copyVendorTables = new NameDescTable[vendorTables.length];
        for (int i = 0; i < vendorTables.length; i++) {
            copyVendorTables[i] = vendorTables[i];
        }
        return copyVendorTables;
    }

    public short getFleetType() {
        return fleetType;
    }

    public int[] getConvenienceChecksNumbers() {
        return convenienceChecksNumbers;
    }

    public boolean getTravelFlag() {
        return travelFlag;
    }

    public boolean getFleetFlag() {
        return fleetFlag;
    }

    public boolean getPurchaseFlag() {
        return purchaseFlag;
    }

    public boolean getInteragencyFlag() {
        return interagencyFlag;
    }

    public boolean getIntegratedFlag() {
        return integratedFlag;
    }

    //This method will need to be changed to reflect the database data
    public String[] getCardTypes() {
        String[] copyCardTypes = new String[cardTypes.length];
        for (int i = 0; i < cardTypes.length; i++) {
            copyCardTypes[i] = new String(cardTypes[i]);
        }
        return copyCardTypes;
    }

//    public CentralAcctTransSummary getCentralAcctTransSummary() {
//        return new CentralAcctTransSummary(centralAcctTransSummary);
//    }

//    public EMall[] getEMalls() {
//        EMall[] copyEMalls = new EMall[eMalls.length];
//        for (int i = 0; i < eMalls.length; i++) {
//            copyEMalls[i] = new EMall(eMalls[i]);
//        }
//        return copyEMalls;
//    }

    //WARNING Retrofit attribute, not filled in all cases.
    public boolean getPastDue61Days() {
        return pastDue61Days;
    }

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements of CentralAcct.

//    public void setEMalls(EMall[] tEMalls) {
//        if (tEMalls != null) {
//            eMalls = tEMalls;
//        } else {
//            eMalls[0] = new EMall("", "");
//        }
//    }

    public void setAcctName(String tAcctName) {
        acctName = validateString(tAcctName);
    }

    public void setAccountType(short tAccountType) {
        accountType = tAccountType;
    }

    public void setAgencyID(int tAgencyID) {
        agencyID = tAgencyID;
    }

    public void setIssueTravelersChecks(boolean tIssueTravelersChecks) {
        issueTravelersChecks = tIssueTravelersChecks;
    }

    public void setIssueTravelersChecks(Boolean tIssueTravelersChecks) {
        issueTravelersChecks = validateBoolean(tIssueTravelersChecks);
    }

    public void setHasATMAccess(boolean tHasATMAccess) {
        hasATMAccess = tHasATMAccess;
    }

    public void setHasATMAccess(Boolean tHasATMAccess) {
        hasATMAccess = validateBoolean(tHasATMAccess);
    }

    public void setUsesDecrementingCard(boolean tUsesDecrementingCard) {
        usesDecrementingCard = tUsesDecrementingCard;
    }

    public void setUsesDecrementingCard(Boolean tUsesDecrementingCard) {
        usesDecrementingCard = validateBoolean(tUsesDecrementingCard);
    }

    public void setCityPairProgram(boolean tCityPairProgram) {
        cityPairProgram = tCityPairProgram;
    }

    public void setCityPairProgram(Boolean tCityPairProgram) {
        cityPairProgram = validateBoolean(tCityPairProgram);
    }

    public void setCardTypes(String[] tCardTypes) {
        if (tCardTypes != null) {
            cardTypes = tCardTypes;
        } else {
            cardTypes[0] = "";
        }
    }

    public void setInvoiceMediumElectronic(boolean tInvoiceMediumElectronic) {
        invoiceMediumElectronic = tInvoiceMediumElectronic;
    }

    public void setInvoiceMediumElectronic(Boolean tInvoiceMediumElectronic) {
        invoiceMediumElectronic = validateBoolean(tInvoiceMediumElectronic);
    }

    public void setInvoiceMediumPaper(boolean tInvoiceMediumPaper) {
        invoiceMediumPaper = tInvoiceMediumPaper;
    }

    public void setInvoiceMediumPaper(Boolean tInvoiceMediumPaper) {
        invoiceMediumPaper = validateBoolean(tInvoiceMediumPaper);
    }

    public void setInvoiceMediumEDI(boolean tInvoiceMediumEDI) {
        invoiceMediumEDI = tInvoiceMediumEDI;
    }

    public void setInvoiceMediumEDI(Boolean tInvoiceMediumEDI) {
        invoiceMediumEDI = validateBoolean(tInvoiceMediumEDI);
    }

    public void setMCCGTables(NameDescTable[] tMCCGTables) {
        if (tMCCGTables != null) {
            mccgTables = tMCCGTables;
        }
    }

    public void setVendorTables(NameDescTable[] tVendorTables) {
        if (tVendorTables != null) {
            vendorTables = tVendorTables;
        }
    }

//    public void setCentralAcctTransSummary(CentralAcctTransSummary tCentralAcctTransSummary) {
//        if (tCentralAcctTransSummary != null) {
//            centralAcctTransSummary = tCentralAcctTransSummary;
//        } else {
//            centralAcctTransSummary = new CentralAcctTransSummary();
//        }
//    }

    public void setFleetType(short tFleetType) {
        fleetType = tFleetType;
    }

    public void setConvenienceChecksNumbers(int[] tConvenienceChecksNumbers) {
        convenienceChecksNumbers = tConvenienceChecksNumbers;
    }

    public void setTravelFlag(boolean tTravelFlag) {
        travelFlag = tTravelFlag;
    }

    public void setTravelFlag(Boolean tTravelFlag) {
        travelFlag = validateBoolean(tTravelFlag);
    }

    public void setFleetFlag(boolean tFleetFlag) {
        fleetFlag = tFleetFlag;
    }

    public void setFleetFlag(Boolean tFleetFlag) {
        fleetFlag = validateBoolean(tFleetFlag);
    }

    public void setPurchaseFlag(boolean tPurchaseFlag) {
        purchaseFlag = tPurchaseFlag;
    }

    public void setPurchaseFlag(Boolean tPurchaseFlag) {
        purchaseFlag = validateBoolean(tPurchaseFlag);
    }

    public void setInteragencyFlag(boolean tInteragencyFlag) {
        interagencyFlag = tInteragencyFlag;
    }

    public void setInteragencyFlag(Boolean tInteragencyFlag) {
        interagencyFlag = validateBoolean(tInteragencyFlag);
    }

    public void setIntegratedFlag(boolean tIntegratedFlag) {
        integratedFlag = tIntegratedFlag;
    }

    public void setIntegratedFlag(Boolean tIntegratedFlag) {
        integratedFlag = validateBoolean(tIntegratedFlag);
    }

    public void setPastDue61Days(boolean tPastDue61Days) {
        pastDue61Days = tPastDue61Days;
    }
*/

}
