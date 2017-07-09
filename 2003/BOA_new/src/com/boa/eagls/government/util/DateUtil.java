package com.boa.eagls.government.util;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * DateUtil carries some useful methods for converting between
 * Date objects and String date formats used by Front-End and Database
 * layers.
 *
 * @author Craig A. Lavender
 * @version $Revision: 1.1 $ $Modtime:   Mar 10 2003 05:53:36  $
 *
 * @see java.util.Date
 * @see java.text.SimpleDateFormat
 **/
public class DateUtil extends java.lang.Object {
    public static final String FULL_DATE_FORMAT = "MM/dd/yyyy";
    public static final String EXPIRY_DATE_FORMAT = "MM/yyyy";
    public static final String DAY_OF_YEAR_DATE_FORMAT = "yyyyDDD";
    public static final String FULL_DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
    public static final String YEARMONTHDAY_DATE_FORMAT = "yyyyMMdd";
    public static final String TIME_FORMAT = "HH:mm";

    /**
     * Protect this class from being instantiated.
     **/

    protected DateUtil() {
    }

    /**
     * Convert a Date object into a String of format specified by FULL_DATE_FORMAT
     *
     * @param	date	the Date object to convert.  If this is null, convertDateToString
     *               		will return null.
     * @return	String	contains the contents of date in format specified in FULL_DATE_FORMAT
     **/

