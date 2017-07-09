/**
 * NBInformation
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
public class NBInformation extends NBException
{
    final static String DELIMITER = "::";

    /**
     * Constructor declaration
     *
     */
    public NBInformation()
    {
        super();
    }

    /**
     * Constructor declaration
     *
     *
     * @param aMessage
     */
    public NBInformation(String aMessage)
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
    public NBInformation(String aMessage, short aSeverity)
    {
        super(aMessage, aSeverity);
    }

}
