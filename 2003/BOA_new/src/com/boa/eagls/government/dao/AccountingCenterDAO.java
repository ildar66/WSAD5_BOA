package com.boa.eagls.government.dao;

import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.accounting.AccountingCenter;
import com.boa.eagls.government.dto.accounting.AccountingCenterSummary;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegment;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.util.pagedList.SearchResult;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * <p><small> VDI Company, 18.07.2003 17:26:44</small></p>
 * @author OlegK
 */
public class AccountingCenterDAO extends DAOBaseNew {
    static final Logger logger = Logger.getLogger(AccountingCenterDAO.class);
    private static final String PACKAGE_NAME = "ACCOUNTING_CENTER";

    /**
     * Calls an SQL query for retrieving the Accounting Center summary
     * by an array of hierarchy numbers passed from the UI for browsing.
     * AOPC Specific changes are due to scope change #324
     *
     * @param  aHierarchy array of hierarchies, depth of search, LaRS params
     * @return  AccountingCenterSummary object
     * @exception SQLException
     * @exception EaglsDAOError
     **/
    public List searchForBrowseAccountingCenterIDs(Connection conn, int[] aHierarchy, short depth,
                                                   String currentBaseRole)
            throws SQLException, EaglsDAOError {
        List result = new LinkedList();
        int[] searchByHierarchy = null;

        // Use hierarchy passed in
        searchByHierarchy = new int[9];
        for (int i = 0; i < 9; i++) {
            searchByHierarchy[i] = aHierarchy[i] > 0 ? aHierarchy[i] : -1;
        }

        //Load string into buffer
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_ACCOUNTING_CENTER_BY_HIERARCHY);

        if (searchByHierarchy != null) {
            for (int i = 0; i < 9; i++) {
                if (searchByHierarchy[i] != -1) { //Only if restriction is by valid hierarchy value
                    //Add AND clause
                    if (i == 0 && Role.A_OPC.equals(currentBaseRole))
                        sqlStmt.append("  AND ((AH.HL").append(i).append(" = ").append(searchByHierarchy[i]);
                    else
                        sqlStmt.append("  AND AH.HL").append(i).append(" = ").append(searchByHierarchy[i]);
                    sqlStmt.append("\n");
                }
            }

            if (Role.A_OPC.equals(currentBaseRole)) {
                sqlStmt.append(")");
                // enabling AOPCs to see accounting centers above in their hierarchy tree
                for (int i = 1; i < 9 && searchByHierarchy[i + 1] > 0; i++) {
                    for (int j = 0; j < 9; j++) {

                        if (j == 0)
                            sqlStmt.append("OR (AH.HL").append(j).append("=").append((j <= i ? searchByHierarchy[j] : 0)).append("\n");
                        else if (j == 8)
                            sqlStmt.append("AND AH.HL").append(j).append("=").append((j <= i ? searchByHierarchy[j] : 0)).append(")\n");
                        else
                            sqlStmt.append("AND AH.HL").append(j).append("=").append((j <= i ? searchByHierarchy[j] : 0)).append("\n");
                    }
                }
                sqlStmt.append(")\n");
            }// end AOPC

        } else {
            return new LinkedList();
        }
        sqlStmt.append(" ORDER BY AAC.ACCOUNTING_CENTER_ID" + "\n");
        //Log the statement
        //debugLog(sqlStmt.toString(), 1);

