package com.boa.eagls.government.dao;

import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.centralaccount.CentralAcct;
import com.boa.eagls.government.dto.centralaccount.CentralAcctSummary;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.NoDataFoundException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.DateUtil;
import com.boa.eagls.government.util.pagedList.SearchResult;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import com.boa.eagls.government.constants.service.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * DAO class direct interaction with DB for Central Account and related entities
 * User: IvanK
 * Date: 14.07.2003
 * Time: 9:10:58
*/
public class CentralAccountDAO extends AccountDAO {

    Double tmpDblVal = null;

    private static final Logger LOGGER = Logger.getLogger(CentralAccountDAO.class);

    public final static String ERROR_DB_TYPE_MISMATCH = "SYS_E0002:DataAccessAdapter::The DAA received an unrecognized value from the database.";
    public final static String ERROR_NULL_VALUE_NOT_SUPPORTED = "SYS_E0002:DataAccessAdapter::Stored procedure returned an unsupported null value.";

    //Used to determine which central accounts to return on searches
    public static final short SETUP_COMPLETED = 0;

    public final static String EMPTY_STRING = " ";
    //Used identify whether accounts are setup or not
    public final static String SEARCH_SETUP_FIELD = "a.ACCOUNTING_CENTER_ID";
    // SQL header used by search methods
    private final static String SQL_SEARCH_CENTRAL_ACCT_SELECT =
            "SELECT a.LAST_NAME, TO_CHAR(a.ACCOUNT_NBR), a.agency_bill_nbr\n";
    private final static String SQL_SEARCH_CENTRAL_ACCT_FROM_HL =
            "  FROM AGENCY_HL h, BILLING_AGENCY b, CENTRAL_OFFICE c, ACCOUNT a\n";
    private final static String SQL_SEARCH_CENTRAL_ACCT_FROM_NO_HL =
            "  FROM BILLING_AGENCY b, CENTRAL_OFFICE c, ACCOUNT a\n";

    // SQL footer used by search methods
    private final static String SQL_SEARCH_CENTRAL_ACCT_FOOTER_HL =
            "   AND a.HIERARCHY_NBR = h.HIERARCHY_NBR\n" +
            //Make sure Agency is setup
            "   AND a.HIERARCHY_NBR = c.HIERARCHY_NBR\n" +
            "   AND b.AGENCY_BILL_NBR = a.AGENCY_BILL_NBR\n";

    // SQL footer used by search methods
    private final static String SQL_SEARCH_CENTRAL_ACCT_FOOTER_NO_HL =
            "   AND a.HIERARCHY_NBR = c.HIERARCHY_NBR\n" +
            "   AND b.AGENCY_BILL_NBR = a.AGENCY_BILL_NBR\n";

    // SQL WHERE clause to search by agency name
    private final static String SQL_SEARCH_BY_AGENCY_NAME_CLAUSE =
            " WHERE h.AGENCY_NAME = ";

    //SQL and clause to restrict for accounts which have been setup on eagls
    private final static String SQL_SEARCH_CENTRAL_ACCT_SETUP_COMPLETED =
            "   AND (a.ACCOUNT_TYPE='C' OR a.ACCOUNT_TYPE='D')\n" +
            "   AND " + SEARCH_SETUP_FIELD + " is not NULL\n";


    //SQL and clause to restrict for accounts which have been setup on eagls
    private final static String SQL_SEARCH_CENTRAL_ACCT_SETUP_INCOMPLETE =
            "   AND ((a.account_type = 'C'\n" +
            "           AND b.accounting_center_id is NULL\n" +
            "           AND a.accounting_center_id is NULL)\n" +
            "       OR (a.account_type = 'D'\n" +
            "           AND b.accounting_center_id is not NULL\n" +
            "           AND a.accounting_center_id is NULL))\n";


