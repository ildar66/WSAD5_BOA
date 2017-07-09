package com.boa.eagls.government.util;

/**
 * SSNumber carries some useful methods for formatting Social Security Number
 * used by Front-End and Database layers.
 * User: OlegK
 * Date: 29.06.2003
 * Time: 18:09:17
 * To change this template use Options | File Templates.
 */
public class SSNumber {
    /* use toString method if you want to insert '-'s in the SSN */
    public static String toString(String ssn) {
        String retString = new String("");
        for (int i = 0, j = 0; i < 11; i++) {
            if (i == 3 || i == 6)
                retString += '-';
            else {
                try {
                    retString += ssn.charAt(j);
                } catch (Exception e) {
                    return "";
                }
                j++;
            }
        }
        return retString;
    }

    /* use the parseString method if you want to remove the '-'s in the SSN */
    public static String parseString(String ssn) {
        if (ssn.length() == 11) {// there are '-'s in this string, hence remove them
            String retString = new String("");
            for (int i = 0; i < 11; i++) {
                if (i != 3 && i != 6)
                    retString += ssn.charAt(i);
            }
            return retString;
        } else {// not in the regular format, give back the string
            return ssn;
        }
    }
}
