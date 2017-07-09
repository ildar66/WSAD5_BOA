/**
 * ReportsConstants
 */
package com.boa.eagls.government.sql.constants;

/**
 * <p>Title: ReportsConstants</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class ReportsConstants
{
    private static final String NEW_LINE_CHARACTER = "\n";

    /**
     * Constructor declaration
     *
     */
    public ReportsConstants()
    {
    }

    /*
     * public final static String SP_ADD_CI_TRANSACTION="CI_REPORTING.ADD_CI_TRANSACTION("+
     * ":iUserID, "+ 				// p_userid             IN     VARCHAR2
     * ":iTransactionType, "+ 		// p_transactiontype    IN     VARCHAR2
     * ":iSetupUserID, "+ 			// p_setupuserid        IN     VARCHAR2
     * ":iPassword, "+ 			// p_password           IN     VARCHAR2
     * ":iFirstName, "+ 			// p_firstname          IN     VARCHAR2
     * ":iLastName, "+ 			// p_lastname           IN     VARCHAR2
     * ":iHierarchyNbr, "+ 		// p_hierarchy_nbr      IN     NUMBER
     * ":iUserRole, "+ 			// p_userrole           IN     VARCHAR2
     * ":iNewPassword, "+ 			// p_newpassword        IN     VARCHAR2
     * ":iReportID, "+ 			// p_newpassword        IN     VARCHAR2
     * ":oResult)"; 				// p_errorcode          OUT    VARCHAR2
     */
    public final static String SP_ADD_CI_TRANSACTION =
            "{CALL CI_REPORTING.ADD_CI_TRANSACTION(?,?,?,?,?,?,?,?,?,?,?)}";

    /**
     * Stored procedure to add an entry to the EAGLS_CI_REPORTS table.
     */
    public final static String SP_ADD_CI_REPORT =
            "CI_REPORTING.ADD_CI_REPORT(" + ":iUserID, "
            + // p_userid             IN     VARCHAR2
            ":iHierarchyNbr, " + // p_hierarchy_nbr      IN     NUMBER
            ":iUserRole, " + // p_userrole           IN     VARCHAR2
            ":iCIReportID, " + // p_cireportid         IN     VARCHAR2
            ":iCIStatus, " + // p_cistatus           IN     VARCHAR2
            ":oResult)";	    // p_errorcode          OUT    VARCHAR2

    /**
     * Embedded SQL to retrieve all roles associated with a hierarchy number and report.
     */
    public final static String SQL_FIND_ROLES =
            "select USER_ROLE from AGENCY_ROLE_REPORTS" + NEW_LINE_CHARACTER
            + "where HIERARCHY_NBR = :hierarchyNbr" + NEW_LINE_CHARACTER
            + "and REPORT_ID = :reportID";

    /**
     *
     */
    public final static String SQL_GET_JULIAN_SECONDS =
            "select to_char((86400 * to_number(to_char(sysdate,'J'))) + "
            + "to_number(to_char(sysdate,'SSSSS'))) from dual";
}
