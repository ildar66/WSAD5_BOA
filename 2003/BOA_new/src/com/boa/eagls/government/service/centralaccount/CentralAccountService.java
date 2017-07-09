package com.boa.eagls.government.service.centralaccount;

import com.boa.eagls.government.dao.AccountDAO;
import com.boa.eagls.government.dao.AgencyDAO;
import com.boa.eagls.government.dao.CentralAccountDAO;
import com.boa.eagls.government.dto.CentralOffice;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.dto.centralaccount.CentralAcct;
import com.boa.eagls.government.dto.centralaccount.CentralAcctSummary;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.NBError;
import com.boa.eagls.government.exceptions.NoDataFoundException;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.util.pagedList.ValueListHandler;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: IvanK
 * Date: 14.07.2003
 * Time: 9:34:47
 * To change this template use Options | File Templates.
 */
public class CentralAccountService extends Service {
    private static final Logger logger = Logger.getLogger(CentralAccountService.class);

    /**
     * performs several types of search depending of the search parameters
     * @param searchCentralAccountForm containing fields from HTML form
     * @param htUserData user data from session
     * @param userID
     * @return
     * @throws com.boa.eagls.government.exceptions.application.EaglsException
     */
    public ValueListIterator searchAccount(SearchCentralAccountParam searchCentralAccountForm,
                                                  Map htUserData, String userID, String currentBaseRole) throws EaglsException, EaglsDAOError {


        String centralAcctID = searchCentralAccountForm.getTxt_centralAccountID();
        String centralAcctName = searchCentralAccountForm.getTxt_centralAccountName();
        String centralAcctNumber = searchCentralAccountForm.getTxt_centralAccountNumber();
        String centralAcctAgencyName = searchCentralAccountForm.getTxt_agencyName();
        String testDepth = searchCentralAccountForm.getTxt_hierarchyDepth();

        boolean chkCentralAccountID = searchCentralAccountForm.isChk_centralAccountID();
        boolean chkCentralAccountNumber = searchCentralAccountForm.isChk_centralAccountNumber();
        boolean chkCentralAccountName = searchCentralAccountForm.isChk_centralAccountName();
        boolean chkAgencyName = searchCentralAccountForm.isChk_AgencyName();
        boolean chkHierLevel = searchCentralAccountForm.isChk_hierLevel();


//        String centralAcctHierDepth = searchCentralAccountForm.getTxt_hierarchyDepth();

        ValueListSelector generator = null;

        int[] usHierarchy = getCurrentShortHierarchy((HierarchyDTO[]) htUserData.get("currentHierarchy"));
        if (chkCentralAccountNumber) {
            generator = new SearchByAcctNumber(centralAcctNumber, usHierarchy);
        } else if (chkCentralAccountName || chkHierLevel || chkCentralAccountID || chkAgencyName) {
            int[] hierarchy = new int[9];
            short hDepth;
            if (chkHierLevel) {
                hDepth = (short) Integer.parseInt(testDepth, 10);
                hierarchy[0] = stringToInteger(searchCentralAccountForm.getTxt_hl0());
                hierarchy[1] = stringToInteger(searchCentralAccountForm.getTxt_hl1());
                hierarchy[2] = stringToInteger(searchCentralAccountForm.getTxt_hl2());
                hierarchy[3] = stringToInteger(searchCentralAccountForm.getTxt_hl3());
                hierarchy[4] = stringToInteger(searchCentralAccountForm.getTxt_hl4());
                hierarchy[5] = stringToInteger(searchCentralAccountForm.getTxt_hl5());
                hierarchy[6] = stringToInteger(searchCentralAccountForm.getTxt_hl6());
                hierarchy[7] = stringToInteger(searchCentralAccountForm.getTxt_hl7());
                hierarchy[8] = stringToInteger(searchCentralAccountForm.getTxt_hl8());
            } else {
                hDepth = 0;
            }
            generator = new SearchByAllParameters(centralAcctID, centralAcctName, centralAcctNumber,
                    centralAcctAgencyName, chkCentralAccountID, chkCentralAccountNumber,
                    chkCentralAccountName, chkAgencyName, chkHierLevel, hierarchy, usHierarchy, currentBaseRole, hDepth);
        }

        ValueListHandler valueListHandler = new ValueListHandler(generator);
        if (valueListHandler.getSize() == 1) {
            Collection col = valueListHandler.getNext(1);
            Iterator colIter = col.iterator();
            CentralAcctSummary centralAcctSummary = (CentralAcctSummary) colIter.next();
            return new CentralAccountValueListHandler(generator, centralAcctSummary.getCentralAcctNo());
        }
        return new CentralAccountValueListHandler(generator, null);
    }


