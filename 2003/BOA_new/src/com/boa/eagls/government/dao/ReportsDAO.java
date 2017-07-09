/**
 * ReportsDAO
 */
package com.boa.eagls.government.dao;

import java.sql.*;
import java.util.*;

import org.apache.log4j.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.sql.constants.*;
import com.boa.eagls.government.util.*;

/**
 * A class that provide all database related function for Reports management .
 * @version 1.0
 * @invariant $none
 */
public class ReportsDAO extends DAOBase
{
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.ReportsDAO.class");

    /**
     * Creates new ReportsDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in ReportsDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public ReportsDAO(Connection connection)
    {
        LOGGER
                .debug("Entering ReportsDAO(java.sql.Connection connection) constructor of ReportsDAO class");
        setConnection(connection);
        LOGGER
                .debug("Exiting ReportsDAO(java.sql.Connection connection) constructor of ReportsDAO class");
    }

    /**
     * Adds a new entry to the EAGLS_CI_TRANSACTIONS table.
     * @param t An instance of CrystalTransaction contains the information about transaction to be saved
     * @param sessionUserID String containing the user ID of currenly login user
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void addTransaction(CrystalTransaction t,
                               String sessionUserID) throws NBException
    {
        LOGGER.debug("Entering  in method  addTransaction in ReportsDAO");
        LOGGER.debug("Param name=\"t\" , value = " + t);
        LOGGER.debug("Param name=\"sessionUserID\" , value = "
                + sessionUserID);
        int hierarchyNbr = 0;

        if (t.getUserRole() != null && !t.getUserRole().equals(""))
        {
            UserProfileDAO userProfileDAO =
                    new UserProfileDAO(getConnection());
            String baseRole =
                    userProfileDAO.getBaseRole(t.getUserRole());

            if (baseRole.equals("GCSU"))
            {
                hierarchyNbr = 0;
            }
            else if (baseRole.equals("AH"))
            {
                hierarchyNbr = -1;
            }
            else
            {
                HierarchyDTO[] h = t.getHierarchy();

                hierarchyNbr = resolveHierarchyNbr(h);
            }
        }
        KeyValueList kvl = new KeyValueList();

        kvl.add("iUserID", sessionUserID, SQLConstants.INPUT_PARAMETER);
        kvl.add("iTransactionType", t.getTransactionType(),
                SQLConstants.INPUT_PARAMETER);
        kvl.add("iSetupUserID", t.getUserID(), SQLConstants.INPUT_PARAMETER);
        kvl.add("iPassword", t.getPassword(), SQLConstants.INPUT_PARAMETER);
        kvl.add("iFirstName", t.getFirstName(), SQLConstants.INPUT_PARAMETER);
        kvl.add("iLastName", t.getLastName(), SQLConstants.INPUT_PARAMETER);
        kvl.add("iHierarchyNbr", "" + hierarchyNbr,
                SQLConstants.INPUT_PARAMETER);
        kvl.add("iUserRole", t.getUserRole(), SQLConstants.INPUT_PARAMETER);
        kvl.add("iNewPassword", t.getNewPassword(),
                SQLConstants.INPUT_PARAMETER);
        kvl.add("iReportID", t.getReportID(), SQLConstants.INPUT_PARAMETER);
        kvl.add("oResult", "", SQLConstants.OUTPUT_PARAMETER, new String());
            callStoredProcedure(ReportsConstants.SP_ADD_CI_TRANSACTION, kvl);
        LOGGER.debug("Exiting from method  addTransaction in ReportsDAO");
    }

    /**
     * Validates given hierarchy levels against the database and returns its associated
     * hierarchy number.  <code>h</code> is an array of nine Hierarchy objects.
     * @param h An instance of HierarchyDTO contains the information about a hierarchy level.
     * @return int Hierarchy Number
     * @throws NBException If error occured
     * @pre $none
     * @post $none
     */
    public int resolveHierarchyNbr(HierarchyDTO[] h) throws NBException
    {
        LOGGER
                .debug("Entering in method  resolveHierarchyNbr method  in ReportsDAO");
        LOGGER.debug("Param name=\"HierarchyDTO\" , value = " + h);
        if (h != null)
        {
            String SQL_GET_HIERARCHY_NBR = "SELECT HIERARCHY_NBR "
                    + "FROM   AGENCY_HL "
                    + "WHERE  HL0=? "
                    + "AND    HL1=? "
                    + "AND    HL2=? "
                    + "AND    HL3=? "
                    + "AND    HL4=? "
                    + "AND    HL5=? "
                    + "AND    HL6=? "
                    + "AND    HL7=? "
                    + "AND    HL8=? ";
            Vector rs = null;
            KeyValueList kvl = new KeyValueList();

            for (short i = 0; i < h.length; i++)
            {
                if (h[i] != null)
                {
                    kvl.add("HL" + i, "" + h[i].getNumber(),
                            SQLConstants.INPUT_PARAMETER);
                }
            }
            rs = executeDBQuery(SQL_GET_HIERARCHY_NBR, kvl, false);
            Hashtable htRowData = (Hashtable) rs.get(0);
            int result =
                    Integer
                    .parseInt((String) (htRowData
                    .get(new Integer(1))));    // rs.getValueInt(1);

            LOGGER
                    .debug("Exiting form method  resolveHierarchyNbr method  in ReportsDAO. Return value ="
                    + result);
            return result;
        }
        else
        {
            throw new NBError("HierarchyDTO found null in resolveHierarchyNbr method");
        }
    }

}
