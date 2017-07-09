package com.boa.eagls.government.util;

import java.util.Date;

/**
 * DisplayDateUtil carries some useful methods for converting between
 * Date objects and String date formats used by Front-End
 *
 * @author Olivia Wu
 * @version    1.10  $ $Modtime:   07 Oct 1998 18:24:12  $
 *
 **/
public class DisplayDateUtil extends DateUtil {
    public static final Date ZERO_DATE = new Date(0);

    public static final String DEFAULT_DATE = "N/A";

    /**
     * Protect this class from being instantiated.
     **/

    protected DisplayDateUtil() {
    }

    /**
     * Convert a Date object into a String of format specified by FULL_DATE_FORMAT
     *
     * @param	date	the Date object to convert.  If this is null or Date 0, convertDateToString
     *					will return "N/A".
     * @return	String	contains the contents of date in format specified in FULL_DATE_FORMAT
     **/

    public static String convertDateToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertDateToString(date);
        }
    }

    /**
     * Convert a Date object, containing an expiration date, into a String of
     * format specified by EXPIRY_DATE_FORMAT
     *
     * @param	date	the Date object to convert.  If this is null or Date 0, convertExpiryToString
     *					will return "N/A".
     * @return	String	contains the contents of date in format specified in EXPIRY_DATE_FORMAT
     **/

    public static String convertExpiryToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertExpiryToString(date);
        }
    }

    /**
     * Convert a Date object, into a String of format specified by DAY_OF_YEAR_DATE_FORMAT.
     * This format consists of seven digits, first four of which signify the year and the
     * last 3 the day of the year (e.g. 1998208 signifies July 27, 1998 which is the 208th
     * day of the year 1998).
     *
     * @param	date	the Date object to convert.  If this is null or date zero, convertDayOfYearToString()
     *					will return "N/A".
     * @return	String	contains the contents of date in format specified in DAY_OF_YEAR_DATE_FORMAT.
     **/

    public static String convertDayOfYearToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertDayOfYearToString(date);
        }

    }

    /**
     * Convert a Date object, into a String of format specified by FULL_DATE_TIME_FORMAT.
     *
     * @param	date	the Date object to convert.  If this is null or Date 0, convertFullDateAndTimeToString()
     *					will return "N/A".
     * @return	String	contains the contents of date in format specified in
     *                  FULL_DATE_TIME_FORMAT.
     **/

    public static String convertFullDateAndTimeToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertFullDateAndTimeToString(date);
        }
    }

    /**
     * Convert a Date object, into a String of format specified by YEARMONTHDAY_DATE_FORMAT.
     *
     * @param	date	the Date object to convert.  If this is null or Date 0, convertYearMonthDayFormatToString()
     *					will return "N/A".
     * @return	String	contains the contents of date in format specified in
     *                  YEARMONTHDAY_DATE_FORMAT.
     **/

    public static String convertYearMonthDayFormatToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertYearMonthDayFormatToString(date);
        }
    }

    /**
     * Converts a Date object into 24-hour time String ("HH:mm").
     *
     * @param	date	a Date object.
     * @return	the String representation of the time portion of <code>date</code>.
     */
    public static String convertTimeToString(Date date) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.convertTimeToString(date);
        }
    }

    /**
     * Generic formatter that converts a Date object <code>date</code>
     * into a string of format <code>f</code>.
     *
     * @param	date	a Date.
     * @param	f		the string format to convert <code>date</code> into.
     * @return	a String representation of <code>date</code>.
     */
    public static String format(Date date, String f) {
        if (date == null || ZERO_DATE.equals(date)) {
            return DEFAULT_DATE;
        } else {
            return DateUtil.format(date, f);
        }
    }

    public static Date convertStringToDate(String dateStr) {
        return DateUtil.convertStringToDate(dateStr);
    }

    public static Date convertStringToExpiry(String dateStr) {
        return DateUtil.convertStringToExpiry(dateStr);
    }

    public static Date convertStringToDayOfYear(String dateStr) {
        return DateUtil.convertStringToDayOfYear(dateStr);
    }

    public static Date convertStringToFullDateAndTime(String dateStr) {
        return DateUtil.convertStringToFullDateAndTime(dateStr);
    }

    public static Date convertStringToYearMonthDayFormat(String dateStr) {
        return DateUtil.convertStringToYearMonthDayFormat(dateStr);
    }

    public static Date convertStringToTime(String s) {
        return DateUtil.convertStringToTime(s);
    }

    public static Date parse(String s, String f) {
        return DateUtil.parse(s, f);
    }

} // End of class DisplayDateUtil