    public static int stringToInteger(String str){
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public CentralAcct retrieveCentralAcctMaint(String userID, String centralAcctNumber) throws EaglsException, EaglsDAOError {
        Connection con = null;
        CentralAcct results = null;
        try {
            con = getPooledConnection();
            CentralAccountDAO adapter = new CentralAccountDAO();
            results = adapter.retrieveCentralAcct(con, centralAcctNumber, userID);
        } catch (SQLException e) {
            throw new EaglsDAOError("SQL error in retrieveCentralAcctMaint(): " + e);
        } finally {
            closeConnection(con);
        }
        return results;
    }

    public void updateCentralAccountMain(CentralAcct oldCentralAcct,
                                         CentralAcct newCentralAcct, String userID)throws EaglsException {
        Connection con = null;
        Boolean commitState = null;
        try {
            con = getPooledConnection();
            commitState = new Boolean(con.getAutoCommit());
            con.setAutoCommit(false);
            CentralAccountDAO adapter = new CentralAccountDAO();
            adapter.updateCentralAccountDetails(con, oldCentralAcct, newCentralAcct, userID);
            con.commit();
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            rollbackTransaction(con);
            throw new EaglsException("SQL error in retrieveCentralAcctMaint(): ", e);
        } catch (NoDataFoundException e) {
            logger.error(e.getMessage(), e);
            rollbackTransaction(con);
            throw new EaglsException("No data found for update ", e);
        } finally {
            recoverCommitState(con, commitState);
            closeConnection(con);
        }
    }


    /**
     * Converts a string holding a currency in the format $#,###.##
     * into a double type.
     *
     * @param   s   holds the currency to convert
     * @return  double   the double conversion of <code>s</code>;
     *                  <code>Double.NAN</code> if s cannot be parsed.
     **/
    public static double currencyToDouble(String s) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        Number num;
        try {
            num = nf.parse(s);
        } catch (ParseException pe) {
            try {
                return Double.valueOf(s).doubleValue();
            } catch (NumberFormatException nfe) {
                return Double.NaN;
            }
        }
        return num.doubleValue();
    }

    public static boolean stringToBoolean(String s) {
        if ((s.equalsIgnoreCase("Y")) ||
                (s.equalsIgnoreCase("YES"))) {
            return true;
        } else {
            return false;
        }
    }


/* ******************************************************************************************************
//                                          Inner classes
******************************************************************************************************* */

    private class SearchByAllParameters extends ValueListSelector {

        private String acctId;
        private String acctName;
        private String acctNumber;
        private String agencyName;
        private boolean selCentralAcctID;
        private boolean selCentralAccountNumber;
        private boolean selCentralAccountName;
        private boolean selCentralAgencyName;
        private boolean selHierLevel;
        private int[] hierarchy;
        private int[] usHierarchy;
        private String currentBaseRole;
        private short depth;

