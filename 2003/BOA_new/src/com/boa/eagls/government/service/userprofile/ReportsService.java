/**
 * ReportsService
 */
package com.boa.eagls.government.service.userprofile;

import com.boa.eagls.government.service.ServiceBase;
import com.boa.eagls.government.dto.CrystalTransaction;
import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.exceptions.*;

/**
 * <p>Title: ReportsService</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class ReportsService extends ServiceBase
{
    ReportsDAO reportsDAO = null;

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public ReportsService(java.sql.Connection connection)
    {
        setConnection(connection);
        reportsDAO = new ReportsDAO(getConnection());
    }

    /**
     * Method declaration
     *
     *
     * @param t
     * @param sessionUserID
     *
     * @exception NBException
     */
    public void addTransaction(CrystalTransaction t,
                               String sessionUserID) throws NBException
    {
        reportsDAO.addTransaction(t, sessionUserID);
    }

}