    // Stored Procedure: retrieve central account info
    private final static String SP_RETRIEVE_CENTRAL_ACCT_INFO =
            "{call CENTRAL_ACCOUNT_INQUIRY.ACCOUNT_INFORMATION(" +
            "?\n," + // p_userid				 		  IN 	VARCHAR2,
            "?\n," + // p_centralaccountnumber         IN OUT VARCHAR2,
            "?\n," + // p_centralaccountname           OUT   VARCHAR2,
            "?,\n" + // p_programnumber                OUT   NUMBER,
            "?,\n" + // p_hl1                          OUT   NUMBER,
            "?,\n" + // p_hl2                          OUT   NUMBER,
            "?,\n" + // p_hl3                          OUT   NUMBER,
            "?,\n" + // p_hl4                          OUT   NUMBER,
            "?,\n" + // p_hl5                          OUT   NUMBER,
            "?,\n" + // p_hl6                          OUT   NUMBER,
            "?,\n" + // p_hl7                          OUT   NUMBER,
            "?,\n" + // p_hl8                          OUT   NUMBER,
            "?,\n" + // p_hierarchydescription0        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription1        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription2        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription3        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription4        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription5        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription6        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription7        OUT   VARCHAR2,
            "?,\n" + // p_hierarchydescription8        OUT   VARCHAR2,
            "?,\n" + // p_centralaccountid             OUT   VARCHAR2,
            "?,\n" + // p_agencyname                   OUT   VARCHAR2,
            "?,\n" +
            "?,\n" + // p_accounttype                  OUT   CHAR,
            "?,\n" + // p_billingtype                  OUT   CHAR(1),
            "?,\n" + // p_masteraccountingcode         OUT   VARCHAR2(64),
            "?,\n" + // p_accountingcenterid           OUT   VARCHAR2,
            "?,\n" + // p_conveniencechecks            OUT   CHAR(1),
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" + // p_travellerschecks             OUT   CHAR(1),
            "?,\n" + // p_debitcard                    OUT   CHAR(1),
            "?,\n" + // p_ATMaccess                    OUT   CHAR(1),
            "?,\n" + // p_programtype				  OUT   VARCHAR2,
            "?,\n" + // p_citypairprogramindicator	  OUT   CHAR(1),
            "?,\n" + // p_invoicemediumEAGLS                OUT   CHAR(1),
            "?,\n" + // p_invoicemedium PAPER               OUT   CHAR(1),
            "?,\n" + // p_invoicemediumEDI              OUT   CHAR(1),
            "?,\n" + // p_invoicemediumELECTRONIC                OUT   CHAR(1),
            "?,\n" + // p_CMID						  OUT	VARCHAR2,
            "?,\n" + // p_carddescription			  OUT	VARCHAR2,
            "?,\n" + // p_accountstatus                OUT   CHAR(1),
            "?,\n" + // p_pastdueamount                OUT   NUMBER,
            "?,\n" + // p_pastduenumberofdays          OUT   NUMBER,
            "?,\n" + // p_accountexpirationdate        OUT   DATE,
            "?,\n" + // p_aggregate                    OUT   NUMBER(14, 2),
            "?,\n" + // p_creditlimit                  OUT   NUMBER,
            "?,\n" + // p_currentblance                OUT   NUMBER,
            "?,\n" + // p_totaldollardisputes          OUT   NUMBER,
            "?,\n" + // p_paymentdue                   OUT   NUMBER,
            "?,\n" + // p_paymentduedate               OUT   DATE,
            "?,\n" + // p_lastpaymentdate              OUT   DATE,
            "?,\n" + // p_lastpaymentamount            OUT   NUMBER,
            "?,\n" + // p_totaldebitamount             OUT   NUMBER,
            "?,\n" + // p_totaldebittransactions       OUT   NUMBER,
            "?,\n" + // p_totalcreditamount            OUT   NUMBER,
            "?,\n" + // p_totalcredittransactions      OUT   NUMBER,
            "?,\n" + // p_totalcycletransactions       OUT   NUMBER,
            "?,\n" + // p_totalamountpreviouscycle     OUT   NUMBER,
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?,\n" +
            "?)}";				// p_errorcode                    OUT   VARCHAR2



// Stored Procedure: retrieve central account info for maintenance screen
    private final static String SP_RETRIEVE_CENTRAL_ACCT_MAINT_INFO =
            "{call CENTRAL_ACCOUNT_MAINTENANCE.ACCOUNT_INFORMATION(" +
            "?," + // p_userid				 		  IN 	VARCHAR2,
            "?," + // p_centralaccountnumber         IN OUT VARCHAR2,
            "?," + // p_centralaccountname           OUT   VARCHAR2,
            "?," + // p_programnumber                OUT   NUMBER,
            "?," + // p_hl1                          OUT   NUMBER,
            "?," + // p_hl2                          OUT   NUMBER,
            "?," + // p_hl3                          OUT   NUMBER,
            "?," + // p_hl4                          OUT   NUMBER,
            "?," + // p_hl5                          OUT   NUMBER,
            "?," + // p_hl6                          OUT   NUMBER,
            "?," + // p_hl7                          OUT   NUMBER,
            "?," + // p_hl8                          OUT   NUMBER,
            "?," + // p_hierarchydescription0        OUT   VARCHAR2,
            "?," + // p_hierarchydescription1        OUT   VARCHAR2,
            "?," + // p_hierarchydescription2        OUT   VARCHAR2,
            "?," + // p_hierarchydescription3        OUT   VARCHAR2,
            "?," + // p_hierarchydescription4        OUT   VARCHAR2,
            "?," + // p_hierarchydescription5        OUT   VARCHAR2,
            "?," + // p_hierarchydescription6        OUT   VARCHAR2,
            "?," + // p_hierarchydescription7        OUT   VARCHAR2,
            "?," + // p_hierarchydescription8        OUT   VARCHAR2,
            "?," + // p_centralaccountid             OUT   VARCHAR2,
            "?," + // p_agencyname                   OUT   VARCHAR2,
            "?," +
            "?," + // p_accounttype                  OUT   CHAR,
            "?," + // p_billingtype                  OUT   CHAR(1),
            "?," + // p_masteraccountingcode         OUT   VARCHAR2(64),
            "?," + // p_accountingcenterid           OUT   VARCHAR2,
            "?," + // p_conveniencechecks            OUT   CHAR(1),
            "?," +
            "?," +
            "?," +
            "?," + // p_travellerschecks             OUT   CHAR(1),
            "?," + // p_debitcard                    OUT   CHAR(1),
            "?," + // p_ATMaccess                    OUT   CHAR(1),
            "?," + // p_programtype				  OUT   VARCHAR2,
            "?," + // p_citypairprogramindicator	  OUT   CHAR(1),
            "?," + // p_invoicemediumEAGLS                OUT   CHAR(1),
            "?," + // p_invoicemedium PAPER               OUT   CHAR(1),
            "?," + // p_invoicemediumEDI              OUT   CHAR(1),
            "?," + // p_invoicemediumELECTRONIC                OUT   CHAR(1),
            "?," + // p_accountexpirationdate        OUT   DATE,
            "?," + // p_aggregate                    OUT   NUMBER(14, 2),
            "?," + // p_creditlimit                  OUT   NUMBER,
            "?," +
            "?," +
            "?," +
            "?," +
            "?," +
            "?," +
            "?)}";				// p_errorcode                    OUT   VARCHAR2


    private static int QUERY_iUserID = 1;
    private static int QUERY_ioAcctNbr = 2;
    private static int QUERY_oCentralAcctName = 3;
    private static int QUERY_oHL = 4;
    private static int QUERY_oHLDesc = 13;
    private static int QUERY_oCentralAcctID = 22;
    private static int QUERY_oAgencyName = 23;
    private static int QUERY_oAgencyID = 24;
    private static int QUERY_oAcctType = 25;
    private static int QUERY_oBillType = 26;
    private static int QUERY_oMstrAcctCode = 27;
    private static int QUERY_oAcctCenterID = 28;
    private static int QUERY_oConvChecksFlg = 29;
    private static int QUERY_oNumberOfConvChecks3 = 30;
    private static int QUERY_oNumberOfConvChecks20 = 31;
    private static int QUERY_oNumberOfConvChecks50 = 32;
    private static int QUERY_oTravChecksFlg = 33;
    private static int QUERY_oDebitCardFlg = 34;
    private static int QUERY_oATMAccessFlg = 35;
    private static int QUERY_oProgType = 36;
    private static int QUERY_oCityPairProg = 37;
    private static int QUERY_oInvoiceMediumEAGLS = 38;
    private static int QUERY_oInvoiceMediumPAPER = 39;
    private static int QUERY_oInvoiceMediumEDI = 40;
    private static int QUERY_oInvoiceMediumELECTRONIC = 41;
    private static int QUERY_oCMID = 42;
    private static int QUERY_oCardDesc = 43;
    private static int QUERY_oAcctStatus = 44;
    private static int QUERY_oPastDueAmt = 45;
    private static int QUERY_oPastDueNbrDays = 46;
    private static int QUERY_oAcctExpiryDate = 47;
    private static int QUERY_oAggregate = 48;
    private static int QUERY_oCreditLimit = 49;
    private static int QUERY_oCurrentBalance = 50;
    private static int QUERY_oTtlDollarDisputes = 51;
    private static int QUERY_oPayDue = 52;
    private static int QUERY_oPayDueDate = 53;
    private static int QUERY_oLastPayDate = 54;
    private static int QUERY_oLastPayAmt = 55;
    private static int QUERY_oTtlDebitAmt = 56;
    private static int QUERY_oTtlDebitTrans = 57;
    private static int QUERY_oTtlCreditAmt = 58;
    private static int QUERY_oTtlCreditTrans = 59;
    private static int QUERY_oTtlCycleTrans = 60;
    private static int QUERY_oTtlAmtPrevCycle = 61;
    private static int QUERY_oIntegratedFlag = 62;
    private static int QUERY_oInteragencyFlag = 63;
    private static int QUERY_oPurchaseFlag = 64;
    private static int QUERY_oTravelFlag = 65;
    private static int QUERY_oFleetFlag = 66;
    private static int QUERY_oFleetType = 67;
    private static int QUERY_oResult = 68;


