package com.boa.eagls.government.dao;

import com.boa.eagls.government.exceptions.NoDataFoundException;
import com.boa.eagls.government.util.ColumnData;
import com.boa.eagls.government.util.KeyValueList;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.Iterator;

/**
 * <p><small> DVI Company, 30.07.2003 18:06:19</small></p>
 * @author romanso
 */
public abstract class DAOUtil {
    static final Logger LOGGER = Logger.getLogger(DAOUtil.class);

    protected final static String A_DB_VALUE_FOR_BOOLEAN_TRUE = "Y";
    protected final static String A_DB_VALUE_FOR_BOOLEAN_FALSE = "N";
    protected final static String NEW_LINE_CHARACTER = System.getProperty("line.separator");

    /**
     * closes ResultSet and Statement opend in derived DAO classes
     * @param rs
     * @param st
     * @throws SQLException
     */
    protected static final void closeAll(ResultSet rs, Statement st) throws SQLException {
        if (rs != null)
            rs.close();
        if (st != null)
            st.close();
    }

    protected static final boolean testOutput(String result) throws SQLException, NoDataFoundException {
        int endIndex = 9;
        int beginIndex = 0;
        if (result == null) {
            throw new SQLException("SYS_E0004:DataAccessAdapter::Stored procedure returned a null output parameter list", "state", 1);
        }
        if (!result.equals("T")) {
            int resultSize = result.length();
            if (resultSize < endIndex) {
                LOGGER.debug("DataAccessAdapter.callStoredProc: Database didn't return an error message");
                //AS: transport.logDebug("DataAccessAdapter.callStoredProc", "Database didn't return an error message");
            } else if (result.substring(beginIndex, endIndex).equals("ORA-01403")) {
                if (resultSize > endIndex) {
                    throw new SQLException(result, "state", 1);
                    //    	throw new NoDataFoundException(result.substring(endIndex,resultSize));
                } else {
                    throw new NoDataFoundException();
                }
            } else {
                throw new SQLException(result, "state", 1);
            }
        }
        return true;
    }

    /**
     * @param sqlStmt
     * @return
     */
    public static final String parseForWildCard(String sqlStmt) {
        LOGGER.debug("Entering parseForWildCard method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sqlStmt\" , value = " + sqlStmt);
        int indexWildCard = sqlStmt.indexOf("*'");

        while (indexWildCard > 0) {
            int equalIndex = sqlStmt.lastIndexOf((int) '=', indexWildCard + 5); // start ahead and work back. This will find the '=' on either side
            int startOfClause = sqlStmt.lastIndexOf((int) '\'', indexWildCard);
            int endOfClause = sqlStmt.indexOf((int) '\'', indexWildCard) + 1;
            StringBuffer beginStmt;
            String endStmt;
            String wildCardClause = sqlStmt.substring(startOfClause, endOfClause);

            if (equalIndex < indexWildCard) { // handle SQL clause ex a.LAST_NAME = 'SMITH'
                beginStmt = new StringBuffer(sqlStmt.substring(0, equalIndex - 1));
                endStmt = sqlStmt.substring(endOfClause);
            } else { // handle ex 'SMITH' = a.LAST_NAME
                beginStmt = new StringBuffer(sqlStmt.substring(0, startOfClause));
                endStmt = sqlStmt.substring(equalIndex + 1).trim();
                int indexNextSpace = endStmt.indexOf(" ");
                int indexLine = endStmt.indexOf(DAOBase.NEW_LINE_CHARACTER);

                beginStmt.append(endStmt.substring(0, indexNextSpace > indexLine ? indexLine : indexNextSpace));
                endStmt = endStmt.substring(indexNextSpace > indexLine ? indexLine : indexNextSpace);
            }
            wildCardClause = " LIKE(" + wildCardClause.replace('*', '%') + ") "; // add LIKE function
            sqlStmt = beginStmt.toString() + wildCardClause + endStmt; // build final sql
            indexWildCard = sqlStmt.indexOf("*'");
        }
        LOGGER.debug("Exiting parseForWildCard method of class DataAccessAdapter. Return value=" + sqlStmt);
        return sqlStmt;
    }

