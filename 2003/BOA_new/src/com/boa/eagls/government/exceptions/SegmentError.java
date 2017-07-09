package com.boa.eagls.government.exceptions;

import org.apache.log4j.Logger;
import com.boa.eagls.government.dto.accounting.AccountingCodeSegmentDescription;

/**
 * <p><small> DVI Company, 22.07.2003 19:59:46</small></p>
 * @author GlebL
 */
public final class SegmentError {
    /**
     * Constructs a SegmentError with a segment number, segment name
     * and segment value.
     *
     * @param   segNumber   the number/position of the segment.
     * @param   segDesc     the description of the segment.
     * @param   segValue    the invalid value.
     */
    public SegmentError(int segNumber,
                           AccountingCodeSegmentDescription segDesc,
                           String segValue) {
        number = segNumber;
        name = segDesc.getName();
        value = segValue;
    }

    public String getSegmentName() {
        return name;
    }

    public int getSegmentNumber() {
        return number;
    }

    public String getInvalidValue() {
        return value;
    }


    public String toString() {
        return this.getClass().getName() + "{Number=" + getSegmentNumber() +
                ";Name=" + getSegmentName() +
                ";Value=" + getInvalidValue() + "}";
    }

    private int number;
    private String name;
    private String value;
}