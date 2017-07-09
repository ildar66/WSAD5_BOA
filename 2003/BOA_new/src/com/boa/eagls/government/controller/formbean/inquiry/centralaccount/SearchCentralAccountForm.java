package com.boa.eagls.government.controller.formbean.inquiry.centralaccount;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionError;
import com.boa.eagls.government.service.centralaccount.SearchCentralAccountParam;
import com.boa.eagls.government.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: AlexanderZe
 * Date: 05.07.2003
 * Time: 16:26:50
 */
public class SearchCentralAccountForm extends ActionForm implements SearchCentralAccountParam  {
//    private int hierarhyAccess[] = null;
    public static final String INQUIRY_SEARCH_STR = "centralAccountInquiry";
    public static final String MAINTENANCE_SEARCH_STR = "centralAccountMaintenance";

    private boolean chk_hierLevel = false;
    private boolean chk_centralAccountName = false;
    private boolean chk_centralAccountNumber = false;
    private boolean chk_centralAccountID = false;
    private boolean chk_agencyName = false;
    private String searchType;
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

    public boolean isChk_hierLevel() {
        return chk_hierLevel;
    }

    public void setChk_hierLevel(boolean chk_hierLevel) {
        this.chk_hierLevel = chk_hierLevel;
    }
    public boolean isChk_AgencyName() {
        return chk_agencyName;
    }
    public boolean isChk_centralAccountName() {
        return chk_centralAccountName;
    }

    public void setChk_centralAccountName(boolean chk_centralAccountName) {
        this.chk_centralAccountName = chk_centralAccountName;
    }

    public boolean isChk_centralAccountNumber() {
        return chk_centralAccountNumber;
    }

    public void setChk_centralAccountNumber(boolean chk_centralAccountNumber) {
        this.chk_centralAccountNumber = chk_centralAccountNumber;
    }

    public boolean isChk_centralAccountID() {
        return chk_centralAccountID;
    }

    public void setChk_centralAccountID(boolean chk_centralAccountID) {
        this.chk_centralAccountID = chk_centralAccountID;
    }

    public String getTxt_hl0() {
        return txt_hl0;
    }

    public void setTxt_hl0(String txt_hl0) {
        this.txt_hl0 = txt_hl0;
    }

    public String getTxt_hl1() {
        return txt_hl1;
    }

    public void setTxt_hl1(String txt_hl1) {
        this.txt_hl1 = txt_hl1;
    }

    public String getTxt_hl2() {
        return txt_hl2;
    }

    public void setTxt_hl2(String txt_hl2) {
        this.txt_hl2 = txt_hl2;
    }

    public String getTxt_hl3() {
        return txt_hl3;
    }

    public void setTxt_hl3(String txt_hl3) {
        this.txt_hl3 = txt_hl3;
    }

    public String getTxt_hl4() {
        return txt_hl4;
    }

    public void setTxt_hl4(String txt_hl4) {
        this.txt_hl4 = txt_hl4;
    }

    public String getTxt_hl5() {
        return txt_hl5;
    }

    public void setTxt_hl5(String txt_hl5) {
        this.txt_hl5 = txt_hl5;
    }

    public String getTxt_hl6() {
        return txt_hl6;
    }

    public void setTxt_hl6(String txt_hl6) {
        this.txt_hl6 = txt_hl6;
    }

    public String getTxt_hl7() {
        return txt_hl7;
    }

    public void setTxt_hl7(String txt_hl7) {
        this.txt_hl7 = txt_hl7;
    }

    public String getTxt_hl8() {
        return txt_hl8;
    }

    public void setTxt_hl8(String txt_hl8) {
        this.txt_hl8 = txt_hl8;
    }

    public String getTxt_hl0Desc() {
        return txt_hl0Desc;
    }

    public void setTxt_hl0Desc(String txt_hl0Desc) {
        this.txt_hl0Desc = txt_hl0Desc;
    }

    public String getTxt_hl1Desc() {
        return txt_hl1Desc;
    }

    public void setTxt_hl1Desc(String txt_hl1Desc) {
        this.txt_hl1Desc = txt_hl1Desc;
    }

    public String getTxt_hl2Desc() {
        return txt_hl2Desc;
    }

    public void setTxt_hl2Desc(String txt_hl2Desc) {
        this.txt_hl2Desc = txt_hl2Desc;
    }

    public String getTxt_hl3Desc() {
        return txt_hl3Desc;
    }

    public void setTxt_hl3Desc(String txt_hl3Desc) {
        this.txt_hl3Desc = txt_hl3Desc;
    }

    public String getTxt_hl4Desc() {
        return txt_hl4Desc;
    }

    public void setTxt_hl4Desc(String txt_hl4Desc) {
        this.txt_hl4Desc = txt_hl4Desc;
    }

    public String getTxt_hl5Desc() {
        return txt_hl5Desc;
    }

    public void setTxt_hl5Desc(String txt_hl5Desc) {
        this.txt_hl5Desc = txt_hl5Desc;
    }

    public String getTxt_hl6Desc() {
        return txt_hl6Desc;
    }

    public void setTxt_hl6Desc(String txt_hl6Desc) {
        this.txt_hl6Desc = txt_hl6Desc;
    }

    public String getTxt_hl7Desc() {
        return txt_hl7Desc;
    }

    public void setTxt_hl7Desc(String txt_hl7Desc) {
        this.txt_hl7Desc = txt_hl7Desc;
    }

    public String getTxt_hl8Desc() {
        return txt_hl8Desc;
    }

    public void setTxt_hl8Desc(String txt_hl8Desc) {
        this.txt_hl8Desc = txt_hl8Desc;
    }

    private String txt_centralAccountID;
    private String txt_centralAccountNumber;
    private String txt_centralAccountName;
    private String txt_agencyName;
    private String txt_hierarchyDepth;


    /**
    * Constructor
    */
    public SearchCentralAccountForm() {
        super();
    }


    public String getTxt_centralAccountID() {
        return txt_centralAccountID;
    }

    public void setTxt_centralAccountID(String txt_centralAccountID) {
        this.txt_centralAccountID = txt_centralAccountID;
    }

    public String getTxt_centralAccountNumber() {
        return txt_centralAccountNumber;
    }

    public void setTxt_centralAccountNumber(String txt_centralAccountNumber) {
        this.txt_centralAccountNumber = txt_centralAccountNumber;
    }


    public String getTxt_centralAccountName() {
        return txt_centralAccountName;

    }

    public void setTxt_centralAccountName(String txt_centralAccountName) {
        this.txt_centralAccountName = txt_centralAccountName;
    }

    public String getTxt_hierarchyDepth() {
        return txt_hierarchyDepth;
    }

    public void setTxt_hierarchyDepth(String txt_hierarchyDepth) {
        this.txt_hierarchyDepth = txt_hierarchyDepth;
    }

    public String getTxt_agencyName() {
        return txt_agencyName;
    }

    public void setTxt_agencyName(String txt_agencyName) {
        this.txt_agencyName = txt_agencyName;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public boolean isChk_agencyName() {
        return chk_agencyName;
    }

    public void setChk_agencyName(boolean chk_agencyName) {
        this.chk_agencyName = chk_agencyName;
    }

}
