package com.boa.eagls.government.controller.formbean.setup;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.service.MCVehicleBaseParam;

/**
 * <p><small> DVI Company, 29.07.2003 17:39:09</small></p>
 * Base form for vehicle setup
 * @author GlebL
 */
public class MCVehicleBaseForm extends ActionForm
        implements MCVehicleBaseParam {
    private String txt_accountingCenterId;
    private String Hdn_centralName;
    private String txt_hl0;
    private String txt_hl1;
    private String txt_hl2;
    private String txt_hl3;
    private String txt_hl4;
    private String txt_hl5;
    private String txt_hl6;
    private String txt_hl7;
    private String txt_hl8;
    private String txt_hl0Desc;
    private String txt_hl1Desc;
    private String txt_hl2Desc;
    private String txt_hl3Desc;
    private String txt_hl4Desc;
    private String txt_hl5Desc;
    private String txt_hl6Desc;
    private String txt_hl7Desc;
    private String txt_hl8Desc;
    private String programDescription;
    private String cmb_equipFuelHigh;
    private String cmb_equipFuelLow;
    private String txt_addressLine1;
    private String txt_addressLine2;
    private String txt_addressLine3;
    private String txt_addressLine4;
    private String txt_city;
    private String txt_state;
    private String txt_country;
    private String txt_zip;
    private String txt_creditLimit;
    private String txt_accountExpireDate;
    private String txt_accountingCode;
    private String hdn_programType;
    private String hdn_billingType;
    private String cmb_cardType;
    private String hdn_card;
    private String rag_generatePaper;
    private String hdn_centralNumber;
    private String hdn_centralId;
    private String txt_equipmentType;
    private String txt_equipmentId;
    private String hdn_agencyName;
    private String hdn_convenienceChecks;

    public String getHdn_convenienceChecks() {
        return hdn_convenienceChecks;
    }

    public void setHdn_convenienceChecks(String hdn_convenienceChecks) {
        this.hdn_convenienceChecks = hdn_convenienceChecks;
    }

    public String getHdn_agencyName() {
        return hdn_agencyName;
    }

    public void setHdn_agencyName(String hdn_agencyName) {
        this.hdn_agencyName = hdn_agencyName;
    }

    public String getHdn_centralNumber() {
        return hdn_centralNumber;
    }

    public void setHdn_centralNumber(String hdn_centralNumber) {
        this.hdn_centralNumber = hdn_centralNumber;
    }

    public String getHdn_centralId() {
        return hdn_centralId;
    }

    public void setHdn_centralId(String hdn_centralId) {
        this.hdn_centralId = hdn_centralId;
    }

    public String getTxt_equipmentType() {
        return txt_equipmentType;
    }

    public void setTxt_equipmentType(String txt_equipmentType) {
        this.txt_equipmentType = txt_equipmentType;
    }

    public String getTxt_equipmentId() {
        return txt_equipmentId;
    }

    public void setTxt_equipmentId(String txt_equipmentId) {
        this.txt_equipmentId = txt_equipmentId;
    }

    public String getCmb_equipFuelLow() {
        return cmb_equipFuelLow;
    }

    public void setCmb_equipFuelLow(String cmb_equipFuelLow) {
        this.cmb_equipFuelLow = cmb_equipFuelLow;
    }

    public String getHdn_centralName() {
        return Hdn_centralName;
    }

    public void setHdn_centralName(String hdn_centralName) {
        Hdn_centralName = hdn_centralName;
    }

    public void setTxt_accountingCenterId(String txt_accountingCenterId) {
        this.txt_accountingCenterId = txt_accountingCenterId;
    }

    public void setTxt_hl0(String txt_hl0) {
        this.txt_hl0 = txt_hl0;
    }

    public void setTxt_hl1(String txt_hl1) {
        this.txt_hl1 = txt_hl1;
    }

    public void setTxt_hl2(String txt_hl2) {
        this.txt_hl2 = txt_hl2;
    }

    public void setTxt_hl3(String txt_hl3) {
        this.txt_hl3 = txt_hl3;
    }

    public void setTxt_hl4(String txt_hl4) {
        this.txt_hl4 = txt_hl4;
    }

    public void setTxt_hl5(String txt_hl5) {
        this.txt_hl5 = txt_hl5;
    }

    public void setTxt_hl6(String txt_hl6) {
        this.txt_hl6 = txt_hl6;
    }

    public void setTxt_hl7(String txt_hl7) {
        this.txt_hl7 = txt_hl7;
    }

    public void setTxt_hl8(String txt_hl8) {
        this.txt_hl8 = txt_hl8;
    }

    public void setTxt_hl0Desc(String txt_hl0Desc) {
        this.txt_hl0Desc = txt_hl0Desc;
    }

    public void setTxt_hl1Desc(String txt_hl1Desc) {
        this.txt_hl1Desc = txt_hl1Desc;
    }

    public void setTxt_hl2Desc(String txt_hl2Desc) {
        this.txt_hl2Desc = txt_hl2Desc;
    }

    public void setTxt_hl3Desc(String txt_hl3Desc) {
        this.txt_hl3Desc = txt_hl3Desc;
    }

    public void setTxt_hl4Desc(String txt_hl4Desc) {
        this.txt_hl4Desc = txt_hl4Desc;
    }

    public void setTxt_hl5Desc(String txt_hl5Desc) {
        this.txt_hl5Desc = txt_hl5Desc;
    }

    public void setTxt_hl6Desc(String txt_hl6Desc) {
        this.txt_hl6Desc = txt_hl6Desc;
    }

    public void setTxt_hl7Desc(String txt_hl7Desc) {
        this.txt_hl7Desc = txt_hl7Desc;
    }

    public void setTxt_hl8Desc(String txt_hl8Desc) {
        this.txt_hl8Desc = txt_hl8Desc;
    }

    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    public void setCmb_equipFuelHigh(String cmb_equipFuelHigh) {
        this.cmb_equipFuelHigh = cmb_equipFuelHigh;
    }

    public void setTxt_addressLine1(String txt_addressLine1) {
        this.txt_addressLine1 = txt_addressLine1;
    }

    public void setTxt_addressLine2(String txt_addressLine2) {
        this.txt_addressLine2 = txt_addressLine2;
    }

    public void setTxt_addressLine3(String txt_addressLine3) {
        this.txt_addressLine3 = txt_addressLine3;
    }

    public void setTxt_addressLine4(String txt_addressLine4) {
        this.txt_addressLine4 = txt_addressLine4;
    }

    public void setTxt_city(String txt_city) {
        this.txt_city = txt_city;
    }

    public void setTxt_state(String txt_state) {
        this.txt_state = txt_state;
    }

    public void setTxt_country(String txt_country) {
        this.txt_country = txt_country;
    }

    public void setTxt_zip(String txt_zip) {
        this.txt_zip = txt_zip;
    }

    public void setTxt_creditLimit(String txt_creditLimit) {
        this.txt_creditLimit = txt_creditLimit;
    }

    public void setTxt_accountExpireDate(String txt_accountExpireDate) {
        this.txt_accountExpireDate = txt_accountExpireDate;
    }

    public void setTxt_accountingCode(String txt_accountingCode) {
        this.txt_accountingCode = txt_accountingCode;
    }

    public void setHdn_programType(String hdn_programType) {
        this.hdn_programType = hdn_programType;
    }

    public void setHdn_billingType(String hdn_billingType) {
        this.hdn_billingType = hdn_billingType;
    }

    public void setCmb_cardType(String cmb_cardType) {
        this.cmb_cardType = cmb_cardType;
    }

    public void setHdn_card(String hdn_card) {
        this.hdn_card = hdn_card;
    }

    public void setRag_generatePaper(String rag_generatePaper) {
        this.rag_generatePaper = rag_generatePaper;
    }

    public String getTxt_accountingCenterId() {
        return txt_accountingCenterId;
    }

    public String getTxt_hl0() {
        return txt_hl0;
    }

    public String getTxt_hl1() {
        return txt_hl1;
    }

    public String getTxt_hl2() {
        return txt_hl2;
    }

    public String getTxt_hl3() {
        return txt_hl3;
    }

    public String getTxt_hl4() {
        return txt_hl4;
    }

    public String getTxt_hl5() {
        return txt_hl5;
    }

    public String getTxt_hl6() {
        return txt_hl6;
    }

    public String getTxt_hl7() {
        return txt_hl7;
    }

    public String getTxt_hl8() {
        return txt_hl8;
    }

    public String getTxt_hl0Desc() {
        return txt_hl0Desc;
    }

    public String getTxt_hl1Desc() {
        return txt_hl1Desc;
    }

    public String getTxt_hl2Desc() {
        return txt_hl2Desc;
    }

    public String getTxt_hl3Desc() {
        return txt_hl3Desc;
    }

    public String getTxt_hl4Desc() {
        return txt_hl4Desc;
    }

    public String getTxt_hl5Desc() {
        return txt_hl5Desc;
    }

    public String getTxt_hl6Desc() {
        return txt_hl6Desc;
    }

    public String getTxt_hl7Desc() {
        return txt_hl7Desc;
    }

    public String getTxt_hl8Desc() {
        return txt_hl8Desc;
    }

    public String getProgramDescription() {
        return programDescription;
    }

    public String getCmb_equipFuelHigh() {
        return cmb_equipFuelHigh;
    }

    public String getTxt_addressLine1() {
        return txt_addressLine1;
    }

    public String getTxt_addressLine2() {
        return txt_addressLine2;
    }

    public String getTxt_addressLine3() {
        return txt_addressLine3;
    }

    public String getTxt_addressLine4() {
        return txt_addressLine4;
    }

    public String getTxt_city() {
        return txt_city;
    }

    public String getTxt_state() {
        return txt_state;
    }

    public String getTxt_country() {
        return txt_country;
    }

    public String getTxt_zip() {
        return txt_zip;
    }

    public String getTxt_creditLimit() {
        return txt_creditLimit;
    }

    public String getTxt_accountExpireDate() {
        return txt_accountExpireDate;
    }

    public String getTxt_accountingCode() {
        return txt_accountingCode;
    }

    public String getHdn_programType() {
        return hdn_programType;
    }

    public String getHdn_billingType() {
        return hdn_billingType;
    }

    public String getCmb_cardType() {
        return cmb_cardType;
    }

    public String getHdn_card() {
        return hdn_card;
    }

    public String getRag_generatePaper() {
        return rag_generatePaper;
    }
}