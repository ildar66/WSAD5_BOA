/**
 * DataAccessAdapter
 */
package com.boa.eagls.government.dao;

import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.util.ColumnData;
import com.boa.eagls.government.util.KeyValueList;
import com.boa.eagls.government.util.SQLConstants;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Vector;

/**
 * <p>Title: </p> <p>Description:  <code>DataAccessAdapter</code> is a base class for interfacing with a
 * back-end database.  It provides a simple interface for performing common database commands, such as queries and insertions.
 * Creating customized database functions entails extending the
 * <code>DataAccessAdapter</code> with a class that defines addtional methods.</p> <p>Copyright: Copyright (c) 2003</p>
 * @version 1.0
 */
public class DAOBase extends DAOUtil {
    private static final Logger LOGGER = Logger.getLogger(DAOBase.class);
    private Connection connection = null;


    /**
     * Calls a given database stored procedure with parameters.  The parameter
     * list must define a parameter named "oResult" that is used by
     * the stored procedure to return a 'T' for success or an error
     * message for failure.  After, the stored procedure is attempted,
     * a NBError will be thrown if no output parameters are returned or
     * there is "oResult" value in the parameter list.  In the case
     * where "oResult" contains some value other than "T", a NBError
     * will be thrown with the value of "oResult" set as its message.
     * This method will not return unless output parameters exist and "oResult" is set to 'T'.
     * @param statement The stored procedure to call.
     * @param kvList The parameters to be used when calling Stored Procedure. This list must contain an entry for "oResult" or
     * an NBError will be thrown.
     * @return HashMap A HashMap containing the output parameters of stored procedure
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     */
    public HashMap callStoredProcedure(String statement, KeyValueList kvList) throws
            EAGLSException, DataConnException, NBException {
        LOGGER.debug("statement=" + statement);
        LOGGER.debug("kvList=" + kvList);
        LOGGER.debug("Entering callStoredProcedure of class DataAccessAdapter");
        LOGGER.debug("Param name=\"statement\" , value = " + statement);
        LOGGER.debug("Param name=\"kvList\" , value = " + kvList);
        if (statement != null && kvList != null && !statement.equals("")) {
            boolean newCreated = false;
            CallableStatement cs = null;
            ColumnData colData = null;
            Vector outputParams = new Vector();

            try {
                if (connection == null || connection.isClosed()) {
                    LOGGER.debug("callStoredProcedure Going to make new conection");
                    IConnectionService connectionService = ConnectionFactory.getInstance();
                    if (connectionService != null) {
                        connection = connectionService.getConnection();
                    }
                    newCreated = true;
                }
                if (null == connection) {
                    LOGGER.debug("Throwing NBError in callStoredProcedure method of class DataAccessAdapter. Reason: Could not get Database Connection");
                    throw new NBError("database.connection.error");
                }
                LOGGER.debug("connection.prepareCall(statement): " + statement);
                cs = connection.prepareCall(statement);
                int i = 1;

                while (null != (colData = kvList.nextColumn())) {
                    if (colData.getParamType()
                            .equals(SQLConstants.INPUT_PARAMETER)) {
                        LOGGER.debug("cs.setObject(i, colData.getValue()), 1 " +
                                i + ", " + colData.getValue());
                        cs.setObject(i, colData.getValue());
                    } else {
                        if (colData.getParamType().equals(SQLConstants.INPUT_OUTPUT_PARAMETER)) {
                            cs.setObject(i, colData.getValue());
                            LOGGER.debug("cs.setObject(i, colData.getValue()), 1 " +
                                    i + ", " + colData.getValue());
                            cs.setObject(i, colData.getValue());
                        }
                        LOGGER.debug("cs.registerOutParameter(i, checkDataType(colData.getParamDataType())); " +
                                i + ", " + checkDataType(colData.getParamDataType()));
                        cs.registerOutParameter(i, checkDataType(colData.getParamDataType()));
                        outputParams.add(new Integer(i));
                    }
                    i++;
                }
//                cs.get
                cs.execute();
                HashMap ret = new HashMap();

                for (int j = 0; j < outputParams.size(); j++) {
                    int paramNum = ((Integer) outputParams.elementAt(j)).intValue();
                    ColumnData cd = kvList.elementAt(paramNum - 1);
                    String colName = cd.getColumnName();
                    Object colVal = cs.getObject(paramNum);
                    ret.put(colName, colVal);
                }
                LOGGER.debug("ret is =====> " + ret);
                String oResult = (String) ret.get("oResult");

                if (oResult == null || oResult.equals("")) {
                    LOGGER
                            .debug("Throwing NBError in callStoredProcedure method of class DataAccessAdapter. Reason: Result flag returned a null value");
                    throw new NBError(
                            "SYS_E0004:DataAccessAdapter::Result flag returned a null value.");
                }
                if (!oResult.equalsIgnoreCase("T")) {
                    LOGGER
                            .debug("Throwing NBError in callStoredProcedure method of class DataAccessAdapter. Reason: "
                            + oResult);
                    throw new NBError(oResult);
                }
                LOGGER
                        .debug("Returning callStoredProcedure method of class DataAccessAdapter. Return value= "
                        + ret);
                return ret;
            } catch (SQLException ex1) {
                LOGGER.error(ex1.getMessage(), ex1);
                throw new NBError(ex1.getMessage());
            } catch (DataConnException ex1) {
                LOGGER.error(ex1.getMessage(), ex1);
                throw new NBError(ex1.getMessage());
            } catch (InstantiationException ex1) {
                LOGGER.error(ex1.getMessage(), ex1);
                throw new NBError(ex1.getMessage());
            } catch (IllegalAccessException ex1) {
                LOGGER.error(ex1.getMessage(), ex1);
                throw new NBError(ex1.getMessage());
            } catch (ClassNotFoundException ex1) {
                LOGGER.error(ex1.getMessage(), ex1);
                throw new NBError(ex1.getMessage());
            } finally {
                try {
                    if (null != cs) {
                        cs.close();
                        cs = null;
                    }
                    if (newCreated && (null != connection)
                            && (!connection.isClosed())) {
                        LOGGER.debug("going to close the collection");
                        connection.close();
                        connection = null;
                        newCreated = false;
                    }
                } catch (SQLException e) {
                    LOGGER.error("Exception in callStoredProcedure method of class DataAccessAdapter.", e);
                    throw new NBError("database.error.finally");
                }
                LOGGER.debug("Exiting callStoredProcedure method of class DataAccessAdapter.");
            }
        } else {
            LOGGER.error("Throwing exception in callStoredProcedure method of class DataAccessAdapter. Reason: Statement OR Key Value List Object is found null in method Call Stored Procedure");
            throw new NBRuntimeError("Statement OR Key Value List Object is found null in method Call Stored Procedure");
        }
    }

