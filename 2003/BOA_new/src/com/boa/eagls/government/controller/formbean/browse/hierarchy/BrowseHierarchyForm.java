package com.boa.eagls.government.controller.formbean.browse.hierarchy;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.service.individualaccount.SearchAccountParam;

/**
 * <p><small> VDI Company, 14.07.2003 15:56:39</small></p>
 * @author OlegK
 */

public class BrowseHierarchyForm extends ActionForm { //implements BrowseHierarchyParam  {

    static final Logger logger = Logger.getLogger(BrowseHierarchyForm.class);

    private String startRecord;
    private String forceUpdate;
    private String availableHierarchiesList;
    private String isTransfer;
    private String hdn_isTransfer;


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

    public String getAvailableHierarchiesList() {
        return availableHierarchiesList;
    }

    public void setAvailableHierarchiesList(String availableHierarchiesList) {
        this.availableHierarchiesList = availableHierarchiesList;
    }

    public String getTransfer() {
        return isTransfer;
    }

    public void setTransfer(String transfer) {
        isTransfer = transfer;
    }

    public String getHdn_isTransfer() {
        return hdn_isTransfer;
    }

    public void setHdn_isTransfer(String hdn_isTransfer) {
        this.hdn_isTransfer = hdn_isTransfer;
    }
}
