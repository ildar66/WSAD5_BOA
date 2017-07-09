/**
 * PasswordDAO
 */
package com.boa.eagls.government.dao;

import org.apache.log4j.*;
import com.boa.eagls.government.exceptions.*;

/**
 * A class that provide all database related function for Password management .
 * @version 1.0
 * @invariant $none
 */
public class PasswordDAO extends DAOBase
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.PasswordDAO.class");

    /**
     * Creates new PasswordDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in PasswordDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public PasswordDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering PasswordDAO(java.sql.Connection connection) constructor of PasswordDAO class");
        setConnection(connection);
        LOGGER
                .debug("Exiting PasswordDAO(java.sql.Connection connection) constructor of PasswordDAO class");
    }

    /**
     * Loads a Password object from persistent store.  This method should
     * only be called by Passwords <code>load()</code> method.
     * @param   userID  fill the Password object with this users password info
     * @param   password    the Password object to fill
     * @throws NBError
     * @pre $none
     * @post $none
     */
    public void loadPassword(String userID, String password) throws NBError
    {
        LOGGER.debug("Entering  in method  loadPassword in PasswordDAO");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"password\" , value = " + password);
        LOGGER.debug("Exiting  in method  loadPassword in PasswordDAO");

        // NOT IMPLEMENTED.
    }

    /**
     * Calls an EAGLS stored procedure to reset a user's password without affecting the history list.
     * @param userid String containing a valid user's ID.
     * @param password String containing a valid, encrypted password.
     * @pre $none
     * @post $none
     * @throws NBError stored procedure failed.
     * @throws NBException stored procedure failed.
     */
    public void resetPassword(String userid,
                              String password) throws NBError, NBException
    {
        LOGGER.debug("Entering  in method  resetPassword in PasswordDAO");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER.debug("Param name=\"password\" , value = " + password);
        LOGGER.debug("Exiting  in method  resetPassword in PasswordDAO");

        // NOT IMPLEMENTED.
    }

    /**
     * Calls an EAGLS stored procedure to change a user's password. The
     * user's previous password will be pushed onto his personal password history list.
     * @param userid	String containing a valid user's ID.
     * @param password	String containing a valid, encrypted password.
     * @pre $none
     * @post $none
     * @throws NBError stored procedure failed.
     * @throws NBException stored procedure failed
     */
    public void changePassword(String userid,
                               String password) throws NBError, NBException
    {
        LOGGER.debug("Entering  in method  changePassword in PasswordDAO");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER.debug("Param name=\"password\" , value = " + password);
        LOGGER.debug("Exiting  in method  changePassword in PasswordDAO");

        // NOT IMPLEMENTED.
    }

    /**
     * This method sets the Report Database's password equalent to Eagls database password.
     * @param userID String containing a valid user's ID.
     * @pre $none
     * @post $none
     * @param password String containing a valid, encrypted password.
     * @throws NBException stored procedure failed
     */
    public void setOraclePassword(String userID,
                                  String password) throws NBException
    {
        LOGGER.debug("Entering  in method  setOraclePassword in PasswordDAO");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"password\" , value = " + password);
        LOGGER.debug("Exiting  in method  setOraclePassword in PasswordDAO");

        // NOT IMPLEMENTED.
    }

    /**
     * Returns the system default password. The system default password is assigned to a user when a supervisor
     * resets their password. This was modified to be retrieved from the Database for Change Request 265.
     * @pre $none
     * @post $none
     * @return String containing the system default password.
     */
    public String getDefaultPassword()
    {

        // NOT IMPLEMENTED.
        LOGGER
                .debug("Entering  in method  getDefaultPassword in PasswordDAO");
        LOGGER.debug("Exiting  in method  getDefaultPassword in PasswordDAO");
        return "defaultPassword";
    }

    /**
     * Returns the system initial password. The system initial password is the password automatically assigned to a new user.
     * This was modified to be retrieved from the Database for Change Request 265.
     * @pre $none
     * @post $none
     * @return	the  system initial password.
     */
    public String getInitialPassword()
    {

        // NOT IMPLEMENTED.
        LOGGER
                .debug("Entering  in method  getInitialPassword in PasswordDAO");
        LOGGER.debug("Exiting  in method  getInitialPassword in PasswordDAO");
        return "initialPassword";
    }

    /**
     * Returns the user's initial password. The user's initial password is the password automatically assigned to
     * a new user when they are setup. This was modified to be retrieved from the Database for Change Request 265.
     * @pre $none
     * @post $none
     * @param userID String containing a valid user's ID.
     * @return	String containing the user's initial password.
     */
    public String getInitialPassword(String userID)
    {

        // NOT IMPLEMENTED.
        LOGGER
                .debug("Entering  in method  getInitialPassword in PasswordDAO");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Exiting  in method  getInitialPassword in PasswordDAO");
        return "initialPassword";
    }

    /**
     * Resets to zero the number of consecutive times a user has failed the login process.
     * @param aUserID the user whos failed count will be incremented
     * @pre $none
     * @post $none
     * @return void
     * @throws NBError
     * @throws NBException
     */
    public void resetFailedPasswdAttempts(String aUserID)
            throws NBError, NBException
    {
        LOGGER
                .debug("Entering  in method  resetFailedPasswdAttempts in PasswordDAO");
        LOGGER.debug("Param name=\"aUserID\" , value = " + aUserID);
        LOGGER
                .debug("Exiting  in method  resetFailedPasswdAttempts in PasswordDAO");

        // NOT IMPLEMENTED.
    }

    /**
     * @param userid
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     * @throws NBError
     * @throws NBException
     */
    public void resetSessionDefaults(String userid)
            throws NBError, NBException
    {
        LOGGER
                .debug("Entering  in method  resetSessionDefaults in PasswordDAO");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER
                .debug("Exiting  in method  resetSessionDefaults in PasswordDAO");
    }

    /**
     * Increments the number of consecutive times a user has failed the login process.
     * @param   aUserID the user whos failed count will be incremented
     * @pre $none
     * @post $none
     * @throws NBError
     * @throws NBException
     */
    public void incFailedPasswdAttempts(String aUserID)
            throws NBError, NBException
    {
        LOGGER
                .debug("Entering  in method  incFailedPasswdAttempts in PasswordDAO");
        LOGGER.debug("Param name=\"aUserID\" , value = " + aUserID);
        LOGGER
                .debug("Exiting  in method  incFailedPasswdAttempts in PasswordDAO");

        // NOT IMPLEMENTED.
    }

}