    /**
     * @param pdt
     * @return
     */
    private int checkDataType(Object pdt) {
        LOGGER.debug("Entering in checkDataType method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"pdt\" , value = " + pdt);
        int result = Types.NULL;

        if (null == pdt) {
            result = Types.NULL;
        } else if (pdt instanceof String) {
            result = Types.VARCHAR;
        } else if (pdt instanceof Integer) {
            result = Types.INTEGER;
        } else if (pdt instanceof Date) {
            result = Types.DATE;
        }

        LOGGER.debug("Exiting checkDataType method of class DataAccessAdapter. Retrun value= "
                + result);
        return result;
    }

    /**
     * @return Returns the connection object of this instance
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * @param connection Set the connection object to the connection object of this instance.
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Method executeDBQuery The executeDBQuery method takes a preformed SQL
     * statement String, and a KeyValueList containing the names of the
     * columns for which results are to returned.  The query is executed
     * and a Vector containing the result set in Hashtable is returned. If the result set is empty an
     * empty Vector is returned.
     * @param sqlStmt String containing the sql prepared statement
     * @param kvList  kvList containg the parameters for prepared statement
     * @return Vector Vector containing the results of the query
     * @param withColumnName A boolean which will decide weather the Vector which this function will return contains
     * Column Name or Column Index
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     */
    protected Vector executeDBQuery(String sqlStmt, KeyValueList kvList,
                                    boolean withColumnName) throws EAGLSException,
            DataConnException, NBException {
        LOGGER
                .debug("Entering in executeDBQuery method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sqlStmt\" , value = " + sqlStmt);
        LOGGER.debug("Param name=\"kvList\" , value = " + kvList);
        LOGGER.debug("Param name=\"withColumnName\" , value = "
                + withColumnName);
        boolean newCreated = false;

        LOGGER.debug("SQLStatement is: " + sqlStmt);
        LOGGER.debug("Key Value List is :" + kvList);
        ResultSet resSet = null;
        Hashtable results = null;
        Vector vResultSet = new Vector();
        ColumnData colData = null;
        PreparedStatement pstmt = null;
        int inParams = 0;
        ResultSetMetaData rsmd = null;

        sqlStmt = parseForWildCard(sqlStmt);
        sqlStmt = handlePreparedWildCards(sqlStmt, kvList);

        LOGGER.debug("After kahani SQLStatement is: " + sqlStmt);
        LOGGER.debug("After kahani Key Value List is :" + kvList);

        try {
            if (connection == null || connection.isClosed()) {
                LOGGER.debug("executeDBQuery Going to make new conection");
                IConnectionService connectionService = ConnectionFactory.getInstance();

                if (connectionService == null) {
                    LOGGER
                            .debug("Throwing NBError in executeDBQuery method of class DataAccessAdapter. Reason: Unable to get connection Service");
                    throw new NBError(
                            "Unable to get connection Service, Contact Administrator for further details.");
                }
                connection = connectionService.getConnection();
                newCreated = true;
            }
            if (null == connection) {
                LOGGER
                        .debug("Throwing EAGLSException in executeDBQuery method of class DataAccessAdapter. Reason: Unable to get database connection.");
                throw new EAGLSException("database.connection.error");
            }
            pstmt = connection.prepareStatement(sqlStmt);
            pstmt.clearParameters();
            if (0 < kvList.getSize()) {
                int i = 1;
                while (null != (colData = kvList.nextColumn())) {
                    if (colData.getParamType()
                            .equals(SQLConstants.INPUT_PARAMETER)) {
                        inParams++;
                        pstmt.setObject(i, colData.getValue());
                    }
                    i++;
                }
            }
            resSet = pstmt.executeQuery();
            if (null == resSet) {
                vResultSet = new Vector();
            } else {
                rsmd = resSet.getMetaData();
                while (resSet.next()) {
                    results = new Hashtable();
                    for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                        if (withColumnName) {
                            if (resSet.getString(j) != null) {
                                results
                                        .put(rsmd.getColumnName(j).toUpperCase(),
                                                resSet.getString(j));
                            } else {
                                results
                                        .put(rsmd.getColumnName(j).toUpperCase(),
                                                "");
                            }
                        } else {
                            if (resSet.getString(j) != null) {
                                results.put(new Integer(j),
                                        resSet.getString(j));
                            } else {
                                results.put(new Integer(j), "");
                            }
                        }
                    }
                    vResultSet.add(results);
                }
            }
        } catch (SQLException sqle) {
            LOGGER.error("Caught SQLException in executeDBQuery method of class DataAccessAdapter.", sqle);
            throw new EAGLSException("database.error.try");
        } catch (InstantiationException ex) {
            LOGGER.error("Caught InstantiationException in executeDBQuery method of class DataAccessAdapter.", ex);
        } catch (IllegalAccessException ex) {
            LOGGER.error("Caught IllegalAccessException in executeDBQuery method of class DataAccessAdapter.", ex);
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Caught ClassNotFoundException in executeDBQuery method of class DataAccessAdapter.", ex);
        } finally {
            try {
                if (null != resSet) {
                    resSet.close();
                    resSet = null;
                }
                if (null != pstmt) {
                    pstmt.close();
                    pstmt = null;
                }
                if (newCreated && (null != connection)
                        && (!connection.isClosed())) {
                    connection.close();
                    connection = null;
                    newCreated = false;
                }
            } catch (SQLException sqle) {
                LOGGER.error("Caught SQLException in executeDBQuery method of class DataAccessAdapter.", sqle);
                throw new EAGLSException("database.error.finally");
            }
        }
        LOGGER.debug("ret is :" + vResultSet);
        return vResultSet;
    }

    /**
     * Method executeDBQuery The executeDBQuery method takes a preformed SQL statement String.  The query is executed
     * and a Vector containing the result set in Hashtable is returned. If the result set is empty an
     * empty Vector is returned.
     * @param sqlStmt String containing the sql prepared statement
     * @return Vector Vector containing the results of the query
     * @param withColumnName A boolean which will decide weather the Vector which this function will return contains
     * Column Name or Column Index
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     */
    protected Vector executeDBQuery(String sqlStmt, boolean withColumnName) throws
            EAGLSException, DataConnException, NBException {
        LOGGER.debug("Entering in executeDBQuery method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sqlStmt\" , value = " + sqlStmt);
        LOGGER.debug("Param name=\"withColumnName\" , value = "
                + withColumnName);
        boolean newCreated = false;

        LOGGER.debug("SQLStatement is : " + sqlStmt);
        LOGGER.debug("withColumnName = " + withColumnName);
        ResultSet resSet = null;
        Hashtable results = null;
        Vector vResultSet = new Vector();
        PreparedStatement pstmt = null;

// int inParams = 0;
        ResultSetMetaData rsmd = null;

        sqlStmt = parseForWildCard(sqlStmt);

        LOGGER.debug("sql statement after parsing =" + sqlStmt);

        try {
            connection = getConnection();
            if (connection == null || connection.isClosed()) { // con != null && con.isClosed() added by azra on 14th

                LOGGER.debug("executeDBQuery Going to make new conection");
                IConnectionService connectionService = ConnectionFactory.getInstance();

                if (connectionService == null) {
                    LOGGER
                            .debug("Throwing NBError in executeDBQuery method of class DataAccessAdapter. Reason: Unable to get connection Service");
                    throw new NBError(
                            "Unable to get connection Service, Contact Administrator for further details.");
                }
                connection = connectionService.getConnection();
                newCreated = true;
            }
            if (null == connection) {
                LOGGER
                        .debug("Throwing EAGLSException in executeDBQuery method of class DataAccessAdapter. Reason: Unable to get the dataabase connection");
                throw new EAGLSException("database.connection.error");
            }
            pstmt = connection.prepareStatement(sqlStmt);
            resSet = pstmt.executeQuery();
            if (null == resSet) {
                vResultSet = new Vector();
            } else {
                rsmd = resSet.getMetaData();
                while (resSet.next()) {
                    results = new Hashtable();
                    for (int j = 1; j <= rsmd.getColumnCount(); j++) {
                        if (!withColumnName) {
                            String temp = resSet.getString(j);

                            if (temp != null) {
                                results.put(new Integer(j), temp);
                            } else {
                                results.put(new Integer(j), "");
                            }
                        } else {
                            if (resSet.getString(j) != null) {
                                results
                                        .put(rsmd.getColumnName(j).toUpperCase(),
                                                resSet.getString(j));
                            } else {
                                results
                                        .put(rsmd.getColumnName(j).toUpperCase(),
                                                "");
                            }
                        }
                    }
                    vResultSet.add(results);
                }
            }
        } catch (InstantiationException ex) {
            LOGGER.error("Caught InstantiationException in executeDBQuery method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } catch (IllegalAccessException ex) {
            LOGGER.error("Caught IllegalAccessException in executeDBQuery method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Caught ClassNotFoundException in executeDBQuery method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } catch (SQLException e) {
            LOGGER.error("Caught SQL in executeDBQuery method of class DataAccessAdapter.", e);
            throw new NBError(e.getMessage());
        } finally {
            try {
                if (null != resSet) {
                    resSet.close();
                    resSet = null;
                }
                if (null != pstmt) {
                    pstmt.close();
                    pstmt = null;
                }
                if (newCreated && (null != connection)
                        && (!connection.isClosed())) {
                    connection.close();
                    connection = null;
                    newCreated = false;
                }
            } catch (SQLException sqle) {
                LOGGER.error("Caught SQLException in executeDBQuery method of class DataAccessAdapter.", sqle);
                throw new EAGLSException("Error was found in query " + sqle.getMessage());
            }
        }
        LOGGER.debug("Exiting executeDBQuery method of class DataAccessAdapter. Return value=" + vResultSet);
        return vResultSet;
    }

    /**
     * Method executeDBUpdate The executeDBUpdate method takes a preformed SQL
     * statement String, and a KeyValueList containing the parameters of
     * the DB update.  The update is executed and a boolean is returned.
     * If the is returned.  If the update is successful true is returned otherwise false is returned.
     * @param sqlStmt String containg the sql statement
     * @param kvList  kvList containg the parameters for prepared statement
     * @return boolean Ture of update were successfull otherwise False
     * @throws EAGLSException
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     */
    protected boolean executeDBUpdate(String sqlStmt, KeyValueList kvList) throws
            EAGLSException, DataConnException, NBException {
        LOGGER
                .debug("Entering in executeDBUpdate method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sqlStmt\" , value = " + sqlStmt);
        LOGGER.debug("Param name=\"kvList\" , value = " + kvList);
        boolean newCreated = false;
        ColumnData colData = null;
        PreparedStatement pstmt = null;
        boolean result = false;

        try {
            if (connection == null || connection.isClosed()) {
                LOGGER.debug("executeDBUpdate Going to make new conection");
                IConnectionService connectionService = ConnectionFactory.getInstance();

                connection = connectionService.getConnection();
                newCreated = true;
            }
            if (null == connection) {
                result = false;
                LOGGER
                        .debug("Throwing EAGLSException in executeDBUpdate method of class DataAccessAdapter. Reason: Unable to get the database connection");
                throw new EAGLSException("database.connection.error");
            }
            pstmt = connection.prepareStatement(sqlStmt);
            pstmt.clearParameters();
            if (0 < kvList.getSize()) {
                int i = 1;

                while (null != (colData = kvList.nextColumn())) {
                    if (colData.getParamType()
                            .equals(SQLConstants.INPUT_PARAMETER)) {
                        if (null == colData.getValue()) {
                            pstmt.setNull(i + 1, Types.DOUBLE);
                        } else {
                            pstmt.setObject(i + 1, colData.getValue());
                        }
                    } else {
                        LOGGER
                                .debug("Throwing EAGLSException in executeDBUpdate method of class DataAccessAdapter. Reason: Invalid parameter");
                        throw new EAGLSException("invalid.parameter.error");
                    }
                    i++;
                }
            }
            int res = pstmt.executeUpdate();

            if (1 == res) {
                result = true;
            }
        } catch (SQLException sqle) {
            LOGGER.error("Caught SQLException in executeDBUpdate method of class DataAccessAdapter.", sqle);
            throw new NBError(sqle.getMessage());
        } catch (InstantiationException ex) {
            LOGGER.error("Caught exception InstantiationException in executeDBUpdate method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } catch (IllegalAccessException ex) {
            LOGGER.error("Caught exception IllegalAccessException in executeDBUpdate method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            LOGGER.error("Caught exception ClassNotFoundException in executeDBUpdate method of class DataAccessAdapter.", ex);
            throw new NBError(ex.getMessage());
        } finally {
            try {
                if (null != pstmt) {
                    pstmt.close();
                    pstmt = null;
                }
                if (newCreated && (null != connection)
                        && (!connection.isClosed())) {
                    connection.close();
                    connection = null;
                    newCreated = false;
                }
            } catch (SQLException sqle) {
                LOGGER.error("Caught SQLException in executeDBUpdate method of class DataAccessAdapter.", sqle);
                throw new EAGLSException("database.error");
            }
        }
        LOGGER.debug("Exiting executeDBUpdate method of class DataAccessAdapter. Return value =" + result);
        return result;
    }

    /**
     * @param sql
     * @return
     */
    private String intelligentSQLParse(String sql) {
        LOGGER.debug("Entering intelligentSQLParse method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sql\" , value = " + sql);
        String start = "SELECT";
        String middle = "";
        String end = "COUNT(*)";
        String lowerCaseSql = sql.toLowerCase();
        String newString = null;
        int indexFrom = lowerCaseSql.indexOf("from");
        int indexDistinct = lowerCaseSql.indexOf("distinct");
        int indexOpenComment = lowerCaseSql.indexOf("/*+");
        int indexOrderBy = lowerCaseSql.indexOf("order by");
        int indexCloseComment = lowerCaseSql.indexOf("*/",
                indexOpenComment);

        if (indexDistinct != -1) {

// For cases when distinct is used, the following format is used
// SELECT COUNT(*) FROM (SELECT XXX FROM YYY WHERE ZZZ)
// This is done since this is the only way of getting the correct count of the number
// of records that will be returned when there is a DISTINCT clause in the query.
// The ORDER BY clause is stripped of when doing count.
            if (indexOrderBy != -1) {
                return "SELECT COUNT(*) FROM ("
                        + sql.substring(0, indexOrderBy) + ")";
            } else {
                return "SELECT COUNT(*) FROM (" + sql + ")";
            }
        }
        if (indexOpenComment != -1 && indexCloseComment != -1) {
            if (indexOpenComment < indexFrom && indexCloseComment < indexFrom) {
                middle =
                        sql.substring(indexOpenComment, indexCloseComment + 2)
                        + " ";
            }
        }
        try {
            newString = start + " " + end + " "
                    + (indexOrderBy == -1 ? sql.substring(indexFrom)
                    : sql.substring(indexFrom, indexOrderBy));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        LOGGER.debug("Exiting intelligentSQLParse method of class DataAccessAdapter. Return value =" + newString);
        return newString;
    }

    /**
     * Sends a given SQL statement to a database.  This method throws a
     * NoDataFoundException if the ResultSet contains no rows
     * of data (i.e. no results found).  An NBError exception is thrown if the
     * SQL statement generates an error while executing on the database.
     * @param SQLStatement String containing the SQL statement to execute
     * @param countFlag boolean used to check for large result set, if true
     * else executes the SQLStatement
     * @param maxSize int containing the threshhold value to throw a LargeResultSet exception
     * @param withColumnName boolean used to check for returning resultset contains columns with column name or index
     * @return Vector containing the resultset of query
     * @throws NBError
     * @throws NoDataFoundException When no data found in execution of query
     * @throws LargeResultSetException If resultset is larger than the MAX allowed resultset
     * @throws NBException
     */

    protected Vector executeDBQuery(String SQLStatement, boolean countFlag,
                                    int maxSize,
                                    boolean withColumnName) throws NBError,
            NoDataFoundException, LargeResultSetException, NBException {

        LOGGER.debug("Entering executeDBQuery method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"countFlag\" , value = " + countFlag);
        LOGGER.debug("Param name=\"maxSize\" , value = " + maxSize);
        LOGGER.debug("Param name=\"withColumnName\" , value = " + withColumnName);

        Vector rs = null; // a result set returned by stored procedure
        Hashtable htRowData = null;

        if (countFlag) {
            String newSQL = intelligentSQLParse(SQLStatement);
            rs = executeDBQuery(newSQL, false);

            htRowData = (Hashtable) rs.get(0);
            int rowCount = Integer.parseInt((String) htRowData.get(new Integer("1")));
            LOGGER.debug("Row Count = " + String.valueOf(rowCount));
            if (rowCount >= maxSize) {
                LOGGER.debug("Throwing LargeResultSetException in executeDBQuery method of class DataAccessAdapter. Reason: Resultset was greater than " +
                        maxSize);
                throw new LargeResultSetException(rowCount);
            }
        }

        rs = executeDBQuery(SQLStatement, withColumnName);
        LOGGER.debug(
                "Exiting from executeDBQuery method of class DataAccessAdapter. Return Value=" +
                rs);
        return rs;
    }

    /**
     * Sends a given SQL statement to a database.  This method throws a
     * NoDataFoundException if the ResultSet contains no rows
     * of data (i.e. no results found).  An NBError exception is thrown if the
     * SQL statement generates an error while executing on the database.
     * @param SQLStatement The SQL statement to send.
     * @param SQLParams	 The list of parameters to run SQLStatement with.
     * @param countFlag    boolean checks for large result set, if true
     *                     else executes the SQLStatement
     * @param   maxSize    int the threshhold value to throw a LargeResultSet exception
     * @param withColumnNames boolean used to check for returning resultset contains columns with column name or index
     * @return Vector containing the resultset of query
     * @throws NBError
     * @throws NoDataFoundException When no data found in execution of query
     * @throws LargeResultSetException If resultset is larger than the MAX allowed resultset
     * @throws NBException
     */
    protected Vector executeDBQuery(String SQLStatement, KeyValueList SQLParams,
                                    boolean countFlag, int maxSize,
                                    boolean withColumnNames) throws
            NBException,
            NoDataFoundException, LargeResultSetException {

        LOGGER.debug("Entering executeDBQuery method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"SQLStatement\" , value = " + SQLStatement);
        LOGGER.debug("Param name=\"SQLParams\" , value = " + SQLParams);
        LOGGER.debug("Param name=\"countFlag\" , value = " + countFlag);
        LOGGER.debug("Param name=\"maxSize\" , value = " + maxSize);
        LOGGER.debug("Param name=\"withColumnNames\" , value = " + withColumnNames);

        Vector rs = null; // a result set returned by stored procedure
        Hashtable htRowData = null;

        if (countFlag) {
            String newSQL = intelligentSQLParse(SQLStatement);
            rs = executeDBQuery(newSQL, SQLParams, false);
            htRowData = (Hashtable) rs.get(0);
            int rowCount = Integer.parseInt((String) htRowData.get(new Integer("1")));
            LOGGER.debug("Row Count = " + rowCount);

            if (rowCount >= maxSize) {
                LOGGER.debug("Throwing LargeResultSetException in executeDBQuery method of class DataAccessAdapter. Reason: Number of rows in Resultset is greater than " +
                        maxSize);
                throw new LargeResultSetException(rowCount);
            }
        }

        rs = executeDBQuery(SQLStatement, SQLParams, withColumnNames);
        LOGGER.debug(
                "Exiting from executeDBQuery method of class DataAccessAdapter. Return value=" +
                rs);
        return rs;
    }

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        String sql = "SELECT * FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu" + NEW_LINE_CHARACTER
                +
                " WHERE up.USERID = ?   AND eu.USERID2 = ?  AND eu.USERID3 = ? "; // +

        KeyValueList kvl = new KeyValueList();
        kvl.add("USERID", "*k*am*", SQLConstants.INPUT_PARAMETER);
        kvl.add("USERID2", "*ran*", SQLConstants.INPUT_PARAMETER);
        kvl.add("USERID3", "*zam*", SQLConstants.INPUT_PARAMETER);

        DAOBase DAA = new DAOBase();
        String testing = DAA.handlePreparedWildCards(sql, kvl);
        LOGGER.debug("main1===" + testing);
        LOGGER.debug("main1===" + kvl);
        testing = DAA.handlePreparedWildCards(testing, kvl);
        LOGGER.debug("main2===" + testing);
        LOGGER.debug("main2===" + kvl);

        testing = DAA.handlePreparedWildCards(testing, kvl);
        LOGGER.debug("main3===" + testing);
        LOGGER.debug("main3===" + kvl);
    }


}
