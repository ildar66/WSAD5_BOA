package com.boa.eagls.government.dto;

import com.boa.eagls.government.business.BusinessObject;

import java.io.Serializable;

/**
 * <code>The Profile object is the parent of many objects in EAGLS
 * It contains profile type information including name and address
 * The lastName is used when only Name is specified for children classes</code>
 *
/* 	@version 1.0 $Modtime:   16 Feb 2000 18:40:58  $$Revision: 1.3 $
/* 	@author  Ed Zhang & Frank Jary
**/
public class Profile extends BusinessObject implements Serializable {
    //The attributes otherwise noted map to screen elements described in SDRs.

    private String lastName = STRING_DEFAULT;      // user's last name or Name in objects with one Name
    private String firstName = STRING_DEFAULT;     // user's first name
    private String address1 = STRING_DEFAULT;
    private String address2 = STRING_DEFAULT;
    private String address3 = STRING_DEFAULT;
    private String address4 = STRING_DEFAULT;
    private String city = STRING_DEFAULT;
    private String stateOrProvince = STRING_DEFAULT;
    private String country = STRING_DEFAULT;
    private String zip = STRING_DEFAULT;
    private String businessPhone = STRING_DEFAULT;
    private String businessPhoneExtension = STRING_DEFAULT;
    private String fax = STRING_DEFAULT;
    private String eMail = STRING_DEFAULT;

//----------------------------------------Constructor-------------------------------------------

    public Profile() {
    }

    public Profile(Profile tProfile) {
        setLastName(tProfile.getLastName());
        setFirstName(tProfile.getFirstName());
        setAddress1(tProfile.getAddress1());
        setAddress2(tProfile.getAddress2());
        setAddress3(tProfile.getAddress3());
        setAddress4(tProfile.getAddress4());
        setCity(tProfile.getCity());
        setStateOrProvince(tProfile.getStateOrProvince());
        setCountry(tProfile.getCountry());
        setZip(tProfile.getZip());
        setBusinessPhone(tProfile.getBusinessPhone());
        setFax(tProfile.getFax());
        setEMail(tProfile.getEMail());
        setBusinessPhoneExtension(tProfile.getBusinessPhoneExtension());
    }


//--------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from Profile.

    public String getBusinessPhoneExtension() {
        return new String(businessPhoneExtension);
    }

    public String getLastName() {
        return new String(lastName);
    }

    public String getFirstName() {
        return new String(firstName);
    }

    public String getAddress1() {
        return new String(address1);
    }

    public String getAddress2() {
        return new String(address2);
    }

    public String getAddress3() {
        return new String(address3);
    }

    public String getAddress4() {
        return new String(address4);
    }

    public String getCity() {
        return new String(city);
    }

    public String getStateOrProvince() {
        return new String(stateOrProvince);
    }

    public String getCountry() {
        return new String(country);
    }

    public String getZip() {
        return new String(zip);
    }

    public String getBusinessPhone() {
        return new String(businessPhone);
    }

    public String getFax() {
        return new String(fax);
    }

    public String getEMail() {
        return new String(eMail);
    }
//--------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in Profile.

    public void setBusinessPhoneExtension(String tBusinessPhoneExtension) {
        businessPhoneExtension = validateString(tBusinessPhoneExtension);
    }

    public void setLastName(String tLastName) {
        lastName = validateString(tLastName);
    }

    public void setFirstName(String tFirstName) {
        firstName = validateString(tFirstName);
    }

    public void setAddress1(String tAddress1) {
        address1 = validateString(tAddress1);
    }

    public void setAddress2(String tAddress2) {
        address2 = validateString(tAddress2);
    }

    public void setAddress3(String tAddress3) {
        address3 = validateString(tAddress3);
    }

    public void setAddress4(String tAddress4) {
        address4 = validateString(tAddress4);
    }

    public void setCity(String tCity) {
        city = validateString(tCity);
    }

    public void setStateOrProvince(String tStateOrProvince) {
        stateOrProvince = validateString(tStateOrProvince);
    }

    public void setCountry(String tCountry) {
        country = validateString(tCountry);
    }

    public void setZip(String tZip) {
        zip = validateString(tZip);
    }

    public void setBusinessPhone(String tBusinessPhone) {
        businessPhone = validateString(tBusinessPhone);
    }

    public void setFax(String tFax) {
        fax = validateString(tFax);
    }

    public void setEMail(String tEMail) {
        eMail = validateString(tEMail);
    }
}
