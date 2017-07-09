package com.boa.eagls.government.dto.search;

/**
 * Value Object for Search by Social Security Number
 * @author OlegK
 * Date: 25.07.2003
 * Time: 11:38:12
 */
public class SearchSocialSecurity {
    private String chk_SSN;
    private String txt_SS1;
    private String txt_SS2;
    private String txt_SS3;

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

}
