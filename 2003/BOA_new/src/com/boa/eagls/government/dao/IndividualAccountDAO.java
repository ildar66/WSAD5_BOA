package com.boa.eagls.government.dao;

import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.account.IndividualAccount;
import com.boa.eagls.government.dto.account.IndividualAcctSummary;
import com.boa.eagls.government.dto.search.SearchDTO;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.SSNumber;
import com.boa.eagls.government.util.pagedList.SearchResult;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: OlegK
 * Date: 24.06.2003
 * Time: 18:47:03
 */
public class IndividualAccountDAO extends DAOBaseNew {

    private static Logger logger = Logger.getLogger(IndividualAccountDAO.class);

    // SQL header used by search methods
    private final static String SQL_SEARCH_IND_ACCT_SELECT =
            "SELECT /*+ RULE */ a.FIRST_NAME, a.LAST_NAME, TO_CHAR(a.ACCOUNT_NBR), a.SSN, a.AGENCY_BILL_NBR\n";

    private final static String SQL_SEARCH_IND_ACCT_FROM_HL =
            "  FROM AGENCY_HL h, ACCOUNT a\n";

    private final static String SQL_SEARCH_IND_ACCT_FROM_NO_HL =
            "  FROM ACCOUNT a\n";

    // SQL WHERE clause for search-by-last-name method
    private final static String SQL_SEARCH_BY_LAST_NAME_CLAUSE =
            " WHERE ? = a.LAST_NAME\n";

    // SQL WHERE clause for search-by-full-name method
    private final static String SQL_SEARCH_BY_FULL_NAME_CLAUSE =
            " WHERE ? = a.LAST_NAME\n" +
            "   AND ? = a.FIRST_NAME\n";

    // SQL WHERE clause for search-by-SSN method
    private final static String SQL_SEARCH_BY_SSN_CLAUSE =
            " WHERE a.SSN = ?\n";

    // SQL AND clause for Central Account ID searches
    private final static String SQL_SEARCH_BY_CENT_ACCT_ID_CLAUSE =
            "   AND a.AGENCY_BILL_NBR = ?\n";  // TODO Remove RTRIM

    // SQL footer used by search methods
    private final static String SQL_SEARCH_IND_ACCT_FOOTER_HL =
            "   AND (a.ACCOUNT_TYPE='I' OR a.ACCOUNT_TYPE='T')\n" + //FIXED 9/25
            "   AND a.HIERARCHY_NBR = h.HIERARCHY_NBR\n";

    private final static String SQL_SEARCH_IND_ACCT_FOOTER_NO_HL =
            "   AND (a.ACCOUNT_TYPE='I' OR a.ACCOUNT_TYPE='T')\n";

    private final static String SP_UPDATE_ACCOUNT =
            "ACCOUNT_MAINT.MAINTAIN_ACCOUNT_INFO(" +
            ":iUSERID," + // p_USERID             IN  VARCHAR2(12)
            ":iACCOUNTNUMBER," + // p_ACCOUNTNUMBER      IN  VARCHAR2
            ":iACCOUNTINGCENTERID," + // p_ACCOUNTINGCENTERID IN  VARCHAR2
            ":iMASTERACCOUNTCODE," + // p_MASTERACCOUNTCODE  IN  VARCHAR2(64)
            ":iFIRSTNAME," + // p_FIRSTNAME          IN  VARCHAR2(36)
            ":iLASTNAME," + // p_LASTNAME           IN  VARCHAR2(36)
            ":iEMPLOYEEID," + // p_EMPLOYEEID         IN  VARCHAR2(20)
            ":iADDRESSLINE1," + // p_ADDRESSLINE1       IN  VARCAHR2(40)
            ":iADDRESSLINE2," + // p_ADDRESSLINE2       IN  VARCAHR2(40)
            ":iADDRESSLINE3," + // p_ADDRESSLINE3       IN  VARCAHR2(40)
            ":iADDRESSLINE4," + // p_ADDRESSLINE4       IN  VARCAHR2(40)
            ":iCITY," + // p_CITY               IN  VARCAHR2(20)
            ":iSTATE," + // p_STATE              IN  CHAR(3)
            ":iCOUNTRY," + // p_COUNTRY            IN  CHAR(3)
            ":iZIP," + // p_ZIP                IN  CHAR(13)
            ":iBUSINESSPHONE," + // p_BUSINESSPHONE      IN  VARCHAR2(17)
            ":iHOMEPHONE," + // p_HOMEPHONE          IN  VARCHAR2(17)
            ":iFAX," + // p_FAX                IN  VARCHAR2(17)
            ":iEMAIL," + // p_EMAIL              IN  VARCHAR2(60)
            ":iCARDFLAG," + // p_CARDFLAG           IN  CHAR(1)
            ":iEXPIRATIONDATE," + // p_EXPIRATIONDATE     IN  VARCHAR2
            ":iGRADE," + // p_GRADE              IN  CHAR(18)
            ":iEMPLOYMENTSTATUS," + // p_EMPLOYMENTSTATUS   IN  VARCHAR2(12)
            ":iCreditLimit," + // p_CREDITLIMIT        IN  ACCOUNT.CREDIT_LIMIT%TYPE
/*
        commented by  on 02/28/2003
        ":iCashLimit," +        // p_CASHLIMIT        IN  ACCOUNT.CASH_LIMIT%TYPE
        ":iTempCashLimit," +        // p_TEMPCASHLIMIT        IN  ACCOUNT.CREDIT_LIMIT%TYPE
        ":iTempCashStartDate," +        // p_TEMPSTARTDATE        IN        VARCHAR2
        ":iTempCashEndDate," +        // p_TEMPENDDATE        IN        VARCHAR2
*/
            ":iDeactivateDate," + // p_DEACTIVATEDATE     IN  VARCHAR2
            ":iActivateDate," + // p_ACTIVATEDATE       IN VARCHAR2
            ":iAccountStatus," + // p_ACCOUNTSTATUS      IN ACCOUNT.ACCOUNT_STATUS%TYPE
            ":iReasonCode," + // p_REASONCODE         IN ACCOUNT_STATUS.REASON_CODE%TYPE,
            ":iBusinessPhoneExt," + // p_BUSINESS_EXT       IN ACCOUNT.BUSINESS_EXT%TYPE,
            ":iWarrantLevelDollarAmt," + // p_WARRANT_LEVEL      IN ACCOUNT.WARRANT_LEVEL_DOLLAR_AMT%TYPE
            ":iTransferPending," + // p_TRANSFER_FLAG      IN ACCOUNT.TRANSFER_PEND_FLAG%TYPE
            ":oResult)";              // p_RESULT             OUT VARCHAR2

