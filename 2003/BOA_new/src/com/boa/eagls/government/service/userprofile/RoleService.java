/**
 * RoleService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.service.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.dto.user.*;

import java.util.*;
import java.sql.*;

import com.boa.eagls.government.service.userprofile.UserAccountService;

/**
 * <p>Role Service </p>
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class RoleService extends ServiceBase
{
    private RoleDAO roleAdapter;    // database adapter
    private UserRoleDTO roleDTO;
    private HierarchyService hierarchyService = null;
    private UserAccountService accountService = null;
    public final static short ADD = 0;
    public final static short REMOVE = 1;

    /**
     * This method is a simple constructor for this class.
     */
    public RoleService(Connection connection)
    {
        setConnection(connection);
        roleAdapter = new RoleDAO(getConnection());
        hierarchyService =
                new HierarchyService(getConnection());    // (IHierarchyService)ServiceFactory.getInstance("com.boa.eagls.government.service.userprofile.HierarchyService");
        accountService =
                new UserAccountService(getConnection());    // (IUserAccountService)ServiceFactory.getInstance("com.boa.eagls.government.service.userprofile.UserAccountService");
        roleDTO = new UserRoleDTO();
        roleDTO.setAccountCache(new Hashtable());
        roleDTO.setHierarchyCache(new Vector());
    }

    // copy constructor

    /**
     * Method declaration
     *
     */
    public void create()
    {

        // this.roleDTO = userRole;
        setStateNew();
    }

    /**
     * Method declaration
     *
     *
     * @param reportsAccess
     *
     * @exception NBException
     */
    public void delete(boolean reportsAccess) throws NBException
    {

        // NOT IMPLEMENTED YET.
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param role
     *
     * @exception NBException
     */
    public void load(String userID, String role) throws NBException
    {
        RoleDAO roleDAO = new RoleDAO(getConnection());

        try
        {
            this.roleDTO = roleDAO.loadUserRole(userID, role);
        }
        catch (EAGLSException ex1)
        {
            throw new NBError(ex1.getMessage());
        }
        catch (DataConnException ex1)
        {
            throw new NBError(ex1.getMessage());
        }
        catch (NBException ex1)
        {
            throw new NBError(ex1.getMessage());
        }
        setStateCurrent();
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param urDTO
     *
     * @exception NBException
     */
    public void save(String userID, UserRoleDTO urDTO) throws NBException
    {

        roleAdapter.setConnection(getConnection());
        hierarchyService.setConnection(getConnection());
        accountService.setConnection(getConnection());
        validate();
        if (getStateCurrent() == BO_STATE_NEW)
        {

            roleAdapter.createUserRole(urDTO);

        }
        else if (getStateCurrent() == BO_STATE_DIRTY)
        {
            roleAdapter.saveUserRole(urDTO);
        }

        // Tell any cached hierarchy objects to save themselves
        if (urDTO.getHierarchyCache() != null)
        {
            Enumeration vEnum = urDTO.getHierarchyCache().elements();

            while (vEnum.hasMoreElements())
            {
                UserHierarchyDTO uhDTO =
                        (UserHierarchyDTO) vEnum.nextElement();

                hierarchyService.save(uhDTO, userID);
            }
        }
        if (urDTO.getUserHierarchies() != null)
        {
            UserHierarchyDTO[] hdtos = urDTO.getUserHierarchies();

            for (short i = 0; i < hdtos.length; i++)
            {
                hierarchyService.save(hdtos[i], userID);
            }
        }

        // Tell any cached individualaccount objects to save themselves
        if (urDTO.getAccountCache() != null)
        {
            Enumeration enum = urDTO.getAccountCache().keys();
            Hashtable htable = urDTO.getAccountCache();

            while (enum.hasMoreElements())
            {
                String key = (String) enum.nextElement();
                UserAccountDTO accDTO = (UserAccountDTO) htable.get(key);

                accountService.save(accDTO);
            }
        }

        // --setStateCurrent();

    }

    /**
     * Method declaration
     *
     */
    public void validate()
    {

        // NOT IMPLEMENTED.
    }

    /**
     * Validate the user role already exists in the valid_roles table
     * @param   role   the role being validated
     */
    public boolean validateUserRole(String role)
    {
        return roleAdapter.validateUserRole(role);
    }

    // Edit Methods

    /**
     * Method declaration
     *
     *
     * @param isDefault
     */
    public void changeDefault(boolean isDefault)
    {
        roleDTO.setDefault(isDefault);
        setStateDirty();
    }

    // Accessor Methods

    /**
     * Method declaration
     *
     *
     * @param isDefault
     */
    public void setDefault(boolean isDefault)
    {
        roleDTO.setDefault(isDefault);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public boolean isDefault()
    {
        return roleDTO.isDefault();
    }

    /**
     * Method declaration
     *
     *
     * @param aUserID
     */
    public void setUserID(String aUserID)
    {
        roleDTO.setUserID(validateString(aUserID));
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getUserID()
    {
        return new String(roleDTO.getUserID());
    }

    /**
     * This method returns the name of this user role.
     * @return          a String containing this object's role name
     */
    public String getRoleName()
    {
        return new String(roleDTO.getRoleName());
    }

    /**
     * This method sets the role name of this object.
     * @param          aRoleName - the new name for this user role
     */
    public void setRoleName(String aRoleName)
    {
        roleDTO.setRoleName(validateString(aRoleName));
    }

    /**
     * This method gets the core role name for this role.
     * @return         a String containing this role's core role name
     */
    public String getCoreRoleName()
    {
        return new String(roleDTO.getCoreRoleName());
    }

    /**
     * This method sets the core role name for this orle.
     * @param          aRoleName - the core role name for this user role
     */
    public void setCoreRoleName(String aRoleName)
    {
        roleDTO.setCoreRoleName(validateString(aRoleName));
    }

    /**
     * This method gets the add or remove functions flag for this user role.
     * @return     true    addFunctions
     * @return     false   removeFunctions
     */
    public short getAddRemoveFunctionsFlag()
    {
        return roleDTO.getAddRemoveFunctionsFlag();
    }

    /**
     * This method sets the add or remove functions flag for this user role.
     * @param      true    addFunctions
     * @param      false   removeFunctions
     */
    public void setAddRemoveFunctionsFlag(short anAddRemoveFunctionsFlag)
    {
        roleDTO.setAddRemoveFunctionsFlag(anAddRemoveFunctionsFlag);
    }

    /**
     * This method gets the access flag for this user role.
     * @return     a String eg: AH, PC,GSA
     */
    public String getAccessFlag()
    {
        return new String(roleDTO.getAccessFlag());
    }

    /**
     * This method sets the access flag for this user role.
     * @param      an access flag
     */
    public void setAccessFlag(String anAccessFlag)
    {
        roleDTO.setAccessFlag(validateString(anAccessFlag));
    }

    /**
     * This method gets the new role Description for this user role.
     * @return         the new role description
     */
    public String getRoleDescription()
    {
        return new String(roleDTO.getRoleDescription());
    }

    /**
     * This method sets the new role description for this user role.
     * @param          a Description for the new role created
     */
    public void setRoleDescription(String aDescription)
    {
        roleDTO.setRoleDescription(validateString(aDescription));
    }

    /**
     * This method gets all the roleFunctions for this role
     * @return         a list of roleFunctions  eg: changeAccount, changeHierarchy
     */
    public String[] getRoleFunctions()
    {
        return roleDTO.getRoleFunctions();
    }

    /**
     * Method declaration
     *
     *
     * @param iFunctions
     */
    public void setRoleFunctions(String[] iFunctions)
    {
        roleDTO.setRoleFunctions(iFunctions);
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     * @param isDefault
     *
     * @exception NBException
     */
    public void addHierarchy(HierarchyDTO[] aHierarchy,
                             boolean isDefault) throws NBException
    {

        // IHierarchyService hierarchyService = (IHierarchyService)ServiceFactory.getInstance("com.boa.eagls.government.service.userprofile.HierarchyService");
        HierarchyService hierarchyService =
                new HierarchyService(getConnection());
        UserHierarchyDTO userHierarchy = new UserHierarchyDTO();

        // hierarchyService.create(userHierarchy);
        Vector hc = roleDTO.getHierarchyCache();

        hc.addElement(userHierarchy);
        roleDTO.setHierarchyCache(hc);
        if (isDefault)
        {
            if (roleDTO.getDefaultHierarchy() != null)
            {
                UserHierarchyDTO oldDefault =
                        getHierarchy(roleDTO.getDefaultHierarchy());
            }
            roleDTO.setDefaultHierarchy(aHierarchy);
        }
    }

    /**
     * Method declaration
     *
     *
     * @param accountDTO
     *
     * @exception DataConnException
     * @exception NBException
     */
    public void addAccount(UserAccountDTO accountDTO)
            throws DataConnException, NBException
    {

        // UserAccount individualaccount = new UserAccount(transport);
        // individualaccount.create(userID, roleName, anAccountNum, isDefault);
        roleDTO.addToAccountCache(accountDTO.getAccountNumber(), accountDTO);

        // accountCache.put(anAccountNum, individualaccount);
        if (accountDTO.isDefault())
        {
            if (accountDTO.getDefaultAccount() != null)
            {
                UserAccountDTO oldDefault =
                        getAccount(roleDTO.getDefaultAccount());
            }
            roleDTO.setDefaultAccount(accountDTO.getAccountNumber());
        }
    }

    /**
     * Method declaration
     *
     *
     * @param anAccountNum
     *
     * @return
     *
     * @exception NBException
     */
    public UserAccountDTO getAccount(String anAccountNum) throws NBException
    {
        UserAccountDTO account;

        // Check if individualaccount is loaded already.
        account =
                (UserAccountDTO) roleDTO.getAccountCache().get(anAccountNum);
        if (account != null)
        {
            return account;
        }

        // individualaccount is not loaded yet; load it from storage.
        account =
                new UserAccountDTO();    // (UserAccountDTO)DTOFactory.getInstance("com.boa.eagls.government.dto.user.UserAccountDTO");
        account.setUserID(this.getUserID());
        account.setRoleName(this.getRoleName());
        account.setAccountNumber(anAccountNum);
        roleDTO.getAccountCache().put(anAccountNum, account);
        return account;
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     *
     * @return
     *
     * @exception NBException
     */
    public UserHierarchyDTO getHierarchy(HierarchyDTO[] aHierarchy)
            throws NBException
    {
        if (roleDTO.getUserHierarchies() != null)
        {
            UserHierarchyDTO[] hd = roleDTO.getUserHierarchies();

            for (short i = 0; i < hd.length; i++)
            {
                if (hd[i].equals(aHierarchy))
                {
                    return hd[i];
                }
            }
        }
        if (roleDTO.getHierarchyCache() == null)
        {
            return null;
        }
        Enumeration vEnum = roleDTO.getHierarchyCache().elements();

        while (vEnum.hasMoreElements())
        {
            UserHierarchyDTO userHier =
                    (UserHierarchyDTO) vEnum.nextElement();

            if (userHier.equals(aHierarchy))
            {
                return userHier;
            }
        }
        return null;
    }

    /**
     * Method declaration
     *
     *
     * @param defaultAccount
     */
    public void setDefaultAccount(String defaultAccount)
    {
        roleDTO.setDefaultAccount(defaultAccount);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getDefaultAccount()
    {
        return roleDTO.getDefaultAccount();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserAccountDTO[] getUserAccounts()
    {
        return roleDTO.getUserAccounts();
    }

    /**
     * Method declaration
     *
     *
     * @param userAccounts
     */
    public void setUserAccounts(UserAccountDTO[] userAccounts)
    {
        roleDTO.setUserAccounts(userAccounts);
    }

    /**
     * Method declaration
     *
     *
     * @param aHierarchy
     */
    public void setDefaultHierarchy(HierarchyDTO[] aHierarchy)
    {
        HierarchyDTO[] defaultHierarchy = new HierarchyDTO[9];

        for (short i = 0; i < 9; i++)
        {
            defaultHierarchy[i] = new HierarchyDTO();
            defaultHierarchy[i].setLevel(i);
            defaultHierarchy[i].setNumber(aHierarchy[i].getNumber());
            defaultHierarchy[i]
                    .setDescription(aHierarchy[i].getDescription());
            defaultHierarchy[i].setAgencyName(aHierarchy[i].getAgencyName());
        }
        roleDTO.setDefaultHierarchy(defaultHierarchy);
    }

    /**
     * Method declaration
     *
     *
     * @param userHierarchy
     */
    public void setUserHierarchy(UserHierarchyDTO[] userHierarchy)
    {
        roleDTO.setUserHierarchies(userHierarchy);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserHierarchyDTO[] getNewUserHierarchies()
    {
        UserHierarchyDTO[] h =
                new UserHierarchyDTO[roleDTO.getHierarchyCache().size()];

        roleDTO.getHierarchyCache().copyInto(h);
        return h;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getHierarchy()
    {
        return roleDTO.getHierarchy();
    }

    /**
     * Method declaration
     *
     *
     * @param tHierarchy
     */
    public void setHierarchy(HierarchyDTO[] tHierarchy)
    {
        roleDTO.setHierarchy(tHierarchy);
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyDTO[] getDefaultHierarchy()
    {
        return roleDTO.getDefaultHierarchy();
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String getRoleAttribute()
    {
        return roleDTO.getRoleAttribute();
    }

    /**
     * Method declaration
     *
     *
     * @param roleAttribute
     */
    public void setRoleAttribute(String roleAttribute)
    {
        roleDTO.setRoleAttribute(roleAttribute);
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchyService
     */
    public void setHierarchyService(HierarchyService hierarchyService)
    {
        roleDTO.addHierarchy(hierarchyService.getHierarchyDTO());
        this.hierarchyService = hierarchyService;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public HierarchyService getHierarchyService()
    {
        return this.hierarchyService;
    }

    /**
     * Method declaration
     *
     *
     * @param roleDTO
     */
    public void setRoleDTO(UserRoleDTO roleDTO)
    {
        this.roleDTO = roleDTO;
    }

    /**
     * Method declaration
     *
     *
     * @param userID
     * @param aRoleName
     *
     * @return
     *
     * @exception NBError
     */
    public UserRoleDTO getRole(String userID, String aRoleName) throws NBError
    {
        try
        {
            roleDTO = roleAdapter.loadUserRole(userID, aRoleName);
        }
        catch (EAGLSException ex)
        {
            throw new NBError(ex.getMessage());
        }
        catch (DataConnException ex)
        {
            throw new NBError(ex.getMessage());
        }
        catch (NBException ex)
        {
            throw new NBError(ex.getMessage());
        }
        return roleDTO;
    }

    /**
     * Method declaration
     *
     *
     * @param accountService
     */
    public void setAccountService(UserAccountService accountService)
    {
        roleDTO
                .addToAccountCache(accountService.getUserAccountDTO()
                .getAccountNumber(), accountService.getUserAccountDTO());
        this.accountService = accountService;
    }

    /**
     * Method declaration
     *
     *
     * @param userId
     * @param roleName
     * @param isDefault
     */
    public void populateRole(String userId, String roleName,
                             boolean isDefault)
    {
        roleDTO = new UserRoleDTO();
        roleDTO.setUserID(userId);
        roleDTO.setRoleName(roleName);
        roleDTO.setDefault(isDefault);
    }

    /**
     * Method declaration
     *
     *
     * @param aRoleDTO
     */
    public void populateRole(UserRoleDTO aRoleDTO)
    {
        roleDTO = new UserRoleDTO();
        roleDTO.setUserID(aRoleDTO.getUserID());
        roleDTO.setRoleName(aRoleDTO.getRoleName());
        roleDTO.setDefault(aRoleDTO.isDefault());
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public UserRoleDTO getRoleDTO()
    {
        return roleDTO;
    }

    /**
     * Method declaration
     *
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBError
     * @exception NBException
     * @exception NoDataFoundException
     */
    public String[] getAllRoles()
            throws NoDataFoundException, NBError, EAGLSException,
            DataConnException, NBException
    {
        UserProfileDAO userProfileDAO = new UserProfileDAO(getConnection());

        try
        {
            return userProfileDAO.getAllRoles();
        }
        catch (SQLException ex)
        {
            throw new NBError(ex.getMessage());
        }
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public String[] getAllBaseRoles()
    {
        UserProfileDAO userProfileDAO = new UserProfileDAO(getConnection());

        return userProfileDAO.getAllBaseRoles();
    }

}
