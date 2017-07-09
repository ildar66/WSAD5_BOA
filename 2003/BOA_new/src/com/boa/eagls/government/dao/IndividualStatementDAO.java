package com.boa.eagls.government.dao;

import com.boa.eagls.government.dao.DAOBase;
import com.boa.eagls.government.dto.transactiondata.TransactionDataDTO;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.individualstatement.IndividualStatementDTO;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.transactiondata.SearchCriteria;
import com.boa.eagls.government.service.transactiondata.SearchTypesValues;
import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.account.AccountSummary;
import com.boa.eagls.government.dto.account.AccountStatus;
import com.boa.eagls.government.dto.account.AccountTransactionSummary;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.hierarchy.Hierarchy;

import java.sql.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;


/**
 *
 * User: StanislavS
 * Date: 18.07.2003
 * Time: 11:44:53
 * To change this template use Options | File Templates.
 */
public class IndividualStatementDAO extends DAOBase {
    private static Logger log4j = Logger.getLogger(IndividualStatementDAO.class);

    private final static String SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_BEFORE_DATE =
            "SELECT /*+ RULE */ " +
            "ah.Agency_Name, " +
            "a1.Account_Nbr, " +
            "a2.Account_Nbr," +
            " a1.Prog_Type, " +
            "a1.Last_Name, " +
            "a1.First_Name, " +
            "ast.Stmt_Date," +
            " a1.Billing_Type," +
            " a1.Account_Type, " +
            "a1.Accounting_Center_Id" +
            " FROM Agency_Hl ah, Account_Statement ast, Account a1, Account a2\n" +
            " WHERE a1.Hierarchy_Nbr = ah.Hierarchy_Nbr\n" +
            " AND a1.Account_Nbr = ast.Account_Nbr\n" +
            " AND (a1.Account_Type = 'I' OR a1.Account_Type = 'T')\n" +
            " AND a2.Account_Type = 'C'\n" +
            " AND ast.Stmt_date >= to_date('";
    private final static String SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_MIDDLE =
            "', 'MM/YYYY')\n" +
            " AND ast.Stmt_date <= last_day(to_date('";

    private final static String SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_AFTER_DATE =
            "', 'MM/YYYY'))\n" +
            " AND a1.Agency_Bill_Nbr = a2.Agency_Bill_Nbr\n" +
            " AND a1.account_open_date <= ast.stmt_date\n";

    private static final String PACKAGE_NAME_INDIVIDUAL_STATEMENT = "INDIVIDUAL_STATEMENT";

    private static final String SP_GET_INDIVIDUAL_STATEMENTS =
            PACKAGE_NAME_INDIVIDUAL_STATEMENT + ".GET_INDIVIDUAL_STATEMENTS(?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ? ,?, " +
            "?, ?)";
    private final static int iUserId = 1;
    //":iUserId, " +
    private final static int iStatementDate = 2;
    //":iStatementDate, " +
    private final static int oRetStatementDate = 3;
    //":oRetStatementDate, " +
    private final static int iSearchType = 4;
    //":iSearchType, " +
    private final static int iSortType = 5;
    //":iSortType, " +
    private final static int iAccountNumber = 6;
    //":iAccountNumber, " +
    private final static int oStatementStatus = 7;
    //":oStatementStatus, " +
    private final static int oPreviousBalanceAmt = 8;
    //":oPreviousBalanceAmt, " +
    private final static int oTravellersCheckAmt = 9;
    //":oTravellersCheckAmt, " +
    private final static int oDisputeAmt = 10;
    //":oDisputeAmt, " +
    private final static int oCashAdvanceAmt = 11;
    //":oCashAdvanceAmt, " +
    private final static int oFees = 12;
    //":oFees, " +
    private final static int oFinanceChargesAmt = 13;
    //":oFinanceChargesAmt, " +
    private final static int oIndConvChecksAmt = 14;
    //":oIndConvChecksAmt, " +
    private final static int oCenConvChecksAmt = 15;
    //":oCenConvChecksAmt, " +
    private final static int oCenPurchasesAmt = 16;
    //":oCenPurchasesAmt, " +
    private final static int oCentralCash = 17;
    //":oCentralCash, " +
    private final static int oCentralBalance = 18;
    //":oCentralBalance, " +
    private final static int oTotalPurchases = 19;
    //":oTotalPurchases, " +
    private final static int oTotalCredits = 20;
    //":oTotalCredits, " +
    private final static int oTotalDisputes = 21;
    //":oTotalDisputes, " +
    private final static int oTotalBalance = 22;
    //":oTotalBalance, " +
    private final static int oAgencyId = 23;
    //":oAgencyId, " +
    private final static int oAgencyName = 24;
    //":oAgencyName, " +
    private final static int oSinglePurchaseLimit = 25;
    //":oSinglePurchaseLimit, " +
    private final static int oCreditLimit = 26;
    //":oCreditLimit, " +
    private final static int oCentralAcctId = 27;
    //":oCentralAcctId, " +
    private final static int oProgramType = 28;
    //":oProgramType, " +
    private final static int oBillingType = 29;
    //":oBillingType, " +
    private final static int oAccountingCode = 30;
    //":oAccountingCode, " +
    private final static int oAccountingCenterId = 31;
//            ":oAccountingCenterId, " +
    private final static int oHl0 = 32;
//            ":oHl0, " +
    private final static int oHl1 = 33;
//            ":oHl1, " +
    private final static int oHl2 = 34;
//            ":oHl2, " +
    private final static int oHl3 = 35;
//            ":oHl3, " +
    private final static int oHl4 = 36;
//            ":oHl4, " +
    private final static int oHl5 = 37;
//            ":oHl5, " +
    private final static int oHl6 = 38;
//            ":oHl6, " +
    private final static int oHl7 = 39;
//            ":oHl7, " +
    private final static int oHl8 = 40;
//            ":oHl8, " +
    private final static int oHl0Desc = 41;
//            ":oHl0Desc, " +
    private final static int oHl1Desc = 42;
//            ":oHl1Desc, " +
    private final static int oHl2Desc = 43;
//            ":oHl2Desc, " +
    private final static int oHl3Desc = 44;
//            ":oHl3Desc, " +
    private final static int oHl4Desc = 45;
//            ":oHl4Desc, " +
    private final static int oHl5Desc = 46;
//            ":oHl5Desc, " +
    private final static int oHl6Desc = 47;
//            ":oHl6Desc, " +
    private final static int oHl7Desc = 48;
//            ":oHl7Desc, " +
    private final static int oHl8Desc = 49;
//            ":oHl8Desc, " +
    private final static int oFirstName = 50;
//            ":oFirstName, " +
    private final static int oLastName = 51;
//            ":oLastName, " +
    private final static int oEquipmentId = 52;
//            ":oEquipmentId, " +
    private final static int oAccountType = 53;
//            ":oAccountType, " +
    private final static int oCurrentBalanceAmt = 54;
//            ":oCurrentBalanceAmt, " +
    private final static int oPastDueAmt = 55;
//            ":oPastDueAmt, " +
    private final static int oPastDueDays = 56;
//            ":oPastDueDays, " +
    private final static int oTotalDisputeAmt = 57;
//            ":oTotalDisputeAmt, " +
    private final static int oPendingAuth = 58;
//            ":oPendingAuth, " +
    private final static int oAvailCredit = 59;
//            ":oAvailCredit, " +
    private final static int oMoneyAvailableSign = 60;
//            ":oMoneyAvailableSign, " +
    private final static int oMoneyAvailablePad = 61;
//            ":oMoneyAvailablePad, " +
    private final static int oPaymentsDueAmt = 62;
//            ":oPaymentsDueAmt, " +
    private final static int oPaymentDueDate = 63;
//            ":oPaymentDueDate, " +
    private final static int oLastPaymentDue = 64;
//            ":oLastPaymentDue, " +
    private final static int oLastPaymentAmt = 65;
//            ":oLastPaymentAmt, " +
    private final static int oAmtDebitTransCc = 66;
//            ":oAmtDebitTransCc, " +
    private final static int oAmtCreditTransCc = 67;
//            ":oAmtCreditTransCc, " +
    private final static int oTotalAmtTransCc = 68;
//            ":oTotalAmtTransCc, " +
    private final static int oAmtDebitTransPc = 69;
//            ":oAmtDebitTransPc, " +
    private final static int oAmtCreditTransPc = 70;
//            ":oAmtCreditTransPc, " +
    private final static int oTotalAmtTransPc = 71;
//            ":oTotalAmtTransPc, " +
    private final static int oResult = 72;
//            ":oResult" +
    //")";

