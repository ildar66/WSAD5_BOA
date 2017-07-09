/**
 * UserAccountDTO
 */
package com.boa.eagls.government.dto.user;

import com.boa.eagls.government.dto.DTOBase;

import java.util.*;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class UserAccountDTO extends DTOBase
{
    private String userID =
            STRING_DEFAULT;					 // user id associated to this individualaccount
    private String roleName =
            STRING_DEFAULT;					 // role this individualaccount falls under
    private String accountNumber = STRING_DEFAULT;    // the individualaccount number
    private boolean Default =
            BOOLEAN_DEFAULT;				 // is this the default individualaccount under this role?
    private String agencyName = STRING_DEFAULT;
    private String defaultAccount =
            STRING_DEFAULT;					 // the default individualaccount number for this role
    private Hashtable accountCache;			 // the list of accounts under this role
    private String[] accountList = STRING_ARRAY_DEFAULT;

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
     * @return
     */
    public String getAgencyName()
    {
        return agencyName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isDefault()
    {
        return Default;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleName()
    {
        return roleName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID()
    {
        return userID;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     */
    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    /**
     * Method declaration
     *
     *
     * @param roleName
     */
    public void setRoleName(String roleName)
    {
        this.roleName = roleName;
    }

    /**
     * Method declaration
     *
     *
     * @param Default
     */
    public void setDefault(boolean Default)
    {
        this.Default = Default;
    }

    /**
     * Method declaration
     *
     *
     * @param agencyName
     */
    public void setAgencyName(String agencyName)
    {
        this.agencyName = agencyName;
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
    public Hashtable getAccountCache()
    {
        return accountCache;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getAccountList()
    {
        return accountList;
    }

    /**
     * Method declaration
     *
     *
     * @param accountCache
     */
    public void setAccountCache(Hashtable accountCache)
    {
        this.accountCache = accountCache;
    }

    /**
     * Method declaration
     *
     *
     * @param accountList
     */
    public void setAccountList(String[] accountList)
    {
        this.accountList = accountList;
    }

    /**
     * Method declaration
     *
     *
     * @param defaultAccount
     */
    public void setDefaultAccount(String defaultAccount)
    {
        this.defaultAccount = defaultAccount;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDefaultAccount()
    {
        return defaultAccount;
    }

}
