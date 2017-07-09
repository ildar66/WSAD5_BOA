/**
 * BrowseDAO
 */
package com.boa.eagls.government.dao;

import java.util.*;

import org.apache.log4j.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;

/**
 * <p>Description: BrowseDAA provides a database interface for browsing functionality in
 * EAGLS.  The EAGLS user interface provides several aids to help the user
 * browse, find and select an item from a large, yet logically-organized,
 * set of similar items.  This Data Access Adapter performs the necessary
 * SQL queries to the EAGLS database to populate the browsing aids with information.
 * Currently, Browse DAA supports:
 * 1. Hierarchy Browse 2. Master Accounting Code Browse </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: E-Dev Technologies</p>
 * @version 1.0
 * @invariant $none
 */
public class BrowseDAO extends DAOBase
{
    private static final String NEW_LINE_CHARACTER = "\n";
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.BrowseDAO.class");

    /**
     * Creates new BrowseDAO
     * @param java.sql.Connection A connection object, which will be used to execute database in AccountDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public BrowseDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering BrowseDAO(java.sql.Connection connection) constructor of BrowseDAO class, connction="
                + connection);
        setConnection(connection);
        LOGGER
                .debug("Exiting BrowseDAO(java.sql.Connection connection) constructor of BrowseDAO class.");
    }

    /**
     * Returns a list of Hierarchy objects, at a given level, that the current
     * user has access to under the users current role and hierarchy.
     * Dependencies:  user is logged on and has its current hierarchy saved
     * in the state layer.  If a hierarchy is not saved in the state layer,
     * the users base role will be checked to see if it is GCSU or AH.  An
     * AH will cause a failure, while GCSU will assume a hierarchy of all zeroes.
     * If the given hierarchy does not match the users hierarchy up to the browsing
     * level, an access error will be recorded and an exception thrown.
     * @param hBrowse the hierarchy level the user is trying to browse.  This
     * must contain a valid, nine-level hierarchy.  browseHierarchy
     * assumes the first level encountered with a value of zero is
     * the desired browsing level.  The remaining hierarchy levels will be ignored.
     * @param boolean countFlag  trigger for LargeResultSet search
     * @param   int     maxSize    the threshold value for LargeResultSet search
     * @param currentHierarchy Hierarchy of Current user which is logged in
     * @param currentBaseRole  Base Role of the current user which is logged in
     * @return HierarchyDTO
     * @throws NBException
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     * @throws NBRuntimeError
     * @pre $none
     * @post $none
     */
    public HierarchyDTO[] browseHierarchy(int[] hBrowse, boolean countFlag,
                                          int maxSize,
                                          HierarchyDTO[] currentHierarchy,
                                          String currentBaseRole) throws NBException,
            EAGLSException, DataConnException,
            NBException, NBRuntimeError
    {
        LOGGER.debug("Entering browseHierarchy method of BrowseDAO class.");
        LOGGER.debug("Param name=\"hBrowse\" , value = " + hBrowse);
        LOGGER.debug("Param name=\"countFlag\" , value = " + countFlag);
        LOGGER.debug("Param name=\"maxSize\" , value = " + maxSize);
        LOGGER.debug("Param name=\"currentHierarchy\" , value = "
                + currentHierarchy);
        LOGGER.debug("Param name=\"currentBaseRole\" , value = "
                + currentBaseRole);
        short i = 0;
        HierarchyDTO[] hResults = null;

        // if (hBrowse.length < 1) {
        // throw new NBError("sads");
        // }
        // if(hBrowse==null||currentBaseRole==null)
        // {
        // throw new NBError("hBrowse or currentBaseRole were null on fuction browseHierarchy");
        // }
        if (hBrowse == null || hBrowse.length < 1 || currentBaseRole == null
                || currentHierarchy == null || currentHierarchy.length < 1)
        {
            LOGGER
                    .debug("Throwing NBRuntimeError Exception in browseHierarchy method of BrowseDAO class, Reason: hBrowse or CurrentBaseRole is null in fuction browse Hierarchy");
            throw new NBRuntimeError("hBrowse or CurrentBaseRole is null in fuction browse Hierarchy");
        }

        // if (hBrowse != null && hBrowse.length > 0 && currentBaseRole != null) {
        StringBuffer sqlBuf;
        StringBuffer sqlAndBuf;
        int[] hAccess;
        String currBaseRole = currentBaseRole;

        // Get users current hierarchy from the state layer
        if (currentHierarchy == null)
        {
            hAccess = null;
        }
        else
        {
            hAccess = new int[currentHierarchy.length];
            for (int j = 0; j < currentHierarchy.length; j++)
            {
                if (currentHierarchy[j] != null)
                {
                    hAccess[j] = currentHierarchy[j].getNumber();
                }
            }
        }
        if (hAccess == null)
        {
            if (currBaseRole.equals("GCSU"))
            {
                hAccess = new int[9];
                for (i = 0; i < 9; i++)
                {
                    hAccess[i] = 0;
                }
            }
            else
            {
                LOGGER
                        .debug("Throwing NBError Exception in browseHierarchy method of BrowseDAO class, Reason: User is not a GCSU and has no current hierarchy");
                throw new NBError("SYS_E0003:Browse::User is not a GCSU and has no current hierarchy");
            }
        }
        StringBuffer logBuf = new StringBuffer("hAccess = >{");

        for (i = 0; i < hAccess.length; i++)
        {
            logBuf.append(hAccess[i] + ".");
        }
        logBuf.append("}<");
        LOGGER.debug(logBuf.toString());
        sqlAndBuf = new StringBuffer();
        if (currBaseRole.equals("GCSU"))
        {
            sqlAndBuf.append("   AND ah.HL0 = " + hBrowse[0]
                    + NEW_LINE_CHARACTER);
        }
        else if (hAccess.length > 0 && hBrowse.length > 0
                && (hAccess[0] == hBrowse[0]))
        {
            sqlAndBuf.append("   AND ah.HL0 = " + hAccess[0]
                    + NEW_LINE_CHARACTER);
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError Exception in browseHierarchy method of BrowseDAO class, Reason: User's program numbers do not match");
            throw new NBError("SYS_E0003:BrowseHierarchy::User's program numbers do not match");
        }

        // Loop through levels in hBrowse while current level is not zero. At each level the
        // search hierarchy must equal its counterpart in the access hierarchy unless that
        // counterpart is zero.
        // if (hBrowse.length >= 9) {
        for (i = 1; i < hBrowse.length && hBrowse[i] > 0; i++)
        {
            if ((hBrowse.length > i && hAccess.length > i)
                    && (hBrowse[i] == hAccess[i] || hAccess[i] == 0))
            {
                sqlAndBuf.append("   AND ah.HL" + i + " = " + hBrowse[i]
                        + NEW_LINE_CHARACTER);
            }
            else
            {
                LOGGER
                        .debug("Throwing NBError Exception in browseHierarchy method of BrowseDAO class, Reason: Search hierarchy did not fall within user's access hierarchy");
                throw new NBError("SYS_E0003:BrowseHierarchy::Search hierarchy did not fall within user's access hierarchy");
            }
        }

        // }
        // Did we reach the end of the loop?
        if (i == 9)
        {
            LOGGER
                    .debug("Throwing NBError Exception in browseHierarchy method of BrowseDAO class, Reason: Hierarchy level overrun");
            throw new NBError("SYS_E0003:BrowseHierarchy::Hierarchy level overrun");
        }
        short browseLevel = i;

        sqlBuf =
                new StringBuffer("SELECT ah.HL" + browseLevel
                + ", ah.HIERARCHY_DESCRIPTION, ah.AGENCY_NAME"
                + NEW_LINE_CHARACTER);
        sqlBuf.append("  FROM gsa.AGENCY_HL ah" + NEW_LINE_CHARACTER);
        if (hAccess.length > i && hAccess[i] != 0)
        {
            sqlBuf.append(" WHERE ah.HL" + i + " = " + hAccess[i]
                    + NEW_LINE_CHARACTER);
        }
        else
        {
            sqlBuf.append(" WHERE ah.HL" + i + " > 0" + NEW_LINE_CHARACTER);
        }
        sqlBuf.append(sqlAndBuf.toString());

        // Loop thru rest of levels in hBrowse, zeroing them out along the way
        for (i++; i < 9; i++)
        {
            sqlBuf.append("   AND ah.HL" + i + " = 0" + NEW_LINE_CHARACTER);
        }

        // Sort the results by agency name
        sqlBuf.append("ORDER BY ah.AGENCY_NAME" + NEW_LINE_CHARACTER);
        Vector rs = null;

        // TODO callQuery temporarily throws NoDataFoundException when zero rows are returned.
        try
        {

            // rs =  callQuery(sqlBuf.toString(), countFlag, maxSize);
            rs = executeDBQuery(sqlBuf.toString(), false);
            Vector vHResults = new Vector(10, 10);
            Hashtable rowData = null;

            for (int rsCount = 0; rsCount < rs.size(); rsCount++)
            {
                rowData = (Hashtable) rs.get(rsCount);
                HierarchyDTO hResult = new HierarchyDTO();

                hResult.setLevel(browseLevel);
                if (rowData.get(new Integer(1)) != null)
                {
                    try
                    {
                        hResult
                                .setNumber(Integer
                                .parseInt((String) rowData
                                .get(new Integer(1))));
                    }
                    catch (NumberFormatException ex)
                    {
                        LOGGER.error("Expecting Number, found String in browseHierarchy method of BrowseDAO class.",ex);
                        throw new NBError("Expecting Number, found String, Contact Database administrator for further information.");
                    }
                }
                hResult.setDescription((String) rowData.get(new Integer(2)));
                hResult.setAgencyName((String) rowData.get(new Integer(3)));
                vHResults.addElement(hResult);
            }
            hResults = new HierarchyDTO[vHResults.size()];
            vHResults.copyInto(hResults);
        }
        catch (NoDataFoundException e)
        {
            hResults = new HierarchyDTO[0];
        }

        // Place resuts in a dynamic list before copying them into a static
        // array when a size is known
        // }
        LOGGER
                .debug("Exiting browseHierarchy method of BrowseDAO class. Return value = "
                + hResults);
        return hResults;
    }