    public Collection searchStatementByHierarchyDate(Connection con, String strDate) throws SQLException {

        ArrayList resCollection = new ArrayList();
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_BEFORE_DATE);
        sqlStmt.append(strDate);
        sqlStmt.append(SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_MIDDLE);
        sqlStmt.append(strDate);
        sqlStmt.append(SQL_SEARCH_STATEMENT_BY_HIERARCHY_DATE_AFTER_DATE);
        log4j.info("SQL:" + sqlStmt.toString());
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sqlStmt.toString());
            rs = ps.executeQuery();
            while (rs.next()) {
                TransactionDataDTO agencyDTO = new TransactionDataDTO();
                fillAgency(rs, agencyDTO);
                resCollection.add(agencyDTO);
            }
        } catch (SQLException e) {
            log4j.error("Short description here", e); //To change body of catch statement use Options | File Templates.
        } finally {
            this.closeAll(rs, ps);
        }

        //sqlStmt.replace()
        //sqlStmt.append(getHierarchyWhereClause(hierarchy, (short) depth));
        //Construct IValList and append Query
        //IValListWrap inParam = new IValListWrap();
        //inParam.set(":monthYear", DateUtil.convertExpiryToString(statementDate));
        //Run the query
        //ResultSet rs = this.callQuery(sqlStmt.toString(), inParam.toIValList(), countFlag, maxSize);
        //if (rs.getRowNumber() == 0) {
        //    GX.Release(rs);
        //   return new IndividualStatementSummary[0];
        //}
        //return this.parseSearchStatement(rs);
        return resCollection;
    }

    private void fillAgency(ResultSet rs, TransactionDataDTO agencyDTO) throws SQLException {
        int i = 1;
        agencyDTO.setAgency_Name(rs.getString(i++)); //agency name
        //log4j.info("AGENCY NAME:" + agencyDTO.getAgency_Name());
        agencyDTO.setAccount_Nbr1(rs.getString(i++)); //account number from a1
        agencyDTO.setAccount_Nbr2(rs.getString(i++)); //account number from a2
        agencyDTO.setProg_Type(rs.getString(i++)); //prog type
        agencyDTO.setLast_Name(rs.getString(i++)); //Last name
        agencyDTO.setFirst_Name(rs.getString(i++)); //First name
        agencyDTO.setStmt_Date(rs.getString(i++)); //Statement date
        agencyDTO.setBilling_Type(rs.getString(i++)); //Billing type
        agencyDTO.setAccount_Type(rs.getString(i++)); //Account type
        agencyDTO.setAccounting_Center_Id(rs.getString(i++)); //Account Center ID
    }

    public Account retrievePartialAccountSummary(Connection con, String accountNumber) throws EaglsException, SQLException {
        StringBuffer sqlstmt = new StringBuffer();

        sqlstmt.append("select /*+ RULE */ a.account_type, a.accounting_center_id, ag.agency_name, a.first_name, a.last_name\n");
        sqlstmt.append("from agency_hl ag, account a\n");
        sqlstmt.append("where a.account_nbr = " + accountNumber + "\n");
        sqlstmt.append("and ag.hierarchy_nbr = a.hierarchy_nbr\n");
        Account aSumm = null;
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            aSumm = new Account();
            st = con.prepareStatement(sqlstmt.toString());
            rs = st.executeQuery();
            while (rs.next()) {
                aSumm.setAccountType(Account.INDIVIDUAL);
                aSumm.setAccountingCenterID(rs.getString(2));
                aSumm.setAgencyName(rs.getString(3));
                aSumm.setFirstName(rs.getString(4));
                aSumm.setLastName(rs.getString(5));
                aSumm.setBillingType((short) 0);
            }
        } catch (SQLException e) {
            log4j.error("Short description here", e);
            throw new EaglsException("", e);
        } finally {
            this.closeAll(rs, st);
        }

        return aSumm;
    }

    public Collection searchStatementByAccountNumber(Connection con, String accountNumber,
                                                     String monthYear) throws SQLException, EaglsException {
        StringBuffer sqlstmt0 = new StringBuffer();

        sqlstmt0.append("SELECT /*+ RULE */ a.last_name, a.first_name, ah.agency_name, to_char(a1.account_nbr), a.prog_type\n");
        sqlstmt0.append("FROM   agency_hl ah, account a1, account a \n");
        sqlstmt0.append("WHERE a.hierarchy_nbr = ah.hierarchy_nbr\n");
        sqlstmt0.append("AND a.agency_bill_nbr = a1.agency_bill_nbr\n");
        sqlstmt0.append("AND a1.account_type = 'C'\n");
        sqlstmt0.append("AND a.account_nbr = " + accountNumber);
        log4j.debug(sqlstmt0.toString());

        PreparedStatement st = con.prepareStatement(sqlstmt0.toString());

        ResultSet rs0 = st.executeQuery();

        String firstName = rs0.getString(2);
        String lastName = rs0.getString(1);
        String agencyName = rs0.getString(3);
        String cenAcctNumber = rs0.getString(4);
        String programType = rs0.getString(5);

        this.closeAll(rs0, st);

        StringBuffer sqlstmt1 = new StringBuffer();
        sqlstmt1.append("SELECT /*+ RULE */ TO_CHAR(STMT_DATE, 'MM/DD/YYYY')\n");
        sqlstmt1.append("FROM ACCOUNT_STATEMENT\n");
        sqlstmt1.append("WHERE ACCOUNT_NBR = " + accountNumber + "\n");
        sqlstmt1.append("AND STMT_DATE BETWEEN TO_DATE('01/" + monthYear + "', 'DD/MM/YYYY') " + "\n");
        sqlstmt1.append("    AND LAST_DAY(TO_DATE('01/" + monthYear + "', 'DD/MM/YYYY'))" + "\n");
        sqlstmt1.append("    AND DAYS_IN_CYCLE > 0 \n");
        /** Added the following for the transactions sorted by statement date. BUG# LSP3147
         * The code still needs to take care of other sorting items if business rules forces to
         * retrieve different order.
         **/

        sqlstmt1.append("ORDER BY STMT_DATE");

        st = con.prepareStatement(sqlstmt1.toString());
//        System.out.println(sqlstmt1.toString());
        ResultSet rs1 = st.executeQuery();
        ArrayList res = new ArrayList();
        while (rs1.next()) {
            IndividualStatementDTO indStmt = new IndividualStatementDTO();// IndividualStatementSummary();
            indStmt.setFirstName(firstName);
            indStmt.setLastName(lastName);
            indStmt.setAgencyName(agencyName);
            indStmt.setCenAccountNumber(cenAcctNumber);
            indStmt.setProgramType(convertProgramType(programType));
            indStmt.setStatementDate(rs1.getString(1));
            res.add(indStmt);
        }
        return res;
    }

    private short convertProgramType(String str) throws EaglsException {
        if (!"".equalsIgnoreCase("")) {
            switch (str.charAt(0)) {
                case 'A':
                    return Account.INTERAGENCY;
                case 'P':
                    return Account.PURCHASE;
                case 'T':
                    return Account.TRAVEL;
                case 'I':
                    return Account.INTEGRATE;
                case 'F':
                    return Account.FLEET;
            }
        }
        throw new EaglsException("ERROR_DB_TYPE_MISMATCH", null);
    }

    public Object getMultipleStatements(Connection con, SearchCriteria c) throws EaglsException, SQLException {

        //valIn = al.valIn;
        //valOut = al.valOut;
        //das = myDas;
        String month = c.getSearchByDate();//valIn.getValString("hdn_searchByDate");
        //TemplateMapBasic map = al.getEAGLSTemplateMap("Individual Statement", "Statements for " + month);
        String accountNumber = c.getAcctNumber(); //valIn.getValString("accountNumber");
        Collection statements = searchStatementByAccountNumber(con, accountNumber, month);
        //TemplateDataBasic listing = new TemplateDataBasic("searchResults");
        Iterator it = statements.iterator();
        while (it.hasNext()) {
            IndividualStatementDTO dto = (IndividualStatementDTO) it.next();
            //iv.setValString("hdn_sortFor", valIn.getValString("hdn_sortFor"));
            //iv.setValString("hdn_searchFor", valIn.getValString("hdn_searchFor"));
            //iv.setValString("accountNumber", valIn.getValString("accountNumber"));
            //iv.setValString("hdn_searchByDate", DateUtil.convertDateToString(statements[i].getStatementDate()));
            //iv.setValString("haventChosenStatement", "false");
            //String hlink = createHyperlink("{D6556170-3CF4-11D2-9C2A-204C4F4F5020}", iv);
            //String progType = "";
            String progType = getStrProgType(dto.getProgramType());
            /*listing.rowAppend("statementDate=" + DateUtil.convertDateToString(statements[i].getStatementDate()) + ";" +
                    "accountHolderName=" + statements[i].getFirstName() + " " + statements[i].getLastName() + ";" +
                    "agencyName=" + statements[i].getAgencyName() + ";" +
                    "centralAccountNumber=" + statements[i].getCenAccountNumber() + ";" +
                    "programType=" + progType + ";" +
                    "individualAccountNumber=" + valIn.getValString("accountNumber") + ";" +
                    "hyperlink=" + hlink + ";");*/
        }
        //int startRecord = 0;
        //int totalRecords = statements.length;
        //int NUMBER_OF_TRANSACTIONS_PER_PAGE = 20;
        //map.putString("showPreviousButton", (startRecord > 0) ? null : "");
        //map.putString("showPageNumbers", totalRecords > NUMBER_OF_TRANSACTIONS_PER_PAGE ? null : "");
        //map.putString("currentPageNumber", String.valueOf(currentPage(startRecord, NUMBER_OF_TRANSACTIONS_PER_PAGE)));
        //map.putString("totalPageNumber", String.valueOf(numberOfPages(totalRecords, NUMBER_OF_TRANSACTIONS_PER_PAGE)));
        //map.putString("showNextButton", (totalRecords > (startRecord + NUMBER_OF_TRANSACTIONS_PER_PAGE)) ? null : "");
        //map.putString("showReviseButton", null);
        //map.putString("reviseHyperlink", createHyperlink("{FF253760-CA08-11D3-A7E4-204C4F4F5020}?searchType=individualStatement", (IValList) null));


        //return al.evalTemplate("gsa/transactions/statement/results_transIndStatementMultipleResults.html", listing, map);

        //valIn.setValString("hdn_sortFor", valIn.getValString("hdn_sortFor"));
        //valIn.setValString("hdn_searchFor", valIn.getValString("hdn_searchFor"));
        //valIn.setValString("accountNumber", valIn.getValString("accountNumber"));
        //valIn.setValString("hdn_searchByDate", DateUtil.convertDateToString(statements[0].getStatementDate()));
        //valIn.setValString("haventChosenStatement", "false");
        //IndStmtAL var = new IndStmtAL();
        //return var.execute(al, das);
        //} else {
        // todo show error if no transaction
        //    return al.evalTemplate("gsa/common/results_noResultsFound.html", (com.kivasoft.ITemplateData) null, map);
        //}
        return statements;

    }

    private String getStrProgType(short value) {
        String progType = "";
        switch (value) {
            case Account.PURCHASE:
                progType = "Purchase";
                break;
            case Account.TRAVEL:
                progType = "Travel";
                break;
            case Account.FLEET:
                progType = "Fleet";
                break;
            case Account.INTEGRATE:
                progType = "Integrated";
                break;
            case Account.INTERAGENCY:
                progType = "Interagency";
                break;
        }
        return progType;
    }

    private short convertAccountOrBillingType(String str) throws EaglsException {
        if (!"".equals(str)) {
            switch (str.charAt(0)) {
                case 'C':
                    return AccountSummary.CENTRAL;
                case 'I':
                    return AccountSummary.INDIVIDUAL;
                case 'M':
                    return AccountSummary.MASTERCARD;
                case 'D':
                    return AccountSummary.DIVERSION_ACCT;
                case 'T':
                    return AccountSummary.MASTERCARD;
            }
        }
        throw new EaglsException("ERROR_DB_TYPE_MISMATCH", null);
    }


    private IndividualStatementDTO retrieveStatement(Connection con, String accountNumber, String dt, short searchType, short sortType, String userID)
            throws EaglsException, SQLException, EaglsDAOError {

/*
        CallableStatement st = con.prepareCall(this.SP_GET_INDIVIDUAL_STATEMENTS);
        //int i = 1;
        //IValListWrap inParams = new IValListWrap();
        // Initialize the Stored Procedure Parameters
//        inParams.set(":iUserId", transport.getUserID());
        st.setString(iUserId, userID);
//        inParams.set(":iStatementDate", DateUtil.convertDateToString(dt));
        st.setString(iStatementDate, dt);
//        inParams.set(":oRetStatementDate", (String) "");
        st.registerOutParameter(oRetStatementDate, Types.VARCHAR);
//        inParams.set(":iSearchType", searchType);
        st.setString(iSearchType, String.valueOf(searchType));
//        inParams.set(":iSortType", sortType);
        st.setString(iSortType, String.valueOf(sortType));
//        inParams.set(":iAccountNumber", (String) accountNumber);
        st.setString(iAccountNumber, accountNumber);
//        inParams.set(":oStatementStatus", (String) "");
        st.registerOutParameter(oStatementStatus, Types.VARCHAR);
//        inParams.set(":oPreviousBalanceAmt", (double) 0.0);
        st.registerOutParameter(oPreviousBalanceAmt, Types.DOUBLE);
//        inParams.set(":oTravellersCheckAmt", (double) 0.0);
        st.registerOutParameter(oTravellersCheckAmt, Types.DOUBLE);
//        inParams.set(":oDisputeAmt", (double) 0.0);
        st.registerOutParameter(oDisputeAmt, Types.DOUBLE);
//        inParams.set(":oCashAdvanceAmt", (double) 0.0);
        st.registerOutParameter(oCashAdvanceAmt, Types.DOUBLE);
//        inParams.set(":oFees", (double) 0.0);
        st.registerOutParameter(oFees, Types.DOUBLE);
//        inParams.set(":oFinanceChargesAmt", (double) 0.0);
        st.registerOutParameter(oFinanceChargesAmt, Types.DOUBLE);
//        inParams.set(":oIndConvChecksAmt", (double) 0.0);
        st.registerOutParameter(oIndConvChecksAmt, Types.DOUBLE);
//        inParams.set(":oCenConvChecksAmt", (double) 0.0);
        st.registerOutParameter(oCenConvChecksAmt, Types.DOUBLE);
//        inParams.set(":oCenPurchasesAmt", (double) 0.0);
        st.registerOutParameter(oCenPurchasesAmt, Types.DOUBLE);
//        inParams.set(":oCentralCash", (double) 0.0);
        st.registerOutParameter(oCentralCash, Types.DOUBLE);
//        inParams.set(":oCentralBalance", (double) 0.0);
        st.registerOutParameter(oCentralBalance, Types.DOUBLE);
//        inParams.set(":oTotalPurchases", (double) 0.0);
        st.registerOutParameter(oTotalPurchases, Types.DOUBLE);
//        inParams.set(":oTotalCredits", (double) 0.0);
        st.registerOutParameter(oTotalCredits, Types.DOUBLE);
//        inParams.set(":oTotalDisputes", (double) 0.0);
        st.registerOutParameter(oTotalDisputes, Types.DOUBLE);
//        inParams.set(":oTotalBalance", (double) 0.0);
        st.registerOutParameter(oTotalBalance, Types.DOUBLE);
//        inParams.set(":oAgencyId", (int) 0);
        st.registerOutParameter(oAgencyId, Types.INTEGER);
//        inParams.set(":oAgencyName", (String) "");
        st.registerOutParameter(oAgencyName, Types.VARCHAR);
//        inParams.set(":oSinglePurchaseLimit", (double) 0.0);
        st.registerOutParameter(oSinglePurchaseLimit, Types.DOUBLE);
//        inParams.set(":oCreditLimit", (double) 0.0);
        st.registerOutParameter(oCreditLimit, Types.DOUBLE);
//        inParams.set(":oCentralAcctId", (String) "");
        st.registerOutParameter(oCentralAcctId, Types.VARCHAR);
//        inParams.set(":oProgramType", (String) "");
        st.registerOutParameter(oProgramType, Types.VARCHAR);
//        inParams.set(":oBillingType", (String) "");
        st.registerOutParameter(oBillingType, Types.VARCHAR);
//        inParams.set(":oAccountingCode", (String) "");
        st.registerOutParameter(oAccountingCode, Types.VARCHAR);
//        inParams.set(":oAccountingCenterId", (String) "");
        st.registerOutParameter(oAccountingCenterId, Types.VARCHAR);
//        inParams.set(":oHl0", (int) 0);
        st.registerOutParameter(oHl0, Types.INTEGER);
//        inParams.set(":oHl1", (int) 0);
        st.registerOutParameter(oHl1, Types.INTEGER);
//        inParams.set(":oHl2", (int) 0);
        st.registerOutParameter(oHl2, Types.INTEGER);
//        inParams.set(":oHl3", (int) 0);
        st.registerOutParameter(oHl3, Types.INTEGER);
//        inParams.set(":oHl4", (int) 0);
        st.registerOutParameter(oHl4, Types.INTEGER);
//        inParams.set(":oHl5", (int) 0);
        st.registerOutParameter(oHl5, Types.INTEGER);
//        inParams.set(":oHl6", (int) 0);
        st.registerOutParameter(oHl6, Types.INTEGER);
//        inParams.set(":oHl7", (int) 0);
        st.registerOutParameter(oHl7, Types.INTEGER);
//        inParams.set(":oHl8", (int) 0);
        st.registerOutParameter(oHl8, Types.INTEGER);
//        inParams.set(":oHl0Desc", "");
        st.registerOutParameter(oHl0Desc, Types.VARCHAR);
//        inParams.set(":oHl1Desc", "");
        st.registerOutParameter(oHl1Desc, Types.VARCHAR);
//        inParams.set(":oHl2Desc", "");
        st.registerOutParameter(oHl2Desc, Types.VARCHAR);
//        inParams.set(":oHl3Desc", "");
        st.registerOutParameter(oHl3Desc, Types.VARCHAR);
//        inParams.set(":oHl4Desc", "");
        st.registerOutParameter(oHl4Desc, Types.VARCHAR);
//        inParams.set(":oHl5Desc", "");
        st.registerOutParameter(oHl5Desc, Types.VARCHAR);
//        inParams.set(":oHl6Desc", "");
        st.registerOutParameter(oHl6Desc, Types.VARCHAR);
//        inParams.set(":oHl7Desc", "");
        st.registerOutParameter(oHl7Desc, Types.VARCHAR);
//        inParams.set(":oHl8Desc", "");
        st.registerOutParameter(oHl8Desc, Types.VARCHAR);
//        inParams.set(":oFirstName", "");
        st.registerOutParameter(oFirstName, Types.VARCHAR);
//        inParams.set(":oLastName", "");
        st.registerOutParameter(oLastName, Types.VARCHAR);
//        inParams.set(":oEquipmentId", "");
        st.registerOutParameter(oEquipmentId, Types.VARCHAR);
//        inParams.set(":oAccountType", "");
        st.registerOutParameter(oAccountType, Types.VARCHAR);
//        inParams.set(":oCurrentBalanceAmt", (double) 0.0);
        st.registerOutParameter(oCurrentBalanceAmt, Types.DOUBLE);
//        inParams.set(":oPastDueAmt", (double) 0.0);
        st.registerOutParameter(oPastDueAmt, Types.DOUBLE);
//        inParams.set(":oPastDueDays", (int) 0);
        st.registerOutParameter(oPastDueDays, Types.INTEGER);
//        inParams.set(":oTotalDisputeAmt", (double) 0.0);
        st.registerOutParameter(oTotalDisputeAmt, Types.DOUBLE);
//        inParams.set(":oPendingAuth", (int) 0);
        st.registerOutParameter(oPendingAuth, Types.INTEGER);
//        inParams.set(":oAvailCredit", (double) 0.0);
        st.registerOutParameter(oAvailCredit, Types.DOUBLE);
//        inParams.set(":oMoneyAvailableSign", (String) "");
        st.registerOutParameter(oMoneyAvailableSign, Types.VARCHAR);
//        inParams.set(":oMoneyAvailablePad", (int) 0);
        st.registerOutParameter(oMoneyAvailablePad, Types.INTEGER);
//        inParams.set(":oPaymentsDueAmt", (double) 0.0);
        st.registerOutParameter(oPaymentsDueAmt, Types.DOUBLE);
//        inParams.set(":oPaymentDueDate", "");
        st.registerOutParameter(oPaymentDueDate, Types.VARCHAR);
//        inParams.set(":oLastPaymentDue", (double) 0.0);
        st.registerOutParameter(oLastPaymentDue, Types.DOUBLE);
//        inParams.set(":oLastPaymentAmt", (double) 0.0);
        st.registerOutParameter(oLastPaymentAmt, Types.DOUBLE);
//        inParams.set(":oAmtDebitTransCc", (double) 0.0);
        st.registerOutParameter(oAmtDebitTransCc, Types.DOUBLE);
//        inParams.set(":oAmtCreditTransCc", (double) 0.0);
        st.registerOutParameter(oAmtCreditTransCc, Types.DOUBLE);
//        inParams.set(":oTotalAmtTransCc", (double) 0.0);
        st.registerOutParameter(oTotalAmtTransCc, Types.DOUBLE);
//        inParams.set(":oAmtDebitTransPc", (double) 0.0);
        st.registerOutParameter(oAmtDebitTransPc, Types.DOUBLE);
        //inParams.set(":oAmtCreditTransPc", (double) 0.0);
        st.registerOutParameter(oAmtCreditTransPc, Types.DOUBLE);
        //inParams.set(":oTotalAmtTransPc", (double) 0.0);
        st.registerOutParameter(oTotalAmtTransPc, Types.DOUBLE);
        //inParams.set(":oResult", (String) " ");
        st.registerOutParameter(oResult, Types.VARCHAR);
        st.execute();

        //IValList outParams = this.callStoredProc(SP_GET_INDIVIDUAL_STATEMENTS, inParams.toIValList());

        //IValListWrap output = new IValListWrap(outParams);

        Double tmpDblVal; // temporary value to avoid reassigning
        Integer tmpIntVal; // temporary calue to avoid reassignment
        String tmpStrVal; // temporary String Value;

        // instantiate the objects
        IndividualStatementDTO ids = new IndividualStatementDTO();
        AccountSummary asum = new AccountSummary();
        AccountTransactionSummary ats = new AccountTransactionSummary();

        //if ((tmpIntVal = st.getInt(oAgencyId)) != null)
        asum.setAgencyID(st.getString(oAgencyId));
        //asum.setAgencyID(output.getString(":oAgencyId"));
        asum.setAgencyName(st.getString(oAgencyName));
        asum.setAccountNumber(accountNumber);
        String accountingCenterID = st.getString(oAccountingCenterId);
        //AccountingCenter ac = AccountingCenter.retrieveReadOnly(acID, transport);

        AccountingCenterDAO adapter = new AccountingCenterDAO();
        AccountingCenter acct = adapter.retrieve(con, accountingCenterID, userID, false, false);// validate = false, forSetup = false
        acct.setReadOnly(true);

//        String tmp;
//        if ((tmp = output.getString(":oAccountingCode")) != null) {
        asum.setAccountingCode(st.getString(oAccountingCode));//, false, false, AccountingCenter.ACCOUNTINGCODE_NO_VALIDATE)).format(true)));
//        }

        asum.setProgramType(convertProgramType(st.getString(oProgramType)));
        //if ((tmpDblVal = output.getDouble(":oCreditLimit")) != null)
        asum.setCreditLimit(st.getDouble(oCreditLimit));
        asum.setBillingType(convertAccountOrBillingType(st.getString(oBillingType)));
        asum.setAccountingCenterID(st.getString(oAccountingCenterId));
        // Create the hierarchy Object
        Hierarchy[] hierarchy = new Hierarchy[9];

        short i = 0;
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl0), st.getString(oHl0Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl1), st.getString(oHl1Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl2), st.getString(oHl2Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl3), st.getString(oHl3Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl4), st.getString(oHl4Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl5), st.getString(oHl5Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl6), st.getString(oHl6Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl7), st.getString(oHl7Desc));
        hierarchy[i] = createHierarchy(st, i++, st.getInt(oHl8), st.getString(oHl8Desc));

////        for (short i = 0; i < 9; i++) {
//            hierarchy[i] = new Hierarchy();
//            hierarchy[i].setPosition(i);
////	    if ((tmpIntVal = output.getInteger(":oHl" + String.valueOf(i))) != null)
////	    {
////	        hierarchy[i].setValue(tmpIntVal.intValue());
//            hierarchy[i].setValue(output.toIValList().getValInt(":oHl" + String.valueOf(i)));
//            hierarchy[i].setDescription(output.getString(":oHl" + String.valueOf(i) + "Desc"));
////        }
//        }
        asum.setHierarchy(hierarchy);
        asum.setFirstName(st.getString(oFirstName));
        asum.setLastName(st.getString(oLastName));
        asum.setEquipmentID(st.getString(oEquipmentId));
        asum.setAccountType(convertAccountOrBillingType(st.getString(oAccountType)));
        asum.setCentralAcctID(st.getString(oCentralAcctId));
//        if ((tmpDblVal = output.getDouble(":oSinglePurchaseLimit")) != null) {
            asum.setSinglePurchaseLimit(st.getDouble(oSinglePurchaseLimit));
//        }
        ids.setAccountSummary(asum);

        if ((tmpDblVal = output.getDouble(":oCurrentBalanceAmt")) != null)
            ats.setCurrentBalance(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oPaymentsDueAmt")) != null)
            ats.setPaymentDue(tmpDblVal.doubleValue());
        if ((tmpIntVal = output.getInteger(":oPastDueDays")) != null)
            ats.setPastDueDays(tmpIntVal.shortValue());
        if ((tmpDblVal = output.getDouble(":oPastDueAmt")) != null)
            ats.setPastDueAmt(tmpDblVal.floatValue());
        if ((tmpDblVal = output.getDouble(":oTotalDisputeAmt")) != null)
            ats.setTotalDisputesAmt(tmpDblVal.doubleValue());
        if ((tmpIntVal = output.getInteger(":oPendingAuth")) != null)
            ats.setPendingAuths(tmpIntVal.longValue());
        if ((tmpDblVal = output.getDouble(":oAvailCredit")) != null)
            ats.setAvailCredit(tmpDblVal.longValue());
        tmpStrVal = output.getString(":oMoneyAvailableSign");
        if (!tmpStrVal.equals("")) {
            ats.setMoneyAvailSign(tmpStrVal.charAt(0));
        }
        if ((tmpIntVal = output.getInteger(":oMoneyAvailablePad")) != null)
            ats.setMoneyAvailPad(tmpIntVal.longValue());
        ats.setLastPaymentDate(output.getDate(":oLastPaymentDate"));
        ats.setPaymentDueDate(output.getDate(":oPaymentDueDate"));
        if ((tmpDblVal = output.getDouble(":oLastPaymentAmt")) != null)
            ats.setLastPaymentAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oAmtDebitTransCc")) != null)
            ats.setAmtDebitTransCC(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oAmtCreditTransCc")) != null)
            ats.setAmtCreditTransCC(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalAmtTransCc")) != null)
            ats.setTotalAmtTransCC(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oAmtDebitTransPc")) != null)
            ats.setAmtDebitTransPC(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oAmtCreditTransPc")) != null)
            ats.setAmtCreditTransPC(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalAmtTransPc")) != null)
            ats.setTotalAmtTransPC(tmpDblVal.doubleValue());

        ids.setAcctTransSummary(ats);

        ids.setStatementDate(output.getDate(":oRetStatementDate"));
        if (((tmpStrVal = output.getString(":oStatementStatus")) != null) && (!tmpStrVal.equals(""))) {
            if (tmpStrVal.charAt(0) == 'C') {
                ids.setStatementStatus(IndividualStatement.CERTIFIED);
            } else if (tmpStrVal.charAt(0) == 'I') {
                ids.setStatementStatus(IndividualStatement.INVOICED);
            }
        } else {
            ids.setStatementStatus(IndividualStatement.INVOICED);
        }
        if ((tmpIntVal = output.getInteger(":oStatementStatus")) != null)
            ids.setStatementStatus(tmpIntVal.shortValue());
        if ((tmpDblVal = output.getDouble(":oPreviousBalanceAmt")) != null)
            ids.setPreviousBalance(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTravellersCheckAmt")) != null)
            ids.setTravelersChecksAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oDisputeAmt")) != null)
            ids.setCenDisputeAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oCashAdvanceAmt")) != null)
            ids.setCashAdvanceAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oFees")) != null)
            ids.setFees(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oFinanceChargesAmt")) != null)
            ids.setFinanceCharges(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oIndConvChecksAmt")) != null)
            ids.setIndConvenienceChecksAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oCenConvChecksAmt")) != null)
            ids.setCenConvenienceChecksAmt(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oCenPurchasesAmt")) != null)
            ids.setCenPurchases(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oCentralCash")) != null)
            ids.setCenCash(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oCentralBalance")) != null)
            ids.setCenBalance(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalPurchases")) != null)
            ids.setTotalPurchases(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalCredits")) != null)
            ids.setTotalCredits(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalDisputes")) != null)
            ids.setTotalDisputes(tmpDblVal.doubleValue());
        if ((tmpDblVal = output.getDouble(":oTotalBalance")) != null)
            ids.setTotalBalance(tmpDblVal.doubleValue());

        String vin = "";
        StringBuffer sqlStmt = new StringBuffer("SELECT VOYAGER_ACCT_NBR FROM VOYAGER_ACCOUNT WHERE ACCOUNT_NBR = ");
        sqlStmt.append(accountNumber);
        try {
            ResultSet rs = this.callQuery(sqlStmt.toString());
            while (rs.next()) {
                vin = rs.getString(1);

            }
            ;
            GX.Release(rs);
            if (vin == null) {
                vin = "";
            }
        } catch (NBException e) {
            vin = "";
        }
        ids.setVisaAccountNumber(vin);
        return ids;
*/
        return null;
    }

    private Hierarchy createHierarchy(CallableStatement st, short i, int value, String descr) {
        Hierarchy hierarchy = new Hierarchy();
        hierarchy = new Hierarchy();
        hierarchy.setPosition(i);
        hierarchy.setValue(value);
        hierarchy.setDescription(descr);
        return hierarchy;
    }


