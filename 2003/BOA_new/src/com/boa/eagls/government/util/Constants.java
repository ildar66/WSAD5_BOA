/**
 * Constants
 */
package com.boa.eagls.government.util;

import java.util.Date;

/**
 * Class to hold constants
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none *
 */
public class Constants
{

    /**
     * Private Constructor
     */
    private Constants()
    {
    }

    /**
     * Values
     */
    public static final boolean BOOLEAN_DEFAULT = false;
    public static final int HIERARCHY_LIMIT = 9;
    public static final String STRING_DEFAULT = "ts";
    public static final String STRING_NUMBER_DEFAULT = "00000";
    public static final String[] STRING_NUMBER_ARRAY_DEFAULT =
            {
                "00000", "00001", "00002"
            };
    public static final short SHORT_DEFAULT = 0;
    public static final Date DATE_DEFAULT = new Date();
    public static final int INT_DEFAULT = 0;
    public static final float FLOAT_DEFAULT = 0;
    public static final long LONG_DEFAULT = 0;
    public static final char CHAR_DEFAULT = '+';
    public static final double DOUBLE_DEFAULT = 0;
    public static final int[] INT_ARRAY_DEFAULT =
            {
                0, 1, 2, 3, 4, 5, 6, 7, 8
            };
    public static final String[] STRING_ARRAY_DEFAULT =
            {
                "Alpha", "Beta", "Charlie", "Delta"
            };
    public static final int DEBUG = 1;
    public static final String NEW_USER_PROFILE = "NewUserProfile";
    public static final String LOGGEDIN_USER = "LoggedInUser";
    public static final String STEP1_UPC_USERPROFILE = "Step1UserProfile";
    public static final String STEP2_UPC_ACCOUNTNUM = "Step2AccountNumber";
    public static final String STEP2_UPC_HIERARCHY = "Step2Hierarchy";
    public static final String STEP2_UPC_HIERARCHYNPT =
            "Step2HierarchyAndPT";
    public static final String STEP4_UPC_AddMOREROLE = "Step4AddMoreRole";
    public static final String STEP3_UPC_CONFIRMATION = "Step3Confirmation";

    /**
     * Account type
     */
    public static final short ACCTYPE_INDIVIDUAL = 1;
    public static final short ACCTYPE_MASTERCARD_VEHICLE = 2;

    /**
     * Hierarchy/ base Role
     */
    public static final short PURCHASE = 0;
    public static final short TRAVEL = 1;
    public static final short FLEET = 2;
    public static final short INTEGRATE = 3;
    public static final short INTERAGENCY = 4;

    /**
     * static finals used individualaccount status.
     */
    public static final short OPEN = 0;
    public static final short PAST_DUE = 1;
    public static final short OVER_LIMIT = 2;
    public static final short CLOSED = 3;

    /**
     * static finals for accountType
     */
    public static final short CENTRAL = 0;    // used for BillingType as well
    public static final short INDIVIDUAL =
            1;    // used for BillingType as well
    public static final short MASTERCARD_VEHICLE = 2;
    public static final short DIVERSION_ACCT = 3;

    /**
     * static final for changeAccountStatus
     */
    public static final short ADD = 0;
    public static final short DELETE = 1;

    /**
     * Roles set 1
     */
    public static final String HL_FMS = "FMS";
    public static final String HL_GSA = "GSA";
    public static final String HL_TC = "TC";
    public static final String HL_NFS = "NFS";
    public static final String HL_A = "A";

    /**
     * Roles set 2
     */
    public static final String HL_A_OPC = "A_OPC";
    public static final String HL_DBO = "DBO";
    public static final String HL_TDO = "TDO";
    public static final String HL_NB_ACCTG = "NB_ACCTG";
    public static final String HL_NB_ADM = "NB_ADM";
    public static final String HL_CL = "CL";

    /**
     * Roles set 3
     */
    public static final String HL_AH = "AH";
    public static final String ROLETYPE_HIERARCHYPT = "Hierarchy/PT";
    public static final String ROLETYPE_HIERARCHY = "Hierarchy";
    public static final String ROLETYPE_GCSU = "GCSU";
    public static final String ROLETYPE_ACCOUNT = "Account";
    public static final String HL_GCSU = "GCSU";

