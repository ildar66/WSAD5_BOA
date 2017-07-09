package com.boa.eagls.government.dto.search;

import org.apache.struts.action.ActionForm;

/**
 * Value Object (and Struts Form Bean) used by Search For Individual Account use case
 * @author OlegK
 * Date: 25.07.2003
 * Time: 10:49:55
 */
public class SearchDTO extends ActionForm {

    private String rag_acctMaintSearchFor = "O";

    private String chk_accountNumber;
    private String txt_accountNumber;

    private String chk_SSN;
    private String txt_SS1;
    private String txt_SS2;
    private String txt_SS3;

    private String chk_hierLevel;
    private String txt_hierarchyDepth;

    private String chk_busPhone;
    private String txt_businessPhone1;
    private String txt_businessPhone2;
    private String txt_businessPhone3;
    private String txt_businessPhoneIntl;

    private String chk_Name;
    private String txt_lastName;
    private String txt_firstName;
    private String txt_zipCode;

    private String txt_programNumber;
    private String txt_hl[] = new String [9];
    private String txt_businessPhone;
    private String txt_CountryCode;
    private String txt_socialSecurityNumber;

//    private AccountMaintSearchFor accountMaintSearchFor= new AccountMaintSearchFor();
//    private SearchName searchName= new SearchName();
//    private SearchAccountNumber searchAccountNumber = new SearchAccountNumber();
//    private SearchHierarchy searchHierarchy = new SearchHierarchy();
//    private SearchSocialSecurity searchSocialSecurity = new SearchSocialSecurity();
//    private SearchBusPhone searchBusPhone = new SearchBusPhone();

    public SearchDTO() {
//        accountMaintSearchFor = new AccountMaintSearchFor();
//        searchName = new
    }

    public void setTxt_hl0(String val) {
        txt_hl[0] = val;
    }

    public void setTxt_hl1(String val) {
        txt_hl[1] = val;
    }

    public void setTxt_hl2(String val) {
        txt_hl[2] = val;
    }

    public void setTxt_hl3(String val) {
        txt_hl[3] = val;
    }

    public void setTxt_hl4(String val) {
        txt_hl[4] = val;
    }

    public void setTxt_hl5(String val) {
        txt_hl[5] = val;
    }

    public void setTxt_hl6(String val) {
        txt_hl[6] = val;
    }

    public void setTxt_hl7(String val) {
        txt_hl[7] = val;
    }

    public void setTxt_hl8(String val) {
        txt_hl[8] = val;
    }

    public String getTxt_hl(int i) {
        return txt_hl[i];
    }

    public String getRag_acctMaintSearchFor() {
        return rag_acctMaintSearchFor;
    }

    public void setRag_acctMaintSearchFor(String rag_acctMaintSearchFor) {
        this.rag_acctMaintSearchFor = rag_acctMaintSearchFor;
    }

    public String getChk_accountNumber() {
        return chk_accountNumber;
    }

    public void setChk_accountNumber(String chk_accountNumber) {
        this.chk_accountNumber = chk_accountNumber;
    }

    public String getTxt_accountNumber() {
        return txt_accountNumber;
    }

    public void setTxt_accountNumber(String txt_accountNumber) {
        this.txt_accountNumber = txt_accountNumber;
    }

    public String getChk_SSN() {
        return chk_SSN;
    }

    public void setChk_SSN(String chk_SSN) {
        this.chk_SSN = chk_SSN;
    }

    public String getTxt_SS1() {
        return txt_SS1;
    }

    public void setTxt_SS1(String txt_SS1) {
        this.txt_SS1 = txt_SS1;
    }

    public String getTxt_SS2() {
        return txt_SS2;
    }

    public void setTxt_SS2(String txt_SS2) {
        this.txt_SS2 = txt_SS2;
    }

    public String getTxt_SS3() {
        return txt_SS3;
    }

    public void setTxt_SS3(String txt_SS3) {
        this.txt_SS3 = txt_SS3;
    }

    public String getChk_hierLevel() {
        return chk_hierLevel;
    }

