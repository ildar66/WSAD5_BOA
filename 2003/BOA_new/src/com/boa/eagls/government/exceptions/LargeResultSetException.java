/**
 * LargeResultSetException
 */
package com.boa.eagls.government.exceptions;

import java.util.*;

/**
 * Class declaration
 *
 *
 * @author
 * @version %I%, %G%
 */
public class LargeResultSetException extends NBInformation
{
    private int resultSetCount = 0;

    /**
     * Constructor declaration
     *
     */
    public LargeResultSetException()
    {
        super();
    }

    /**
     * Constructor declaration
     *
     *
     * @param resultSetCount
     */
    public LargeResultSetException(int resultSetCount)
    {
        super();
        this.resultSetCount = resultSetCount;
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     */
    public LargeResultSetException(String aMessage)
    {
        super(aMessage);
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     * @param resultSetCount
     */
    public LargeResultSetException(String aMessage, int resultSetCount)
    {
        super(aMessage);
        this.resultSetCount = resultSetCount;
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     * @param aSeverity
     */
    public LargeResultSetException(String aMessage, short aSeverity)
    {
        super(aMessage, aSeverity);
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     * @param resultSetCount
     * @param aSeverity
     */
    public LargeResultSetException(String aMessage, int resultSetCount,
                                   short aSeverity)
    {
        super(aMessage, aSeverity);
        this.resultSetCount = resultSetCount;
    }

    /**
     * Method declaration
     *
     *
     * @return
     */
    public int getResultSetCount()
    {
        return this.resultSetCount;
    }

    /**
     * Method declaration
     *
     *
     * @param resultSetCount
     */
    public void setResultSetCount(int resultSetCount)
    {
        this.resultSetCount = resultSetCount;
    }

}
