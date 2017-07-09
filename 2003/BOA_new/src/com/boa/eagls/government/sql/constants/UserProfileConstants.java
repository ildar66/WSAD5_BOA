/**
 * UserProfileConstants
 */
package com.boa.eagls.government.sql.constants;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class UserProfileConstants
{

    // public final static String SP_DELETE_USER_PROFILE ="USER_PROFILE_SETUP.DELETE_USER_PROFILE(:userID,:setupUserID,:oResult)";

    /**
     *
     */
    public final static String SP_DELETE_USER_PROFILE =
            "{CALL USER_PROFILE_SETUP.DELETE_USER_PROFILE(?,?,?)}";

    /**
     *
     */
    public final static String SP_CREATE_USER_PROFILE =
            "{CALL USER_PROFILE_SETUP.INSERT_NEW_USER(?,?,?,?,?,?,?,?)}";

    /**
     *
     */
    public final static String SP_CREATE_USER_ROLE =
            "{CALL USER_PROFILE_SETUP.ASSIGN_ROLE(?,?,?,?)}";

    /**
     *
     */
    public final static String SQL_LOAD_USER_PROFILE =
            "SELECT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME, up.SETUP_STATUS, eu.USER_ROLE\n"
            + "  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu\n"
            + " WHERE up.USERID = ?\n" + "   AND eu.USERID = up.USERID\n";    // +

    /**
     *
     */
    public final static String SP_CREATE_USER_HIERARCHY =
            "{CALL USER_PROFILE_SETUP.ASSIGN_HIERARCHY(?,?,?, ?,?,?,?,?,?,?,?,?,?)}";

    /**
     *
     */
    public static final String SQL_FETCH_USER_ROLE_NAMES =
            "SELECT UNIQUE(USER_ROLE), DEFAULT_ROLE\n"
            + "  FROM gsa.EAGLS_USERS eu \n" + " WHERE USERID = ?\n"
            + "   AND (DEFAULT_ATTRIBUTE IS NULL OR DEFAULT_ATTRIBUTE = 'Y')";

    /**
     *
     */
    public final static String SP_CREATE_USER_ACCOUNT =
            "{CALL USER_PROFILE_SETUP.ASSIGN_ACCOUNT_NUMBER(?,?,?,?,?)}";

    /**
     *
     */
    public final static String SP_GET_HIERARCHY_NBR =
            "{CALL STANDARD_OBJECTS.GET_HIERARCHY_NBR(?,?,?,?,?,?,?,?,?,?,?,?)}";

    /**
     *
     */
    public final static String SQL_GET_ACCOUNT_TYPE =
            "SELECT account_type FROM gsa.ACCOUNT WHERE account_nbr = ?";

    /**
     *
     */
    public final static String SP_CREATE_PROGRAM_TYPE =
            "{CALL USER_PROFILE_SETUP.ASSIGN_PROGRAM_TYPE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";

    /**
     *
     */
    public final static String SP_RETRIEVE_CLUSTER_NAME =
            "{CALL USER_SETTINGS.RETRIEVE_CLUSTER_NAME(?,?,?)}";

    /**
     *
     */
    public final static String NEW_SQL_LOAD_USER_PROFILE =
            "SELECT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME, up.SETUP_STATUS, eu.USER_ROLE\n"
            + "  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu\n"
            + " WHERE  eu.DEFAULT_ROLE = 'Y' AND  eu.USERID = up.USERID \n ";    // +

    /**
     *
     */
    public static final String SP_CHANGE_PASSWD_FAIL_COUNT =
            "{CALL EAGLS_LOGON.INCREMENT_FAIL_COUNT(?,?,?)}";

    /**
     *
     */
    public final static String SP_RESET_SESSION_DEFAULTS =
            "{ CALL EAGLS_LOGON.RESET_DEFAULTS(" + ":i?," + // IN  VARCHAR2
            "?)}";								  // OUT VARCHAR2

    /**
     *
     */
    public final static String SP_RETRIEVE_PASSWORDS =
            "{CALL USER_SETTINGS.GET_INITIAL_RESET_PASSWORDS(" + "?," + "?,"
            + "?," + "?" + ")}";

    /**
     *
     */
    public final static String SP_RETRIEVE_INITIAL_PWD =
            "{CALL USER_SETTINGS.GET_USERS_INITIAL_PASSWORD(" + "?," + "?," + "?"
            + ")}";

    /**
     *
     */
    public final static String NEW_SQL_LOAD_VALID_FUNCTIONS =
            "SELECT vrf.user_role_funct " + "FROM valid_role_functions vrf "
            + "WHERE ";

    /**
     *
     */
    public final static String SQL_LOAD_USER_ROLE = "SELECT vr.BASE_ROLE\n"
            + "  FROM gsa.VALID_ROLES vr, gsa.EAGLS_USERS eu\n"
            + " WHERE eu.USERID = ?\n" + "   AND eu.USER_ROLE = ?\n"
            + "   AND eu.USER_ROLE = vr.USER_ROLE\n";

    /**
     *
     */
    public static final String SQL_FETCH_USER_ACCOUNTS =
            "SELECT TO_CHAR(e.ACCOUNT_NBR), e.DEFAULT_ATTRIBUTE, ah.AGENCY_NAME\n"
            + "  FROM gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n"
            + " WHERE e.USERID = ?\n" + "   AND e.USER_ROLE = ?\n"
            + "   AND e.ACCOUNT_NBR = a.ACCOUNT_NBR\n"
            + "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR";

    /**
     *
     */
    public static final String SQL_FETCH_USER_HIERARCHIES =
            "SELECT h.HL0, h.HL1, h.HL2, h.HL3, h.HL4, h.HL5, h.HL6, h.HL7, h.HL8,\n"
            + "       e.PROG_TYPE, e.DEFAULT_ATTRIBUTE, h.AGENCY_NAME, h.EXTENDED_DESCRIPTION\n"
            + "  FROM gsa.EAGLS_USERS e, gsa.AGENCY_HL h\n"
            + " WHERE e.USERID = ?\n" + "   AND e.USER_ROLE = ?\n"
            + "   AND e.HIERARCHY_NBR = h.HIERARCHY_NBR";

    /*
     * public final static short ADD = 0;
     * public final static short REMOVE = 1;
     * // -- SQL TEMPLATES & STORED PROCEDURES ----------------------------------
     * //public final static String SP_DELETE_USER_PROFILE ="USER_PROFILE_SETUP.DELETE_USER_PROFILE(:userID,:setupUserID,:oResult)";
     * public final static String SP_DELETE_USER_PROFILE = "{CALL USER_PROFILE_SETUP.DELETE_USER_PROFILE(?,?,?)}";
     * public final static String SP_CREATE_CUSTOM_ROLE = "CUSTOM_ROLE.CREATE_CUSTOM_ROLE(:userid,:newRole,:baseRole,:oResult)";
     * public final static String SP_ADD_OR_REMOVE_FUNCTION =
     * "CUSTOM_ROLE.ADD_OR_REMOVE_FUNCTION(:userid,:addOrRemove,:newRole,:function,:oResult)";
     * public final static String SP_ASSIGN_ROLE_ATTRIBUTES =
     * "CUSTOM_ROLE.ASSIGN_ROLE_ATTRIBUTES(:userid,:newRole,:description,:oResult)";
     * //public final static String SQL_LOAD_USER_PROFILE ="SELECT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME,
     * // up.SETUP_STATUS, eu.USER_ROLE\n" +"  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu\n" +" WHERE up.USERID = :userid\n"
     * // +"   AND eu.USERID = up.USERID\n"; // +
     * //"   AND eu.DEFAULT_ROLE = 'Y'";
     * public final static String SQL_LOAD_USER_PROFILE =
     * "SELECT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME, up.SETUP_STATUS, eu.USER_ROLE\n" +
     * "  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu\n" + " WHERE up.USERID = ?\n" + "   AND eu.USERID = up.USERID\n"; // +
     * public final static String SQL_LOAD_VALID_FUNCTIONS =
     * "SELECT vrf.user_role_funct " + "FROM valid_role_functions vrf " + "WHERE vrf.user_role = ?";
     * public final static String NEW_SQL_LOAD_VALID_FUNCTIONS =
     * "SELECT vrf.user_role_funct " + "FROM valid_role_functions vrf " + "WHERE ";
     * public final static String NEW_SQL_LOAD_USER_PROFILE =
     * "SELECT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME, up.SETUP_STATUS, eu.USER_ROLE\n" +
     * "  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu\n" + " WHERE  eu.DEFAULT_ROLE = 'Y' AND  eu.USERID = up.USERID \n "; // +
     * public final static String SQL_LOAD_USER_PROFILE_NON_GCSU =
     * "SELECT DISTINCT up.LAST_NAME, up.FIRST_NAME, up.USER_STATUS, up.CLUSTER_NAME, up.SETUP_STATUS, eu.USER_ROLE\n" +
     * "  FROM gsa.USER_PROFILE up, gsa.EAGLS_USERS eu \n" + " WHERE up.USERID = :userid\n" +
     * "   AND eu.USERID = up.USERID\n";
     * //public final static String SP_CREATE_USER_PROFILE =USER_PROFILE_SETUP.INSERT_NEW_USER(:pUSERID, :pFIRSTNAME, :pLASTNAME,
     * // :iPassword, :pQUEUE, :oResult, :pRequester, :iInitialPassword)";
     * public final static String SP_CREATE_USER_PROFILE = "{CALL USER_PROFILE_SETUP.INSERT_NEW_USER(?,?,?,?,?,?,?,?)}";
     * public final static String SQL_LOAD_PASSWORD =
     * "SELECT PASSWD, FAIL_COUNT, TO_CHAR(EXPIRATION_DATE, 'MM/DD/YYYY')\n" + "  FROM gsa.USER_PROFILE\n" +
     * " WHERE USERID = :userid";
     * public final static String SQL_LOAD_USER_ROLE2 =
     * "SELECT vr.BASE_ROLE, TO_CHAR(eu.ACCOUNT_NBR), eu.HIERARCHY_NBR\n" +
     * "  FROM gsa.VALID_ROLES vr, gsa.EAGLS_USERS eu\n" + " WHERE eu.USERID = :userid\n" + "   AND eu.USER_ROLE = :role\n";
     * //public final static String SQL_LOAD_USER_ROLE ="SELECT vr.BASE_ROLE\n" +"  FROM gsa.VALID_ROLES vr, gsa.EAGLS_USERS
     * // eu\n" +" WHERE eu.USERID = :userid\n" +"   AND eu.USER_ROLE = :role\n" +"   AND eu.USER_ROLE = vr.USER_ROLE\n";
     * public final static String SQL_LOAD_USER_ROLE =
     * "SELECT vr.BASE_ROLE\n" + "  FROM gsa.VALID_ROLES vr, gsa.EAGLS_USERS eu\n" + " WHERE eu.USERID = ?\n" +
     * "   AND eu.USER_ROLE = ?\n" + "   AND eu.USER_ROLE = vr.USER_ROLE\n";
     * //public final static String SP_CREATE_USER_ROLE ="USER_PROFILE_SETUP.ASSIGN_ROLE(:userid, :role, :default, :oResult)";
     * public final static String SP_CREATE_USER_ROLE = "{CALL USER_PROFILE_SETUP.ASSIGN_ROLE(?,?,?,?)}";
     * public final static String SQL_RESOLVE_HIERARCHY_LEVELS =
     * "SELECT HL0, HL1, HL2, HL3, HL4, HL5, HL6, HL7, HL8\n" + "  FROM gsa.AGENCY_HL\n" + " WHERE HIERARCHY_NBR = :hnbr";
     * public final static String SQL_LOAD_USER_HIERARCHY =
     * "SELECT eu.PROG_TYPE\n" + "  FROM gsa.EAGLS_USERS eu, gsa.AGENCY_HL ah\n" + " WHERE eu.USERID = :userid\n" +
     * "   AND eu.USER_ROLE = :role\n" + "   AND eu.HIERARCHY_NBR = ah.HIERARCHY_NBR\n" + "   AND ah.HL0 = :hl0\n" +
     * "   AND ah.HL1 = :hl1\n" + "   AND ah.HL2 = :hl2\n" + "   AND ah.HL3 = :hl3\n" + "   AND ah.HL4 = :hl4\n" +
     * "   AND ah.HL5 = :hl5\n" + "   AND ah.HL6 = :hl6\n" + "   AND ah.HL7 = :hl7\n" + "   AND ah.HL8 = :hl8";
     * //public final static String SP_CREATE_USER_HIERARCHY ="USER_PROFILE_SETUP.ASSIGN_HIERARCHY(:userid, :role, :hl0, :hl1,
     * // :hl2, :hl3, :hl4, " +":hl5, :hl6, :hl7, :hl8, :default, :oResult)";
     * public final static String SP_CREATE_USER_HIERARCHY =
     * "{CALL USER_PROFILE_SETUP.ASSIGN_HIERARCHY(?,?,?, ?,?,?,?,?,?,?,?,?,?)}";
     * //public final static String SP_CREATE_PROGRAM_TYPE ="USER_PROFILE_SETUP.ASSIGN_PROGRAM_TYPE(:userid, :role, " +":hl0,
     * // :hl1, :hl2, :hl3, :hl4, :hl5, :hl6, :hl7, :hl8, :progtype, :oResult)";
     * public final static String SP_CREATE_PROGRAM_TYPE =
     * "{CALL USER_PROFILE_SETUP.ASSIGN_PROGRAM_TYPE(?,?,?,?,?,?,?,?,?,?,?,?,?)}";
     * public static final String SP_DELETE_PROGRAM_TYPE =
     * "USER_PROFILE_MAINT.DELETE_PROGRAM_TYPE(\n" + "    :iReqUserID, :iTargetUserID, :iRoleName, :iProgType,\n" +
     * "    :iHL0, :iHL1, :iHL2, :iHL3, :iHL4, :iHL5, :iHL6, :iHL7, :iHL8,\n" + "    :oResult)";
     * //public final static String SP_CREATE_USER_ACCOUNT ="USER_PROFILE_SETUP.ASSIGN_ACCOUNT_NUMBER(:userid, :role, :accnbr, :default, :oResult)";
     * public final static String SP_CREATE_USER_ACCOUNT = "{CALL USER_PROFILE_SETUP.ASSIGN_ACCOUNT_NUMBER(?,?,?,?,?)}";
     * //public final static String SQL_GET_ACCOUNT_TYPE ="SELECT account_type FROM ACCOUNT WHERE gsa.account_nbr = :individualaccount";
     * public final static String SQL_GET_ACCOUNT_TYPE = "SELECT account_type FROM gsa.ACCOUNT WHERE account_nbr = ?";
     * public static final String SQL_SEARCH_USERID_ACCT_NUM_HEADER =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS \n" +
     * "FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e \n" + "WHERE \n";
     * public static final String SQL_USER_SEARCH_GCSU_FOOTER = "   AND (ah.HIERARCHY_NBR = e.HIERARCHY_NBR OR (a.ACCOUNT_NBR = e.ACCOUNT_NBR)) \n"; //+
     * public static final String SQL_SEARCH_USERIDS_ACCT_NUM_NON_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS \n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" + "   WHERE \n" +
     * "   u.USERID = e.USERID \n" + "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR \n" + "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR \n"; //+
     * public static final String SQL_SEARCH_USERIDS_OTHER_CRITERIA_NON_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS \n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah\n" + "   WHERE \n" + "   u.USERID = e.USERID \n" +
     * "   AND e.HIERARCHY_NBR = ah.HIERARCHY_NBR \n";
     * public static final String SQL_SEARCH_USERIDS_HIER_HEADER =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS \n" +
     * "FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n" + "WHERE \n";
     * public static final String SQL_SEARCH_USERIDS_NO_HIER_HEADER =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS \n" +
     * "FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e \n" + "WHERE \n";
     * public static final String SQL_SEARCH_USERID_HIER_FOOTER =
     * "   AND u.USERID = e.USERID \n" + "   AND e.HIERARCHY_NBR = ah.HIERARCHY_NBR \n" + "   Order by u.USERID";
     * public static final String SQL_SEARCH_USERID_NO_HIER_FOOTER = "   AND u.USERID = e.USERID \n" + "   Order by u.USERID";
     * public static final String SQL_SEARCH_USERID_FOOTER_GCSU =
     * "   AND u.USERID = e.USERID \n" + "   AND e.Default_Role = 'Y' \n" +
     * "   AND (e.Default_Attribute = 'Y' or e.Default_Attribute is null)\n";
     * //public static final String SQL_USER_SEARCH_BY_ACCOUNT ="SELECT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,
     * // e.USER_ROLE,\n" +"       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +"  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e\n"
     * // +" WHERE e.ACCOUNT_NBR = :accnbr\n" +"   AND u.USERID = e.USERID";
     * public static final String SQL_USER_SEARCH_BY_ACCOUNT =
     * "SELECT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e\n" +
     * " WHERE e.ACCOUNT_NBR = ?\n" + "   AND u.USERID = e.USERID";
     * //public static final String SQL_USER_SEARCH_BY_ACCOUNT_NON_GCSU ="SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME,
     * // u.USER_STATUS, e.USER_ROLE,\n" +"       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +"  FROM gsa.USER_PROFILE u,
     * // gsa.EAGLS_USERS e, gsa.agency_hl ah, gsa.ACCOUNT A\n" +" WHERE e.ACCOUNT_NBR = :accnbr\n" +"   AND a.ACCOUNT_NBR =
     * // e.ACCOUNT_NBR \n" +"   AND a.hierarchy_nbr = ah.hierarchy_nbr \n" +"   AND u.USERID = e.USERID \n";
     * public static final String SQL_USER_SEARCH_BY_ACCOUNT_NON_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.agency_hl ah, gsa.ACCOUNT A\n" + " WHERE e.ACCOUNT_NBR = ?\n" +
     * "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR \n" + "   AND a.hierarchy_nbr = ah.hierarchy_nbr \n" +
     * "   AND u.USERID = e.USERID \n";
     * public static final String SQL_USER_SEARCH_BY_LAST_NAME =
     * "SELECT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,\n" +
     * "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e \n" +
     * " WHERE u.USERID = e.USERID" + "   AND e.DEFAULT_ROLE='Y' \n" + "   AND (e.DEFAULT_ATTRIBUTE ='Y' or  \n" +
     * "          (e.DEFAULT_ATTRIBUTE is null and \n" + "           exists(select 'yes' from gsa.valid_roles z\n" +
     * "                  where e.user_role = z.user_role \n" + "                  AND z.base_role = 'GCSU')\n" +
     * "           ) \n" + "       )";
     * public static final String SQL_USER_SEARCH_BY_LAST_NAME_NON_GCSU1 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" + " WHERE u.USERID = e.USERID \n" +
     * "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR \n" + "   AND e.DEFAULT_ROLE='Y'\n" +
     * "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR \n" + "   AND e.DEFAULT_ATTRIBUTE='Y'\n";
     * public static final String SQL_USER_SEARCH_BY_LAST_NAME_NON_GCSU2 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, \n" +
     * "  e.user_role,     TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n" + " WHERE u.USERID = e.USERID \n" +
     * "   AND e.DEFAULT_ROLE='Y'\n" + "   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR \n" + "   AND e.DEFAULT_ATTRIBUTE='Y'\n";
     * // Philip Tobin 09/09/99 -- New Search by Hiearchy query
     * public static final String SQL_USER_SEARCH_BY_HIERARCHY1 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, null, null, null \n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" + " WHERE u.USERID = e.USERID \n" +
     * "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR \n" + "   AND e.DEFAULT_ROLE='Y'\n";
     * //Philip Tobin 09/09/99 Part 2 of query search by hierarchy, these two queries are UNIONed together with the hierachies appened to both.
     * public static final String SQL_USER_SEARCH_BY_HIERARCHY2 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, null, null, null\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n" + " WHERE u.USERID = e.USERID \n" +
     * "   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR \n"; //+
     * //public static final String SQL_USER_SEARCH_BY_FULL_NAME ="SELECT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,\n"
     * // +"       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +"  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e \n" +"
     * // WHERE  u.USERID = e.USERID \n" +"   AND e.DEFAULT_ROLE='Y' \n" +"   AND (e.DEFAULT_ATTRIBUTE ='Y' or  \n" +"
     * // (e.DEFAULT_ATTRIBUTE is null and \n" +"           exists(select 'yes' from gsa.valid_roles z\n" +"
     * // where e.user_role = z.user_role \n" +"                  AND z.base_role = 'GCSU')\n" +"           ) \n" +"       )";
     * public static final String SQL_USER_SEARCH_BY_FULL_NAME =
     * "SELECT  u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,\n" +
     * "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e \n" +
     * " WHERE  u.USERID = e.USERID \n" + "   AND e.DEFAULT_ROLE='Y' \n" + "   AND (e.DEFAULT_ATTRIBUTE ='Y' or  \n" +
     * "          (e.DEFAULT_ATTRIBUTE is null and \n" + "           exists(select 'yes' from gsa.valid_roles z\n" +
     * "                  where e.user_role = z.user_role \n" + "                  AND z.base_role = 'GCSU')\n" +
     * "           ) \n" + "       )";
     * public static final String SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" + " WHERE u.USERID = e.USERID \n" +
     * "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR \n" + "   AND e.DEFAULT_ROLE='Y'\n" +
     * "   AND a.ACCOUNT_NBR = e.ACCOUNT_NBR \n" + "   AND (e.DEFAULT_ATTRIBUTE='Y')\n";
     * public static final String SQL_USER_SEARCH_BY_FULL_NAME_NON_GCSU2 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE, \n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n" +
     * " WHERE u.USERID = e.USERID \n" + "   AND e.DEFAULT_ROLE='Y'\n" + "   AND ah.HIERARCHY_NBR = e.HIERARCHY_NBR \n" +
     * "   AND (e.DEFAULT_ATTRIBUTE='Y')\n";
     * ///////////////////////////////
     * public final static String SQL_LOAD_HIERARCHY_FOR_USERID =
     * "SELECT distinct h.hl0, h.hl1, h.hl2, h.hl3, h.hl4, h.hl5, h.hl6, h.hl7, h.hl8\n " +
     * "FROM gsa.AGENCY_HL h, gsa.EAGLS_USERS e \n" + "WHERE e.USERID = :userid\n" +
     * "AND h.hierarchy_nbr = e.hierarchy_nbr \n";
     * public static final String SQL_USER_SEARCH_HEADER =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, \n" +
     * "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n";
     * public static final String SQL_USER_SEARCH_HEADER_MAINT =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n";
     * public static final String SQL_USER_SEARCH_HEADER2 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, \n" +
     * "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n";
     * public static final String SQL_USER_SEARCH_HEADER_MAINT2 =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL ah \n";
     * public static final String SQL_USER_SEARCH_HEADER_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS,\n" +
     * "       e.USER_ROLE, TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e\n";
     * public static final String SQL_USER_SEARCH_HEADER_GCSU_MAINT =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS\n" +
     * "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e\n";
     * public static final String SQL_USER_SEARCH_FOOTER =
     * "   AND u.USERID = e.USERID \n" + "   AND UPPER(e.DEFAULT_ROLE) = 'Y'\n" +
     * "   AND UPPER(e.DEFAULT_ATTRIBUTE) = 'Y'\n" +
     * "   AND (e.HIERARCHY_NBR = a.HIERARCHY_NBR OR e.ACCOUNT_NBR = c.ACCOUNT_NBR)\n";
     * // public static final String SP_CHANGE_PASSWD_FAIL_COUNT = "EAGLS_LOGON.INCREMENT_FAIL_COUNT(:userid, :resetflg, :oResult)";
     * public static final String SP_CHANGE_PASSWD_FAIL_COUNT = "{CALL EAGLS_LOGON.INCREMENT_FAIL_COUNT(?,?,?)}";
     * public static final String SQL_SEARCH_USERS_BY_USERID =
     * "SELECT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e\n" +
     * " WHERE u.USERID = :userid\n" + "   AND u.USERID = e.USERID\n";
     * public static final String SQL_SEARCH_USERS_BY_USERID_NON_GCSU =
     * "SELECT DISTINCT u.USERID, u.LAST_NAME, u.FIRST_NAME, u.USER_STATUS, e.USER_ROLE,\n" +
     * "       TO_CHAR(e.ACCOUNT_NBR), e.HIERARCHY_NBR\n" + "  FROM gsa.USER_PROFILE u, gsa.EAGLS_USERS e, gsa.AGENCY_HL AH\n" +
     * " WHERE u.USERID = :userid\n" + "   AND u.USERID = e.USERID\n";
     * public static final String SP_DELETE_USER_ROLE =
     * // All arguments are VARCHAR2
     * "USER_PROFILE_MAINT.DELETE_ROLE(:iCurrUserID, :iTargetUserID, :iTargetRoleName, :oResult)";
     * public static final String SQL_SEARCH_BY_USER_ROLE =
     * "SELECT vr.USER_ROLE\n" + " FROM gsa.VALID_ROLES vr\n" + "  WHERE vr.USER_ROLE = :role\n";
     * public static final String SP_DELETE_USER_ATTRIBUTE =
     * "USER_PROFILE_MAINT.DELETE_ATTRIBUTE(\n" + "       :iCurrUserID, :iTargetUserID, :iTargetRoleName,\n" +
     * "       :iHL0, :iHL1, :iHL2, :iHL3, :iHL4, :iHL5, :iHL6, :iHL7, :iHL8,\n" + "       :iAcctNumber, :oResult)";
     * public static final String SP_SET_DEFAULT_USER_ROLE =
     * "USER_PROFILE_MAINT.ASSIGN_DEFAULT_ROLE(\n" + "		:iRequestUserID, :iTargetUserID, :iTargetRoleName, :oResult)";
     * public static final String SP_SET_DEFAULT_USER_ATTRIBUTE =
     * "USER_PROFILE_MAINT.ASSIGN_DEFAULT_ATTRIBUTE(\n" + "		:iRequestUserID, :iTargetUserID, :iTargetRoleName,\n" +
     * "       :iHL0, :iHL1, :iHL2, :iHL3, :iHL4, :iHL5, :iHL6, :iHL7, :iHL8,\n" + "		:iAccountNumber, :oResult)";
     * // SELECTS ALL ROLES ASSOCIATED WITH A USERID *
     * //public static final String SQL_FETCH_USER_ROLE_NAMES ="SELECT UNIQUE(USER_ROLE), DEFAULT_ROLE\n" +"  FROM
     * // gsa.EAGLS_USERS eu \n" +" WHERE USERID = :userid\n" +"   AND (DEFAULT_ATTRIBUTE IS NULL OR DEFAULT_ATTRIBUTE = 'Y')";
     * public static final String SQL_FETCH_USER_ROLE_NAMES =
     * "SELECT UNIQUE(USER_ROLE), DEFAULT_ROLE\n" + "  FROM gsa.EAGLS_USERS eu \n" + " WHERE USERID = ?\n" +
     * "   AND (DEFAULT_ATTRIBUTE IS NULL OR DEFAULT_ATTRIBUTE = 'Y')";
     * // SELECTS HIERARCHIES FOR A GIVEN USERID AND ROLE
     * //public static final String SQL_FETCH_USER_HIERARCHIES ="SELECT h.HL0, h.HL1, h.HL2, h.HL3, h.HL4, h.HL5, h.HL6, h.HL7,
     * // h.HL8,\n" +"       e.PROG_TYPE, e.DEFAULT_ATTRIBUTE, h.AGENCY_NAME, h.EXTENDED_DESCRIPTION\n" +"  FROM gsa.EAGLS_USERS
     * // e, gsa.AGENCY_HL h\n" +" WHERE e.USERID = :userid\n" +"   AND e.USER_ROLE = :role\n" +"   AND e.HIERARCHY_NBR = h.HIERARCHY_NBR";
     * public static final String SQL_FETCH_USER_HIERARCHIES =
     * "SELECT h.HL0, h.HL1, h.HL2, h.HL3, h.HL4, h.HL5, h.HL6, h.HL7, h.HL8,\n" +
     * "       e.PROG_TYPE, e.DEFAULT_ATTRIBUTE, h.AGENCY_NAME, h.EXTENDED_DESCRIPTION\n" +
     * "  FROM gsa.EAGLS_USERS e, gsa.AGENCY_HL h\n" + " WHERE e.USERID = ?\n" + "   AND e.USER_ROLE = ?\n" +
     * "   AND e.HIERARCHY_NBR = h.HIERARCHY_NBR";
     * // SELECTS ACCOUNT NUMBERS FOR A GIVEN USERID AND ROLE
     * //public static final String SQL_FETCH_USER_ACCOUNTS ="SELECT TO_CHAR(e.ACCOUNT_NBR), e.DEFAULT_ATTRIBUTE,
     * // ah.AGENCY_NAME\n" +"  FROM gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" +" WHERE e.USERID = :userid\n" +"
     * // AND e.USER_ROLE = :role\n" +"   AND e.ACCOUNT_NBR = a.ACCOUNT_NBR\n" +"   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR";
     * public static final String SQL_FETCH_USER_ACCOUNTS =
     * "SELECT TO_CHAR(e.ACCOUNT_NBR), e.DEFAULT_ATTRIBUTE, ah.AGENCY_NAME\n" +
     * "  FROM gsa.EAGLS_USERS e, gsa.AGENCY_HL ah, gsa.ACCOUNT a\n" + " WHERE e.USERID = ?\n" + "   AND e.USER_ROLE = ?\n" +
     * "   AND e.ACCOUNT_NBR = a.ACCOUNT_NBR\n" + "   AND a.HIERARCHY_NBR = ah.HIERARCHY_NBR";
     *
     * // Stored Procedure to reset a user's password.
     * public final static String SP_RESET_PASSWORD =
     * "USER_SETTINGS.RESET_PASSWORD(:iUSERID, :iUSERPROFILEID, :iPASSWORD, :oResult)";
     *
     * //Stored Procedure to change a user's password.
     * //public final static String SP_CHANGE_PASSWORD ="USER_SETTINGS.CHANGE_PASSWORD(:iUSERID, :iPASSWORD, :oResult)";
     * public final static String SP_CHANGE_PASSWORD = "{CALL USER_SETTINGS.CHANGE_PASSWORD(?,?,?)}";
     *
     * Stored procedure to reset session info to defaults.
     * //    public final static String SP_RESET_SESSION_DEFAULTS = "EAGLS_LOGON.RESET_DEFAULTS(" + ":iUSERID," + //  IN  VARCHAR2
     * //        ":oResult)"; //  OUT VARCHAR2
     * public final static String SP_RESET_SESSION_DEFAULTS = "{ CALL EAGLS_LOGON.RESET_DEFAULTS(" + ":i?," + //  IN  VARCHAR2
     * "?)}"; //  OUT VARCHAR2
     * //public final static String SP_GET_HIERARCHY_NBR ="STANDARD_OBJECTS.GET_HIERARCHY_NBR(:USERID, :HL0, :HL1, :HL2, :HL3,
     * // :HL4, :HL5, :HL6, :HL7, :HL8, :oHierarchyNbr, :oResult)";
     * public final static String SP_GET_HIERARCHY_NBR = "{CALL STANDARD_OBJECTS.GET_HIERARCHY_NBR(?,?,?,?,?,?,?,?,?,?,?,?)}";
     * public final static String SP_UPDATE_USER_STATUS = "USER_PROFILE_MAINT.UPDATE_USER_STATUS(:iUSERID, :iSTATUS, :oResult)";
     * // Stored Procedure to get ClusterName
     * //public final static String SP_RETRIEVE_CLUSTER_NAME ="USER_SETTINGS.RETRIEVE_CLUSTER_NAME(" +":iUserID," +":oClusterName," +":oResult" +")";
     * public final static String SP_RETRIEVE_CLUSTER_NAME = "{CALL USER_SETTINGS.RETRIEVE_CLUSTER_NAME(?,?,?)}";
     * // Stored Procedure to get Initial Password for Modifying Reports Access in User Profile Maintenance
     * // Retrieves initial password from User_Profile database table
     * //    public final static String SP_RETRIEVE_INITIAL_PWD =
     * //        "USER_SETTINGS.GET_USERS_INITIAL_PASSWORD(" + ":iUserID," + ":oPassword," + ":oResult" + ")";
     * //
     * public final static String SP_RETRIEVE_INITIAL_PWD =
     * "{CALL USER_SETTINGS.GET_USERS_INITIAL_PASSWORD(" + "?," + "?," + "?" + ")}";
     * // Stored Procedure to get Initial Password and Default Passwords
     * // Retrieves passwords from EAGLS_DEFAULTS database table
     * //    public final static String SP_RETRIEVE_PASSWORDS =
     * //        "USER_SETTINGS.GET_INITIAL_RESET_PASSWORDS(" + ":iUserID," + ":oInitialPassword," +
     * //        ":oDefaultPassword," + ":oResult" + ")";
     * public final static String SP_RETRIEVE_PASSWORDS =
     * "{CALL USER_SETTINGS.GET_INITIAL_RESET_PASSWORDS(" + "?," + "?," + "?," + "?" + ")}";
     * // Stored Procedure to update User Profile information in the Queue Table.
     * public final static String SP_UPDATE_USER_PROFILE =
     * "QUEUE.UPDATE_USER_PROFILE(" + ":iUserID," + ":iProfileUserID," + ":iStatus," + ":oResult" + ")";
     * public static final String SP_CHANGE_ORA_PASSWORD =
     * "   CI_REPORTING.CHANGE_ORA_PASSWORD( :iUserID, \n" + "                                     :iPassword, \n" +
     * "                                     :oResult )";
     * public final static String SQL_GET_CURRENT_PROGRAM_TYPES =
     * " SELECT PROG_TYPE FROM gsa.EAGLS_USERS \n" + " WHERE USERID = :iUserID AND USER_ROLE = :iUserRole";
     * // -- END SQL TEMPLATES & STORED PROCEDURES ------------------------------
     */
}
