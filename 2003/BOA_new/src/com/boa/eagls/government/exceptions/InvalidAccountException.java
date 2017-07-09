package com.boa.eagls.government.exceptions;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class InvalidAccountException extends NBException
{

    /**
     * Constructor declaration
     *
     */
    public InvalidAccountException()
    {
    }

    /**
     * Constructor declaration
     *
     *
     * @param msg
     */
    public InvalidAccountException(String msg)
    {
        super(msg);
    }

}
