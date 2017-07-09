package com.boa.eagls.government.dto.browse;

import org.apache.log4j.Logger;
import com.boa.eagls.government.business.BusinessObject;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;


import java.io.Serializable;

/**
 * <p><small> DVI Company, 23.07.2003 12:07:45</small></p>
 * @author GlebL
 */
public class BrowseMasterAcctCodeDTO
        extends BusinessObject implements Serializable{
    static final Logger logger =
            Logger.getLogger(BrowseMasterAcctCodeDTO.class);
    private String hdn_segNumSet;
    private String hdn_valueSet;
    private String segmentRepresentation1;
    private String segmentRepresentation;
    private String segmentDescription;
    private String segmentDescription1;
    private String hdn_accountingCenterId;
    private String doNotHide ;
    private String hideButton;
    private String showButton;
    private String slashes;

    public String getHdn_accountingCode() {
        return hdn_accountingCode;
    }

    public void setHdn_accountingCode(String hdn_accountingCode) {
        this.hdn_accountingCode = hdn_accountingCode;
    }

    private String hdn_accountingCode;
    private AccountingCodeSegmentDescription[] acsd;

    public AccountingCodeSegmentDescription[] getAccountingCodeSegmentDescriptions() {
        return acsd;
    }

    public void setAccountingCodeSegmentDescriptions(AccountingCodeSegmentDescription[] acsd) {
        this.acsd = acsd;
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

    public String getSegmentRepresentation1() {
        return segmentRepresentation1;
    }

    public void setSegmentRepresentation1(String segmentRepresentation1) {
        this.segmentRepresentation1 = segmentRepresentation1;
    }

    public String getSegmentRepresentation() {
        return segmentRepresentation;
    }

    public void setSegmentRepresentation(String segmentRepresentation) {
        this.segmentRepresentation = segmentRepresentation;
    }

    public String getSegmentDescription() {
        return segmentDescription;
    }

    public void setSegmentDescription(String segmentDescription) {
        this.segmentDescription = segmentDescription;
    }

    public String getSegmentDescription1() {
        return segmentDescription1;
    }

    public void setSegmentDescription1(String segmentDescription1) {
        this.segmentDescription1 = segmentDescription1;
    }

    public String getHdn_accountingCenterId() {
        return hdn_accountingCenterId;
    }

    public void setHdn_accountingCenterId(String hdn_accountingCenterId) {
        this.hdn_accountingCenterId = hdn_accountingCenterId;
    }

    public String getDoNotHide() {
        return doNotHide;
    }

    public void setDoNotHide(String doNotHide) {
        this.doNotHide = doNotHide;
    }

    public String getHideButton() {
        return hideButton;
    }

    public void setHideButton(String hideButton) {
        this.hideButton = hideButton;
    }

    public String getShowButton() {
        return showButton;
    }

    public void setShowButton(String showButton) {
        this.showButton = showButton;
    }

    public String getSlashes() {
        return slashes;
    }

    public void setSlashes(String slashes) {
        this.slashes = slashes;
    }

}