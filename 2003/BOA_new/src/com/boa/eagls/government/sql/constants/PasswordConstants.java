/**
 * PasswordConstants
 */
package com.boa.eagls.government.sql.constants;

/**
 * <p>Title: </p> <p>Description: </p> <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public abstract class PasswordConstants
{
    private static final String NEW_LINE_CHARACTER = "\n";
    private final static String SQL_LOAD_PASSWORD =
            "SELECT PASSWD, FAIL_COUNT, TO_CHAR(EXPIRATION_DATE, 'MM/DD/YYYY')"
            + NEW_LINE_CHARACTER + "  FROM USER_PROFILE" + NEW_LINE_CHARACTER
            + " WHERE USERID = :userid";

    /**
     * Stored Procedure to reset a user's password.
     */
    private final static String SP_RESET_PASSWORD =
            "USER_SETTINGS.RESET_PASSWORD(:iUSERID, :iUSERPROFILEID, :iPASSWORD, :oResult)";

    /**
     * Stored Procedure to change a user's password.
     */
    private final static String SP_CHANGE_PASSWORD =
            "USER_SETTINGS.CHANGE_PASSWORD(:iUSERID, :iPASSWORD, :oResult)";

    /**
     *
     */
    private final static String SP_RETRIEVE_PASSWORDS =
            "USER_SETTINGS.GET_INITIAL_RESET_PASSWORDS(" + ":iUserID,"
            + ":oInitialPassword," + ":oDefaultPassword," + ":oResult" + ")";

    /**
     *
     */
    public static final String SP_CHANGE_ORA_PASSWORD =
            "   CI_REPORTING.CHANGE_ORA_PASSWORD( :iUserID, "
            + NEW_LINE_CHARACTER
            + "                                     :iPassword, "
            + NEW_LINE_CHARACTER
            + "                                     :oResult )";
}