    /**
     * @param b
     * @return
     */
    protected String booleanToString(boolean b) {
        LOGGER.debug("Entering booleanToString method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"b\" , value = " + b);
        String result = null;

        if (b) {
            result = A_DB_VALUE_FOR_BOOLEAN_TRUE;
        } else {
            result = A_DB_VALUE_FOR_BOOLEAN_FALSE;
        }
        LOGGER.debug("Exiting booleanToString method of class DataAccessAdapter. Return value=" + result);
        return result;
    }

    /**
     * @param s
     * @return
     */
    protected boolean stringToBoolean(String s) {
        LOGGER.debug("Entering stringToBoolean method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"s\" , value = " + s);
        boolean result = false;

        if (s != null && s.trim().equalsIgnoreCase("Y")) {
            result = true;
        } else {
            result = false;
        }
        LOGGER.debug("Exiting stringToBoolean method of class DataAccessAdapter. Return value=" + result);
        return result;
    }

    /**
     * @param sqlStmt
     * @param kvList
     * @return
     */
    public static final String handlePreparedWildCards(String sqlStmt, Iterator kvList) {

        LOGGER.debug("Entering in handlePreparedWildCards method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sqlStmt\" , value = " + sqlStmt);
        LOGGER.debug("Param name=\"kvList\" , value = " + kvList);

        int inParams = 0;
        if (kvList != null) {
                while (kvList.hasNext()) { //  for(int i=0; i<sqlParams.count(); i++){
                    ColumnData colData = null;
                    Object value = null;
                    String keyVal = null;
                    value = kvList.next();
                    if(value instanceof ColumnData){
                        colData = (ColumnData)value;
                        keyVal = (String) colData.getValue();
                    }else{
                        keyVal = value.toString();
                    }

                    if (keyVal != null && (keyVal.indexOf("*") != -1)) {
                        int indexWildCard = getPreviousStatementWithRespectToTokenCount(sqlStmt, inParams); //sqlStmt.indexOf(key);
                        int equalIndex = sqlStmt.lastIndexOf((int) '=', indexWildCard + 6); //start ahea and work back. This will find the '=' on either side
                        StringBuffer beginStmt;
                        String endStmt;
                        if (equalIndex < indexWildCard) { //handle SQL clause ex a.LAST_NAME = 'SMITH'
                            beginStmt = new StringBuffer(sqlStmt.substring(0, equalIndex - 1));
                            endStmt = sqlStmt.substring(indexWildCard + 1);
                        } else { //handle ex 'SMITH' = a.LAST_NAME
                            beginStmt = new StringBuffer(sqlStmt.substring(0, indexWildCard));
                            endStmt = sqlStmt.substring(equalIndex + 1).trim();
                            int indexNextSpace = endStmt.indexOf(" ");
                            int indexLine = endStmt.indexOf(DAOBase.NEW_LINE_CHARACTER);
                            beginStmt.append(endStmt.substring(0,
                                    indexNextSpace > indexLine ? indexLine :
                                    indexNextSpace));
                            endStmt = endStmt.substring(indexNextSpace > indexLine ?
                                    indexLine : indexNextSpace);
                        }

                        colData.setValue(keyVal.replace('*', '%')); //change * to sql wildcard % in the Value
                        String wildCardClause = " LIKE( ? ) "; //add LIKE function

                        sqlStmt = beginStmt.toString() + wildCardClause + endStmt; // build final sql
                    }

                    inParams++;
            }
        }
        LOGGER.debug("Exiting from handlePreparedWildCards method of class DataAccessAdapter. Return value= " + sqlStmt);
        return sqlStmt;
    }

