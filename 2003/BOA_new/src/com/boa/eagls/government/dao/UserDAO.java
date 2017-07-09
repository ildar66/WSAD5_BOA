/**
 * UserDAO
 */
package com.boa.eagls.government.dao;

import java.util.Hashtable;
import java.util.Vector;

import com.boa.eagls.government.dto.user.UserInfoDTO;
import com.boa.eagls.government.dto.user.UserProfileDTO;
import com.boa.eagls.government.dto.user.UserHierarchyDTO;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.dao.HierarchyDAO;
import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.util.UserProfileUtil;
import org.apache.log4j.Logger;
import com.boa.eagls.government.util.KeyValueList;
import com.boa.eagls.government.util.SQLConstants;

/**
 * A class that provide all database related function for Account management .
 * @version 1.0
 * @invariant $none
 */
public class UserDAO extends DAOBase {

    /**
     * Creates new UserDAO
     * @param java.sql.Connection A connection object, which will be used to execute database in UserDAO
     * @exception none
     * @pre $none
     * @post $none
     */
    private static Logger logger =
            Logger.getLogger("com.boa.eagls.government.dao.UserDAO.class");

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public UserDAO(java.sql.Connection connection) {
        setConnection(connection);
    }

    /**
     * serach user by individualaccount number
     * @param searchText
     * @param sessionAuthorizedUser
     * @return
     * @pre $none
     * @post $none
     * @throws NBException
     */
    public Vector searchUserByAccountNumber(String searchText,
                                            Hashtable sessionAuthorizedUser,
                                            boolean countFlag,
                                            int larsSize) throws NBException {
        logger
                .debug("SearchUserService --> SearchUserByAccountNumber() --> START");

        searchText = searchText.toUpperCase();

        KeyValueList kvl = new KeyValueList();
        kvl.add("accnbr", "" + searchText, SQLConstants.INPUT_PARAMETER);
        StringBuffer sqlStmt = new StringBuffer();
        int[] hierarchy = null;
        Vector rs = null;


        String roleType =
                (String) sessionAuthorizedUser.get("currentRoleType");

        try {
            if (roleType.equals(Constants.ROLETYPE_GCSU)) {    // Logged in user is GCSU type Constants.ROLETYPE_ACCOUNT) ||
                logger.debug("SearchUserService --> SearchUserByAccountNumber() --> Role Type GCSU");
                sqlStmt.append(SQL_USER_SEARCH_BY_ACCOUNT);
                //sqlStmt.append(" AND e.ACCOUNT_NBR = " + searchText);
                sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
                logger.debug(" SerachDAO --> SearchUserByAccountNumber() --> SQL " + sqlStmt);
//         rs = executeDBQuery(sqlStmt.toString(), false);
                rs = executeDBQuery(sqlStmt.toString(), kvl, countFlag, larsSize, false);
                return transformResultSetToUserInfoVector(rs);

            } else {
                sqlStmt.append(SQL_USER_SEARCH_BY_ACCOUNT_NON_GCSU);
                //sqlStmt.append(" AND e.ACCOUNT_NBR = " + searchText);
                HierarchyDTO[] uHierarchy =
                        (HierarchyDTO[]) sessionAuthorizedUser
                        .get("currentHierarchy");
                int[] userHierarchy = new int[uHierarchy.length];

                for (int i = 0; i < uHierarchy.length; i++) {
                    userHierarchy[i] = uHierarchy[i].getNumber();
                }
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch((int[]) null,
                                userHierarchy, (short) 0);
                if (hierarchy == null) {
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND AH.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                if (roleType.equals(Constants.ROLETYPE_HIERARCHYPT)) {
                    char[] programTypes =
                            (char[]) sessionAuthorizedUser
                            .get("currentProgramTypes");

                    if (programTypes == null && programTypes.length <= 0) {
                        throw new NoDataFoundException();
                    }
                    StringBuffer programTypeQString =
                            new StringBuffer("   AND ");

                    // append column name
                    programTypeQString.append("A.PROG_TYPE");
                    programTypeQString.append(" IN (");
                    programTypeQString.append("'" + programTypes[0] + "'");
                    for (short i = 1; i < programTypes.length; i++) {
                        programTypeQString.append(", '" + programTypes[i]
                                + "'");
                    }
                    programTypeQString.append(")" + NEW_LINE_CHARACTER);

                    // append query portion for program types
                    sqlStmt.append(programTypeQString.toString());
                }
                sqlStmt.append(" And  ah.HIERARCHY_NBR = e.HIERARCHY_NBR ");
                sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
                logger.debug(" SerachDAO --> SearchUserByAccountNumber() --> SQL " + sqlStmt);
// query has constructed now execute
//                 rs = executeDBQuery(sqlStmt.toString(), false);
                rs = executeDBQuery(sqlStmt.toString(), kvl, countFlag, larsSize, false);
                return transformResultSetToUserInfoVectorHierarchy(rs);

            }
        } catch (Exception e) {
            logger.error("SearchUserService --> SearchUserByAccountNumber() --> Exception  ",  e);
            throw new NBError("Search by Account Number failed!");
        }

    }

    /**
     * search user by user name
     * @param lastName
     * @param firstName
     * @param sessionAuthorizedUser
     * @return
     * @pre $none
     * @post $none
     * @throws NBException
     */
    public Vector searchUserByName(String lastName, String firstName,
                                   Hashtable sessionAuthorizedUser,
                                   boolean countFlag,
                                   int larsSize) throws NBException, LargeResultSetException {
        logger.debug("SearchUserService --> SearchUserByName() --> Start  ");

        KeyValueList kvl = new KeyValueList();

        kvl.add("lastname", "" + lastName.toUpperCase(), SQLConstants.INPUT_PARAMETER);
        if (!firstName.equals(""))
            kvl.add("firstname", "" + firstName.toUpperCase(), SQLConstants.INPUT_PARAMETER);


        StringBuffer sqlStmt = new StringBuffer();
        int[] hierarchy = null;

        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();
        Vector rs = null;
        String roleType =
                (String) sessionAuthorizedUser.get("currentRoleType");

        try {
            if (roleType.equals(Constants.ROLETYPE_GCSU)) {

                // Logged-in user is GCSU type or AH type
                sqlStmt.append(SQL_USER_SEARCH_BY_FULL_NAME);
                sqlStmt.append("  AND u.LAST_NAME = ?");
                if (!firstName.equals("")) {
                    sqlStmt.append("  AND u.FIRST_NAME = ?");
                }

                sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
                logger.debug(" SerachDAO --> SearchUserByAccountNumber() --> SQL " + sqlStmt);
//                rs = executeDBQuery(sqlStmt.toString(), false);
                rs = executeDBQuery(sqlStmt.toString(), kvl, countFlag, larsSize, false);
                return transformResultSetToUserInfoVector(rs);

            } else {
                sqlStmt.append(SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU);
                sqlStmt.append("  AND u.LAST_NAME = ?");
                if (!firstName.equals("")) {
                    sqlStmt.append("  AND u.FIRST_NAME = ?");
                }
                HierarchyDTO[] uHierarchy =
                        (HierarchyDTO[]) sessionAuthorizedUser
                        .get("currentHierarchy");
                int[] userHierarchy = new int[uHierarchy.length];

                for (int i = 0; i < uHierarchy.length; i++) {
                    userHierarchy[i] = uHierarchy[i].getNumber();
                }
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch((int[]) null,
                                userHierarchy, (short) 0);
                if (hierarchy == null) {
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }

                // if user is current user has program types also
                if (roleType.equals(Constants.ROLETYPE_HIERARCHYPT)) {
                    char[] programTypes =
                            (char[]) sessionAuthorizedUser
                            .get("currentProgramTypes");

                    if (programTypes == null && programTypes.length <= 0) {
                        throw new NoDataFoundException();
                    }
                    StringBuffer programTypeQString =
                            new StringBuffer("   AND ");

                    // append column name
                    programTypeQString.append("A.PROG_TYPE");
                    programTypeQString.append(" IN (");
                    programTypeQString.append("'" + programTypes[0] + "'");
                    for (short i = 1; i < programTypes.length; i++) {
                        programTypeQString.append(", '" + programTypes[i]
                                + "'");
                    }
                    programTypeQString.append(")" + NEW_LINE_CHARACTER);

                    // append query portion for program types
                    sqlStmt.append(programTypeQString.toString());
                }

                // ------------------------- End of programType query -------------------------------------------
                sqlStmt.append("UNION " + NEW_LINE_CHARACTER);
                sqlStmt.append(SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU2);
                sqlStmt.append("  AND u.LAST_NAME = ?");
                if (!firstName.equals("")) {
                    sqlStmt.append("  AND u.FIRST_NAME = ?");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(" And  ah.HIERARCHY_NBR = e.HIERARCHY_NBR ");
                sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
                logger.debug(" SerachDAO --> SearchUserByAccountNumber() --> SQL " + sqlStmt);
// query has constructed now execute
//                rs = executeDBQuery(sqlStmt.toString(), false);
                rs = executeDBQuery(sqlStmt.toString(), kvl, countFlag, larsSize, false);
                return transformResultSetToUserInfoVectorHierarchy(rs);

            }

        } catch (NoDataFoundException e) {
            logger.error("SearchUserService --> SearchUserByName() -->  Exception ", e);
            throw new NBError("Search by User Name failed!");
        } catch (Exception e) {
            logger.error("SearchUserService --> SearchUserByName() --> Exception ",  e);
            throw new NBError("Search by User Name failed!");
        }

    }

    /**
     * search user by hierarchy
     * @param searchHierarchy
     * @param depth
     * @param funcType
     * @param sessionAuthorizedUser
     * @return
     * @pre $none
     * @post $none
     * @throws NBException
     */
    public Vector searchUserByHierarchy(String[] searchHierarchy,
                                        short depth, short funcType,
                                        Hashtable sessionAuthorizedUser,
                                        boolean countFlag,
                                        int larsSize) throws NBException {
        logger
                .debug("SearchUserService --> SearchUserByHierarchy() --> START");
        StringBuffer sqlStmt = new StringBuffer();
        int[] hierarchy = null;
        Vector rs = null;

        // int funcType = 0;
        try {
            String roleType =
                    (String) sessionAuthorizedUser.get("currentRoleType");

            // String currentBaseRole = (String)sessionAuthorizedUser.get("currentBaseRole");
            int[] sHierarchy = new int[searchHierarchy.length];

            for (short j = 0; j < searchHierarchy.length; j++) {
                sHierarchy[j] = Integer.parseInt(searchHierarchy[j]);
            }

            // for gcsu
            if (roleType.equals(Constants.ROLETYPE_GCSU)) {
                // Logged-in user is GCSU type or AH type
                if (funcType > 0) {
                    sqlStmt = new StringBuffer(SQL_USER_SEARCH_HEADER_GCSU);
                }
//		else
//		{
//		    sqlStmt =
//			new StringBuffer(SQL_USER_SEARCH_HEADER_GCSU_MAINT);
//		}
                sqlStmt.append("   WHERE e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ATTRIBUTE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ROLE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt
                        .append(" AND( ( EXISTS (SELECT 'X' FROM ACCOUNT a, AGENCY_HL ah "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    WHERE  a.ACCOUNT_NBR = e.ACCOUNT_NBR  "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    AND ah.HIERARCHY_NBR = a.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch(sHierarchy,
                                null, depth);
                if (hierarchy == null) {
                    logger.error("Hierarchy is null");
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append("))" + NEW_LINE_CHARACTER);
                sqlStmt.append(" OR " + NEW_LINE_CHARACTER);
                sqlStmt.append("( " + NEW_LINE_CHARACTER);
                sqlStmt.append(" EXISTS (SELECT 'X' FROM AGENCY_HL ah "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    WHERE ah.HIERARCHY_NBR = e.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(") " + NEW_LINE_CHARACTER);
                sqlStmt.append(")) " + NEW_LINE_CHARACTER);
                sqlStmt.append(" And  ahl.HIERARCHY_NBR = e.HIERARCHY_NBR ");

            } else {
                HierarchyDTO[] uHierarchy =
                        (HierarchyDTO[]) sessionAuthorizedUser
                        .get("currentHierarchy");
                int[] userHierarchy = new int[uHierarchy.length];

                for (int i = 0; i < uHierarchy.length; i++) {
                    userHierarchy[i] = uHierarchy[i].getNumber();
                }
                if (funcType > 0) {
                    sqlStmt = new StringBuffer(SQL_USER_SEARCH_HEADER);
                }
//		else
//		{
//		    sqlStmt = new StringBuffer(SQL_USER_SEARCH_HEADER_MAINT);
//		}
                sqlStmt.append("   WHERE ah.HIERARCHY_NBR = a.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ROLE = 'Y' "
                        + NEW_LINE_CHARACTER);
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch(sHierarchy,
                                userHierarchy, depth);
                if (hierarchy == null) {
                    logger.error("Hierarchy is null");
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }

                // if user is current user has program types also
                if (roleType.equals(Constants.ROLETYPE_HIERARCHYPT)) {
                    sqlStmt.append("   AND ( a.ACCOUNT_NBR = e.ACCOUNT_NBR "
                            + NEW_LINE_CHARACTER);
                    char[] programTypes =
                            (char[]) sessionAuthorizedUser
                            .get("currentProgramTypes");

                    // String[] programTypes = sessionAuthorizedUser.getProgramTypes();
                    if (programTypes == null && programTypes.length <= 0) {
                        throw new NoDataFoundException();
                    }
                    StringBuffer programTypeQString =
                            new StringBuffer("   AND ");

                    // append column name
                    programTypeQString.append(" A.PROG_TYPE ");
                    programTypeQString.append(" IN (");
                    programTypeQString.append("'" + programTypes[0] + "'");
                    for (short i = 1; i < programTypes.length; i++) {
                        programTypeQString.append(", '" + programTypes[i]
                                + "'");
                    }
                    programTypeQString.append(")" + NEW_LINE_CHARACTER);

                    // append query portion for program types
                    sqlStmt.append(programTypeQString.toString());
                    sqlStmt.append("  )" + NEW_LINE_CHARACTER);
                } else {    // none of the above roles
                    sqlStmt.append("   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR  "
                            + NEW_LINE_CHARACTER);
                }
                sqlStmt.append("   AND e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ATTRIBUTE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt.append(" And  ah.HIERARCHY_NBR = e.HIERARCHY_NBR " + NEW_LINE_CHARACTER);
                sqlStmt.append("UNION " + NEW_LINE_CHARACTER);
                if (funcType > 0) {
                    sqlStmt.append(SQL_USER_SEARCH_HEADER2);
                }
//		else
//		{
//		    sqlStmt.append(SQL_USER_SEARCH_HEADER_MAINT2);
//		}
                sqlStmt.append("   WHERE e.DEFAULT_ROLE = 'Y' "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR  "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND (e.DEFAULT_ATTRIBUTE='Y')"
                        + NEW_LINE_CHARACTER);
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(" And  ah.HIERARCHY_NBR = e.HIERARCHY_NBR " + NEW_LINE_CHARACTER);
            }

            // query has constructed now execute
            sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
            logger
                    .debug("SearchUserService --> SearchUserByHierarchy() --> SQL "
                    + sqlStmt);
//	    rs = executeDBQuery(sqlStmt.toString(), false);
            rs = executeDBQuery(sqlStmt.toString(), countFlag, larsSize, false);
        } catch (NoDataFoundException e) {
            logger
                    .debug("SearchUserService --> SearchUserByHierarchy() --> Exception"
                    + e);
        } catch (Exception e) {
            logger
                    .debug("SearchUserService --> SearchUserByHierarchy() --> Exception"
                    + e);
        }
        return transformResultSetToUserInfoVectorHierarchy(rs);
    }

    /**
     * search user by Name an d hierarchy
     * @param firstName
     * @param lastName
     * @param searchHierarchy
     * @param depth
     * @param sessionAuthorizedUser
     * @return
     * @pre $none
     * @post $none
     * @throws NBException
     */
    public Vector searchUserByNameHierarchy(String firstName,
                                            String lastName,
                                            String[] searchHierarchy,
                                            short depth,
                                            Hashtable sessionAuthorizedUser,
                                            boolean countFlag,
                                            int larsSize) throws NBException {
        logger.debug("UserDAO --> SearchUserByNameHierarchy() --> START");
        StringBuffer sqlStmt = new StringBuffer();
        int[] hierarchy = null;

        firstName = firstName.toUpperCase();
        lastName = lastName.toUpperCase();

        KeyValueList kvl = new KeyValueList();

        kvl.add("lastname", "" + lastName, SQLConstants.INPUT_PARAMETER);
        if (!firstName.equals(""))
            kvl.add("firstname", "" + firstName, SQLConstants.INPUT_PARAMETER);


        Vector rs = null;

        try {
            String roleType =
                    (String) sessionAuthorizedUser.get("currentRoleType");
            int[] sHierarchy = new int[searchHierarchy.length];

            for (short j = 0; j < searchHierarchy.length; j++) {
                if (searchHierarchy[j].equals("0000000")
                        || searchHierarchy[j] == "0000000") {
                    sHierarchy[j] = 0;
                } else {
                    try {
                        sHierarchy[j] = Integer.parseInt(searchHierarchy[j]);
                    } catch (NumberFormatException ex) {
                        sHierarchy[j] = 0;
                    }
                }
            }
            if (roleType.equals(Constants.ROLETYPE_GCSU)) {

                // Logged-in user is GCSU type
                sqlStmt = new StringBuffer(SQL_USER_SEARCH_HEADER_GCSU);
                sqlStmt.append("   WHERE e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ATTRIBUTE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ROLE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND u.LAST_NAME = ? "
                        + NEW_LINE_CHARACTER);
                if (!firstName.equals("")) {
                    sqlStmt.append("   AND u.FIRST_NAME = ? " + NEW_LINE_CHARACTER);
                }
                sqlStmt
                        .append(" AND( ( EXISTS (SELECT 'X' FROM ACCOUNT a, AGENCY_HL ah "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    WHERE  a.ACCOUNT_NBR = e.ACCOUNT_NBR  "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    AND ah.HIERARCHY_NBR = a.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch(sHierarchy,
                                (int[]) null, depth);
                if (hierarchy == null) {
                    logger
                            .debug("UserDAO --> SearchUserByNameHierarchy() --> Hierarchy is null");
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(" ))" + NEW_LINE_CHARACTER);
                sqlStmt.append(" OR " + NEW_LINE_CHARACTER);
                sqlStmt.append("( " + NEW_LINE_CHARACTER);
                sqlStmt.append(" EXISTS (SELECT 'X' FROM AGENCY_HL ah "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("    WHERE ah.HIERARCHY_NBR = e.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(") " + NEW_LINE_CHARACTER);
                sqlStmt.append(")) " + NEW_LINE_CHARACTER);
                sqlStmt.append(" And  ahl.HIERARCHY_NBR = e.HIERARCHY_NBR ");
            } else {
                HierarchyDTO[] uHierarchy =
                        (HierarchyDTO[]) sessionAuthorizedUser
                        .get("currentHierarchy");
                int[] userHierarchy = new int[uHierarchy.length];

                for (int i = 0; i < uHierarchy.length; i++) {
                    userHierarchy[i] = uHierarchy[i].getNumber();
                }
                sqlStmt = new StringBuffer(SQL_USER_SEARCH_HEADER);

                sqlStmt.append("   WHERE ah.HIERARCHY_NBR = a.HIERARCHY_NBR "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND u.LAST_NAME = ?"
                        + NEW_LINE_CHARACTER);
                if (!firstName.equals("")) {
                    sqlStmt.append("   AND u.FIRST_NAME = ?" + NEW_LINE_CHARACTER);
                }
                sqlStmt.append("   AND e.DEFAULT_ROLE = 'Y'"
                        + NEW_LINE_CHARACTER);
                hierarchy =
                        UserProfileUtil.authorizeHierarchySearch(sHierarchy,
                                userHierarchy, depth);
                if (hierarchy == null) {
                    logger
                            .debug("UserDAO --> SearchUserByNameHierarchy() --> Hierarchy is null");
                    throw new NoDataFoundException("SYS_E0003:DataAccessAdapter::Unauthorized hierarchy search");
                }
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }

                // if user is current user has program types also
                if (roleType.equals(Constants.ROLETYPE_HIERARCHYPT)) {
                    sqlStmt.append("   AND ( a.ACCOUNT_NBR = e.ACCOUNT_NBR "
                            + NEW_LINE_CHARACTER);
                    char[] programTypes =
                            (char[]) sessionAuthorizedUser
                            .get("currentProgramTypes");

                    if (programTypes == null && programTypes.length <= 0) {
                        logger
                                .debug("UserDAO --> SearchUserByNameHierarchy() --> Program Type is null");
                        throw new NoDataFoundException();
                    }
                    StringBuffer programTypeQString =
                            new StringBuffer("   AND ");

                    // append column name
                    programTypeQString.append("A.PROG_TYPE");
                    programTypeQString.append(" IN (");
                    programTypeQString.append("'" + programTypes[0] + "'");
                    for (short i = 1; i < programTypes.length; i++) {
                        programTypeQString.append(", '" + programTypes[i]
                                + "'");
                    }
                    programTypeQString.append(")" + NEW_LINE_CHARACTER);

                    // append query portion for program types
                    sqlStmt.append(programTypeQString.toString());
                    sqlStmt.append("  )" + NEW_LINE_CHARACTER);
                } else {    // none of the above roles
                    sqlStmt.append("   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR  "
                            + NEW_LINE_CHARACTER);
                }
                sqlStmt.append("   AND e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.DEFAULT_ATTRIBUTE='Y'"
                        + NEW_LINE_CHARACTER);
                sqlStmt.append(" UNION " + NEW_LINE_CHARACTER);
                sqlStmt.append(SQL_USER_SEARCH_HEADER2);
                sqlStmt.append("   WHERE e.DEFAULT_ROLE = 'Y' "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR  "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND u.LAST_NAME = ?"
                        + NEW_LINE_CHARACTER);

                if (!firstName.equals(""))
                    sqlStmt.append("   AND u.FIRST_NAME = ? " + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND e.USERID = u.USERID "
                        + NEW_LINE_CHARACTER);
                sqlStmt.append("   AND (e.DEFAULT_ATTRIBUTE='Y')"
                        + NEW_LINE_CHARACTER);
                for (short j = 0; j < hierarchy.length; j++) {
                    if (hierarchy[j] >= 0) {
                        sqlStmt.append(" AND ah.HL" + j + "=" + hierarchy[j]
                                + NEW_LINE_CHARACTER);
                    }
                }
                sqlStmt.append(" And  ah.HIERARCHY_NBR = e.HIERARCHY_NBR ");
            }

            // query has constructed now execute
            sqlStmt.append(SEARCH_USER_ORDER_BY_CLAUSE);
            logger.debug("UserDAO --> SearchUserByNameHierarchy() --> SQL "
                    + sqlStmt.toString());
            rs = executeDBQuery(sqlStmt.toString(), kvl, countFlag, larsSize, false);
        } catch (NoDataFoundException e) {
            logger.error("UserDAO --> SearchUserByNameHierarchy() --> Exception ",  e);
            throw new NBError("Search by User Name and Hierarchy failed!");
        } catch (Exception e) {
            logger.error("UserDAO --> SearchUserByNameHierarchy() --> Exception ",  e);
            throw new NBError("Search by User Name and Hierarchy failed!");
        }
        return transformResultSetToUserInfoVectorHierarchy(rs);
    }

    /**
     * transform result set hashtable into vector of userInfoDTO type objects
     * @param rs
     * @return
     * @throws NBException
     * @pre $none
     * @post $none
     */
    private Vector transformResultSetToUserInfoVector(Vector rs)
            throws NBException {
        logger
                .debug("UserDAO --> transformResultSetToUserInfoVecto() --> START");
        Vector result = new Vector();

        if (rs != null) {
            Vector users = new Vector();
            UserInfoDTO user = null;
            Hashtable htRowData = null;

            for (int rsCount = 0; rsCount < rs.size(); rsCount++) {
                htRowData = (Hashtable) rs.get(rsCount);
                user = new UserInfoDTO();
                user.setUserId((String) htRowData.get(new Integer(1)));
                user.setLastName((String) htRowData.get(new Integer(2)));
                user.setFirstName((String) htRowData.get(new Integer(3)));
                user.setStatus((String) htRowData.get(new Integer(4)));
                user.setRole((String) htRowData.get(new Integer(5)));
                user.setAccountNumber((String) htRowData.get(new Integer(6)));
                String hnumber = (String) htRowData.get(new Integer(7));

                user.setHierarchyNumber(hnumber);
                if (!hnumber.equals("")) {
                    user.setHierarchy(getHierarchy(hnumber));
                }
                users.addElement(user);
            }
            result = users;
        } else {
            result = new Vector();
        }
        logger
                .debug("UserDAO --> transformResultSetToUserInfoVecto() --> END");
        return result;
    }

    private Vector transformResultSetToUserInfoVectorHierarchy(Vector rs)
            throws NBException {
        logger
                .debug("UserDAO --> transformResultSetToUserInfoVectorHierarchy() --> START");
        Vector result = new Vector();

        if (rs != null) {
            Vector users = new Vector();
            UserInfoDTO user = null;
            Hashtable htRowData = null;

            for (int rsCount = 0; rsCount < rs.size(); rsCount++) {
                htRowData = (Hashtable) rs.get(rsCount);
                user = new UserInfoDTO();
                user.setUserId((String) htRowData.get(new Integer(1)));
                user.setLastName((String) htRowData.get(new Integer(2)));
                user.setFirstName((String) htRowData.get(new Integer(3)));
                user.setStatus((String) htRowData.get(new Integer(4)));
                user.setRole((String) htRowData.get(new Integer(5)));
                user.setAccountNumber((String) htRowData.get(new Integer(6)));
                String hnumber = (String) htRowData.get(new Integer(7));

                user.setHierarchyNumber(hnumber);
                if (!hnumber.equals("")) {
                    String hl0 = (String) htRowData.get(new Integer(8));
                    String hl1 = (String) htRowData.get(new Integer(9));
                    String hl2 = (String) htRowData.get(new Integer(10));
                    String hl3 = (String) htRowData.get(new Integer(11));
                    String hl4 = (String) htRowData.get(new Integer(12));
                    String hl5 = (String) htRowData.get(new Integer(13));
                    String hl6 = (String) htRowData.get(new Integer(14));
                    String hl7 = (String) htRowData.get(new Integer(15));
                    String hl8 = (String) htRowData.get(new Integer(16));

                    user.setHierarchy(hl0 + "."
                            + hl1 + "."
                            + (hl2 == null ? "0" : hl2) + "."
                            + (hl3 == null ? "0" : hl3) + "."
                            + (hl4 == null ? "0" : hl4) + "."
                            + (hl5 == null ? "0" : hl5) + "."
                            + (hl6 == null ? "0" : hl6) + "."
                            + (hl7 == null ? "0" : hl7) + "."
                            + (hl8 == null ? "0" : hl8) + "."
                    );
                }
                users.addElement(user);
            }
            result = users;
        } else {
            result = new Vector();
        }
        logger
                .debug("UserDAO --> transformResultSetToUserInfoVecto() --> END");
        return result;
    }


    /**
     * Method declaration
     *
     *
     * @param hierarchyNumber
     *
     * @return
     */
    public String getHierarchy(String hierarchyNumber) {
        String hrcy = "";

        try {
            HierarchyDAO ser = new HierarchyDAO(null);
            String[] hrcys = ser.getHierarchy(hierarchyNumber);

            for (int i = 0; i < hrcys.length; i++) {
                hrcy += hrcys[i];
                if (i < hrcys.length - 1) {
                    hrcy += ".";
                }
            }
        } catch (Exception ex) {
            logger.debug("UserDAO --> getHierarchy() --> Exception " + ex);
        }
        return hrcy;
    }

    private static final String NEW_LINE_CHARACTER = "\n";

    // -- Static SHORTS
    /*private static final String SQL_USER_SEARCH_BY_ACCOUNT =
	"SELECT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,"
	+ NEW_LINE_CHARACTER + "  TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR"
	+ NEW_LINE_CHARACTER + "  FROM USER_PROFILE u, EAGLS_USERS e"
	+ NEW_LINE_CHARACTER + "  WHERE u.USERID = e.USERID";
      */
    private static final String SQL_USER_SEARCH_BY_ACCOUNT =
            "SELECT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE," + NEW_LINE_CHARACTER +
            "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR" + NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e" + NEW_LINE_CHARACTER +
            " WHERE e.ACCOUNT_NBR = ?" + NEW_LINE_CHARACTER +
            "   AND u.USERID = e.USERID";




/*    private static final String SQL_USER_SEARCH_BY_ACCOUNT_NON_GCSU =
	"SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,"
	+ NEW_LINE_CHARACTER
	+ "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
	+ NEW_LINE_CHARACTER
        +" ah.HL0, ah.HL1, ah.HL2, ah.HL3, ah.HL4, ah.HL5, ah.HL6, ah.HL7, ah.HL8 "
	+ "  FROM USER_PROFILE u, EAGLS_USERS e, agency_hl ah, ACCOUNT A"
	+ NEW_LINE_CHARACTER + " WHERE a.ACCOUNT_NBR = e.ACCOUNT_NBR "
	+ NEW_LINE_CHARACTER + "   AND a.hierarchy_nbr = ah.hierarchy_nbr "
	+ NEW_LINE_CHARACTER + "   AND u.USERID = e.USERID "
	+ NEW_LINE_CHARACTER;    // +
*/

    private static final String SQL_USER_SEARCH_BY_ACCOUNT_NON_GCSU =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE," + NEW_LINE_CHARACTER +
            "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR" + NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e, agency_hl ah, ACCOUNT A" + NEW_LINE_CHARACTER +
            " WHERE e.ACCOUNT_NBR = ?" + NEW_LINE_CHARACTER +
            "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR " + NEW_LINE_CHARACTER +
            "   AND a.hierarchy_nbr = ah.hierarchy_nbr " + NEW_LINE_CHARACTER +
            "   AND u.USERID = e.USERID " + NEW_LINE_CHARACTER;// +



    // "   AND e.DEFAULT_ROLE = 'Y' "+NEW_LINE_CHARACTER;



    private static final String SQL_USER_SEARCH_BY_FULL_NAME =
            "SELECT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,"
            + NEW_LINE_CHARACTER
            + "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR"
            + NEW_LINE_CHARACTER + "  FROM USER_PROFILE u, EAGLS_USERS e "
            + NEW_LINE_CHARACTER + " WHERE  u.USERID = e.USERID "
            + NEW_LINE_CHARACTER + "   AND e.DEFAULT_ROLE='Y' "
            + NEW_LINE_CHARACTER + "   AND (e.DEFAULT_ATTRIBUTE ='Y' or  "
            + NEW_LINE_CHARACTER + "          (e.DEFAULT_ATTRIBUTE is null and "
            + NEW_LINE_CHARACTER
            + "           exists(select 'yes' from valid_roles z"
            + NEW_LINE_CHARACTER
            + "                  where e.user_role = z.user_role "
            + NEW_LINE_CHARACTER + "                  AND z.base_role = 'GCSU')"
            + NEW_LINE_CHARACTER + "           ) " + NEW_LINE_CHARACTER
            + "       )";

    private static final String SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,"
            + NEW_LINE_CHARACTER
            + "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
            + NEW_LINE_CHARACTER
            + " ah.HL0, ah.HL1, ah.HL2, ah.HL3, ah.HL4, ah.HL5, ah.HL6, ah.HL7, ah.HL8"
            + NEW_LINE_CHARACTER
            + "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah, ACCOUNT a"
            + NEW_LINE_CHARACTER + "  WHERE u.USERID = e.USERID "
            + NEW_LINE_CHARACTER + "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR "
            + NEW_LINE_CHARACTER + "   AND e.DEFAULT_ROLE='Y'"
            + NEW_LINE_CHARACTER + "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR "
            + NEW_LINE_CHARACTER + "   AND (e.DEFAULT_ATTRIBUTE='Y')"
            + NEW_LINE_CHARACTER;
    private static final String SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU2 =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE, "
            + NEW_LINE_CHARACTER
            + "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
            + NEW_LINE_CHARACTER
            + " ah.HL0, ah.HL1, ah.HL2, ah.HL3, ah.HL4, ah.HL5, ah.HL6, ah.HL7, ah.HL8"
            + NEW_LINE_CHARACTER
            + "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah "
            + NEW_LINE_CHARACTER + " WHERE u.USERID = e.USERID "
            + NEW_LINE_CHARACTER + "   AND e.DEFAULT_ROLE='Y'"
            + NEW_LINE_CHARACTER + "   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR "
            + NEW_LINE_CHARACTER + "   AND (e.DEFAULT_ATTRIBUTE='Y')"
            + NEW_LINE_CHARACTER;

    private static final String SQL_USER_SEARCH_HEADER_GCSU =
            "SELECT DISTINCT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,"
            + NEW_LINE_CHARACTER
            + "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
            + NEW_LINE_CHARACTER
            + " ahl.HL0, ahl.HL1, ahl.HL2, ahl.HL3, ahl.HL4, ahl.HL5, ahl.HL6, ahl.HL7, ahl.HL8 "
            +

// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e , AGENCY_HL ahl" + NEW_LINE_CHARACTER;

    private static final String SQL_USER_SEARCH_HEADER_GCSU_MAINT =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS"
            + NEW_LINE_CHARACTER +

// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e" + NEW_LINE_CHARACTER;

    private static final String SQL_USER_SEARCH_HEADER =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, "
            + NEW_LINE_CHARACTER
            + "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
            + NEW_LINE_CHARACTER
            + " ah.HL0, ah.HL1, ah.HL2, ah.HL3, ah.HL4, ah.HL5, ah.HL6, ah.HL7, ah.HL8"
            + NEW_LINE_CHARACTER
// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            + "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah, ACCOUNT a" + NEW_LINE_CHARACTER;

    private static final String SQL_USER_SEARCH_HEADER_MAINT =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS"
            + NEW_LINE_CHARACTER +

// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah, ACCOUNT a" + NEW_LINE_CHARACTER;
    private static final String SQL_USER_SEARCH_HEADER2 =
            "SELECT DISTINCT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, "
            + NEW_LINE_CHARACTER
            + "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR,"
            + NEW_LINE_CHARACTER
            + " ah.HL0, ah.HL1, ah.HL2, ah.HL3, ah.HL4, ah.HL5, ah.HL6, ah.HL7, ah.HL8"
            + NEW_LINE_CHARACTER
// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            + "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah " + NEW_LINE_CHARACTER;

    private static final String SQL_USER_SEARCH_HEADER_MAINT2 =
            "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS"
            + NEW_LINE_CHARACTER +

// "       a.HL0, a.HL1, a.HL2, a.HL3, a.HL4, a.HL5, a.HL6, a.HL7, a.HL8,"+NEW_LINE_CHARACTER +
            "  FROM USER_PROFILE u, EAGLS_USERS e, AGENCY_HL ah " + NEW_LINE_CHARACTER;
    private static final String SEARCH_USER_ORDER_BY_CLAUSE = " ORDER BY 1";
}