    /**
     * Returns a list of Hierarchy objects with description, at a given level, that the current
     * user has access to under the users current role and hierarchy.
     * Dependencies:  user is logged on and has its current hierarchy saved
     * in the state layer.  If a hierarchy is not saved in the state layer,
     * the users base role will be checked to see if it is GCSU or AH.  An
     * AH will cause a failure, while GCSU will assume a hierarchy of all zeroes.
     * If the given hierarchy does not match the users hierarchy up to the browsing
     * level, an access error will be recorded and an exception thrown.
     * @param hBrowse the hierarchy level the user is trying to browse.  This
     * must contain a valid, nine-level hierarchy.  browseHierarchy
     * assumes the first level encountered with a value of zero is
     * the desired browsing level.  The remaining hierarchy levels will be ignored.
     * @param boolean countFlag  trigger for LargeResultSet search
     * @param   int     maxSize    the threshold value for LargeResultSet search
     * @param currentHierarchy Hierarchy of Current user which is logged in
     * @param currentBaseRole  Base Role of the current user which is logged in
     * @return HierarchyDTO
     * @throws NBException
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     * @throws NBRuntimeError
     * @pre $none
     * @post $none
     */
    public HierarchyDTO[] browseHierarchyWithDesc(int[] hBrowse,
                                                  boolean countFlag, int maxSize, HierarchyDTO[] currentHierarchy,
                                                  String currentBaseRole) throws NBException,
            java.sql.SQLException,
            EAGLSException, DataConnException,
            NBException
    {
        return browseHierarchyWithDesc(hBrowse, countFlag, maxSize, false,
                currentHierarchy, currentBaseRole);
    }

