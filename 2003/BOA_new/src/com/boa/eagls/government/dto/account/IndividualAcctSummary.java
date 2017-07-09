package com.boa.eagls.government.dto.account;


import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * IndividualAcctSummary is used to show the results of a multiple search result for Indivdiual Acct Inquiry
 * A set of these IndividualSummary is returned for multiple results.
 * @author Oleg Klimenko
 */
public class IndividualAcctSummary extends BusinessObject implements Serializable {
    //static finals for status
    public static final short OPEN = 0;
    public static final short CLOSED = 1;

    //The attributes otherwise noted map to screen elements described in SDRs.
    private String lastName = STRING_DEFAULT;
    private String firstName = STRING_DEFAULT;
    private String acctNumber = STRING_DEFAULT;
    private String ssn = STRING_DEFAULT;
    private String centralAcctID = STRING_DEFAULT;

    /* private boolean accountActive = true;  //this is a retrofit
     private boolean pastDue61Days = BOOLEAN_DEFAULT;  //this is a retrofit
     private short status = SHORT_DEFAULT;  //this is a retrofit
 */
    public IndividualAcctSummary() {
    }

//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from IndividualAcctSummary.

    public String getAccountName() {
        return firstName + " " + lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public String getSsn() {
        return ssn;
    }

    public String getCentralAcctID() {
        return new String(centralAcctID);
    }

/*        //WARNING Retrofit attribute, not filled in all cases.
    public boolean getAccountActive()
    {
        return accountActive;
    }

    //WARNING Retrofit attribute, not filled in all cases.
    public boolean getPastDue61Days()
    {
        return pastDue61Days;
    }

    //WARNING Retrofit attribute, not filled in all cases.
    public short getStatus()
    {
        return status;
    }
    */

//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in IndividualAcctSummary.

    public void setLastName(String tLastName) {
        lastName = validateString(tLastName);
    }

    public void setFirstName(String tFirstName) {
        firstName = validateString(tFirstName);
    }

    public void setAcctNumber(String tAcctNumber) {
        acctNumber = validateString(tAcctNumber);
    }

    public void setSSN(String tSSN) {
        ssn = validateString(tSSN);
    }

    public void setCentralAcctID(String tCentralAcctID) {
        centralAcctID = validateString(tCentralAcctID);
    }

    /*   public void setAccountActive(boolean tAccountActive)
       {
           accountActive = tAccountActive;
       }

       public void setPastDue61Days(boolean tPastDue61Days)
       {
           pastDue61Days = tPastDue61Days;
       }

       public void setStatus(short tStatus)
       {
           status = tStatus;
       }
       */
}
