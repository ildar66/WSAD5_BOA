/**
 * IConnectionService
 */
package com.boa.eagls.government.dao;

import java.sql.*;

import com.boa.eagls.government.exceptions.*;

/**
 * An Interface that provide common funcationality for connection management.
 * @version 1.0
 * @invariant $none
 */
public interface IConnectionService
{

    /**
     * This function sets the commit feature of connection object of current class
     * @param autoCommit
     * @return boolean true if successfull otherwise false
     * @throws DataConnException If Error occured.
     * @pre $none
     * @post $none
     */
    boolean setAutoCommit(boolean autoCommit) throws DataConnException;

    /**
     * This function rollback the current transaction on connetion object of current class
     * @return boolean true if autocommit was ON otherwise false
     * @throws DataConnException If error occured
     * @pre $none
     * @post $none
     */
    boolean rollback() throws DataConnException;

    /**
     * This function gets the auto commit status  of connection object of current class
     * @return boolean true if successfull otherwise false
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    boolean getAutoCommit() throws DataConnException;

    /**
     * This function  commits the current transaction which were started earlier by using current class's connection object
     * @return boolean true if commit was successfull otherwise false
     * @throws DataConnException
     * @pre $none
     * @post $none
     */
    boolean commit() throws DataConnException;

    /**
     * This function close the connection object of the current class
     * @return boolean true if successfully close the connection of current class's connection object , otherwise false
     * @throws DataConnException If error occured during closing the connection
     * @pre $none
     * @post $none
     */
    boolean close() throws DataConnException;

    /**
     * This funcation checks weather the connection object of the current class is closed or not
     * @return boolean ture if connection object of current class is closed otherwise false
     * @throws DataConnException If error occurred while checking the close status of the connection object of current class
     * @pre $none
     * @post $none
     */
    boolean isClosed() throws DataConnException;

    /**
     * This function gets the database connection of the currenlty specifed class in properties file
     * @return Connection The database connection of the currenlty specifed class in properties file
     * @throws DataConnException If error occured while getting connection
     * @pre $none
     * @post $none
     */
    Connection getConnection() throws DataConnException;
}
