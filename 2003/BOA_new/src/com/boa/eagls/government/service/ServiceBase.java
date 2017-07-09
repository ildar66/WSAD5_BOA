/**
 * ServiceBase
 */
package com.boa.eagls.government.service;

import com.boa.eagls.government.dto.*;
import com.boa.eagls.government.dto.user.*;

import java.util.Date;

/**
 * Abstract base class for all factories.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public abstract class ServiceBase extends Service {

    // General static final values
    public static final int HIERARCHY_LIMIT = 9;
    protected final static int BO_STATE_EMPTY = 0;
    protected final static int BO_STATE_NEW = 1;
    protected final static int BO_STATE_DIRTY = 2;
    protected final static int BO_STATE_CURRENT = 3;


    protected int state = BO_STATE_CURRENT;    // current state
    private java.sql.Connection connection = null;

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getStateCurrent()
    {
        return state;
    }

    /**
     * Method declaration
     *
     */
    protected void setStateEmpty()
    {
        state = BO_STATE_EMPTY;
    }

    /**
     * Method declaration
     *
     */
    protected void setStateNew()
    {
        state = BO_STATE_NEW;
    }

    /**
     * Method declaration
     *
     */
    protected void setStateDirty()
    {

        // Can't UPDATE an object that isn't INSERTed yet.
        if (state != BO_STATE_NEW)
        {
            state = BO_STATE_DIRTY;
        }
    }

    /**
     * Method declaration
     *
     */
    protected void setStateCurrent()
    {
        state = BO_STATE_CURRENT;
    }

    /*
     * Validation Utility Methods
     */

    /**
     * Method declaration
     *
     *
     * @param value
     *
     * @return
     */
    protected int validateInteger(Integer value)
    {
        if (value != null)
        {
            return value.intValue();
        }
        else
        {
            return com.boa.eagls.government.util.Constants.INT_DEFAULT;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param value
     *
     * @return
     */
    protected String validateString(String value)
    {
        if (value != null)
        {
            return value;
        }
        else
        {
            return com.boa.eagls.government.util.Constants.STRING_DEFAULT;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param date
     *
     * @return
     */
    protected Date validateDate(Date date)
    {
        if (date != null)
        {
            return date;
        }
        else
        {
            return com.boa.eagls.government.util.Constants.DATE_DEFAULT;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     *
     * @return
     */
    protected HierarchyDTO[] validateHierarchy(HierarchyDTO[] hierarchy)
    {
        if (hierarchy != null)
        {
            return hierarchy;
        }
        else
        {
            HierarchyDTO[] newHierarchy = new HierarchyDTO[HIERARCHY_LIMIT];

            for (int i = 0; i < HIERARCHY_LIMIT; i++)
            {
                newHierarchy[i] = new HierarchyDTO((short) 0, 0, "");
            }
            return newHierarchy;
        }
    }

    /**
     * Method declaration
     *
     *
     * @param profile
     *
     * @return
     */
    protected ProfileDTO validateProfile(ProfileDTO profile)
    {
        if (profile != null)
        {
            return profile;
        }
        else
        {
            return new ProfileDTO();
        }
    }

    /**
     * Method declaration
     *
     *
     * @param hierarchy
     *
     * @return
     */
    protected HierarchyDTO[] copyHierarchy(HierarchyDTO[] hierarchy)
    {
        HierarchyDTO[] copyHierarchy = new HierarchyDTO[hierarchy.length];

        for (int i = 0; i < hierarchy.length; i++)
        {
            copyHierarchy[i] = hierarchy[i];
        }
        return copyHierarchy;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public java.sql.Connection getConnection()
    {
        return connection;
    }

    /**
     * Method declaration
     *
     *
     * @param connection
     */
    public void setConnection(java.sql.Connection connection)
    {
        this.connection = connection;
    }

}
