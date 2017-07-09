/**
 * UserProfileService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.service.ServiceBase;
import com.boa.eagls.government.dao.*;

import java.util.*;
import java.sql.*;

import com.boa.eagls.government.util.*;
import com.boa.eagls.government.dao.IConnectionService;
import org.apache.log4j.*;

/**
 * User Profile Service - Concrete Class
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class UserProfileService extends ServiceBase
{
    UserProfileDTO userProfile;
    UserProfileDAO userProfileDAO;
    RoleService roleService;
    ReportsService reportsService = null;
    HierarchyService hierarchyService;
    PasswordService passwordService;
    UserAccountService accountService;


    private static final Logger LOGGER = Logger.getLogger("com.boa.eagls.government.service.userprofile.UserProfileservice");

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public UserProfileService(java.sql.Connection connection)
    {

        LOGGER.debug("Entering in constructor of UserProfileService .....");
        setConnection(connection);
        reportsService = new ReportsService(getConnection());
        userProfile = new UserProfileDTO();
        userProfileDAO = new UserProfileDAO(getConnection());
        roleService = new RoleService(getConnection());
        hierarchyService = new HierarchyService(getConnection());
        passwordService = new PasswordService(getConnection());
        accountService = new UserAccountService(getConnection());

        LOGGER.debug("Exiting from constructor of UserProfileService .....");
    }

    /**
     * create
     * Creates a new user profile.
     *
     * @exception NBException
     */
    public void create() throws NBException
    {
        LOGGER.debug("Entering in create() of UserProfileService .....");
        setStateNew();
        LOGGER.debug("Exiting from create() of UserProfileService .....");
    }

    /**
     * assignRole
     * Assigns role to a user profile.
     *
     * @param roleDTO
     *
     * @exception NBException
     */
    public void assignRole(UserRoleDTO roleDTO) throws NBException
    {
        LOGGER.debug("Entering in assignRole() of UserProfileService .....");
        addRole(roleDTO);
        LOGGER.debug("Exiting from assignRole() of UserProfileService .....");
    }

    /**
     * load
     * Loads a user profile from the persistance layer.
     *
     * @param UserID
     * @param currentUserID
     *
     * @exception NBException
     */
    public void load(String UserID, String currentUserID) throws NBException
    {
        LOGGER.debug("Entering in load() of UserProfileService .....");
        try
        {
            if ((UserID != null) && (currentUserID != null))
            {
                if (!UserID.equalsIgnoreCase(currentUserID))
                {
                    // userProfileDAO.compareHierarchy(UserID);
                    throw new NBError("Never Thrown");
                }
            }
        }
        catch (NBException e)
        {
            if (e.getSeverity() == Messages.APP_W0006_Severity)
            {
                NBError _nbError = new NBError(Messages.APP_W0006,
                        Messages.APP_W0006_Severity);

                _nbError.setDetailedMessage("User Profile Authorization");
                throw _nbError;
            }
            ;
        }
        ;
        try
        {
            this.userProfile = userProfileDAO.loadUserProfile(UserID);
        }
        catch (NBException ex)
        {
            throw new NBError(ex.getMessage());
        }
        catch (SQLException ex)
        {
            throw new NBError(ex.getMessage());
        }
        setStateCurrent();

        LOGGER.debug("Exiting from load() of UserProfileService .....");
    }

    /**
     * save
     * Saves a user profile by requesting the DAO.
     *
     * @param sessionUserID
     *
     * @exception NBException
     */
    public void save(String sessionUserID) throws Exception
    {
        LOGGER.debug("Entering in save() of UserProfileService .....");
        // ////////////////////// connection work starts here////////////////////////
        IConnectionService connectionService = null;

        try
        {
            connectionService =
                    (IConnectionService) ConnectionFactory.getInstance();


            connectionService.setAutoCommit(false);
            setConnection(connectionService.getConnection());
            userProfileDAO.setConnection(getConnection());
            reportsService.setConnection(getConnection());

            // hierarchyService = roleService.getHierarchyService();
            // /////////////////////////////////////// connection work ends here////////////////
            validate();
            String initialPwd = PasswordService.getInitialPassword();

            if (getStateCurrent() == BO_STATE_NEW)
            {
                userProfileDAO
                        .createUserProfile(this
                        .userProfile, PasswordService
                        .encryptPassword(initialPwd), initialPwd, userProfile
                        .getUserID());
            }
            else if (roleService.getStateCurrent() == BO_STATE_DIRTY)
            {
                userProfileDAO.saveUserProfile(this.userProfile);
            }
            boolean isFirstEntry = (getStateCurrent()
                    == BO_STATE_NEW);
            boolean ahRoleOnly =
                    true;    // This is used to check if ReportsAccess is allowed for only AH roles, then no Reports priveledges.
            CrystalTransaction ctrans = new CrystalTransaction();
            Vector ctv = new Vector();
            Enumeration enum = userProfile.getRoleCache().elements();

            while (enum.hasMoreElements())
            {
                UserRoleDTO role = (UserRoleDTO) enum.nextElement();
                String roleName = role.getRoleName();
                String baseRoleName = null;

                baseRoleName = userProfileDAO.getBaseRole(roleName);
                if (userProfile.hasReportsAccess())
                {			   // only do the following if the user has reports Access

                    // Special Case: GCSU
                    if (baseRoleName.equals("GCSU"))
                    {
                        if (isFirstEntry)
                        {
                            ctrans =
                                    new CrystalTransaction(this.getUserID(),
                                            roleName,
                                            this.getFirstName(),
                                            this.getLastName());
                            ctrans
                                    .setPassword(PasswordService
                                    .retrieveReportsPassword(this
                                    .getUserID()));
                            ctrans.setTransactionType("AU");
                            ctv.addElement(ctrans);
                            isFirstEntry = false;
                            ahRoleOnly = false;

                            // Create an oracle password when the user gets access to reports
                            PasswordService
                                    .setOraclePassword(this.getUserID(),
                                            initialPwd);
                        }
                        ctrans = new CrystalTransaction(this.getUserID(),
                                roleName,
                                this.getFirstName(),
                                this.getLastName());
                        ctrans.setUserRole(roleName);
                        ctrans.setTransactionType("AUG");
                        ctv.addElement(ctrans);
                        ahRoleOnly = false;
                    }			   // Special Case: AH roles
                    else if (baseRoleName.equals("AH")
                            || (baseRoleName.equals("NFC"))
                            || (baseRoleName.equals("A"))
                            || (baseRoleName.equals("TC")))
                    {
                    }
                    else
                    {

                        // Traverse the new hierarchies for this role.
                        // --UserHierarchyDTO[] hierarchies = hierarchyService.getNewUserHierarchies();
                        UserHierarchyDTO[] hierarchies =
                                roleService.getNewUserHierarchies();

                        for (short i = 0; i < hierarchies.length; i++)
                        {

                            // if (hierarchyService.getState hierarchies[i].getStateCurrent() == BO_STATE_NEW) {
                            if (((HierarchyService) hierarchyService)
                                    .getStateCurrent() == BO_STATE_NEW)
                            {
                                if (isFirstEntry)
                                {
                                    ctrans =
                                            new CrystalTransaction(this
                                            .getUserID(), roleName, this
                                            .getFirstName(), this
                                            .getLastName());
                                    ctrans
                                            .setPassword(PasswordService
                                            .retrieveReportsPassword(this
                                            .getUserID()));
                                    ctrans
                                            .setHierarchy(hierarchies[i]
                                            .getHierarchy());
                                    ctrans.setTransactionType("AU");
                                    ctv.addElement(ctrans);
                                    isFirstEntry = false;
                                    ahRoleOnly = false;
                                    PasswordService
                                            .setOraclePassword(this.getUserID(),
                                                    initialPwd);
                                }
                                ctrans =
                                        new CrystalTransaction(this
                                        .getUserID(), roleName, this
                                        .getFirstName(), this
                                        .getLastName());
                                ctrans
                                        .setHierarchy(hierarchies[i]
                                        .getHierarchy());
                                ctrans.setTransactionType("AUG");
                                ctv.addElement(ctrans);
                                ahRoleOnly = false;
                            }
                        }
                    }
                }
                roleService.setConnection(getConnection());
                roleService.save(this.getUserID(), role);
            }
            if ((userProfile.hasReportsAccess()) & !(ahRoleOnly))
            {				   // only do the following if the user has Reports access
                CrystalTransaction[] ctransTable =
                        new CrystalTransaction[ctv.size()];

                ctv.copyInto(ctransTable);
                setStateCurrent();
                for (int k = 0; k < ctransTable.length; k++)
                {
                    reportsService.addTransaction(ctransTable[k],
                            sessionUserID);

                }
            }
            connectionService.commit();
        }
        catch (InstantiationException ex)
        {
            connectionService.rollback();
            connectionService.close();
            throw new NBError(ex.getMessage());
        }
        catch (IllegalAccessException ex)
        {
            connectionService.rollback();
            connectionService.close();
            throw new NBError(ex.getMessage());
        }
        catch (ClassNotFoundException ex)
        {
            connectionService.rollback();
            connectionService.close();
            throw new NBError(ex.getMessage());
        }
        catch (NBException ex)
        {
            connectionService.rollback();
            connectionService.close();
            throw ex;
        }
        catch (Exception ex)
        {
            connectionService.rollback();
            connectionService.close();
            throw ex;
        }
        if ((null != connectionService.getConnection())
                && (!connectionService.isClosed()))
        {
            connectionService.close();
        }

        LOGGER.debug("Exiting from save() of UserProfileService .....");
    }

    /**
     * validate
     *
     *
     * @exception NBException
     */
    public void validate() throws NBException
    {
        LOGGER.debug("Entering in validate() of UserProfileService .....");
        // NOT IMPLEMENTED.
        LOGGER.debug("Exiting from validate() of UserProfileService .....");
    }

    /**
     * changeDefaultRole
     *
     *
     * @param userID
     * @param aRoleName
     *
     * @exception NBException
     */
    public void changeDefaultRole(String userID,
                                  String aRoleName) throws NBException
    {
        LOGGER.debug("Entering in changeDefaultRole() of UserProfileService .....");
        this.setDefaultRoleName(aRoleName);
        LOGGER.debug("Exiting from changeDefaultRole() of UserProfileService .....");
    }

    /**
     * addRole
     * Adds role to user profile.
     *
     * @param role
     *
     * @exception NBException
     */
    public void addRole(UserRoleDTO role) throws NBException
    {
        LOGGER.debug("Entering in addRole() of UserProfileService .....");
        roleService.setRoleDTO(role);
        roleService.create();

        // --userProfile.addRoleToCache(role);
        userProfile.addRole(role);
        if (role.isDefault())
        {

            // TODO is this necessary since stored procedure will reset old default role.
            if (!((userProfile.getDefaultRoleName() == null)
                    || (userProfile.getDefaultRoleName().equals(""))))
            {
                UserRoleDTO oldDefault =
                        getUserRole(userProfile.getDefaultRoleName());

                oldDefault.setDefault(false);
            }
            this.setDefaultRoleName(role.getRoleName());
        }
        LOGGER.debug("Exiting from addRole() of UserProfileService .....");
    }

    /**
     * setRoleNames
     *
     *
     * @param tRoleNames
     */
    public void setRoleNames(String[] tRoleNames)
    {
        LOGGER.debug("Entering in setRoleNames() of UserProfileService .....");
        userProfile.setRoleNames(tRoleNames);
        LOGGER.debug("Exiting from setRoleNames() of UserProfileService .....");
    }

    /**
     * getRoleNames
     *
     *
     * @return
     */
    public String[] getRoleNames()
    {
        LOGGER.debug("Entering in getRoleNames() of UserProfileService .....");

        String[] returnValue = null;

        if (userProfile.getRoleNames() != null)
        {
            returnValue = (String[]) userProfile.getRoleNames().clone();
        }

        LOGGER.debug("Exiting from getRoleNames() of UserProfileService .....");

        return returnValue;
    }

    /**
     * getUserRoles
     *
     *
     * @return
     *
     * @exception NBException
     */
    public UserRoleDTO[] getUserRoles() throws NBException
    {
        LOGGER.debug("Entering in getUserRoles() of UserProfileService .....");
        return userProfile.getUserRoles();
    }

    /**
     * setUserRoles
     *
     *
     * @param roles
     *
     * @exception NBException
     */
    public void setUserRoles(UserRoleDTO[] roles) throws NBException
    {
        LOGGER.debug("Entering in setUserRoles() of UserProfileService .....");

        if (roles != null)
        {
            userProfile.setUserRoles(roles);
        }
        else
        {
            userProfile.setUserRoles(new UserRoleDTO[]
            {
                new UserRoleDTO()
            });
        }

        LOGGER.debug("Exiting from setUserRoles() of UserProfileService .....");
    }

    /**
     * getUserRole
     * Gets user role from the cache,
     * if not present then loads it from the database.
     *
     * @param aRoleName
     *
     * @return
     *
     * @exception NBException
     */
    public UserRoleDTO getUserRole(String aRoleName) throws NBException
    {

        LOGGER.debug("Entering in getUserRole() of UserProfileService .....");

        UserRoleDTO role;

        // Check if role is loaded already.
        role = (UserRoleDTO) userProfile.getRoleCache().get(aRoleName);
        if (role != null)
        {
            return role;
        }

        // Role is not loaded yet; load it from storage.
        roleService.load(getUserID(), role.getRoleName());
        userProfile.addRole(role);

        LOGGER.debug("Exiting from getUserRole() of UserProfileService .....");
        return role;
    }

    /**
     * assignHierarchy
     *
     *
     * @param aRole
     * @param hierarchy
     *
     * @exception NBException
     */
    public void assignHierarchy(String aRole, UserHierarchyDTO[] hierarchy)
            throws NBException
    {
        LOGGER.debug("Entering in assignHierarchy() of UserProfileService .....");
        // NOT IMPLEMENTED.
        LOGGER.debug("Exiting from assignHierarchy() of UserProfileService .....");
    }

    /**
     * assignAccount
     * Creates individualaccount for the user profile.
     *
     * @param account
     *
     * @exception NBException
     */
    public void assignAccount(UserAccountDTO account) throws NBException
    {
        LOGGER.debug("Entering in assignAccount() of UserProfileService .....");
        userProfileDAO.createUserAccount(account);
        LOGGER.debug("Exiting from assignAccount() of UserProfileService .....");
    }

    /**
     * getPassword
     * Gets the password of the user profile.
     *
     * @return
     *
     * @exception NBException
     */
    public PasswordDTO getPassword() throws NBException
    {
        LOGGER.debug("Entering in getPassword() of UserProfileService .....");

        if (getPassword() == null)
        {
            userProfile.setPassword(new PasswordDTO());    // this is temporary
            passwordService.load(userProfile.getUserID());
        }

        LOGGER.debug("Exiting from getPassword() of UserProfileService .....");
        return userProfile.getPassword();
    }

    /**
     * getDefaultRoleName
     *
     *
     * @return
     *
     * @exception NBException
     */
    public String getDefaultRoleName() throws NBException
    {
        LOGGER.debug("Entering in getDefaultRoleName() of UserProfileService .....");
        return userProfile.getDefaultRoleName();
    }

    /**
     * setDefaultRoleName
     *
     *
     * @param aRoleName
     */
    public void setDefaultRoleName(String aRoleName)
    {
        LOGGER.debug("Entering in setDefaultRoleName() of UserProfileService .....");
        userProfile.setDefaultRoleName(aRoleName);
        LOGGER.debug("Exiting from setDefaultRoleName() of UserProfileService .....");
    }

    /**
     * getUserID
     *
     *
     * @return
     */
    public String getUserID()
    {
        LOGGER.debug("Entering in getUserID() of UserProfileService .....");
        return userProfile.getUserID();
    }

    /**
     * setUserID
     *
     *
     * @param aUserId
     */
    public void setUserID(String aUserId)
    {
        LOGGER.debug("Entering in setUserID() of UserProfileService .....");
        userProfile.setUserID(aUserId);
        LOGGER.debug("Exiting from setUserID() of UserProfileService .....");
    }

    /**
     * getLastName
     *
     *
     * @return
     */
    public String getLastName()
    {
        LOGGER.debug("Entering in getLastName() of UserProfileService .....");
        return userProfile.getLastName();
    }

    /**
     * setLastName
     *
     *
     * @param newLastName
     */
    public void setLastName(String newLastName)
    {
        LOGGER.debug("Entering in setLastName() of UserProfileService .....");
        userProfile.setLastName(newLastName);
        LOGGER.debug("Exiting from setLastName() of UserProfileService .....");
    }

    /**
     * getFirstName
     *
     *
     * @return
     */
    public String getFirstName()
    {
        LOGGER.debug("Entering in getFirstName() of UserProfileService .....");
        return userProfile.getFirstName();
    }

    /**
     * setFirstName
     *
     *
     * @param newFirstName
     */
    public void setFirstName(String newFirstName)
    {
        LOGGER.debug("Entering in setFirstName() of UserProfileService .....");
        userProfile.setFirstName(newFirstName);
    }

    /**
     * getStatus
     *
     *
     * @return
     */
    public String getStatus()
    {
        LOGGER.debug("Entering in getStatus() of UserProfileService .....");
        return userProfile.getStatus();
    }

    /**
     * setStatus
     *
     *
     * @param aStatus
     */
    public void setStatus(String aStatus)
    {
        LOGGER.debug("Entering in setStatus() of UserProfileService .....");
        userProfile.setStatus(aStatus);
    }

    /**
     * hasReportsAccess
     *
     *
     * @return
     */
    public boolean hasReportsAccess()
    {
        LOGGER.debug("Entering in hasReportsAccess() of UserProfileService .....");
        return userProfile.hasReportsAccess();
    }

    /**
     * setHasReportsAccess
     *
     *
     * @param reportsAccess
     */
    public void setHasReportsAccess(boolean reportsAccess)
    {
        LOGGER.debug("Entering in setHasReportsAccess() of UserProfileService .....");
        userProfile.setHasReportsAccess(reportsAccess);
    }

    /**
     * getClusterName
     * Retrives cluster name for the user profile.
     *
     * @return
     */
    public String getClusterName()
    {
        LOGGER.debug("Entering in getClusterName() of UserProfileService .....");
        return userProfile.getClusterName();
    }

    /**
     * setClusterName
     * Sets the cluster name for the user profile.
     *
     * @param newClusterName
     */
    public void setClusterName(String newClusterName)
    {
        LOGGER.debug("Entering in setClusterName() of UserProfileService .....");
        userProfile.setClusterName(newClusterName);
    }

    /**
     * getSetupStatus
     *
     *
     * @return
     */
    public String getSetupStatus()
    {
        LOGGER.debug("Entering in getSetupStatus() of UserProfileService .....");
        return userProfile.getSetupStatus();
    }

    /**
     * setSetupStatus
     *
     *
     * @param aStatus
     */
    public void setSetupStatus(String aStatus)
    {
        LOGGER.debug("Entering in setSetupStatus() of UserProfileService .....");
        userProfile.setSetupStatus(aStatus);
    }

    /**
     * addReportsAccess
     * Adds reports access for the user profile.
     *
     * @exception NBException
     */
    public void addReportsAccess() throws NBException
    {
        LOGGER.debug("Entering in addReportsAccess() of UserProfileService .....");
        try
        {
            UserRoleDTO[] currentRoles = userProfile.getUserRoles();
            Vector ctv = new Vector();
            boolean isFirstEntry = true;

            for (int k = 0; k < currentRoles.length; k++)
            {
                UserRoleDTO role = currentRoles[k];
                String roleName = role.getRoleName();
                String baseRoleName = "";

                // Special Case: GCSU
                if (baseRoleName.equals("GCSU"))
                {
                    if (isFirstEntry)
                    {    // This is only an AU for first entry.
                        isFirstEntry = false;
                        PasswordService
                                .setOraclePassword(getUserID(), PasswordService
                                .getInitialPassword(getUserID()));
                    }
                }	 // Special Case: AH roles
                else if (baseRoleName.equals("AH")
                        || (baseRoleName.equals("NFC"))
                        || (baseRoleName.equals("A"))
                        || (baseRoleName.equals("TC")))
                {
                }
                else
                {

                    // Traverse the new hierarchies for this role.
                }
            }
        }
        catch (NBException e)
        {
            throw e;
        }
        finally
        {
            LOGGER.debug("Exiting from addReportsAccess() of UserProfileService .....");
        }

    }

    /**
     * loadUserProfile
     * Loads User Profile from the database.
     *
     * @param userID
     *
     * @return
     *
     * @exception NBException
     */
    public UserProfileDTO loadUserProfile(String userID) throws NBException
    {
        LOGGER.debug("Entering in loadUserProfile() of UserProfileService .....");

        try
        {
            return userProfileDAO.loadUserProfile(userID);
        }
        catch (SQLException ex)
        {
            throw new NBError(ex.getMessage());
        }
        finally
        {
            LOGGER.debug("Exiting from loadUserProfile() of UserProfileService .....");
        }

    }

    /**
     * getBaseRole
     * Gets the base role for a given role.
     *
     * @param aRole
     *
     * @return
     *
     * @exception NBException
     */
    public String getBaseRole(String aRole) throws NBException
    {
        return userProfileDAO.getBaseRole(aRole);
    }

    /**
     * getAccountType
     * Gets the individualaccount type for a given individualaccount number.
     *
     * @param acctNbr
     *
     * @return
     *
     * @exception NBException
     */
    public String getAccountType(String acctNbr) throws NBException
    {
        LOGGER.debug("Entering in getAccountType() of UserProfileService .....");

        try
        {
            return userProfileDAO.getAccountType(acctNbr);
        }
        catch (EAGLSException ex)
        {
            throw new NBError(ex.getMessage());
        }
        catch (DataConnException ex)
        {
            throw new NBError(ex.getMessage());
        }
        finally
        {
            LOGGER.debug("Exiting from getAccountType() of UserProfileService .....");
        }

    }

    /**
     * retrieveClusterName
     * Retrieves the cluster name if there is any.
     *
     * @param userID
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBException
     */
    public String retrieveClusterName(String userID)
            throws NBException, EAGLSException, DataConnException
    {
        LOGGER.debug("Entered in retrieveClusterName() of UserProfileService .....");
        return userProfileDAO.retrieveClusterName(userID);
    }

    /**
     * setRoleService
     * Sets the contained role service instance.
     *
     * @param roleService
     */
    public void setRoleService(RoleService roleService)
    {
        LOGGER.debug("Entering in setRoleService() of UserProfileService .....");
        userProfile.addRole(roleService.getRoleDTO());
        this.roleService = roleService;
        LOGGER.debug("Exiting from setRoleService() of UserProfileService .....");
    }

    /**
     * isUserIDNew
     * Checks that if the user id already exists?
     *
     * @param userID
     *
     * @return
     *
     * @exception Exception
     * @exception NBError
     */
    public boolean isUserIDNew(String userID) throws NBError, Exception
    {
        LOGGER.debug("Entered in isUserIDNew() of UserProfileService .....");
        return userProfileDAO.isUserIDNew(userID);
    }

    /**
     * populateUserProfile
     * Populates the user profile data.
     *
     * @param userId
     * @param firstName
     * @param lastName
     * @param status
     * @param reportsAccess
     */
    public void populateUserProfile(String userId, String firstName,
                                    String lastName, String status,
                                    boolean reportsAccess)
    {
        LOGGER.debug("Entering in populateUserProfile() of UserProfileService .....");

        userProfile.setUserID(userId);
        userProfile.setFirstName(firstName);
        userProfile.setLastName(lastName);
        userProfile.setStatus(status);
        userProfile.setHasReportsAccess(reportsAccess);

        LOGGER.debug("Exiting from populateUserProfile() of UserProfileService .....");
    }

    /**
     * Sets the roles of user
     * @param encodedSentence
     * @param currentRole
     * @param currentHierarchy
     * @param currentUserId
     * @throws NBException
     */
    public void setUserRoles(String encodedSentence, String currentRole,
                             HierarchyDTO[] currentHierarchy,
                             String currentUserId) throws NBException
    {
        LOGGER.debug("Entering in setUserRoles() of UserProfileService .....");

        // Get each role segment and encapsulate the role name and the remainder into a roleUtil obj
        RoleUtil[] roleUtil = UserProfileUtil.decodeRole(encodedSentence);
        String coreRole = null;
        String newValue = null;
        String refHierarchy = null;
        HierarchyDTO[] newHierarchy = null;
        StringBuffer newSBHierarchy = new StringBuffer("");

        // Move through each roleUtil obj and parse its remainder to set the business objects
        for (int i = 0; i < roleUtil.length; i++)
        {

            // Look up the core role for the given role  - this is used to determine which parsing algorithm
            try
            {
                coreRole = getBaseRole(roleUtil[i].roleType);
            }
            catch (NBException e)
            {
                LOGGER.error(e.getMessage(), e);
                throw new NBError(e.getMessage());
            }
            if (coreRole.equals("GCSU"))
            {    // Set custom/core role into business obj, if first in array, set as default
                roleService.populateRole(this.getUserID(),
                        roleUtil[i].roleType, i == 0);
                roleService.create();
                addRole(roleService.getRoleDTO());
                newValue = roleUtil[i].roleType;
            }
            else if (coreRole.equals("AH"))
            {		 // Get the array of acct numbers associated with the AH
                AcctNumberUtil[] acctNumberUtil =
                        UserProfileUtil
                        .decodeAccountNumber(roleUtil[i].roleRemainder);

                // first validate individualaccount nos; make sure all assigned accounts are of type "I"
                setUserAccounts(acctNumberUtil);

                // Set custom/core role into business obj
                roleService.populateRole(getUserID(), roleUtil[i].roleType,
                        i == 0);
                roleService.create();
                addRole(roleService.getRoleDTO());
                newValue = roleUtil[i].roleType;
                for (int x = 0; x < acctNumberUtil.length; x++)
                {
                    if (getUserRole(roleUtil[i].roleType) == null)
                    {
                        roleService.load(getUserID(), roleUtil[i].roleType);
                    }

                    // try {
                    accountService
                            .populateAccount(getUserID(),
                                    roleService.getRoleName(),
                                    acctNumberUtil[x].acctNumber,
                                    x == 0);
                    accountService.create();
                    roleService.setAccountService(accountService);

                    // Log ACCOUNT_NUMBER
                    newValue = acctNumberUtil[x].acctNumber;
                }
            }
            else if (coreRole.equals("A_OPC") || coreRole.equals("DBO")
                    || coreRole.equals("TDO") || coreRole.equals("NB_ADM")
                    || coreRole.equals("NB_ACCTG") || coreRole.equals("CL"))
            {

                // Get all the HierachyPT objs from encoded string
                HierarchyPTUtil[] hierachyPTUtil =
                        UserProfileUtil
                        .decodeHierarchyPT(roleUtil[i].roleRemainder);

                setUserHierarchiesPT(hierachyPTUtil, currentRole,
                        currentHierarchy, currentUserId);
                roleService.populateRole(getUserID(), roleUtil[i].roleType,
                        i == 0);
                roleService.create();
                addRole(roleService.getRoleDTO());
                newValue = roleUtil[i].roleType;
                for (int j = 0; j < hierachyPTUtil.length; j++)
                {
                    if (getUserRole(roleUtil[i].roleType) == null)
                    {
                        roleService.load(getUserID(), roleUtil[i].roleType);
                    }
                    hierarchyService
                            .poplulateHierarchy(hierachyPTUtil[j].hierarchy,
                                    j == 0,
                                    roleService.getRoleName(),
                                    getUserID());
                    hierarchyService.create();
                    roleService.setHierarchyService(hierarchyService);
                    refHierarchy =
                            hierarchyService
                            .validateHierarchy(currentUserId,
                                    hierachyPTUtil[j].hierarchy);
                    newHierarchy = hierachyPTUtil[j].hierarchy;

                    // Reset Hierarchy String Buffer
                    newSBHierarchy = new StringBuffer("");

                    // Convert Hierarchy object into a string.
                    for (int r = 0; r < newHierarchy.length; r++)
                    {
                        newSBHierarchy.append(":"
                                + newHierarchy[r].getNumber());
                    }

                    // Update Program types.
                    for (int z = 0; z < hierachyPTUtil[j].programType.size();
                         z++)
                    {
                        hierarchyService
                                .addProgramType(hierachyPTUtil[j].programType
                                .elementAt(z).toString());
                        roleService.setHierarchyService(hierarchyService);
                        newValue =
                                hierachyPTUtil[j].programType.elementAt(z)
                                .toString();
                    }    // End for
                }       // End for
            }
            else if (coreRole.equals("GSA") || coreRole.equals("FMS")
                    || coreRole.equals("A") || coreRole.equals("TC")
                    || coreRole.equals("NFC"))
            {
                HierarchyUtil[] hierarchyUtil =
                        UserProfileUtil
                        .decodeHierarchy(roleUtil[i].roleRemainder);

                setUserHierarchies(hierarchyUtil, currentRole, currentHierarchy, currentUserId);

                // Set custom/core role into business obj
                roleService.populateRole(getUserID(), roleUtil[i].roleType,
                        i == 0);
                roleService.create();
                addRole(roleService.getRoleDTO());
                newValue = roleUtil[i].roleType;
                for (int y = 0; y < hierarchyUtil.length; y++)
                {
                    if (getUserRole(roleUtil[i].roleType) == null)
                    {
                        roleService.load(getUserID(), roleUtil[i].roleType);
                    }
                    hierarchyService
                            .poplulateHierarchy(hierarchyUtil[y].hierarchy,
                                    y == 0,
                                    roleService.getRoleName(),
                                    getUserID());
                    hierarchyService.create();
                    roleService.setHierarchyService(hierarchyService);
                    refHierarchy =
                            hierarchyService
                            .validateHierarchy(currentUserId,
                                    hierarchyUtil[y].hierarchy);
                    newHierarchy = hierarchyUtil[y].hierarchy;
                    newSBHierarchy = new StringBuffer("");

                    // Convert Hierarchy object into a string.
                    for (int r = 0; r < newHierarchy.length; r++)
                    {
                        newSBHierarchy.append(":"
                                + newHierarchy[r].getNumber());
                    }
                    if (coreRole.equals("A") || coreRole.equals("TC")
                            || coreRole.equals("NFC"))
                    {
                        newValue = "T";
                        hierarchyService.addProgramType("T");
                        roleService.setHierarchyService(hierarchyService);
                    }
                }	 // End for
            }
        }

        LOGGER.debug("Exiting from setUserRoles() of UserProfileService .....");
    }

    /* setHierarchy */

    /**
     * Sets the user hierarchies.
     * @param hierarchyUtil
     * @param currentRole
     * @param currentHierarchy
     * @param currentUserId
     * @throws NBException
     */
    public void setUserHierarchies(HierarchyUtil[] hierarchyUtil,
                                   String currentRole,
                                   HierarchyDTO[] currentHierarchy,
                                   String currentUserId) throws NBException
    {

        LOGGER.debug("Entering in setUserHierarchies() of UserProfileService .....");

        for (int j = 0; j < hierarchyUtil.length; j++)
        {
            if (hierarchyService.validateHierarchy(currentRole,
                    hierarchyUtil[j].hierarchy) == null)
            {
                throw new NBError("Invalid Hierarchy entered.");
            }
            //make sure A_OPC can add the Hierarchy
            String tempor = null;
            try
            {
                tempor = getBaseRole(currentRole);
            }
            catch (NBException ex5)
            {
                LOGGER.error(ex5.getMessage(), ex5);
                throw new NBError(ex5.getMessage());
            }
            if (tempor.equals("A_OPC"))
            {
                HierarchyDTO[] h1 = currentHierarchy;
                HierarchyDTO[] h2 = hierarchyUtil[j].hierarchy;
                for (int k = 0; k < h1.length; k++)
                {
                    if (h1[k].getNumber() != 0 && h1[k].getNumber() != h2[k].getNumber())
                    {
                        throw new NBError("An A_OPC cannot assign this Hierarchy.");
                    }
                }
            }
        }
        LOGGER.debug("Exiting from setUserHierarchies() of UserProfileService .....");
    }


    /**
     * Set User Accounts
     * @param acctNumberUtil
     * @throws NBException
     */
    public void setUserAccounts(AcctNumberUtil[] acctNumberUtil) throws
            NBException
    {

        LOGGER.debug("Entering in setUserAccounts() of UserProfileService .....");

        for (int x = 0; x < acctNumberUtil.length; x++)
        {
            String acctType = null;
            try
            {
                acctType = getAccountType(acctNumberUtil[x].acctNumber);
                LOGGER.debug("acctType is " + acctType);
            }
            catch (NBError ex2)
            {
                LOGGER.error("In UserProfile Service - Setting Roles 12 ", ex2);
                throw new NBError(ex2.getMessage());
            }
            if (acctType == null)
            {
                LOGGER.error("In UserProfile Service - Setting Roles 13 : acctType == null");
                throw new NBError("Account does not exist.");
            }
            else if (!(acctType.equals("I") || acctType.equals("T")))
            {
                LOGGER.error("Cannot assign individualaccount that is not of type Individual.");
                throw new NBError(
                        "Cannot assign individualaccount that is not of type Individual.");
            }
        }
        LOGGER.debug("Exiting from setUserAccounts() of UserProfileService .....");
    }


    /**
     * Set UserHierarchy and Program Types
     *
     *
     * @param hierarchyPTUtil
     * @param currentRole
     * @param currentHierarchy
     * @param currentUserId
     *
     * @exception NBException
     */
    public void setUserHierarchiesPT(HierarchyPTUtil[] hierarchyPTUtil,
                                     String currentRole,
                                     HierarchyDTO[] currentHierarchy,
                                     String currentUserId) throws NBException
    {
        LOGGER.debug("Entering in setUserHierarchiesPT() of UserProfileService .....");

        for (int j = 0; j < hierarchyPTUtil.length; j++)
        {
            if (hierarchyService
                    .validateHierarchy(currentRole,
                            hierarchyPTUtil[j].hierarchy) == null)
            {
                throw new NBError("Invalid Hierarchy entered.");
            }

            // make sure A_OPC can add the Hierarchy
            String tempo = null;

            try
            {
                tempo = getBaseRole(currentRole);
            }
            catch (NBException ex4)
            {
                throw new NBError(ex4.getMessage());
            }
            if (tempo.equals("A_OPC"))
            {
                HierarchyDTO[] h1 = currentHierarchy;
                HierarchyDTO[] h2 = hierarchyPTUtil[j].hierarchy;

                for (int k = 0; k < h1.length; k++)
                {
                    if (h1[k].getNumber() != 0
                            && h1[k].getNumber() != h2[k].getNumber())
                    {
                        throw new NBError("This A_OPC cannot assign this Hierarchy.");
                    }
                }
            }
        }
        LOGGER.debug("Exiting from setUserHierarchiesPT() of UserProfileService .....");
    }

    /**
     * saveUserProfile
     *
     *
     * @param sessionUserID
     *
     * @exception NBException
     */
    public void saveUserProfile(String sessionUserID) throws Exception
    {
        LOGGER.debug("Entering in saveUserProfile() of UserProfileService .....");
        save(sessionUserID);
        LOGGER.debug("Exiting from saveUserProfile() of UserProfileService .....");
    }
}
