/**
 * DTOBase
 */
package com.boa.eagls.government.dto;

/**
 * Abstract base class for all DTOs <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.Serializable;
import java.util.Date;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public abstract class DTOBase implements Serializable
{

    /**
     * String Default
     */
    protected static final String STRING_DEFAULT = "";

    /**
     * String Array Default
     */
    protected static final String[] STRING_ARRAY_DEFAULT =
            {
                "", "", ""
            };

    /**
     * Short Integer Default
     */
    protected static final short SHORT_DEFAULT = 0;

    /**
     * Date Default
     */
    protected static final Date DATE_DEFAULT = new Date();

    /**
     * Integer Default
     */
    protected static final int INT_DEFAULT = 0;

    /**
     * Floating Point Default
     */
    protected static final float FLOAT_DEFAULT = 0;

    /**
     * Boolean Default
     */
    protected static final boolean BOOLEAN_DEFAULT = false;

    /**
     * Long Integer Default
     */
    protected static final long LONG_DEFAULT = 0;

    /**
     * Character Default
     */
    protected static final char CHAR_DEFAULT =
            '*';    // TODO:  Need to evaluate.

    /**
     * Double Default
     */
    protected static final double DOUBLE_DEFAULT = 0;

    /**
     * Integer Array Default
     */
    protected static final int[] INT_ARRAY_DEFAULT =
            {
                0, 0, 0, 0, 0, 0, 0, 0, 0
            };

    /**
     * General static final values
     */

    /**
     * Hierarchy Max Limit
     */
    public static final int HIERARCHY_LIMIT = 9;

    /**
     * DTO State Empty
     */
    protected final static int BO_STATE_EMPTY = 0;

    /**
     * DTO State New
     */
    protected final static int BO_STATE_NEW = 1;

    /**
     * DTO State Dirty
     */
    protected final static int BO_STATE_DIRTY = 2;

    /**
     * DTO State Current
     */
    protected final static int BO_STATE_CURRENT = 3;

    /**
     * DTO Current State Indicator
     */
    protected int state = BO_STATE_CURRENT;    // current state

    /**
     * Utility method for validating Integers
     * @param value
     * @return int
     */
    protected int validateInteger(Integer value)
    {
        int returnValue = INT_DEFAULT;

        if (value != null)
        {
            returnValue = value.intValue();
        }
        return returnValue;
    }

    /**
     * Utility method for validating String
     * @param value
     * @return String
     */
    protected String validateString(String value)
    {
        String returnValue = STRING_DEFAULT;

        if (value != null)
        {
            returnValue = value;
        }
        return returnValue;
    }

}
