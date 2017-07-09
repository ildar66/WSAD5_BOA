package com.boa.eagls.government.dto.accounting;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * <p><small> DVI Company, 22.07.2003 18:03:23</small></p>
 * @author GlebL
 */
public class AccountingCode {
    static final Logger logger = Logger.getLogger(AccountingCode.class);


    /**
     * Constructs an Accounting Code object with a Vector of segments.
     * The Vector must hold the segments in their proper order.
     *
     * @param   theSegments     a Vector containing this codes segments in
     *                          order.
     */
    public AccountingCode(ArrayList theSegments) {
        if (segments != null) {
            segments = theSegments;
        }
    }

    /**
     * Constructs an Accounting Code object with an array of segments.
     * The array must hold the segments in their proper order.
     *
     * @param   theSegments     an array containing this codes segments
     *                          in order.
     */
    public AccountingCode(AccountingCodeSegment[] theSegments) {
        if (theSegments != null) {
            // No need to clear the ArrayList since this is a constructor.
            for (short i = 0; i < theSegments.length; i++) {
                segments.add(theSegments[i]);
            }
        }
    }

    /**
     * Formats the accounting code into a string representation
     * with or without slashes as segment separators.
     *
     * @param   withSlashes true will insert slashes between segments;
     *                      false will not use any sort of separator.
     * @return  a string representation of this accounting code.
     */
    public String format(boolean withSlashes) {

        Iterator it = segments.iterator();
        StringBuffer buf = new StringBuffer();
        boolean isFirst = true;
        while (it.hasNext()) {
            // decide whether to append a slash
            if (isFirst) {
                isFirst = false;
            } else if (withSlashes) {
                buf.append('/');
            }

            // append the nextColumn segment
            buf.append(((AccountingCodeSegment) it.next()).getValue());
        }
        return buf.toString();
    }

    /**
     * Formats the description portion of the accounting code
     * into a string.
     *
     * @return  a String representation of the the accounting
     *          code description.
     */
    public String formatDescription() {
        Iterator it = segments.iterator();
        StringBuffer buf = new StringBuffer();
        boolean isFirst = true;
        while (it.hasNext()) {
            // decide whether to append a slash
            if (isFirst) {
                isFirst = false;
            } else {
                buf.append('/');
            }

            // append the nextColumn segment
            buf.append(((AccountingCodeSegment) it.next()).getDescription());
        }
        return buf.toString();

    }

    /**
     * Returns a string representation of the object.  The string
     * will contain the value of the accounting code for debug
     * use.
     *
     * @return  a string representation of the object.
     */
    public String toString() {
        return this.getClass().getName() + "{acctCode=" + format(true) + "}";

    }

    /**
     *The following method evaluates wheather the  campared object is similar to
     * the AccountinCode Object.
     * They are equal if thier format string of value if same.
     * <li>Note we do not compare the description or any other property of object to make the comparision.</li>
     * @param Object The objec  to be compared to.
     */

    public boolean equals(Object obj) {

        if (!(obj instanceof AccountingCode)) return false;
        AccountingCode ac1 = (AccountingCode) obj;
        if (!ac1.format(false).equals(this.format(false))) return false;
        return true;

    }

    /**
     * This method removes slashes from an accounting code (or any string)
     * and returns a string to the caller.
     * @param String The string to be parsed
     * @param String The token to be parsed out of the input string
     */

    public String removeToken(String str, String token) {

        StringBuffer retStr = new StringBuffer();

        StringTokenizer strTok = new StringTokenizer(str, token, false);
        while (strTok.hasMoreElements())
            retStr.append((String) strTok.nextElement());

        return retStr.toString();
    }


    private ArrayList segments = new ArrayList(10);

}