    /**
     * Returns a list of Hierarchy objects that an A_OPC is allowed to transfer an individualaccount into.
     * Dependencies:  user is logged on and has its current hierarchy saved
     * in the state layer.  If a hierarchy is not saved in the state layer,
     * the users base role will be checked to see if it is GCSU or AH.  An
     * AH will cause a failure, while GCSU will assume a hierarchy of all zeroes.
     * If the given hierarchy does not match the users hierarchy up to the browsing
     * level, an access error will be recorded and an exception thrown.
     * @param   boolean countFlag  trigger for LargeResultSet search
     * @param   int     maxSize    the threshold value for LargeResultSet search
     * @param	hBrowse	the hierarchy level the user is trying to browse.  This
     * must contain a valid, nine-level hierarchy.  browseHierarchy
     * assumes the first level encountered with a value of zero is
     * the desired browsing level.  The remaining hierarchy levels will be ignored.
     * @param isTransfer
     * @param currentHierarchy
     * @param currentBaseRole
     * @return	Hierarchy[]	an array of the hierarchies the user is allowed to browse
     * @throws NBException
     * @throws java.sql.SQLException
     * @throws EAGLSException
     * @throws DataConnException
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public HierarchyDTO[] browseHierarchyWithDesc(int[] hBrowse,
                                                  boolean countFlag, int maxSize, boolean isTransfer,
                                                  HierarchyDTO[] currentHierarchy,
                                                  String currentBaseRole) throws NBException,
            java.sql.SQLException,
            EAGLSException, DataConnException,
            NBException
    {
        LOGGER
                .debug("Entering browseHierarchyWithDesc method of BrowseDAO class.");
        LOGGER.debug("Param name=\"hBrowse\" , value = " + hBrowse);
        LOGGER.debug("Param name=\"countFlag\" , value = " + countFlag);
        LOGGER.debug("Param name=\"maxSize\" , value = " + maxSize);
        LOGGER.debug("Param name=\"isTransfer\" , value = " + isTransfer);
        LOGGER.debug("Param name=\"currentHierarchy\" , value = "
                + currentHierarchy);
        LOGGER.debug("Param name=\"currentBaseRole\" , value = "
                + currentBaseRole);
        LOGGER.debug("browseHierarchyWithDesc 1 = " + hBrowse.length);
        LOGGER.debug("browseHierarchyWithDesc 2 = " + countFlag);
        LOGGER.debug("browseHierarchyWithDesc 3 = " + maxSize);
        LOGGER.debug("browseHierarchyWithDesc 4 = " + isTransfer);
        LOGGER.debug("browseHierarchyWithDesc 5 = " + currentHierarchy);
        LOGGER.debug("browseHierarchyWithDesc 6 = " + currentBaseRole);
        if (hBrowse == null || currentBaseRole == null
                || currentBaseRole == "")
        {

            // return null;
            throw new NBError("hBrowse or currentBaseRole is null in method browseHierarchyWithDesc");
        }
        short i;		// counter
        StringBuffer sqlBuf;    // the entire sql SELECT
        int[] hAccess;    // defines the hierarchy this user is currently restricted to
        String currBaseRole = currentBaseRole;

        if (currentHierarchy == null)
        {
            hAccess = null;
        }
        else
        {
            hAccess = new int[currentHierarchy.length];
            for (int m = 0; m < currentHierarchy.length; m++)
            {
                if (currentHierarchy[m] != null)
                {
                    hAccess[m] = currentHierarchy[m].getNumber();
                }
            }
        }

        // Get users current hierarchy from the state layer
        if (hAccess == null)
        {

            // User has no current hierarchy; check base role is GCSU.
            if (currBaseRole.equals("GCSU"))
            {

                // GCSU - set user hierarchy to all zeros
                hAccess = new int[9];
                for (i = 0; i < 9; i++)
                {
                    hAccess[i] = 0;
                }
            }
            else
            {
                LOGGER
                        .debug("Throwing NBError in browseHierarchyWithDesc method of BrowseDAO class. Reason: User is not a GCSU and has no current hierarchy");
                throw new NBError("SYS_E0003:Browse::User is not a GCSU and has no current hierarchy");
            }
        }
        else if (currBaseRole.equals("A_OPC") && isTransfer)
        {

            // if (hAccess.length >= 9) {
            for (i = 2; i < hAccess.length; i++)
            {
                hAccess[i] = 0;
            }

            // }
        }
        StringBuffer logBuf = new StringBuffer("hAccess = >{");

        // if (hAccess.length >= 9) {
        for (i = 0; i < hAccess.length; i++)
        {
            logBuf.append(hAccess[i] + ".");
        }

        // }
        logBuf.append("}<");
        LOGGER.debug(logBuf.toString());

        // get the browse level
        for (i = 0; i < hBrowse.length; i++)
        {
            if (hBrowse[i] == 0)
            {
                break;
            }
        }
        short browseLevel = i;    // The level being browsed

        sqlBuf = new StringBuffer("SELECT ah" + browseLevel + ".HL"
                + browseLevel + "," + NEW_LINE_CHARACTER);
        for (int j = i; j > 0; j--)
        {
            sqlBuf.append("\tah" + j + ".AGENCY_NAME");
            if (j != 1)
            {
                sqlBuf.append(", " + NEW_LINE_CHARACTER);
            }
            else
            {
                sqlBuf.append(" " + NEW_LINE_CHARACTER);
            }
        }
        sqlBuf.append("  FROM " + NEW_LINE_CHARACTER);
        for (int j = 1; j < (i + 1); j++)
        {
            sqlBuf.append("\t gsa.AGENCY_HL ah" + j);
            if (j != i)
            {
                sqlBuf.append(", " + NEW_LINE_CHARACTER);
            }
            else
            {
                sqlBuf.append(" " + NEW_LINE_CHARACTER);
            }
        }
        sqlBuf.append("WHERE " + NEW_LINE_CHARACTER);

        // Construct the hierarchy condition
        // For all roles, hl0 (program number) must be specified.
        if (currBaseRole.equals("GCSU"))
        {
            sqlBuf.append("   ah" + browseLevel + ".HL0 = " + hBrowse[0]
                    + NEW_LINE_CHARACTER);
        }
        else if (hAccess.length > 0 && hBrowse.length > 0
                && (hAccess[0] == hBrowse[0]))
        {
            sqlBuf.append("   ah" + browseLevel + ".HL0 = " + hAccess[0]
                    + NEW_LINE_CHARACTER);
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in browseHierarchyWithDesc method of BrowseDAO class. Reason: User's program numbers do not match");
            throw new NBError("SYS_E0003:BrowseHierarchy::User's program numbers do not match");
        }

        // Loop through levels in hBrowse while current level is not zero. At each level the
        // search hierarchy must equal its counterpart in the access hierarchy unless that
        // counterpart is zero.
        for (int j = 1; j < browseLevel; j++)
        {
            for (i = 0; i < hBrowse.length; i++)
            {
                if (hBrowse[i] == hAccess[i] || hAccess[i] == 0 || isTransfer)
                {
                    if (i > j)
                    {
                        sqlBuf.append("   AND ah" + j + ".HL" + i + " = 0 "
                                + NEW_LINE_CHARACTER);
                    }
                    else
                    {
                        sqlBuf.append("   AND ah" + j + ".HL" + i + " = ah"
                                + browseLevel + ".HL" + i
                                + NEW_LINE_CHARACTER);
                    }
                }
                else
                {
                    throw new NBError("SYS_E0003:BrowseHierarchy::Search hierarchy did not fall within user's access hierarchy");
                }
            }
            sqlBuf.append("\t");
        }
        for (i = 1; i < hBrowse.length; i++)
        {
            if ((hBrowse.length > i && hAccess.length > i)
                    && (hBrowse[i] == hAccess[i] || hAccess[i] == 0
                    || isTransfer))
            {
                if (i != browseLevel)
                {
                    sqlBuf.append("   AND ah" + browseLevel + ".HL" + i
                            + " = " + hBrowse[i] + NEW_LINE_CHARACTER);
                }
                else
                {
                    sqlBuf.append("   AND ah" + browseLevel + ".HL" + i
                            + " > " + hBrowse[i] + NEW_LINE_CHARACTER);
                }
            }
            else
            {
                LOGGER
                        .debug("Throwing NBError in browseHierarchyWithDesc method of BrowseDAO class. Reason: Search hierarchy did not fall within user's access hierarchy");
                throw new NBError("SYS_E0003:BrowseHierarchy::Search hierarchy did not fall within user's access hierarchy");
            }
        }
        sqlBuf.append("ORDER BY ah" + browseLevel + ".AGENCY_NAME"
                + NEW_LINE_CHARACTER);
        Vector rs = null;

        // TODO callQuery temporarily throws NoDataFoundException when zero rows are returned.
        try
        {

            rs = executeDBQuery(sqlBuf.toString(), countFlag, maxSize, false);
            //rs = executeDBQuery(sqlBuf.toString(), false);
        }
        catch (NoDataFoundException e)
        {
            return new HierarchyDTO[0];
        }
        if (rs.size() == 0)
        {
            return new HierarchyDTO[0];
        }

        // Place resuts in a dynamic list before copying them into a static
        // array when a size is known
        Vector vHResults = new Vector(10, 10);
        Hashtable htRowData = null;

        for (int rsCount = 0; rsCount < rs.size(); rsCount++)
        {
            htRowData = (Hashtable) rs.get(rsCount);
            HierarchyDTO hResult = new HierarchyDTO();

            hResult.setLevel(browseLevel);
            if (htRowData.get(new Integer(1)) != null)
            {
                try
                {
                    hResult
                            .setNumber(Integer
                            .parseInt((String) htRowData
                            .get(new Integer(1))));
                }
                catch (NumberFormatException ex)
                {
                    LOGGER.error("Throwing NBError in browseHierarchyWithDesc method of BrowseDAO class. Reason: Expecting Number, found String");
                    throw new NBError("Expecting Number, found String, Please Contact database administrator for further information");
                }
            }
            hResult.setDescription((String) htRowData.get(new Integer(2)));
            hResult.setAgencyName((String) htRowData.get(new Integer(2)));
            for (int j = 0; j < browseLevel; j++)
            {
                hResult
                        .setParentAgencyName(browseLevel - j,
                                (String) htRowData
                        .get(new Integer(j + 2)));
            }
            vHResults.addElement(hResult);
        }
        HierarchyDTO[] hResults = new HierarchyDTO[vHResults.size()];

        vHResults.copyInto(hResults);
        LOGGER
                .debug("Exiting browseHierarchyWithDesc method of BrowseDAO class. Return value is "
                + hResults);
        return hResults;
    }

}
