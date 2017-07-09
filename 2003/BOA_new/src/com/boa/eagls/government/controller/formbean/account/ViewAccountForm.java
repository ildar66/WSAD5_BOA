package com.boa.eagls.government.controller.formbean.account;

import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.dto.account.CentralAccount;
import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;

/**
 * Form bean for View Account in Individual Account Setup
 * @author Oleg Klimenko
 * Date: 08.07.2003
 * Time: 10:31:24
 */
public class ViewAccountForm extends ActionForm implements CentralAccount {// implements

    //The attributes otherwise noted map to screen elements described in SDRs.

    //static finals for accountType
    public static final short CENTRAL_ACCT = 0;
    public static final short DIVERSION_ACCT =1;

    //static finals for program type
    public static final short PURCHASE = 0;
    public static final short TRAVEL =1;
    public static final short FLEET =2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY =4;

    //static finals for fleetType
    public static final short MASTERCARD = 0;
    public static final short VOYAGER =1;

    /*private int txtHl0;
    private int txtHl1;
    private int txtHl2;
    private int txtHl3;
    private int txtHl4;
    private int txtHl5;
    private int txtHl6;
    private int txtHl7;
    private int txtHl8;
    private int txtAcctCenterID;
    private int txtMasterAccountingCode;

    private String cmbCardType;
    private String cmbCardNoncard;
    private String txtCreditLimit;

    private String txtLastName;
    private String txtFirstName;
    private String txtAddressLine1;
    private String txtAddressLine2;
    private String txtAddressLine3;
    private String txtAddressLine4;
    private String txtCity;
    private String txtState;
    private String txtCountry;
    private int txtZipCode;
    private String txtBusinessPhone;
    private String txtEmailAddress;

    private int txtSocialSecurityNumber;
    private int txtEmployeeIdentificationNumber;

    private String cmbStatus; */


    //hidden fields
    private String txt_acctCenterID;
    private int centralAcctID;
    private String centralAccountName;
    private String centralAccountNumber;

    private String hdn_programNumber;
    private int hdn_centralAccountID;
    private String hdn_centralAccountName;
    private String hdn_programType;
    private String hdn_centralAccountNumber;
    private String hdn_travelersCheckFlag;
    private String hdn_billingType;
    private String accountingCenterID;
    private String txt_lastName;
    private String txt_firstName;
    private String txt_socialSecurityNumber;
    private String txt_addressLine1;
    private String txt_addressLine2;
    private String txt_addressLine3;
    private String txt_addressLine4;
    private String txt_city;
    private String txt_state;
    private String txt_country;
    private String txt_zipCode;
    private String txt_businessPhone;
    private String txt_emailAddress;
    private String txt_masterAccountingCode;
    private String txt_accountExpirationDate;
    private String txt_creditLimit;
    private String txt_employeeIdentificationNumber;
    private String hdn_crdtLmt;
    private String txt_programDescription;
    private String hdn_hl1;
    private String hdn_hl2;
    private String hdn_hl3;
    private String hdn_hl4;
    private String hdn_hl5;
    private String hdn_hl6;
    private String hdn_hl7;
    private String hdn_hl8;
    private String txt_hl1Descr;
    private String txt_hl2Descr;
    private String txt_hl3Descr;
    private String txt_hl4Descr;
    private String txt_hl5Descr;
    private String txt_hl6Descr;
    private String txt_hl7Descr;
    private String txt_hl8Descr;
    private String hdn_oldAcct;
    private BrowseHierarchyFields browseHierarchyFields;
    private BrowseHierarchyFields browseHierarchyFieldsReadOnly;
    private String txt_hl[] = new String[9];
    private String cmb_cardType;
    private String cmb_cardNoncard;
    private String cmb_generatePaperStatementFlag;
    private String cmb_grade;

//    private String txt_hl1;
//    private String txt_hl2;
//    private String txt_hl3;
//    private String txt_hl4;
//    private String txt_hl5;
//    private String txt_hl6;
//    private String txt_hl7;
//    private String txt_hl8;


    public int getCentralAcctID() {
        return centralAcctID;
    }

