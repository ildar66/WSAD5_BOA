package com.boa.eagls.government.controller.formbean.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.service.common.BrowseMasterAcctCodeActionsParam;

/**
 * <p><small> DVI Company, 24.07.2003 20:13:54</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeActionsForm
        extends ActionForm implements BrowseMasterAcctCodeActionsParam{
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeActionsForm.class);
    private String startRecord;
    private String forceUpdate;
    private String txt_browseCriteria;
    private String hdn_accountingCenterId;
    private String but_submit;
    private String sel_availableSegments;
    private String hdn_valueSet;
    private String hdn_segNumSet;
    private String hideButton;
    private String slashes;
    private String txt_segmentValue;

    public String getTxt_segmentValue() {
        return txt_segmentValue;
    }

    public void setTxt_segmentValue(String txt_segmentValue) {
        this.txt_segmentValue = txt_segmentValue;
    }

    public String getSlashes() {
        return slashes;
    }

    public void setSlashes(String slashes) {
        this.slashes = slashes;
    }

    public String getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(String startRecord) {
        this.startRecord = startRecord;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public String getTxt_browseCriteria() {
        return txt_browseCriteria;
    }

    public void setTxt_browseCriteria(String txt_browseCriteria) {
        this.txt_browseCriteria = txt_browseCriteria;
    }

    public String getHdn_accountingCenterId() {
        return hdn_accountingCenterId;
    }

    public void setHdn_accountingCenterId(String hdn_accountingCenterId) {
        this.hdn_accountingCenterId = hdn_accountingCenterId;
    }

    public String getBut_submit() {
        return but_submit;
    }

    public void setBut_submit(String but_submit) {
        this.but_submit = but_submit;
    }

    public String getSel_availableSegments() {
        return sel_availableSegments;
    }

    public void setSel_availableSegments(String sel_availableSegments) {
        this.sel_availableSegments = sel_availableSegments;
    }

    public String getHdn_valueSet() {
        return hdn_valueSet;
    }

    public void setHdn_valueSet(String hdn_valueSet) {
        this.hdn_valueSet = hdn_valueSet;
    }

    public String getHdn_segNumSet() {
        return hdn_segNumSet;
    }

    public void setHdn_segNumSet(String hdn_segNumSet) {
        this.hdn_segNumSet = hdn_segNumSet;
    }

    public String getHideButton() {
        return hideButton;
    }

    public void setHideButton(String hideButton) {
        this.hideButton = hideButton;
    }

}