/*
    public Object retriveIndStatementScreen(Connection con, SearchCriteria c) throws SQLException, EaglsException {
        //valIn = al.valIn;
        //valOut = al.valOut;
        //das = myDas;
        Collection res = null;
        try {
            //TemplateMapBasic map = al.getEAGLSTemplateMap("Individual Statement", "");
            int sortCriteria = c.getSortTransBy();
            //short sortCriteria = Short.parseShort(valIn.getValString("hdn_sortFor"));
            int searchFor = c.getSearchFor();
            //short searchFor = Short.parseShort(valIn.getValString("hdn_searchFor"));
            String acctName = "";
            String acctNumber = c.getAcctNumber();
            //String acctNumber = valIn.getValString("accountNumber");
            String searchByDate = c.getSearchByDate();
            //String searchByDate = valIn.getValString("hdn_searchByDate");
            //String searchFromDate = valIn.getValString("hdn_searchFromDate");
            //String searchToDate = valIn.getValString("hdn_searchToDate");

            boolean haventChosenStatement = true;
            //if (valIn.getValString("haventChosenStatement") != null)
            //    haventChosenStatement = false;

            IndividualStatement smt = null;

            TransactionTotals transactionTotals = null;

// Mekbib - search for trasnactions with in a range of dates
            if (searchFor == SearchTypesValues.MERCHANT /*3*) {
                /* NOT USED NOW on web page only 0, 1, 2 values presents*/
                //al.valIn.setValString("headerName", "Individual Statement");
                //IndStmtTransListAL var = new IndStmtTransListAL();
                //return var.execute(al, das);
