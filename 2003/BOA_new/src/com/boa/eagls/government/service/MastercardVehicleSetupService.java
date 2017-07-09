package com.boa.eagls.government.service;

import com.boa.eagls.government.service.Service;
import com.boa.eagls.government.exceptions.system.EaglsDAOError;
import com.boa.eagls.government.dao.MastercardVehicleDAO;
import com.boa.eagls.government.dto.account.Account;
import com.boa.eagls.government.dto.HierarchyDTO;
import com.boa.eagls.government.dto.agency.AgencyCore;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: GlebL
 * Date: 10.07.2003
 * Time: 18:41:25
 * To change this template use Options | File Templates.
 */
public class MastercardVehicleSetupService extends Service {
    private static final Logger logger= Logger.getLogger(MastercardVehicleSetupService.class);

    public Account retrieveCentralAcctByCentralAcctID(String centralAcctID,
                                                             String userID)
            throws EaglsDAOError {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            MastercardVehicleDAO adapter = new MastercardVehicleDAO();
            return adapter.retrieveCentralAcctByCentralAcctID(conn, centralAcctID, userID);
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
            throw new EaglsDAOError();
//            logger.debug(this, e);
        } finally {
            closeConnection(conn);
        }
    }

    public AgencyCore retrieveAgencyCoreByCentralAcctID(String centralAcctID,
                                                               String userID)
            throws EaglsDAOError {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            MastercardVehicleDAO adapter = new MastercardVehicleDAO();
            return adapter.retrieveAgencyCoreByCentralAcctID(conn, centralAcctID, userID);
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
            throw new EaglsDAOError();
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }

    public ArrayList retrieveFuelTypeInfo()
            throws EaglsDAOError {
        Connection conn = null;
        try {
			conn = getPooledConnection();
            MastercardVehicleDAO adapter = new MastercardVehicleDAO();
            return adapter.retrieveFuelTypeInfo(conn);
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
            throw new EaglsDAOError();
        } finally {
            closeConnection(conn);
        }
    }

    public ArrayList searchMastercardVehicleByEquipmentID(
            String centralAcctID, String equipmentID, Map htUserData)
            throws EaglsDAOError {
        Connection conn = null;
        try {
            conn = getPooledConnection();
            MastercardVehicleDAO adapter = new MastercardVehicleDAO();
            int[] hierarchy = getCurrentShortHierarchy((HierarchyDTO[]) htUserData.get("currentHierarchy"));
            return adapter.searchMastercardVehicleByEquipmentID(
                    conn, centralAcctID, equipmentID, hierarchy);
        } catch (Exception e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
            throw new EaglsDAOError();
        } finally {
            closeConnection(conn);
        }
    }

}
