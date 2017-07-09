/**
 * UserProfileDTO
 */
package com.boa.eagls.government.dto.user;

import java.util.Hashtable;

import com.boa.eagls.government.dto.*;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class UserProfileDTO extends DTOBase
{
    private String userID = STRING_DEFAULT;       // user's unique ID
    private String lastName = STRING_DEFAULT;     // user's last name
    private String firstName = STRING_DEFAULT;    // user's first name
    private String status = STRING_DEFAULT;       // status of this user
    private String setupStatus =
            STRING_DEFAULT;					 // status of this user during setup
    private boolean hasReportsAccess =
            false;						 // indicator of userID having reports Access
    private String clusterName =
            STRING_DEFAULT;					 // cluster Name is the user has Access to Reports
    private String defaultRoleName =
            STRING_DEFAULT;					 // this user's default role
    private String[] roleNames = STRING_ARRAY_DEFAULT;
    private Hashtable roleCache;			 // container for this user's roles
    private PasswordDTO password;			 // user's password
    private UserRoleDTO[] userRoles;
    private String accountNumber;
    private String hierarchyNumber;
    private String role;

    /**
     * Constructor declaration
     *
     */
    public UserProfileDTO()
    {
        roleCache = new Hashtable();
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
     * @param lastName
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
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
     * @param firstName
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
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
     * @param status
     */
    public void setStatus(String status)
    {
        this.status = status;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getSetupStatus()
    {
        return setupStatus;
    }

    /**
     * Method declaration
     *
     *
     * @param setupStatus
     */
    public void setSetupStatus(String setupStatus)
    {
        this.setupStatus = setupStatus;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean hasReportsAccess()
    {
        return hasReportsAccess;
    }

    /**
     * Method declaration
     *
     *
     * @param hasReportsAccess
     */
    public void setHasReportsAccess(boolean hasReportsAccess)
    {
        this.hasReportsAccess = hasReportsAccess;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getClusterName()
    {
        return clusterName;
    }

    /**
     * Method declaration
     *
     *
     * @param clusterName
     */
    public void setClusterName(String clusterName)
    {
        this.clusterName = clusterName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDefaultRoleName()
    {
        return defaultRoleName;
    }

    /**
     * Method declaration
     *
     *
     * @param defaultRoleName
     */
    public void setDefaultRoleName(String defaultRoleName)
    {
        this.defaultRoleName = defaultRoleName;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getRoleNames()
    {
        return roleNames;
    }

    /**
     * Method declaration
     *
     *
     * @param roleNames
     */
    public void setRoleNames(String[] roleNames)
    {
        this.roleNames = roleNames;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public Hashtable getRoleCache()
    {
        return roleCache;
    }

    /**
     * Method declaration
     *
     *
     * @param roleCache
     */
    public void setRoleCache(Hashtable roleCache)
    {
        this.roleCache = roleCache;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public PasswordDTO getPassword()
    {
        return password;
    }

    /**
     * Method declaration
     *
     *
     * @param password
     */
    public void setPassword(PasswordDTO password)
    {
        this.password = password;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserRoleDTO[] getUserRoles()
    {
        return userRoles;
    }

    /**
     * Method declaration
     *
     *
     * @param userRoles
     */
    public void setUserRoles(UserRoleDTO[] userRoles)
    {
        this.userRoles = userRoles;
    }

    /*
     * public void addRoleToCache(UserRoleDTO role) {
     * roleCache.put(role.getRoleName(), role);
     * }
     */

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
    public String getRole()
    {
        return role;
    }

    /**
     * Method declaration
     *
     *
     * @param role
     */
    public void setRole(String role)
    {
        this.role = role;
    }

    /**
     * Method declaration
     *
     *
     * @param aRoleName
     *
     * @return
     */
    public UserRoleDTO getRole(String aRoleName)
    {
        UserRoleDTO role = (UserRoleDTO) roleCache.get(aRoleName);

        if (role != null)
        {
            return role;
        }
        return null;
    }

    /**
     * Method declaration
     *
     *
     * @param dto
     */
    public void addRole(UserRoleDTO dto)
    {
        roleCache.put(dto.getRoleName(), dto);
        if (dto.isDefault())
        {

            // TODO is this necessary since stored procedure will reset old default role.
            if (!((defaultRoleName == null) || (defaultRoleName.equals(""))))
            {
                UserRoleDTO oldDefault = getRole(defaultRoleName);

                oldDefault.setDefault(false);
            }
            defaultRoleName = dto.getRoleName();
        }
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isHasReportsAccess()
    {
        return hasReportsAccess;
    }

}
