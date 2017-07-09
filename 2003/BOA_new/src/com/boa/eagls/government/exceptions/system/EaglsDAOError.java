package com.boa.eagls.government.exceptions.system;

/**
 * System level exception. It's thrown from DAO slasses when there is no SQL error but
 * data incosistence is found.
 * User: OlegK
 * Date: 28.06.2003
 * Time: 12:08:23
 */
public class EaglsDAOError extends Exception {

    /**
     * Default constrauctor
     */
    public EaglsDAOError() {
        super();
    }

    /**
     * Overloaded constructor
     */
    public EaglsDAOError(String msg) {
        super(msg);
    }

    public EaglsDAOError(Throwable err) {
        super(err.getMessage());
    }

}
