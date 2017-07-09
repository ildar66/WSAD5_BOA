/**
 * NBError
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: NBError</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class NBError extends NBException
{

    /**
     * Constructor declaration
     *
     */
    public NBError()
    {
        super();
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     */
    public NBError(String aMessage)
    {
        super(aMessage);
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     * @param aSeverity
     */
    public NBError(String aMessage, short aSeverity)
    {
        super(aMessage, aSeverity);
    }

}