    public void setCentralAcctID(int centralAcctID) {
        this.centralAcctID = centralAcctID;
    }

    public String getCentralAccountName() {
        return centralAccountName;
    }

    public void setCentralAccountName(String centralAccountName) {
        this.centralAccountName = centralAccountName;
    }

    public String getCentralAccountNumber() {
        return centralAccountNumber;
    }

    public void setCentralAccountNumber(String centralAccountNumber) {
        this.centralAccountNumber = centralAccountNumber;
    }

    public String getHdn_programNumber() {
        return hdn_programNumber;
    }

    public void setHdn_programNumber(String hdn_programNumber) {
        this.hdn_programNumber = hdn_programNumber;
    }

    public int getHdn_centralAccountID() {
        return hdn_centralAccountID;
    }

    public void setHdn_centralAccountID(int hdn_centralAccountID) {
        this.hdn_centralAccountID = hdn_centralAccountID;
    }

    public String getHdn_centralAccountName() {
        return hdn_centralAccountName;
    }

    public void setHdn_centralAccountName(String hdn_centralAccountName) {
        this.hdn_centralAccountName = hdn_centralAccountName;
    }

    public String getHdn_programType() {
        return hdn_programType;
    }

    public void setHdn_programType(String hdn_programType) {
        this.hdn_programType = hdn_programType;
    }

    public String getHdn_centralAccountNumber() {
        return hdn_centralAccountNumber;
    }

    public void setHdn_centralAccountNumber(String hdn_centralAccountNumber) {
        this.hdn_centralAccountNumber = hdn_centralAccountNumber;
    }

    public String getHdn_travelersCheckFlag() {
        return hdn_travelersCheckFlag;
    }

    public void setHdn_travelersCheckFlag(String hdn_travelersCheckFlag) {
        this.hdn_travelersCheckFlag = hdn_travelersCheckFlag;
    }

    public String getHdn_billingType() {
        return hdn_billingType;
    }

    public void setHdn_billingType(String hdn_billingType) {
        this.hdn_billingType = hdn_billingType;
    }

    public String getAccountingCenterID() {
        return accountingCenterID;
    }

    public void setAccountingCenterID(String accountingCenterID) {
        this.accountingCenterID = accountingCenterID;
    }

    public String getTxt_lastName() {
        return txt_lastName;
    }

    public void setTxt_lastName(String txt_lastName) {
        this.txt_lastName = txt_lastName;
    }

    public String getTxt_firstName() {
        return txt_firstName;
    }

    public void setTxt_firstName(String txt_firstName) {
        this.txt_firstName = txt_firstName;
    }

    public String getTxt_socialSecurityNumber() {
        return txt_socialSecurityNumber;
    }

    public void setTxt_socialSecurityNumber(String txt_socialSecurityNumber) {
        this.txt_socialSecurityNumber = txt_socialSecurityNumber;
    }

    public String getTxt_addressLine1() {
        return txt_addressLine1;
    }

    public void setTxt_addressLine1(String txt_addressLine1) {
        this.txt_addressLine1 = txt_addressLine1;
    }

    public String getTxt_addressLine2() {
        return txt_addressLine2;
    }

    public void setTxt_addressLine2(String txt_addressLine2) {
        this.txt_addressLine2 = txt_addressLine2;
    }

    public String getTxt_addressLine3() {
        return txt_addressLine3;
    }

    public void setTxt_addressLine3(String txt_addressLine3) {
        this.txt_addressLine3 = txt_addressLine3;
    }

    public String getTxt_addressLine4() {
        return txt_addressLine4;
    }

    public void setTxt_addressLine4(String txt_addressLine4) {
        this.txt_addressLine4 = txt_addressLine4;
    }

    public String getTxt_city() {
        return txt_city;
    }

    public void setTxt_city(String txt_city) {
        this.txt_city = txt_city;
    }

    public String getTxt_state() {
        return txt_state;
    }

    public void setTxt_state(String txt_state) {
        this.txt_state = txt_state;
    }

    public String getTxt_country() {
        return txt_country;
    }

    public void setTxt_country(String txt_country) {
        this.txt_country = txt_country;
    }