    public void setChk_hierLevel(String chk_hierLevel) {
        this.chk_hierLevel = chk_hierLevel;
    }

    public String getTxt_hierarchyDepth() {
        return txt_hierarchyDepth;
    }

    public void setTxt_hierarchyDepth(String txt_hierarchyDepth) {
        this.txt_hierarchyDepth = txt_hierarchyDepth;
    }

    public String getChk_busPhone() {
        return chk_busPhone;
    }

    public void setChk_busPhone(String chk_busPhone) {
        this.chk_busPhone = chk_busPhone;
    }

    public String getTxt_businessPhone1() {
        return txt_businessPhone1;
    }

    public void setTxt_businessPhone1(String txt_businessPhone1) {
        this.txt_businessPhone1 = txt_businessPhone1;
    }

    public String getTxt_businessPhone2() {
        return txt_businessPhone2;
    }

    public void setTxt_businessPhone2(String txt_businessPhone2) {
        this.txt_businessPhone2 = txt_businessPhone2;
    }

    public String getTxt_businessPhone3() {
        return txt_businessPhone3;
    }

    public void setTxt_businessPhone3(String txt_businessPhone3) {
        this.txt_businessPhone3 = txt_businessPhone3;
    }

    public String getTxt_businessPhoneIntl() {
        return txt_businessPhoneIntl;
    }

    public void setTxt_businessPhoneIntl(String txt_businessPhoneIntl) {
        this.txt_businessPhoneIntl = txt_businessPhoneIntl;
    }

    public String getChk_Name() {
        return chk_Name;
    }

    public void setChk_Name(String chk_Name) {
        this.chk_Name = chk_Name;
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

    public String getTxt_zipCode() {
        return txt_zipCode;
    }

    public void setTxt_zipCode(String txt_zipCode) {
        this.txt_zipCode = txt_zipCode;
    }

    public String getTxt_programNumber() {
        return txt_programNumber;
    }

    public void setTxt_programNumber(String txt_programNumber) {
        this.txt_programNumber = txt_programNumber;
    }

    public String getTxt_businessPhone() {
        return txt_businessPhone;
    }

    public void setTxt_businessPhone(String txt_businessPhone) {
        this.txt_businessPhone = txt_businessPhone;
    }

    public String getTxt_CountryCode() {
        return txt_CountryCode;
    }

    public void setTxt_CountryCode(String txt_CountryCode) {
        this.txt_CountryCode = txt_CountryCode;
    }

    public String getTxt_socialSecurityNumber() {
        return txt_socialSecurityNumber;
    }

    public void setTxt_socialSecurityNumber(String txt_socialSecurityNumber) {
        this.txt_socialSecurityNumber = txt_socialSecurityNumber;
    }



    /*
    public AccountMaintSearchFor getAccountMaintSearchFor() {
        return accountMaintSearchFor;
    }

    public void setAccountMaintSearchFor(AccountMaintSearchFor accountMaintSearchFor) {
        this.accountMaintSearchFor = accountMaintSearchFor;
    }

    public SearchName getSearchName() {
        return searchName;
    }

    public void setSearchName(SearchName searchName) {
        this.searchName = searchName;
    }

    public SearchAccountNumber getSearchAccountNumber() {
        return searchAccountNumber;
    }

    public void setSearchAccountNumber(SearchAccountNumber searchAccountNumber) {
        this.searchAccountNumber = searchAccountNumber;
    }

    public SearchHierarchy getSearchHierarchy() {
        return searchHierarchy;
    }

    public void setSearchHierarchy(SearchHierarchy searchHierarchy) {
        this.searchHierarchy = searchHierarchy;
    }

    public SearchSocialSecurity getSearchSocialSecurity() {
        return searchSocialSecurity;
    }

    public void setSearchSocialSecurity(SearchSocialSecurity searchSocialSecurity) {
        this.searchSocialSecurity = searchSocialSecurity;
    }

    public SearchBusPhone getSearchBusPhone() {
        return searchBusPhone;
    }

    public void setSearchBusPhone(SearchBusPhone searchBusPhone) {
        this.searchBusPhone = searchBusPhone;
    }*/

}