    private final static String SP_RETRIEVE_TEMP_ACCOUNT =
            "TEMP_ACCOUNT_DETAILS.GET_ACCOUNT_INFO(" +
            ":iUserID," +
            ":iTempAccountNumber," +
            ":oCentralAccountID," +
            ":oHierarchyNumber," +
            ":oHL0," +
            ":oHL1," + // p_HL1                IN  NUMBER(7)
            ":oHL2," + // p_HL2                IN  NUMBER(7)
            ":oHL3," + // p_HL3                IN  NUMBER(7)
            ":oHL4," + // p_HL4                IN  NUMBER(7)
            ":oHL5," +
            ":oHL6," + // p_HL6                IN  NUMBER(7)
            ":oHL7," + // p_HL7                IN  NUMBER(7)
            ":oHL8," +
            ":oHL0Desc," +
            ":oHL1Desc," +
            ":oHL2Desc," +
            ":oHL3Desc," +
            ":oHL4Desc," +
            ":oHL5Desc," +
            ":oHL6Desc," +
            ":oHL7Desc," +
            ":oHL8Desc," +
            ":oAgencyName," +
            ":oProgramType," +
            ":oFirstName," +
            ":oLastName," +
            ":oSSN," +
            ":oEmployeeID," +
            ":oAccountType," +
            ":oBillingType," +
            ":oAddress1," +
            ":oAddress2," +
            ":oAddress3," +
            ":oAddress4," +
            ":oCity," +
            ":oState," +
            ":oCountry," +
            ":oZip," +
            ":oBusinessPhone," +
            ":oHomePhone," +
            ":oFax," +
            ":oEmail," +
            ":oMasterAccountingCode," +
            ":oAccountingCenterID," +
            ":oRankGrade," +
            ":oEmploymentSatus," +
            ":oConvenienceChecks," +
            ":oCardFlag," +
            ":oPaperFlag," +
            ":oCMID," +
            ":oATMAccess," +
            ":oDebitCard," +
            ":oTravellersCheck," +
            ":oAccountStatus," +
            ":oExpirationDate," +
            ":oCreditLimit," +
            ":oInsertDate," +
            ":oTsysAccountNumber," +
            ":oTaxExemptStatus," +
            ":oAccountingCode," +
            ":oCityPairID," +
            ":oEdiAddresss," +
            ":oABANumber," +
            ":oDDANumber," +
            ":oAchFormat," +
            ":oCityPairProgind," +
            ":oSuppressMemoFlag," +
            ":oBillingCycle," +
            ":oVIN," +
            ":oAgencyID," +
            ":oVehicleType," +
            ":oFuelLow," +
            ":oFuelHigh," +
            ":oOfficeID," +
            ":oResult)";


    private final static String SP_REQUEST_ID =
            "ACCOUNT_MAINT.GET_REQUEST_ID(" +
            ":oREQUESTID," + // p_REQUESTID      OUT VARCHAR2
            ":oResult)";				// p_RESULT         OUT VARCHAR

    private final static String SP_QUEUE_ACCOUNT =
            "ACCOUNT_MAINT.MAINTAIN_ACCOUNT_QUEUE(" +
            ":iUSERID," + // p_USERID            IN  VARCHAR2
            ":iREQUESTID," + // p_REQUESTID         IN  VARCHAR2
            ":iREQUESTNUMBER," + // p_REQUESTNUMBER     IN  NUMBER
            ":iACCOUNTNUMBER," + // p_ACCOUNTNUMBER     IN  VARCHAR2
            ":iSTATUS," + // p_STATUS            IN  VARCHAR2
            ":iMAINTFUNCTION," + // p_MAINTFUNCTION     IN  VARCHAR2
            ":iFIELDNAME," + // p_FIELDNAME         IN  VARCHAR2
            ":iOLDVALUE," + // p_OLDVALUE		   IN  VARCHAR2
            ":iNEWVALUE," + // p_NEWVALUE          IN  VARCHAR2
            ":iTIMESTAMP," + // p_TIMESTAMP         IN  VARCHAR2
            ":oResult)";				// p_RESULT            OUT VARCHAR2);

    // added by  on 02/28/2003
    private final static String SP_QUEUE_CASH_ACCOUNT =
            "ACCOUNT_MAINT.MAINTAIN_ACCOUNT_CASH_QUEUE(" +
            ":iUSERID," + // p_USERID            IN  VARCHAR2
            ":iREQUESTID," + // p_REQUESTID         IN  VARCHAR2
            ":iREQUESTNUMBER," + // p_REQUESTNUMBER     IN  NUMBER
            ":iACCOUNTNUMBER," + // p_ACCOUNTNUMBER     IN  VARCHAR2
            ":iSTATUS," + // p_STATUS            IN  VARCHAR2
            ":iMAINTFUNCTION," + // p_MAINTFUNCTION     IN  VARCHAR2
            ":iFIELDNAME," + // p_FIELDNAME         IN  VARCHAR2
            ":iOLDVALUE," + // p_OLDVALUE		   IN  VARCHAR2
            ":iNEWVALUE," + // p_NEWVALUE          IN  VARCHAR2
            ":iTIMESTAMP," + // p_TIMESTAMP         IN  VARCHAR2
            ":iTEMPCASHSTARTDATE," + // p_TEMPCASHSTARTDATE         IN  VARCHAR2
            ":iTEMPCASHENDDATE," + // p_TEMPCASHENDDATE         IN  VARCHAR2
            ":oResult)";				// p_RESULT            OUT VARCHAR2);