    public String getTxt_zipCode() {
        return txt_zipCode;
    }

    public void setTxt_zipCode(String txt_zipCode) {
        this.txt_zipCode = txt_zipCode;
    }

    public String getTxt_businessPhone() {
        return txt_businessPhone;
    }

    public void setTxt_businessPhone(String txt_businessPhone) {
        this.txt_businessPhone = txt_businessPhone;
    }

    public String getTxt_emailAddress() {
        return txt_emailAddress;
    }

    public void setTxt_emailAddress(String txt_emailAddress) {
        this.txt_emailAddress = txt_emailAddress;
    }

    public String getTxt_masterAccountingCode() {
        return txt_masterAccountingCode;
    }

    public void setTxt_masterAccountingCode(String txt_masterAccountingCode) {
        this.txt_masterAccountingCode = txt_masterAccountingCode;
    }

    public String getTxt_accountExpirationDate() {
        return txt_accountExpirationDate;
    }

    public void setTxt_accountExpirationDate(String txt_accountExpirationDate) {
        this.txt_accountExpirationDate = txt_accountExpirationDate;
    }

    public String getTxt_creditLimit() {
        return txt_creditLimit;
    }

    public void setTxt_creditLimit(String txt_creditLimit) {
        this.txt_creditLimit = txt_creditLimit;
    }

    public String getTxt_employeeIdentificationNumber() {
        return txt_employeeIdentificationNumber;
    }

    public void setTxt_employeeIdentificationNumber(String txt_employeeIdentificationNumber) {
        this.txt_employeeIdentificationNumber = txt_employeeIdentificationNumber;
    }

    public String getHdn_crdtLmt() {
        return hdn_crdtLmt;
    }

    public void setHdn_crdtLmt(String hdn_crdtLmt) {
        this.hdn_crdtLmt = hdn_crdtLmt;
    }

    public String getTxt_programDescription() {
        return txt_programDescription;
    }

    public void setTxt_programDescription(String txt_programDescription) {
        this.txt_programDescription = txt_programDescription;
    }

    public String getHdn_hl1() {
        return hdn_hl1;
    }

    public void setHdn_hl1(String hdn_hl1) {
        this.hdn_hl1 = hdn_hl1;
    }

    public String getHdn_hl2() {
        return hdn_hl2;
    }

    public void setHdn_hl2(String hdn_hl2) {
        this.hdn_hl2 = hdn_hl2;
    }

    public String getHdn_hl3() {
        return hdn_hl3;
    }

    public void setHdn_hl3(String hdn_hl3) {
        this.hdn_hl3 = hdn_hl3;
    }

    public String getHdn_hl4() {
        return hdn_hl4;
    }

    public void setHdn_hl4(String hdn_hl4) {
        this.hdn_hl4 = hdn_hl4;
    }

    public String getHdn_hl5() {
        return hdn_hl5;
    }

    public void setHdn_hl5(String hdn_hl5) {
        this.hdn_hl5 = hdn_hl5;
    }

    public String getHdn_hl6() {
        return hdn_hl6;
    }

    public void setHdn_hl6(String hdn_hl6) {
        this.hdn_hl6 = hdn_hl6;
    }

    public String getHdn_hl7() {
        return hdn_hl7;
    }

    public void setHdn_hl7(String hdn_hl7) {
        this.hdn_hl7 = hdn_hl7;
    }

    public String getHdn_hl8() {
        return hdn_hl8;
    }

    public void setHdn_hl8(String hdn_hl8) {
        this.hdn_hl8 = hdn_hl8;
    }

    public String getTxt_hlDescr(int i) {
        String res = null;
        switch (i) {
            case 1:
                res = txt_hl1Descr;
            case 2:
                res = txt_hl2Descr;
            case 3:
                res = txt_hl3Descr;
            case 4:
                res = txt_hl4Descr;
            case 5:
                res = txt_hl5Descr;
            case 6:
                res = txt_hl6Descr;
            case 7:
                res = txt_hl7Descr;
            case 8:
                res = txt_hl8Descr;
        }
        return res;
    }

