package com.boa.eagls.government.dto.hierarchy;

import org.apache.log4j.Logger;
import com.boa.eagls.government.util.HierarchyDisplay;

import java.io.Serializable;

/**
 * Valued Object for Hierarchy grid
 * <p><small> VDI Company, 15.07.2003 19:36:23</small></p>
 * @author Oleg Klimenko
 */
public class BrowseHierarchyFields implements Serializable {

    private String[] EditHlNumber = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
    private String[] YesEditHlNumber = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
    private String[] HlNumber = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
    private String[] EditHlDescription = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
    private String[] HlDescription = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
    private boolean GCSU= true;



//    public boolean isGSCU() {
    /*EDIT_HL0_NUMBER
    HL0_NUMBER
    YESEDIT_HL0_NUMBER
    EDIT_HL0_DESCRIPTION
    HL0_DESCRIPTION
    EDIT_HL1_NUMBER
    HL1_NUMBER
    YESEDIT_HL1_NUMBER
    HL1_NUMBER
    EDIT_HL1_DESCRIPTION
    HL1_DESCRIPTION
    EDIT_HL2_NUMBER
    HL2_NUMBER
    YESEDIT_HL2_NUMBER
    HL2_NUMBER
    EDIT_HL2_DESCRIPTION
    HL2_DESCRIPTION
    EDIT_HL3_NUMBER
    HL3_NUMBER
    YESEDIT_HL3_NUMBER
    HL3_NUMBER
    EDIT_HL3_DESCRIPTION
    HL3_DESCRIPTION
    EDIT_HL4_NUMBER
    HL4_NUMBER
    YESEDIT_HL4_NUMBER
    HL4_NUMBER
    EDIT_HL4_DESCRIPTION*/



    public String getEDIT_HL_NUMBER(int i) {
        return EditHlNumber[i];
    }

//    public String[] getEDIT_HL_NUMBER() {
//        return EditHlNumber;
//    }

    public String getYESEDIT_HL_NUMBER(int i) {
        return YesEditHlNumber[i];
    }

    public String getHL_NUMBER(int i) {
        return HlNumber[i];
    }

    public String getEDIT_HL_DESCRIPTION(int i) {
        return EditHlDescription[i];
    }

    public String getHL_DESCRIPTION(int i) {
        return HlDescription[i];
    }

    public String[] getEditHlNumber() {
        return EditHlNumber;
    }

    public void setEditHlNumber(String[] editHlNumber) {
        EditHlNumber = editHlNumber;
    }

    public String[] getYesEditHlNumber() {
        return YesEditHlNumber;
    }

    public void setYesEditHlNumber(String[] yesEditHlNumber) {
        YesEditHlNumber = yesEditHlNumber;
    }

    public String[] getHlNumber() {
        return HlNumber;
    }

    public void setHlNumber(String[] hlNumber) {
        HlNumber = hlNumber;
    }

    public String[] getEditHlDescription() {
        return EditHlDescription;
    }

    public void setEditHlDescription(String[] editHlDescription) {
        EditHlDescription = editHlDescription;
    }

    public String[] getHlDescription() {
        return HlDescription;
    }

    public void setHlDescription(String[] hlDescription) {
        HlDescription = hlDescription;
    }

    public boolean isGCSU() {
        return GCSU;
    }

    public void setGCSU(boolean GCSU) {
        this.GCSU = GCSU;
    }

}
