/**
 * UserRoleDTO
 */
package com.boa.eagls.government.dto.user;

import java.util.Vector;
import java.util.Hashtable;

import com.boa.eagls.government.dto.*;

import java.util.*;

import com.boa.eagls.government.dto.user.*;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class UserRoleDTO extends DTOBase
{
    private String userID =
            STRING_DEFAULT;     // the user of this role
    private String roleName =
            STRING_DEFAULT;     // the name of this role
    private String coreRoleName =
            STRING_DEFAULT;     // the core role this role is based on
    private boolean Default =
            BOOLEAN_DEFAULT;    // is this the user's default role
    private HierarchyDTO[] defaultHierarchy;    // the default hierarchy for this role
    private Vector hierarchyCache;    // the list of hierarchies under this role
    private UserHierarchyDTO[] userHierarchies;
    private String defaultAccount =
            STRING_DEFAULT;     // the default individualaccount number for this role
    private Hashtable accountCache;    // the list of accounts under this role
    private String[] accountList = STRING_ARRAY_DEFAULT;
    private UserAccountDTO[] userAccounts;
    private HierarchyDTO[] hierarchy;
    private short addRemoveFunctionsFlag = SHORT_DEFAULT;
    private String accessFlag = STRING_DEFAULT;
    private String roleDescription = STRING_DEFAULT;
    private String[] roleFunctions = STRING_ARRAY_DEFAULT;
    private Vector validFunctions;
    private String roleAttribute;
    private String roleFunctionsStr = "";

    /**
     * Constructor declaration
     *
     */
    public UserRoleDTO()
    {
        hierarchyCache = new Vector();
        accountCache = new Hashtable();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getValidFunctions()
    {
        return validFunctions;
    }

    /**
     * Method declaration
     *
     *
     * @param validFunctions
     */
    public void setValidFunctions(Vector validFunctions)
    {
        this.validFunctions = validFunctions;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getAccessFlag()
    {
        return accessFlag;
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
     * @return
     */
    public short getAddRemoveFunctionsFlag()
    {
        return addRemoveFunctionsFlag;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getCoreRoleName()
    {
        return coreRoleName;
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
    public String getDefaultAccount()
    {
        return defaultAccount;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getDefaultHierarchy()
    {
        return defaultHierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Vector getHierarchyCache()
    {
        return hierarchyCache;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleDescription()
    {
        return roleDescription;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getRoleFunctions()
    {
        return roleFunctions;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleFunctionStr()
    {
        return roleFunctionsStr;
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
    public UserAccountDTO[] getUserAccounts()
    {
        return userAccounts;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserHierarchyDTO[] getUserHierarchies()
    {
        return userHierarchies;
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
     * @param userHierarchies
     */
    public void setUserHierarchies(UserHierarchyDTO[] userHierarchies)
    {
        this.userHierarchies = userHierarchies;
    }

    /**
     * Method declaration
     *
     *
     * @param userAccounts
     */
    public void setUserAccounts(UserAccountDTO[] userAccounts)
    {
        this.userAccounts = userAccounts;
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
     * @param roleFunctions
     */
    public void setRoleFunctions(String[] roleFunctions)
    {
        this.roleFunctions = roleFunctions;
    }

    /**
     * Method declaration
     *
     *
     * @param rolefunctionsstr
     */
    public void setRoleFunctionsStr(String rolefunctionsstr)
    {
        this.roleFunctionsStr = rolefunctionsstr;
    }

    /**
     * Method declaration
     *
     *
     * @param roleDescription
     */
    public void setRoleDescription(String roleDescription)
    {
        this.roleDescription = roleDescription;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyCache
     */
    public void setHierarchyCache(Vector hierarchyCache)
    {
        this.hierarchyCache = hierarchyCache;
    }

    /**
     * Method declaration
     *
     *
     * @param defaultHierarchy
     */
    public void setDefaultHierarchy(HierarchyDTO[] defaultHierarchy)
    {
        this.defaultHierarchy = defaultHierarchy;
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
     * @param coreRoleName
     */
    public void setCoreRoleName(String coreRoleName)
    {
        this.coreRoleName = coreRoleName;
    }

    /**
     * Method declaration
     *
     *
     * @param addRemoveFunctionsFlag
     */
    public void setAddRemoveFunctionsFlag(short addRemoveFunctionsFlag)
    {
        this.addRemoveFunctionsFlag = addRemoveFunctionsFlag;
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
     * @param accessFlag
     */
    public void setAccessFlag(String accessFlag)
    {
        this.accessFlag = accessFlag;
    }

    /**
     * Method declaration
     *
     *
     * @param accNumber
     * @param accDTO
     */
    public void addToAccountCache(String accNumber, UserAccountDTO accDTO)
    {
        accountCache.put(accNumber, accDTO);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getHierarchy()
    {
        return hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     */
    public void setHierarchy(HierarchyDTO[] hierarchy)
    {
        this.hierarchy = hierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleAttribute()
    {
        return roleAttribute;
    }

    /**
     * Method declaration
     *
     *
     * @param roleAttribute
     */
    public void setRoleAttribute(String roleAttribute)
    {
        this.roleAttribute = roleAttribute;
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     */
    public void addHierarchy(UserHierarchyDTO aHierarchy)
    {
        if (hierarchyCache.contains(aHierarchy))
        {
            hierarchyCache.remove(aHierarchy);
        }
        hierarchyCache.addElement(aHierarchy);
        if (aHierarchy.isDefault())
        {
            if (defaultHierarchy != null)
            {
                UserHierarchyDTO oldDefault = getHierarchy(defaultHierarchy);
            }
            defaultHierarchy = aHierarchy.getDefaultHierarchy();
        }
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     *
     * @return
     */
    public UserHierarchyDTO getHierarchy(HierarchyDTO[] aHierarchy)
    {
        if (userHierarchies != null)
        {
            for (short i = 0; i < userHierarchies.length; i++)
            {
                if (userHierarchies[i].equals(aHierarchy))
                {
                    return userHierarchies[i];
                }
            }
        }
        if (hierarchyCache == null)
        {
            return null;
        }
        Enumeration vEnum = hierarchyCache.elements();

        while (vEnum.hasMoreElements())
        {
            UserHierarchyDTO userHier =
                    (UserHierarchyDTO) vEnum.nextElement();
            String str = userHier.getProgramTypes();

            if (userHier.equals(aHierarchy))
            {
                return userHier;
            }
        }
        return null;
    }

}
