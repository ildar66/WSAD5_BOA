package com.boa.eagls.government.service;

import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.accounting.*;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.exceptions.application.EaglsException;
import com.boa.eagls.government.exceptions.SegmentError;
import com.boa.eagls.government.exceptions.InvalidAccountingCodeError;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.dao.AccountingCenterDAO;
import com.boa.eagls.government.util.HierarchyDisplay;
import com.boa.eagls.government.util.pagedList.ValueListSelector;
import com.boa.eagls.government.util.pagedList.ValueListIterator;
import com.boa.eagls.government.util.pagedList.ValueListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

/**
 * <p><small> VDI Company, 18.07.2003 17:24:10</small></p>
 * @author OlegK
 */
public class AccountingService extends Service {
    static final Logger logger = Logger.getLogger(AccountingService.class);

    /**
     * retrives Accounting Center IDs from DB
     * @param aHierarchy
     * @param depth
     * @param currentBaseRole
     * @return
     * @throws EaglsException
     */
    public List browseAccountingCenterIDs(int[] aHierarchy, short depth, String currentBaseRole)
            throws EaglsException {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountingCenterDAO adapter = new AccountingCenterDAO();
            return adapter.searchForBrowseAccountingCenterIDs(conn, aHierarchy, depth, currentBaseRole);
        } catch (Exception e) {
            logger.error("can't get DB info", e);
            throw new EaglsException("error.retrieveCentralOffice");
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Static "retriveReadOnly" method will construct a AccountingCenter object from the database.
     * AccountingCenterDAA may be used for this for this purpose.
     *
     * @param accountingCenterID The unique identification fo the object to be retrived.
     * @return AccountingCenter.
     * @since Eagls Enhancement
     */
    public AccountingCenter retrieveReadOnly(String accountingCenterID,
                                                    String userID)
            throws EaglsException {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountingCenterDAO adapter = new AccountingCenterDAO();
            AccountingCenter acct =
                    adapter.retrieve(conn, accountingCenterID, userID, false, false);
            acct.setReadOnly(true);
            return acct;
        } catch (Exception e) {
            logger.error("can't get DB info", e);
            throw new EaglsException("error.retrieveAccountingCenter");
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * parses Accounting Code
     * @param accountingCenter
     * @param acctCodeStr
     * @return
     * @throws InvalidAccountingCodeError
     */
    public AccountingCode
            parseAccountingCode(AccountingCenter accountingCenter,
                                              String acctCodeStr)
            throws InvalidAccountingCodeError {
        return parseAccountingCode(accountingCenter, acctCodeStr, ACCOUNTINGCODE_VALIDATE);
    }

    /*
     * parses Accounting Code
     * @param   validate to specify if validation is required or only formatting will do. Note description
     *          will not be returned if validate is set to ACCOUNTINGCODE_NO_VALIDATE.
     */
    public AccountingCode parseAccountingCode(AccountingCenter accountingCenter,
                                              String acctCodeStr, int validate)
            throws InvalidAccountingCodeError {
        return parseAccountingCode(accountingCenter, acctCodeStr, true, validate);
    }

    /**
     * Intelligently Parses and validates a string representation of an accounting code
     * before constructing an AccountingCode object.
     * <p>
     * TODO
     * <ul>
     * <li>Need to take care of old deleted values</li>
     * </ul>
     *
     * @param   acctCodeStr string representation of an accounting code.
     * @param   activesOnly
     * @param   validate to specify if validation is required or only formatting will do. Note description
     *          will not be returned if validate is set to ACCOUNTINGCODE_NO_VALIDATE.
     * @return  the AccountingCode object referenced by acctCodeStr.
     * @exception   InvalidAccountingCodeError  the accounting code string is invalid.
     */
    public AccountingCode parseAccountingCode(AccountingCenter accountingCenter,
                                              String acctCodeStr,
                                              boolean activesOnly, int validate)
            throws InvalidAccountingCodeError {
        // Locate any slashes in the accounting code string...
        ArrayList errors = new ArrayList();
        if (acctCodeStr == null) {
            throw new InvalidAccountingCodeError("Invalid Accounting Code: " + acctCodeStr,
                    (short) 1, errors);
        }
        int index = acctCodeStr.indexOf('/');
        if (index < 0) {   // No slashes found, parse without slashes
            return parseAccountingCode(accountingCenter, acctCodeStr,
                    false, activesOnly, validate);
        } else {
            return parseAccountingCode(accountingCenter, acctCodeStr,
                    true, activesOnly, validate);
        }

    }

    /**
     * validates Segment Value
     * @param acsd
     * @param acctCentralID
     * @param value
     * @param activesOnly
     * @return
     */
    public AccountingCodeSegment validateSegmentValue(AccountingCodeSegmentDescription acsd,
                                                      String acctCentralID,
                                                      String value,
                                                      boolean activesOnly) {
        return validateSegmentValue(acsd, acctCentralID,
                value, activesOnly, AccountingCenter.ACCOUNTINGCODE_VALIDATE);
    }

    /**
     * Validates the given value against this segments list of valid values.
     * If activesOnly is true, the segment will be validated against only
     * active values.  If it is false, an inactive match will return success.
     *
     * <li>Note the Value entered might be an 'Old deleted value' due the
     * prior concept of deleting the values instead of deActivating them.
     * hence if user has specifically called for 'activeOnly' as FALSE the
     * function will return a newly generated AccountingCodeSegment with "" description
     * and active status as false.<li>
     *
     * @param   value   the segment value to validate.
     * @param   activesOnly validate against active valid values only if true.
     * @param   validate so just making a validation call to database or just check for length.
     * of validating SegmentValue for large number of values. As normally a AccountingCodeSegmentDescription object does not retain the referance of AccountingCenter
     * object. The referance id required here as a direct database check up is needed to be done.!!!
     *
     * @return  an AccountingCodeSegment that matches the given value; null if
     *          there is no match or validation fails.
     */
    public static AccountingCodeSegment validateSegmentValue(AccountingCodeSegmentDescription acsd,
                                                             String acctCentralID,
                                                             String value,
                                                             boolean activesOnly,
                                                             int validate) {
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
            if (acsd.isRequired()) {
                return null;
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
        Connection conn = null;
        AccountingCodeSegment acs = null;
        try {
            conn = getPooledConnection();
            AccountingCenterDAO adapter = new AccountingCenterDAO();
            acs = adapter.
                    fetchAccountingCodeSegment(conn, acctCentralID,
                            acsd.getName(), value);
        } catch (SQLException e) {
            logger.error("can't get DB info", e);
            throw new IllegalArgumentException(e.getMessage());
//            throw new EaglsException("error.AccountingCodeSegment");
        } finally {
            closeConnection(conn);
        }
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
     * Parses and may or may not validates a string representation of an accounting code
     * before constructing an AccountingCode object.
     * <p>
     * TODO
     * <ul>
     * <li> Need to take care of old deleted values.</li>
     * <li> old deleted values will be returned as passed as
     *      new AccountingCodeSegment object with "" description
     *      and active status as false.</li>
     * <li> AccountingCode String may not be null.</li>
     * <li> AccountingCode String may not be null.</li>
     * </ul>
     *
     * @param   acctCodeStr string representation of an accounting code.
     * @param   withSlashes are segments separated by slashes?
     * @param   activesOnly validate against active valid segments only?
     * @param   validate to specify if validation is required or only formatting will do. Note description
     *          will not be returned if validate is set to ACCOUNTINGCODE_NO_VALIDATE.
     * @return  the AccountingCode object referenced by acctCodeStr.
     * @exception   InvalidAccountingCodeError  the accounting code string is invalid.
     */
    public AccountingCode parseAccountingCode(AccountingCenter accountingCenter,
                                                     String acctCodeStr, boolean withSlashes,
                                                     boolean activesOnly, int validate)
            throws InvalidAccountingCodeError {
        ArrayList errors = new ArrayList();

        if (acctCodeStr == null) {
            if (activesOnly) {
                throw new InvalidAccountingCodeError("Invalid Accounting Code: accounting code was empty",
                        (short) 1, errors);
            } else {
                return new AccountingCode(new AccountingCodeSegment[]{new AccountingCodeSegment("", "", false)});
            }
        }

        StringTokenizer validTokens = new StringTokenizer(acctCodeStr, "/", false);
        int count = validTokens.countTokens();
        String[] tokens = new String[count];

        for (int i = 0; i < count; i++) {
            tokens[i] = validTokens.nextToken();
        }//end for loop

        int numSegments = accountingCenter.getNoOfAccountingCodeSegments();
        AccountingCodeSegment[] segments = new AccountingCodeSegment[numSegments];
        AccountingCodeSegment acs = null;
        AccountingCodeSegmentDescription acsd = null;
        int curPosition = 0;
        int length = 0;
        String segment = null;
        acctCodeStr = acctCodeStr.trim();

        if (((!withSlashes && accountingCenter.getSegmentsTotalLength() != acctCodeStr.length()) ||
                (withSlashes && (accountingCenter.getSegmentsTotalLength() + accountingCenter.getAccountingCodeSegmentDescriptions().length - 1) != acctCodeStr.length())) && (!activesOnly)) {
            // this condition is executed only if total segment length is not equal (both more or less) to AccountingCode string passed.
            // and if activeOnly is false. This will return a AccountingCode as a Single segment (No 'Slashes' possible for formatting.)
            // Due to less than also comming in the above condition, OLD DELETED values which were comming up with slashes also will be shown a single segment only.
            return new AccountingCode(new AccountingCodeSegment[]{new AccountingCodeSegment(acctCodeStr, "", false)});/// move out of process
        }

        if (!withSlashes) {
            for (short i = 0; i < numSegments; i++) {
                try {
                    //Get nextColumn segment description
                    acsd = accountingCenter.getAccountingCodeSegmentDescription(i);

                    // Find segment length and read that many characters
                    // from acctCodeStr
                    length = acsd.getLength();

                    try {
                        segment = (i == (numSegments - 1)) ? acctCodeStr.substring(curPosition) : acctCodeStr.substring(curPosition, curPosition + length);
                        segment = accountingCenter.parseSpace(segment, '#', length);
                    }//end try
                    catch (StringIndexOutOfBoundsException e) {
                        // Reached the end of the string, capture what's left.
                        if (curPosition < acctCodeStr.length()) {
                            // There's a segment fragment left
                            String fragment = acctCodeStr.substring(curPosition);
                            errors.add(new SegmentError(i, acsd, fragment));
                        } else {
                            errors.add(new SegmentError(i, acsd, ""));
                        }
                        segments[i] = null;
                        continue;
                    }//end catch

                    acs = validateSegmentValue(acsd, accountingCenter.getAccountingCenterID(),
                            segment, activesOnly, validate);
                    if (acs == null) {
                        errors.add(new SegmentError(i, acsd, acctCodeStr));
                    }

                    // Add valid value to collection
                    segments[i] = acs;
                }//end try
                finally {
                    // increment current position to nextColumn segment
                    curPosition += (withSlashes) ? length + 1 : length;
                }//end finally
            }//end for loop
        }//end if
        else {
            for (short i = 0; ((i < count) && (i < numSegments)); i++) {

                //Get  nextColumn segment description
                acsd = accountingCenter.getAccountingCodeSegmentDescription(i);

                //Get nextColumn token
                segment = tokens[i];

                acs = validateSegmentValue(acsd, accountingCenter.getAccountingCenterID(),
                        segment, activesOnly, validate);
                if (acs == null) {
                    errors.add(new SegmentError(i, acsd, segment));
                }

                // Add valid value to collection
                segments[i] = acs;

            }//end for loop
        }//end else

        // If there were any errors, throw exception.
        if (errors.size() > 0) {
            //throw new InvalidAccountingCodeError("Invalid Accounting Code: "+acctCodeStr,
            //                                        (short)1, errors);
            throw new InvalidAccountingCodeError("Invalid Accounting Code: " + acctCodeStr, "The Master Accounting Code field contains incomplete or invalid information.",
                    (short) 1, errors);
        }

        // If there were no errors, construct AccountingCode object.
        return new AccountingCode(segments);
    }//end method

    /**
     * Parses and validates a string representation of an accounting code
     * before constructing an AccountingCode object.
     * <p>
     * TODO
     * <ul>
     * <li> Need to take care of old deleted values.</li>
     * <li> old deleted values will be returned as passed as
     *      new AccountingCodeSegment object with "" description
     *      and active status as false.</li>
     * <li> AccountingCode String may not be null.</li>
     * <li> AccountingCode String may not be null.</li>
     * </ul>
     *
     * @param   acctCodeStr string representation of an accounting code.
     * @param   withSlashes are segments separated by slashes?
     * @param   activesOnly validate against active valid segments only?
     * @return  the AccountingCode object referenced by acctCodeStr.
     * @exception   InvalidAccountingCodeError  the accounting code string is invalid.
     */
    public AccountingCode parseAccountingCode(AccountingCenter accountingCenter,
                                                     String acctCodeStr,
                                                     boolean withSlashes,
                                                     boolean activesOnly)
            throws InvalidAccountingCodeError {
        return parseAccountingCode(accountingCenter,
                acctCodeStr, withSlashes, activesOnly,
                accountingCenter.ACCOUNTINGCODE_VALIDATE);

    }

    /**
     * Static "retrive" method will construct a AccountingCenter object from the database.
     * AccountingCenterDAA may be used for this for this purpose.
     *
     * @param accountingCenterID The unique identification fo the object to be retrived.
     * @return AccountingCenter.
     * @exception EaglsException If method fails to retrive the ASccountingCenter object
     * @since Eagls Enhancement
     * @see AccountingCenterDAO
     */
    public static AccountingCenter retrieve(String accountingCenterID, String userID) throws EaglsException {

        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountingCenterDAO adapter = new AccountingCenterDAO();//transport);
            AccountingCenter acct = adapter.retrieve(conn, accountingCenterID, userID, true, false);// validate = true, forSetup = false
            acct.setReadOnly(false);
            return acct;
        } catch (Exception e) {
            logger.error("can't get DB info", e);
            throw new EaglsException("error.retrieveAccountingCenter");
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Static "retrive" method will construct a AccountingCenter object from the database.
     * AccountingCenterDAA may be used for this for this purpose.
     * this method bypasses verfication on the hierarchy.
     *
     * @param accountingCenterID The unique identification fo the object to be retrived.
     * @return AccountingCenter
     * @exception EaglsException If method fails to retrive the AccountingCenter object
     * @since Eagls Enhancement
     * @see AccountingCenterDAO
     */
    public static AccountingCenter retrieveAopc(String accountingCenterID, String userID, HierarchyDTO[] currentHierarchy)
            throws EaglsException {

        Connection conn = null;
        try {
            conn = getPooledConnection();
            AccountingCenterDAO adapter = new AccountingCenterDAO();
            AccountingCenter acct = adapter.retrieve(conn, accountingCenterID, userID, false, false);// validate = false, forSetup = false
            acct.setReadOnly(false);

            // This validation below being done only for AOPC as specified in scope change 324.
            if (validateAOPCAccess(getCurrentShortHierarchy(currentHierarchy), getCurrentShortHierarchy(acct.getHierarchy())))
                return acct;
            else
                throw new EaglsException("The user " + userID.toUpperCase() +
                        " does not have access to the accounting center " + accountingCenterID);
        } catch (Exception e) {
            logger.error("can't get DB info", e);
            throw new EaglsException("error.retrieveAccountingCenter");
        } finally {
            closeConnection(conn);
        }
    }

    // method to validate an AOPC's access to a certain AccountingCenter
    // while doing Account/MCVAccount/CentralAccount setup/maint.
    // The AOPC has access to the AC if the AC is below the user,
    // or at the level of the user, at a level above the user in the same hierarchy tree
    // REFER PCR324 FOR MORE INFORMATION!!!!
    private static boolean validateAOPCAccess(int[] userHierarchy, int[] acHierarchy) {

        boolean isBelow = true;
        boolean isAbove = true;

        // checking contents of both the arrays
        // checking if the accounting center is at or below the user's hierarchy
        for (int i = 0; i < HierarchyDisplay.NUMBER_OF_SEGMENTS && userHierarchy[i] != 0; i++)
            if (userHierarchy[i] != acHierarchy[i]) {
                isBelow = false;
                break;
            }

        // checking if AC is at or above the user in the same tree (except HL0)
        // since ACs cannot be at HL0
        for (int j = 1; j < HierarchyDisplay.NUMBER_OF_SEGMENTS && acHierarchy[j] != 0; j++)
            if (acHierarchy[j] != userHierarchy[j]) {
                isAbove = false;
                break;
            }

        return (isBelow || isAbove);
    }

    public static final short ACCOUNTINGCODE_VALIDATE = 4;

    /**
     * To get back a perticular AccountCodeSegment
     * @param value
     * @return AccountingCodeSegment validSegmentValue
     * @exception IllegalArgumentException  is thrown if there
     *            is no AccountingCodeSegment corrosponding to the value inputted.

     */
    public AccountingCodeSegment getAccountingCodeSegment(
            AccountingCodeSegmentDescription acsd,
            String acctCentralID,
            String value) throws EaglsException {
        ArrayList accountingCodeSegmentList =
                acsd.getAccountingCodeSegmentList();
        Iterator it = accountingCodeSegmentList.iterator();
        AccountingCodeSegment acs = null;
        if (!acsd.isValidValuesLoaded()) {///all values have not yet been loaded.
            while (it.hasNext()) {
                acs = (AccountingCodeSegment) it.next();
                if (acs.getValue().equals(value)) {
                    return acs;
                }
            }//first try to load from individual vector.

            Connection conn = null;
            try {
                conn = getPooledConnection();
                AccountingCenterDAO adapter = new AccountingCenterDAO();
                acs = adapter.fetchAccountingCodeSegment(conn, acctCentralID,
                        acsd.getName(), value);
            } catch (Exception e) {
                logger.error("can't get DB info", e);
                throw new EaglsException("error.fetchAccountingCodeSegment");
            } finally {
                closeConnection(conn);
            }
            if (acs != null) {
//                accountingCodeSegmentList.add(acs);
                acsd.setIndividualFetchEver(true);
                return acs;
            }

        }
        else {
            while (it.hasNext()) {
                acs = (AccountingCodeSegment) it.next();
                if (acs.getValue().equals(value)) {
                    return acs;
                }
            }
        }
        throw new IllegalArgumentException("Value X" + value +
                " is not listed with the Segment" +
                acsd.getName() + " " + acsd.getDescription());
    }

    /**
     * searchs Accounting Code Segments
     * @param accountingCenterID
     * @param browseCriteria
     * @param desc
     * @return
     * @throws EaglsDAOError
     */
    public ValueListIterator searchAccountingCodeSegments(
            String accountingCenterID,
            String browseCriteria,
            AccountingCodeSegmentDescription desc)
            throws EaglsDAOError {
        ValueListSelector generator =
                new SearchAccountingCodeSegments(accountingCenterID, browseCriteria, desc);
        return new ValueListHandler(generator);
    }

    private class SearchAccountingCodeSegments
            extends ValueListSelector {
        private String accountingCenterID;
        private String browseCriteria;
        private AccountingCodeSegmentDescription desc;
        private AccountingCenterDAO accountingCenterDAO;

        public SearchAccountingCodeSegments(String accountingCenterID,
                                            String browseCriteria,
                                            AccountingCodeSegmentDescription desc) {
            this.accountingCenterID = accountingCenterID;
            this.browseCriteria = browseCriteria;
            this.desc = desc;
            accountingCenterDAO = new AccountingCenterDAO();
        }

        public Collection select(int firstRecord, int numRecords) throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountingCenterDAO.
                        searchAccountingCodeSegments(con, accountingCenterID,
                        browseCriteria,
                        desc, firstRecord, numRecords);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchAccountingCodeSegments.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

        public int count() throws EaglsDAOError {
            Connection con = null;
            try {
                con = getPooledConnection();
                return accountingCenterDAO.
                        countAccountingCodeSegments(con, accountingCenterID,
                                browseCriteria, desc);
            } catch (SQLException e) {
                throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
            } finally {
                closeConnection(con);
            }
        }

    }

}
