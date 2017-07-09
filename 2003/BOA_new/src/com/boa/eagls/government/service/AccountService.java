package com.boa.eagls.government.service;

import com.boa.eagls.government.constants.service.Role;
import com.boa.eagls.government.dao.AccountDAO;
import com.boa.eagls.government.dao.AgencyDAO;
import com.boa.eagls.government.dto.CentralOffice;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.NameDescTable;
import com.boa.eagls.government.dto.account.CentralAccount;
import com.boa.eagls.government.dto.agency.AgencyCore;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.service.userprofile.UserAccountService;
import com.boa.eagls.government.util.HierarchyDisplay;
import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.authorizationcontrols.AcctControls;

import java.sql.Connection;
import java.util.Map;

/**
 * Service for Central Account operations
 * @author Oleg Klimenko
 * Date: 28.06.2003
 * Time: 13:38:53
 */
public class AccountService extends Service {
    private static final Logger logger = Logger.getLogger(AccountService.class);

    public static CentralOffice retrieveCentralOffice(
            int agencyID,
            int[] hierarchy,
            String userId)
            throws EaglsException {
        return retrieveCentralOffice(agencyID, hierarchy, true, userId);
    }

    /**
     * checks for Account and Current hierarchies for AOPC role setup
     * @param acctHierarchy
     * @param currentHierarchy
     * @return
     */
    public boolean validateHierarchyForAopcSetup(
            int[] acctHierarchy,
            HierarchyDTO[] currentHierarchy) {
        int userHierarchy[] = getCurrentShortHierarchy(currentHierarchy);
        if (userHierarchy == null) {
            return false;
        }
        try {
            HierarchyBase.sameHierarchyTree(userHierarchy, acctHierarchy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * retrieves Central Office object details. Actually delegates invocation to DAO object AgencyDAO
     * @param agencyID search by agencyID
     * @param hierarchy search by hierarchy
     * @param isSetup search by isSetup
     * @param userId search by userId
     * @return
     * @throws EaglsException
     */
    public static CentralOffice retrieveCentralOffice(
            int agencyID,
            int[] hierarchy,
            boolean isSetup,
            String userId)
            throws EaglsException {

        Connection conn = null;
        try {
            conn = getPooledConnection();
            AgencyDAO adapter = new AgencyDAO();
            return adapter.retrieveCentralOfficeDetail(
                    conn,
                    hierarchy,
                    agencyID,
                    isSetup,
                    userId);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.retrieveCentralOffice");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * used from View Individual Account use case. Gets full information by several parameters
     * @param centralAccountID
     * @param userID
     * @param agency
     * @param currentBaseRole
     * @param currentHierarchy
     * @param lastName
     * @param firstName
     * @param socialSecurityNumber
     * @return
     * @throws EaglsException
     */
    public CentralAccount retrieveAcctID(int centralAccountID, String userID, AgencyCore agency,
                                         String currentBaseRole, HierarchyDTO[] currentHierarchy, String lastName,
                                         String firstName, String socialSecurityNumber) throws EaglsException {
        Connection conn = null;
        CentralAccount centralAccount = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();

            // getting
            HierarchyDisplay hDisplay = null;
            HierarchyDTO[] hier;

            if (!Role.GCSU.equals(currentBaseRole)) {
                hier = currentHierarchy;
                int[] hierarchyD = new HierarchyDisplay(hier).getValues();
                try {
                    HierarchyBase hierarchyBase = new HierarchyBase();
                    String hierarchyNbr =
                            hierarchyBase.getHierarchyNumber(hierarchyD);
                    UserAccountService userAccountService =
                            new UserAccountService(null);

                    //                hDisplay = AccountController.getHierarchyAgencyNames(new Integer(hierarchyNbr).intValue(), das);
                    hDisplay =
                            userAccountService.getHierarchyAgencyNames(
                                    new Integer(hierarchyNbr).intValue());
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                } //end catch NBException
            } else {
                hier = agency.getAHierarchy();
                hDisplay = new HierarchyDisplay(hier);
            }

            //        request.getSession().setAttribute("browseHierarchyFields", browseHierarchyFields);

            ///////////////////////////////
            centralAccount =
                    adapter.retrieveCentralAcctByCentralAcctID(
                            conn,
                            centralAccountID,
                            agency,
                            userID,
                            currentBaseRole,
                            currentHierarchy,
                            lastName,
                            firstName,
                            socialSecurityNumber,
                            hier,
                            hDisplay);
            //            centralAccount.setTxt_acctCenterID(String.valueOf(centralAccountID));
            centralAccount.setBrowseHierarchyFields(
                    fillHierarchyBean(
                            agency.getAHierarchy(),
                            currentHierarchy,
                            currentBaseRole,
                            true));
            centralAccount.setBrowseHierarchyFieldsReadOnly(
                    fillHierarchyBean(
                            agency.getAHierarchy(),
                            currentHierarchy,
                            currentBaseRole,
                            false));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            //todo rethrow new exception. It should be exception of Web Layer
            //            throw new EaglsException("error.centralAccountDoesntExist");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
        return centralAccount;
    }

    /**
     * used by ViewAccountAction. Gets Agency details by centralAccountID
     * @param centralAccountID
     * @return
     */
    public AgencyCore retrieveAgencyCoreByCentralAcctID(int centralAccountID,
                                                        String userID) throws EaglsException {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();
            return adapter.retrieveAgencyCoreByCentralAcctID(conn, centralAccountID, userID);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.centralAccountDoesntExist");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * get retrieve vendor names from DB. Call underlying DAO class
     * @param centralAcctID
     * @return
     * @throws EaglsException
     */
    public static NameDescTable[] retrieveVendorNames(String centralAcctID)
            throws EaglsException {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();
            return adapter.retrieveVendorNames(conn, centralAcctID);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.retrieveVendorNames");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    public static NameDescTable[] retrieveMCCGTableNamesByCentralAcctID(String centralAcctID)
            throws EaglsException {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();
            return adapter.retrieveMCCGTableNamesByCentralAcctID(conn, centralAcctID);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.retrieveMCCGTableNamesByCentralAcctID");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    public static int createAccountControls(AcctControls acctControls, String accountNumber)
            throws EaglsException {
               Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO();
            return AcctControls.createQueueRequest(conn, acctControls, accountNumber);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.createAccountControls");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }

    }

    /**
     * performs Central Account validation durind Setup For Individual Account
     * @param centralAccountID
     * @param htUserData
     * @param userID
     * @return
     * @throws EaglsException
     */
    public boolean validateCentralAcctIDForSetup(
            int centralAccountID,
            Map htUserData,
            String userID)
            throws EaglsException {
        boolean res = false;
        Connection conn = null;
        try {
            //session.getCurrentBaseRole(request)
            conn = getPooledConnection();
            AccountDAO adapter = new AccountDAO(); //aDASTransport);
            int[] hierarchy = getCurrentShortHierarchy((HierarchyDTO[]) htUserData.get("currentHierarchy"));
            //check for current base role
            if ((Role.A_OPC)
                    .equalsIgnoreCase((String) htUserData.get("currentRole"))) {
                res =
                        adapter.validateCentralAcctIDForSetup(
                                conn,
                                centralAccountID,
                                hierarchy);
            } else {
                //don't need a conn as parameter dut to pack's code
                res =
                        adapter.validateCentralAcctID(
                                conn,
                                centralAccountID,
                                userID);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new EaglsException("error.centralAccountDoesntExist");
            //            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
        return res;
    }

}
