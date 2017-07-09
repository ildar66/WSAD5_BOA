package com.boa.eagls.government.dto.setup.authorizationcontrols;

/**
 * <p><small> VDI Company, 18.07.2003</small></p>
 * @author Danil Tupikin
 */
public interface AccountInformation {

    String getAuth_accountNumber();

    String getAuth_accountName();

    String getAuth_agencyName();

    String getAuth_centralId();

    String getAuth_creditLimit();

    String getAuth_guid();
}
