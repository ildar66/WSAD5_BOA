/**
 * UserProfileDAO
 */
package com.boa.eagls.government.dao;

import java.util.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.util.*;
import com.boa.eagls.government.sql.constants.UserProfileConstants;
import org.apache.log4j.*;
import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.util.UserProfileUtil;

import java.util.HashMap;
import java.util.Vector;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

/**
 * A class that provide all database related function for User Profile management .
 * @version 1.0
 * @invariant $none
 */
public class UserProfileDAO extends DAOBase
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.UserProfileDAO.class");
    private static final String PASSWORD_DEFAULT =
            "NSCR60";    // the default password
    private static final String PASSWORD_INITIAL = "SDK659";
    private static final String NEW_LINE_CHARACTER = "\n";

    /**
     * Creates new UserProfileDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in UserProfileDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public UserProfileDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering UserProfileDAO(java.sql.Connection connection) constructor of UserProfileDAO class, connction="
                + connection);
        setConnection(connection);


        LOGGER
                .debug("Exiting UserProfileDAO(java.sql.Connection connection) constructor of UserProfileDAO class");
    }

    /**
     * This function gets the all vaild roles defined in the Eagls
     * @return  String[] containing all base roles.
     * @pre $none
     * @post $none
     */
    public String[] getAllBaseRoles()
    {
        LOGGER
                .debug("Entering getAllBaseRoles method of UserProfileDAO class.");
        Vector brVect = new Vector();
        String retVal[] = null;

        brVect.addElement("A_OPC");
        brVect.addElement("AH");
        brVect.addElement("CL");
        brVect.addElement("DBO");
        brVect.addElement("FMS");
        brVect.addElement("GSA");
        brVect.addElement("NB_ACCTG");
        brVect.addElement("NB_ADM");
        brVect.addElement("TDO");
        brVect.addElement("GCSU");
        brVect.addElement("A");
        brVect.addElement("TC");
        brVect.addElement("NFC");
        retVal = new String[brVect.size()];
        for (int i = 0; i < brVect.size(); i++)
        {
            retVal[i] = (String) brVect.elementAt(i);
        }
        LOGGER
                .debug("Exiting getAllBaseRoles method of UserProfileDAO class. Return value="
                + retVal);
        return retVal;
    }

    /**
     * Retrieves a list of all base and custom roles.
     * @return    a list of base and custom roles.
     * @pre $none
     * @post $none
     * @throws NoDataFoundException
     * @throws NBError
     * @throws SQLException
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     */
    public String[] getAllRoles()
            throws NoDataFoundException, NBError, SQLException,
            EAGLSException, DataConnException, NBException
    {
        LOGGER.debug("Entering getAllRoles method of UserProfileDAO class.");
        String SQL =
                "SELECT user_role FROM gsa.valid_roles WHERE user_role NOT IN (SELECT DISTINCT base_role FROM gsa.valid_roles) ORDER BY user_role";
        Vector rs = executeDBQuery(SQL, false);

        if (rs.size() == 0)
        {
            LOGGER
                    .debug("Throwing NoDataFoundException in getAllRoles method of UserProfileDAO class. Reason: No data found");
            throw new NoDataFoundException();
        }
        Vector v = new Vector();
        Hashtable htRowData = null;

        for (int rsCount = 0; rsCount < rs.size(); rsCount++)
        {
            htRowData = (Hashtable) rs.get(rsCount);
            v.addElement((String) htRowData.get(new Integer(1)));
        }
        String[] results = new String[v.size()];

        v.copyInto(results);
        LOGGER
                .debug("Exiting from getAllRoles method of UserProfileDAO class. Return value="
                + results);
        return results;
    }

    /**
     * Gets the base role of the provided role.
     * @param aRole String containing the role whose base role is required
     * @return String containing the base role
     * @pre $none
     * @post $none
     * @throws NBException
     */
    public String getBaseRole(String aRole) throws NBException
    {
        LOGGER.debug("Entering getBaseRole method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"aRole\" , value = " + aRole);
        String result = null;

        LOGGER.debug("Entered getBaseRole and role is" + aRole);
        Vector rs = null;
        String sql = "SELECT base_role FROM gsa.valid_roles WHERE user_role='"
                + aRole + "'";

        try
        {
            rs = executeDBQuery(sql, false);
        }
        finally
        {

        }
        if (rs.size() < 1)
        {
            result = null;
        }
        else
        {
            Hashtable htRowData = (Hashtable) rs.get(0);

            LOGGER.debug("htRowData.get(new Integer(1) = "+ htRowData.get(new Integer(1)));
            result = (String) htRowData.get(new Integer(1));
        }
        LOGGER.debug("Exiting from getBaseRole method of UserProfileDAO class. Return value ="
                + result);
        return result;
    }

    /**
     * Deletes a database entry for user Profile if creation is fails.
     * Should only be called by UserProfiles <code>save()</code> method.
     * @param userID String containing the userid whose profile needs to be deleted
     * @param sessionUserID String containing the userid who is perform this operation
     * @throws NBError If error occured during profile deleteion
     * @throws NBException If error occured during profile deleteion
     * @throws EAGLSException If error occured during profile deleteion
     * @throws DataConnException If error occured during profile deleteion
     * @pre $none
     * @post $none
     */
    public void deleteUserProfile(String userID, String sessionUserID)
            throws NBError, NBException, EAGLSException, DataConnException
    {
        LOGGER
                .debug("Entering deleteUserProfile method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"sessionUserID\" , value = "
                + sessionUserID);
            KeyValueList kvl = new KeyValueList();

            kvl.add("iUserID", sessionUserID, SQLConstants.INPUT_PARAMETER);
            kvl.add("setupUserID", userID, SQLConstants.INPUT_PARAMETER);
            kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, " ");
            callStoredProcedure(UserProfileConstants.SP_DELETE_USER_PROFILE,
                    kvl);

            /*
             * htParam.put("iUserID", sessionUserID);
             * htParam.put("setupUserID", userID);
             * htParam.put("oResult", " ");
             * callStoredProc(SP_DELETE_USER_PROFILE, vParams);
             */
        LOGGER.debug("Exiting from deleteUserProfile method of UserProfileDAO class.");
    }

    /**
     * Creates a database entry for a new UserProfile object.  This method
     * should only be called by UserProfiles <code>save()</code> method.
     * @param   userProfile a UserProfile object that has had its <code>create</code>
     * method called to fill it up with new data.
     * @param	password String containing the new user's password.
     * @param initialPassword String containing the new user's initial password.
     * @param currentUserID String containing the userid which is performing this operation
     * @param userProfile An instance of UserProfileDTO which contains the information about the User Profile to be created
     * @
     * @throws NBError
     * @throws NBException
     * @throws EAGLSException
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    public void createUserProfile(UserProfileDTO userProfile,
                                  String password, String initialPassword,
                                  String currentUserID) throws NBError,
            NBException, EAGLSException,
            DataConnException
    {
        LOGGER
                .debug("Entering createUserProfile method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userProfile\" , value = " + userProfile);
        LOGGER.debug("Param name=\"password\" , value = " + password);
        LOGGER.debug("Param name=\"initialPassword\" , value = "
                + initialPassword);
        LOGGER.debug("Param name=\"currentUserID\" , value = "
                + currentUserID);
        try
        {
            KeyValueList kvl = new KeyValueList();

            kvl.add("pUSERID", userProfile.getUserID(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("pFIRSTNAME", userProfile.getFirstName(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("pLASTNAME", userProfile.getLastName(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("iPassword", password, SQLConstants.INPUT_PARAMETER);
            kvl.add("pQUEUE",
                    userProfile.getSetupStatus().equals("Q") ? "Y" : "N",
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, " ");
            kvl.add("pRequester", currentUserID,
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("iInitialPassword", initialPassword,
                    SQLConstants.INPUT_PARAMETER);
            callStoredProcedure(UserProfileConstants.SP_CREATE_USER_PROFILE,
                    kvl);

            /*
             * setSPParameters("pUSERID", userProfile.getUserID());
             * setSPParameters("pFIRSTNAME", userProfile.getFirstName());
             * setSPParameters("pLASTNAME", userProfile.getLastName());
             * setSPParameters("iPassword", password);
             * setSPParameters("pQUEUE",userProfile.getSetupStatus().equals("Q") ? "Y" : "N");
             * setSPParameters("oResult", "");
             * setSPParameters("pRequester", currentUserID);
             * setSPParameters("iInitialPassword", initialPassword);
             * rs = callStoredProc(SP_CREATE_USER_PROFILE, getSPParameters());
             */
        }
        finally
        {
        }
        LOGGER
                .debug("Exiting from createUserProfile method of UserProfileDAO class.");
    }

    /**
     * Creates a database entry for a new UserRole object.  This method
     * should only be called by UserRoles <code>save()</code> method.
     * @param   userRole    An instance of  UserRoleDTO object that has had its <code>create</code> method called to
     * fill it up with new data.
     * @throws NBError If error occured during role creation
     * @throws NBException If error occured during role creation
     * @throws EAGLSException If error occured during role creation
     * @throws DataConnException If error occured during role creation
     * @pre $none
     * @post $none
     */
    public void createUserRole(UserRoleDTO userRole)
            throws NBError, NBException, EAGLSException, DataConnException
    {
        LOGGER
                .debug("Entering in createUserRole method of UserProfileDAO class.");
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
            kvl.add("oResult", " ", SQLConstants.OUTPUT_PARAMETER, " ");
            callStoredProcedure(UserProfileConstants.SP_CREATE_USER_ROLE,
                    kvl);

            /*
             * setSPParameters("userid", userRole.getUserID());
             * setSPParameters("role", userRole.getRoleName());
             * setSPParameters("default", userRole.isDefault() ? "Y" : "N");
             * setSPParameters("oResult", " ");
             * callStoredProc(UserProfileConstants.SP_CREATE_USER_ROLE, getSPParameters());
             */
        }
        finally
        {
        }
        LOGGER
                .debug("Exiting from createUserRole method of UserProfileDAO class.");
    }

    /**
     * @param   userHierarchy
     * @exception   NBException
     */

    /**
     * Creates a database entry for a new UserHierarchy object.  This
     * method should only be used by UserHierarchys <code>save()</code> method.
     * @param userHierarchy An instance of UserHierarchyDTO  filled with information required to create the user Hierarchy
     * @param userID
     * @throws NBError If hierarchy already assigned to user role
     * @throws NBException If hierarchy already assigned to user role
     * @throws EAGLSException If hierarchy already assigned to user role
     * @throws DataConnException If there is some error with database connection operation
     * @pre $none
     * @post $none
     */
    public void createUserHierarchy(UserHierarchyDTO userHierarchy,
                                    String userID) throws NBError,
            NBException, EAGLSException,
            DataConnException
    {
        LOGGER
                .debug("Entering in createUserHierarchy method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userHierarchy\" , value = "
                + userHierarchy);
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        try
        {
            KeyValueList kvl = new KeyValueList();

            LOGGER.debug("-------- userID = " + userID);
            LOGGER.debug("-------- role = " + userHierarchy.getRoleName());
            kvl.add("userid", userID, SQLConstants.INPUT_PARAMETER);
            kvl.add("role", userHierarchy.getRoleName(),
                    SQLConstants.INPUT_PARAMETER);

            // setSPParameters("userid", userID);
            // setSPParameters("role", userHierarchy.getRoleName());
            HierarchyDTO[] hierarchy = userHierarchy.getHierarchy();

            for (int i = 0; i < 9; i++)
            {    // TODO : MAGIC NUMBER
                if (hierarchy[i] != null)
                {
                    LOGGER.debug("-------- hl" + i + "="+ hierarchy[i].getNumber());
                    kvl.add("hl" + i, "" + hierarchy[i].getNumber(),
                            SQLConstants.INPUT_PARAMETER);

                    // setSPParameters("hl" + i, "" + hierarchy[i].getNumber());
                }
                else
                {
                    LOGGER.debug("----hierarchy[i] is null ");
                    kvl.add("hl" + i, "" + 0, SQLConstants.INPUT_PARAMETER);

                    // setSPParameters("hl" + i, "" + 0);
                }
            }
            LOGGER.debug("------------ default "+ userHierarchy.isDefault());
            kvl.add("default", userHierarchy.isDefault() ? "Y" : "N",
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("oResult", " ", SQLConstants.OUTPUT_PARAMETER,
                    new String());

            // setSPParameters("default", userHierarchy.isDefault() ? "Y" : "N");
            // setSPParameters("oResult", " ");
            callStoredProcedure(UserProfileConstants.SP_CREATE_USER_HIERARCHY,
                    kvl);
        }
        finally
        {
        }
        LOGGER
                .debug("Exiting from createUserHierarchy method of UserProfileDAO class.");
    }

    /**
     * Loads a UserProfile object from persistent store.  This method should
     * only be called by UserProfiles <code>load()</code> method.
     * @param userID String containing user ID to load its profile
     * @return UserProfileDTO An instance of UserProfileDTO which contains the information about user profile
     * @throws NBError If error occured during user profile loading
     * @throws SQLException If error occured during user profile loading
     * @throws EAGLSException If error occured during user profile loading
     * @throws DataConnException If error occured in database connection during user profile loading
     * @throws NBException If error occured during user profile loading
     * @pre $none
     * @post $none
     */
    public UserProfileDTO loadUserProfile(String userID)
            throws NBError, SQLException, EAGLSException, DataConnException,
            NBException
    {
        LOGGER
                .debug("Entering in loadUserProfile method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        UserProfileDTO userProfile = new UserProfileDTO();
        KeyValueList kvl = new KeyValueList();

        kvl.add("userid", userID.toUpperCase(), SQLConstants.INPUT_PARAMETER);
        Vector rs = null;

        {
            try
            {
                rs =
                        executeDBQuery(UserProfileConstants.SQL_LOAD_USER_PROFILE,
                                kvl, false);
            }
            catch (NoDataFoundException e)
            {
                LOGGER
                        .error("Caught  NoDataFoundException in loadUserProfile method of UserProfileDAO class.",
                                e);
                throw new NBError("APP_W0014:UserProfileDAA::User " + userID
                        + " not found");
            }
        }    // end else null userID  - for load of user profile during login
        if (rs.size() < 1)
        {
            LOGGER
                    .debug("Throwing NBError in loadUserProfile method of UserProfileDAO class. Reason: UserProfileDAA::User "
                    + userID + " not found");
            throw new NBError("APP_W0014:UserProfileDAA::User " + userID
                    + " not found");
        }
        userProfile.setUserID(userID);
        Hashtable htRowData = (Hashtable) rs.get(0);    // 1 was there
        String columnData = null;

        columnData = (String) htRowData.get(new Integer(1));
        if (columnData == null)
        {    // MM--Modified code to check if database is returning null
            userProfile.setLastName("");
        }
        else
        {
            userProfile.setLastName(columnData);
        }
        columnData = (String) htRowData.get(new Integer(2));
        if (columnData == null)
        {
            userProfile.setFirstName("");
        }
        else
        {
            userProfile.setFirstName(columnData);
        }
        columnData = (String) htRowData.get(new Integer(3));
        userProfile.setStatus(columnData);

        // reports Access flag
        columnData = (String) htRowData.get(new Integer(4));
        if (columnData == null)
        {
            userProfile.setClusterName("");
            userProfile.setHasReportsAccess(false);
        }
        else
        {
            userProfile.setClusterName(columnData);
            userProfile.setHasReportsAccess(true);
        }
        columnData = (String) htRowData.get(new Integer(5));
        userProfile.setSetupStatus(columnData);
        String getRoles = UserProfileConstants.SQL_FETCH_USER_ROLE_NAMES;

        try
        {

            // rs = callQuery(getRoles.toString(), htQueryParam);
            rs = executeDBQuery(getRoles.toString(), kvl, false);
        }
        catch (NoDataFoundException e)
        {
            LOGGER
                    .debug("Throwing NBError in loadUserProfile method of UserProfileDAO class. Reason: User "
                    + userID + " has no assigned roles");
            throw new NBError("APP_W0015:UserProfileDAA::User " + userID
                    + " has no assigned roles");
        }
        if (rs.size() < 1)
        {
            LOGGER
                    .debug("Throwing NBError in loadUserProfile method of UserProfileDAO class. Reason: User "
                    + userID + " has no assigned roles");
            throw new NBError("APP_W0015:UserProfileDAA::User " + userID
                    + " has no assigned roles");
        }
        Vector vRoleNames = new Vector();
        boolean isDefault;
        String roleName;

        for (int rsCount = 0; rsCount < rs.size(); rsCount++)
        {
            htRowData = (Hashtable) rs.get(rsCount);
            roleName = (String) htRowData.get(new Integer(1));
            if (roleName != null)
            {
                vRoleNames.addElement(roleName);
                isDefault =
                        stringToBoolean((String) htRowData.get(new Integer(2)));
                if (isDefault)
                {
                    userProfile.setDefaultRoleName(roleName);
                }
            }
        }
        String[] roleNames = new String[vRoleNames.size()];

        vRoleNames.copyInto(roleNames);
        userProfile.setRoleNames(roleNames);
        LOGGER
                .debug("Exiting from loadUserProfile method of UserProfileDAO class. ");
        return userProfile;
    }

    /**
     * Creates a database entry for a new UserAccount object.  This
     * method should only be used by UserAccounts <code>save()</code> method.
     * @param userAccount An instance of UserAccountDTO which contains the information needed to create the user individualaccount
     * @throws NBError
     * @throws NBException Account already assigned to user role
     * @throws EAGLSException If error occured during user individualaccount creation
     * @throws DataConnException If error occured in database connection
     * @pre $none
     * @post $none
     */
    public void createUserAccount(UserAccountDTO userAccount)
            throws NBError, NBException, EAGLSException, DataConnException
    {
        LOGGER
                .debug("Entering in createUserAccount method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userAccount\" , value = " + userAccount);
        try
        {
            KeyValueList kvl = new KeyValueList();

            kvl.add("userid", userAccount.getUserID(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("role", userAccount.getRoleName(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("accnbr", userAccount.getAccountNumber(),
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("default", userAccount.isDefault() ? "Y" : "N",
                    SQLConstants.INPUT_PARAMETER);
            kvl.add("oResult", " ", SQLConstants.OUTPUT_PARAMETER,
                    new String());
            callStoredProcedure(UserProfileConstants.SP_CREATE_USER_ACCOUNT,
                    kvl);

            /*
             * setSPParameters("userid", userAccount.getUserID());
             * setSPParameters("role", userAccount.getRoleName());
             * setSPParameters("accnbr", userAccount.getAccountNumber());
             * setSPParameters("default", userAccount.isDefault() ? "Y" : "N");
             * setSPParameters("oResult", " ");
             */

            // /      callStoredProc(SP_CREATE_USER_ACCOUNT, getSPParameters());
        }
        finally
        {
        }
        LOGGER
                .debug("Exiting from createUserAccount method of UserProfileDAO class.");
    }

    /**
     * @param userProfile
     * @throws NBError
     * @pre $none
     * @post $none
     */
    public void saveUserProfile(UserProfileDTO userProfile) throws NBError
    {
        LOGGER
                .debug("Entering in saveUserProfile method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userProfile\" , value = " + userProfile);
        LOGGER
                .debug("Entering in saveUserProfile method of UserProfileDAO class.");
    }

    /**
     * This funcation is used to validate hierarchy of given user id.
     * @param userID String containing the user ID whose hierarchy will be validated
     * @param hierarchy HierarchyDTO containing the information about the hierarchy
     * @return String containing the valid hierarchy number
     * @throws NBException If an occured during validation of hierarchy
     * @throws EAGLSException If an occured during validation of hierarchy
     * @throws DataConnException If an occured during validation of hierarchy
     * @throws NBException If an occured during validation of hierarchy
     * @pre $none
     * @post $none
     */
    public String validateHierarchy(String userID, HierarchyDTO[] hierarchy)
            throws NBException, EAGLSException, DataConnException, NBException
    {
        LOGGER
                .debug("Entering in validateHierarchy method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"hierarchy\" , value = " + hierarchy);
        if (userID != null && hierarchy != null)
        {
            LOGGER.debug("Entered validateHierarchy");
            KeyValueList kvl = new KeyValueList();

            /*
             * kvl.add("iUserID",sessionUserID, SQLConstants.INPUT_PARAMETER);
             * kvl.add("setupUserID", userID, SQLConstants.INPUT_PARAMETER);
             * kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, " ");
             * callStoredProcedure(UserProfileConstants.SP_DELETE_USER_PROFILE,kvl);
             */
            try
            {
                kvl.add("USERID", userID, SQLConstants.INPUT_PARAMETER);
                for (int i = 0; i < hierarchy.length; i++)
                {
                    if (hierarchy[i] != null)
                    {
                        kvl.add("HL" + i, "" + hierarchy[i].getNumber(),
                                SQLConstants.INPUT_PARAMETER);
                    }
                }
                kvl.add("oHierarchyNbr", "0", SQLConstants.OUTPUT_PARAMETER,
                        " ");
                kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, " ");
                HashMap hashMap =
                        callStoredProcedure(UserProfileConstants
                        .SP_GET_HIERARCHY_NBR, kvl);
                String oHierarchyNbr = (String) hashMap.get("oHierarchyNbr");

                LOGGER.debug("Return value=" + oHierarchyNbr);
                return oHierarchyNbr;
            }
            finally
            {
                LOGGER
                        .debug("Exiting from validateHierarchy method of UserProfileDAO class.");
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in validateHierarchy method of UserProfileDAO class. Reason: User ID or Hierarchy found null in validateHierarchy method ");
            throw new NBError("User ID or Hierarchy found null in validateHierarchy method ");
        }
    }

    /**
     * This function retrieves the type of a given individualaccount number.
     * @param acctNbr String the individualaccount number
     * @return String containing the individualaccount type
     * @throws EAGLSException error occurred while querying the database.
     * @throws NBError error occurred while querying the database.
     * @throws DataConnException error in database connection.
     * @throws NBException error occurred while querying the database.
     * @pre $none
     * @post $none
     */
    public String getAccountType(String acctNbr)
            throws EAGLSException, NBError, DataConnException, NBException
    {
        LOGGER
                .debug("Entering in getAccountType method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"acctNbr\" , value = " + acctNbr);
        Vector rs = null;

        LOGGER.debug("Entered getAccountType");
        try
        {
            KeyValueList kvl = new KeyValueList();

            kvl.add("individualaccount", acctNbr, SQLConstants.INPUT_PARAMETER);
            try
            {

                // rs = callQuery(SQL_GET_ACCOUNT_TYPE, inParams);
                rs = executeDBQuery(UserProfileConstants.SQL_GET_ACCOUNT_TYPE,
                        kvl, false);
            }
            catch (NoDataFoundException e)
            {
                LOGGER
                        .error("Caught NoDataFoundException in getAccountType method of UserProfileDAO class.",
                                e);
                throw e;
            }
            String accountType = null;

            if (rs.size() > 0)
            {
                Hashtable htRowData = (Hashtable) rs.get(0);

                accountType = (String) htRowData.get(new Integer(1));

                // rs.getString(1);
            }
            LOGGER
                    .debug("In getAccountType method of UserProfileDAO class. Return value="
                    + accountType);
            return accountType;
        }
        finally
        {
            LOGGER
                    .debug("Exiting from getAccountType method of UserProfileDAO class.");
        }
    }

    /**
     * Creates a database entry for a new Program Type.  This
     * method should only be used by UserHierarchys <code>save()</code> method.
     * @param   userHierarchy   assign the program type to this user hierarchy
     * @param   pType   the program type to assign
     * @throws NBError invalid program type or hierarchy does not exist
     * @throws NBException invalid program type or hierarchy does not exist
     * @throws EAGLSException invalid program type or hierarchy does not exist
     * @throws DataConnException error in database.
     * @pre $none
     * @post $none
     */
    public void createProgramType(UserHierarchyDTO userHierarchy,
                                  String pType) throws NBError, NBException,
            EAGLSException, DataConnException
    {
        LOGGER
                .debug("Entering in createProgramType method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userHierarchy\" , value = "
                + userHierarchy);
        LOGGER.debug("Param name=\"pType\" , value = " + pType);
        if (userHierarchy != null && pType != null)
        {
            try
            {
                KeyValueList kvl = new KeyValueList();

                kvl.add("userid", userHierarchy.getUserID(),
                        SQLConstants.INPUT_PARAMETER);
                kvl.add("role", userHierarchy.getRoleName(),
                        SQLConstants.INPUT_PARAMETER);
                HierarchyDTO hierarchy[] = userHierarchy.getHierarchy();

                for (int i = 0; i < hierarchy.length; i++)
                {    // TODO : MAGIC NUMBER
                    if (hierarchy[i] != null)
                    {
                        kvl.add("hl" + i, "" + hierarchy[i].getNumber(),
                                SQLConstants.INPUT_PARAMETER);

                        // setSPParameters("hl" + i, "" + hierarchy[i].getNumber());
                    }
                }
                kvl.add("progtype", pType, SQLConstants.INPUT_PARAMETER);
                kvl.add("oResult", " ", SQLConstants.OUTPUT_PARAMETER,
                        new String());
                callStoredProcedure(UserProfileConstants
                        .SP_CREATE_PROGRAM_TYPE, kvl);
            }
            finally
            {
                LOGGER
                        .debug("Exiting from createProgramType method of UserProfileDAO class.");
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in createProgramType method of UserProfileDAO class. Reason: User Hierarchy or Account Type found null in method createProgramType");
            throw new NBError("User Hierarchy or Account Type found null in method createProgramType");
        }
    }

    /**
     * Retrives the cluster name of given user
     * @param userID String containing the userID whose cluster number is needed
     * @return String containing the cluster name of given userID
     * @throws NBException If an error occured while retrieving the cluster name
     * @throws EAGLSException If an error occured while retrieving the cluster name
     * @throws DataConnException If a database error occured while retrieving the cluster name
     * @throws NBException If an error occured while retrieving the cluster name
     * @pre $none
     * @post $none
     */
    public String retrieveClusterName(String userID)
            throws NBException, EAGLSException, DataConnException, NBException
    {
        LOGGER
                .debug("Entering in retrieveClusterName method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        KeyValueList kvl = new KeyValueList();

        kvl.add("iUserID", userID, SQLConstants.INPUT_PARAMETER);
        kvl.add("oClusterName", "", SQLConstants.OUTPUT_PARAMETER,
                new String());
        kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, new String());

        /*
         * setSPParameters("iUserID", userID);
         * setSPParameters(":oClusterName", "");
         * setSPParameters(":oResult", "");
         */

        // Call the Stored Procedure.
        HashMap outParams =
                callStoredProcedure(UserProfileConstants.SP_RETRIEVE_CLUSTER_NAME,
                        kvl);
        String str = (String) outParams.get("oClusterName");

        if ((str == null) || (str.trim().equals("")))
        {
            throw new NBError("No Cluster Name found for UserID");
        }
        LOGGER
                .debug("Exiting from retrieveClusterName method of UserProfileDAO class. Return value="
                + str);
        return str = str.trim();
    }    // End of method retrieveClusterName()

    /**
     * Checks weather the given userID is exist in eagls
     * @param userID String containing the user ID whose existance is to be checked
     * @return boolean ture if userID is not present in eagls otherwise false
     * @throws NBError If error occured while checking for user existance
     * @throws Exception If error occured while checking for user existance
     * @pre $none
     * @post $none
     */
    public boolean isUserIDNew(String userID) throws NBError, Exception
    {
        LOGGER
                .debug("Entering in isUserIDNew method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        boolean result = false;

        if (userID != null)
        {
            userID = userID.toUpperCase();
            StringBuffer sqlstmt =
                    new StringBuffer("SELECT COUNT(*) from user_profile where userid = \'");

            sqlstmt.append(userID);
            sqlstmt.append("\'");
            Vector rs = null;

            try
            {
                rs = executeDBQuery(sqlstmt.toString(), false);
            }
            catch (NoDataFoundException e)
            {
                LOGGER
                        .error("Caught NoDataFoundException in isUserIDNew method of UserProfileDAO class.",
                                e);
                result = true;
            }
            Hashtable htRowData = (Hashtable) rs.get(0);
            int userCount =
                    Integer.parseInt((String) htRowData.get(new Integer(1)));

            if (userCount == 0)
            {
                result = true;
            }
            else
            {
                result = false;
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in isUserIDNew method of UserProfileDAO class.Reason: Did not recieve userID");
            throw new NBError("Did not recieve userID");
        }
        LOGGER
                .debug("Exiting from isUserIDNew method of UserProfileDAO class. Return value="
                + result);
        return result;
    }

    // ****************Added by Azra for Logon on 11th June at 3:56*************

    /**
     * Retrives the encrypted password of given user ID
     * @param userID String containing the userID whose encrypted password is to be retrived
     * @return PasswordDTO An instance of PasswordDTO containing the information about encrypted password of given userID
     * @throws NBException If error occured during password reterival
     * @pre $none
     * @post $none
     */
    public PasswordDTO getPassword(String userID) throws NBException
    {
        LOGGER
                .debug("Entering in getPassword method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        PasswordDTO pDTO = null;
        String sqlStmt =
                "SELECT PASSWD, FAIL_COUNT, "
                + "TO_CHAR(EXPIRATION_DATE, 'MM/DD/YYYY') AS EXPIRATION_DATE"
                + " FROM USER_PROFILE" + " WHERE USERID = '"
                + userID.toUpperCase() + "'";

        try
        {

            Vector results = executeDBQuery(sqlStmt, false);

            if (1 != results.size())
            {
                LOGGER
                        .debug("Throwing NoDataFoundException in getPassword method of UserProfileDAO class. Reason: User Id doesn't exist  not ");
                throw new NoDataFoundException("User Id doesn't exist  not ");
            }
            Hashtable hm = (Hashtable) results.elementAt(0);

            pDTO = new PasswordDTO();
            pDTO.setUserID(userID);
            pDTO.setPassword((String) hm.get(new Integer(1)));
            String failedCount =
                    ((String) hm.get(new Integer(2)));//new Integer(0);

            pDTO.setFailedCount(failedCount != null ?
                    Integer.parseInt(failedCount) : 0);
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

            // DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
            String date = hm.get(new Integer(3)).toString();

            try
            {
                pDTO.setExpiryDate(sdf.parse(date));
            }
            catch (ParseException ex)
            {
                LOGGER.error(ex.getMessage(), ex);
            }
        }
        catch (NBException e)
        {

            LOGGER.error("Caught NBException  in getPassword method of UserProfileDAO class.",e);
            pDTO = null;
            throw e;
        }
        LOGGER.debug("Exiting from getPassword method of UserProfileDAO class. Return value ="+ pDTO);
        return pDTO;
    }

    /**
     * This function will be used to get User profile from eagls Database
     * @param userID String containing the userID whose profile is to be reterived
     * @throws NBException If error occured while processing
     * @return UserProfileDTO An instance of UserProfileDTO which contains the information about user profile
     * @pre $none
     * @post $none
     */
    public UserProfileDTO getUserProfile(String userID) throws NBException
    {
        LOGGER
                .debug("Entering in getUserProfile method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        UserProfileDTO upDTO = null;
        StringBuffer sqlStmt = null;
        Vector resVec = null;

        try
        {
            sqlStmt =
                    new StringBuffer(UserProfileConstants
                    .NEW_SQL_LOAD_USER_PROFILE);
            sqlStmt.append(" AND up.USERID = '" + userID.toUpperCase() + "' "
                    + NEW_LINE_CHARACTER);
            resVec = executeDBQuery(sqlStmt.toString(), false);
            if (resVec.size() == 0)
            {
                throw new NoDataFoundException("database.error");
            }
            upDTO = new UserProfileDTO();
            Hashtable hm = (Hashtable) resVec.elementAt(0);
            upDTO.setUserID(userID);
            upDTO.setLastName((String) hm.get(new Integer(1)));
            upDTO.setFirstName((String) hm.get(new Integer(2)));
            upDTO.setStatus((String) hm.get(new Integer(3)));
            upDTO.setClusterName((String) hm.get(new Integer(4)));
            upDTO.setSetupStatus((String) hm.get(new Integer(5)));
            String role = (String) hm.get(new Integer(6));
            upDTO.setDefaultRoleName(role);
        }
        catch (NBException e)
        {
            LOGGER
                    .error("Caught NBException in  getUserProfile method of UserProfileDAO class.",
                            e);

            throw e;
        }
        LOGGER
                .debug("Exiting from getUserProfile method of UserProfileDAO class. Return value="
                + upDTO);
        return upDTO;
    }

    /**
     * This function will change the failed password attemps previously done by given user ID
     * @param userID String containing the UserID whose password failed count is to be changed
     * @throws NBException If error occured during change the failed password attemps
     * @pre $none
     * @post $none
     */
    public void incFailedPasswdAttempts(String userID) throws NBException
    {
        LOGGER
                .debug("Entering in incFailedPasswdAttempts method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        if (userID != null)
        {
            try
            {
                KeyValueList kvl = new KeyValueList();

                kvl.add("USERID", userID,
                        com.boa.eagls.government.util.SQLConstants.INPUT_PARAMETER);
                kvl.add("RESET", "F",
                        com.boa.eagls.government.util.SQLConstants.INPUT_PARAMETER);
                kvl.add("oResult", "",
                        com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                        new String());
                callStoredProcedure(UserProfileConstants
                        .SP_CHANGE_PASSWD_FAIL_COUNT, kvl);
            }
            catch (NBException e)
            {
                LOGGER
                        .error("Caught NBException in incFailedPasswdAttempts method of UserProfileDAO class.",
                                e);
                throw e;
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in incFailedPasswdAttempts method of UserProfileDAO class. Reason: User ID found null in method incFailedPasswdAttempts.");
            throw new NBError("User ID found null in method incFailedPasswdAttempts.");
        }
    }

    /**
     * This function resets the session deafults of given user ID
     * @param userID String containing the userID whose session defaults to be reset
     * @throws NBException If error occured during session reset process
     * @pre $none
     * @post $none
     */
    public void resetSessionDefaults(String userID) throws NBException
    {
        LOGGER
                .debug("Entering in resetSessionDefaults method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        if (userID != null)
        {
            try
            {
                KeyValueList kvl = new KeyValueList();

                kvl.add("USERID", userID,
                        com.boa.eagls.government.util.SQLConstants.INPUT_PARAMETER);
                kvl.add("oResult", "",
                        com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                        new String());
                callStoredProcedure(UserProfileConstants
                        .SP_RESET_SESSION_DEFAULTS, kvl);
            }
            catch (NBException e)
            {
                LOGGER
                        .error("Caught  NBException in resetSessionDefaults method of UserProfileDAO class.",
                                e);

                throw e;
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in resetSessionDefaults method of UserProfileDAO class. Reason: UserID was found null in method resetSessionDefaults.");
            throw new NBError("UserID was found null in method resetSessionDefaults.");
        }
    }

    /**
     * This funcation gets the dafault encrypted password of the given User
     * @param userID String containing the User ID whole default encrypted password is needed
     * @return String containing the dafault encrypted password of the given User
     * @throws NBException If error occured during reterival of dafault encrypted password of the given User
     * @pre $none
     * @post $none
     */
    public String getDefaultPassword(String userID) throws NBException
    {
        LOGGER
                .debug("UserProfileDAO --> getDefaultPassword() --> START");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        String pwd = null;

        try
        {
            KeyValueList kvList = new KeyValueList();

            kvList.add("USERID", userID,
                    com.boa.eagls.government.util.SQLConstants.INPUT_PARAMETER);
            kvList.add("InitialPassword", "",
                    com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                    new String());
            kvList.add("ResetPassword", "",
                    com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                    new String());
            kvList.add("oResult", "",
                    com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                    new String());
            callStoredProcedure(UserProfileConstants.SP_RETRIEVE_PASSWORDS,
                    kvList);
            pwd = kvList.getValue("ResetPassword").toString().trim();
            if ((pwd == null) || (pwd.equals("")))
            {
                pwd = this.PASSWORD_DEFAULT;
            }
        }
        catch (NBException e)
        {
            LOGGER
                    .error("Caught  NBException in getDefaultPassword method of UserProfileDAO class.",
                            e);
            throw e;
        }
        LOGGER
                .debug("UserProfileDAO --> getDefaultPassword() --> END class.Return value="
                + pwd);
        return pwd;
    }

    /**
     * Returns the system initial password. The system initial password is the password automatically assigned to a new user
     * @return String containing the system initial password
     * @throws NBException If error occurred during reterival of initial password
     * @pre $none
     * @post $none
     */
    public String getInitialPassword() throws NBException
    {
        LOGGER
                .debug("Entering in getInitialPassword method of UserProfileDAO class.");
        LOGGER
                .debug("Exiting from getInitialPassword method of UserProfileDAO class.");
        return getInitialPassword("");
    }

    /**
     * Returns the user's initial password. The user's initial password is the password automatically assigned to
     * a new user when they are setup.
     * @param userID String containing the user ID whose initial password is subject to reterive
     * @return String containing the user's initial password.
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public String getInitialPassword(String userID) throws NBException
    {
        LOGGER
                .debug("Entering in getInitialPassword method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        String pwd = null;

        if (userID != null)
        {
            try
            {
                KeyValueList kvl = new KeyValueList();

                kvl.add("USERID", userID,
                        com.boa.eagls.government.util.SQLConstants.INPUT_PARAMETER);
                kvl.add("PASSWORD", "",
                        com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                        new String());
                kvl.add("oResult", "",
                        com.boa.eagls.government.util.SQLConstants.OUTPUT_PARAMETER,
                        new String());
                callStoredProcedure(UserProfileConstants
                        .SP_RETRIEVE_INITIAL_PWD, kvl);
                pwd = kvl.getValue("PASSWORD").toString().trim();
                if ((pwd == null) || (pwd.equals("")))
                {
                    pwd = this.PASSWORD_INITIAL;
                }
            }
            catch (NBException e)
            {
                LOGGER
                        .error("Caught NBException in getInitialPassword method of UserProfileDAO class.",
                                e);
                throw e;
            }
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in getInitialPassword method of UserProfileDAO class. Reason: UserID was found null in method getInitialPassword.");
            throw new NBError("UserID was found null in method getInitialPassword.");
        }
        LOGGER
                .debug("Exiting from getInitialPassword method of UserProfileDAO class. Return value="
                + pwd);
        return pwd;
    }

    /**
     * This function reterives all the valid function assigned to the give role
     * @param userRole String containing the userI Role whose valid functions to be loaded
     * @return String containing the valid list of function of given user ROLE
     * @throws NBException if error occured during reterival of user function
     * @pre $none
     * @post $none
     */
    public String getUserFunctions(String userRole) throws NBException
    {
        LOGGER
                .debug("Entering in getUserFunctions method of UserProfileDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userRole);
        String functionStr = "";
        StringBuffer sqlStr =
                new StringBuffer(UserProfileConstants
                .NEW_SQL_LOAD_VALID_FUNCTIONS);

        sqlStr.append(" vrf.user_role = '" + userRole + "'");
        LOGGER.debug("UserProfileDAO --> UserFunctions() --> SQL  "+ sqlStr);
        Vector validFunctions = executeDBQuery(sqlStr.toString(), false);

        if (0 == validFunctions.size())
        {
            LOGGER
                    .debug("Throwing  NoDataFoundException in getUserFunctions method of UserProfileDAO class. Reason: Databse connection error.");
            throw new NoDataFoundException("database.error");
        }
        else
        {
            Vector vf = new Vector();

            for (int i = 0; i < validFunctions.size(); i++)
            {
                Hashtable hm = (Hashtable) validFunctions.elementAt(i);

                vf.add(hm.get(new Integer(1)));
            }
            ValidFunctions functions = new ValidFunctions();

            functionStr = functions.getFunctionsString(vf);
        }
        LOGGER
                .debug("Exiting from  getUserFunctions method of UserProfileDAO class. Return value="
                + functionStr);
        return functionStr;
    }

    public String getUserFunctionsLastModifiedDate() throws NBException
    {
        String strDate = new String();
        StringBuffer sqlStmt = new StringBuffer();
        sqlStmt.append("SELECT TO_CHAR(MAX(POSTING_DATE), 'MM/DD/YYYY') \n");
        sqlStmt.append("FROM GSA.BATCH_NUMBER\n");
        Vector rs = this.executeDBQuery(sqlStmt.toString(), false);
        if (1 != rs.size())
        {
            LOGGER
                    .debug("Throwing  NoDataFoundException in getUserFunctions method of UserProfileDAO class. Reason: Databse connection error.");

            throw new NoDataFoundException("database.error");
        }
        Hashtable hm = (Hashtable) rs.elementAt(0);

        strDate = (String) hm.get(new Integer(1));

        return strDate;
    }

}    // End of class DAAUserProfile