        //Run the query
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(sqlStmt.toString());
            rs = st.executeQuery();
            result = parseAccountingCenterSearchResults(rs);
        } finally {
            closeAll(rs, st);
        }
        //Return an array of objects

        return result;
    }

    /**
     * Parses results of above SQL queries for retrieving the Accounting Center summary
     * and populates the AccountingCenterSummary objects.
     *
     * @param  rs
     * @return  Array of AccountingCenterSummary objects
     * @exception SQLException
     **/
    private List parseAccountingCenterSearchResults(ResultSet rs) throws SQLException {

        List collector = new LinkedList();
        // copy result table into UserSearchResult objects
        while (rs.next()) {
            //Construct agency summary object
            AccountingCenterSummary acSummary = new AccountingCenterSummary();
            //Set it's attributes

            // need to put in row id later
            // it is not part of BO yet
//            if (!rs.wasNull(1)) rs.getString(1);
            acSummary.setAccountingCenterID(rs.getString(2));
            acSummary.setAccountingCenterName(rs.getString(3) != null ? rs.getString(3) : "");
            acSummary.setAgencyName(rs.getString(4) != null ? rs.getString(4) : "");

            //Build and set array of hierarchy objects
            HierarchyDTO[] ha = new HierarchyDTO[9];
            for (int i = 0; i < 9; i++) {
                HierarchyDTO h = new HierarchyDTO();
                h.setLevel((short) i);
                h.setNumber(rs.getInt(i + 5));
                h.setDescription(getHierarchyDescriptionByPosition(rs.getString(14), i + 1));
                ha[i] = h;
            }
            acSummary.setHierarchy(ha);
            //add new object to vector
            collector.add(acSummary);
        }

        // Move results from temporary vector to an array
//        int numResults = collector.size();
//        AccountingCenterSummary results[] = new AccountingCenterSummary[numResults];
//        collector.toArray(results);

        return collector;
    }

    /**
     * <code>getHierarchyDescriptionByPosition</code> will return the description of a particular
     *   hierarchy node from the extended description in the database.
     *
     * @param   descriptionString  String which contains the / delimited descriptions
     * @param   position  The desired hierarchy level description to return
     *
     * @return  String
     **/
    private String getHierarchyDescriptionByPosition(String descriptionString, int position) {
        if (descriptionString == null)
            return "";
        StringBuffer strBuff = new StringBuffer();
        int j = 0;
        int k = 0;
        j = descriptionString.indexOf("//");
        if (j == -1) strBuff.append(descriptionString);

        while (j != -1) {
            strBuff.append(descriptionString.substring(k, j + 1));
            strBuff.append("~");
            k = j + 1;
            j = descriptionString.indexOf("//", k);
            if (j == -1) {
                strBuff.append(descriptionString.substring(k));
                descriptionString = strBuff.toString();
            }
        }
        String description = null;
        StringTokenizer strToken = new StringTokenizer(descriptionString, "/", false);
        if (strToken.countTokens() >= position) {  //only do if if the position is within the range of the tokens
            for (int i = 1; i <= position; i++) {
                String temp = strToken.nextElement().toString();
                if (temp.equals("~")) temp = null;  //Tildes in dB are supposed to be NULLs
                if (i == position) description = temp;
            }
        }
        return description;
    }

    /**
     * Get AccountingCenter
     * @param conn
     * @param accountingCenterID
     * @param userID
     * @param validate
     * @param forSetup
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public AccountingCenter retrieve(Connection conn,
                                     String accountingCenterID,
                                     String userID,
                                     boolean validate,
                                     boolean forSetup)
            throws SQLException, EaglsDAOError {
        CallableStatement cs = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            cs = conn.prepareCall("{CALL " + SP_GET_ACC_ID_INFO + "}");

            cs.setString(QUERY_SGAII_iAcctCtrID, accountingCenterID);
            cs.setString(QUERY_SGAII_iUserID, userID);
            String strValidate = validate ? "Y" : "N";
            cs.setString(QUERY_SGAII_iValidateHierarchy, strValidate);

            cs.registerOutParameter(QUERY_SGAII_iAcctCtrID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_iUserID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_iValidateHierarchy, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oRowID, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oACName, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oAgencyName, Types.VARCHAR);
            for (short i = 0, j = 0; i < 9; i++, j += 2) {
                cs.registerOutParameter(QUERY_SGAII_oHL + j, Types.INTEGER);
                cs.registerOutParameter(QUERY_SGAII_oHLDesc + j, Types.VARCHAR);
            }
            cs.registerOutParameter(QUERY_SGAII_oRuleSeqNum, Types.INTEGER);
            cs.registerOutParameter(QUERY_SGAII_oCommentType, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oDbtCdtInd, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oReAllocFlag, Types.VARCHAR);
            cs.registerOutParameter(QUERY_SGAII_oResult, Types.VARCHAR);

            cs.execute();
            AccountingCenter acctCntr =
                    new AccountingCenter(accountingCenterID,
                            cs.getString(QUERY_SGAII_oRowID));
            acctCntr.setAccountingCenterName(cs.getString(QUERY_SGAII_oACName));
            HierarchyDTO hierarchy[] = new HierarchyDTO[9];
            for (short i = 0, j = 0; i < 9; i++, j += 2) {
                HierarchyDTO hLevel = new HierarchyDTO();
                hLevel.setLevel(i);
                hLevel.setNumber(cs.getInt(QUERY_SGAII_oHL + j));
                hLevel.setAgencyName(cs.getString(QUERY_SGAII_oHLDesc + j));
                hierarchy[i] = hLevel;
            }
            acctCntr.setHierarchy(hierarchy);

            if ("S".equalsIgnoreCase(cs.getString(QUERY_SGAII_oCommentType))) {
                acctCntr.setCommentType(AccountingCenter.SEGMENTED_COMMENTS);
            } else {
                acctCntr.setCommentType(AccountingCenter.FREEFORM_COMMENTS);
            }
            // parsing rule sequence number
            String rsn = String.valueOf((cs.getInt(QUERY_SGAII_oRuleSeqNum)));
            Integer cap = new Integer(rsn.substring(0, 1));
            Integer ahp = new Integer(rsn.substring(1, 2));
            Integer mcp = new Integer(rsn.substring(2, 3));
            acctCntr.setCentralAccountPriority(cap.intValue());
            acctCntr.setAccountHolderPriority(ahp.intValue());
            acctCntr.setMCCPriority(mcp.intValue());

            //setting debit credit indicator
            String oDbtCdtInd = cs.getString(QUERY_SGAII_oDbtCdtInd);
            if (oDbtCdtInd != null) {
                boolean oDbtCdtIndBool = oDbtCdtInd.equals("Y") ? true : false;
                acctCntr.setCreditAllocsAllowed(oDbtCdtIndBool);
            }

            //setting AAS reallocation
            String oReAllocFlag = cs.getString(QUERY_SGAII_oReAllocFlag);
            if (oReAllocFlag != null) {
                boolean oReAllocFlagBool = oReAllocFlag.equals("Y") ? true : false;
                acctCntr.setReallocsAfterAASAllowed(oReAllocFlagBool);
            }
            //get Segmented Comments
            st = conn.prepareStatement(SQL_SEARCH_SEG_COMMENTS);
            st.setString(QUERY_SSSC_acctCtrID, accountingCenterID);
            rs = st.executeQuery();
            while (rs.next()) {
                String tempSegComName = "";
                String tempRowIDSegCmts = "";
                tempRowIDSegCmts = rs.getString(1) != null ? rs.getString(1) : "";
                tempSegComName = rs.getString(2) != null ? rs.getString(2) : "";
                int tempSegLen = rs.getInt(3);
                acctCntr.addCommentSegment(tempSegComName, tempSegLen, tempRowIDSegCmts);
            }
            //get Accounting Code Segments
            st = conn.prepareStatement(SQL_SEARCH_ACCT_SEG);
            st.setString(QUERY_SSAS_acctCtrID, accountingCenterID);
            rs = st.executeQuery();
            int segmentNumber = -1;// initial set to -1 , will be 0 once enters do while loop.
            while (rs.next()) {
                segmentNumber++; //to do ever time loop moves
                String tempName = "", tempDesc = "", tempRowIDACSgmnts = "";
                int tempLength = 0, tempStartPos = 0;
                boolean tempReq = false;
                tempRowIDACSgmnts = (rs.getString(1) != null ? rs.getString(1) : "");
                tempName = (rs.getString(2) != null ? rs.getString(2) : "");
                tempDesc = (rs.getString(3) != null ? rs.getString(3) : "");
                tempLength = (rs.getString(4) != null ? rs.getInt(4) : 0);
                tempReq = (("Y".equals(rs.getString(5))) ? true : false);
                tempStartPos = rs.getInt(6);
                acctCntr.addSegment(tempName, tempDesc, tempLength,
                        tempReq, tempStartPos, tempRowIDACSgmnts);
            }
            if (segmentNumber < 0) {
                // throw Exception, since AccountingCenter cannot exist without any segments.
                logger.debug("No accounting code segments found for accounting center ID:" + accountingCenterID);
                if (!forSetup) {
                    throw new EaglsDAOError("APP_W0028:AccountingCenterDAA::The Accounting Center with Accounting Center ID " +
                            accountingCenterID + " does not have any accounting code segments setup.");
                }
            }
            return acctCntr;
        } finally {
            closeAll(rs, st);
            closeAll(null, cs);
        }
    }

    /**
     * Fetch AccountingCodeSegment
     * @param conn
     * @param acctCentralID
     * @param segmentName
     * @param validValueName
     * @return
     * @throws SQLException
     */
    public AccountingCodeSegment fetchAccountingCodeSegment(Connection conn,
                                                            String acctCentralID,
                                                            String segmentName,
                                                            String validValueName)
            throws SQLException {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(SQL_FETCH_VALID_SEG);
            st.setString(QUERY_SFVS_acctCtrID, acctCentralID);
            st.setString(QUERY_SFVS_segmentName, segmentName);
            st.setString(QUERY_SFVS_validValue, validValueName);
            rs = st.executeQuery();
            String tempValue = "", tempValDesc = "";
            boolean tempActive = false;
            if (rs.next()) {
                tempValue = rs.getString(2) != null ? rs.getString(2) : "";
                tempValDesc = rs.getString(3) != null ? rs.getString(3) : "";
                tempActive = "Y".equals(rs.getString(4)) ? true : false;
            }
            return new AccountingCodeSegment(tempValue, tempValDesc, tempActive);
        } finally {
            closeAll(rs, st);
        }
    }

    /**
     * Validate AccountingCodeSegmentDescription
     * @param conn
     * @param acctCentralID
     * @param acsd
     * @param value
     * @param activesOnly
     * @param validate
     * @return
     * @throws SQLException
     */
    public AccountingCodeSegment
            validateSegmentValueIncludeBadSegment(Connection conn,
                                                  String acctCentralID,
                                                  AccountingCodeSegmentDescription acsd,
                                                  String value,
                                                  boolean activesOnly,
                                                  int validate)
            throws SQLException {
        // Make sure value is correct length
        if (value.length() != acsd.getLength()) {
            return null;
        }
        if (validate == AccountingCenter.PARSE_ONLY) {
            //after checking lenght and NULL cases/
            // fuction is called for parsing only no validation required.
            return new AccountingCodeSegment(value, "", true);///no description is sent back.
        }
        // Make sure nulls are allowed if value is null.
        boolean isNull = acsd.isSegmentValueNull(value);
        if (isNull) {
            if (acsd.isRequired()) {//Modified by Jasper Harding
                //  return null;
                return new AccountingCodeSegment(value, "", true);
            } else {
                // If value is null and nulls are allowed, create a null
                // AccountingCodeSegment.
                return new AccountingCodeSegment(value, "", true);
            }
        }


        if (validate == AccountingCenter.ACCOUNTINGCODE_NO_VALIDATE) {
            //after checking lenght and NULL cases/
            // fuction is called for parsing only no validation required.
            return new AccountingCodeSegment(value, "", true);///no description is sent back.
        }

        //Instead of iterating through all the valid values(they can be > 40,000)
        // a direct database check up will be done.
        AccountingCodeSegment acs =
                fetchAccountingCodeSegment(conn, acctCentralID, acsd.getName(), value);
        if (acs != null) {
            if (acs.getValue().equals(value)) {
                if (activesOnly && !acs.isActive()) {
                    return null;
                }
                return acs;
            }
        }


        // Find a match for the value
        /*

        Enumeration enum=this.accountingCodeSegmentVector.elements();
        while (enum.hasMoreElements())
        {
        AccountingCodeSegment acs=(AccountingCodeSegment)enum.nextElement();
        if (acs.getValue().equals(value))
        {
        if (activesOnly && !acs.isActive())
        {
        return null;
        }
        return acs;
        }
        }

        */

        if (!activesOnly) {
            // to take care of old deleted values.
            // Value entered might be an Old deleted value (prior to the concept of deactivating the value instead of deleting!!)
            //then just pass the validation if user has specifically NOT called for 'activeOnly' check.
            //Once database is corrected with such old values. (Either they are removed from individual accounts or are introduced as 'deActivated' in corrosponding AccountingCenter)
            //then following line should be changed to null.
            return new AccountingCodeSegment(value, "", false);// same value / blank description / put active Flag as false.
        }
        return null;

    }

    /**
     *
     * @param con
     * @param accountingCenterID
     * @param browseCriteria
     * @param desc
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public int countAccountingCodeSegments(Connection con,
                                           String accountingCenterID,
                                           String browseCriteria,
                                           AccountingCodeSegmentDescription desc)
            throws SQLException, EaglsDAOError {
        return getAccountingCodeSegments(con, accountingCenterID, browseCriteria,
                desc, -1, -1, true).getSize();
    }

    /**
     *
     * @param con
     * @param accountingCenterID
     * @param browseCriteria
     * @param desc
     * @param firstRecord
     * @param numRecords
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public Collection searchAccountingCodeSegments(Connection con,
                                                   String accountingCenterID,
                                                   String browseCriteria,
                                                   AccountingCodeSegmentDescription desc,
                                                   int firstRecord,
                                                   int numRecords)
            throws SQLException, EaglsDAOError {
        return getAccountingCodeSegments(con,
                accountingCenterID,
                browseCriteria,
                desc,
                firstRecord,
                numRecords,
                false).getElements();
    }

    /*public SearchResult getAccountingCodeSegments(Connection con,
                                                  String accountingCenterID,
                                                  AccountingCodeSegmentDescription desc,
                                                  int firstRecord,
                                                  int numRecords,
                                                  boolean count)
            throws SQLException, EaglsDAOError {
        return getAccountingCodeSegments(con, accountingCenterID, null,
                desc,
                firstRecord,
                numRecords,
                count);
    }*/

    /**
     *
     * @param con
     * @param accountingCenterID
     * @param browseCriteria
     * @param desc
     * @param firstRecord
     * @param numRecords
     * @param count
     * @return
     * @throws SQLException
     * @throws EaglsDAOError
     */
    public SearchResult getAccountingCodeSegments(Connection con,
                                                  String accountingCenterID,
                                                  String browseCriteria,
                                                  AccountingCodeSegmentDescription desc,
                                                  int firstRecord,
                                                  int numRecords,
                                                  boolean count)
            throws SQLException, EaglsDAOError {

        SearchResult result = new SearchResult();
        ArrayList list = new ArrayList();
        String segmentName = desc.getName();
        StringBuffer sqlStmt = new StringBuffer(SQL_SEARCH_VALID_SEG);
        PreparedStatement st = null;
        ResultSet res = null;
        if (browseCriteria != null) {
            browseCriteria = browseCriteria.trim();
            sqlStmt.append(" AND SEGMENT_VALUE LIKE '" + browseCriteria + "%' \n");
        }
        try {
            if (count) {
                String countSQL = ValueListSelector.generatePagedCountSQL(sqlStmt.toString());
                st = con.prepareStatement(countSQL);
                st.setString(QUERY_SSVS_acctCtrID, accountingCenterID);
                st.setString(QUERY_SSVS_segmentName, segmentName);
                res = st.executeQuery();
                if (res.next()) {
                    result.setSize(res.getInt(1));
                }
            } else {
                String pagedSQL = ValueListSelector.generatePagedSQL(sqlStmt.toString(),
                        firstRecord, numRecords);
                st = con.prepareStatement(pagedSQL);
                st.setString(QUERY_SSVS_acctCtrID, accountingCenterID);
                st.setString(QUERY_SSVS_segmentName, segmentName);
                res = st.executeQuery();
                try {
                    while (res.next()) {
                        AccountingCodeSegment acs = new AccountingCodeSegment(res.getString(1));
                        acs.getDescription();
                        list.add(acs);
                    }
                } catch (Exception e) {
                    logger.error("bad AccountingCodeSegment", e);
                    throw new EaglsDAOError(e);
                }
                result.setElements(list);
            }
        } finally {
            closeAll(res, st);
        }
        return result;
    }


    private static final String SQL_SEARCH_ACCOUNTING_CENTER_BY_HIERARCHY =
            "SELECT ROWIDTOCHAR(AAC.ROWID), AAC.ACCOUNTING_CENTER_ID,\n" +
            " AAC.ACCOUNTING_CENTER_NAME, AH.AGENCY_NAME, AH.HL0, AH.HL1,\n" +
            " AH.HL2, AH.HL3, AH.HL4, AH.HL5, AH.HL6, AH.HL7, AH.HL8, AH.EXTENDED_DESCRIPTION \n" +
            " FROM AGENCY_ACCOUNTING_CENTER AAC, AGENCY_HL AH \n" +
            " WHERE AAC.HIERARCHY_NBR = AH.HIERARCHY_NBR \n";

    private static final String SQL_SEARCH_SEG_COMMENTS =
            "SELECT ROWIDTOCHAR(ROWID), SEGMENT_COMMENT_NAME, SEGMENT_COMMENT_LENGTH \n" +
            " FROM SEGMENTED_COMMENTS \n" +
            " WHERE ACCOUNTING_CENTER_ID = ? \n" + //  :acctCtrID
            " ORDER BY SEGMENT_COMMENT_NBR";

    private static final String SQL_SEARCH_ACCT_SEG =
            "SELECT ROWIDTOCHAR(ROWID), SEGMENT_NAME, SEGMENT_DESCRIPTION, SEGMENT_LENGTH, REQUIRED_FLAG, \n" +
            " SEGMENT_STARTING_POSITION FROM ACCOUNTING_SEGMENT \n" +
            " WHERE ACCOUNTING_CENTER_ID = ? \n" + // :acctCtrID
            " ORDER BY SEGMENT_STARTING_POSITION \n";

    private static final String SQL_FETCH_VALID_SEG =
            " SELECT ROWIDTOCHAR(ROWID), SEGMENT_VALUE, DESCRIPTION, ACTIVE \n" +
            " FROM VALID_SEGMENTS \n" +
            " WHERE ACCOUNTING_CENTER_ID = ? \n" + //:acctCtrID
            " AND SEGMENT_NAME = ? \n" + //:segmentName
            " AND SEGMENT_VALUE = ?";  //:validValue

    private static final String SQL_SEARCH_VALID_SEG =
            " SELECT SEGMENT_VALUE||'^'||DESCRIPTION||'^'||ACTIVE \n" +
            " FROM VALID_SEGMENTS \n" +
            " WHERE ACCOUNTING_CENTER_ID = ? \n" + // :acctCtrID
            " AND SEGMENT_NAME = ? \n";  //  :segmentName

    private static final String SP_GET_ACC_ID_INFO =
            PACKAGE_NAME + ".GET_ACC_ID_INFO(" +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
            "?, ?, ?, ?, ?, ?, ?, ?, ?" +
            ")";

    private final static int QUERY_SGAII_iAcctCtrID = 1;
    private final static int QUERY_SGAII_iUserID = 2;
    private final static int QUERY_SGAII_iValidateHierarchy = 3;
    private final static int QUERY_SGAII_oRowID = 4;
    private final static int QUERY_SGAII_oACName = 5;
    private final static int QUERY_SGAII_oAgencyName = 6;
    private final static int QUERY_SGAII_oHL = 7;
    private final static int QUERY_SGAII_oHLDesc = 8;
    private final static int QUERY_SGAII_oRuleSeqNum = 25;
    private final static int QUERY_SGAII_oCommentType = 26;
    private final static int QUERY_SGAII_oDbtCdtInd = 27;
    private final static int QUERY_SGAII_oReAllocFlag = 28;
    private final static int QUERY_SGAII_oResult = 29;

    private final static int QUERY_SSSC_acctCtrID = 1;

    private final static int QUERY_SSAS_acctCtrID = 1;

    private final static int QUERY_SFVS_acctCtrID = 1;
    private final static int QUERY_SFVS_segmentName = 2;
    private final static int QUERY_SFVS_validValue = 3;

    private final static int QUERY_SSVS_acctCtrID = 1;
    private final static int QUERY_SSVS_segmentName = 2;

}
