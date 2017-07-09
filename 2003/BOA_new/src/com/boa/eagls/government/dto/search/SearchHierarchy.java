package com.boa.eagls.government.dto.search;

/**
 * Search Hierarchy value object
 * @author Oleg Klimenko
 * Date: 25.07.2003
 * Time: 11:39:09
 */
public class SearchHierarchy {
    private String chk_hierLevel;
    private String txt_hierarchyDepth;

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
}
