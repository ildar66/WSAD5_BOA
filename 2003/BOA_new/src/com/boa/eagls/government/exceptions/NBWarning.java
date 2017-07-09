/**
 * NBWarning
 */
package com.boa.eagls.government.exceptions;

/**
 * NBWarning
 *
 *
 * @author
 * @version %I%, %G%
 */
public class NBWarning extends NBException
{

    /**
     * Constructor declaration
     *
     */
    public NBWarning()
    {
        super();
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     */
    public NBWarning(String aMessage)
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
    public NBWarning(String aMessage, short aSeverity)
    {
        super(aMessage, aSeverity);
    }

}
