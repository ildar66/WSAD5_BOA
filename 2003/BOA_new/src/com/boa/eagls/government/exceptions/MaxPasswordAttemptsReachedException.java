package com.boa.eagls.government.exceptions;

public class MaxPasswordAttemptsReachedException extends NBException
{

    /**
     */
    public MaxPasswordAttemptsReachedException()
    {
    }

    /**
     * @param msg
     */
    public MaxPasswordAttemptsReachedException(String msg)
    {
        super(msg);
    }


}
