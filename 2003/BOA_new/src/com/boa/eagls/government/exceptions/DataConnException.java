/**
 * DataConnException
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 * @invariant $none
 */
public class DataConnException extends NBException
{

    /**
     * Default constrauctor
     */
    public DataConnException()
    {
        super();
    }

    /**
     * Overloaded constructor
     */
    public DataConnException(String msg)
    {
        super(msg);
    }

}