    private final static String GET_ACCOUNT_OWNER_INFO = "ACCOUNT_HOLDER_INQUIRY.GET_ACCOUNT_OWNER_INFO(" +
            ":iUSERID," +
            ":iACCOUNTNUMBER," + // p_ACCOUNTNUMBER      IN  VARCHAR2
            ":oFIRSTNAME," + // p_FIRSTNAME          IN  VARCHAR2(36)
            ":oLASTNAME," + // p_LASTNAME           IN  VARCHAR2(36)
            ":oSSN," + // p_SSN                IN  CHAR(9)
            ":oResult)";

    /**
     * Searches EAGLS database for accounts that match any of the non null parameters
     * of a given cardholder.
     * @throws SQLException
     * @throws EaglsDAOError
     * @see IndividualAcctSummary
     **/
    public Collection search(Connection conn,
                             SearchDTO viewAccountForm,
                             int hierarchy[],
                             short depth,
                             int currentHierarchy[],
                             String currentBaseRole,
                             char[] programTypes)
            throws SQLException, EaglsDAOError {
        // Create access hierarchy to restrict the search results to those
        // under the user's current hierarchy

        int hAccess[] = authorizeHierarchySearch(hierarchy, depth, currentHierarchy);


        boolean nameFlag = false;
        if (hAccess == null) {
            throw new EaglsDAOError("SYS_E0003:IndividualAccountDAA::Could not generate authorization hierarchy");
        }

        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_IND_ACCT_SELECT);
        if (hierarchy != null && hierarchy.length > 0) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_NO_HL);
        }
        sqlStmt.append("WHERE \n");


        String acctNum = viewAccountForm.getTxt_accountNumber();
        String acctStatus = viewAccountForm.getRag_acctMaintSearchFor();
        String lastName = viewAccountForm.getTxt_lastName();
        String firstName = viewAccountForm.getTxt_firstName();

        String zip = viewAccountForm.getTxt_zipCode();
        String busPhone = viewAccountForm.getTxt_businessPhone();
        String countryCode = viewAccountForm.getTxt_CountryCode();
        String ssNbr = viewAccountForm.getTxt_socialSecurityNumber();

        boolean selectedAccountNumber = !(acctNum == null || acctNum.equals(""));
        boolean selectedLastName = !(lastName == null || lastName.equals(""));
        boolean selectedZip = !(zip == null || zip.equals(""));
        boolean selectedBusPhone = !(busPhone == null || busPhone.equals(""));
        boolean selectedCountryCode = !(countryCode == null || countryCode.equals(""));
        boolean selectedSSNbr = !(ssNbr == null || ssNbr.equals(""));
        boolean selectedHierarchy = !(viewAccountForm.getChk_hierLevel() == null ||
                "".equals(viewAccountForm.getChk_hierLevel()));

        // Append access hierarchy to end of WHERE clause in embedded SQL
        boolean allNull = true;

        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                if (allNull) {
                    sqlStmt.append("   h.HL" + i + " = " + hAccess[i] + "\n");
                    allNull = false;
                } else
                    sqlStmt.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
            }
        }

        if (acctStatus != null && !acctStatus.equals("")) {
            if (allNull)
                sqlStmt.append("    a.ACCOUNT_STATUS='" + acctStatus.trim() + "'\n");
            else
                sqlStmt.append("    AND a.ACCOUNT_STATUS='" + acctStatus.trim() + "'\n");
            allNull = false;
        }
        if (lastName != null && !lastName.equals("")) {
            lastName = lastName.toUpperCase();
            if (allNull) {
                if (lastName.indexOf('*') >= 0) {
                    sqlStmt.append("    a.LAST_NAME LIKE('"+lastName.replace('*', '%')+"')\n");//:lastName
                } else {
                    sqlStmt.append("    a.LAST_NAME = '"+lastName+"'\n");//:lastName
                }
            } else {
//                sqlStmt.append("    AND a.LAST_NAME = ?\n");//:lastName
                if (lastName.indexOf('*') >= 0) {
                    sqlStmt.append("  AND a.LAST_NAME LIKE('"+lastName.replace('*', '%')+"')\n");//:lastName
                } else {
                    sqlStmt.append("  AND a.LAST_NAME = '"+lastName+"'\n");//:lastName
                }
            }
            allNull = false;
            //moved to PreparedStatement filling
//            inParams.set(":lastName", lastName.toUpperCase());
            nameFlag = true;
        }
        if (firstName != null && !firstName.equals("")) {
            firstName = firstName.toUpperCase();
            if (allNull) {
//                sqlStmt.append("    a.FIRST_NAME = ? \n");//:firstName
                if (firstName.indexOf('*') >= 0) {
                    sqlStmt.append("    a.FIRST_NAME LIKE('"+firstName.replace('*', '%')+"')\n");//:firstName
                } else {
                    sqlStmt.append("    a.FIRST_NAME = "+firstName+"\n");//:firstName
                }
            }
            else {
                if (firstName.indexOf('*') >= 0) {
                    sqlStmt.append("  AND  a.FIRST_NAME LIKE('%"+firstName+"%')\n");//:firstName
                } else {
                    sqlStmt.append("  AND  a.FIRST_NAME = '"+firstName+"'\n");//:firstName
                }
//                sqlStmt.append("    AND a.FIRST_NAME =? \n");//:firstName
            }
            allNull = false;
            //moved to PreparedStatement filling
//            inParams.set(":firstName", firstName.toUpperCase());
            nameFlag = true;
        }

        if (zip != null && !zip.equals("")) {
            if (allNull)
                sqlStmt.append("    a.ZIP = '" + zip + "'\n");
            else
                sqlStmt.append("    AND a.ZIP = '" + zip + "'\n");
            allNull = false;
        }
        if (busPhone != null && !busPhone.equals("")) {
            if (allNull)
                sqlStmt.append("    a.WORK_PHONE = '" + busPhone + "'\n");
            else
                sqlStmt.append("    AND a.WORK_PHONE = '" + busPhone + "'\n");
            allNull = false;
        }
        /* // business Phone Country Code supposed to be the first x chars of Work Phone
              if(countryCode != null && !countryCode.equals("")){
                  if(allNull)
                     sqlStmt.append("    a.WORK_PHONE = '" + countryCode +"'\n");
                  else
                     sqlStmt.append("    AND a.WORK_PHONE = '" + countryCode +"'\n");
                  allNull=false;
              }
         */
        if (ssNbr != null && !ssNbr.equals("")) {
            if (allNull)
                sqlStmt.append("    a.SSN = '" + SSNumber.parseString(ssNbr) + "'\n");
            else
                sqlStmt.append("    AND a.SSN = '" + SSNumber.parseString(ssNbr) + "'\n");
            allNull = false;
        }

        if (hierarchy != null && hierarchy.length > 0) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_NO_HL);
        }
        if (allNull)
            throw new EaglsDAOError("No Criteria Selected");

        sqlStmt = appendPTCheckByUserPT(sqlStmt, currentBaseRole, programTypes);
        sqlStmt.append(" ORDER BY A.LAST_NAME, A.FIRST_NAME \n");

        PreparedStatement st = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        int count = 1;
        try {
//            inParams.set(":lastName", lastName.toUpperCase());
            st = conn.prepareStatement(sqlStmt.toString());

//            if (lastName != null && !lastName.equals("")) {
//                st.setString(count++, lastName.toUpperCase());//":lastName"
//            }
//            if (firstName != null && !firstName.equals("")) {
//                st.setString(count++, firstName.toUpperCase());//":firstName"
//            }

//            st.setInt(1, new Integer(SSNumber.parseString(SSN)).intValue());
//            st.setInt(2, new Integer(centralAcctID).intValue());
            res = st.executeQuery();
            while (res.next()) {
                IndividualAcctSummary ia = populateIndividualAcctSummary(res, 1);
                list.add(ia);
            }
        } finally {
            closeAll(res, st);
        }
        // Prepare parameter list