    /**
     * Error Causes
     */
    public static final int NO_LOGON_ATTEMPT = -2;
    public static final int ACCESS_DENIED = 1;
    public static final int MAX_LOGON_TRIES = 3;
    public final static short PASSWD_MAX_FAILED_LOGINS = 3;
    public static final short RESET_FAILED_PASSWORD_ATTEMPTS = 4;
    public static final short INVALID_USERID_PASSWORD = 5;
    public static final short DEFAULT_PASSWORD = 7;
    public static final short SUCCESSFUL = 8;
    public static final short BAD_OLD_PASSWORD = 9;
    public static final short BAD_FORMATTED_NEWPASSWORD = 10;
    public static final short STORED_PROCEDURE_ERROR = 11;
    public static final short NEW_PASSWORD_USED_PREVIOUSLY = 12;
    public static final String USERID_KEY = "userID";
    public static final String CURRENT_ROLE_KEY = "currentRole";
    public static final String CURRENT_BASE_ROLE_KEY = "currentBaseRole";
    public static final String CURRENT_ACCOUNT_KEY = "currentAccount";
    public static final String ROLE_FUNCTIONS = "roleFunctions";
    public static final String DYNAMIC_MENU = "dynamicMenu";
    public static final String CURRENT_MENU = "currentMenu";

    public static final String ERR_MSG = "msg";
    public static final String ERR_HEADING = "heading";
    public static final String ERR_DESC = "desc";
    public static final String ERR_SCODE = "scode";

    public static final String HEADER_HEADING = "header.heading";
    public static final String HEADER_SUBHEADING = "header.subheading";

    public static final String SEARCH_PARAM_SEARCHTYPE = "searchType";

    public static final String FUNC_ACC_INQUIRY_MAINTENANCE = "Account Inquiry/Maintenance";
    public static final String FUNC_S_AGENCY = "S-Agency";
    public static final String FUNC_S_ACC_CENTER = "S-Accounting Center";
    public static final String FUNC_S_CENTRAL_ACC = "S-Central Account";
    public static final String FUNC_S_ACCOUNT = "S-Account";
    public static final String FUNC_S_VEHICLE_ACC = "S-Vehicle Account";
    public static final String FUNC_I_AGENCY_PROFILE = "I-Agency Profile";
    public static final String FUNC_I_CENTRAL_ACC = "I-Central Account";
    public static final String FUNC_I_ACCOUNT = "I-Account";
    public static final String FUNC_I_VEHICLE_ACC = "I-Vehicle Account";
    public static final String FUNC_M_AGENCY_PROFILE = "M-Agency Profile";
    public static final String FUNC_M_ACCOUNTING_CENTER = "M-Accounting Center";
    public static final String FUNC_M_CENTRAL_ACC = "M-Central Account";
    public static final String FUNC_M_TRANSFER_ACC = "M-Transfer Account";
    public static final String FUNC_M_TRANSFER_ACC_QUEUE = "M-Transfer Account Queue";
    public static final String FUNC_M_VEHICLE_ACC = "M-Vehicle Account";
    public static final String FUNC_M_ACC_ACTIVATION = "M-Account Activation";
    public static final String FUNC_M_ACC_STATUS = "M-Account Status";
    public static final String FUNC_M_BULK_ACTIVATION = "M-Bulk Activation";
    public static final String FUNC_M_CHANGE_ATM_PIN = "M-Change ATM PIN";
    public static final String FUNC_M_CREDIT_LIMIT = "M-Credit Limit";
    public static final String FUNC_M_QUEUED_REQ = "M-Queued Requests";
    public static final String FUNC_M_TRAVELERS_CHECKS = "M-Travelers Checks";
    public static final String FUNC_M_RENEW_ACC = "M-Renew Account";
    public static final String FUNC_M_REQUEST_CARD = "M-Request Card";
    public static final String FUNC_T_AGENCY_INVOICE = "T-Agency Invoice";
    public static final String FUNC_T_COST_ALLOCATION = "T-Cost Allocation";
    public static final String FUNC_T_INDV_STATEMENT = "T-Individual Statement";
    public static final String FUNC_T_VIEW_CURRENT = "T-View Current";
    public static final String FUNC_T_RAPID_RECON = "T-RapidRecon";
    public static final String FUNC_U_CHANGE_ACCOUNT = "U-Change Account";


    public static final String FUNC_U_CHANGE_HIER = "U-Change Hierarchy";
    public static final String FUNC_U_CHANGE_ROLE = "U-Change Role";
    public static final String FUNC_U_CHANGE_PWD = "U-Change Password";
    public static final String FUNC_U_CREATE_ROLE = "U-Create Role";
    public static final String FUNC_U_MODIFY_ROLE = "U-Modify Role";
    public static final String FUNC_U_RESET_PWD = "U-Reset Password";
    public static final String FUNC_U_CREATE_USER_PROFILE = "U-Create User Profile";
    public static final String FUNC_U_MODIFY_USER_PROFILE = "U-Modify User Profile";
    public static final String FUNC_U_FAVORITES_LIST = "U-Favorites List";
    public static final String FUNC_P_PCN_PROGRAM_FORMS = "P-PCN Program Forms";
    public static final String FUNC_P_NEWSLETTER = "P-Newsletters";
    public static final String FUNC_M_CONVENIENCE_CHECKS = "M-Convenience Checks";


}



