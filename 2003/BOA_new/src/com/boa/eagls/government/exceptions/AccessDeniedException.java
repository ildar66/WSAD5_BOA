/**
 * AccessDeniedException
 */
package com.boa.eagls.government.exceptions;

/**
 * <p>Title: GEAGLS</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author
 * @version 1.0
 * @invariant $none
 */
public class AccessDeniedException extends NBException
{

    /**
     * Default constrauctor
     * @pre $none
     * @post $none
     */
    public AccessDeniedException()
    {
        super();
    }

    /**
     * Overloaded constructor
     * @param msg
     * @pre $none
     * @post $none
     */
    public AccessDeniedException(String msg)
    {
        super(msg);
    }

}
