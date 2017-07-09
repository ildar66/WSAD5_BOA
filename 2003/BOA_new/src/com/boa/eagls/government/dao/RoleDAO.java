/**
 * RoleDAO
 */
package com.boa.eagls.government.dao;

import java.util.*;

import org.apache.log4j.*;
import org.apache.log4j.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.sql.constants.*;
import com.boa.eagls.government.util.*;

/**
 * A class that provide all database related function for Role management .
 * @version 1.0
 * @invariant $none
 */
public class RoleDAO extends DAOBase
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.RoleDAO.class");
    private final static String SP_CREATE_USER_ROLE =
            "{CALL USER_PROFILE_SETUP.ASSIGN_ROLE(?, ?, ?, ?)}";

    /**
     * Creates new RoleDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in RoleDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public RoleDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering RoleDAO(java.sql.Connection connection) constructor of RoleDAO class, connction="
                + connection);
        setConnection(connection);
        LOGGER
                .debug("Exiting RoleDAO(java.sql.Connection connection) constructor of RoleDAO class");
    }

    /**
     * @param newRole
     * @param addRemoveFunctionsFlag
     * @param baseRole
     * @param descrption
     * @param functions
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void createRole(String newRole, short addRemoveFunctionsFlag,
                           String baseRole, String descrption,
                           String[] functions) throws NBError, NBException
    {
        LOGGER.debug("Entering createRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"newRole\" , value = " + newRole);
        LOGGER.debug("Param name=\"addRemoveFunctionsFlag\" , value = "
                + addRemoveFunctionsFlag);
        LOGGER.debug("Param name=\"baseRole\" , value = " + baseRole);
        LOGGER.debug("Param name=\"descrption\" , value = " + descrption);
        LOGGER.debug("Param name=\"functions\" , value = " + functions);
        LOGGER.debug("Exiting createRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * @param role
     * @param functions
     * @param addRemoveFunctionsFlag
     * @pre $none
     * @post $none
     * @throws NBError
     * @throws NBException
     */
    public void modifyFunctionsForRole(String role, String[] functions,
                                       short addRemoveFunctionsFlag) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering modifyFunctionsForRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"role\" , value = " + role);
        LOGGER.debug("Param name=\"functions\" , value = " + functions);
        LOGGER.debug("Param name=\"addRemoveFunctionsFlag\" , value = "
                + addRemoveFunctionsFlag);
        LOGGER
                .debug("Exiting modifyFunctionsForRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * @param role
     * @param description
     * @param functions
     * @param addRemoveFunctionsFlag
     * @pre $none
     * @post $none
     * @throws NBError
     * @throws NBException
     */
    public void modifyRole(String role, String description,
                           String[] functions,
                           short addRemoveFunctionsFlag) throws NBError,
            NBException
    {
        LOGGER.debug("Entering modifyRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"description\" , value = " + description);
        LOGGER.debug("Param name=\"role\" , value = " + role);
        LOGGER.debug("Param name=\"functions\" , value = " + functions);
        LOGGER.debug("Param name=\"addRemoveFunctionsFlag\" , value = "
                + addRemoveFunctionsFlag);
        LOGGER.debug("Exiting modifyRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Constructs a call to a database stored procedure that will delete
     * a given user role from the system. A valid user ID and role must be supplied.
     * @param UserID String containng the user whos role will be deleted
     * @param RoleName String containing the role that will be deleted
     * @pre $none
     * @post $none
     * @throws NBError Stored procedure returns an error; stored procedures error message stored in exception
     * @throws NBException Stored procedure returns an error; stored procedures error message stored in exception
     */
    public void deleteUserRole(String UserID,
                               String RoleName) throws NBError, NBException
    {
        LOGGER.debug("Entering deleteUserRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"UserID\" , value = " + UserID);
        LOGGER.debug("Param name=\"RoleName\" , value = " + RoleName);
        LOGGER.debug("Exiting deleteUserRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Constructs a call to a SQL statement that will verify the given
     * user role is a valid, existing user_role in the valid_roles table.
     * @pre $none
     * @post $none
     * @param roleName String conataining the role that is being verified
     * @return
     */
    public boolean validateUserRole(String roleName)
    {
        LOGGER.debug("Entering validateUserRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"roleName\" , value = " + roleName);
        LOGGER.debug("Exiting validateUserRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
        return true;
    }

    /**
     * @param userRole
     * @throws NBError
     * @pre $none
     * @post $none
     */
    public void saveUserRole(UserRoleDTO userRole) throws NBError
    {
        LOGGER.debug("Entering saveUserRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"userRole\" , value = " + userRole);
        LOGGER.debug("Exiting saveUserRole method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Loads a UserRole object from persistent store.  This method should
     * only be called by UserRoles <code>load()</code> method.
     * @param userID String conataing the ID of the user the role information belongs to
     * @param roleName String containing the name of the role to load
     * @return UserRoleDTO containing the user role information
     * @throws NBError User information is not found
     * @throws EAGLSException User information is not found
     * @throws DataConnException User information is not found
     * @throws EAGLSException User information is not found
     * @throws DataConnException User information is not found
     * @throws NBException User information is not found
     * @pre $none
     * @post $none
     */
    public UserRoleDTO loadUserRole(String userID, String roleName)
            throws NBError, EAGLSException, DataConnException,
            EAGLSException, DataConnException, NBException
    {
        LOGGER.debug("Entering in loadUserRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"roleName\" , value = " + roleName);
        UserRoleDTO userRole =
                new UserRoleDTO();    // (IUserRoleDTO)DTOFactory.getInstance("com.boa.eagls.government.dto.user.UserRoleDTO");
        Vector rs = null;

        try
        {
            KeyValueList kvl = new KeyValueList();

            kvl.add("userid", userID.toUpperCase(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("role", roleName.toUpperCase(),
                    SQLConstants.INPUT_PARAMETER);
            try
            {
                rs = executeDBQuery(UserProfileConstants.SQL_LOAD_USER_ROLE,
                        kvl, false);
            }
            catch (NoDataFoundException e)
            {
                LOGGER
                        .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason Role named "
                        + roleName + "could not be found for user "
                        + userID);
                throw new NBError("SYS_E0004:UserProfileDAA::Role named "
                        + roleName + "could not be found for user "
                        + userID);
            }
            if (rs.size() < 1)
            {
                LOGGER
                        .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason: Role named "
                        + roleName + "could not be found for user "
                        + userID);
                throw new NBError("SYS_E0004:UserProfileDAA::Role named "
                        + roleName + "could not be found for user "
                        + userID);
            }
            userRole.setUserID(userID);
            userRole.setRoleName(roleName);
            Hashtable htRowData = (Hashtable) rs.get(0);
            String columnData = null;
            String baseRole = (String) htRowData.get(new Integer(1));

            userRole.setCoreRoleName(baseRole);
            if (baseRole.equals("AH"))
            {
                String getAccounts =
                        UserProfileConstants.SQL_FETCH_USER_ACCOUNTS;

                try
                {
                    rs = executeDBQuery(getAccounts, kvl, false);
                }
                catch (NoDataFoundException e)
                {
                    LOGGER.error("Caught NoDataFoundException in loadUserRole method of RoleDAO class",
                                    e);
                    throw new NBError("SYS_E0003:UserProfileDAA::No accounts were found (under user's Hierarchy) for role "
                            + roleName);
                }
                if (rs.size() < 1)
                {
                    LOGGER
                            .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason: No accounts were found (under user's Hierarchy) for role "
                            + roleName);
                    throw new NBError("SYS_E0003:UserProfileDAA::No accounts were found (under user's Hierarchy) for role "
                            + roleName);
                }
                Vector vAccounts = new Vector();
                boolean isDefault;
                UserAccountDTO account;

                for (int rsCount = 0; rsCount < rs.size(); rsCount++)
                {
                    htRowData = (Hashtable) rs.get(rsCount);
                    account = new UserAccountDTO();
                    account.setUserID(userID);
                    account.setRoleName(roleName);
                    account
                            .setAccountNumber((String) htRowData
                            .get(new Integer(1)));
                    account
                            .setAgencyName((String) htRowData
                            .get(new Integer(3)));
                    isDefault =
                            this
                            .stringToBoolean((String) htRowData
                            .get(new Integer(2)));
                    account.setDefault(isDefault);
                    vAccounts.addElement(account);
                    if (isDefault)
                    {
                        userRole
                                .setDefaultAccount(account.getAccountNumber());
                    }
                }
                UserAccountDTO[] accounts =
                        new UserAccountDTO[vAccounts.size()];

                vAccounts.copyInto(accounts);
                userRole.setUserAccounts(accounts);
            }
            else if (baseRole.equals("GSA") || baseRole.equals("FMS"))
            {    // Has No Program Types
                try
                {
                    rs =
                            executeDBQuery(UserProfileConstants
                            .SQL_FETCH_USER_HIERARCHIES, kvl, false);
                }
                catch (NoDataFoundException e)
                {
                    LOGGER
                            .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason: No hierarchies were found (under user's Hierarchy) for role "
                            + roleName);
                    throw new NBError("SYS_E0003:UserProfileDAA::No hierarchies were found (under user's Hierarchy) for role "
                            + roleName);
                }
                if (rs.size() < 1)
                {
                    LOGGER
                            .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason: No hierarchies were found (under user's Hierarchy) for role "
                            + roleName);
                    throw new NBError("SYS_E0003:UserProfileDAA::No hierarchies were found (under user's Hierarchy) for role "
                            + roleName);
                }
                Vector vHierarchies = new Vector();
                UserHierarchyDTO hierarchy;
                boolean isDefault;

                for (int rsCount = 0; rsCount < rs.size(); rsCount++)
                {
                    htRowData = (Hashtable) rs.get(rsCount);
                    HierarchyDTO[] hLevels = new HierarchyDTO[9];

                    hierarchy = new UserHierarchyDTO();
                    StringTokenizer parser = null;

                    try
                    {
                        columnData =
                                (String) htRowData.get(new Integer("13"));
                        parser = new StringTokenizer(columnData, "/");
                    }
                    catch (NullPointerException npe)
                    {
                        LOGGER.error("Caught NullPointerException in loadUserRole method of RoleDAO class.",npe);
                        parser = new StringTokenizer("", "/");
                    }
                    for (short i = 0; i < 9; i++)
                    {
                        hLevels[i] = new HierarchyDTO();
                        hLevels[i].setLevel(i);
                        columnData = (String) htRowData.get(new Integer(i
                                + 1));
                        hLevels[i].setNumber(Integer.parseInt(columnData));
                        hLevels[i].setDescription(parser.hasMoreTokens()
                                ? parser.nextToken() : "");
                    }
                    hierarchy.setHierarchy(hLevels);
                    hierarchy.setUserID(userID);
                    hierarchy.setRoleName(roleName);
                    hierarchy
                            .setAgencyName((String) htRowData
                            .get(new Integer("12")));
                    isDefault =
                            this
                            .stringToBoolean((String) htRowData
                            .get(new Integer("11")));
                    hierarchy.setDefault(isDefault);
                    if (isDefault)
                    {
                        userRole.setDefaultHierarchy(hLevels);
                    }
                    vHierarchies.addElement(hierarchy);
                }
                UserHierarchyDTO[] hierarchies =
                        new UserHierarchyDTO[vHierarchies.size()];

                vHierarchies.copyInto(hierarchies);
                userRole.setUserHierarchies(hierarchies);
            }
            else if (!baseRole.equals("GCSU"))
            {    // Has Program Types
                try
                {

                    // rs = callQuery(SQL_FETCH_USER_HIERARCHIES, htQueryParam);
                    rs =
                            executeDBQuery(UserProfileConstants
                            .SQL_FETCH_USER_HIERARCHIES, kvl, false);
                }
                catch (NoDataFoundException e)
                {
                    LOGGER
                            .error("Caught NoDataFoundException in loadUserRole method of RoleDAO class.",
                                    e);
                    LOGGER
                            .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason:No hierarchies or program types were found (under user's Hierarchy) for role"
                            + roleName);
                    throw new NBError("SYS_E0003:UserProfileDAA::No hierarchies or program types were found (under user's Hierarchy) for role"
                            + roleName);
                }
                if (rs.size() < 1)
                {
                    LOGGER
                            .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason:No hierarchies or program types were found (under user's Hierarchy) for role"
                            + roleName);
                    throw new NBError("SYS_E0004:UserProfileDAA::No hierarchies or program types were found (under user's Hierarchy) for role"
                            + roleName);
                }
                Vector vHierarchies = new Vector();
                UserHierarchyDTO hierarchy = null;
                boolean isDefault;
                boolean isCurrent;

                for (int rsCount = 0; rsCount < rs.size(); rsCount++)
                {
                    htRowData = (Hashtable) rs.get(rsCount);
                    HierarchyDTO[] hLevels = new HierarchyDTO[9];
                    String currPType =
                            (String) htRowData.get(new Integer("10"));

                    if (currPType == null || currPType.equals(""))
                    {
                        LOGGER
                                .debug("Throwing NBError in loadUserRole method of RoleDAO class. Reason:Illegal Program Type Found");
                        throw new NBError("SYS_E0004:UserProfileDAA::Illegal Program Type Found");
                    }
                    String tmpString =
                            (String) htRowData.get(new Integer("13"));
                    StringTokenizer parser = null;

                    parser = new StringTokenizer(tmpString, "/");
                    for (short i = 0; i < 9; i++)
                    {
                        hLevels[i] = new HierarchyDTO();
                        hLevels[i].setLevel(i);
                        columnData = (String) htRowData.get(new Integer(i
                                + 1));
                        hLevels[i].setNumber(Integer.parseInt(columnData));
                        if (parser != null)
                        {
                            hLevels[i].setDescription(parser.hasMoreTokens()
                                    ? parser.nextToken()
                                    : "");
                        }
                    }
                    Enumeration enum = vHierarchies.elements();
                    boolean wasFound = false;

                    while (!wasFound && enum.hasMoreElements())
                    {
                        hierarchy = (UserHierarchyDTO) enum.nextElement();
                        if (hierarchy.equals(hLevels))
                        {
                            wasFound = true;
                        }
                    }
                    if (!wasFound)
                    {
                        hierarchy = new UserHierarchyDTO();
                        hierarchy.setHierarchy(hLevels);
                        hierarchy.setUserID(userID);
                        hierarchy.setRoleName(roleName);
                        hierarchy
                                .setAgencyName((String) htRowData
                                .get(new Integer("12")));
                        isDefault =
                                this
                                .stringToBoolean((String) htRowData
                                .get(new Integer("11")));
                        isCurrent =
                                htRowData.get(new Integer("11")).toString()
                                .equalsIgnoreCase("C");

                        // isCurrent = rs.getString(11).equalsIgnoreCase("C");
                        hierarchy.setDefault(isDefault);
                        if (isDefault)
                        {
                            userRole.setDefaultHierarchy(hLevels);
                        }
                        vHierarchies.addElement(hierarchy);
                    }
                    StringBuffer pTypes =
                            new StringBuffer(hierarchy.getProgramTypes());

                    if (pTypes == null)
                    {
                        pTypes = new StringBuffer(currPType);
                    }
                    else
                    {
                        pTypes.append(currPType);
                    }
                    hierarchy.setProgramTypes(pTypes.toString());
                }
                UserHierarchyDTO[] hierarchies =
                        new UserHierarchyDTO[vHierarchies.size()];

                vHierarchies.copyInto(hierarchies);
                userRole.setUserHierarchies(hierarchies);
            }
            LOGGER
                    .debug("Exiting form loadUserRole method of RoleDAO class. Return value="
                    + userRole);
            return userRole;
        }
        finally
        {
            LOGGER.debug("Exiting loadUserRole");
        }
    }

    /**
     * Creates a database entry for a new UserRole object.  This method
     * should only be called by UserRoles <code>save()</code> method.
     * @param userRole UserRoleDTO containing a UserRole object that has had its <code>create</code> method called to
     * fill it up with new data.
     * @throws NBError Role already assigned to user
     * @throws NBException Role already assigned to user
     * @throws EAGLSException Role already assigned to user
     * @throws DataConnException Role already assigned to user
     * @pre $none
     * @post $none
     */

    // public void createUserRole(UserRoleBO userRole) throws NBError, NBException {
    public void createUserRole(UserRoleDTO userRole)
            throws NBError, NBException, EAGLSException, DataConnException
    {
        LOGGER.debug("Entering in createUserRole method of RoleDAO class.");
        LOGGER.debug("Param name=\"userRole\" , value = " + userRole);
        try
        {
            KeyValueList kvl = new KeyValueList();

            kvl.add("userid", userRole.getUserID(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("role", userRole.getRoleName(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("default", userRole.isDefault() ? "Y" : "N",
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER,
                    new String());
            callStoredProcedure(SP_CREATE_USER_ROLE, kvl);

            /*
             * setSPParameters("userid", userRole.getUserID());
             * setSPParameters("role", userRole.getRoleName());
             * setSPParameters("default", userRole.isDefault() ? "Y" : "N");
             * setSPParameters("oResult", " ");
             */

            // callStoredProc(SP_CREATE_USER_ROLE, getSPParameters());
        }
        finally
        {
            LOGGER
                    .debug("Exiting form createUserRole method of RoleDAO class.");
        }
    }

    /**
     * Constructs a call to a database stored procedure that will change
     * a user's default individualaccount to the given individualaccount number.  The individualaccount
     * designated by the given individualaccount number must already exist and be
     * assigned to the user under the given role.  This method only
     * updates the database as to the new default individualaccount.  The caller
     * must ensure the associated business objects are updated to retain consistency with the database.
     * @param UserID String containing the user whos default individualaccount is changing
     * @param RoleName String containing the name of the role the individualaccount resides under
     * @param AccountNumber String containing the individualaccount designate as the default
     * @throws NBError A current user not found or stored procedure failed
     * @throws NBException A current user not found or stored procedure failed
     * @pre $none
     * @post $none
     */
    public void setDefaultUserAccount(String UserID, String RoleName,
                                      String AccountNumber) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering in setDefaultUserAccount method of RoleDAO class.");
        LOGGER.debug("Param name=\"UserID\" , value = " + UserID);
        LOGGER.debug("Param name=\"RoleName\" , value = " + RoleName);
        LOGGER.debug("Param name=\"AccountNumber\" , value = "
                + AccountNumber);
        LOGGER
                .debug("Exiting form setDefaultUserAccount method of RoleDAO class.");

        // NOT IMPLEMENTED.
    }

}
