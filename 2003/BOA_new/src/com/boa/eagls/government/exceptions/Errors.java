/**
 * Errors
 */
package com.boa.eagls.government.exceptions;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class Errors
{
    public final static String APP_MSG001 =
            "The Program Number fields contain incomplete or invalid information.";
    public final static String APP_MSG002 =
            "The Account Number field contains incomplete or invalid information.";
    public final static String APP_MSG003 =
            "The Hierarchy Number contains incomplete or invalid information.";
    public final static String APP_MSG004 =
            "An unauthorized search was attempted.";
    public final static String SYS_MSG001 = "Database connection failed";
    public final static String SYS_MSG002 = "Connection to TSYS failed";
    public final static String SYS_MSG003 = "Database type mismatch";
    public final static String SYS_MSG005 =
            "The user does not have hierarchical access.";
    public final static String APP_MSG005 =
            "The user did not have authorized access by hierarchy.";
    public final static String SYS_MSG006 =
            "SQL statement returned a null resultset.";
    public final static String APP_MSG006 =
            "The SQL statement returned no data.";
    public final static String APP_MSG007 =
            "The remote system did not recieve your request. Please try again later.";
    public final static String APP_MSG008 =
            "The query on the database failed.";
}
