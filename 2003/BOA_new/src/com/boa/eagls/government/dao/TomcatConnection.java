/**
 * Tomcat Connection Class
 */
package com.boa.eagls.government.dao;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

import org.apache.log4j.*;
import com.boa.eagls.government.exceptions.*;

/**
 * <p>Description: This class will be used to create Tomcat Database Connections</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @version 1.0
 * @invariant $none
 */
public class TomcatConnection implements IConnectionService {
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.TomcatConnection.class");
    private Connection connection = null;
    private static final String JNDI_NAME = "jdbc/boa";
    //private static final String JNDI_NAME = "java:comp/env/jdbc/boa";
    private static DataSource ds = null;
    private static final Object L0CK = new Object();

    /**
     * Creates new TomcatConnection
     * @exception NBError
     * @pre $none
     * @post $none
     */
    public TomcatConnection() throws NBError {
        LOGGER.debug("Entering in TomcatConnection() constructor of TomcatConnection calss");
        try {
            ds = getDataSource();
            getConnectionFromDB();
        }
//        catch (DataConnException ex)
//        {
//            LOGGER.error("Caught DataConnException in TomcatConnection() constructor of TomcatConnection calss",ex);
//        }
        catch (Exception e) {
            LOGGER.error("Caught Exception in TomcatConnection() constructor of TomcatConnection calss", e);
            throw new NBError(e.getMessage());
        }
//        LOGGER.debug("Exiting from TomcatConnection() constructor of TomcatConnection calss");
    }

    /**
     * This function closes the connection object of the current class
     * @return boolean true if successfully close the connection of current class's connection object , otherwise false
     * @throws DataConnException If error occured during closing the connection
     * @pre $none
     * @post $none
     */
    public boolean close() throws DataConnException {
        LOGGER.debug("Entering close method of TomcatConnection class.");
        boolean result = false;

        try {
            connection.close();
            result = true;
        } catch (SQLException e) {
            LOGGER.error("Caught SQLException in close method of TomcatConnection class.", e);
            result = false;
        } finally {
            connection = null;
        }
        LOGGER
                .debug("Exiting close method of TomcatConnection class. Return value ="
                + result);
        return result;
    }

    /**
     * This function  commits the current transaction which were started earlier by using current class's connection object
     * @return boolean true if commit was successfull otherwise false
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    public boolean commit() throws DataConnException {
        LOGGER.debug("Entering commit method of TomcatConnection class.");
        boolean result = false;

        try {
            connection.commit();
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER.debug("Exiting from commit method of TomcatConnection class. Return value is " + result);
        return result;
    }

    /**
     * This function gets the auto commit status  of connection object of current class
     * @return boolean true if successfull otherwise false
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    public boolean getAutoCommit() throws DataConnException {
        LOGGER
                .debug("Entering in getAutoCommit method of TomcatConnection class. ");
        boolean result = false;

        try {
            result = connection.getAutoCommit();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER.debug("Exiting from  getAutoCommit method of TomcatConnection class. Return value is " + result);
        return result;
    }

    /**
     * This function rollback the current transaction on connetion object of current class
     * @return boolean true if autocommit was ON otherwise false
     * @throws DataConnException If error occured
     * @pre $none
     * @post $none
     */
    public boolean rollback() throws DataConnException {
        LOGGER
                .debug("Entering in rollback method of TomcatConnection class. ");
        boolean result = false;

        try {
            connection.rollback();
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from rollback method of TomcatConnection class. Return value is "
                + result);
        return result;
    }

    /**
     * This function sets the commit feature of connection object of current class
     * @param autoCommit
     * @return boolean true if successfull otherwise false
     * @throws DataConnException If Error occured.
     * @pre $none
     * @post $none
     */
    public boolean setAutoCommit(boolean autoCommit) throws DataConnException {
        LOGGER
                .debug("Entering in setAutoCommit method of TomcatConnection class. ");
        LOGGER.debug("Param name=\"autoCommit\" , value = " + autoCommit);
        boolean result = false;

        try {
            connection.setAutoCommit(autoCommit);
            result = true;
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from setAutoCommit method of TomcatConnection class. Return value is "
                + result);
        return result;
    }

    /**
     * This funcation checks weather the connection object of the current class is closed or not
     * @return boolean ture if connection object of current class is closed otherwise false
     * @throws DataConnException If error occurred while checking the close status of the connection object of current class
     * @pre $none
     * @post $none
     */
    public boolean isClosed() throws DataConnException {
        LOGGER
                .debug("Entering in isClosed method of TomcatConnection class. ");
        boolean result = false;

        try {
            result = connection.isClosed();
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER
                .debug("Exiting from isClosed method of TomcatConnection class. Return value is "
                + result);
        return result;
    }

    /**
     * @throws SQLException
     */
    private void getConnectionFromDB() throws SQLException {
//        try
//        {
        connection = ds.getConnection();
//        }
//        catch (Exception e)
//        {
//            LOGGER.error("get connection ", e);
//        }
    }

    /**
     * This function gets the database connection of the currenlty specifed class in properties file
     * @return Connection The database connection of the currenlty specifed class in properties file
     * @pre $none
     * @post $none
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * @return
     * @throws SQLException
     */
    private static final DataSource getDataSource() throws SQLException {
        if (ds == null) {
            InitialContext ic = null;
            synchronized (L0CK) {
                if (ds == null)
                    try {
                        ic = new InitialContext();
                        ds = (DataSource) ic.lookup(JNDI_NAME);
                    } catch (NamingException e) {
                            LOGGER.fatal(JNDI_NAME + " DataSource not found", e);
                            throw new SQLException(e.getMessage());
                    }
            }
        }
        return ds;
    }

}
