/**
 * SearchUserService
 */
package com.boa.eagls.government.service.userprofile;

/**
 * <p>Title: SearchUserService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */

import java.util.Vector;
import java.util.Hashtable;

import com.boa.eagls.government.util.Constants;
import com.boa.eagls.government.dao.UserDAO;
import com.boa.eagls.government.dto.user.SearchUserDTO;
import com.boa.eagls.government.dto.user.UserHierarchyDTO;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.exceptions.NBException;
import com.boa.eagls.government.exceptions.SearchCriteriaNotDefined;
import com.boa.eagls.government.service.ServiceBase;
import org.apache.log4j.Logger;
import org.apache.commons.beanutils.DynaBean;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class SearchUserService extends ServiceBase
{

    // Set Logger
    private static Logger logger =
            Logger
            .getLogger(com.boa.eagls.government.service.userprofile
            .SearchUserService.class);

    /**
     * Constructor declaration
     *
     */
    public SearchUserService()
    {
        setConnection(null);
    }

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public SearchUserService(java.sql.Connection connection)
    {
        logger
                .debug("Entering SearchUserService - constructor LargeRS().....");
        setConnection(connection);
        logger
                .debug("Exiting SearchUserService - constructor LargeRS().....");
    }


    /**
     * delegate action call to UserDAO for searching user on the basis of values of input parameters
     * @param iProfileData
     * @param sessionAuthorizedUser
     * @return
     * @throws NBException
     */
    public Vector SearchUser(SearchUserDTO iProfileData,
                             Hashtable sessionAuthorizedUser, int larsSize) throws NBException
    {
        logger
                .debug("Entering SearchUserService - SearchUserDTO iProfileData, Hashtable sessionAuthorizedUser.....");
        Vector toReturn = new Vector();
        UserDAO userDAO = new UserDAO(getConnection());
        String accountNumber = iProfileData.getAccountNumber();
        String firstName = iProfileData.getFirstName();
        String lastName = iProfileData.getLastName();
        boolean countFlag = iProfileData.isCountFlag();
        String[] shierarchy = iProfileData.getHierarchy();
        short depth = Short.parseShort(iProfileData.getHierarchyDepth());
        String[] hierarchy = null;

        if (shierarchy != null)
        {
            hierarchy = new String[shierarchy.length];
            for (short j = 0; j < hierarchy.length; j++)
            {
                if (shierarchy[j].equals(""))
                {
                    hierarchy[j] = "0";
                }
                hierarchy[j] = shierarchy[j];
            }
        }
        try
        {
            if (accountNumber != null && !accountNumber.equals(""))
            {
                toReturn = userDAO.searchUserByAccountNumber(accountNumber,
                        sessionAuthorizedUser, countFlag, larsSize);
            }
            else
            {
                if ((firstName != null && !firstName.equals(""))
                        || (lastName != null && !lastName.equals("")))
                {
                    if (hierarchy != null && hierarchy.length > 0)
                    {    // hierarchy and name search
                        toReturn = userDAO.searchUserByNameHierarchy(firstName,
                                lastName, hierarchy, depth,
                                sessionAuthorizedUser, countFlag, larsSize);
                    }
                    else
                    {
                        toReturn =
                                userDAO.searchUserByName(lastName, firstName,
                                        sessionAuthorizedUser,
                                        countFlag, larsSize);
                    }
                }
                else if (hierarchy != null && hierarchy.length > 0)
                {	 // only hierarchySearch
                    toReturn =
                            userDAO.searchUserByHierarchy(hierarchy, depth,
                                    (short) 1,
                                    sessionAuthorizedUser,
                                    countFlag, larsSize);
                }
                else
                {
                    logger
                            .debug("SearchUserService - searchUser(SearchUserDTO iProfileData, Hashtable sessionAuthorizedUser).....SearchCriteriaNotDefined");
                    throw new SearchCriteriaNotDefined();
                }
            }
        }
        catch (NBException e)
        {

            // logger.error("Exception SearchUserService - searchUser(SearchUserDTO iProfileData, Hashtable
            // sessionAuthorizedUser).........."+e.getStackTrace());
            throw e;
        }
        logger
                .debug("Exiting SearchUserService - SearchUserDTO iProfileData, Hashtable sessionAuthorizedUser.....");
        return toReturn;
    }

}
