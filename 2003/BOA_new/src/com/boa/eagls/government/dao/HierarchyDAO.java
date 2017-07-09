/**
 * HierarchyDAO
 */
package com.boa.eagls.government.dao;

import java.util.*;

import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.util.*;
import org.apache.log4j.*;

/**
 * A class that provide all database related function for Hierarchy management .
 * @version 1.0
 * @invariant $none
 */
public class HierarchyDAO extends DAOBase
{
    private static final String NEWLINECHARACTER = "\n";
    private static final Logger LOGGER =
            Logger.getLogger("com.boa.eagls.government.dao.HierarchyDAO.class");

    /**
     * Creates new HierarchyDAO
     * @param java.sql.Connection A connection object, which will be used to execute database operations in HierarchyDAO
     * @exception $none
     * @pre $none
     * @post $none
     */
    public HierarchyDAO(java.sql.Connection connection)
    {
        LOGGER
                .debug("Entering HierarchyDAO(java.sql.Connection connection) constructor of HierarchyDAO class, connction="
                + connection);
        setConnection(connection);
        LOGGER
                .debug("Exiting HierarchyDAO(java.sql.Connection connection) constructor of HierarchyDAO class");
    }

    /**
     * @param userID
     * @throws NBError
     * @throws NBWarning
     * @pre $none
     * @post $none
     */
    public void compareHierarchy(String userID) throws NBError, NBWarning
    {
        LOGGER
                .debug("Entering compareHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER
                .debug("Exiting compareHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    // ----- USER HIERARCHY -----

    /**
     * Loads a UserHierarchy object from persistent store.  This method should
     * only be called by UserHierarchys <code>load()</code> method.
     * @param   userID  the ID of the user the hierarchy information belongs to
     * @param   roleName    the name of the role the hierarchy is under
     * @param   aHierarchy  an array of Hierarchy levels.  The array must have the max
     * @param userHierarchy HierarchyDTO type object contains the information about the User Hierarchy to load
     * @throws NBError
     * @pre $none
     * @post $none
     */
    public void loadUserHierarchy(String userID, String roleName,
                                  HierarchyDTO aHierarchy[],
                                  HierarchyDTO userHierarchy) throws NBError
    {
        LOGGER
                .debug("Entering loadUserHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"roleName\" , value = " + roleName);
        LOGGER.debug("Param name=\"aHierarchy\" , value = " + aHierarchy);
        LOGGER.debug("Param name=\"userHierarchy\" , value = "
                + userHierarchy);
        LOGGER
                .debug("Exiting loadUserHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Creates a database entry for a new UserHierarchy object.  This
     * method should only be used by UserHierarchys <code>save()</code> method.
     * @param userHierarchy
     * @throws NBError
     * @throws NBException
     * @pre $none
     * @post $none
     */
    public void createUserHierarchy(HierarchyDTO userHierarchy)
            throws NBError, NBException
    {
        LOGGER
                .debug("Entering createUserHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userHierarchy\" , value = "
                + userHierarchy);
        LOGGER
                .debug("Exiting createUserHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Constructs a call to a database stored procedure that will delete
     * a given user hierarchy from the system. A valid user ID and role must
     * be supplied along with the hierarchy to be deleted.
     * @param	UserID	        the user whos hierarchy will be deleted
     * @param	RoleName	the role the deleted hierarchy resides
     * @param	Hierarchy	HierarchyDTO type object which contains the information about the hierarchy to delete
     * @throws	NBError	stored procedure returns an error; stored procedures error message stored in exception
     * @throws	NBException	stored procedure returns an error; stored procedures error message stored in exception
     * @pre $none
     * @post $none
     */
    public void deleteUserHierarchy(String UserID, String RoleName,
                                    HierarchyDTO[] Hierarchy) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering deleteUserHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"UserID\" , value = " + UserID);
        LOGGER.debug("Param name=\"RoleName\" , value = " + RoleName);
        LOGGER.debug("Param name=\"Hierarchy\" , value = " + Hierarchy);
        LOGGER
                .debug("Exiting deleteUserHierarchy method of HierarchyDAO class.");
    }

    /**
     * Constructs a call to a database stored procedure that will save
     * a given user hierarchy to the system. A valid HierarchyDTO must be supplied along with the hierarchy to be saved.
     * @param	userHierarchy	HierarchyDTO type object which contains the information about the hierarchy to saved
     * @throws	NBException	stored procedure returns an error; stored procedures error message stored in exception
     * @pre $none
     * @post $none
     */
    public void saveUserHierarchy(HierarchyDTO userHierarchy) throws NBError
    {
        LOGGER
                .debug("Entering saveUserHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userHierarchy\" , value = "
                + userHierarchy);
        LOGGER
                .debug("Exiting saveUserHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Constructs a call to a database stored procedure that will validate
     * a given user hierarchy to the system. A valid HierarchyDTO and User ID must be supplied to validate the hierarchy.
     * @param	hierarchy	HierarchyDTO type object which contains the information about the hierarchy to be validated
     * @param userID
     * @throws	NBException	stored procedure returns an error; stored procedures error message stored in exception
     * @return String This routine returns a "true" if a Hierarchy exists, "false" otherwise
     * @pre $none
     * @post $none
     */
    public String validateHierarchy(String userID, HierarchyDTO[] hierarchy)
            throws NBException
    {
        LOGGER
                .debug("Entering validateHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"userID\" , value = " + userID);
        LOGGER.debug("Param name=\"hierarchy\" , value = " + hierarchy);
        LOGGER
                .debug("Exiting validateHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
        return "";
    }

    /**
     * Constructs a call to a database stored procedure that will change
     * a users default hierarchy to the given hierarchy.  The given
     * hierarchy must already exist and be assigned to the user.  This method only updates the database as to the new default
     * hierarchy.  The caller must ensure the associated business objects are updated to retain consistency with the database.
     * @param UserID String, contains the user whos default hierarchy is changing
     * @param RoleName String, contains the name of the role the hierarchy is assigned under
     * @param hierarchy Instance of HierarchyDTO which contains a valid hierarchy to designate as the users default
     * @throws NBError Current user not found or stored procedure failed
     * @throws NBException Current user not found or stored procedure failed
     * @pre $none
     * @post $none
     */
    public void setDefaultUserHierarchy(String UserID, String RoleName,
                                        HierarchyDTO[] hierarchy) throws NBError,
            NBException
    {
        LOGGER
                .debug("Entering setDefaultUserHierarchy method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"UserID\" , value = " + UserID);
        LOGGER.debug("Param name=\"RoleName\" , value = " + RoleName);
        LOGGER
                .debug("Exiting setDefaultUserHierarchy method of HierarchyDAO class.");

        // NOT IMPLEMENTED.
    }

    /**
     * Constructs a call to a database query that will get a hierarchy to the given Hierarchy Number.
     * @param HierarchyNumber
     * @return
     * @throws NBError
     * @throws Exception
     * @pre $none
     * @post $none
     */
    public String[] getHierarchy(String HierarchyNumber)
            throws NBError, Exception
    {
        String retHier[] = new String[HierarchyDisplay.NUMBER_OF_SEGMENTS];

        if (HierarchyNumber != null)
        {
            LOGGER
                    .debug("Entering getHierarchy method of HierarchyDAO class.");
            LOGGER.debug("Param name=\"HierarchyNumber\" , value = "
                    + HierarchyNumber);
            StringBuffer sqlBuf =
                    new StringBuffer("SELECT HL0, HL1, HL2, HL3, HL4, HL5, HL6, HL7, HL8"
                    + NEWLINECHARACTER);

            sqlBuf.append("FROM AGENCY_HL WHERE" + NEWLINECHARACTER);
            sqlBuf.append("HIERARCHY_NBR = " + HierarchyNumber);
            java.util.Vector rs = null;

            // TODO callQuery temporarily throws NoDataFoundException when zero rows are returned.
            try
            {
                rs = executeDBQuery(sqlBuf.toString(), false);
            }
            catch (NoDataFoundException e)
            {
                LOGGER
                        .error("Caught NoDataFoundException in  setDefaultUserHierarchy method of HierarchyDAO class.",
                                e);
                throw new NBError("No Hierarchy number exist for the input data ");
            }
            if (rs.size() == 0)
            {
                LOGGER
                        .debug("Throwing NBError in  setDefaultUserHierarchy method of HierarchyDAO class Reason: No Hierarchy number exist for the input data.");
                throw new NBError("No Hierarchy number exist for the input data ");
            }

            // Place resuts in a dynamic list before copying them into a static
            // array when a size is known
            Hashtable htRowData = null;

            for (int rsCount = 0; rsCount < rs.size(); rsCount++)
            {
                htRowData = (Hashtable) rs.get(rsCount);
                for (int i = 0; i < HierarchyDisplay.NUMBER_OF_SEGMENTS; i++)
                {
                    retHier[i] = (String) htRowData.get(new Integer(i + 1));
                }
            }
            LOGGER
                    .debug("Exiting getHierarchy method of HierarchyDAO class. Return value is"
                    + retHier);
        }
        else
        {
            LOGGER
                    .debug("Throwing NBError in getHierarchy method of HierarchyDAO class. Reason: HierarchyNumber found null in method getHierarchy");
            throw new NBError("HierarchyNumber found null in method getHierarchy");
        }
        return retHier;
    }

    /**
     * Constructs a call to a database query that will get a hierarchy number to the given browse array.
     * @param hBrowse
     * @return String containing the Hierarchy Number
     * @throws NBError
     * @throws Exception
     * @pre $none
     * @post $none
     */
    public String getHierarchyNumber(int[] hBrowse) throws NBError, Exception
    {
        LOGGER
                .debug("Entering getHierarchyNumber method of HierarchyDAO class.");
        LOGGER.debug("Param name=\"hBrowse\" , value = " + hBrowse);
        StringBuffer sqlBuf;    // the entire sql SELECT

        sqlBuf = new StringBuffer("SELECT ah.HIERARCHY_NBR"
                + NEWLINECHARACTER);
        sqlBuf.append("  FROM AGENCY_HL ah" + NEWLINECHARACTER);
        sqlBuf.append(" WHERE ah.HL" + 0 + " = " + hBrowse[0]
                + NEWLINECHARACTER);
        for (int j = 1; j < 9; j++)
        {
            sqlBuf.append("   AND ah.HL" + j + " = " + hBrowse[j]
                    + NEWLINECHARACTER);
        }
        Vector rs;

        // TODO callQuery temporarily throws NoDataFoundException when zero rows are returned.
        try
        {
            rs = executeDBQuery(sqlBuf.toString(), false);
        }
        catch (NoDataFoundException e)
        {
            LOGGER
                    .error("Caught NoDataFoundException in getHierarchyNumber method of HierarchyDAO class.",
                            e);
            throw new NBError("No Hierarchy number exist for the input data ");
        }
        if (rs.size() == 0)
        {
            LOGGER
                    .debug("Throwing NBError in getHierarchyNumber method of HierarchyDAO class. Reason: No Hierarchy number exist for the input data");
            throw new NBError("No Hierarchy number exist for the input data ");
        }
        Hashtable htRowData = (Hashtable) rs.get(0);

        LOGGER
                .debug("Exiting getHierarchyNumber method of HierarchyDAO class. Return value="
                + (String) htRowData.get(new Integer(1)));
        return (String) htRowData.get(new Integer(1));
    }

}
