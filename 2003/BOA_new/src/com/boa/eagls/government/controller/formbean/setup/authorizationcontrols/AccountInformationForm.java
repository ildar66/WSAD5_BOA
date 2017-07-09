package com.boa.eagls.government.controller.formbean.setup.authorizationcontrols;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import com.boa.eagls.government.dto.setup.authorizationcontrols.AccountInformation;

/**
 * <p><small> VDI Company, 18.07.2003</small></p>
 * @author Danil Tupikin
 */
public class AccountInformationForm extends ActionForm implements AccountInformation {

    private String auth_accountNumber;

    private String auth_accountName;

    private String auth_agencyName;

    private String auth_centralId;

    private String auth_creditLimit;

    private String auth_guid;

    public String getAuth_accountNumber() {
        return auth_accountNumber;
    }

    public void setAuth_accountNumber(String auth_accountNumber) {
        this.auth_accountNumber = auth_accountNumber;
    }

    public String getAuth_accountName() {
        return auth_accountName;
    }

    public void setAuth_accountName(String auth_accountName) {
        this.auth_accountName = auth_accountName;
    }

    public String getAuth_agencyName() {
        return auth_agencyName;
    }

    public void setAuth_agencyName(String auth_agencyName) {
        this.auth_agencyName = auth_agencyName;
    }

    public String getAuth_centralId() {
        return auth_centralId;
    }

    public void setAuth_centralId(String auth_centralId) {
        this.auth_centralId = auth_centralId;
    }

    public String getAuth_creditLimit() {
        return auth_creditLimit;
    }

    public void setAuth_creditLimit(String auth_creditLimit) {
        this.auth_creditLimit = auth_creditLimit;
    }

    public String getAuth_guid() {
        return auth_guid;
    }

    public void setAuth_guid(String auth_guid) {
        this.auth_guid = auth_guid;
    }
}