    public static final int getPreviousStatementWithRespectToTokenCount(String sql, int tokenCount) {

        LOGGER.debug("Entering in getPreviousStatementWithRespectToTokenCount method of class DataAccessAdapter.");
        LOGGER.debug("Param name=\"sql\" , value = " + sql);
        LOGGER.debug("Param name=\"tokenCount\" , value = " + tokenCount);

        int loopCheck = 0;
        int result = 0;
        StringBuffer stringBuffer = new StringBuffer("");
        StringTokenizer stringTokenizer = new StringTokenizer(sql, "?");
        while (stringTokenizer.hasMoreTokens() && loopCheck <= tokenCount) {
            if (loopCheck == tokenCount) {
                stringBuffer.append(stringTokenizer.nextToken());
            } else {
                stringBuffer.append(stringTokenizer.nextToken() + "?");

            }
            loopCheck++;
        }
        result = stringBuffer.toString().length();
        LOGGER.debug("Exiting from getPreviousStatementWithRespectToTokenCount method of class DataAccessAdapter. Return value =" + result);
        return result;
    }

    /**
     * Authorizes a hierarchy search parameter against the user's current
     * hierarchy.  If authorized, the search parameter is restructured so
     * that it can be easily embedded in a SQL statement.  For example,
     * given the following parameters:
     * <pre>
     * 		User's current hierarchy:	1.2.3.0.0.0.0.0.0
     *		Given search hierarchy:		1.2.3.4.5.0.0.0.0
     *		Given search depth:			2
     * </pre>
     * this method will return
     * <pre>
     *		1.2.3.4.5.-1.-1.0.0
     * </pre>
     * A hierarchy level of -1 signifies it is wildcarded and should not
     * be used in the construction of the SQL statement.
     *
     * @param	hSearch	an array of hierarchy levels to search for (required
     *					length == 0)
     * @param	depth	the number of levels down the search should return
     *					(required 0-8)
     * @param	hUser   user's current hierarchy
     * @return	int[]	the modified hierarchy to contstruct a SQL query with
     **/
    protected static final int[] authorizeHierarchySearch(int hSearch[], short depth, int hUser[]) {
        short i;                        // counter
        int hNew[] = new int[9];        // will hold new hierarchy
        //int hUser[] = transport.getCurrentHierarchy();  // user's current hierarchy
        if (depth < 0) {
            return null;
        }		// sanity check

        // if both hSearch and hUser are null just return an array
        // of wildcards.  This means the caller is GCSU searching
        // for on something other than hierarchy
        if (hSearch == null && hUser == null) {
            for (i = 0; i < 9; i++) {
                hNew[i] = -1;
            }
            return hNew;
        }


        // if hSearch is null then caller is not searching by hierarchy
        // but needs a hierarchy to restrict query access
        if (hSearch == null) {
            for (i = 0; i < 9; i++) {
                hNew[i] = (hUser[i] > 0) ? hUser[i] : -1;
            }
            return hNew;
        }


        // hSearch is not null so caller wants to search based on hierarchy
        // first, reconstruct the query factoring in depth and wildcards

        for (i = 0; i < 9 && hSearch[i] == 0; i++) {
            // wildcard leading zeros to single level search
            hNew[i] = -1;
        }
        for (; i < 9 && hSearch[i] > 0; i++) {
            // copy search level(s)
            hNew[i] = hSearch[i];
        }
        for (short j = depth; i < 9 && j > 0; j--, i++) {
            // wildcard depth levels
            hNew[i] = -1;
        }
        for (; i < 9; i++) {
            // zero out remaining levels
            hNew[i] = 0;
        }

        // second, check against user's hierarchy to authorize
        if (hUser == null) {
            // user is GCSU with full access
            return hNew;
        }
        for (i = 0; i < 9 && hUser[i] > 0; i++) {
            if (hNew[i] == -1) {
                // overwrite wildcards
                hNew[i] = hUser[i];
            } else if (hNew[i] != hUser[i]) {
                // mismatch; authorization denied
                return null;
            }
        }

        return hNew;
    }


}