    public String getTxt_hl1Descr() {
        return txt_hl1Descr;
    }

    public void setTxt_hl1Descr(String txt_hl1Descr) {
        this.txt_hl1Descr = txt_hl1Descr;
    }

    public String getTxt_hl2Descr() {
        return txt_hl2Descr;
    }

    public void setTxt_hl2Descr(String txt_hl2Descr) {
        this.txt_hl2Descr = txt_hl2Descr;
    }

    public String getTxt_hl3Descr() {
        return txt_hl3Descr;
    }

    public void setTxt_hl3Descr(String txt_hl3Descr) {
        this.txt_hl3Descr = txt_hl3Descr;
    }

    public String getTxt_hl4Descr() {
        return txt_hl4Descr;
    }

    public void setTxt_hl4Descr(String txt_hl4Descr) {
        this.txt_hl4Descr = txt_hl4Descr;
    }

    public String getTxt_hl5Descr() {
        return txt_hl5Descr;
    }

    public void setTxt_hl5Descr(String txt_hl5Descr) {
        this.txt_hl5Descr = txt_hl5Descr;
    }

    public String getTxt_hl6Descr() {
        return txt_hl6Descr;
    }

    public void setTxt_hl6Descr(String txt_hl6Descr) {
        this.txt_hl6Descr = txt_hl6Descr;
    }

    public String getTxt_hl7Descr() {
        return txt_hl7Descr;
    }

    public void setTxt_hl7Descr(String txt_hl7Descr) {
        this.txt_hl7Descr = txt_hl7Descr;
    }

    public String getTxt_hl8Descr() {
        return txt_hl8Descr;
    }

    public void setTxt_hl8Descr(String txt_hl8Descr) {
        this.txt_hl8Descr = txt_hl8Descr;
    }

    public String getHdn_oldAcct() {
        return hdn_oldAcct;
    }

    public void setHdn_oldAcct(String hdn_oldAcct) {
        this.hdn_oldAcct = hdn_oldAcct;
    }

    public String getTxt_acctCenterID() {
        return txt_acctCenterID;
    }

    public void setTxt_acctCenterID(String txt_acctCenterID) {
        this.txt_acctCenterID = txt_acctCenterID;
    }

    public BrowseHierarchyFields getBrowseHierarchyFields() {
        return browseHierarchyFields;
    }

    public void setBrowseHierarchyFields(BrowseHierarchyFields browseHierarchyFields) {
        this.browseHierarchyFields = browseHierarchyFields;
    }

    public String getTxt_hl0() {
        return txt_hl[0];
    }

    public void setTxt_hl0(String txt_hl0) {
        this.txt_hl[0] = txt_hl0;
    }

    public String getTxt_hl1() {
        return txt_hl[1];
    }

    public void setTxt_hl1(String txt_hl1) {
        this.txt_hl[1] = txt_hl1;
    }

    public String getTxt_hl2() {
        return txt_hl[2];
    }

    public void setTxt_hl2(String txt_hl2) {
        this.txt_hl[2] = txt_hl2;
    }

    public String getTxt_hl3() {
        return txt_hl[3];
    }

    public void setTxt_hl3(String txt_hl3) {
        this.txt_hl[3] = txt_hl3;
    }

    public String getTxt_hl4() {
        return txt_hl[4];
    }

    public void setTxt_hl4(String txt_hl4) {
        this.txt_hl[4] = txt_hl4;
    }

    public String getTxt_hl5() {
        return txt_hl[5];
    }

    public void setTxt_hl5(String txt_hl5) {
        this.txt_hl[5] = txt_hl5;
    }

    public String getTxt_hl6() {
        return txt_hl[6];
    }

    public void setTxt_hl6(String txt_hl6) {
        this.txt_hl[6] = txt_hl6;
    }

    public String getTxt_hl7() {
        return txt_hl[7];
    }

    public void setTxt_hl7(String txt_hl7) {
        this.txt_hl[7] = txt_hl7;
    }

    public String getTxt_hl8() {
        return txt_hl[8];
    }

    public void setTxt_hl8(String txt_hl8) {
        this.txt_hl[8] = txt_hl8;
    }

