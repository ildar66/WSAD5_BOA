/**
 * SessionDAO
 */
package com.boa.eagls.government.dao;

import org.apache.log4j.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;

/**
 * A class that provide all database related function for Session management .
 * @version 1.0
 * @invariant $none
 */
public class SessionDAO extends DAOBase
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.SessionDAO.class");

    /**
     * Creates new SessionDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in SessionDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public SessionDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering SessionDAO(java.sql.Connection connection) constructor of SessionDAO class, connction="
                + connection);
        setConnection(connection);
        LOGGER
                .debug("Exiting SessionDAO(java.sql.Connection connection) constructor of SessionDAO class");
    }

    /**
     * @param userid
     * @param role
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void changeCurrentRole(String userid,
                                  String role) throws NBError, NBException
    {
        LOGGER
                .debug("Entering changeCurrentRole method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER.debug("Param name=\"role\" , value = " + role);
        LOGGER
                .debug("Exiting changeCurrentRole method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * @param userid
     * @param role
     * @param account
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void changeCurrentAccount(String userid, String role,
                                     String account) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering changeCurrentAccount method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER.debug("Param name=\"role\" , value = " + role);
        LOGGER.debug("Param name=\"individualaccount\" , value = " + account);
        LOGGER
                .debug("Exiting changeCurrentAccount method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * @param userid
     * @param role
     * @param hierarchy
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void changeCurrentHierarchy(String userid, String role,
                                       HierarchyDTO[] hierarchy) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering changeCurrentHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userid\" , value = " + userid);
        LOGGER.debug("Param name=\"role\" , value = " + role);
        LOGGER.debug("Param name=\"hierarchy\" , value = " + hierarchy);
        LOGGER
                .debug("Exiting changeCurrentHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

}
