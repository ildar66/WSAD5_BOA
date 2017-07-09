/**
 * UserAlreadyExistException
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: UserAlreadyExistException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class UserAlreadyExistException extends NBException
{

    /**
     * Constructor declaration
     *
     */
    public UserAlreadyExistException()
    {
    }

    /**
     * Constructor declaration
     *
     *
     * @param msg
     */
    public UserAlreadyExistException(String msg)
    {
        super(msg);
    }

}
