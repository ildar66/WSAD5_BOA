/**
 * AccountConstants
 */
package com.boa.eagls.government.sql.constants;

import java.util.*;

/**
 *
 * <p>Title: AccountConstants</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class AccountConstants
{
    private static final String NEW_LINE_CHARACTER = "\n";

    /**
     *
     */
    public final static String EMPTY_STRING = " ";

    /**
     *
     */
    public final static String VALIDATE_ACCOUNT =
            "{call STANDARD_OBJECTS_2.VALIDATE_ACCOUNT(?,?,?,?)}";

    /**
     *
     */
    public final static String SP_GET_HIERARCHY_AGENCY_NAME =
            "{CALL STANDARD_OBJECTS.GET_HIERARCHY_AGENCY_NAME(" + "?," + "?,"
            + // p_hl0               OUT     AGENCY_HL.HL0%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl1               OUT     AGENCY_HL.HL1%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl2               OUT     AGENCY_HL.HL2%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl3               OUT     AGENCY_HL.HL3%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl4               OUT     AGENCY_HL.HL4%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl5               OUT     AGENCY_HL.HL5%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl6               OUT     AGENCY_HL.HL6%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl7               OUT     AGENCY_HL.HL7%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl8               OUT     AGENCY_HL.HL8%TYPE,                  /*NUMBER(7)*/
            "?," + // p_hl0description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl1description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl2description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl3description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl4description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl5description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl6description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl7description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?," + // p_hl8description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,/*VARCHAR2(18)*/
            "?" + ")}";

    /*
     * public final static String GET_AUTHORIZATION_CONTROL_INFO = "AUTHORIZATION_CONTROL_INQUIRY.GET_ACCOUNT_LIMIT_INFO(" + ":iUserId," + //p_userid IN
     * ":iAccountNumber," + //p_accountnumber IN
     * ":oCreditLimit," + ":oAccountName," + ":oAgencyName," + ":oCentralAccountId," + ":oSinglePurchaseLimit," +
     * ":oUseMCCGTables," + ":oFCurrencyRestrictionSet," + ":oForeignType," + ":oForeignAction," + ":oVendorCheck," +
     * ":oVendorType," + ":oVendorAction," + ":oPreferredVendorTable," + ":oDailyTransactionLimit," + ":oDailyAmountLimit," +
     * ":oTotalCycleTransactions," + ":oCycleAmountLimit," + ":oMonthlyTransactionLimit," + ":oMonthlyAmountLimit," +
     * ":oOtherRefreshDate," + ":oOtherTotalNumberOfDays," + ":oOtherTotalTransactions," +
     * ":oOtherTotalAmountLimit," + ":oResult)";
     * public final static String GET_MCCG_CONTROL_INFO =
     * "AUTHORIZATION_CONTROL_INQUIRY.GET_MCCG_CONTROL_INFO(" + ":iUserId," + ":iMCCGTableName," + ":iAccountNumber," +
     * ":oAccountName," + ":oAgencyName," + ":oCentralAccountId," + ":oMCCGAction," + ":oSinglePurchaseLimit," +
     * ":oVendorCheck," + ":oVendorType," + ":oVendorAction," + ":oPreferredVendorTable," + ":oDailyTransactionLimit," +
     * ":oDailyAmountLimit," + ":oTotalCycleTransactions," + ":oCycleAmountLimit," + ":oMonthlyTransactionLimit," +
     * ":oMonthlyAmountLimit," + ":oOtherRefreshDate," + ":oOtherTotalNumberOfDays," + ":oOtherTotalTransactions," +
     * ":oOtherTotalAmountLimit," + ":oResult)";
     * public final static String GET_AGENCY_INFO = "STANDARD_OBJECTS_2.GET_AGENCY_INFO(" + ":iUserId," + //p_userid IN
     * ":iCentralAccountId," + // p_CentralAccountID IN
     * ":oPerformCreditChecks," + ":oHL0," + ":oHL1," + ":oHL2," + ":oHL3," + ":oHL4," + ":oHL5," + ":oHL6," + ":oHL7," +
     * ":oHL8," + ":oHL0Desc," + ":oHL1Desc," + ":oHL2Desc," + ":oHL3Desc," + ":oHL4Desc," + ":oHL5Desc," + ":oHL6Desc," +
     * ":oHL7Desc," + ":oHL8Desc," + ":oAgencyName," + ":oAgencyID," + ":oAccountingCenterID," + ":oFleetType," + ":oResult)";
     * public final static String GET_CENTRAL_ID_INFO = "STANDARD_OBJECTS_2.GET_CENTRAL_ID_INFO(" + ":iUserId," + //p_userid IN
     * ":iCentralAccountId," + // p_CentralAccountID IN
     * ":oCentralAccountName," + ":oCentralAccountNumber," + ":oConvenienceChecks," + ":oBillingType," + ":oTravelerChecks," +
     * ":oAtmAccess," + ":oDecrementingCard," + ":oProgramType," + ":oAccountingCenterID," + ":oResult)";
     * public final static String GET_ACCOUNT_STATUS_INFO = "ACCOUNT_HOLDER_INQUIRY.GET_ACCOUNT_STATUS(" + ":iAccountNbr," + // p_CentralAccountID IN
     * ":oStatusDescription1," + ":oStatusDescription2," + ":oStatusDescription3," + ":oAccountStatus," + ":oResult)";
     * public final static String VALIDATE_CENTRAL_ACCOUNT_ID = "STANDARD_OBJECTS_2.VALIDATE_CENTRAL_ACCOUNT_ID(" + ":iUserId," + //p_userid IN
     * ":iCentralAccountId," + // p_CentralAccountID IN
     * ":oResult)";
     * public final static String SP_CHECK_AUTH_CONTROL = "STANDARD_OBJECTS_2.CHECK_AUTH_QUEUE(" + ":iUserID," + //p_userid IN
     * ":iAccountNumber," + // p_accountnumber IN
     * ":oResult)";
     * public final static String GET_REQUEST_ID = "AUTHORIZATION_CONTROL_SETUP.GET_REQUEST_ID(" + ":iUserId," + ":oRequestID," + // p_CentralAccountID IN
     * ":oResult)";
     * public final static String SET_AUTH_CONTROL =
     * "AUTHORIZATION_CONTROL_SETUP.SET_AUTH_CONTROLS(" + ":iUserId," + ":iControlType," + ":iAccountNumber," +
     * ":iSingleTransLimit," + ":iMCCGTableName," + ":iMCCGAction," + ":iForeignRestrictionSet," + ":iForeignType," +
     * ":iForeignAction," + ":iVendorCheck," + ":iVendorType," + ":iVendorAction," + ":iPreferredVendorTable," +
     * ":iDailyTransactionLimit," + ":iDailyAmountLimit," + ":iTotalCycleTransactions," + ":iCycleAmountLimit," +
     * ":iMonthlyTransactionLimit," + ":iMonthlyAmountLimit," + ":iOtherRefreshDate," + ":iOtherTotalNumberOfDays," +
     * ":iOtherTotalTrans," + ":iOtherTotalAmountLimit," + ":iRequestID," + //New one
     * ":oResult)";
     * public final static String ACTIVATE_ACCOUNT =
     * "ACCOUNT_MAINT.ACTIVATE_ACCOUNT(" + ":iUserId," + ":iAccountNumber," + ":oResult)";
     * public final static String GET_CUSTOMER_ID =
     * "ACCOUNT_MAINT.GET_CUSTOMER_ID(" + ":iUserId," + ":iAccountNumber," + ":oCustomerId," + ":oResult)";
     * //public final static String VALIDATE_ACCOUNT = "STANDARD_OBJECTS_2.VALIDATE_ACCOUNT(" +":iUserId,"+":iAccountNumber,"+":oAccountType,"+":oResult)";
     * public final static String VALIDATE_ACCOUNT = "{call STANDARD_OBJECTS_2.VALIDATE_ACCOUNT(?,?,?,?)}";
     * public final static String UPDATE_ACCOUNT_STATUS =
     * "STANDARD_OBJECTS_2.UPDATE_ACCOUNT_STATUS(" + ":iUserId," + ":iAccountNumber," + ":iAccountStatus," + ":oResult)";
     * public final static String GET_CENTRAL_CASH_LIMIT =
     * "STANDARD_OBJECTS_2.GET_CENTRAL_CASH_LIMIT(" + ":iUserId," + ":iCentralAccountID," + ":oCentralCashLimit," +
     * ":oCentralCashStartDate," + ":oCentralCashEndDate," + ":oResult)";
     * public final static String GET_CENTRAL_CREDIT_LIMIT =
     * "STANDARD_OBJECTS_2.GET_CENTRAL_CREDIT_LIMIT(" + ":iUserId," + ":iCentralAccountID," +
     * ":oCentralCreditLimit," + ":oResult)";
     * public final static String GET_AGGREGATE_CREDIT_LIMIT =
     * "STANDARD_OBJECTS_2.GET_AGGREGATE_CREDIT_LIMIT(" + ":iUserId," + ":iCentralAccountID," +
     * ":oAggregateCreditLimit," + ":oResult)";
     * public final static String GET_ACCOUNT_INFO =
     * "ACCOUNT_MAINT.GET_ACCOUNT_INFO(" + ":iUserId," + ":iAccountNumber," + ":oLastName," + ":oFirstName," +
     * ":oEquipmentID," + ":oAgencyName," + ":oAddress1," + ":oAddress2," + ":oAddress3," + ":oAddress4," + ":oCity," +
     * ":oState," + ":oZip," + ":oCountry," + ":oResult)";
     * public final static String UPDATE_CASH_LIMIT =
     * "CASH_LIMIT_MAINT.UPDATE_CASH_LIMIT(" + ":iUserID," + ":iAccountNumber," + ":iCashLimit," + ":iTempCashLimit," +
     * ":iStartDate," + ":iEndDate," + ":iAction," + ":iDataEntry," + ":oResult" + ")";
     * public final static String UPDATE_CREDIT_LIMIT =
     * "CREDIT_LIMIT_MAINT.UPDATE_CREDIT_LIMIT(" + ":iUserID," + ":iAccountNumber," + ":iCreditLimit," +
     * ":iAction," + ":oResult" + ")";
     * public final static String CHANGE_AUTHORIZATION_CONTROL =
     * "AUTHORIZATION_CONTROL_MAINT.CHANGE_AUTHORIZATION_CONTROL(" + ":iUserId," + ":iAccountNumber," + ":iControlType," +
     * ":iRequestID," + ":iMCCGTableName," + ":iSingleTransLimit," + ":iMCCGAction," + ":iForeignRestrictionSet," +
     * ":iForeignType," + ":iForeignAction," + ":iVendorCheck," + ":iVendorType," + ":iVendorAction," +
     * ":iPreferredVendorTable," + ":iDailyTransactionLimit," + ":iDailyAmountLimit," + ":iTotalCycleTransactions," +
     * ":iCycleAmountLimit," + ":iMonthlyTransactionLimit," + ":iMonthlyAmountLimit," + ":iOtherRefreshDate," +
     * ":iOtherTotalNumberOfDays," + ":iOtherTotalTrans," + ":iOtherTotalAmountLimit," + ":iMCCGTableFlag," +
     * ":iAuthorizeUserId," + ":iStatus," + ":iLastStatusDate," + ":iRequesterUserID," + ":iPostDateID," + ":iQueueType," +
     * ":iChangeType," + ":oResult)";
     * public final static String GET_ACCOUNT_SUMMARY = "STANDARD_OBJECTS_3.GET_ACCOUNT_SUMMARY(" + ":iUserId," + //p_userid IN
     * ":iAccountNumber," + // p_CentralAccountID IN
     * ":oAgencyID," + ":oAgencyName," + ":oSinglePurchaseLimit," + ":oCreditLimit," + ":oCentralAccountID," +
     * ":oCentralAccountNumber," + ":oProgramType," + ":oBillingType," + ":oAccountingCode," + ":oAccountingCenterID," +
     * ":oProgramNumber," + ":oHL1," + ":oHL2," + ":oHL3," + ":oHL4," + ":oHL5," + ":oHL6," + ":oHL7," + ":oHL8," +
     * ":oProgramNumberDesc," + ":oHL1Desc," + ":oHL2Desc," + ":oHL3Desc," + ":oHL4Desc," + ":oHL5Desc," + ":oHL6Desc," +
     * ":oHL7Desc," + ":oHL8Desc," + ":oFirstName," + ":oLastName," + ":oEquipmentID," + ":oAccountType," +
     * ":oAccountStatus," + ":oCrvstatus," + ":oResult)";
     * public final static String GET_TRANSFER_ACCOUNT_SUMMARY = "TRANSFER_ACCOUNT.GET_ACCOUNT_SUMMARY(" + ":iUserId," + //p_userid IN
     * ":iAccountNumber," + // p_CentralAccountID IN
     * ":oAgencyID," + ":oAgencyName," + ":oSinglePurchaseLimit," + ":oCreditLimit," + ":oCentralAccountID," +
     * ":oCentralAccountNumber," + ":oProgramType," + ":oBillingType," + ":oAccountingCode," + ":oAccountingCenterID," +
     * ":oProgramNumber," + ":oHL1," + ":oHL2," + ":oHL3," + ":oHL4," + ":oHL5," + ":oHL6," + ":oHL7," + ":oHL8," +
     * ":oProgramNumberDesc," + ":oHL1Desc," + ":oHL2Desc," + ":oHL3Desc," + ":oHL4Desc," + ":oHL5Desc," + ":oHL6Desc," +
     * ":oHL7Desc," + ":oHL8Desc," + ":oFirstName," + ":oLastName," + ":oEquipmentID," + ":oAccountType," +
     * ":oAccountStatus," + ":oCrvstatus," + ":oResult)";
     * public final static String SETUP_MCC_DEFAULTS =
     * "ACCOUNTING_CENTER.SETUP_MCC_DEFAULTS(" + ":iUserId," + ":iAccountingCenterID," + ":iMCCID," + ":iMccStartingRange," +
     * ":iMccEndingRange," + ":iAccountingCode," + ":oResult)";
     * public final static String VALIDATE_MCC_DEFAULTS =
     * "ACCOUNTING_CENTER.VALIDATE_MCC(" + ":iUserId," + ":iAccountingCenterID," + ":iMccStartingRange," +
     * ":iMccEndingRange," + ":oMCCID," + ":oAccountingCode," + ":oResult)";
     * public final static String UPDATE_AUTH_CONTROL_STATUS =
     * "QUEUE.UPDATE_AUTH_CONTROL_STATUS(" + ":iUserID," + ":iRequestID," + ":iAccountNumber," + ":iStatus," + ":oResult)";
     * public static final String SP_QUEUE_TRAVELERS_CHECKS =
     * "ORDER_TRAVELERS_CHECKS.QUEUE_TRAVELERS_CHECKS(" + ":userID, :accountNumber, " +
     * ":requestID, :thomasCookAgentCode, :requestersName, " +
     * ":requesterPhoneNo, :address1, :address2, :city, :state, :country, " + ":zip, :oResult" + ")";
     * public static final String SP_TRAVELERS_CHECK_DETAIL =
     * "ORDER_TRAVELERS_CHECKS.TRAVELERS_CHECK_DETAIL(" + ":userID, :accountNumber, :requestID, :requestNumber, " +
     * ":denomination, :book, :brick, :quantity, :oResult" + ")";
     * public static final String SP_GET_REQUEST_ID =
     * "ORDER_TRAVELERS_CHECKS.GET_REQUEST_ID(" + ":userID, :requestID, :oResult" + ")";
     * /////UPDATE AUTH CONTROL CHANGE
     * public final static String UPDATE_AUTH_CONTROLS_QUEUE =
     * "QUEUE.UPDATE_AUTH_CONTROLS_QUEUE(" + ":iUserID," + ":iRequestID," + ":iAccountNumber," + ":iSingleTransLimit," +
     * ":iMCCGTableName," + ":iMCCGAction," + ":iForeignRestrictionSet," + ":iForeignType," + ":iForeignAction," +
     * ":iVendorCheck," + ":iVendorType," + ":iVendorAction," + ":iPreferredVendorTable," + ":iDailyTransactionLimit," +
     * ":iDailyAmountLimit," + ":iTotalCycleTransactions," + ":iCycleAmountLimit," + ":iMonthlyTransactionLimit," +
     * ":iMonthlyAmountLimit," + ":iOtherRefreshDate," + ":iOtherTotalNumberOfDays," + ":iOtherTotalTrans," +
     * ":iOtherTotalAmountLimit," + ":oResult)";
     * public final static String RETRIEVE_AUTH_QUEUE =
     * "RETRIEVE_QUEUE.RETRIEVE_AUTH_CONTROL_REQUESTS(" + ":iUserID," + ":iRequestID," + ":iControlType," + ":oLastName," +
     * ":oFirstName," + ":oAgencyName," + ":oCentralAcctID," + ":iMCCGTableName," + ":oMCCGAction," + ":oCreditLimit," +
     * ":oSinglePurchaseLimit," + ":oMCCGTables," + ":oForeignRestrictionSet," + ":oForeignType," + ":oForeignAction," +
     * ":oVendorCheck," + ":oVendorType," + ":oVendorAction," + ":oPreferredVendorTable," + ":oDailyTransactionLimit," +
     * ":oDailyAmountLimit," + ":oTotalCycleTransactions," + ":oCycleAmountLimit," + ":oMonthlyTransactionLimit," +
     * ":oMonthlyAmountLimit," + ":oOtherRefreshDate," + ":oOtherTotalNumberOfDays," + ":oOtherTotalTrans," +
     * ":oOtherTotalAmountLimit," + ":oAccountNumber," + ":oResult)";
     * // Retrieve Credit Limt Request from Queue
     * public static final String SP_RETRIEVE_CASH_LIMIT_REQUESTS =
     * "RETRIEVE_QUEUE.RETRIEVE_CASH_LIMIT_REQUESTS(" + ":iUserID," + ":oFirstName," + ":oLastName," + ":oRankGrade," +
     * ":oEmployeeStatus," + ":oCentralAccountID," + ":oEmployeeID," + ":oAgencyName," + ":oAddress1," + ":oAddress2," +
     * ":oAddress3," + ":oAddress4," + ":oCity," + ":oState," + ":oCountry," + ":oZip," + ":iAccountNumber," +
     * ":oOldCashLimit," + ":oNewCashLimit," + ":iRequestID," + ":iStartDate," + ":iEndDate," + ":oHL0," + ":oHL1," +
     * ":oHL2," + ":oHL3," + ":oHL4," + ":oHL5," + ":oHL6," + ":oHL7," + ":oHL8," + ":oHL0Desc," + ":oHL1Desc," +
     * ":oHL2Desc," + ":oHL3Desc," + ":oHL4Desc," + ":oHL5Desc," + ":oHL6Desc," + ":oHL7Desc," + ":oHL8Desc," + ":oRestricted," + //
     * // added by  on 03/06/2003
     * ":oAccountStatus," + // added by  on 03/06/2003
     * ":oResult" + ")";
     * // Retrieve Credit Limt Request from Queue
     * public static final String SP_RETRIEVE_CREDIT_LIMIT_REQUESTS =
     * "RETRIEVE_QUEUE.RETRIEVE_CREDIT_LIMIT_REQUESTS(" + ":iUserID," + ":oFirstName," + ":oLastName," + ":oRankGrade," +
     * ":oEmployeeStatus," + ":oCentralAccountID," + ":oEmployeeID," + ":oAgencyName," + ":oAddress1," + ":oAddress2," +
     * ":oAddress3," + ":oAddress4," + ":oCity," + ":oState," + ":oCountry," + ":oZip," + ":iAccountNumber," +
     * ":oOldCreditLimit," + ":oNewCreditLimit," + ":iRequestID," + ":oHL0," + ":oHL1," + ":oHL2," + ":oHL3," + ":oHL4," +
     * ":oHL5," + ":oHL6," + ":oHL7," + ":oHL8," + ":oHL0Desc," + ":oHL1Desc," + ":oHL2Desc," + ":oHL3Desc," + ":oHL4Desc," +
     * ":oHL5Desc," + ":oHL6Desc," + ":oHL7Desc," + ":oHL8Desc," + ":oResult" + ")";
     * public static final String SP_UPDATE_ACCT_MAINT_QUEUE =
     * "QUEUE.UPDATE_ACCT_MAINT_QUEUE(" + ":iUserID," + ":iAccountNumber," + ":iRequestID," + ":iRequestNumber," +
     * ":iStatus," + ":iNewCreditLimit," + ":oResult" + ")";
     * // Retrieve TravelersChecksRequest from Queue
     * public static final String SP_RETRIEVE_TRAV_CHKS_QUEUE =
     * "RETRIEVE_QUEUE.RETRIEVE_TRAV_CHKS_QUEUE(" + ":iUserID," + ":iAccountNumber," + ":iRequestID," +
     * ":oThomasCookAgentCode," + ":oRequester," + ":oRequesterPhoneNo," + ":oRequestDate," + ":oRequestTime," +
     * ":oLastName," + ":oFirstName," + ":oAddress1," + ":oAddress2," + ":oCity," + ":oState," + ":oCountry," +
     * ":oZip," + ":oResult" + ")";
     * public static final String SP_UPDATE_TRAVELERS_CHECK_QUEUE =
     * "QUEUE.UPDATE_TRAVELERS_CHECK_QUEUE(" + ":iUserID," + ":iAccountNumber," + ":iRequestID," +
     * ":iStatus," + ":oResult" + ")";
     * public final static String SP_RETRIEVE__AUTHCONTROL_CHANGE = "STANDARD_OBJECTS_2.GET_ACCOUNT_INFORMATION(" + ":iUserID," + //
     * // p_userid            IN      EAGLS_USERS.USERID%TYPE,
     * ":iAccountNumber," + // p_accountnumber     IN OUT  VARCHAR2,
     * ":oLastName," + // p_lastname          OUT     VARCHAR2,
     * ":oFirstName," + // p_firstname         OUT     VARCHAR2,
     * ":oAgencyName," + // p_agencyname        OUT     AGENCY_HL.AGENCY_NAME%TYPE,
     * ":oCentralAccountID," + // p_centralaccountid  OUT     ACCOUNT.AGENCY_BILL_NBR%TYPE,
     * ":oHL0," + // p_hl0               OUT     AGENCY_HL.HL0%TYPE,
     * ":oHL1," + // p_hl1               OUT     AGENCY_HL.HL1%TYPE,
     * ":oHL2," + // p_hl2               OUT     AGENCY_HL.HL2%TYPE,
     * ":oHL3," + // p_hl3               OUT     AGENCY_HL.HL3%TYPE,
     * ":oHL4," + // p_hl4               OUT     AGENCY_HL.HL4%TYPE,
     * ":oHL5," + // p_hl5               OUT     AGENCY_HL.HL5%TYPE,
     * ":oHL6," + // p_hl6               OUT     AGENCY_HL.HL6%TYPE,
     * ":oHL7," + // p_hl7               OUT     AGENCY_HL.HL7%TYPE,
     * ":oHL8," + // p_hl8               OUT     AGENCY_HL.HL8%TYPE,
     * ":oHL0Desc," + // p_hl0description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL1Desc," + // p_hl1description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL2Desc," + // p_hl2description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL3Desc," + // p_hl3description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL4Desc," + // p_hl4description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL5Desc," + // p_hl5description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL6Desc," + // p_hl6description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL7Desc," + // p_hl7description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL8Desc," + // p_hl8description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oRankGrade," + // p_rankgrade         OUT     ACCOUNT.RANK_GRADE%TYPE,
     * ":oEmploymentStatus," + // p_employmentstatus  OUT     ACCOUNT.EMPLOYEE_STATUS%TYPE,
     * ":oCreditLimit," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oCashLimit," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oTempCashLimit," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oTempCashStartDate," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oTempCashEndDate," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,           ":oCreditLimit," +		  // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oRestricted," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,           			":oCreditLimit," +		  // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oExpirationDate," + // p_expirationdate    OUT     ACCOUNT.EXPIRATION_DATE%TYPE,
     * ":oAccountStatus," + // p_accountstatus     OUT     ACCOUNT.ACCOUNT_STATUS%TYPE,
     * ":oAddress1," + // p_address1          OUT     ACCOUNT.ADDRESS1%TYPE,
     * ":oAddress2," + // p_address2          OUT     ACCOUNT.ADDRESS2%TYPE,
     * ":oAddress3," + // p_address3          OUT     ACCOUNT.ADDRESS3%TYPE,
     * ":oAddress4," + // p_address4          OUT     ACCOUNT.ADDRESS4%TYPE,
     * ":oCity," + // p_city              OUT     ACCOUNT.CITY%TYPE,
     * ":oState," + // p_state             OUT     ACCOUNT.STATE%TYPE,
     * ":oZip," + // p_zip               OUT     ACCOUNT.ZIP%TYPE,
     * ":oCountry," + // p_country           OUT     ACCOUNT.COUNTRY%TYPE,
     * ":oCardDesign," + // p_carddesign        OUT     CARD_DESIGN.DESCRIPTION%TYPE,
     * ":oCRVStatus," + ":isPastDue," + ":oResult" + // p_errorcode         OUT     VARCHAR2
     * ")";
     * public final static String SP_RETRIEVE__AUTHCONTROL_SETUP_CHANGE = "STANDARD_OBJECTS_2.GET_TEMP_ACCOUNT_INFORMATION(" + ":iUserID," + //
     * // p_userid            IN      EAGLS_USERS.USERID%TYPE,
     * ":iAccountNumber," + // p_accountnumber     IN OUT  VARCHAR2,
     * ":oLastName," + // p_lastname          OUT     VARCHAR2,
     * ":oFirstName," + // p_firstname         OUT     VARCHAR2,
     * ":oAgencyName," + // p_agencyname        OUT     AGENCY_HL.AGENCY_NAME%TYPE,
     * ":oCentralAccountID," + // p_centralaccountid  OUT     ACCOUNT.AGENCY_BILL_NBR%TYPE,
     * ":oHL0," + // p_hl0               OUT     AGENCY_HL.HL0%TYPE,
     * ":oHL1," + // p_hl1               OUT     AGENCY_HL.HL1%TYPE,
     * ":oHL2," + // p_hl2               OUT     AGENCY_HL.HL2%TYPE,
     * ":oHL3," + // p_hl3               OUT     AGENCY_HL.HL3%TYPE,
     * ":oHL4," + // p_hl4               OUT     AGENCY_HL.HL4%TYPE,
     * ":oHL5," + // p_hl5               OUT     AGENCY_HL.HL5%TYPE,
     * ":oHL6," + // p_hl6               OUT     AGENCY_HL.HL6%TYPE,
     * ":oHL7," + // p_hl7               OUT     AGENCY_HL.HL7%TYPE,
     * ":oHL8," + // p_hl8               OUT     AGENCY_HL.HL8%TYPE,
     * ":oHL0Desc," + // p_hl0description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL1Desc," + // p_hl1description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL2Desc," + // p_hl2description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL3Desc," + // p_hl3description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL4Desc," + // p_hl4description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL5Desc," + // p_hl5description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL6Desc," + // p_hl6description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL7Desc," + // p_hl7description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oHL8Desc," + // p_hl8description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * ":oRankGrade," + // p_rankgrade         OUT     ACCOUNT.RANK_GRADE%TYPE,
     * ":oEmploymentStatus," + // p_employmentstatus  OUT     ACCOUNT.EMPLOYEE_STATUS%TYPE,
     * ":oCreditLimit," + // p_creditlimit       OUT     ACCOUNT.CREDIT_LIMIT%TYPE,
     * ":oExpirationDate," + // p_expirationdate    OUT     ACCOUNT.EXPIRATION_DATE%TYPE,
     * ":oAccountStatus," + // p_accountstatus     OUT     ACCOUNT.ACCOUNT_STATUS%TYPE,
     * ":oAddress1," + // p_address1          OUT     ACCOUNT.ADDRESS1%TYPE,
     * ":oAddress2," + // p_address2          OUT     ACCOUNT.ADDRESS2%TYPE,
     * ":oAddress3," + // p_address3          OUT     ACCOUNT.ADDRESS3%TYPE,
     * ":oAddress4," + // p_address4          OUT     ACCOUNT.ADDRESS4%TYPE,
     * ":oCity," + // p_city              OUT     ACCOUNT.CITY%TYPE,
     * ":oState," + // p_state             OUT     ACCOUNT.STATE%TYPE,
     * ":oZip," + // p_zip               OUT     ACCOUNT.ZIP%TYPE,
     * ":oCountry," + // p_country           OUT     ACCOUNT.COUNTRY%TYPE,
     * ":oCardDesign," + // p_carddesign        OUT     CARD_DESIGN.DESCRIPTION%TYPE,
     * ":oCRVStatus," + ":isPastDue," + ":oResult" + // p_errorcode         OUT     VARCHAR2
     * ")";
     * public final static String SP_CHECK_USER_ACCESS =
     * "STANDARD_OBJECTS.CHECK_USER_ACCESS(" + ":iUserID," + ":iAccountNumber," + ":oAccessFlag," + ":oResult" + ")";
     * public final static String SQL_RETRIEVE_VEHICLE_FLAG = "SELECT VEHICLE_FLAG FROM ACCOUNT WHERE ACCOUNT_NBR = :iAcctNo"+NEW_LINE_CHARACTER;
     * public final static String SQL_GET_AUTH_CONTROL_QUEUE =
     * "SELECT NVL(ACQ.MCCG_TABLE_NAME, 'Account Control'),ACQ.CONTROL_TYPE, ACQ.CHANGE_TYPE"+NEW_LINE_CHARACTER +
     * "FROM AUTH_CONTROL_QUEUE ACQ"+NEW_LINE_CHARACTER + "WHERE ACQ.REQUEST_ID = :iRequestID"+NEW_LINE_CHARACTER;
     * ///EMBEDDED SQL'S  ///EMBEDDED SQL'S   ///EMBEDDED SQL'S
     * ///EMBEDDED SQL'S  ///EMBEDDED SQL'S   ///EMBEDDED SQL'S
     * public final static String SQL_SEARCH_ACCOUNT_HEADER =
     *
     * "SELECT  a.FIRST_NAME, a.LAST_NAME, TO_CHAR(a.ACCOUNT_NBR), a.ACCOUNT_TYPE, v.VIN,"+NEW_LINE_CHARACTER +
     * " a.ACCOUNT_STATUS, a.CRV_STATUS "+NEW_LINE_CHARACTER + " FROM ACCOUNT a, AGENCY_HL h, VEHICLE_ACCOUNT v"+NEW_LINE_CHARACTER;
     * // SQL WHERE clause for search-by-last-name method
     * public final static String SQL_SEARCH_BY_LAST_NAME_CLAUSE = "  WHERE :lastname = a.LAST_NAME"+NEW_LINE_CHARACTER;
     * // SQL WHERE clause for search-by-full-name method
     * public final static String SQL_SEARCH_BY_FULL_NAME_CLAUSE =
     * "  WHERE :lastname = a.LAST_NAME"+NEW_LINE_CHARACTER + "    AND :firstname = a.FIRST_NAME"+NEW_LINE_CHARACTER;
     * public final static String SQL_SEARCH_ACCOUNT_FOOTER = "    AND a.HIERARCHY_NBR = h.HIERARCHY_NBR"+NEW_LINE_CHARACTER +
     * //   "    AND a.ACCOUNTING_CENTER_ID is not NULL"+NEW_LINE_CHARACTER +
     * "    AND a.ACCOUNT_NBR = v.ACCOUNT_NBR(+)"+NEW_LINE_CHARACTER;
     *
     *
     * public final static String SQL_SEARCH_ACCOUNT_ACCTSTATUS_FOOTER =
     * "    AND a.HIERARCHY_NBR = h.HIERARCHY_NBR"+NEW_LINE_CHARACTER + "    AND a.ACCOUNT_NBR = v.ACCOUNT_NBR(+)"+NEW_LINE_CHARACTER +
     * "    AND (a.ACCOUNT_TYPE = 'I' "+NEW_LINE_CHARACTER + "         OR v.VIN IS NOT NULL) "+NEW_LINE_CHARACTER;
     * public final static String SQL_SEARCH_CARD_REQUEST_FOOTER = "    AND (a.CARD_FLAG ='Y' OR a.CARD_FLAG IS null)"+NEW_LINE_CHARACTER +
     * // "    AND a.CARD_FLAG != 'N'"+NEW_LINE_CHARACTER +
     * "    AND (a.ACCOUNT_TYPE='I' OR a.ACCOUNT_TYPE='T')"+NEW_LINE_CHARACTER + "    AND a.HIERARCHY_NBR = h.HIERARCHY_NBR"+NEW_LINE_CHARACTER +
     * "    AND a.ACCOUNT_NBR = v.ACCOUNT_NBR(+)"+NEW_LINE_CHARACTER +
     * Added additional conditions from here for performance enhancement. Earlier
     * following logics were in Account controller.
     * "    AND (a.ACCOUNT_TYPE = 'C' "+NEW_LINE_CHARACTER + "    OR (a.ACCOUNT_STATUS = 'O' "+NEW_LINE_CHARACTER + "    AND NOT EXISTS "+NEW_LINE_CHARACTER +
     * "    (SELECT '1' "+NEW_LINE_CHARACTER +
     * "    FROM ACCOUNT A2, ACCOUNT_STATEMENT AST, ACCOUNT_STATUS S, REASON_CODE R "+NEW_LINE_CHARACTER +
     * "    WHERE  A.ACCOUNT_NBR = S.ACCOUNT_NBR "+NEW_LINE_CHARACTER + "    AND A2.ACCOUNT_NBR = A.ACCOUNT_NBR "+NEW_LINE_CHARACTER +
     * "    AND A.ACCOUNT_NBR = AST.ACCOUNT_NBR "+NEW_LINE_CHARACTER + "    AND R.REASON_CODE = S.REASON_CODE "+NEW_LINE_CHARACTER +
     * "    AND R.TSYS_STATUS_CODE = S.TSYS_STATUS_CODE "+NEW_LINE_CHARACTER + "    AND (((UPPER(a.billing_cycle) between '01' and '31') "+NEW_LINE_CHARACTER +
     * "             AND (((R.reason_code = '02') AND (AST.payment_due_date < trunc(sysdate + 6))) "+NEW_LINE_CHARACTER +
     * "                       OR (R.REASON_CODE = '03'))) "+NEW_LINE_CHARACTER +
     * "                     OR ((UPPER(a.billing_cycle) IN ('WM','WT','WW','WH','WF')) AND "+NEW_LINE_CHARACTER +
     * "                   (((R.reason_code = '08') AND (AST.payment_due_date >  trunc(sysdate + 3))) "+NEW_LINE_CHARACTER +
     * "                       OR (R.REASON_CODE IN ('09','1A','1B')) "+NEW_LINE_CHARACTER +
     * "                       OR ((UPPER(R.reason_code) = '1C') AND (AST.payment_due_date < trunc(sysdate + 5))))) "+NEW_LINE_CHARACTER +
     * "                     OR ((UPPER(a.billing_cycle) IN ('BM','BT','BW','BH','BF')) AND "+NEW_LINE_CHARACTER +
     * "                   (((R.reason_code = '04') AND (AST.payment_due_date > trunc(sysdate + 3))) "+NEW_LINE_CHARACTER +
     * "                       OR (R.REASON_CODE = '05') "+NEW_LINE_CHARACTER +
     * "                       OR ((R.reason_code = '06') AND (AST.payment_due_date < trunc(sysdate + 6))))) "+NEW_LINE_CHARACTER +
     * "        ) "+NEW_LINE_CHARACTER + "    ) "+NEW_LINE_CHARACTER +
     * "    ) "+NEW_LINE_CHARACTER + //end OR
     * "   ) "+NEW_LINE_CHARACTER; //end
     * public final static String SQL_SEARCH_MCCG_TABLE_NAMES_HEADER =
     * "SELECT  m.MCCG_TABLE_NAME " + "FROM   MCC_CONTROL m, ACCOUNT a " +
     * "WHERE  m.MCC_OPT_SET_ID = a.MCC_OPT_SET_ID"+NEW_LINE_CHARACTER + "AND    a.account_Nbr = :accountnbr";
     * public final static String SQL_SEARCH_EMPLOYMENT_STATUS =
     * "SELECT  e.STATUS, e.DESCRIPTION" + " FROM EMPLOYMENT_STATUS e " +
     * " WHERE E.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR "+NEW_LINE_CHARACTER + " FROM ACCOUNT a "+NEW_LINE_CHARACTER + " WHERE a.ACCOUNT_TYPE = 'C' " +
     * " AND a.AGENCY_BILL_NBR = :iCentralAccountId)";
     * //Added order by - VK
     * public final static String SQL_SEARCH_RANK_GRADE =
     * " SELECT r.RANK_GRADE " + " FROM RANK_GRADE r " + " WHERE r.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR  "+NEW_LINE_CHARACTER +
     * " FROM ACCOUNT A  "+NEW_LINE_CHARACTER + " WHERE A.ACCOUNT_TYPE = 'C'  "+NEW_LINE_CHARACTER + " AND A.AGENCY_BILL_NBR = :iCentralAccountId)  "+NEW_LINE_CHARACTER +
     * " ORDER BY r.RANK_GRADE ";
     * public final static String SQL_ACCOUNTING_CENTER_IDS =
     * "SELECT ACCOUNTING_CENTER_ID"+NEW_LINE_CHARACTER + "FROM BILLING_AGENCY"+NEW_LINE_CHARACTER + "WHERE AGENCY_BILL_NBR = :iCentralAccountId"+NEW_LINE_CHARACTER;
     * public final static String SQL_SEARCH_CARD_TYPES =
     * "SELECT c.CMID, c.DESCRIPTION " + "FROM CARD_DESIGN c, ACCOUNT a " + "WHERE a.ACCOUNT_TYPE = 'C' " + //MM--MODIFIED AS
     * // PER REQUETS FROM PERFORMANCE TEAM
     * "AND a.AGENCY_BILL_NBR = :iCentralAccountId " + "AND a.HIERARCHY_NBR = c.HIERARCHY_NBR(+) ";
     * //Reverted back to older version need to check with SME--Muragesh--09/03
     * //  "SELECT c.CMID, c.DESCRIPTION"+
     * //  "FROM CARD_DESIGN c, ACCOUNT a, CENTRAL_OFFICE co"+
     * //  "WHERE a.HIERARCHY_NBR = co.HIERARCHY_NBR"+
     * //  "AND co.OFFICE_ID = c.OFFICE_ID"+
     * //  "AND a.ACCOUNT_TYPE='C'"+
     * //  "AND a.AGENCY_BILL_NBR =:iCentralAccountId";
     * public final static String SQL_SEARCH_RENEWABLE_ACCOUNTS =
     * "SELECT  ah.hl0, ah.hl1, ah.hl2, ah.hl3, ah.hl4, ah.hl5, ah.hl6, ah.hl7, ah.hl8, " + //9
     * "       "+NEW_LINE_CHARACTER+"\tah.hierarchy_nbr, TO_CHAR(a.account_nbr), a.first_name, a.last_name, " + //13
     * "       \trank_grade, employee_status, credit_limit, substr(expiration_date,1,2)||'/'||substr(expiration_date,3,6), account_status, "+NEW_LINE_CHARACTER +
     * "       \tagency_name, a.crv_status"+NEW_LINE_CHARACTER + //20
     * "  FROM individualaccount a, agency_hl ah "+NEW_LINE_CHARACTER + " WHERE a.hierarchy_nbr = ah.hierarchy_nbr "+NEW_LINE_CHARACTER +
     * //"   AND a.debit_card_flag is not NULL "+NEW_LINE_CHARACTER +
     * "   AND (a.crv_status = 'N' OR a.crv_status is NULL) "+NEW_LINE_CHARACTER + "   AND a.ACCOUNT_TYPE != 'D' AND a.ACCOUNT_TYPE != 'C' "+NEW_LINE_CHARACTER +
     * "   AND (a.account_status = 'O' or a.account_status is NULL)"+NEW_LINE_CHARACTER;
     * public static final String SP_UPDATE_ACCOUNT_STATUS =
     * "STANDARD_OBJECTS_2.UPDATE_ACCOUNT_STATUS(" +
     * ":userID,:accountNumber,:accountStatus,:accountStatusReason,:oResult" + ")";
     * public static final String SQL_SEARCH_TRAVELERS_CHECKS_ACCOUNTS =
     * ""+NEW_LINE_CHARACTER + "SELECT to_char(A.ACCOUNT_NBR), A.FIRST_NAME, A.LAST_NAME, H.AGENCY_NAME, A.ACCOUNT_TYPE\n" +
     * "  FROM AGENCY_HL H, CENTRAL_OFFICE C, ACCOUNT A\n" + " WHERE A.HIERARCHY_NBR = H.HIERARCHY_NBR\n" +
     * "   AND C.HIERARCHY_NBR = A.HIERARCHY_NBR\n" + "   AND C.TRAVELERS_CHECK__FLAG = 'Y'\n" +
     * "   AND A.TRAVELERS_CHECK_FLAG = 'Y'\n" + "   AND C.TRAVELERS_CHECK_INVENTORY != 'Y'\n" +
     * "   AND A.ACCOUNTING_CENTER_ID is not NULL\n" + "   AND (A.ACCOUNT_TYPE='I' OR A.ACCOUNT_TYPE='C' OR A.ACCOUNT_TYPE='D')" +
     * "   AND (A.ACCOUNT_STATUS = 'O' OR A.ACCOUNT_STATUS is NULL)" + "    AND NOT EXISTS \n" + "    (SELECT '1' \n" +
     *
     * "    FROM ACCOUNT A2, ACCOUNT_STATEMENT AST, ACCOUNT_STATUS S, REASON_CODE R \n" +
     * "    WHERE  A.ACCOUNT_NBR = S.ACCOUNT_NBR \n" + "    AND A2.ACCOUNT_NBR = A.ACCOUNT_NBR \n" +
     * "    AND A.ACCOUNT_NBR = AST.ACCOUNT_NBR \n" + "    AND R.REASON_CODE = S.REASON_CODE \n" +
     * "    AND R.TSYS_STATUS_CODE = S.TSYS_STATUS_CODE \n" + "    AND (((UPPER(a.billing_cycle) between '01' and '31') \n" +
     * "             AND (((R.reason_code = '02') AND (AST.payment_due_date < trunc(sysdate + 6))) \n" +
     * "                       OR (R.REASON_CODE = '03'))) \n" +
     * "                     OR ((UPPER(a.billing_cycle) IN ('WM','WT','WW','WH','WF')) AND \n" +
     * "                   (((R.reason_code = '08') AND (AST.payment_due_date >  trunc(sysdate + 3))) \n" +
     * "                       OR (R.REASON_CODE IN ('09','1A','1B')) \n" +
     * "                       OR ((UPPER(R.reason_code) = '1C') AND (AST.payment_due_date < trunc(sysdate + 5))))) \n" +
     * "                     OR ((UPPER(a.billing_cycle) IN ('BM','BT','BW','BH','BF')) AND \n" +
     * "                   (((R.reason_code = '04') AND (AST.payment_due_date > trunc(sysdate + 3))) \n" +
     * "                       OR (R.REASON_CODE = '05') \n" +
     * "                       OR ((R.reason_code = '06') AND (AST.payment_due_date < trunc(sysdate + 6))))) \n" +
     * "        ) \n" + "    ) \n";
     *
     *
     *
     * //Getting the officer code from the NationsBank Table which has only one instance
     * public final static String SQL_SEARCH_NB_OFFICER_CODE = "SELECT OFFICER_CODE FROM NATIONSBANK\n";
     * public final static String SQL_SEARCH_BULK_ACCT_HEADER =
     * "SELECT  TO_CHAR(a.ACCOUNT_NBR), a.LAST_NAME,  a.FIRST_NAME, TO_CHAR(a.CREDIT_LIMIT), TO_CHAR(a.ACCOUNT_OPEN_DATE, 'MM/DD/YYYY'), h.AGENCY_NAME, v.VIN\n" +
     * " FROM AGENCY_HL h, VEHICLE_ACCOUNT v, ACCOUNT a\n" + " WHERE a.CRV_STATUS = 'Y'\n" +
     * " AND (a.ACCOUNT_STATUS = 'O' OR a.ACCOUNT_STATUS is NULL)\n";
     * public final static String SQL_SEARCH_BULK_ACCT_FOOTER =
     * "   AND a.HIERARCHY_NBR = h.HIERARCHY_NBR\n" + "    AND a.ACCOUNT_NBR = v.ACCOUNT_NBR(+)\n";
     * public static final String SP_CHANGE_ACCOUNT_STATUS =
     * "STANDARD_OBJECTS_2.CHANGE_ACCOUNT_STATUS(" +
     * ":iUserId,:iAccountNumber,:iAccountStatus,:iAccountStatus2,:iReasonCode,:iAction,:oResult)";
     * public static final String SQL_RETRIEVE_ACCOUNT_STATUSES =
     * "SELECT acs.tsys_status_code,rc.account_status,acs.reason_code,to_char(acs.status_date,'MM/DD/YYYY')," +
     * "acs.priority, rc.description\n" + "  FROM account_status acs, reason_code rc\n" +
     * " WHERE acs.account_nbr = :iAccountNumber\n" + "   AND acs.tsys_status_code = rc.tsys_status_code\n" +
     * "   AND acs.reason_code = rc.reason_code\n";
     * public static final String SQL_RETRIEVE_ACTIVATION_DATES =
     * "\nSELECT  to_char(activate_date, 'MM/DD/YYYY'), to_char(deactivate_date, 'MM/DD/YYYY')\n" +
     * " FROM account_activation\n" + " WHERE account_nbr = :iAccountNumber\n";
     * public static final String SP_ADD_ACTIVATION_DATES =
     * "STANDARD_OBJECTS_2.ADD_ACTIVATION_DATE(" + ":iUserID,:iAccountNumber,:iDeacDate,:iAcDate,:oResult)";
     * public static final String SQL_RETRIEVE_VENDOR_NAMES =
     * "\nSELECT  preferred_vendor_table_name, vendor_description\n" + " FROM preferred_vendor\n" +
     * " WHERE agency_bill_nbr = :iCentralAcctID\n";
     * //Added thid method to retrieve data from ACCOUNT_CONTROL table--LAC3099
     * public static final String SQL_RETRIEVE_ACC_VENDOR_NAMES =
     * "SELECT  DISTINCT(ac.VENDOR_MCCG_TABLE)\n" + " FROM  ACCOUNT_CONTROL ac, ACCOUNT a\n" +
     * " WHERE a.AGENCY_BILL_NBR = :iCentralAcctID\n" + " AND  ac.ACCT_OPT_SET_ID = a.ACCT_OPT_SET_ID";
     * public static final String SQL_RETRIEVE_MCCGTABLE_NAMES =
     * "SELECT  mccg_table_name, mccg_description\n" + " FROM mccg\n" +
     * " WHERE agency_bill_nbr = :iCentralAcctID\n";
     * //Added to retrieve data from MCC_CONTROL as per LAC3099--06/06/00--MM
     * public static final String SQL_RETRIEVE_MCC_CONTROL_TABLE_NAMES =
     * "SELECT  DISTINCT(m.VENDOR_MCCG_TABLE)\n" + "FROM MCC_CONTROL m, ACCOUNT a\n" +
     * "WHERE a.AGENCY_BILL_NBR = :iCentralAcctID\n" + "AND m.MCC_OPT_SET_ID = a.MCC_OPT_SET_ID\n";
     * public static final String SQL_RETRIEVE_PROG_TYPE =
     * "SELECT PROG_TYPE FROM ACCOUNT \n" + "WHERE ACCOUNT_NBR = :iAccountNumber ";
     * //     public static final String SQL_SEARCH_TRANSFER_ACCOUNT_HEADER =
     * //     "SELECT  A.FIRST_NAME, TO_CHAR(A.ACCOUNT_NBR), A.ZIP, A.LAST_NAME, A.WORK_PHONE, A.SSN \n"+
     * //    	 " FROM AGENCY_HL H, ACCOUNT A \n" +
     * //       	 " WHERE (A.ACCOUNT_TYPE='I' OR A.ACCOUNT_TYPE='T') \n" +
     * //    	 " WHERE A.HIERARCHY_NBR = H.HIERARCHY_NBR \n";
     * public final static String SQL_SEARCH_TRANSFER_ACCOUNT_FROM_CLAUSE =
     * "\n FROM AGENCY_HL H, ACCOUNT A \n" + " WHERE A.HIERARCHY_NBR = H.HIERARCHY_NBR \n";
     * public final static String SP_GET_CARD_FLAG =
     * "STANDARD_OBJECTS.GET_CARD_FLAG(" + ":iUserID," + ":iAccountNumber," + ":oCardFlag," + ":oResult" + ")";
     * public final static String SP_GET_BILLING_TYPE =
     * "STANDARD_OBJECTS.GET_BILLING_TYPE(" + ":iUserID," + ":iAccountNumber," + ":oBillingType," + ":oResult" + ")";
     * public static final String SQL_ACCOUNT_HOLDER =
     * "\nSELECT account_nbr\n" + " from eagls_users\n" + " where account_nbr = :iAccountNumber\n" +
     * " and userid = :iUserId\n";
     * public static final String SQL_SEARCH_ACCOUNT_CREDIT_LIMIT_FOOTER =
     * " AND a.HIERARCHY_NBR = h.HIERARCHY_NBR\n" + " AND a.ACCOUNT_NBR = v.ACCOUNT_NBR(+)\n" +
     * " AND ( A.ACCOUNT_TYPE = 'C' OR A.ACCOUNT_STATUS = 'O' )\n";
     * public final static String SQL_GET_ACCOUNT_BUSINESS_PHONE =
     * "SELECT WORK_PHONE \n" + " FROM ACCOUNT WHERE ACCOUNT_NBR = :acctNum";
     * public final static String SP_GET_HIERARCHY_AGENCY_NAME = "{call STANDARD_OBJECTS.GET_HIERARCHY_AGENCY_NAME(" + "?," + "?," + //
     * // p_hl0               OUT     AGENCY_HL.HL0%TYPE,
     * "?," + // p_hl1               OUT     AGENCY_HL.HL1%TYPE,
     * "?," + // p_hl2               OUT     AGENCY_HL.HL2%TYPE,
     * "?," + // p_hl3               OUT     AGENCY_HL.HL3%TYPE,
     * "?," + // p_hl4               OUT     AGENCY_HL.HL4%TYPE,
     * "?," + // p_hl5               OUT     AGENCY_HL.HL5%TYPE,
     * "?," + // p_hl6               OUT     AGENCY_HL.HL6%TYPE,
     * "?," + // p_hl7               OUT     AGENCY_HL.HL7%TYPE,
     * "?," + // p_hl8               OUT     AGENCY_HL.HL8%TYPE,
     * "?," + // p_hl0description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl1description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl2description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl3description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl4description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl5description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl6description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl7description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?," + // p_hl8description    OUT     AGENCY_HL.HIERARCHY_DESCRIPTION%TYPE,
     * "?" + ")}";
     * public final static String SP_GET_CENTRAL_ACCT_HIER_NBR = "STANDARD_OBJECTS_2.GET_CENTRAL_ACCT_HIER_NBR(" + ":iAgencyBillNbr," + //p_userid IN
     * ":oHierarchyNbr," + // p_Hierarchy_Nbr OUT
     * ":oResult)";
     * //OTHER STATIC
     *
     * public final static short INCLUDE_TRANSACTION_SUMMARY = 0;
     * public final static short BASE_ACCOUNT = 1;
     * public final static short ADD = 0;
     * public final static short DELETE = 1;
     *
     *
     *
     * public AccountActivationSummary searchAccountForAccountActivation(String acctNo)throws NBException, NoDataFoundException;
     * public AccountSummary[] searchAccount(String lastName)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchAccount(String lastName, boolean usesConvChecks)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchAccount(int[] hierarchy, short depth, boolean usesConvChecks)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchAccount(String firstName, String lastName)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchAccount(int hierarchy[], short depth)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchAccount(String firstName, String lastName, boolean usesConvChecks)throws NBException, NoDataFoundException;;
     * public BulkActivationSummary[] searchAccountsForBulkActivation(Date begDat, Date endDat, int[] heirarchy, int depth, short sortParam) throws NBException, NoDataFoundException;;
     * public BulkActivationSummary[] searchAccountsForBulkActivation(int [] heirarchy, short depth, short sortParam) throws NBException, NoDataFoundException;
     * public BulkActivationSummary[] searchAccountsForBulkActivation(Date begDat, Date endDat, short sortParam) throws NBException, NoDataFoundException;
     * //**************************ACCOUNT STATUS****************
     */

    // public void changeAccountStatus(String acctNumber, short acctstatus) throws NBException, NoDataFoundException;
    // public void changeAccountStatus(String acctNumber, String acctStatus, String reasonCode) throws NBException, NoDataFoundException;
    // public void changeAccountStatus(String acctNumber, AccountStatus[] accountStatuses, short action) throws NBException, NoDataFoundException;
    // **************************CUSTOMER ID*****************/
    // public int getCustomerID(String acctNumber)throws NBException, NoDataFoundException;
    // **************************ORDER CONVENIENCE CHECKS*****************/
    // public void orderConvenienceChecks(String acctNo, int numberOfChecks)throws NBException, NoDataFoundException;;
    // **************************ACCOUNT CONTROLS*****************/

    /*
     * public AcctControls retrieveAcctControls(String acctNumber) throws NBException;
     * public int queueAccountControls(String tempAccountNumber, AcctControls anAcctControl) throws NBException;
     * public void updateAccountControls(String acctNo, AcctControls acctControls)throws NBException, NoDataFoundException;
     * //**************************MCCG CONTROLS(QUEUE-CREATE, UPDATE, DELETE, ADD)****************
     */

    /*
     * public void queueMCCGControls(String tempAccountNumber, MCCGControls aMCCGControl) throws NBException;
     * public void queueMCCGControls(String tempAccountNumber, int requestID, MCCGControls aMCCGControl) throws NBException;
     * public void updateMCCGControls(String acctNo, MCCGControls aMCCG)throws NBException, NoDataFoundException;
     * public MCCGControls[] retrieveMCCGControls(String acctNumber) throws NBException;
     * public MCCGControls retrieveMCCGControls(String acctNumber, String mccgTableName) throws NBException;
     * public void deleteMCCGTable(String acctNo, String mccgTableName)throws NBException, NoDataFoundException;
     * //**************************RENEW ACCOUNTS****************
     */

    /*
     * public AccountRenewalSummary[] searchAccountsForAccountRenewal(int[] searchHierarchy, short searchDepth)throws NBException, NoDataFoundException;;
     * public AccountRenewalTable[] renewAccountsAction(AccountRenewalTable[] accountRenewalTables)throws NBException, NoDataFoundException;;
     * //**************************CREDIT LIMIT****************
     */

    /*
     * public double retrieveAggregateAccountCreditLimit(String centralAcctId)throws NBException, NoDataFoundException;
     * public double retrieveCentralAccountCreditLimit(String centralAcctId)throws NBException, NoDataFoundException;
     * public void queueCreditLimit(String acctNo, double newCreditLimit)throws NBException, NoDataFoundException;
     * public void updateCreditLimit(String acctNo, double newCreditLimit, double oldCreditLimit ) throws NBException, NoDataFoundException;
     *
     * //**************************CARD REQUEST****************
     */

    /*
     * public void requestCard(RequestCard requestCard) throws NBException, NoDataFoundException;
     * public AccountSummary[] searchCardRequest(String lastName)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchCardRequest(int hierarchy[], short depth)throws NBException, NoDataFoundException;;
     * public AccountSummary[] searchCardRequest(String firstName, String lastName)throws NBException, NoDataFoundException;;
     * //**************************VALIDATE****************
     */

    /*
     * public short validateAccountNumber(String acctNumber)throws NBException, NoDataFoundException;
     * public boolean validateCentralAcctID(String centralAcctID) throws NBException, NoDataFoundException;
     * //**************************HEIRARCHY****************
     */

    // public String getHierarchyDescriptionByPosition(String descriptionString, int position)throws NBException, NoDataFoundException;;
    // **************************AGENCY*****************/

    /*
     * public AgencyCore retrieveAgencyCoreByCentralAcctID(String centralAcctID)throws NBException, NoDataFoundException;;
     * public int retrieveAgencyIDByAccountNumber(String acctNo)throws NBException, NoDataFoundException;;
     *
     * //**************************CENTRAL ACCOUNT****************
     */

    /*
     * public CentralAcct retrieveCentralAcctByCentralAcctID(String centralAcctID) throws NBException, NoDataFoundException;
     * public CentralOffice retrieveCentralOfficeDetail(int aHierarchy[], int aAgencyID)throws NBException, NoDataFoundException;;
     */
}
