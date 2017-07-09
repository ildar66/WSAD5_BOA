package com.boa.eagls.government.dto.centralaccount;

import org.apache.log4j.Logger;

import java.io.Serializable;

import com.boa.eagls.government.dto.account.Account;


/**
 * <p><small> VDI Company, 21.07.2003 15:13:06</small></p>
 * @author AlexanderZe
 */

public class CentralAcct extends Account implements Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

     static final Logger logger = Logger.getLogger(CentralAcct.class);

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


    private String acctName = STRING_DEFAULT;
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
    /*
    private NameDescTable[] mccgTables;
    private NameDescTable[] vendorTables;
    private CentralAcctTransSummary centralAcctTransSummary;
    private CentralAcctPOC[] centralAcctPOCs;
    private DBOffice dbo;
    private TDOffice tdo;
    private ECEDIOffice ecedi;
    private PaymentOffice paymentOffice;
    private RebateTable rebateTable;
    private com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport;
    private EMall[] eMalls;
    */
    // Constructor
    public CentralAcct() {
        super();
        /*mccgTables = new NameDescTable[0];
        vendorTables = new NameDescTable[0];
        centralAcctTransSummary = new CentralAcctTransSummary();
        centralAcctPOCs = new CentralAcctPOC[0];
        dbo = new DBOffice();
        tdo = new TDOffice();
        ecedi = new ECEDIOffice();
        paymentOffice = new PaymentOffice();
        rebateTable = new RebateTable();
        eMalls = new EMall[0];
        */
    }

    public CentralAcct(CentralAcct centralAcct) {
    }

    // Standard interface methods
    /*
    public static void create(CentralAcct aCentralAcct, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA cAdapter = new CentralAccountDAA(aDASTransport);
        cAdapter.createCentralAccountDetails(aCentralAcct);
    }

    public static void update(CentralAcct oldCentralAcct, CentralAcct newCentralAcct, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        adapter.updateCentralAccountDetails(oldCentralAcct, newCentralAcct);
    }

    public static double retrieveCreditLimit(String centralAcctID, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        AccountDAA adapter = new AccountDAA(aDASTransport);
        return adapter.retrieveCentralAccountCreditLimit(centralAcctID);
        //returns double of credit limit
    }
    */
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
    /*
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
    */
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
    /*
    public CentralAcctTransSummary getCentralAcctTransSummary() {
        return new CentralAcctTransSummary(centralAcctTransSummary);
    }

    public EMall[] getEMalls() {
        EMall[] copyEMalls = new EMall[eMalls.length];
        for (int i = 0; i < eMalls.length; i++) {
            copyEMalls[i] = new EMall(eMalls[i]);
        }
        return copyEMalls;
    }
    */
    //WARNING Retrofit attribute, not filled in all cases.
    public boolean getPastDue61Days() {
        return pastDue61Days;
    }

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements of CentralAcct.

    /*
    public void setEMalls(EMall[] tEMalls) {
        if (tEMalls != null) {
            eMalls = tEMalls;
        } else {
            eMalls[0] = new EMall("", "");
        }
    }
    */
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
    /*
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

    public void setCentralAcctTransSummary(CentralAcctTransSummary tCentralAcctTransSummary) {
        if (tCentralAcctTransSummary != null) {
            centralAcctTransSummary = tCentralAcctTransSummary;
        } else {
            centralAcctTransSummary = new CentralAcctTransSummary();
        }
    }
    */
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

    //-----------------------------------static retrieve methods-----------------------------------------------
    // These static methods are used to retrieve objects that are components of CentralAcct.
    /*
    public static CentralAcctPOC[] retrieveCentralAcctPOCs(String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrieveCentralAcctPOCList(acctNo);
    }

    public static CentralOffice retrieveCentralOffice(int agencyID, int[] hierarchy, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        return retrieveCentralOffice(agencyID, hierarchy, aDASTransport, true);
    }

    public static CentralOffice retrieveCentralOffice(int agencyID, int[] hierarchy, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport, boolean isSetup)
            throws NBException {
        AgencyDAA adapter = new AgencyDAA(aDASTransport);
        return adapter.retrieveCentralOfficeDetail(hierarchy, agencyID, isSetup);
    }

    public static DBOffice retrieveDBOffice(String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrieveDBOffice(acctNo);
    }

    public static TDOffice retrieveTDOffice(String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrieveTDOffice(acctNo);
    }

    public static ECEDIOffice retrieveECEDIOffice(String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrieveECEDIOffice(acctNo);
    }

    public static PaymentOffice retrievePaymentOffice(String acctNo, short searchMethod, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrievePaymentOffice(acctNo, searchMethod);
    }

    public static PaymentOffice retrievePaymentOffice(String acctNo, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        CentralAccountDAA adapter = new CentralAccountDAA(aDASTransport);
        return adapter.retrievePaymentOffice(acctNo, adapter.SETUP_COMPLETED);
    }

    //check credit Limit Polymorph
    public static boolean checkForValidCredit(String acctno, String centralAcctID, short accountType, double oldCreditLimit, double newCreditLimit, com.nb.gsa.Adapter.DataAccess.core.DASTransport aDASTransport)
            throws NBException {
        if (accountType == CENTRAL) {
            //This is the check for a Central Account Credit Limit Change
            double aggregateCreditLimit = AccountController.retrieveAggregateCreditLimit(centralAcctID, aDASTransport);

            if (newCreditLimit >= aggregateCreditLimit) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    */
}