    public String getTxt_hl(int i) {
        return this.txt_hl[i];
    }

    public String getCmb_cardType() {
        return cmb_cardType;
    }

    public void setCmb_cardType(String cmb_cardType) {
        this.cmb_cardType = cmb_cardType;
    }

    public String getCmb_cardNoncard() {
        return cmb_cardNoncard;
    }

    public void setCmb_cardNoncard(String cmb_cardNoncard) {
        this.cmb_cardNoncard = cmb_cardNoncard;
    }

    public String getCmb_generatePaperStatementFlag() {
        return cmb_generatePaperStatementFlag;
    }

    public void setCmb_generatePaperStatementFlag(String cmb_generatePaperStatementFlag) {
        this.cmb_generatePaperStatementFlag = cmb_generatePaperStatementFlag;
    }

    public String getCmb_grade() {
        return cmb_grade;
    }

    public void setCmb_grade(String cmb_grade) {
        this.cmb_grade = cmb_grade;
    }

    public BrowseHierarchyFields getBrowseHierarchyFieldsReadOnly() {
        return browseHierarchyFieldsReadOnly;
    }

    public void setBrowseHierarchyFieldsReadOnly(BrowseHierarchyFields browseHierarchyFieldsReadOnly) {
        this.browseHierarchyFieldsReadOnly = browseHierarchyFieldsReadOnly;
    }