    /**
     * Constructs a CentAcctInquiryDAA object with access to system services.
     **/
    public CentralAccountDAO() {
        super();
    }

    public Collection searchByAllParameters(Connection conn, boolean selCentralAcctName,
                                            boolean selCentralAcctID,
                                            boolean selCentralAcctNumber,
                                            boolean selAgencyName,
                                            boolean selHierarchy,
                                            String centralAcctID,
                                            String centralAcctName,
                                            String centralAcctNumber,
                                            String agencyName,
                                            int[] hierarchy,
                                            int[] usHierarchy,
                                            String currentBaseRole, short depth, int firstRecord, int numRecords)
            throws SQLException, EaglsDAOError {
        return searchCentralAcctByHierarchyIDName(conn, selCentralAcctName, selCentralAcctID, selCentralAcctNumber,
                selAgencyName, selHierarchy, centralAcctID, centralAcctName, centralAcctNumber, agencyName, hierarchy, usHierarchy, currentBaseRole,
                depth, (short) 0, false, firstRecord, numRecords).getElements();
    }

    public int countByAllParameters(Connection conn, boolean selCentralAcctName, boolean selCentralAcctID,
                                    boolean selCentralAcctNumber,
                                    boolean selAgencyName,
                                    boolean selHierarchy,
                                    String centralAcctID,
                                    String centralAcctName,
                                    String centralAcctNumber,
                                    String agencyName,
                                    int[] hierarchy,
                                    int[] usHierarchy,
                                    String currentBaseRole, short depth) throws SQLException, EaglsDAOError {
        return searchCentralAcctByHierarchyIDName(conn, selCentralAcctName, selCentralAcctID, selCentralAcctNumber,
                selAgencyName, selHierarchy, centralAcctID, centralAcctName, centralAcctNumber, agencyName, hierarchy, usHierarchy, currentBaseRole,
                depth, (short) 0, true, -1, -1).getSize();
    }

    public Collection searchByAcctNumber(Connection conn, String centralAcctNumber,
                                         int hUser[], int firstRecord, int numRecords) throws SQLException, EaglsDAOError {
        return searchCentralAcctByHierarchyIDName(conn, false, false, true, false, false, null, null, centralAcctNumber, null, new int[0], hUser, null, (short) 0, (short) 0, false, firstRecord, numRecords).getElements();
    }

    public int countByAcctNumber(Connection conn, String centralAcctNumber,
                                 int hUser[]) throws SQLException, EaglsDAOError {
        return searchCentralAcctByHierarchyIDName(conn, false, false, true, false, false, null, null, centralAcctNumber, null, new int[0], hUser, null, (short) 0, (short) 0, true, -1, -1).getSize();
    }

    public Collection searchByAgencyName(Connection conn, String centralAgencyName,
                                         int hUser[], int firstRecord, int numRecords) throws SQLException, EaglsDAOError {
        return searchCentralAcctByAgencyName(conn, centralAgencyName, SETUP_COMPLETED, false, firstRecord, numRecords).getElements();
    }

    public int countByAgencyName(Connection conn, String centralAgencyName,
                                 int hUser[]) throws SQLException, EaglsDAOError {
        return searchCentralAcctByAgencyName(conn, centralAgencyName, SETUP_COMPLETED, true, -1, -1).getSize();
    }

    private SearchResult searchCentralAcctByHierarchyIDName(Connection conn,
                                                            boolean selCentralAcctName,
                                                            boolean selCentralAcctID,
                                                            boolean selCentralAcctNumber,
                                                            boolean selAgencyName, boolean selHierarchy,
                                                            String centralAcctID,
                                                            String centralAcctName,
                                                            String centralAcctNumber,
                                                            String agencyName, int[] hierarchy,
                                                            int[] usHierarchy,
                                                            String currentBaseRole,
                                                            short depth,
                                                            short searchMethod,
                                                            boolean countFlag,
                                                            int firstRecord,
                                                            int numRecords)
            throws SQLException, EaglsDAOError {
        // Create access hierarchy to restrict the search results to those
        // under the user's current hierarchy

        SearchResult result = new SearchResult();
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_CENTRAL_ACCT_SELECT);

        PreparedStatement st = null;
        ResultSet res = null;

        int[] hAccess;
        if (selHierarchy) {
            hAccess = authorizeHierarchySearch(hierarchy, depth, usHierarchy);
        } else {
            hAccess = authorizeHierarchySearch(null, (short) 0, null);
        }

        if (hAccess == null) {
            //todo: реализовать корректное логирование
            //           transport.logError("searchCentralAcctByHierarchy",
            //                   "Unauthorized Search by " + transport.getUserID());
            //throw new NBError(Errors.APP_MSG004);
        }

