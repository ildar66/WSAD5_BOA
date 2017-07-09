/**
 * LoginFailedException
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: LoginFailedException</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 */
public class LoginFailedException extends NBException
{

    /**
     */
    public LoginFailedException()
    {
    }

    /**
     * @param msg
     */
    public LoginFailedException(String msg)
    {
        super(msg);
    }

}