    public static String convertDateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_FORMAT);
        return formatter.format(date);
    }

    /**
     * Convert a Date object into a String of format specified by FULL_DATE_FORMAT
     *
     * @param date  the Date object to convert.  If this is null, convertDateToSafeString
     *          will return an empty string.
     * @return	String	contains the contents of date in format specified in FULL_DATE_FORMAT
     **/

    public static String convertDateToSafeString(Date date) {
        if (date == null) {
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_FORMAT);
        return formatter.format(date);
    }


    /**
     * Convert a String holding a date in the format specified by FULL_DATE_FORMAT into
     * a Date object.
     *
     * @param	dateStr	a String holding a date in the format specified by FULL_DATE_FORMAT
     * @return	Date	a Date object created from parsing dateStr.
     **/

    public static Date convertStringToDate(String dateStr) {
        if (dateStr == null || dateStr.equals("~")) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_FORMAT);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Convert a Date object, containing an expiration date, into a String of
     * format specified by EXPIRY_DATE_FORMAT
     *
     * @param	date	the Date object to convert.  If this is null, convertExpiryToString
     *               		will return null.
     * @return	String	contains the contents of date in format specified in EXPIRY_DATE_FORMAT
     **/

    public static String convertExpiryToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(EXPIRY_DATE_FORMAT);
        return formatter.format(date);
    }

    /**
     * Convert a String holding an expiration date in the format specified
     * by EXPIRY_DATE_FORMAT into a Date object.
     *
     * @param	dateStr	a String holding an expiration date in the format
     *               		specified by EXPIRY_DATE_FORMAT.
     * @return	Date	a Date object created from parsing dateStr.
     **/

    public static Date convertStringToExpiry(String dateStr) {
        if (dateStr == null || dateStr.equals("~")) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(EXPIRY_DATE_FORMAT);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Convert a Date object, into a String of format specified by DAY_OF_YEAR_DATE_FORMAT.
     * This format consists of seven digits, first four of which signify the year and the
     * last 3 the day of the year (e.g. 1998208 signifies July 27, 1998 which is the 208th
     * day of the year 1998).
     *
     * @param	date	the Date object to convert.  If this is null, convertDayOfYearToString()
     *               		will return null.
     * @return	String	contains the contents of date in format specified in DAY_OF_YEAR_DATE_FORMAT.
     **/

    public static String convertDayOfYearToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(DAY_OF_YEAR_DATE_FORMAT);
        return formatter.format(date);
    }

    /**
     * Convert a String holding an expiration date in the format specified
     * by DAY_OF_YEAR_DATE_FORMAT into a Date object.
     *
     * @param	dateStr	a String holding an expiration date in the format
     *               		specified by DAY_OF_YEAR_DATE_FORMAT. If this is
     *                  null,
     * @return	Date	a Date object created from parsing dateStr.
     **/

    public static Date convertStringToDayOfYear(String dateStr) {
        if (dateStr == null || dateStr.equals("~")) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(DAY_OF_YEAR_DATE_FORMAT);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Convert a String holding a date in the format specified by FULL_DATE_TIME_FORMAT
     * into a Date object.
     *
     * @param	dateStr	a String holding a date in the format specified by
     *          FULL_DATE_TIME_FORMAT.
     * @return	Date	a Date object created from parsing dateStr.
     **/

    public static Date convertStringToFullDateAndTime(String dateStr) {
        if (dateStr == null || dateStr.equals("~")) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_TIME_FORMAT);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException pe) {
            return null;
        }
    }

    /**
     * Convert a Date object, into a String of format specified by FULL_DATE_TIME_FORMAT.
     *
     * @param	date	the Date object to convert.  If this is null, convertFullDateAndTimeToString()
     *               		will return null.
     * @return	String	contains the contents of date in format specified in
     *                  FULL_DATE_TIME_FORMAT.
     **/

    public static String convertFullDateAndTimeToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(FULL_DATE_TIME_FORMAT);
        return formatter.format(date);
    }

    /**
     * Convert a String holding a date in the format specified by YEARMONTHDAY_DATE_FORMAT
     * into a Date object.
     *
     * @param	dateStr	a String holding a date in the format specified by
     *          YEARMONTHDAY_DATE_FORMAT.
     * @return	Date	a Date object created from parsing dateStr.
     **/

    public static Date convertStringToYearMonthDayFormat(String dateStr) {
        if (dateStr == null || dateStr.equals("~")) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(YEARMONTHDAY_DATE_FORMAT);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException pe) {
            return null;
        }
    }


    /**
     * Convert a Date object, into a String of format specified by YEARMONTHDAY_DATE_FORMAT.
     *
     * @param	date	the Date object to convert.  If this is null, convertYearMonthDayFormatToString()
     *               		will return null.
     * @return	String	contains the contents of date in format specified in
     *                  YEARMONTHDAY_DATE_FORMAT.
     **/

    public static String convertYearMonthDayFormatToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(YEARMONTHDAY_DATE_FORMAT);
        return formatter.format(date);
    }

    /** NOT IMPLEMENTED
     *  Adds(/Subtracts) the specified (signed) amount of time to(/from) the given time field.
     *  For example, to subtract 5 days from the current time of the calendar, you can achieve
     *  it by calling: add(getDATE(), -5).
     *  It does not compute the days in a month in a year for each given date.
     *  Moreover, it adds, subtracts based on the day of the month for the given date.
     *  For example 1.add(9/21/98 (date), 10) then it return 1
     *  add(9/12/98, -9) would return 3
     *  add(9/12/98,-21) would return -9
     **/

    /* public static int addDate(Date date, int noOfDays) {
      return date.getDate() + noOfDays;
    }*/


    /**
     * Converts a Date object into 24-hour time String ("HH:mm").
     *
     * @param	date	a Date object.
     * @return	the String representation of the time portion of <code>date</code>.
     */
    public static String convertTimeToString(Date date) {
        return format(date, TIME_FORMAT);
    }


    /**
     * Converts a String in format, "HH:mm", into a date object.
     * The String is in 24-hour format.  TODO note date portion.
     *
     * @param	s	a string in the format "HH:mm".
     * @return	a date object created from s.
     */
    public static Date convertStringToTime(String s) {
        return parse(s, TIME_FORMAT);
    }


    /**
     * Generic parser that converts a string <code>s</code>
     * of format <code>f</code> into a Date object.
     *
     * @param	s	a string representation of date and/or time.
     * @param	f	the format of <code>s</code>.
     * @return	a Date object representation of <code>s</code>.
     */
    public static Date parse(String s, String f) {
        if (s == null) {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat(f);
        try {
            return formatter.parse(s);
        } catch (ParseException e) {
            return null;
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
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(f);
        return formatter.format(date);

    }


    /* code for julian date conversion starts here */

    // written by devarajan on 02/13/2003 for incorporation of julian date

    public static long convertStringToJulianDate(String sDate) {
        long lJulianDate = 0;
        if (sDate != null && !sDate.trim().equals("")) {
            Date dt = new Date(sDate);
            int iYear = dt.getYear();
            int iMonth = dt.getMonth();
            int iDay = dt.getDay();
            double dbYear = iYear;
            double dbMonth = iMonth;
            double dbDate = iDay;
            double dbHour = 0;
            double dbMinute = 0;
            double dbSecond = 0;
            double dbExtra = 0;
            double dbRjd = 0;
            dbExtra = 100.0 * dbYear + dbMonth - 190002.5;
            dbRjd = 367.0 * dbYear;
            dbRjd -=
                    Math.floor(7.0 * (dbYear + Math.floor((dbMonth + 9.0) / 12.0)) / 4.0);
            dbRjd += Math.floor(275.0 * dbMonth / 9.0);
            dbRjd += dbDate;
            dbRjd += (dbHour + (dbMinute + dbSecond / 60.0) / 60.) / 24.0;
            dbRjd += 1721013.5;
            dbRjd -= 0.5 * dbExtra / Math.abs(dbExtra);
            dbRjd += 0.5;
//            System.out.println("The value of the date converted is :"+ (long)dbRjd);
            lJulianDate = (long) dbRjd;
        }

        return lJulianDate;
    }

    /* code for julian date conversion ends here */


} // End of class DateUtil
