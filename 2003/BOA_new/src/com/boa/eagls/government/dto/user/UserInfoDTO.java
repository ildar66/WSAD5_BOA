package com.boa.eagls.government.dto.user;

import com.boa.eagls.government.service.userprofile.*;

/**
 * UserInfoDTO
 *
 *
 * @author
 * @version %I%, %G%
 */
public class UserInfoDTO implements java.io.Serializable
{
    private String lastName;	     // user's last name
    private String firstName;	     // user's first name
    private String status;	     // status of this user
    private String role;    // this user's last role added to the collection this field is just for display
    private String userId;	     // the user this password pertains to
    private String hierarchyNumber;
    private String hierarchy;
    private String accountNumber;    // the current password

    /**
     * Constructor declaration
     *
     */
    public UserInfoDTO()
    {
    }

    /**
     * Returns the user ID associated with this object
     * @return          a String containing this object's user ID
     */
    public String getUserId()
    {
        return userId;
    }

    /**
     * Sets this objects user ID to that given in the parameter list
     * @param           aUserID - set the userID attribute to this
     */
    public void setUserId(String aUserId)
    {
        userId = aUserId;
    }

    /**
     * Sets this object's password to that given in the parameter list
     * @param           aPassword - set the password attribute to this
     */

    /**
     * Returns the password history associated with this object
     * @return          a Vector containing a history of this object's passwords
     */
    public String getRole()
    {
        return role;
    }

    /**
     * Method declaration
     *
     *
     * @param aRoleName
     */
    public void setRole(String aRoleName)
    {
        role = aRoleName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * Method declaration
     *
     *
     * @param newLastName
     */
    public void setLastName(String newLastName)
    {
        lastName = newLastName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * Method declaration
     *
     *
     * @param newFirstName
     */
    public void setFirstName(String newFirstName)
    {
        firstName = newFirstName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * Method declaration
     *
     *
     * @param aStatus
     */
    public void setStatus(String aStatus)
    {
        status = aStatus;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getHierarchyNumber()
    {
        return hierarchyNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyNumber
     */
    public void setHierarchyNumber(String hierarchyNumber)
    {
        this.hierarchyNumber = hierarchyNumber;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * Method declaration
     *
     *
     * @param accountNumber
     */
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getHierarchy()
    {
        return hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param h
     */
    public void setHierarchy(String h)
    {
        hierarchy = h;
    }

}
