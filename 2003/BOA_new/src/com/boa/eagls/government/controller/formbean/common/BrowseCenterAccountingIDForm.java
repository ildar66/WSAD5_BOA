package com.boa.eagls.government.controller.formbean.common;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;

import java.util.List;
import java.util.LinkedList;

/**
 * <p><small> VDI Company, 18.07.2003 16:55:00</small></p>
 * @author OlegK
 */
public class BrowseCenterAccountingIDForm extends ActionForm {
    static final Logger logger = Logger.getLogger(BrowseCenterAccountingIDForm.class);

    private String availableAccountingCenterIds;
//    private List availableAccountingCenterIds;

    private List idValues;
    private List idDescriptions;


    public String getAvailableAccountingCenterIds() {
        return availableAccountingCenterIds;
    }

    public void setAvailableAccountingCenterIds(String availableAccountingCenterIds) {
        this.availableAccountingCenterIds = availableAccountingCenterIds;
    }

    public List getIdValues() {
        return idValues;
    }

    public void setIdValues(List idValues) {
        this.idValues = idValues;
    }

    public List getIdDescriptions() {
        return idDescriptions;
    }

    public void setIdDescriptions(List idDescriptions) {
        this.idDescriptions = idDescriptions;
    }

}