//        IValListWrap inParams = new IValListWrap();
        return list;
    }

    public Collection searchBySsn(Connection conn, int CentralAcctID, String SSN,
                                  int hUser[], int firstRecord, int numRecords) throws SQLException, EaglsDAOError {
        return searchIndAcctBySSN(conn, CentralAcctID, SSN, hUser, firstRecord, numRecords, false).getElements();
    }

    public int countBySsn(Connection conn, int centralAcctID, String SSN,
                          int hUser[]) throws SQLException, EaglsDAOError {
        return searchIndAcctBySSN(conn, centralAcctID, SSN, hUser, -1, -1, true).getSize();
    }

    /**
     * Searches EAGLS database for accounts that match the given central account ID
     * and account holder's social security number.  SSN must be a non-null value.
     * centralAcctID will be ignored if it is null.
     *
     * @param	centralAcctID	return accounts assigned to this central accounting ID (optional)
     * @param	SSN				return accounts assigned this social security number
     * @return  IndividualAcctSummary[] a summary list of accounts that match
     *                                  SSN and centralAcctID; length==0 if no results found
     * @exception	com.nb.gsa.Exceptions.NBException	search failed
     * @see com.nb.gsa.Business.IndividualAccount.IndividualAcctSummary
     **/
    private SearchResult searchIndAcctBySSN(Connection conn,
                                            int centralAcctID,
                                            String SSN,
                                            int hUser[],
                                            int firstRecord,
                                            int numRecords,
                                            boolean count
                                            )
            throws SQLException, EaglsDAOError {
        SearchResult result = new SearchResult();
        // Create access hierarchy to restrict the search results to those
        // under the user's current hierarchy
        int hAccess[] = authorizeHierarchySearch(null, (short) 0, hUser);
        if (hAccess == null) {
            throw new EaglsDAOError("internalError.nullAuthorizationHierarchy");
            //new NBError("SYS_E0003:IndividualAccountDAA::Could not generate authorization hierarchy");
        }

        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_IND_ACCT_SELECT);
        StringBuffer hlClause = new StringBuffer();

        // Append access hierarchy to end of WHERE clause in embedded SQL
        boolean useHLTable = false;
        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                hlClause.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
                useHLTable = true;
            }
        }

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_NO_HL);
        }
        sqlStmt.append(SQL_SEARCH_BY_SSN_CLAUSE);

        if (centralAcctID > 0) {
            sqlStmt.append(SQL_SEARCH_BY_CENT_ACCT_ID_CLAUSE);
//            inParams.set(":centralacctid", centralAcctID);
        }

        sqlStmt.append(hlClause.toString());

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_NO_HL);
        }

        sqlStmt.append(" ORDER BY a.ACCOUNT_NBR \n");
        PreparedStatement st = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        try {
            if (count) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = conn.prepareStatement(countSQL);
                st.setInt(1, new Integer(SSNumber.parseString(SSN)).intValue());
                st.setInt(2, new Integer(centralAcctID).intValue());
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(), firstRecord, numRecords);
                st = conn.prepareStatement(pagedSQL);
                st.setInt(1, new Integer(SSNumber.parseString(SSN)).intValue());
                st.setInt(2, new Integer(centralAcctID).intValue());
                res = st.executeQuery();
                while (res.next()) {
                    IndividualAcctSummary ia = populateIndividualAcctSummary(res, 1);
                    list.add(ia);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;
    }

    /**
     * Searches EAGLS database for accounts that match the given central account ID
     * and account holder's full name.  LastName and lastName must be non-null values.
     * centralAcctID will be ignored if it is null.
     *
     * @param	centralAcctID	return accounts assigned to this central accounting ID (optional)
     * @param   firstName   the cardholder's first name to search for (required)
     * @param   lastName    the last name to search for (required)
     * @return  IndividualAcctSummary[] a summary list of accounts that match
     *                                  firstName & lastName; length==0 if no results found
     * @exception	com.nb.gsa.Exceptions.NBException	search failed
     * @see com.nb.gsa.Business.IndividualAccount.IndividualAcctSummary
     **/
    private SearchResult searchIndAcctByFullName(Connection conn,
                                                 int centralAcctID,
                                                 String firstName,
                                                 String lastName,
                                                 int hUser[],
                                                 int firstRecord,
                                                 int numRecords,
                                                 boolean count)
            throws SQLException, EaglsDAOError {
        SearchResult result = new SearchResult();
        // Create access hierarchy to restrict the search results to those
        // under the user's current hierarchy
        int hAccess[] = authorizeHierarchySearch(null, (short) 0, hUser);
        if (hAccess == null) {
            throw new EaglsDAOError("internalError.nullAuthorizationHierarchy");
            //throw new NBError("SYS_E0003:IndividualAccountDAA::Could not generate authorization hierarchy");
        }

        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_IND_ACCT_SELECT);
        StringBuffer hlClause = new StringBuffer();

        // Append access hierarchy to end of WHERE clause in embedded SQL
        boolean useHLTable = false;
        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                hlClause.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
                useHLTable = true;
            }
        }

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_NO_HL);
        }
        sqlStmt.append(SQL_SEARCH_BY_FULL_NAME_CLAUSE);

