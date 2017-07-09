/**
 * ProfileDTO
 */
package com.boa.eagls.government.dto;

import com.boa.eagls.government.util.Constants;

import java.util.*;
import java.io.*;

/**
 * Concrete Profile DTO <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class ProfileDTO extends DTOBase
{

    // The attributes otherwise noted map to screen elements described in SDRs.
    private String lastName =
            STRING_DEFAULT;    // user's last name or Name in objects with one Name
    private String firstName = STRING_DEFAULT;    // user's first name
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

    // ----------------------------------------Constructor-------------------------------------------

    /**
     * Constructor declaration
     *
     */
    public ProfileDTO()
    {
    }

    /**
     * Constructor declaration
     *
     *
     * @param tProfile
     */
    public ProfileDTO(ProfileDTO tProfile)
    {
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

    // --------------------------------------get methods-----------------------------------------------
    // These methods are used to retrieve data elements from Profile.

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getBusinessPhoneExtension()
    {
        return new String(businessPhoneExtension);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getLastName()
    {
        return new String(lastName);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getFirstName()
    {
        return new String(firstName);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAddress1()
    {
        return new String(address1);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAddress2()
    {
        return new String(address2);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAddress3()
    {
        return new String(address3);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAddress4()
    {
        return new String(address4);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getCity()
    {
        return new String(city);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getStateOrProvince()
    {
        return new String(stateOrProvince);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getCountry()
    {
        return new String(country);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getZip()
    {
        return new String(zip);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getBusinessPhone()
    {
        return new String(businessPhone);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getFax()
    {
        return new String(fax);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getEMail()
    {
        return new String(eMail);
    }

    // --------------------------------------set methods-----------------------------------------------
    // These methods are used to set data elements in Profile.

    /**
     * Method declaration
     *
     *
     * @param tBusinessPhoneExtension
     */
    public void setBusinessPhoneExtension(String tBusinessPhoneExtension)
    {
        businessPhoneExtension = validateString(tBusinessPhoneExtension);
    }

    /**
     * Method declaration
     *
     *
     * @param tLastName
     */
    public void setLastName(String tLastName)
    {
        lastName = validateString(tLastName);
    }

    /**
     * Method declaration
     *
     *
     * @param tFirstName
     */
    public void setFirstName(String tFirstName)
    {
        firstName = validateString(tFirstName);
    }

    /**
     * Method declaration
     *
     *
     * @param tAddress1
     */
    public void setAddress1(String tAddress1)
    {
        address1 = validateString(tAddress1);
    }

    /**
     * Method declaration
     *
     *
     * @param tAddress2
     */
    public void setAddress2(String tAddress2)
    {
        address2 = validateString(tAddress2);
    }

    /**
     * Method declaration
     *
     *
     * @param tAddress3
     */
    public void setAddress3(String tAddress3)
    {
        address3 = validateString(tAddress3);
    }

    /**
     * Method declaration
     *
     *
     * @param tAddress4
     */
    public void setAddress4(String tAddress4)
    {
        address4 = validateString(tAddress4);
    }

    /**
     * Method declaration
     *
     *
     * @param tCity
     */
    public void setCity(String tCity)
    {
        city = validateString(tCity);
    }

    /**
     * Method declaration
     *
     *
     * @param tStateOrProvince
     */
    public void setStateOrProvince(String tStateOrProvince)
    {
        stateOrProvince = validateString(tStateOrProvince);
    }

    /**
     * Method declaration
     *
     *
     * @param tCountry
     */
    public void setCountry(String tCountry)
    {
        country = validateString(tCountry);
    }

    /**
     * Method declaration
     *
     *
     * @param tZip
     */
    public void setZip(String tZip)
    {
        zip = validateString(tZip);
    }

    /**
     * Method declaration
     *
     *
     * @param tBusinessPhone
     */
    public void setBusinessPhone(String tBusinessPhone)
    {
        businessPhone = validateString(tBusinessPhone);
    }

    /**
     * Method declaration
     *
     *
     * @param tFax
     */
    public void setFax(String tFax)
    {
        fax = validateString(tFax);
    }

    /**
     * Method declaration
     *
     *
     * @param tEMail
     */
    public void setEMail(String tEMail)
    {
        eMail = validateString(tEMail);
    }

}