/*
                    else {

                        System.out.println("IndStmtAl.java -> searchFor=3, haventChosenstatment=true\n\n\n\n\n\n\n\n\n");
                        smt = TransactionController.searchStatementByAccountNumber(
                                            acctNumber,
                                            DisplayDateUtil.convertStringToDate(searchFromDate),
                                            sortCriteria,
                                            al.getTransport());

                        transactionTotals = new TransactionTotals(
                                            TransactionController.retrieveTransactionTotals(acctNumber,
                                                                                            DisplayDateUtil.convertStringToDate(searchFromDate),
                                                                                            al.getTransport()));
                    }

            } else if (searchFor != SearchTypesValues.TRANSACTION_STATUS) {  //2
                res = searchStatementByAccountNumber(con, acctNumber, searchByDate);
                //todo transaction totals
                //transactionTotals = new TransactionTotals(TransactionController.retrieveTransactionTotals(acctNumber, al.getTransport()));

            } else { // search by statement date
                if (haventChosenStatement) {
                    res = (Collection) getMultipleStatements(con, c);
                    //return var.execute(al, das);
                } else {
                    smt = TransactionController.searchStatementByAccountNumber(
                            acctNumber,
                            DisplayDateUtil.convertStringToDate(searchByDate),
                            sortCriteria,
                            al.getTransport());

                    transactionTotals = new TransactionTotals(TransactionController.retrieveTransactionTotals(acctNumber, DisplayDateUtil.convertStringToDate(searchByDate), al.getTransport()));
                }
            }

            AccountSummary accSumm = smt.getAccountSummary();
            short bType = accSumm.getBillingType();
            if ((bType == AccountSummary.INDIVIDUAL) && (smt.getCenPurchases() == 0))
                map.putString("centralBillingInfo", "");
            if (bType == AccountSummary.CENTRAL)
                map.putString("centralBillingInfo", null);
            AcctTransSummary accTransSumm = smt.getAcctTransSummary();
            /** PUT THE DATA ON THE PAGE **/