//        if (centralAcctID != null && centralAcctID.length() > 0) {
        if (centralAcctID > 0) {
            sqlStmt.append(SQL_SEARCH_BY_CENT_ACCT_ID_CLAUSE);
        }

        sqlStmt.append(hlClause);

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_NO_HL);
        }
        logger.debug("SQL validateCentralAcctIDForSetup: " + sqlStmt);
        PreparedStatement st = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        try {
            if (count) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = conn.prepareStatement(countSQL);
                st.setString(1, lastName.toUpperCase());
                st.setString(2, firstName.toUpperCase());
                st.setInt(3, centralAcctID);
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(), firstRecord, numRecords);
                st = conn.prepareStatement(pagedSQL);
                st.setString(1, lastName.toUpperCase());
                st.setString(2, firstName.toUpperCase());
                st.setInt(3, centralAcctID);
                res = st.executeQuery();
                while (res.next()) {
                    IndividualAcctSummary ia = populateIndividualAcctSummary(res, 1);
                    list.add(ia);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;//list;
    }


    /**
     *
     * @param con
     * @param centralAcctID
     * @param lastName
     * @param hUser user's current hierarchy
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */

    public Collection searchByFullName(Connection conn, int centralAcctID, String firstName, String lastName,
                                       int hUser[], int firstRecord, int numRecords) throws SQLException, EaglsDAOError {
        return searchIndAcctByFullName(conn, centralAcctID, firstName, lastName, hUser, firstRecord, numRecords, false).getElements();
    }

    public int countByFullName(Connection conn, int centralAcctID, String firstName, String lastName,
                               int hUser[]) throws SQLException, EaglsDAOError {
        return searchIndAcctByFullName(conn, centralAcctID, firstName, lastName, hUser, -1, -1, true).getSize();
    }

    public Collection searchByLastName(Connection conn, int centralAcctID, String lastName,
                                       int hUser[], int firstRecord, int numRecords) throws SQLException, EaglsDAOError {
        return searchIndAcctByLastName(conn, centralAcctID, lastName, hUser, firstRecord, numRecords, false).getElements();
    }

    public int countByLastName(Connection conn, int centralAcctID, String lastName,
                               int hUser[]) throws SQLException, EaglsDAOError {
        return searchIndAcctByLastName(conn, centralAcctID, lastName, hUser, -1, -1, true).getSize();
    }

    public SearchResult searchIndAcctByLastName(Connection con, int centralAcctID, String lastName, int hUser[],
                                                int firstRecord, int numRecords, boolean count)
            throws SQLException, EaglsDAOError {

        SearchResult result = new SearchResult();
        int hAccess[] = authorizeHierarchySearch(null, (short) 0, hUser);
        if (hAccess == null) {
            throw new EaglsDAOError("internalError.nullAuthorizationHierarchy");
        }

        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_IND_ACCT_SELECT);
        StringBuffer hlClause = new StringBuffer();

        PreparedStatement st = null;
        ResultSet res = null;

        // Append access hierarchy to end of WHERE clause in embedded SQL
        boolean useHLTable = false;
        for (short i = 0; i < 9; i++) {
            if (hAccess[i] >= 0) {
                hlClause.append("   AND h.HL" + i + " = " + hAccess[i] + "\n");
                useHLTable = true;
            }
        }

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FROM_NO_HL);
        }
        sqlStmt.append(SQL_SEARCH_BY_LAST_NAME_CLAUSE);

//        if (CentralAcctID != null && CentralAcctID.length() > 0) {
        sqlStmt.append(SQL_SEARCH_BY_CENT_ACCT_ID_CLAUSE);
        //inParams.set(":centralacctid", CentralAcctID);
