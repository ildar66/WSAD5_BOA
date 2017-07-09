/*
 * BrowseService
 */

package com.boa.eagls.government.service.common;

import com.boa.eagls.government.dto.user.*;
import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.exceptions.*;
import com.boa.eagls.government.dao.*;
import com.boa.eagls.government.service.ServiceBase;

/**
 * BrowseService
 *
 *
 * @author
 * @version %I%, %G%
 */
public class BrowseService extends ServiceBase
{

    /**
     * Constructor declaration
     *
     *
     * @param connection
     */
    public BrowseService(java.sql.Connection connection)
    {
        setConnection(connection);
    }

    /**
     * Method declaration
     *
     *
     * @param hBrowse
     * @param countFlag
     * @param maxSize
     * @param isTransfer
     * @param currentHierarchy
     * @param currentBaseRole
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBException
     * @exception NBException
     * @exception java.sql.SQLException
     */
    public HierarchyDTO[] browseHierarchyWithDesc(int[] hBrowse,
                                                  boolean countFlag, int maxSize, boolean isTransfer,
                                                  HierarchyDTO[] currentHierarchy,
                                                  String currentBaseRole) throws NBException,
            java.sql.SQLException,
            EAGLSException, DataConnException,
            NBException
    {
        BrowseDAO browseDAO = new BrowseDAO(null);

        return browseDAO.browseHierarchyWithDesc(hBrowse, countFlag, maxSize,
                isTransfer,
                currentHierarchy,
                currentBaseRole);
    }

    /**
     * Method declaration
     *
     *
     * @param hBrowse
     * @param countFlag
     * @param maxSize
     * @param currentHierarchy
     * @param currentBaseRole
     *
     * @return
     *
     * @exception DataConnException
     * @exception EAGLSException
     * @exception NBException
     * @exception NBException
     * @exception java.sql.SQLException
     */
    public HierarchyDTO[] browseHierarchyWithDesc(int[] hBrowse,
                                                  boolean countFlag, int maxSize, HierarchyDTO[] currentHierarchy,
                                                  String currentBaseRole) throws NBException,
            java.sql.SQLException,
            EAGLSException, DataConnException,
            NBException
    {
        BrowseDAO browseDAO = new BrowseDAO(null);

        return browseDAO.browseHierarchyWithDesc(hBrowse, countFlag, maxSize,
                currentHierarchy,
                currentBaseRole);
    }

}
