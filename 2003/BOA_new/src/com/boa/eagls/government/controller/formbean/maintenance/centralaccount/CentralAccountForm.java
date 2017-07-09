package com.boa.eagls.government.controller.formbean.maintenance.centralaccount;

import com.boa.eagls.government.dto.hierarchy.BrowseHierarchyFields;
import org.apache.struts.action.ActionForm;

/**
 * <p><small> VDI Company, 21.07.2003 15:30:00</small></p>
 * @author AlexanderZe
 */
public class CentralAccountForm extends ActionForm {
    public static final String FORM_BEAN = "frm_centralAccountMaintenanceForm";

    private String centralAccountNumber;
    private String txt_centralAccountName;
    private String agencyName;
    private String centralAccountID;
    private String txt_masterAccountingCode;
    private String txt_accountingCenterID;
    private String accountType;
    private String billingType;
    private String fleetType;
    private String txt_accountExpirationDate;
    private String cmb_travelerChecks = "No";
    private String cmb_atmAccess = "Yes";
    private String cmb_decrementCard = "Yes";
    private String cmb_citypairProgram = "Yes";
    private String creditLimit;
    private String programType;
    private String integratedProgram;
    private String integratedType;
    private String showPlainText;
    private boolean selectTravelCheckBox;
    private boolean selectFleetCheckBox;
    private boolean selectPurchaseCheckBox;
    private boolean selectInteragencyCheckBox;
    private String showCheckBoxes;
    private String cmb_convenienceChecks = "No";
    private boolean chk_checkProgram1;
    private boolean chk_checkProgram2;
    private boolean chk_checkProgram3;
    private String cardPrograms;
    private String numberConvenienceChecks;
    private String invoiceMedium;
    private BrowseHierarchyFields browseHierarchyFields;


    public String getCardPrograms() {
        return cardPrograms;
    }

    public void setCardPrograms(String cardPrograms) {
        this.cardPrograms = cardPrograms;
    }

    public String getNumberConvenienceChecks() {
        return numberConvenienceChecks;
    }

    public void setNumberConvenienceChecks(String numberConvenienceChecks) {
        this.numberConvenienceChecks = numberConvenienceChecks;
    }

    public String getInvoiceMedium() {
        return invoiceMedium;
    }

    public void setInvoiceMedium(String invoiceMedium) {
        this.invoiceMedium = invoiceMedium;
    }

    public BrowseHierarchyFields getBrowseHierarchyFields() {
        return browseHierarchyFields;
    }

    public void setBrowseHierarchyFields(BrowseHierarchyFields browseHierarchyFields) {
        this.browseHierarchyFields = browseHierarchyFields;
    }

    public boolean isSelectTravelCheckBox() {
        return selectTravelCheckBox;
    }

    public void setSelectTravelCheckBox(boolean selectTravelCheckBox) {
        this.selectTravelCheckBox = selectTravelCheckBox;
    }

    public boolean isSelectFleetCheckBox() {
        return selectFleetCheckBox;
    }

    public void setSelectFleetCheckBox(boolean selectFleetCheckBox) {
        this.selectFleetCheckBox = selectFleetCheckBox;
    }

    public boolean isSelectPurchaseCheckBox() {
        return selectPurchaseCheckBox;
    }

    public void setSelectPurchaseCheckBox(boolean selectPurchaseCheckBox) {
        this.selectPurchaseCheckBox = selectPurchaseCheckBox;
    }

    public boolean isSelectInteragencyCheckBox() {
        return selectInteragencyCheckBox;
    }

    public void setSelectInteragencyCheckBox(boolean selectInteragencyCheckBox) {
        this.selectInteragencyCheckBox = selectInteragencyCheckBox;
    }

    public String getShowCheckBoxes() {
        return showCheckBoxes;
    }

    public void setShowCheckBoxes(String showCheckBoxes) {
        this.showCheckBoxes = showCheckBoxes;
    }


    public boolean isElectronicYes() {
        return electronicYes;
    }

    public void setElectronicYes(boolean electronicYes) {
        this.electronicYes = electronicYes;
    }

    public boolean isEDIYes() {
        return EDIYes;
    }