//        }
        sqlStmt.append(hlClause);

        if (useHLTable) {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_HL);
        } else {
            sqlStmt.append(SQL_SEARCH_IND_ACCT_FOOTER_NO_HL);
        }
        ArrayList list = new ArrayList();
        try {
            if (count) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = con.prepareStatement(countSQL);
                st.setString(1, lastName.toUpperCase());
                st.setInt(2, centralAcctID);
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(), firstRecord, numRecords);
                st = con.prepareStatement(pagedSQL);
                st.setString(1, lastName.toUpperCase());
                st.setInt(2, centralAcctID);
                res = st.executeQuery();
                while (res.next()) {
                    IndividualAcctSummary ia = populateIndividualAcctSummary(res, 1);
                    list.add(ia);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;//list;
    }

    public Collection searchIndAcctByCentralAccID(Connection con,
                                                  String centralAcctID, int larsSize) throws SQLException {
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_IND_ACCT_SELECT);

//        KeyValueList kvl = new KeyValueList();//empty for test case

        if (centralAcctID != null && !centralAcctID.equals("")) {
            sqlStmt.append(" a.AGENCY_BILL_NBR = '" + centralAcctID + "'\n");
        }
        PreparedStatement st = null;
        ResultSet res = null;
        ArrayList list = new ArrayList();
        try {
            //todo send SQL to logger
            logger.debug("SQL : " + sqlStmt);
            logger.debug("con: " + con.toString());
            st = con.prepareStatement(sqlStmt.toString());
            logger.debug("st: " + st);
            res = st.executeQuery();
            logger.debug("res: " + res);
            while (res.next()) {
                IndividualAccount ia = populate(res, 1);
                logger.debug("AG_BIIL_NR: " + ia.getAgencyBillNumber());
                list.add(ia);
            }
        } finally {
            closeAll(res, st);
        }
