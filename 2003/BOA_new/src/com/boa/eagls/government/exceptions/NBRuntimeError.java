/**
 * NBRuntimeError
 */

package com.boa.eagls.government.exceptions;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class NBRuntimeError extends RuntimeException
{

    /**
     * Constructor declaration
     *
     */
    public NBRuntimeError()
    {
        super();
    }

    /**
     * Constructor declaration
     *
     *
     * @param msg
     */
    public NBRuntimeError(String msg)
    {
        super(msg);
    }

}
