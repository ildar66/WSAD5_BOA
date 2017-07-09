package com.boa.eagls.government.dto.centralaccount;

import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <p><small> VDI Company, 17.07.2003 16:45:41</small></p>
 * @author AlexanderZe
 */
public class CentralAcctSummary extends BusinessObject implements Serializable {

    //The attributes otherwise noted map to screen elements described in SDRs.
    private String centralAcctName = STRING_DEFAULT;
    private String centralAcctNo = STRING_DEFAULT;
    private String centralAcctID = STRING_DEFAULT;


    public CentralAcctSummary() {
       }

//--------------------------------------get methods-----------------------------------------------
       // These methods are used to retrieve data elements from CentralAcctSummary.

       public String getCentralAcctName() {
           return new String(centralAcctName);
       }

       public String getCentralAcctID() {
           return new String(centralAcctID);
       }

       public String getCentralAcctNo() {
           return new String(centralAcctNo);
       }

//--------------------------------------set methods-----------------------------------------------
       // These methods are used to set data elements in CentralAcctSummary.

       public void setCentralAcctName(String tCentralAcctName) {
           centralAcctName = validateString(tCentralAcctName);
       }

       public void setCentralAcctNo(String tCentralAcctNo) {
           centralAcctNo = validateString(tCentralAcctNo);
       }

       public void setCentralAcctID(String tCentralAcctID) {
           centralAcctID = validateString(tCentralAcctID);
       }

}