//            Hierarchy[] hr = accSumm.getHierarchy();
//            String hrString = "";
//            int startingHL;
//
//            if (al.getCurrentBaseRole().equals("GCSU")) {
//                startingHL = 0;
//            } else {
//                startingHL = 1;
//            }
//            for (int i = startingHL; i < hr.length; i++) {
//                if (hr[i].getValue() != 0) {
//                    hrString += Integer.toString(hr[i].getValue());
//                    hrString += " ";
//                }
//            }
//
//            NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
//
//            acctName = accSumm.getFirstName() + " " + accSumm.getLastName();
//            String equipmentID = accSumm.getEquipmentID();
//            String visaAcctNum = smt.getVisaAccountNumber();
//            map.putString("accountData.accountHolderName", acctName);
//            map.putString("accountData.equipmentId", equipmentID);
//            map.putString("accountData.equipmentId", equipmentID);
//            map.putString("accountData.visaAccountNumber", visaAcctNum);
//            map.putString("accountData.individualAccountNumber", acctNumber);
//            map.putString("accountData.agencyName", accSumm.getAgencyName());
//            map.putString("accountData.agencyId", accSumm.getAgencyID());
//            map.putString("accountData.agencyHierarchy", hrString);
//            map.putString("accountData.singlePurchaseLimit", formatCurrencyString(accSumm.getSinglePurchaseLimit()));
//            map.putString("accountData.creditLimit", formatCurrencyString(accSumm.getCreditLimit()));
//            map.putString("accountData.availableCredit", formatCurrencyString(accTransSumm.getAvailCredit()));
//            map.putString("accountData.statementDate", DisplayDateUtil.convertDateToString(smt.getStatementDate()));
//            switch (smt.getStatementStatus()) {
//                case IndividualStatement.CERTIFIED:
//                    map.putString("accountData.statementStatus", "Certified");
//                    break;
//                case IndividualStatement.INVOICED:
//                    map.putString("accountData.statementStatus", "Invoiced");
//                    break;
//            }
//            map.putString("individualData.pastDueAmount", formatCurrencyString(accTransSumm.getPastDueAmt()));
//            map.putString("individualData.previousBalance", formatCurrencyString(smt.getPreviousBalance()));
//            map.putString("individualData.pastDueNumberDays", String.valueOf(accTransSumm.getPastDueDays()));
//// uncommented the next line for undo of invoice seg.
//// This file has logic for invoice segregation but it is not displayed on the old (EAGLS 2.0) HTML file.
//            map.putString("individualData.payments", formatCurrencyString(accTransSumm.getLastPaymentAmt()));
//
////              System.out.println("DATE: "+DisplayDateUtil.convertDateToString(accTransSumm.getPaymentDueDate()));
////              System.out.println(formatCurrencyString(accTransSumm.getLastPaymentAmt()));
//            map.putString("individualData.paymentDueDate", DisplayDateUtil.convertDateToString(accTransSumm.getPaymentDueDate()));
//            map.putString("individualData.credits", formatCurrencyString(accTransSumm.getAmtCreditTransCC()));
//// uncommented the next line for undo of invoice seg.
//            map.putString("individualData.travelersChecksAmount", formatCurrencyString(smt.getTravelersChecksAmt()));
//            map.putString("individualData.fees", formatCurrencyString(smt.getFees()));
//            map.putString("individualData.totalDisputeAmount", formatCurrencyString(accTransSumm.getTotalDisputesAmt()));
//            map.putString("individualData.financeCharges", formatCurrencyString(smt.getFinanceCharges()));
//// uncommented the next line for undo of invoice seg.
//            map.putString("individualData.cashAdvanceAmount", formatCurrencyString(smt.getCashAdvanceAmt()));
//// uncommented the next line for undo of invoice seg.
//            map.putString("individualData.convenienceChecksAmt", formatCurrencyString(smt.getIndConvenienceChecksAmt()));
//            map.putString("individualData.currentPaymentDue", formatCurrencyString(accTransSumm.getPaymentDue()));
//            map.putString("individualData.purchases", formatCurrencyString(accTransSumm.getAmtDebitTransCC()));
//
//            map.putString("individualData.invSegPayments", formatCurrencyString(transactionTotals.getPaymentsAmount()));
//            map.putString("individualData.paymentsTotal", String.valueOf(numberFormat.format(transactionTotals.getPaymentsCount())));
//
//            map.putString("individualData.convenienceChecks", formatCurrencyString(transactionTotals.getConvenienceChecksAmount()));
//            map.putString("individualData.convenienceChecksTotal", String.valueOf(numberFormat.format(transactionTotals.getConvenienceChecksCount())));
//
//            map.putString("individualData.travelersChecks", formatCurrencyString(transactionTotals.getTravelersChecksAmount()));
//            map.putString("individualData.travelersChecksTotal", String.valueOf(numberFormat.format(transactionTotals.getTravelersChecksCount())));
//
//            map.putString("individualData.cashAdvances", formatCurrencyString(transactionTotals.getCashAdvancesAmount()));
//            map.putString("individualData.cashAdvancesTotal", String.valueOf(numberFormat.format(transactionTotals.getCashAdvancesCount())));
//
//            map.putString("individualData.purchase", formatCurrencyString(transactionTotals.getPurchaseAmount()));
//            map.putString("individualData.purchaseTotal", String.valueOf(numberFormat.format(transactionTotals.getPurchaseCount())));
//
//            map.putString("individualData.travel", formatCurrencyString(transactionTotals.getTravelAmount()));
//            map.putString("individualData.travelTotal", String.valueOf(numberFormat.format(transactionTotals.getTravelCount())));
//
//            map.putString("individualData.additionalTrans", formatCurrencyString(transactionTotals.getAdditionalAmount()));
//            map.putString("individualData.additionalTransTotal", String.valueOf(numberFormat.format(transactionTotals.getAdditionalCount())));
//
//            map.putString("individualData.fleet", formatCurrencyString(transactionTotals.getFleetAmount()));
//            map.putString("individualData.fleetTotal", String.valueOf(numberFormat.format(transactionTotals.getFleetCount())));
//
//            map.putString("centralData.purchases", formatCurrencyString(smt.getCenPurchases()));
//            map.putString("centralData.totalDisputeAmount", formatCurrencyString(smt.getTotalDisputes()));
//            map.putString("centralData.cash", formatCurrencyString(smt.getCenCash()));
//            map.putString("centralData.totalCentralBalance", formatCurrencyString(smt.getCenBalance()));
//            map.putString("centralData.convenienceCheckAmount", formatCurrencyString(smt.getCenConvenienceChecksAmt()));
//            map.putString("totalData.totalStatementPurchases", formatCurrencyString(smt.getTotalPurchases()));
//            map.putString("totalData.totalCredits", formatCurrencyString(smt.getTotalCredits()));
//            map.putString("totalData.totalDisputes", formatCurrencyString(smt.getTotalDisputes()));
//            map.putString("totalData.totalStatementBalance", formatCurrencyString(smt.getTotalBalance()));
//            switch (accSumm.getAccountType()) {
//                case Account.CENTRAL:
//                case Account.INDIVIDUAL:
//                    map.putString("nonFleetAccount", null);
//                    map.putString("fleetAccount", "");
//                    map.putString("visaAccount", "");
//                    break;
//                case Account.MASTERCARD_VEHICLE:
//                    map.putString("nonFleetAccount", "");
//                    map.putString("fleetAccount", "");
//                    map.putString("visaAccount", null);
//                    break;
//                default:
//                    map.putString("nonFleetAccount", "");
//                    map.putString("fleetAccount", "");
//                    map.putString("visaAccount", "");
//                    break;
//            }
//
//            IValList iValList = GX.CreateValList();
//            iValList.setValString("accountNumber", acctNumber);
//            iValList.setValString("hdn_acctName", acctName);
//            iValList.setValString("agencyName", accSumm.getAgencyName());
//            iValList.setValString("hdn_sortFor", valIn.getValString("hdn_sortFor"));
//            iValList.setValString("hdn_searchFor", valIn.getValString("hdn_searchFor"));
//            iValList.setValString("bType", String.valueOf(bType));
//            iValList.setValString("acctType", String.valueOf(accSumm.getAccountType()));
//            iValList.setValString("headerName", "Individual Statement");
//            iValList.setValString("acctgCenID", accSumm.getAccountingCenterID());
//            iValList.setValString("hdn_searchByDate", searchByDate);
//            iValList.setValString("haventChosenStatement", "false");
//            String homeGUIDa = createHyperlink("{D6556170-3CF4-11D2-9C2A-204C4F4F5020}", iValList);
//            iValList.setValString("segregationType", String.valueOf(TransactionController.ALL));
//            String homeGUIDlist = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            iValList.setValString("homeGUID", homeGUIDlist);      // use the transaction List homeGUID
//
//            map.putString("hyperlinkData.transactionLink", createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList));
//            iValList.setValString("homeGUID", homeGUIDa);   //switch to a homeGUID that will return to the Statement.
//            map.putString("hyperlinkData.statementInsertsLink", createHyperlink("{7372B640-6795-11D2-9C79-204C4F4F5020}", null));
//            map.putString("navigate.accNameTransmit", accSumm.getFirstName() + " " + accSumm.getLastName());
//
//// Hyperlinks for segregations....
//
//// hyperlink for transaction segregated by purchase program
//            String hyperlink = new String("");
//            iValList.setValString("segregationType", String.valueOf(TransactionController.PURCHASE));
//            iValList.setValString("headerName", "Purchase");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.purchaseHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by travel program
//            iValList.setValString("segregationType", String.valueOf(TransactionController.TRAVEL));
//            iValList.setValString("headerName", "Travel");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.travelHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by fleet program
//            iValList.setValString("segregationType", String.valueOf(TransactionController.FLEET));
//            iValList.setValString("headerName", "Fleet");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.fleetHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by payments
//            iValList.setValString("segregationType", String.valueOf(TransactionController.PAYMENTS));
//            iValList.setValString("headerName", "Payments");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.paymentsHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by conv. checks
//            iValList.setValString("segregationType", String.valueOf(TransactionController.CONVENIENCE_CHECKS));
//            iValList.setValString("headerName", "Convenience Checks");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.convenienceChecksHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by traveler's checks
//            iValList.setValString("segregationType", String.valueOf(TransactionController.TRAVELERS_CHECKS));
//            iValList.setValString("headerName", "Traveler\'s Checks");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.travelersChecksHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by cash advances
//            iValList.setValString("segregationType", String.valueOf(TransactionController.CASH_ADVANCES));
//            iValList.setValString("headerName", "Cash Advances");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.cashAdvancesHyperlink", hyperlink);
//
//// hyperlink for transaction segregated by additional trans
//            iValList.setValString("segregationType", String.valueOf(TransactionController.ADDITIONAL));
//            iValList.setValString("headerName", "Additional");
//            hyperlink = createHyperlink("{A3B7BE20-3D1B-11D2-9C2C-204C4F4F5020}", iValList);
//            map.putString("individualData.additionalTranssHyperlink", hyperlink);
//
//            return al.evalTemplate("gsa/transactions/statement/trans_IndStatement.html", (com.kivasoft.ITemplateData) null, map);
//        } catch (NBException e) {
//            if (e.getMessage().equals(Messages.ORA_01403))
//                return al.processException(new NoDataFoundException());
//            else
//                return al.processException(e);
//        }
//    }
//*/


}


