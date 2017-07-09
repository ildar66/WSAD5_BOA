package com.boa.eagls.government.dao;

import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.setup.MCVehicleAcctSummary;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.exceptions.NoDataFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: GlebL
 * Date: 10.07.2003
 * Time: 13:00:47
 * To change this template use Options | File Templates.
 */
public class MastercardVehicleDAO extends DAOBaseNew {

    public final static String GET_CENTRAL_ID_INFO = "STANDARD_OBJECTS_2.GET_CENTRAL_ID_INFO(" +
            "?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?)";

    public final static String GET_AGENCY_INFO = "STANDARD_OBJECTS_2.GET_AGENCY_INFO(" +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?)";

    public final static String SP_VEHICLE_ACCOUNT_SETUP =
            "MASTERCARD_VEHICLE_SETUP.VEHICLE_ACCOUNT_SETUP(" +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
            "?, ?, ?, ?, ?, ?, ?, ?)";

    public final static String SQL_SEARCH_VEHICLE_BY_EQUIPMENTID_CENTRALID =
            "select DISTINCT(to_char(a.account_nbr)), a.last_name, va.vin, v.voyager_acct_nbr \n" +
            "from account a, vehicle_account va, agency_hl ah, voyager_account v \n" +
            "where a.account_nbr = va.account_nbr \n" +
            "AND a.account_nbr = v.account_nbr(+) \n" +
            "AND ah.hierarchy_nbr = a.hierarchy_nbr AND a.agency_bill_nbr = ";

    public final static String SQL_PROGRAM_TYPE_FOOTER = "\n AND a.account_type = 'T'" +
            "AND (a.prog_type = 'F' \n" +
            "OR a.agency_bill_nbr = (SELECT distinct ac.agency_bill_nbr \n" +
            "FROM account ac \n" +
            "WHERE ac.account_type = 'C' \n" +
            "AND ac.prog_type = 'I' \n" +
            "AND ac.agency_bill_nbr = a.agency_bill_nbr \n" +
            "AND ac.account_nbr in ( select account_nbr \n" +
            "from integrated_card \n" +
            "Where fleet_flag = 'Y'))) \n";

    public final static String SQL_ACCOUNTING_CENTER_IDS =
            "SELECT ACCOUNTING_CENTER_ID\n" +
            "FROM BILLING_AGENCY\n" +
            "WHERE AGENCY_BILL_NBR = ?\n"; // :iCentralAccountId

    public final static String SQL_SEARCH_CARD_TYPES =
            "SELECT c.CMID, c.DESCRIPTION " +
            "FROM CARD_DESIGN c, ACCOUNT a " +
            "WHERE a.ACCOUNT_TYPE = 'C' " + //MM--MODIFIED AS PER REQUETS FROM PERFORMANCE TEAM
            "AND a.AGENCY_BILL_NBR = ? " + // :iCentralAccountId
            "AND a.HIERARCHY_NBR = c.HIERARCHY_NBR(+) ";

    public final static String SQL_SEARCH_EMPLOYMENT_STATUS =
            "SELECT /*+ RULE */ e.STATUS, e.DESCRIPTION" +
            " FROM EMPLOYMENT_STATUS e " +
            " WHERE E.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR \n " +
            " FROM ACCOUNT a \n " +
            " WHERE a.ACCOUNT_TYPE = 'C' " +
            " AND a.AGENCY_BILL_NBR = ?)";   // :iCentralAccountId

    public final static String SQL_SEARCH_RANK_GRADE =
            " SELECT r.RANK_GRADE " +
            " FROM RANK_GRADE r " +
            " WHERE r.HIERARCHY_NBR = (SELECT A.HIERARCHY_NBR \n " +
            " FROM ACCOUNT A \n " +
            " WHERE A.ACCOUNT_TYPE = 'C' \n " +
            " AND A.AGENCY_BILL_NBR = ?) \n " + // :iCentralAccountId
            " ORDER BY r.RANK_GRADE ";

    public final static String SQL_SEARCH_PRODUCTS =
            "select * from mc_fleet_products";

    private final static int QUERY_GCII_iUserId = 1;
    private final static int QUERY_GCII_iCentralAccountId = 2;
    private final static int QUERY_GCII_oCentralAccountName = 3;
    private final static int QUERY_GCII_oCentralAccountNumber = 4;
    private final static int QUERY_GCII_oConvenienceChecks = 5;
    private final static int QUERY_GCII_oBillingType = 6;
    private final static int QUERY_GCII_oTravelerChecks = 7;
    private final static int QUERY_GCII_oAtmAccess = 8;
    private final static int QUERY_GCII_oDecrementingCard = 9;
    private final static int QUERY_GCII_oProgramType = 10;
    private final static int QUERY_GCII_oAccountingCenterID = 11;
    private final static int QUERY_GCII_oResult = 12;

    private final static int QUERY_GAI_iUserId = 1;
    private final static int QUERY_GAI_iCentralAccountId = 2;
    private final static int QUERY_GAI_oPerformCreditChecks = 3;
    private final static int QUERY_GAI_oHL = 4;
    private final static int QUERY_GAI_oAgencyName = 22;
    private final static int QUERY_GAI_oAgencyID = 23;
    private final static int QUERY_GAI_oAccountingCenterID = 24;
    private final static int QUERY_GAI_oFleetType = 25;
    private final static int QUERY_GAI_oResult = 26;

    private final static int QUERY_SVAS_iUserId = 1;
    private final static int QUERY_SVAS_iAccountNumber = 2;
    private final static int QUERY_SVAS_iHL = 3;
    private final static int QUERY_SVAS_iAgencyBillNumber = 12;
    private final static int QUERY_SVAS_iLastName = 13;
    private final static int QUERY_SVAS_iProgramType = 14;
    private final static int QUERY_SVAS_iAcctCenter = 15;
    private final static int QUERY_SVAS_iMasterAcctCode = 16;
    private final static int QUERY_SVAS_iConvenienceCheckFlag = 17;
    private final static int QUERY_SVAS_iPaperFlag = 18;
    private final static int QUERY_SVAS_iBillingType = 19;
    private final static int QUERY_SVAS_iCardType = 20;
    private final static int QUERY_SVAS_iVehicleType = 21;
    private final static int QUERY_SVAS_iVehicleID = 22;
    private final static int QUERY_SVAS_iFuelLow = 23;
    private final static int QUERY_SVAS_iFuelHigh = 24;
    private final static int QUERY_SVAS_iAddress1 = 25;
    private final static int QUERY_SVAS_iAddress2 = 26;
    private final static int QUERY_SVAS_iAddress3 = 27;
    private final static int QUERY_SVAS_iAddress4 = 28;
    private final static int QUERY_SVAS_iCity = 29;
    private final static int QUERY_SVAS_iState = 30;
    private final static int QUERY_SVAS_iCountry = 31;
    private final static int QUERY_SVAS_iZip = 32;
    private final static int QUERY_SVAS_iCardNonCard = 33;
    private final static int QUERY_SVAS_iExpirationDate = 34;
    private final static int QUERY_SVAS_iCreditLimit = 35;
    private final static int QUERY_SVAS_oTempAcctNumber = 36;
    private final static int QUERY_SVAS_iInsertUpdateFlag = 37;
    private final static int QUERY_SVAS_oResult = 38;

    /**
     * Get account
     * @param conn
     * @param centralAcctID
     * @param userID
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public Account retrieveCentralAcctByCentralAcctID(Connection conn,
                                                      String centralAcctID,
                                                      String userID)
            throws SQLException, EaglsDAOError {

        Account centralAcct = new Account();
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall("{CALL " + GET_CENTRAL_ID_INFO + "}");
            cs.setString(QUERY_GCII_iUserId, userID);
            cs.setString(QUERY_GCII_iCentralAccountId, centralAcctID);

            cs.registerOutParameter(QUERY_GCII_iUserId, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_iCentralAccountId, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oCentralAccountName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oCentralAccountNumber, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oConvenienceChecks, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oTravelerChecks, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oBillingType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oAtmAccess, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oDecrementingCard, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oProgramType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oAccountingCenterID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GCII_oResult, Types.VARCHAR);

            cs.execute();

            centralAcct.setCentralAcctID(centralAcctID);
            centralAcct.setAcctName(cs.getString(QUERY_GCII_oCentralAccountName));
            centralAcct.setAcctNo(cs.getString(QUERY_GCII_oCentralAccountNumber));
            String  strConvenienceChecks = cs.getString(QUERY_GCII_oConvenienceChecks);
            if (strConvenienceChecks != null) {
                 strConvenienceChecks = strConvenienceChecks.trim();
            }
            centralAcct.setIssueConvenienceChecks(
                    "Y".equals(strConvenienceChecks) ? true : false);
            String strTravelerChecks = cs.getString(QUERY_GCII_oTravelerChecks);
            if (strTravelerChecks != null) {
                 strTravelerChecks = strTravelerChecks.trim();
            }
            centralAcct.setIssueTravelersChecks(
                    "Y".equals(strTravelerChecks) ? true : false);
            centralAcct.setAccountingCenterID(cs.getString(QUERY_GCII_oAccountingCenterID));

            short billingType = 0;

            String strBillingType = cs.getString(QUERY_GCII_oBillingType);
            if (strBillingType != null) {
                strBillingType = strBillingType.trim();
            }
            if ("C".equals(strBillingType)) {
                billingType = centralAcct.CENTRAL;
            } else if ("I".equals(strBillingType)) {
                billingType = centralAcct.INDIVIDUAL;
            }
            centralAcct.setBillingType(billingType);
            String strAtmAccess = cs.getString(QUERY_GCII_oAtmAccess);
            if (strAtmAccess != null) {
                strAtmAccess = strAtmAccess.trim();
            }
            centralAcct.setHasATMAccess(
                    "Y".equals(strAtmAccess) ? true : false);
            String strDecrementingCard = cs.getString(QUERY_GCII_oDecrementingCard);
            if (strDecrementingCard != null) {
                strDecrementingCard = strDecrementingCard.trim();
            }
            centralAcct.setUsesDecrementingCard(
                    "Y".equals(strDecrementingCard) ? true : false);

            short programType = 0;

            String strProgramType = cs.getString(QUERY_GCII_oProgramType);
            if (strProgramType != null) {
                strProgramType = strProgramType.trim();
            }
            if ("P".equals(strProgramType)) {
                programType = centralAcct.PURCHASE;
            } else if ("T".equals(strProgramType)) {
                programType = centralAcct.TRAVEL;
            } else if ("F".equals(strProgramType)) {
                programType = centralAcct.FLEET;
            } else if ("I".equals(strProgramType)) {
                programType = centralAcct.INTEGRATE;
            } else if ("I".equals(strProgramType)) {
                programType = centralAcct.INTERAGENCY;
            }

            centralAcct.setProgramType(programType);
            return centralAcct;
        } finally {
            closeAll(null, cs);
        }
    }

    /**
     * Get AgencyCore
     * @param conn
     * @param centralAcctID
     * @param userID
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public AgencyCore retrieveAgencyCoreByCentralAcctID(Connection conn,
                                                        String centralAcctID,
                                                        String userID)
            throws SQLException, EaglsDAOError {
        if (centralAcctID == null || centralAcctID.equals("")) {
            throw new EaglsDAOError("APP_W0009:AccountDAA: centralAcctID unspecified" + ".");
        }

        AgencyCore newAgencyCore = new AgencyCore();
        CallableStatement cs = null;

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            cs = conn.prepareCall("{CALL " + GET_AGENCY_INFO + "}");
            cs.setString(QUERY_GAI_iUserId, userID);
            cs.setString(QUERY_GAI_iCentralAccountId, centralAcctID);

            cs.registerOutParameter(QUERY_GAI_iUserId, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GAI_iCentralAccountId, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GAI_oPerformCreditChecks, Types.VARCHAR);
            for (short i = 0; i < 9; i++) {
                cs.registerOutParameter(QUERY_GAI_oHL + i, Types.INTEGER);
                cs.registerOutParameter(QUERY_GAI_oHL + 9 + i, Types.VARCHAR);
            }
            cs.registerOutParameter(QUERY_GAI_oAgencyName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GAI_oAgencyID, Types.INTEGER);
            cs.registerOutParameter(QUERY_GAI_oAccountingCenterID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_GAI_oFleetType, Types.VARCHAR);
            //cs.registerOutParameter(QUERY_GAI_oFleetType, Types.CHAR);
            cs.registerOutParameter(QUERY_GAI_oResult, Types.VARCHAR);

            cs.execute();

            HierarchyDTO newHierarchy[] = new HierarchyDTO[9];
            for (short i = 0; i < 9; i++) {
                HierarchyDTO hLevel = new HierarchyDTO();
                hLevel.setLevel(i);
                hLevel.setNumber(cs.getInt(QUERY_GAI_oHL + i));
                String tmpDesc = cs.getString(QUERY_GAI_oHL + i + 9);
                if (tmpDesc == null || tmpDesc.equals("~")) {
                    hLevel.setDescription("");
                    hLevel.setAgencyName("");
                } else {
                    hLevel.setDescription(tmpDesc);
                    hLevel.setAgencyName(tmpDesc);
                }
                newHierarchy[i] = hLevel;
            }
            newAgencyCore.setHierarchy(newHierarchy);
            newAgencyCore.setPerformCreditChecks(
                    "Y".equals(cs.getString(QUERY_GAI_oPerformCreditChecks)) ? true : false);
            newAgencyCore.setAgencyName(cs.getString(QUERY_GAI_oAgencyName));
            newAgencyCore.setAgencyID(cs.getInt(QUERY_GAI_oAgencyID));
            newAgencyCore.setAccountingCenterID(cs.getString(QUERY_GAI_oAccountingCenterID));
            short fleetType = 0;
            String strFleetType = cs.getString(QUERY_GAI_oFleetType);
            //int strFleetType = cs.getInt(QUERY_GAI_oFleetType);
            if (strFleetType != null) {
                strFleetType = strFleetType.trim();
            }
            if ("M".equals(strFleetType)) {
                fleetType = AgencyCore.MASTERCARD;
            } else if ("V".equals(strFleetType)) {
                fleetType = AgencyCore.VOYAGER;
            } else {
                fleetType = AgencyCore.NONE;
            }
            /*if (Character.getNumericValue('M') == strFleetType) {
                fleetType = AgencyCore.MASTERCARD;
            } else if (Character.getNumericValue('V') == strFleetType) {
                fleetType = AgencyCore.VOYAGER;
            } else {
                fleetType = AgencyCore.NONE;
            } */
            newAgencyCore.setFleetType(fleetType);
            ArrayList list = new ArrayList();
            st = conn.prepareStatement(SQL_ACCOUNTING_CENTER_IDS);
            st.setString(1, centralAcctID);
            rs = st.executeQuery();
            while (rs.next()) {
                String s = rs.getString(1);
                if (!rs.wasNull()) {
                    list.add(s.trim());
                }
            }
            newAgencyCore.setAccountingCenterIDs(listToStrArray(list));


            st.close();

            list = new ArrayList();
            st = conn.prepareStatement(SQL_SEARCH_CARD_TYPES);
            st.setString(1, centralAcctID);
            rs = st.executeQuery();
            while (rs.next()) {
                NameDescTable aNDT = new NameDescTable();
                aNDT.setName(rs.getString(1));
                aNDT.setDescription(rs.getString(2));
                list.add(aNDT);

            }
            newAgencyCore.setCardTypes(listToStrNameDescTable(list));

            // Employment Status
            String employmentStatus[] = retrieveEmploymentStatuses(conn, centralAcctID);
            newAgencyCore.setEmploymentStatuses(employmentStatus);

            // Rank Grade
            String rankGrade[] = retrieveRankGrades(conn, centralAcctID);
            newAgencyCore.setGradeAndStatuses(rankGrade);
        } finally {
            closeAll(rs, st);
            closeAll(null, cs);
        }
        return newAgencyCore;
    }

    /**
     * Retrieves Listing of Ranks and Grades for the passed in central Acct ID
     *
     * @param   conn    connection
     * @param   centralAcctID    identifies the central Acct ID
     * @return  String[]        List of Ranks and Grades
     *
     **/
    public String[] retrieveRankGrades(Connection conn, String centralAcctID)
            throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            st = conn.prepareStatement(SQL_SEARCH_RANK_GRADE);
            st.setString(1, centralAcctID);
            rs = st.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1);
                if (!rs.wasNull()) {
                    list.add(str);
                }
            }
        } finally {
            closeAll(rs, st);
        }
        return listToStrArray(list);
    }

    /**
     * Retrieves Listing of Employment Statuses for the passed in central Acct ID
     *
     * @param   conn    connection
     * @param   centralAcctID    identifies the central Acct ID
     * @return  String[]        List of Employment Statuses
     *
     **/
    public String[] retrieveEmploymentStatuses(Connection conn, String centralAcctID)
            throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            st = conn.prepareStatement(SQL_SEARCH_EMPLOYMENT_STATUS);
            st.setString(1, centralAcctID);
            rs = st.executeQuery();
            while (rs.next()) {
                String str = rs.getString(1);
                if (!rs.wasNull()) {
                    list.add(str);
                }
            }
        } finally {
            closeAll(rs, st);
        }
        return listToStrArray(list);
    }

    /**
     * Search Mastercard Vehicle
     * @param conn
     * @param centralAcctID
     * @param equipmentID
     * @param hierarchy
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public ArrayList searchMastercardVehicleByEquipmentID(Connection conn,
                                                          String centralAcctID,
                                                          String equipmentID,
                                                          int[] hierarchy)
            throws SQLException, EaglsDAOError {
        int aHierarchy[] = authorizeHierarchySearch(null, (short) 0, hierarchy);

        if (aHierarchy == null) {
            throw new EaglsDAOError("internalError.nullAuthorizationHierarchy");
        }

        String sql = SQL_SEARCH_VEHICLE_BY_EQUIPMENTID_CENTRALID + centralAcctID +
                "\n   AND a.account_nbr = va.account_nbr " +
                "\n   AND va.vin =  upper('" + equipmentID + "')\n";

        StringBuffer sqlStmt = new StringBuffer(sql);

        for (short i = 0; i < 9; i++) {
            if (aHierarchy[i] >= 0) {
                sqlStmt.append("   AND ah.HL" + i + " = " + aHierarchy[i] + "\n");
            }
        }

        sqlStmt.append(SQL_PROGRAM_TYPE_FOOTER);
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {
            String pagedSQL = sqlStmt.toString();
            st = conn.prepareStatement(pagedSQL);
            rs = st.executeQuery();
            while (rs.next()) {
                list.add(populateMCVehicleAcctSummary(rs));
            }
        } finally {
            closeAll(rs, st);
        }
        return list;
    }

    /**
     * Retrieve fuel type info
     * @param conn
     * @return
     * @throws SQLException
     */
    public ArrayList retrieveFuelTypeInfo(Connection conn)
            throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList();
        try {

            st = conn.prepareStatement(SQL_SEARCH_PRODUCTS);
            rs = st.executeQuery();
            while (rs.next()) {
                NameDescTable nameDescTable = new NameDescTable();
                nameDescTable.setName(rs.getString(1));
                nameDescTable.setDescription(rs.getString(2));
                list.add(nameDescTable);
            }
        } finally {
            closeAll(rs, st);
        }
        return list;
    }

    private int parseNullHierarchy(HierarchyDTO h) {
        if (h == null) {
            return 0;
        } else {
            return h.getNumber();
        }
    }

    /**
     * Send mastercard vehicle info to DB
     * @param conn
     * @param params
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     * @throws NoDataFoundException
     */
    public String sendMastercardVehicle(Connection conn, Map params)
            throws SQLException, EaglsDAOError, NoDataFoundException {
        CallableStatement cs = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            cs = conn.prepareCall("{CALL " + SP_VEHICLE_ACCOUNT_SETUP + "}");
            cs.setString(QUERY_SVAS_iUserId, (String) params.get("userID"));
            cs.setString(QUERY_SVAS_iAccountNumber,
                    (String) params.get("hdn_centralNumber"));
            HierarchyDTO[] hierarchy = (HierarchyDTO[]) params.get("aHierarchy");
            for (short i = 0; i < 9; i++) {
              cs.setInt(QUERY_SVAS_iHL + i, parseNullHierarchy(hierarchy[i]));
            }
            cs.setString(QUERY_SVAS_iAgencyBillNumber, (String) params.get("hdn_centralId"));
            cs.setString(QUERY_SVAS_iLastName, "");
            short i = ((Short) params.get("hdn_programType")).shortValue();
            switch (i) {
                case 0:
                    cs.setString(QUERY_SVAS_iProgramType, "P");
                    break;
                case 1:
                    cs.setString(QUERY_SVAS_iProgramType, "T");
                    break;
                case 2:
                    cs.setString(QUERY_SVAS_iProgramType, "F");
                    break;
                case 3:
                    cs.setString(QUERY_SVAS_iProgramType, "I");
                    break;
                case 4:
                    cs.setString(QUERY_SVAS_iProgramType, "A");
                    break;
            }
            cs.setString(QUERY_SVAS_iAcctCenter,
                    (String) params.get("txt_accountingCenterId"));
            cs.setString(QUERY_SVAS_iMasterAcctCode,
                    (String) params.get("txt_accountingCode"));
            cs.setString(QUERY_SVAS_iConvenienceCheckFlag, "Y");
            cs.setString(QUERY_SVAS_iPaperFlag, (String) params.get("rag_generatePaper"));
            cs.setString(QUERY_SVAS_iBillingType, (String) params.get("hdn_billingType"));
            cs.setString(QUERY_SVAS_iCardType, (String) params.get("cmb_cardType"));
            cs.setString(QUERY_SVAS_iVehicleType, (String) params.get("txt_equipmentType"));
            cs.setString(QUERY_SVAS_iVehicleID, (String) params.get("txt_equipmentId"));
            cs.setString(QUERY_SVAS_iFuelLow, ((String) params.get("cmb_equipFuelLow")).substring(0, 3));
            cs.setString(QUERY_SVAS_iFuelHigh, ((String) params.get("cmb_equipFuelHigh")).substring(0, 3));
            cs.setString(QUERY_SVAS_iAddress1, (String) params.get("txt_addressLine1"));
            cs.setString(QUERY_SVAS_iAddress2, (String) params.get("txt_addressLine2"));
            cs.setString(QUERY_SVAS_iAddress3, (String) params.get("txt_addressLine3"));
            cs.setString(QUERY_SVAS_iAddress4, (String) params.get("txt_addressLine4"));
            cs.setString(QUERY_SVAS_iCity, (String) params.get("txt_city"));
            cs.setString(QUERY_SVAS_iState, (String) params.get("txt_state"));
            cs.setString(QUERY_SVAS_iCountry, (String) params.get("txt_country"));
            cs.setString(QUERY_SVAS_iZip, (String) params.get("txt_zip"));
            cs.setString(QUERY_SVAS_iCardNonCard, "Y");

            cs.setString(QUERY_SVAS_iExpirationDate,
                    (String) params.get("txt_accountExpireDate"));
            cs.setDouble(QUERY_SVAS_iCreditLimit,
                    ((Double) params.get("txt_creditLimit")).doubleValue());

            cs.setString(QUERY_SVAS_iInsertUpdateFlag, "0");
            cs.registerOutParameter(QUERY_SVAS_oTempAcctNumber, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SVAS_oResult, Types.VARCHAR);
            cs.execute();
            testOutput(cs.getString(QUERY_SVAS_oResult));
            return cs.getString(QUERY_SVAS_oTempAcctNumber);
        } finally {
            closeAll(rs, st);
            closeAll(null, cs);
        }
    }

    private MCVehicleAcctSummary populateMCVehicleAcctSummary(ResultSet rs)
            throws SQLException {
        MCVehicleAcctSummary summary = new MCVehicleAcctSummary();
        summary.setAcctNumber(rs.getString(1));
        summary.setAcctName(rs.getString(2));
        summary.setEquipmentID(rs.getString(3));
        summary.setVoyagerAccountNumber(rs.getString(3));
        return summary;
    }

    private String[] listToStrArray(ArrayList list) {
        String[] strArray = new String[list.size()];
        list.toArray(strArray);
        return strArray;
    }

    private NameDescTable[] listToStrNameDescTable(ArrayList list) {
        NameDescTable[] ndtArray = new NameDescTable[list.size()];
        list.toArray(ndtArray);
        return ndtArray;
    }
}
