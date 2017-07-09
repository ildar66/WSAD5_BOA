/**
 * DBException
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: DBException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author unascribed
 * @version 1.0
 */
public class DBException extends NBException
{

    /**
     * Constructor declaration
     *
     */
    public DBException()
    {
    }

    /**
     * Constructor declaration
     *
     *
     * @param msg
     */
    public DBException(String msg)
    {
        super(msg);
    }

}
