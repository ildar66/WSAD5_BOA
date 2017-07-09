package com.boa.eagls.government.exceptions.application;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Base application level exception. All other application level exeception must extend this.
 * User: OlegK
 * Date: 28.06.2003
 * Time: 11:59:24
 */
public class EaglsException extends Exception {
    private Exception cause;

    /**
     * Default constrauctor
     */
    public EaglsException() {
        super();
    }

    public EaglsException(String message, Exception cause) {
        super(message);
        this.cause = cause;
    }

    public EaglsException(Exception e) {
        super();
        this.cause = e;
    }

    /**
     * Overloaded constructor
     * @deprecated use constructor based on exception
     */
    public EaglsException(String msg) {
        super(msg);
    }


    public Exception getRootCause() {
        return cause;
    }

    public void printStackTrace() {
        super.printStackTrace();
        cause.printStackTrace();
    }

    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s);
        s.println("ROOT CAUSE IS:");
        cause.printStackTrace(s);
    }

    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
        s.println("ROOT CAUSE IS:");
        cause.printStackTrace(s);
    }
}
