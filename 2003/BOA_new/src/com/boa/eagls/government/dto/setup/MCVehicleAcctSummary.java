package com.boa.eagls.government.dto.setup;

import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;


/**
 * <code>This is the container object that stores basic Fleet account information.
 * It is used in an array for searches to obtain Fleet accounts.</code>
 *
 */

public class MCVehicleAcctSummary extends BusinessObject implements Serializable {
    //static finals for status
    public static final short OPEN = 0;
    public static final short CLOSED = 1;

    //The attributes otherwise noted map to screen elements described in SDRs.
    private String voyagerAcctNo = STRING_DEFAULT;
    private String acctNumber = STRING_DEFAULT;
    private String acctName = STRING_DEFAULT;
    private String equipmentID = STRING_DEFAULT;
    private String vin = STRING_DEFAULT;
    /* private boolean accountActive = true;  //this is a retrofit
     private boolean pastDue61Days = BOOLEAN_DEFAULT;  //this is a retrofit
     private short status = SHORT_DEFAULT;  //this is a retrofit
     */

    public MCVehicleAcctSummary() {
    }

    //--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from MCVehicleAcctSummary.

    public String getAcctNumber() {
        return new String(acctNumber);
    }

    public String getAcctName() {
        return new String(acctName);
    }

    public String getEquipmentID() {
        return new String(equipmentID);
    }

    public String getVoyagerAccountNumber() {
        return new String(voyagerAcctNo);
    }

    public String getVIN()               //MM--added this to bring in va.VIN for VehicleID
    {
        return new String(vin);
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
    // These methods are used to set data elements in MCVehicleAcctSummary.

    public void setAcctNumber(String tAcctNumber) {
        acctNumber = validateString(tAcctNumber);
    }

    public void setAcctName(String tAcctName) {
        acctName = validateString(tAcctName);
    }

    public void setEquipmentID(String tEquipmentID) {
        equipmentID = validateString(tEquipmentID);
    }

    public void setVoyagerAccountNumber(String tVoyagerAccountNumber) {
        voyagerAcctNo = validateString(tVoyagerAccountNumber);
    }

    public void setVIN(String tvin)                //MM--added this to bring in va.VIN for VehicleID
    {
        vin = validateString(tvin);

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
       }*/
}