package com.boa.eagls.government.exceptions;

import java.util.ArrayList;

/**
 * <p><small> DVI Company, 22.07.2003 20:01:55</small></p>
 * @author GlebL
 */
public class InvalidAccountingCodeError extends NBError {

    /**
     * Constructs the exception with a message, a severity level and a ArrayList
     * of SegmentError objects..
     *
     * @param   message     the error message.
     * @param   severity    the severity level of the error.
     * @param   errors      a ArrayList of SegmentError objects.
     */
    public InvalidAccountingCodeError(String message,
                                      short severity,
                                      ArrayList errors) {
        super("", severity);
        this.setDetailedMessage(message);
        this.segmentErrors = errors;
    }

    public InvalidAccountingCodeError(String messageD, String message, short severity, ArrayList errors) {
        super("", severity);
        this.setDetailedMessage(messageD);
        this.setMessage(message);
        this.segmentErrors = errors;
    }

    /**
     * Returns the number of segment errors recorded to this point.
     *
     * @return  the number of segment errors recorded in the exception.
     */
    public int getNumberOfErrors() {
        return segmentErrors.size();
    }


    /**
     * Returns an enumeration to iterate through the SegmentErrors
     * reported with this exception.
     *
     * @return      an Enumeration to iterate through SegmentError objects.
     */
    public ArrayList getSegmentErrors() {
        return segmentErrors;
    }

    /**
     * Contains the list of invalid segments.
     */
    private ArrayList segmentErrors = new ArrayList();

}