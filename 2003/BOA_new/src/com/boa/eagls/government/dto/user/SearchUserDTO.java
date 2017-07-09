/* SearchUserDTO class */
package com.boa.eagls.government.dto.user;

import com.boa.eagls.government.dto.DTOBase;

/**
 * <p>Title: </p> <p>Description: This data tranfer object is used to transfer the
 * searching criteria from search action to search service.</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 * @invariant $none
 */
public class SearchUserDTO extends DTOBase
{
    private String accountNumber;
    private String lastName;
    private String firstName;
    private String hierarchyDepth;
    private String programNumber;
    private String[] hierarchy;
    private boolean countFlag;

    /**
     * Returns the individualaccount Number.
     * @return the individualaccount Number.
     * @pre $none
     * @post $none
     */
    public String getAccountNumber()
    {
        return accountNumber;
    }

    /**
     * sets the individualaccount number.
     * @param accountNumber
     * @pre $none
     * @post $none
     */
    public void setAccountNumber(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    /**
     * Returns the last name.
     * @return the last name.
     * @pre $none
     * @post $none
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * sets the last name.
     * @param lastName
     * @pre $none
     * @post $none
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * Returns the first name.
     * @return the first name.
     * @pre $none
     * @post $none
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * sets the first name
     * @param firstName
     * @pre $none
     * @post $none
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * Returns the hierarchy depth.
     * @return the hierarchy depth.
     * @pre $none
     * @post $none
     */
    public String getHierarchyDepth()
    {
        return hierarchyDepth;
    }

    /**
     * sets the hierarchy depth.
     * @param hierarchyDepth
     * @pre $none
     * @post $none
     */
    public void setHierarchyDepth(String hierarchyDepth)
    {
        this.hierarchyDepth = hierarchyDepth;
    }

    /**
     * Returns the program number.
     * @return the program number.
     * @pre $none
     * @post $none
     */
    public String getProgramNumber()
    {
        return programNumber;
    }

    /**
     * sets the program number.
     * @param programNumber
     * @pre $none
     * @post $none
     */
    public void setProgramNumber(String programNumber)
    {
        this.programNumber = programNumber;
    }

    /**
     * Returns the hierarchy levels.
     * @return the hierarchy levels.
     * @pre $none
     * @post $none
     */
    public String[] getHierarchy()
    {
        return hierarchy;
    }

    /**
     * sets the hierarchy levels.
     * @param hierarchy
     * @pre $none
     * @post $none
     */
    public void setHierarchy(String[] hierarchy)
    {
        this.hierarchy = hierarchy;
    }

    public boolean isCountFlag()
    {
        return countFlag;
    }

    public void setCountFlag(boolean countFlag)
    {
        this.countFlag = countFlag;
    }

}