//        return transformResultSetToUserInfoVectorHierarchy(rs);
        return list;
    }

    private IndividualAcctSummary populateIndividualAcctSummary(ResultSet rs, int index) throws SQLException {
        IndividualAcctSummary result = new IndividualAcctSummary();
        result.setFirstName(rs.getString(index++));
        result.setLastName(rs.getString(index++));
        result.setAcctNumber(rs.getString(index++));
        result.setSSN(SSNumber.toString(rs.getString(index++)));
        result.setCentralAcctID(rs.getString(index++));
        return result;
    }

    private IndividualAccount populate(ResultSet rs, int index) throws SQLException {
        return new IndividualAccount(rs.getString(index++), // first name
                rs.getString(index++), // first name
                rs.getString(index++), // first name
                rs.getString(index++), // first name
                rs.getString(index++));
    }

    /**
     * Store a new Individual Account in the EAGLS database.  This method
     * calls the stored procedure specified in IndividualAcctDAA.SP_CREATE_IND_ACCT.
     * If the stored procedure succeeds in creating the new account, it returns
     * the temporary account number assigned to it.  This account number can be
     * used to refer to the account until TSYS assigns a real account number to
     * the account.
     *
     * @param	account	Contains the initial attributes for the new account
     * @return	String	The temporary account number assigned to the new account
     * @exception	com.nb.gsa.Exceptions.NBException	Failed to create the new account
     **/
    public String createIndividualAcct(Connection conn, com.boa.eagls.government.dto.IndividualAccount account)
            throws SQLException {
//        Profile profile = account.getProfile();
        String tmpAccountNumber = null;

        String TMP_SQL = "INSERT INTO ACCOUNT(ACCOUNT_NBR, HIERARCHY_NBR, AGENCY_BILL_NBR," +
                " PROG_TYPE, ACCOUNTING_CENTER_ID, MASTER_ACCOUNT_CODE," + //6
                "CONVENIENCE_CHECK_FLAG, TRAVELERS_CHECK_FLAG," + //8
                "ACCOUNT_STATUS, ACCT_OPT_SET_ID, MCC_OPT_SET_ID, " + ////////////////////////////////// 11
                "ATM_ACCESS_FLAG, DEBIT_CARD_FLAG, " + //11
                "BILLING_TYPE, CMID, FIRST_NAME, LAST_NAME, SSN, " +
                "EMPLOYEE_ID, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, " +
                "CITY, STATE, COUNTRY, ZIP, WORK_PHONE, HOME_PHONE, " +
                "EMAIL, CARD_FLAG, EXPIRATION_DATE, CREDIT_LIMIT, RANK_GRADE, " +
                "EMPLOYEE_STATUS, ACCOUNT_TYPE) " +

                "VALUES(?, ?, ?," + //ACCOUNT_NBR, HIERARCHY_NBR, AGENCY_BILL_NBR,     //3
                " ?, ?, ?, " + //PROG_TYPE, ACCOUNTING_CENTER_ID, MASTER_ACCOUNT_CODE  //6
                " ?, ?, " + //CONVENIENCE_CHECK_FLAG, TRAVELERS_CHECK_FLAG," +  //8

                "'O', '03C07', 'CW004', " + //ACCOUNT_STATUS, ACCT_OPT_SET_ID, MCC_OPT_SET_ID       //8 - 11

                " ?, ?, " + //11 - 8     ATM_ACCESS_FLAG, DEBIT_CARD_FLAG,  // 10  - 13
                "?, ?, ?, ?, ?," + //   BILLING_TYPE, CMID, FIRST_NAME, LAST_NAME, SSN, // 15 - 18
                " ?, ?, ?, ?, ?," + //20    -  17   EMPLOYEE_ID, ADDRESS1, ADDRESS2, ADDRESS3, ADDRESS4, // 20 - 23
                /*32*/         "?, ?, ?, ?, ?, ?," + // CITY, STATE, COUNTRY, ZIP, WORK_PHONE, HOME_PHONE, // 26 - 29
                " ?, ?, ?, ?, ?," + // EMAIL, CARD_FLAG, EXPIRATION_DATE, CREDIT_LIMIT, RANK_GRADE, // 31 34
                " ?, 'I'" + // EMPLOYEE_STATUS, ACCOUNT_TYPE   32 35
                ")";

        int CREATE_IND_ACCT_QUERY_l_tempaccountnumber = 1;
        int CREATE_IND_ACCT_QUERY_l_hierarchynumber = 2;
        int CREATE_IND_ACCT_QUERY_p_CENTRALACCOUNTID = 3;
        int CREATE_IND_ACCT_QUERY_p_PROGRAMTYPE = 4;
        int CREATE_IND_ACCT_QUERY_l_accountingcenterid = 5;
        int CREATE_IND_ACCT_QUERY_p_MASTERACCOUNTCODE = 6;
        int CREATE_IND_ACCT_QUERY_p_CONVENIENCECHECK = 7;
        int CREATE_IND_ACCT_QUERY_p_TRAVELERSCHECKFLAG = 8;
        int CREATE_IND_ACCT_QUERY_p_ATMACCESSFLAG = 9;
        int CREATE_IND_ACCT_QUERY_p_DEBITCARDFLAG = 10;

        int CREATE_IND_ACCT_QUERY_p_BILLINGTYPE = 11;
        int CREATE_IND_ACCT_QUERY_p_CARDDESIGN = 12;
        int CREATE_IND_ACCT_QUERY_p_FIRSTNAME = 13;
        int CREATE_IND_ACCT_QUERY_p_LASTNAME = 14;
        int CREATE_IND_ACCT_QUERY_p_SSN = 15;
        int CREATE_IND_ACCT_QUERY_p_EMPLOYEEID = 16;
        int CREATE_IND_ACCT_QUERY_p_ADDRESSLINE1 = 17;
        int CREATE_IND_ACCT_QUERY_p_ADDRESSLINE2 = 18;
        int CREATE_IND_ACCT_QUERY_p_ADDRESSLINE3 = 19;
        int CREATE_IND_ACCT_QUERY_p_ADDRESSLINE4 = 20;
        int CREATE_IND_ACCT_QUERY_p_CITY = 21;
        int CREATE_IND_ACCT_QUERY_p_STATE = 22;
        int CREATE_IND_ACCT_QUERY_p_COUNTRY = 23;
        int CREATE_IND_ACCT_QUERY_p_ZIP = 24;
        int CREATE_IND_ACCT_QUERY_p_BUSINESSPHONE = 25;
        int CREATE_IND_ACCT_QUERY_p_HOMEPHONE = 26;
        int CREATE_IND_ACCT_QUERY_p_EMAIL = 27;
        int CREATE_IND_ACCT_QUERY_p_CARDFLAG = 28;
        int CREATE_IND_ACCT_QUERY_l_expirationdate = 29;
        int CREATE_IND_ACCT_QUERY_p_CREDITLIMIT = 30;
        int CREATE_IND_ACCT_QUERY_p_GRADE = 31;
        int CREATE_IND_ACCT_QUERY_p_EMPLOYMENTSTATUS = 32;
//        int CREATE_IND_ACCT_QUERY_SYSDATE = 33;
        PreparedStatement st = null;
        ResultSet res = null;

        try {
            String getTemp = "SELECT TEMP_ACCOUNT_NBR_SEQUENCE.NEXTVAL FROM DUAL";
            st = conn.prepareStatement(getTemp);
            res = st.executeQuery();
            if (res.next()) {
                tmpAccountNumber = res.getString(1);
            }

            st = conn.prepareStatement(TMP_SQL);
            st.setString(CREATE_IND_ACCT_QUERY_l_tempaccountnumber, tmpAccountNumber);
            st.setString(CREATE_IND_ACCT_QUERY_l_hierarchynumber, "53420");
            st.setString(CREATE_IND_ACCT_QUERY_p_CENTRALACCOUNTID, account.getCentralAcctID());

            String progType = null;
            switch (account.getProgramType()) {
                case Account.PURCHASE:
                    progType = "P";
                    break;
                case Account.TRAVEL:
                    progType = "T";
                    break;
                case Account.FLEET:
                    progType = "F";
                    break;
                case Account.INTEGRATE:
                    progType = "I";
                    break;
                case Account.INTERAGENCY:
                    progType = "A";
                    break;
                default:
                    progType = "~";
                    break;
            }
            st.setString(CREATE_IND_ACCT_QUERY_p_PROGRAMTYPE, progType);
            st.setString(CREATE_IND_ACCT_QUERY_l_accountingcenterid, "305690");//account.getAccountingCenterID());
            st.setString(CREATE_IND_ACCT_QUERY_p_MASTERACCOUNTCODE, account.getDefaultAccountingCode());
            st.setBoolean(CREATE_IND_ACCT_QUERY_p_CONVENIENCECHECK, account.getIssueConvenienceChecks());
            st.setBoolean(CREATE_IND_ACCT_QUERY_p_TRAVELERSCHECKFLAG, account.getUsesTravelersChecks());
            st.setString(CREATE_IND_ACCT_QUERY_p_ATMACCESSFLAG, "N");
            st.setString(CREATE_IND_ACCT_QUERY_p_DEBITCARDFLAG, "N");
            st.setString(CREATE_IND_ACCT_QUERY_p_BILLINGTYPE, account.getBillingType() == 1 ? "I" : "C");
            st.setString(CREATE_IND_ACCT_QUERY_p_CARDDESIGN, account.getCardType());
            st.setString(CREATE_IND_ACCT_QUERY_p_FIRSTNAME, account.getFirstName().toUpperCase());
            st.setString(CREATE_IND_ACCT_QUERY_p_LASTNAME, account.getLastName().toUpperCase());
            st.setString(CREATE_IND_ACCT_QUERY_p_SSN, account.getSSN());
            st.setString(CREATE_IND_ACCT_QUERY_p_EMPLOYEEID, account.getEmployeeID());
            st.setString(CREATE_IND_ACCT_QUERY_p_ADDRESSLINE1, account.getAddress1());
            st.setString(CREATE_IND_ACCT_QUERY_p_ADDRESSLINE2, account.getAddress2());
            st.setString(CREATE_IND_ACCT_QUERY_p_ADDRESSLINE3, account.getAddress3());
            st.setString(CREATE_IND_ACCT_QUERY_p_ADDRESSLINE4, account.getAddress4());
            st.setString(CREATE_IND_ACCT_QUERY_p_CITY, account.getCity());
            st.setString(CREATE_IND_ACCT_QUERY_p_STATE, account.getStateOrProvince());
            st.setString(CREATE_IND_ACCT_QUERY_p_COUNTRY, account.getCountry());
            st.setString(CREATE_IND_ACCT_QUERY_p_ZIP, account.getZip());
            st.setString(CREATE_IND_ACCT_QUERY_p_BUSINESSPHONE, account.getBusinessPhone());
            st.setString(CREATE_IND_ACCT_QUERY_p_HOMEPHONE, account.getHomePhone());
            st.setString(CREATE_IND_ACCT_QUERY_p_EMAIL, account.getEMail());
            st.setString(CREATE_IND_ACCT_QUERY_p_CARDFLAG, account.getCardExists() ? "Y" : "N");
            st.setString(CREATE_IND_ACCT_QUERY_l_expirationdate, null);
            st.setDouble(CREATE_IND_ACCT_QUERY_p_CREDITLIMIT, account.getCreditLimit());
            st.setString(CREATE_IND_ACCT_QUERY_p_GRADE, account.getGrade());
            st.setString(CREATE_IND_ACCT_QUERY_p_EMPLOYMENTSTATUS, account.getEmploymentStatus());
            st.executeUpdate();
        } finally {
            closeAll(null, st);
        }


//        IValListWrap inParams = new IValListWrap();

//        String currentUserID = transport.getUserID();
//        inParams.set(":iUserID", currentUserID);
//
//        HierarchyDTO[] hierarchy = account.getAHierarchy();
//        if (hierarchy == null || hierarchy.length != 9) {
//            for (short i = 0; i < 9; i++) {
//                inParams.set(":HL" + i, 0);
//            }
//        } else {
//            for (short i = 0; i < 9; i++) {
//                inParams.set(":iHL" + i, hierarchy[i].getValue());
//            }
//        }
//
//        inParams.set(":iCntrlAcctID", account.getCentralAcctID());
//
//        switch (account.getProgramType()) {
//            case Account.PURCHASE:
//                inParams.set(":iProgType", "P");
//                break;
//            case Account.TRAVEL:
//                inParams.set(":iProgType", "T");
//                break;
//            case Account.FLEET:
//                inParams.set(":iProgType", "F");
//                break;
//            case Account.INTEGRATE:
//                inParams.set(":iProgType", "I");
//                break;
//            case Account.INTERAGENCY:
//                inParams.set(":iProgType", "A");
//                break;
//            default:
//                inParams.set(":iProgType", "~");
//                break;
//        }
//
//        inParams.set(":iAcctCntrID", (account.getAccountingCenterID()));
//        inParams.set(":iMstrAcctCode", (account.getDefaultAccountingCode()));
//        inParams.set(":iConvChckFlg", (account.getIssueConvenienceChecks()));
//        inParams.set(":iTravelCheckFlg", (account.getUsesTravelersChecks()));


/*        inParams.set(":iATMAccessFlg", (account.getHasATMAccess()));
        inParams.set(":iDebitCardFlg", (account.getUsesDecrementingCard()));
        inParams.set(":iGenPaperFlg", (account.getGeneratePaperStatement()));
        inParams.set(":iBillType", (account.getBillingType() == 1) ? "I" : "C");
        inParams.set(":iCardDesign", (account.getCardType()));
        inParams.set(":iFirstName", (profile.getFirstName()));
        inParams.set(":iLastName", (profile.getLastName()));
        inParams.set(":iSSN", (account.getSSN()));
        inParams.set(":iEmplID", (account.getEmployeeID()));
        inParams.set(":iAddress1", (profile.getAddress1()));
        inParams.set(":iAddress2", (profile.getAddress2()));
        inParams.set(":iAddress3", (profile.getAddress3()));
        inParams.set(":iAddress4", (profile.getAddress4()));
        inParams.set(":iCity", (profile.getCity()));
        inParams.set(":iState", (profile.getStateOrProvince()));
        inParams.set(":iCountry", (profile.getCountry()));
        inParams.set(":iZip", (profile.getZip()));
        inParams.set(":iBusPhone", (profile.getBusinessPhone()));
        inParams.set(":iHomePhone", (account.getHomePhone()));
        inParams.set(":iEmail", (profile.getEMail()));
        inParams.set(":iCardFlg", (account.getCardExists()));
        inParams.setExpiryDate(":iExpiryDate", (account.getAccountExpDate()));
        inParams.set(":iCreditLimit", account.getCreditLimit());
        inParams.set(":iGrade", (account.getGrade()));
        inParams.set(":iEmplStatus", (account.getEmploymentStatus()));
        inParams.set(":oTmpAcctNbr", " ");
        inParams.set(":oResult", " ");

        IValListWrap outParams = new IValListWrap(callStoredProc(SP_CREATE_IND_ACCT, inParams.toIValList()));


        // Stored procedure was successful; fetch the new temporary account number
        // and return it
        return outParams.getSafeString(":oTmpAcctNbr");
        */
        return tmpAccountNumber;
    }

}
