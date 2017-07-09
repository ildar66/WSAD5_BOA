package com.boa.eagls.government.service.pointOfContact;

import com.boa.eagls.government.controller.formbean.inquiry.IPointOfContact;
import com.boa.eagls.government.dao.PointOfContactDAO;
import com.boa.eagls.government.service.Service;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: StanislavS
 * Date: 02.07.2003
 * Time: 14:41:03
 * To change this template use Options | File Templates.
 */
public class PointOfContactService extends Service {
    private static final Logger logger = Logger.getLogger(PointOfContactService.class);



    //todo throw exception like EJBException - it should be a kind of Web Layer exception
    /**
     * getPointOfContact gets list of POC of current Account of Agency
     * @param laRSSize
     * @return
     */
    public Collection getPointOfContact(String accNum) {
        Connection con = null;
        try {
            con = getPooledConnection();
            return new PointOfContactDAO().selectPointsOfContacts(con, accNum);
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
        } finally {
            closeConnection(con);
        }
        return null;
    }

    /***
     * UpdatePointOfContact updates POC with new values
     * @param formBean
     */

    public void updatePointOfContact(IPointOfContact formBean) {
        PointOfContactDAO pointOfContactDAO = new PointOfContactDAO();
        Connection con = null;
        try {
            con = getPooledConnection();
            pointOfContactDAO.updatePointOfContact(con, formBean);
        } catch (SQLException e) {
            // todo log system exceptions into DB table (as specified)
            //todo rethrow new exception. It should be exception of Web Layer
            logger.error(e.getMessage(), e);
        } finally {
            closeConnection(con);
        }
    }
}