        public SearchByAllParameters(String acctId, String acctName, String acctNumber, String agencyName,
                                     boolean selCentralAcctID, boolean selCentralAccountNumber,
                                     boolean selCentralAccountName, boolean selCentralAgencyName, boolean selHierLevel,
                                     int[] hierarchy, int[] usHierarchy, String currentBaseRole, short depth) {
            this.acctId = acctId;
            this.acctName = acctName;
            this.acctNumber = acctNumber;
            this.agencyName = agencyName;
            this.selCentralAcctID = selCentralAcctID;
            this.selCentralAccountNumber = selCentralAccountNumber;
            this.selCentralAccountName = selCentralAccountName;
            this.selCentralAgencyName = selCentralAgencyName;
            this.selHierLevel = selHierLevel;
            this.hierarchy = hierarchy;
            this.usHierarchy = usHierarchy;
            this.currentBaseRole = currentBaseRole;
            this.depth = depth;

        }


        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.countByAllParameters(con, selCentralAccountName, selCentralAcctID,
                        selCentralAccountNumber, selCentralAgencyName, selHierLevel, acctId, acctName, acctNumber,
                        agencyName, hierarchy, usHierarchy, currentBaseRole, depth);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAcctId.count(): " + e);
            } finally {
                closeConnection(con);
            }
        }


        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.searchByAllParameters(con, selCentralAccountName, selCentralAcctID,
                        selCentralAccountNumber, selCentralAgencyName, selHierLevel, acctId, acctName, acctNumber,
                        agencyName, hierarchy, usHierarchy, currentBaseRole, depth,
                        firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAcctId.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

    }

    private class SearchByAcctNumber extends ValueListSelector {

        private String acctNumber;
        private int[] usHierarchy;

        public SearchByAcctNumber(String number, int[] hierarchy) {
            this.acctNumber = number;
            this.usHierarchy = hierarchy;
        }


        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.countByAcctNumber(con, acctNumber, usHierarchy);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAccountName.count(): " + e);
            } finally {
                closeConnection(con);
            }
        }


        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.searchByAcctNumber(con, acctNumber, usHierarchy, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAccountName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

    }


    private class SearchByAgencyName extends ValueListSelector {

        private String agencyName;
        private int[] usHierarchy;

        public SearchByAgencyName(String name, int[] hierarchy) {
            this.agencyName = name;
            this.usHierarchy = hierarchy;
        }


        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.countByAgencyName(con, agencyName, usHierarchy);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAccountName.count(): " + e);
            } finally {
                closeConnection(con);
            }
        }


        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                CentralAccountDAO accountDAO = new CentralAccountDAO();
                return accountDAO.searchByAgencyName(con, agencyName, usHierarchy, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByAccountName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

    }

    public static CentralOffice retrieveCentralOffice(int agencyID, int[] hierarchy, String userId)
            throws EaglsException {
        return retrieveCentralOffice(agencyID, hierarchy, true, userId);
    }

    public static CentralOffice retrieveCentralOffice(int agencyID, int[] hierarchy, boolean isSetup, String userId)
            throws EaglsException {

        Connection conn = null;
        try {
            conn = getPooledConnection();
            AgencyDAO adapter = new AgencyDAO();
            return adapter.retrieveCentralOfficeDetail(conn, hierarchy, agencyID, isSetup, userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            throw new EaglsException("error.retrieveCentralOffice");
//            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * used by ViewAccountAction
     * @param centralAccountID
     * @return
     */
    public static AgencyCore retrieveAgencyCoreByCentralAcctID(int centralAccountID, String userID) throws EaglsException {

        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();
            return adapter.retrieveAgencyCoreByCentralAcctID(conn, centralAccountID, userID);
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.centralAccountDoesntExist");
//            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    public static boolean validateCentralAcctIDForSetup(int centralAccountID,
                                                        Map htUserData,
                                                        String userID)
            throws EaglsException {
        boolean res = false;
        Connection conn = null;
        try {
            //session.getCurrentBaseRole(request)
            conn = getPooledConnection();
            // todo pack's lagacy code. Remove connection from constructor to method calls
            AccountDAO adapter = new AccountDAO();//aDASTransport);
            int[] hierarchy = getCurrentShortHierarchy((HierarchyDTO[]) htUserData.get("currentHierarchy"));
            //check for current base role
            if (("A_OPC").equalsIgnoreCase((String) htUserData.get("currentRole"))) {
                res = adapter.validateCentralAcctIDForSetup(conn, centralAccountID,
                        hierarchy);
            } else {
                //don't need a conn as parameter dut to pack's code
                res = adapter.validateCentralAcctID(conn, centralAccountID, userID);
            }
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
//            throw new EaglsException("error.centralAccountDoesntExist");
//            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
        return res;
    }


}