    /*public int getTxtHl0() {
        return txtHl0;
    }

    public void setTxtHl0(int txtHl0) {
        this.txtHl0 = txtHl0;
    }

    public int getTxtHl1() {
        return txtHl1;
    }

    public void setTxtHl1(int txtHl1) {
        this.txtHl1 = txtHl1;
    }

    public int getTxtHl2() {
        return txtHl2;
    }

    public void setTxtHl2(int txtHl2) {
        this.txtHl2 = txtHl2;
    }

    public int getTxtHl3() {
        return txtHl3;
    }

    public void setTxtHl3(int txtHl3) {
        this.txtHl3 = txtHl3;
    }

    public int getTxtHl4() {
        return txtHl4;
    }

    public void setTxtHl4(int txtHl4) {
        this.txtHl4 = txtHl4;
    }

    public int getTxtHl5() {
        return txtHl5;
    }

    public void setTxtHl5(int txtHl5) {
        this.txtHl5 = txtHl5;
    }

    public int getTxtHl6() {
        return txtHl6;
    }

    public void setTxtHl6(int txtHl6) {
        this.txtHl6 = txtHl6;
    }

    public int getTxtHl7() {
        return txtHl7;
    }

    public void setTxtHl7(int txtHl7) {
        this.txtHl7 = txtHl7;
    }

    public int getTxtHl8() {
        return txtHl8;
    }

    public void setTxtHl8(int txtHl8) {
        this.txtHl8 = txtHl8;
    }

    public int getTxtAcctCenterID() {
        return txtAcctCenterID;
    }

    public void setTxtAcctCenterID(int txtAcctCenterID) {
        this.txtAcctCenterID = txtAcctCenterID;
    }

    public int getTxtMasterAccountingCode() {
        return txtMasterAccountingCode;
    }

    public void setTxtMasterAccountingCode(int txtMasterAccountingCode) {
        this.txtMasterAccountingCode = txtMasterAccountingCode;
    }

    public boolean isCmbGeneratePaperStatementFlag() {
        return cmbGeneratePaperStatementFlag;
    }

    public void setCmbGeneratePaperStatementFlag(boolean cmbGeneratePaperStatementFlag) {
        this.cmbGeneratePaperStatementFlag = cmbGeneratePaperStatementFlag;
    }

    public String getCmbCardType() {
        return cmbCardType;
    }

    public void setCmbCardType(String cmbCardType) {
        this.cmbCardType = cmbCardType;
    }

    public String getCmbCardNoncard() {
        return cmbCardNoncard;
    }

    public void setCmbCardNoncard(String cmbCardNoncard) {
        this.cmbCardNoncard = cmbCardNoncard;
    }

    public String getTxtAccountExpirationDate() {
        return txtAccountExpirationDate;
    }

    public void setTxtAccountExpirationDate(String txtAccountExpirationDate) {
        this.txtAccountExpirationDate = txtAccountExpirationDate;
    }

    public String getTxtCreditLimit() {
        return txtCreditLimit;
    }

    public void setTxtCreditLimit(String txtCreditLimit) {
        this.txtCreditLimit = txtCreditLimit;
    }

    public String getTxtLastName() {
        return txtLastName;
    }

    public void setTxtLastName(String txtLastName) {
        this.txtLastName = txtLastName;
    }

    public String getTxtFirstName() {
        return txtFirstName;
    }

    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName = txtFirstName;
    }

    public String getTxtAddressLine1() {
        return txtAddressLine1;
    }

    public void setTxtAddressLine1(String txtAddressLine1) {
        this.txtAddressLine1 = txtAddressLine1;
    }

    public String getTxtAddressLine2() {
        return txtAddressLine2;
    }

    public void setTxtAddressLine2(String txtAddressLine2) {
        this.txtAddressLine2 = txtAddressLine2;
    }

    public String getTxtAddressLine3() {
        return txtAddressLine3;
    }

    public void setTxtAddressLine3(String txtAddressLine3) {
        this.txtAddressLine3 = txtAddressLine3;
    }

    public String getTxtAddressLine4() {
        return txtAddressLine4;
    }

    public void setTxtAddressLine4(String txtAddressLine4) {
        this.txtAddressLine4 = txtAddressLine4;
    }

    public String getTxtCity() {
        return txtCity;
    }

    public void setTxtCity(String txtCity) {
        this.txtCity = txtCity;
    }

    public String getTxtState() {
        return txtState;
    }

    public void setTxtState(String txtState) {
        this.txtState = txtState;
    }

    public String getTxtCountry() {
        return txtCountry;
    }

    public void setTxtCountry(String txtCountry) {
        this.txtCountry = txtCountry;
    }

    public int getTxtZipCode() {
        return txtZipCode;
    }

    public void setTxtZipCode(int txtZipCode) {
        this.txtZipCode = txtZipCode;
    }

    public String getTxtBusinessPhone() {
        return txtBusinessPhone;
    }

    public void setTxtBusinessPhone(String txtBusinessPhone) {
        this.txtBusinessPhone = txtBusinessPhone;
    }

    public String getTxtEmailAddress() {
        return txtEmailAddress;
    }

    public void setTxtEmailAddress(String txtEmailAddress) {
        this.txtEmailAddress = txtEmailAddress;
    }

    public int getTxtSocialSecurityNumber() {
        return txtSocialSecurityNumber;
    }

    public void setTxtSocialSecurityNumber(int txtSocialSecurityNumber) {
        this.txtSocialSecurityNumber = txtSocialSecurityNumber;
    }

    public int getTxtEmployeeIdentificationNumber() {
        return txtEmployeeIdentificationNumber;
    }

    public void setTxtEmployeeIdentificationNumber(int txtEmployeeIdentificationNumber) {
        this.txtEmployeeIdentificationNumber = txtEmployeeIdentificationNumber;
    }

    public String getCmbGrade() {
        return cmbGrade;
    }

    public void setCmbGrade(String cmbGrade) {
        this.cmbGrade = cmbGrade;
    }

    public String getCmbStatus() {
        return cmbStatus;
    }

    public void setCmbStatus(String cmbStatus) {
        this.cmbStatus = cmbStatus;
    }

    public int getCentralAcctID() {
        return centralAcctID;
    }

    public void setCentralAcctID(int centralAcctID) {
        this.centralAcctID = centralAcctID;
    }

    public String getTxt_centralAccountName() {
        return centralAccountName;
    }

    public void setTxt_centralAccountName(String centralAccountName) {
        this.centralAccountName = centralAccountName;
    }

    public String getCentralAccountNumber() {
        return centralAccountNumber;
    }

    public void setCentralAccountNumber(String centralAccountNumber) {
        this.centralAccountNumber = centralAccountNumber;
    }*/

}