    public void setEDIYes(boolean EDIYes) {
        this.EDIYes = EDIYes;
    }

    public boolean isPaperYes() {
        return paperYes;
    }

    public void setPaperYes(boolean paperYes) {
        this.paperYes = paperYes;
    }

    public String getCmb_convenienceChecks() {
        return cmb_convenienceChecks;
    }

    public void setCmb_convenienceChecks(String cmb_convenienceChecks) {
        this.cmb_convenienceChecks = cmb_convenienceChecks;
    }

    public boolean isChk_checkProgram1() {
        return chk_checkProgram1;
    }

    public void setChk_checkProgram1(boolean chk_checkProgram1) {
        this.chk_checkProgram1 = chk_checkProgram1;
    }

    public boolean isChk_checkProgram2() {
        return chk_checkProgram2;
    }

    public void setChk_checkProgram2(boolean chk_checkProgram2) {
        this.chk_checkProgram2 = chk_checkProgram2;
    }

    public boolean isChk_checkProgram3() {
        return chk_checkProgram3;
    }

    public void setChk_checkProgram3(boolean chk_checkProgram3) {
        this.chk_checkProgram3 = chk_checkProgram3;
    }

    private boolean electronicYes;
    private boolean EDIYes;
    private boolean paperYes;


    public String getIntegratedProgram() {
        return integratedProgram;
    }

    public void setIntegratedProgram(String integratedProgram) {
        this.integratedProgram = integratedProgram;
    }

    public String getCentralAccountNumber() {
        return centralAccountNumber;
    }

    public void setCentralAccountNumber(String centralAccountNumber) {
        this.centralAccountNumber = centralAccountNumber;
    }

    public String getTxt_centralAccountName() {
        return txt_centralAccountName;
    }

    public void setTxt_centralAccountName(String txt_centralAccountName) {
        this.txt_centralAccountName = txt_centralAccountName;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getCentralAccountID() {
        return centralAccountID;
    }

    public void setCentralAccountID(String centralAccountID) {
        this.centralAccountID = centralAccountID;
    }

    public String getTxt_masterAccountingCode() {
        return txt_masterAccountingCode;
    }

    public void setTxt_masterAccountingCode(String txt_masterAccountingCode) {
        this.txt_masterAccountingCode = txt_masterAccountingCode;
    }

    public String getTxt_accountingCenterID() {
        return txt_accountingCenterID;
    }

    public void setTxt_accountingCenterID(String txt_accountingCenterID) {
        this.txt_accountingCenterID = txt_accountingCenterID;
    }

    public String getCmb_travelerChecks() {
        return cmb_travelerChecks;
    }

    public void setCmb_travelerChecks(String cmb_travelerChecks) {
        this.cmb_travelerChecks = cmb_travelerChecks;
    }

    public String getCmb_decrementCard() {
        return cmb_decrementCard;
    }

    public void setCmb_decrementCard(String cmb_decrementCard) {
        this.cmb_decrementCard = cmb_decrementCard;
    }

    public String getCmb_citypairProgram() {
        return cmb_citypairProgram;
    }

    public void setCmb_citypairProgram(String cmb_citypairProgram) {
        this.cmb_citypairProgram = cmb_citypairProgram;
    }

    public String getCmb_atmAccess() {
        return cmb_atmAccess;
    }

    public void setCmb_atmAccess(String cmb_atmAccess) {
        this.cmb_atmAccess = cmb_atmAccess;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public String getFleetType() {
        return fleetType;
    }

    public void setFleetType(String fleetType) {
        this.fleetType = fleetType;
    }

    public String getTxt_accountExpirationDate() {
        return txt_accountExpirationDate;
    }

    public void setTxt_accountExpirationDate(String txt_accountExpirationDate) {
        this.txt_accountExpirationDate = txt_accountExpirationDate;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getIntegratedType() {
        return integratedType;
    }

    public void setIntegratedType(String integratedType) {
        this.integratedType = integratedType;
    }

    public String getShowPlainText() {
        return showPlainText;
    }

    public void setShowPlainText(String showPlainText) {
        this.showPlainText = showPlainText;
    }


}