        if (selHierarchy || selAgencyName || !Role.GCSU.equals(currentBaseRole)) {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FROM_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FROM_NO_HL);
        }

        //sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FROM_HL_REVISED); //Discrepancy: 001394 - added

        // Append access hierarchy to end of WHERE clause in embedded SQL
        boolean doneWhere = false;	// have we done the WHERE part yet

        // Discrepancy: 001394 -start of addition
        sqlStmt.append(" WHERE a.ACCOUNT_TYPE in ('C','D')" + "\n");
        sqlStmt.append("   AND a.ACCOUNTING_CENTER_ID is not NULL" + "\n");
        /*sqlStmt.append("   AND EXISTS (SELECT 'X' FROM" + "\n");
        sqlStmt.append("                AGENCY_HL h" + "\n");
        sqlStmt.append("               WHERE a.hierarchy_nbr = h.hierarchy_nbr" + "\n");*/
        // Discrepancy: 001394 -end of addition

        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                /* Discrepancy: 001394 - start of revision
             if (doneWhere) {
                sqlStmt.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
             } else {
                sqlStmt.append(" WHERE h.HL" + i + " = " + hAccess[i] + "\n");
                doneWhere = true;
             }
                Discrepancy: 001394 -end of revision
             */
                sqlStmt.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
            }
        }


        // Discrepancy: 001394 - start of addition
        /*sqlStmt.append(")\n");// close the hierarchy subselect
          sqlStmt.append(" AND EXISTS (select 'X' from" +"\n");
          sqlStmt.append("              BILLING_AGENCY b" +"\n");
          sqlStmt.append("              WHERE b.AGENCY_BILL_NBR = a.AGENCY_BILL_NBR)" +"\n");
          sqlStmt.append(" AND EXISTS (select 'X' from" +"\n");
          sqlStmt.append("              CENTRAL_OFFICE c" +"\n");
          sqlStmt.append("              WHERE a.HIERARCHY_NBR = C.HIERARCHY_NBR)" +"\n");*/
        // Discrepancy: 001394 - end of addition
        if (selCentralAcctName) {
            sqlStmt.append("   AND a.last_name = '" + centralAcctName.trim().toUpperCase() + "'\n");
        }

        if (selCentralAcctID) {
            sqlStmt.append("   AND a.AGENCY_BILL_NBR = '" + centralAcctID.trim() + "'\n");
        }

        if (selCentralAcctNumber) {
            sqlStmt.append("   AND a.ACCOUNT_NBR = '" + centralAcctNumber.trim() + "'\n");
        }

        if (selAgencyName) {
            sqlStmt.append("   AND h.AGENCY_NAME = '" + agencyName.trim() + "'\n");
        }
        /*
        // Discrepancy: 001394 - start of revision
         //Search for the correct account types
         this.appendAccountRestriction(sqlStmt, setup);
         //Append Footer
         sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FOOTER_HL);

       sqlStmt = appendPTCheckByUserPT(sqlStmt);
        // Discrepancy: 001394 - end of revision
       */
        if (selHierarchy || selAgencyName || !Role.GCSU.equals(currentBaseRole)) {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FOOTER_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FOOTER_NO_HL);
        }
        sqlStmt.append("ORDER BY a.ACCOUNT_NBR \n");

        ArrayList list = new ArrayList();
        try {
            if (countFlag) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = conn.prepareStatement(countSQL);
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(), firstRecord, numRecords);
                st = conn.prepareStatement(pagedSQL);
                res = st.executeQuery();
                while (res.next()) {
                    CentralAcctSummary ca = populateCentralAcctSummary(res, 1);
                    list.add(ca);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;//list;
    }

    private SearchResult searchCentralAcctByAgencyName(Connection conn,
                                                       String agencyName,
                                                       short setup,
                                                       boolean countFlag,
                                                       int firstRecord,
                                                       int numRecords)
            throws SQLException, EaglsDAOError {

        SearchResult result = new SearchResult();

        PreparedStatement st = null;
        ResultSet res = null;        // Create access hierarchy to restrict the search results to those
        // under the user's current hierarchy
        int hAccess[] = authorizeHierarchySearch(null, (short) 0, null);
        if (hAccess == null) {
            //todo: реализовать корректное логирование
//            transport.logError("searchCentralAcctByAgencyName",
//                    "Unauthorized Search by " + transport.getUserID());
//            throw new NBError(Errors.APP_MSG004);
        }

        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_CENTRAL_ACCT_SELECT);
        sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FROM_HL);

        // Prepare parameter list
        sqlStmt.append(SQL_SEARCH_BY_AGENCY_NAME_CLAUSE);
        sqlStmt.append("'");
        sqlStmt.append(agencyName.trim().toUpperCase());
        sqlStmt.append("'\n");

        // Append access hierarchy to end of WHERE clause in embedded SQL
        StringBuffer hlClause = new StringBuffer();
        boolean useHLTable = false;
        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                sqlStmt.append("   AND h.HL");
                sqlStmt.append(i);
                sqlStmt.append(" = ");
                sqlStmt.append(hAccess[i]);
                useHLTable = true;
            }
        }

        //Search for the correct account types
        this.appendAccountRestriction(sqlStmt, setup);

        //Append Footer
        sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_FOOTER_HL);

        // Bug 001575. Adding an Order By clause
        sqlStmt.append(" ORDER BY a.ACCOUNT_NBR \n");

        ArrayList list = new ArrayList();
        try {
            if (countFlag) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = conn.prepareStatement(countSQL);
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(), firstRecord, numRecords);
                st = conn.prepareStatement(pagedSQL);
                res = st.executeQuery();
                while (res.next()) {
                    CentralAcctSummary ca = populateCentralAcctSummary(res, 1);
                    list.add(ca);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;//list;

    }

    private void appendAccountRestriction(StringBuffer sqlStmt, short setup) {
        if (setup == this.SETUP_COMPLETED) {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_SETUP_COMPLETED);
        } else {
            sqlStmt.append(SQL_SEARCH_CENTRAL_ACCT_SETUP_INCOMPLETE);
        }
    }

    private CentralAcctSummary populateCentralAcctSummary(ResultSet rs, int index) {
        CentralAcctSummary result = new CentralAcctSummary();
        try {
            int columnCount = rs.getMetaData().getColumnCount();
            result.setCentralAcctName(rs.getString(index++));
            result.setCentralAcctNo(rs.getString(index++));
            result.setCentralAcctID(rs.getString(index++));
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return result;
    }


    /**
     * Retrieves detailed information from TSYS and EAGLS database about
     * a central account.
     *
     * @param   acctNum		identifies an central card account to retrieve
     *						(required non-null)
     * @return  CentralAcct  contains central account details
     **/
    public CentralAcct retrieveCentralAcct(Connection connection, String acctNum, String userId)
            throws SQLException, EaglsDAOError {
        // Call EAGLS stored procedure to retrieve main central account info
        // initialize stored procedure parameters
        CallableStatement cs = null;
        CentralAcct acctInfo = new CentralAcct();
        LOGGER.debug(SP_RETRIEVE_CENTRAL_ACCT_INFO);
        try {
            cs = connection.prepareCall(SP_RETRIEVE_CENTRAL_ACCT_INFO);
            cs.setString(QUERY_iUserID, userId); //super.parseNullStrings(transport.getUserID()));
            cs.setString(QUERY_ioAcctNbr, acctNum);
            cs.registerOutParameter(QUERY_oCentralAcctName, Types.VARCHAR);
            for (short i = 0; i < 9; i++) {
                cs.registerOutParameter(QUERY_oHL + i, Types.INTEGER);
                cs.registerOutParameter(QUERY_oHLDesc + i, Types.VARCHAR);
            }
            cs.registerOutParameter(QUERY_oCentralAcctID, Types.INTEGER);
            cs.registerOutParameter(QUERY_oAgencyName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAgencyID, Types.INTEGER);//int
            cs.registerOutParameter(QUERY_oAcctType, Types.CHAR);
            cs.registerOutParameter(QUERY_oBillType, Types.CHAR);
            cs.registerOutParameter(QUERY_oMstrAcctCode, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAcctCenterID, Types.INTEGER);
            cs.registerOutParameter(QUERY_oConvChecksFlg, Types.CHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks3, Types.CHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks20, Types.CHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks50, Types.CHAR);
            cs.registerOutParameter(QUERY_oTravChecksFlg, Types.CHAR);
            cs.registerOutParameter(QUERY_oDebitCardFlg, Types.CHAR);
            cs.registerOutParameter(QUERY_oATMAccessFlg, Types.CHAR);
            cs.registerOutParameter(QUERY_oProgType, Types.CHAR);
            cs.registerOutParameter(QUERY_oCityPairProg, Types.CHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumEAGLS, Types.CHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumPAPER, Types.CHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumEDI, Types.CHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumELECTRONIC, Types.CHAR);
            cs.registerOutParameter(QUERY_oCMID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oCardDesc, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAcctStatus, Types.CHAR);
            cs.registerOutParameter(QUERY_oPastDueAmt, Types.INTEGER);
            cs.registerOutParameter(QUERY_oPastDueNbrDays, Types.INTEGER);
            cs.registerOutParameter(QUERY_oAcctExpiryDate, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAggregate, Types.INTEGER);
            cs.registerOutParameter(QUERY_oCreditLimit, Types.INTEGER);
            cs.registerOutParameter(QUERY_oCurrentBalance, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlDollarDisputes, Types.INTEGER);
            cs.registerOutParameter(QUERY_oPayDue, Types.INTEGER);
            cs.registerOutParameter(QUERY_oPayDueDate, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oLastPayDate, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oLastPayAmt, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlDebitAmt, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlDebitTrans, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlCreditAmt, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlCreditTrans, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlCycleTrans, Types.INTEGER);
            cs.registerOutParameter(QUERY_oTtlAmtPrevCycle, Types.INTEGER);
            cs.registerOutParameter(QUERY_oIntegratedFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInteragencyFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oPurchaseFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oTravelFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oFleetFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oFleetType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oResult, Types.VARCHAR);
            cs.execute();
            acctInfo.setAcctNo(acctNum);
            acctInfo.setCentralAcctID(cs.getString(QUERY_oCentralAcctName));
            acctInfo.setAgencyName(cs.getString(QUERY_oAgencyName));
            try {
                acctInfo.setAgencyID(new Integer(cs.getString(QUERY_oAgencyID)).intValue());
            } catch (Exception e) {
                acctInfo.setAgencyID(0);
            }
            acctInfo.setAcctName(cs.getString(QUERY_oCentralAcctName));
            acctInfo.setCentralAcctID(cs.getString(QUERY_oCentralAcctID));
            acctInfo.setAccountingCenterID(cs.getString(QUERY_oAcctCenterID));
            acctInfo.setInvoiceMediumPaper(super.stringToBoolean(cs.getString(QUERY_oInvoiceMediumPAPER)));
            acctInfo.setInvoiceMediumEDI(super.stringToBoolean(cs.getString(QUERY_oInvoiceMediumEDI)));
            acctInfo.setInvoiceMediumElectronic(super.stringToBoolean(cs.getString(QUERY_oInvoiceMediumELECTRONIC)));
            acctInfo.setTravelFlag(super.stringToBoolean(cs.getString(QUERY_oTravelFlag)));
            acctInfo.setFleetFlag(super.stringToBoolean(cs.getString(QUERY_oFleetFlag)));
            acctInfo.setPurchaseFlag(super.stringToBoolean(cs.getString(QUERY_oPurchaseFlag)));
            acctInfo.setInteragencyFlag(super.stringToBoolean(cs.getString(QUERY_oInteragencyFlag)));
            acctInfo.setIntegratedFlag(super.stringToBoolean(cs.getString(QUERY_oIntegratedFlag)));
            acctInfo.setFleetType(this.convertFleetType(cs.getString(QUERY_oFleetType)));

            int[] numberOfConvChecks = new int[3];
            if (super.stringToBoolean(cs.getString(QUERY_oNumberOfConvChecks3))) numberOfConvChecks[0] = 3;
            if (super.stringToBoolean(cs.getString(QUERY_oNumberOfConvChecks20))) numberOfConvChecks[1] = 20;
            if (super.stringToBoolean(cs.getString(QUERY_oNumberOfConvChecks50))) numberOfConvChecks[2] = 50;
            acctInfo.setConvenienceChecksNumbers(numberOfConvChecks);
            //END work to set numberOfConvChecks

            short acctStatus = CentralAcct.OPEN;

            String str = cs.getString(QUERY_oAcctStatus);
            if (str != null) {
                str = str.trim().toUpperCase();
                if (str.length() != 0) {
                    switch (str.charAt(0)) {
                        case 'O':
                            {
                                acctStatus = CentralAcct.OPEN;
                                break;
                            }
                        case 'P':
                            {
                                acctStatus = CentralAcct.PAST_DUE;
                                break;
                            }
                        case 'L':
                            {
                                acctStatus = CentralAcct.OVER_LIMIT;
                                break;
                            }
                        case 'C':
                            {
                                acctStatus = CentralAcct.CLOSED;
                                break;
                            }
                        default :
                            {
                                LOGGER.error(ERROR_DB_TYPE_MISMATCH);
                                throw new EaglsDAOError(ERROR_DB_TYPE_MISMATCH);
                            }
                    }
                }
            }
            acctInfo.setAcctStatus(acctStatus);

            //Set account type
            String acctType = cs.getString(QUERY_oAcctType).toUpperCase();
            if (acctType.equals("C")) {
                acctInfo.setAccountType(CentralAcct.CENTRAL_ACCT);
            } else if (acctType.equals("D")) {
                acctInfo.setAccountType(CentralAcct.DIVERSION_ACCT);
            }
            /* TODO
            else {  // Data type mismatch
               transport.logError("retrieveCentralAcct", "Could not match Account Type from database");
               throw new NBException(Errors.SYS_MSG003);
            }
            */
            str = cs.getString(QUERY_oProgType);
            if (str == null) {
                LOGGER.error(ERROR_NULL_VALUE_NOT_SUPPORTED);
                throw new EaglsDAOError(ERROR_NULL_VALUE_NOT_SUPPORTED);
            }
            str = str.trim().toUpperCase();
            if (str.length() != 1) {
                LOGGER.error(ERROR_DB_TYPE_MISMATCH);
                throw new EaglsDAOError(ERROR_DB_TYPE_MISMATCH);
            }
            switch (str.charAt(0)) {
                case 'P':
                    {
                        acctInfo.setProgramType(CentralAcct.PURCHASE);
                        break;
                    }
                case 'T':
                    {
                        acctInfo.setProgramType(CentralAcct.TRAVEL);
                        break;
                    }
                case 'F':
                    {
                        acctInfo.setProgramType(CentralAcct.FLEET);
                        break;
                    }
                case 'I':
                    {
                        acctInfo.setProgramType(CentralAcct.INTEGRATE);
                        break;
                    }
                case 'A':
                    {
                        acctInfo.setProgramType(CentralAcct.INTERAGENCY);
                        break;
                    }
                default:
                    {
                        LOGGER.error(ERROR_DB_TYPE_MISMATCH);
                        throw new EaglsDAOError(ERROR_DB_TYPE_MISMATCH);
                    }
            }

            acctInfo.setDefaultAccountingCode(cs.getString(QUERY_oMstrAcctCode));
            acctInfo.setIssueConvenienceChecks((cs.getString(QUERY_oConvChecksFlg).trim().equals("Y")) ? true : false);
            acctInfo.setIssueTravelersChecks((cs.getString(QUERY_oTravChecksFlg).trim().equals("Y")) ? true : false);
            acctInfo.setHasATMAccess((cs.getString(QUERY_oATMAccessFlg).trim().equals("Y")) ? true : false);
            acctInfo.setUsesDecrementingCard((cs.getString(QUERY_oDebitCardFlg).trim().equals("Y")) ? true : false);
            acctInfo.setCityPairProgram((cs.getString(QUERY_oCityPairProg).trim().equals("Y")) ? true : false);
            acctInfo.setBillingType((cs.getString(QUERY_oBillType).trim().equals("I")) ? CentralAcct.INDIVIDUAL : CentralAcct.CENTRAL);

//Set Date
            try {
                Date expiryDate = DateUtil.convertStringToExpiry(cs.getString(QUERY_oAcctExpiryDate));
                acctInfo.setAccountExpDate(expiryDate);
            } catch (Exception e) {/*DO NOTHING?*/
            }

            // TODO one interface for invoice medium / database value range
            //acctInfo.setInvoiceMediumElectronic(super.stringToBoolean(outParams.getValString(QUERY_oInvoiceMedium")));
            //acctInfo.setInvoiceMediumPaper((outParams.getValString(QUERY_oInvoiceMedium").equals("P"))
            //? true : false);
            //acctInfo.setInvoiceMediumEDI((outParams.getValString(QUERY_oInvoiceMedium").equals("E"))
            //? true : false);
            //Set card types
            String[] cardTypes = new String[1];
            cardTypes[0] = cs.getString(QUERY_oCardDesc);
            acctInfo.setCardTypes(cardTypes);
            if ((tmpDblVal = new Double(cs.getString(QUERY_oCreditLimit))) != null)
                acctInfo.setCreditLimit(tmpDblVal.doubleValue());

            //Set the hierarchy
            HierarchyDTO[] hierarchy = new HierarchyDTO[9];
            for (short i = 0; i < 9; i++) {
                hierarchy[i] = new HierarchyDTO();
                hierarchy[i].setLevel(i);
                hierarchy[i].setNumber(cs.getInt(QUERY_oHL + i));
                hierarchy[i].setDescription(this.parseTildaString(cs.getString(QUERY_oHLDesc + i)));
                hierarchy[i].setAgencyName(this.parseTildaString(cs.getString(QUERY_oHLDesc + i)));
            }
            acctInfo.setHierarchy(hierarchy);

            /*AlexanderZe

            // Transaction Summary
            CentralAcctTransSummary acctTrans = new CentralAcctTransSummary();
            //	...from EAGLS
            Integer tmpIntVal = new Integer(0);
            if ((tmpDblVal = output.getDouble(QUERY_oPastDueAmt")) != null)
                acctTrans.setPastDueAmt(tmpDblVal.doubleValue());
            if ((tmpDblVal = output.getDouble(QUERY_oPayDue")) != null)
                acctTrans.setPaymentDue(tmpDblVal.doubleValue());

            //Put in the payment due date
            String paymentDueDate = outParams.getValString(QUERY_oPayDueDate");
            if (paymentDueDate != null)
                try {
                    acctTrans.setPaymentDueDate(DateUtil.convertStringToDate(paymentDueDate));
                } catch (Exception e) {
                }//Do nothing if it fails for any reason

            if ((tmpIntVal = output.getInteger(QUERY_oTtlDollarDisputes")) != null)
                acctTrans.setTotalDisputesAmt(tmpIntVal.doubleValue());

            //Previously Commented

            String lastPayDate = outParams.getValString(QUERY_oLastPayDate");
            if (lastPayDate != null)
                try {
                    acctTrans.setLastPaymentDate(new Date(outParams.getValString(QUERY_oLastPayDate")));
                } catch (Exception e) {
                }//Do nothing if it fails for any reason

            if ((tmpDblVal = output.getDouble(QUERY_oLastPayAmt")) != null)
                acctTrans.setLastPaymentAmt(tmpDblVal.doubleValue());

            if ((tmpIntVal = output.getInteger(QUERY_oPastDueNbrDays")) != null)
                acctTrans.setPastDueDays(tmpIntVal.shortValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlDebitAmt")) != null)
                acctTrans.setAmtDebitTransCC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlDebitTrans")) != null)
                acctTrans.setAmtDebitTransPC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlCreditAmt")) != null)
                acctTrans.setAmtCreditTransCC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlCreditTrans")) != null)
                acctTrans.setAmtCreditTransPC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlCycleTrans")) != null)
                acctTrans.setTotalAmtTransCC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oTtlAmtPrevCycle")) != null)
                acctTrans.setTotalAmtTransPC(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oCurrentBalance")) != null)
                acctTrans.setCurrentBalance(tmpDblVal.doubleValue());

            if ((tmpDblVal = output.getDouble(QUERY_oAggregate")) != null)
                acctTrans.setAggregateCreditSum(tmpDblVal.doubleValue());

            //ONLY DO IF REQUESTED BY BO
            if (returnTypes == super.INCLUDE_TRANSACTION_SUMMARY) {
                //	...from TSYS
                if (!didTSYSFail) {
                    // TSYS sends the Total Outstanding (or Pending) Authorizations in dollar not in cents.
                    acctTrans.setPendingAuths((double) TSYSData.getOutstandingAuth());
                    acctTrans.setMoneyAvailSign(TSYSData.getMoneyAvailSign());
                    acctTrans.setMoneyAvailPad(TSYSData.getMoneyAvailPad());
                    acctTrans.setAvailCredit(TSYSData.getMoneyAvailable());
                }
            }
            //Set the account transaction objecty to the account info object
            acctInfo.setCentralAcctTransSummary(acctTrans);
            // query EAGLS for MCCG Tables
            IValList inParams1 = GX.CreateValList();
            inParams1.setValString(QUERY_AcctNbr", acctNum);
            ResultSet rs;
            try {
                rs = this.callQuery(SQL_RETRIEVE_VALID_MCCG_TABLES, inParams1);
                if (rs != null) {
                    Vector vMCCGTable = new Vector(10, 5);
                    while(rs.nextColumn()) {
                        NameDescTable pair = new NameDescTable();
                        pair.setName(rs.getValueString(1));
                        pair.setDescription(rs.getValueString(2));
                        vMCCGTable.addElement(pair);
                    };
                    GX.Release(rs);
                    NameDescTable[] MCCGtable = new NameDescTable[vMCCGTable.size()];
                    vMCCGTable.copyInto(MCCGtable);
                    acctInfo.setMCCGTables(MCCGtable);
                }
            } catch (NBException e) {
                //If nodatafound then set to an array of zero length
                acctInfo.setMCCGTables(new NameDescTable[0]);
            }
            // query EAGLS for Vendor Tables
            inParams1 = GX.CreateValList();
            inParams1.setValString(QUERY_AcctNbr", acctNum);
            try {
                rs = this.callQuery(SQL_RETRIEVE_VALID_VENDOR_TABLES, inParams1);
                    Vector vVendorTable = new Vector(10, 5);
                    while(rs.nextColumn()) {
                        NameDescTable pair = new NameDescTable();
                        pair.setName(rs.getString(1));
                        pair.setDescription(rs.getString(2));
                        vVendorTable.addElement(pair);
                    }
                    GX.Release(rs);
                    NameDescTable[] VendorTable = new NameDescTable[vVendorTable.size()];
                    vVendorTable.copyInto(VendorTable);
                    acctInfo.setVendorTables(VendorTable);
            } catch (NBException e) {
                //If nodatafound then set to an array of zero length
                acctInfo.setVendorTables(new NameDescTable[0]);
            }


            AlexanderZe*/

        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            if (cs != null)
                cs.close();
        }
        return acctInfo;
    }

    private final static String SP_UPDATE_CENTRAL_ACCOUNT_INFO =
            "{call Central_account_maintenance.maintain_account_info(" +
            "?,?,?,?,?," +
            "?,?,?,?,?," +
            "?,?,?,?,?," +
            "?,?,?,?,?," +
            "?,?,?,?,?," +
            "?)}";


    private final int UPDATE_iUserID = 1;
    private final int UPDATE_iAgencyName = 2;
    private final int UPDATE_iCentralaccountid = 3;
    private final int UPDATE_iCentralaccountnbr = 4;
    private final int UPDATE_iCentralaccountname = 5;
    private final int UPDATE_iMasteraccountingcd = 6;
    private final int UPDATE_iAccountingcenterid = 7;
    private final int UPDATE_iConveniencechecks = 8;
    private final int UPDATE_iNumberofconvchecks3 = 9;
    private final int UPDATE_iNumberofconvchecks20 = 10;
    private final int UPDATE_iNumberofconvchecks50 = 11;
    private final int UPDATE_iATMaccess = 12;
    private final int UPDATE_iDecrementingcard = 13;
    private final int UPDATE_iTravellerschecks = 14;
    private final int UPDATE_iCitypairprogram = 15;
    private final int UPDATE_iInvoicemediumEAGLS = 16;
    private final int UPDATE_iInvoicemediumpaper = 17;
    private final int UPDATE_iInvoicemediumEDI = 18;
    private final int UPDATE_iInvoicemediumELEC = 19;
    private final int UPDATE_iExpirationdate = 20;
    private final int UPDATE_iIntegratedflag = 21;
    private final int UPDATE_iInteragencyflag = 22;
    private final int UPDATE_iPurchaseflag = 23;
    private final int UPDATE_iTravelflag = 24;
    private final int UPDATE_iFleetflag = 25;
    private final int UPDATE_oResult = 26;


    /**
     * Gets all the information related to the central account maintenance and updates
     * the EAGLS database.
     *
     * @param oldCentralAccount - Object containing the old values of the Central Account ** this may not be needed
     * @param newCentralAccount - Object containing the new values of the Central Account
     * @return void - throw exception(s) if there is a failure
     *
     **/

    public void updateCentralAccountDetails(Connection connection, CentralAcct oldCentralAccount,
                                            CentralAcct newCentralAccount, String userId)throws SQLException, NoDataFoundException {
        //  transport.logDebug(null,"*************************INSIDE CentralAccountDAA.updateCentralAccountDetails()");
        CallableStatement cs = null;
        // Fill the IValList from the Business Object
        try {

            cs = connection.prepareCall(SP_UPDATE_CENTRAL_ACCOUNT_INFO);
            cs.setString(UPDATE_iUserID, userId);
            cs.setString(UPDATE_iAgencyName, newCentralAccount.getAgencyName().trim());
            cs.setString(UPDATE_iCentralaccountid, newCentralAccount.getCentralAcctID().trim());
            cs.setString(UPDATE_iCentralaccountnbr, newCentralAccount.getAcctNo().trim());
            cs.setString(UPDATE_iCentralaccountname, newCentralAccount.getAcctName().trim());
            cs.setString(UPDATE_iMasteraccountingcd, newCentralAccount.getDefaultAccountingCode().trim());
            cs.setString(UPDATE_iAccountingcenterid, newCentralAccount.getAccountingCenterID().trim());
            cs.setString(UPDATE_iConveniencechecks, newCentralAccount.getIssueConvenienceChecks()?"Y":"N");
            //*** BEGIN NumberOfConvenienceChecks processing -- start them all at "N"
            cs.setString(UPDATE_iNumberofconvchecks3, "N");
            cs.setString(UPDATE_iNumberofconvchecks20, "N");
            cs.setString(UPDATE_iNumberofconvchecks50, "N");
            int[] c = newCentralAccount.getConvenienceChecksNumbers();
            if ((c != null) && (c.length > 0))//only do the loops if needed
                for (int i = 0; i < c.length; i++) {//do these nextColumn two loops in case array contents are in a mixed up order
                    for (int j = 0; j < c.length; j++) {//to account for all possibilities
                        //Check all the node contents to see if they equal a valid number
                        if (c[j] == 3) cs.setString(UPDATE_iNumberofconvchecks3, "Y");
                        if (c[j] == 20) cs.setString(UPDATE_iNumberofconvchecks20, "Y");
                        if (c[j] == 50) cs.setString(UPDATE_iNumberofconvchecks50, "Y");
                    }
                }//*** END NumberOfConvenienceChecks processing
            cs.setString(UPDATE_iATMaccess, newCentralAccount.getHasATMAccess()?"Y":"N");
            cs.setString(UPDATE_iDecrementingcard, newCentralAccount.getUsesDecrementingCard()?"Y":"N");
            cs.setString(UPDATE_iTravellerschecks, newCentralAccount.getIssueTravelersChecks()?"Y":"N");
            cs.setString(UPDATE_iCitypairprogram, newCentralAccount.getCityPairProgram()?"Y":"N");
            cs.setString(UPDATE_iInvoicemediumEAGLS, true?"Y":"N");
            cs.setString(UPDATE_iInvoicemediumpaper, newCentralAccount.getInvoiceMediumPaper()?"Y":"N");
            cs.setString(UPDATE_iInvoicemediumEDI, newCentralAccount.getInvoiceMediumEDI()?"Y":"N");
            cs.setString(UPDATE_iInvoicemediumELEC, newCentralAccount.getInvoiceMediumElectronic()?"Y":"N");
            cs.setString(UPDATE_iExpirationdate, DateUtil.convertExpiryToString(newCentralAccount.getAccountExpDate()).trim());
            cs.setString(UPDATE_iIntegratedflag, newCentralAccount.getIntegratedFlag()?"Y":"N");
            cs.setString(UPDATE_iInteragencyflag, newCentralAccount.getInteragencyFlag()?"Y":"N");
            cs.setString(UPDATE_iPurchaseflag, newCentralAccount.getPurchaseFlag()?"Y":"N");
            cs.setString(UPDATE_iTravelflag, newCentralAccount.getTravelFlag()?"Y":"N");
            cs.setString(UPDATE_iFleetflag, newCentralAccount.getFleetFlag()?"Y":"N");
            cs.registerOutParameter(UPDATE_oResult, Types.VARCHAR);
            cs.execute();

            testOutput(cs.getString(UPDATE_oResult));
        } finally {
            closeAll(null, cs);
        }
        //todo log
//           // Enable Auditing on this function
//           insertAuditInformation(newCentralAccount.getAcctNo(),
//                   newCentralAccount.getAHierarchy(),
//                   SP_UPDATE_CENTRAL_ACCOUNT_INFO,
//                   inParams.toIValList());
    }




    /**
     * Retrieves detailed information from TSYS and EAGLS database about
     * a central account.
     *
     * @param   acctNum		identifies an central card account to retrieve
     *						(required non-null)
     * @return  CentralAcct  contains central account details
     **/
    /*AlexanderZe
    public CentralAcct retrieveCentralAcctMaint(Connection conn, String acctNum, String userId)
            throws SQLException, EaglsDAOError {
        isCentralAccount(acctNum);///# to check the input value is a valid central account.
        // Call EAGLS stored procedure to retrieve main central account info
//        IValListWrap inParams = new IValListWrap();
        // initialize stored procedure parameters
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(SP_RETRIEVE_CENTRAL_ACCT_MAINT_INFO);
            cs.setString(QUERY_iUserID, userId); //super.parseNullStrings(transport.getUserID()));
            cs.setString(QUERY_ioAcctNbr, acctNum);
            cs.registerOutParameter(QUERY_oCentralAcctName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHL0, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL1, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL2, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL3, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL4, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL5, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL6, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL7, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHL8, Types.INTEGER);
            cs.registerOutParameter(QUERY_oHLDesc0, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc1, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc2, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc3, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc4, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc5, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc6, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc7, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oHLDesc8, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oCentralAcctID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAgencyName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAgencyID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAcctType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oBillType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oMstrAcctCode, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAcctCenterID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oConvChecksFlg, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks3, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks20, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oNumberOfConvChecks50, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oTravChecksFlg, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oDebitCardFlg, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oATMAccessFlg, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oProgType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oCityPairProg, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumEAGLS, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumPAPER, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumEDI, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInvoiceMediumELECTRONIC, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAcctExpiryDate, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oAggregate, Types.DOUBLE);
            cs.registerOutParameter(QUERY_oCreditLimit, Types.DOUBLE);
            cs.registerOutParameter(QUERY_oIntegratedFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oInteragencyFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oPurchaseFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oTravelFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oFleetFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oFleetType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_oResult, Types.VARCHAR);
        // call the stored procedure with input parameters
        } catch {

        } finally {
            closeAll(conn, st);
        }
   /*AlexanderZe*/

    private static final short convertFleetType(String s) {
        if (s != null) {
            if ("M".equalsIgnoreCase(s)) {
                return CentralAcct.MASTERCARD;
            }
            if ("V".equalsIgnoreCase(s)) {
                return CentralAcct.VOYAGER;
            }
        }
        return CentralAcct.MASTERCARD;
    }

    private static final String parseTildaString(String s) {
        if (s == null) {
            return new String("");
        }
        return s;
    }

}
