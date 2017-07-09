/**
 * Websphere Application Server Connection Class
 */
package com.boa.eagls.government.dao;

import java.sql.Connection;
import javax.naming.*;
import javax.sql.*;
import java.util.Properties;
import java.io.IOException;
import java.sql.SQLException;
import java.io.InputStream;

import com.boa.eagls.government.exceptions.*;

import java.io.FileInputStream;

import org.apache.log4j.*;

/**
 * <p>Description: This class will be used to create Tomcat Database Connections</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @version 1.0
 * @invariant $none
 */
public class WASConnection implements IConnectionService
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.WASConnection.class");
    private Connection connection = null;
    private DataSource ds = null;
    private String dbUserID = null, dbPassword = null,
    jndiName = null, url = null;

    /**
     * Creates new WASConnection
     * @exception NBError
     * @pre $none
     * @post $none
     */
    public WASConnection() throws NBError
    {
        LOGGER
                .debug("Entering in WASConnection() constructor of WASConnection calss");
        try
        {
            loadProperties();
            getDataSource();
            getConnectionFromDB();
        }
        catch (DataConnException ex)
        {
            LOGGER.error("Caught DataConnException in WASConnection() constructor of WASConnection calss",ex);
        }
        catch (Exception e)
        {
            LOGGER.error("Caught Exception in WASConnection() constructor of WASConnection calss",e);
            throw new NBError(e.getMessage());
        }
        LOGGER.debug("Exiting from WASConnection() constructor of WASConnection calss");
    }

    /**
     * This function closes the connection object of the current class
     * @return boolean true if successfully close the connection of current class's connection object , otherwise false
     * @throws DataConnException If error occured during closing the connection
     * @pre $none
     * @post $none
     */
    public boolean close() throws DataConnException
    {
        LOGGER.debug("Entering close method of WASConnection class.");
        boolean result = false;

        try
        {
            connection.close();
            result = true;
        }
        catch (SQLException e)
        {
            LOGGER.error(e.getMessage(), e);
            result = false;
        }
        finally
        {
            connection = null;
        }
        LOGGER
                .debug("Exiting from close method of WASConnection class. Return value="
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
    public boolean commit() throws DataConnException
    {
        LOGGER.debug("Entering commit method of WASConnection class.");
        boolean result = false;

        try
        {
            connection.commit();
            result = true;
        }
        catch (SQLException ex)
        {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from commit method of WASConnection class. Result="
                + result);
        return result;
    }

    /**
     * This function gets the auto commit status  of connection object of current class
     * @return boolean true if successfull otherwise false
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    public boolean getAutoCommit() throws DataConnException
    {
        LOGGER.debug("Entering getAutoCommit method of WASConnection class.");
        boolean result = false;

        try
        {
            result = connection.getAutoCommit();
        }
        catch (SQLException ex)
        {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from getAutoCommit method of WASConnection class. Return value="
                + result);
        return result;
    }

    /**
     * This function rollback the current transaction on connetion object of current class
     * @return boolean true if autocommit was ON otherwise false
     * @throws DataConnException If error occured
     * @pre $none
     * @post $none
     */
    public boolean rollback() throws DataConnException
    {
        LOGGER.debug("Entering in rollback method of WASConnection class.");
        boolean result = false;

        try
        {
            connection.rollback();
            result = true;
        }
        catch (SQLException ex)
        {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from rollback method of WASConnection class. Return value="
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
    public boolean setAutoCommit(boolean autoCommit) throws DataConnException
    {
        LOGGER
                .debug("Entering in setAutoCommit method of WASConnection class.");
        LOGGER.debug("Param name=\"autoCommit\" , value = " + autoCommit);
        boolean result = false;

        try
        {
            connection.setAutoCommit(autoCommit);
            result = true;
        }
        catch (SQLException ex)
        {
            LOGGER.error(ex.getMessage(), ex);
            result = false;
        }
        LOGGER
                .debug("Exiting from setAutoCommit method of WASConnection class. Return value="
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
    public boolean isClosed() throws DataConnException
    {
        LOGGER.debug("Entering in isClosed method of WASConnection class.");
        boolean result = false;

        try
        {
            result = connection.isClosed();
        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER
                .debug("Exiting from in isClosed method of WASConnection class. Return value="
                + result);
        return result;
    }

    /**
     * @throws DataConnException
     */
    private void getConnectionFromDB() throws DataConnException
    {
        LOGGER
                .debug("Entering in getConnectionFromDB method of WASConnection class.");
        try
        {
            connection = ds.getConnection();
        }
        catch (Exception e)
        {
            LOGGER.error("Caught Exception in getConnectionFromDB method of WASConnection class.",e);
            throw new DataConnException(e.getMessage());
        }
        LOGGER
                .debug("Exiting from in getConnectionFromDB method of WASConnection class.");
    }

    /**
     * This function gets the database connection of the currenlty specifed class in properties file
     * @return Connection The database connection of the currenlty specifed class in properties file
     * @pre $none
     * @post $none
     */
    public Connection getConnection()
    {
        return this.connection;
    }

    /**
     * @return
     * @throws Exception
     * @pre $none
     * @post $none
     */
    private void getDataSource() throws NBError
    {
        LOGGER
                .debug("Entering in getDataSource method of WASConnection class.");
        try
        {
            Properties properties = new Properties();

            properties
                    .put(Context.INITIAL_CONTEXT_FACTORY,
                            "com.ibm.websphere.naming.WsnInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "iiop://localhost:900");
            InitialContext context = new InitialContext(properties);

            ds = (DataSource) context.lookup(jndiName);
        }
        catch (NamingException exception)
        {
            LOGGER.error("Caught NamingException in getDataSource method of WASConnection class.",exception);
            throw new NBError("NamingException while looking up DB context : "
                    + exception.getMessage());
        }
        LOGGER.debug("Exiting from getDataSource method of WASConnection class.");
    }

    /**
     * @throws IOException
     */
    private void loadProperties() throws IOException
    {
        LOGGER
                .debug("Entering in loadProperties method of WASConnection class.");
        Properties prop = new Properties();
        InputStream is =
                new FileInputStream(getClass().getResource("/eagls.properties")
                .getFile());

        prop.load(is);
        dbUserID = prop.getProperty("userID");
        dbPassword = prop.getProperty("password");
        jndiName = prop.getProperty("jndiName");
        url = prop.getProperty("url");
        LOGGER
                .debug("Exiting from loadProperties method of WASConnection class.");
    }

}
