package com.boa.eagls.government.dto.account;

import java.io.Serializable;

/**
 * Individual Account value object and Struts Form Bean
 * @author Oleg Klimenko
 * Date: 24.06.2003
 * Time: 20:38:46
 */
public class IndividualAccount implements Serializable {

    // todo check if getter methods are required

    private String firstName = null;
    private String lastName = null;
    // todo check for type of accountNumber
    private String accountNumber = null;
    // todo check for type of SSN
    private String SSN = null;
    // todo check for type of agencyBillNumber
    private String agencyBillNumber = null;

    public IndividualAccount() {

    }

    public IndividualAccount(String firstName, String lastName, String accountNumber, String SSN, String agencyBillNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNumber = accountNumber;
        this.SSN = SSN;
        this.agencyBillNumber = agencyBillNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public String getAgencyBillNumber() {
        return agencyBillNumber;
    }

    public void setAgencyBillNumber(String agencyBillNumber) {
        this.agencyBillNumber = agencyBillNumber;
    }


}
