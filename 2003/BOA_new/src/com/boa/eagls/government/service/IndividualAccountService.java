package com.boa.eagls.government.service;

import com.boa.eagls.government.dao.IndividualAccountDAO;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.search.SearchDTO;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.service.individualaccount.SearchAccountParam;
import com.boa.eagls.government.util.pagedList.ValueListHandler;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import com.boa.eagls.government.util.HierarchyDisplay;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Service for Individual Account operations
 * @author OlegK
 * Date: 24.06.2003
 * Time: 16:03:58
 */
public class IndividualAccountService extends Service {

    private static Logger logger = Logger.getLogger(IndividualAccountService.class);
//    private SearchAccountParam searchAccountForm;
    private int centralAcctID;
    private IndividualAccountDAO accountDAO = null;

    /**
     * performs Search for Individual Accoutn (Account Inquiry/Maintenance menu)
     * @param viewAccountForm search parameters (form bean)
     * @param currentHierarchy current user's parameter
     * @param currentBaseRole current user's parameter
     * @param programTypes current user's parameter
     * @return
     * @throws EaglsDAOError
     */
    public Collection search(SearchDTO viewAccountForm, HierarchyDTO currentHierarchy[],
                                          String currentBaseRole,
                                          char[] programTypes)
            throws EaglsDAOError {

        Connection con = null;
        try {
            IndividualAccountDAO accountDAO = new IndividualAccountDAO();
            con = getPooledConnection();

            String[] h = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];
            h[0] = viewAccountForm.getTxt_programNumber();
            for (int i = 1; i < HierarchyDisplay.NUMBER_OF_SEGMENTS; i++) {
                h[i] = viewAccountForm.getTxt_hl(i);
            }
            HierarchyDisplay hierarchy = new HierarchyDisplay(h);

            short hDepth;

            try {
                hDepth = Short.parseShort(viewAccountForm.getTxt_hierarchyDepth(), 10);
            } catch (NumberFormatException e) {
                hDepth = 1;
            }

            return accountDAO.search(con, viewAccountForm, hierarchy.getValues(),
                    hDepth, getCurrentShortHierarchy(currentHierarchy), currentBaseRole, programTypes);
        } catch (SQLException e) {
            logger.error("SQL error", e);
            throw new EaglsDAOError("SQL error " + e);
        } catch (EaglsDAOError e) {
            logger.error("logic error error", e);
            throw new EaglsDAOError("logic error " + e);
        } finally {
            closeConnection(con);
        }
    }

    /**
     * stub for TSYS. Creates individual accoutn in Setup For Individual Account use case
     * @param account
     * @return
     * @throws EaglsDAOError
     */
    public String createIndividualAcct(com.boa.eagls.government.dto.IndividualAccount account)
            throws EaglsDAOError {

        Connection con = null;
        try {
            IndividualAccountDAO accountDAO = new IndividualAccountDAO();
            con = getPooledConnection();
            return accountDAO.createIndividualAcct(con, account);
        } catch (SQLException e) {
            throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
        } finally {
            closeConnection(con);
        }
    }

    /**
     * performs several types of search depending of the search parameters
     * @param searchAccountForm VO containing fields from HTML form
     * @param htUserData user data from session
     * @param userID
     * @return
     * @throws com.boa.eagls.government.exceptions.application.EaglsException
     */
    public ValueListIterator searchAccount(SearchAccountParam searchAccountForm,
                                           Map htUserData, String userID) throws EaglsException, EaglsDAOError {
//        this.searchAccountForm = searchAccountForm;
//        this.htUserData = htUserData;
//        this.userID = userID;
        this.centralAcctID = Integer.parseInt(searchAccountForm.getTxtCentralAccountID());
        accountDAO = new IndividualAccountDAO();

        String lastName = searchAccountForm.getTxtLastName();
        String firstName = searchAccountForm.getTxtFirstName();
        String socialSecurityNumber = searchAccountForm.getTxtSocialSecurityNumber();
        ValueListSelector generator = null;
        if (searchAccountForm.getTxtCentralAccountID() == null ||
                searchAccountForm.getTxtCentralAccountID().length() <= 0) {
            throw new EaglsException("error.emptyOrNullCentralAccountID");
        }
        AccountService centralAccount = new AccountService();
        if (centralAccount.validateCentralAcctIDForSetup(centralAcctID, htUserData, userID)) {
            // search on individualaccount name
            int[] hierarchy = getCurrentShortHierarchy((HierarchyDTO[]) htUserData.get("currentHierarchy"));
            if (searchAccountForm.isSearchByName()) {
                if ("".equals(firstName)) {
                    generator = new SearchByLastName(lastName, hierarchy);
                } else {
                    generator = new SearchByFullName(firstName, lastName, hierarchy);
                }
            }
            // search on ssn
            else {
                generator = new SearchBySSN(socialSecurityNumber, hierarchy);
            }
        } else {
            throw new EaglsException("error.centralAccountDoesntExist");
        }
        return new ValueListHandler(generator);
    }

    private class SearchByLastName extends ValueListSelector {

        private String lastName;
        private int[] hierarchy;

        public SearchByLastName(String lastName, int[] hierarchy) {
            this.lastName = lastName;
            this.hierarchy = hierarchy;
        }


        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.countByLastName(con, centralAcctID, lastName, hierarchy);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }


        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.searchByLastName(con, centralAcctID, lastName, hierarchy, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError(e);
            } finally {
                closeConnection(con);
            }
        }

    }

    private class SearchByFullName extends ValueListSelector {
        private String lastName;
        private String firstName;
        private int[] hierarchy;

        public SearchByFullName(String firstName, String lastName, int[] hierarchy) {
            this.lastName = lastName;
            this.hierarchy = hierarchy;
            this.firstName = firstName;
        }

        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.searchByFullName(con, centralAcctID, firstName, lastName, hierarchy, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.countByFullName(con, centralAcctID, firstName, lastName, hierarchy);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

    }

    private class SearchBySSN extends ValueListSelector {

        private String socialSecurityNumber;
        private int[] hierarchy;

        public SearchBySSN(String socialSecurityNumber, int[] hierarchy) {
            this.socialSecurityNumber = socialSecurityNumber;
            this.hierarchy = hierarchy;
        }

        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.countBySsn(con, centralAcctID, socialSecurityNumber, hierarchy);
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountDAO.searchBySsn(con, centralAcctID, socialSecurityNumber, hierarchy, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }
    }
}
