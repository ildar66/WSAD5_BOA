package com.boa.eagls.government.service;

import com.boa.eagls.government.dao.HierarchyDAO;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.util.HierarchyDisplay;

import java.sql.Connection;

/**
 * Service for Hiearachy operations
 * @author OlegK
 * Date: 28.06.2003
 * Time: 15:05:52
 */
public final class HierarchyBase extends Service {

    /**
     * gets Hierarchy Number (scalar valur) by int hierarchy array
     * @param hierarchy int hierarchy array
     * @return
     * @throws EaglsDAOError
     */
    public String getHierarchyNumber(int[] hierarchy) throws EaglsDAOError {

        Connection con = null;
        try {
            con = getPooledConnection();
            // No validation check.
            if (hierarchy.length != HierarchyDisplay.NUMBER_OF_SEGMENTS)
                throw new IllegalArgumentException("HierarchyBase: Not a valid hierarchy" + String.valueOf(hierarchy));
            HierarchyDAO dAA = new HierarchyDAO(con);
            return dAA.getHierarchyNumber(hierarchy);

        } catch (Exception e) {
            throw new EaglsDAOError("SQL error in SearchByFullName.select(): " + e);
        } finally {
            closeConnection(con);
        }
    }

    /**
     * checks for same hierarchies
     * @param hierarchy1
     * @param hierarchy2
     * @throws Exception
     */
    public static void sameHierarchyTree(int[] hierarchy1, int[] hierarchy2) throws Exception {

        int loopCount = HierarchyDisplay.NUMBER_OF_SEGMENTS - 1;
        for (loopCount = HierarchyDisplay.NUMBER_OF_SEGMENTS - 1; loopCount >= 0; loopCount--) {
            if (hierarchy1[loopCount] > 0) {
                break;
            }
        }
        // loopCount is the last non zero hierarchy for the account
        for (int i = 0; i <= loopCount; i++) {
            if (hierarchy2[i] == 0) {
                // we have matched all user hierarchies
                return; //true;
            } else {
                if (hierarchy2[i] != hierarchy1[i]) {
                    // account is not in the user hierarchy
                    Exception nbError = new Exception("Hierarchy Not Under The Same Hierarchy Structure");
//                    nbError.setMessage("Hierarchy Not Under The Same Hierarchy Structure");
                    throw nbError;

                }
            }
        }
        // userHierarchy matched every hierarchy level in acct hiearchy
        return;
    }

    // General static final values
    public static final int HIERARCHY_LIMIT = 9;
}
