package com.boa.eagls.government.dto.browse;

import org.apache.log4j.Logger;

import java.util.Collection;

import com.boa.eagls.government.util.pagedList.ValueListIterator;

/**
 * <p><small> DVI Company, 24.07.2003 19:31:42</small></p>
 * @author GlebL
 */
public class BrowseAccountingCodesDTO {
    static final Logger logger =
            Logger.getLogger(BrowseAccountingCodesDTO.class);
    private ValueListIterator codeOptions;
    private String hdn_accountingCenterId;
    private String but_submit;
    private String hdn_segmentNumber;
    private String hdn_segNumSet;
    private String hdn_segmentName;
    private String hideButton;
    private String slashes;
    private String optionHyperlink;
    private String hdn_valueSet;
    private String hdn_segmentValue;
    private String segmentName;

    public String getHdn_segmentValue() {
        return hdn_segmentValue;
    }

    public void setHdn_segmentValue(String hdn_segmentValue) {
        this.hdn_segmentValue = hdn_segmentValue;
    }

    public String getSegmentName() {
        return segmentName;
    }

    public void setSegmentName(String segmentName) {
        this.segmentName = segmentName;
    }

    public String getOptionHyperlink() {
        return optionHyperlink;
    }

    public void setOptionHyperlink(String optionHyperlink) {
        this.optionHyperlink = optionHyperlink;
    }

    public String getSlashes() {
        return slashes;
    }

    public void setSlashes(String slashes) {
        this.slashes = slashes;
    }

    public String getHideButton() {
        return hideButton;
    }

    public void setHideButton(String hideButton) {
        this.hideButton = hideButton;
    }

    public String getHdn_segmentName() {
        return hdn_segmentName;
    }

    public void setHdn_segmentName(String hdn_segmentName) {
        this.hdn_segmentName = hdn_segmentName;
    }

    public String getHdn_segNumSet() {
        return hdn_segNumSet;
    }

    public void setHdn_segNumSet(String hdn_segNumSet) {
        this.hdn_segNumSet = hdn_segNumSet;
    }

    public String getHdn_valueSet() {
        return hdn_valueSet;
    }

    public void setHdn_valueSet(String hdn_valueSet) {
        this.hdn_valueSet = hdn_valueSet;
    }

    public String getHdn_segmentNumber() {
        return hdn_segmentNumber;
    }

    public void setHdn_segmentNumber(String hdn_segmentNumber) {
        this.hdn_segmentNumber = hdn_segmentNumber;
    }

    public String getHdn_displaySegmentNumber() {
        return hdn_displaySegmentNumber;
    }

    public void setHdn_displaySegmentNumber(String hdn_displaySegmentNumber) {
        this.hdn_displaySegmentNumber = hdn_displaySegmentNumber;
    }

    private String hdn_displaySegmentNumber;

    public String getBut_submit() {
        return but_submit;
    }

    public void setBut_submit(String but_submit) {
        this.but_submit = but_submit;
    }

    public String getHdn_accountingCenterId() {
        return hdn_accountingCenterId;
    }

    public void setHdn_accountingCenterId(String hdn_accountingCenterId) {
        this.hdn_accountingCenterId = hdn_accountingCenterId;
    }

    public ValueListIterator getCodeOptions() {
        return codeOptions;
    }

    public void setCodeOptions(ValueListIterator codeOptions) {
        this.codeOptions = codeOptions;
    }

}