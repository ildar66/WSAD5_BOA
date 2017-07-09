/**
 * NoDataFoundException
 */
package com.boa.eagls.government.exceptions;

import org.apache.log4j.Logger;

/**
 * NoDataFoundException
 *
 *
 * @author
 * @version %I%, %G%
 */
public class NoDataFoundException extends NBException{
    private static final Logger logger= Logger.getLogger(NoDataFoundException.class);

    /**
     * Constructor declaration
     *
     */
    public NoDataFoundException()
    {
        super("APP_W0009");
    }

    /**
     * Constructor declaration
     *
     *
     * @param errorMsg
     */
    public NoDataFoundException(String errorMsg)
    {
        super();
        String exception = "APP_W0009" + ":" + errorMsg;

        logger.error("No Data Found Exception : "+ exception);
        setException(exception);
    }

}
