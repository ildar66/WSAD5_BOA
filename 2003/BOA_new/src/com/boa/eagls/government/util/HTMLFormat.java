/**
 * HTMLFormat
 */
package com.boa.eagls.government.util;

/**
 * contains HTML format utilities.
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class HTMLFormat
{

    /**
     * Default private constructor
     */
    private HTMLFormat()
    {
    }

    /**
     * replaces all occurances of encodeChar with the html format '&#[asciiVal]'
     * to make characters such as '=' safe for use in input values.
     */
    public static String htmlReplace(String html, char encodeChar)
    {
        int qtIndex = html.indexOf(encodeChar);
        int lastIndex = 0;

        while (qtIndex >= 0)
        {
            boolean numNext = (html.length() > qtIndex + 1);

            if (numNext)
            {
                numNext = numNext
                        && (47 < html.charAt(qtIndex + 1)
                        & html.charAt(qtIndex + 1) < 58);
            }
            html = html.substring(0, qtIndex) + "&#" + (int) encodeChar
                    + (numNext ? " " : "")
                    + (html.length() > qtIndex + 1
                    ? html.substring(qtIndex + 1) : "");
            lastIndex = qtIndex;
            qtIndex = html.indexOf(encodeChar, lastIndex);
        }
        return html;
    }

}
