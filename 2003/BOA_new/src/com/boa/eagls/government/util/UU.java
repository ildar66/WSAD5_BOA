/**
 * UU
 */
package com.boa.eagls.government.util;

import java.util.*;

/**
 * UUencode/decode implementation for NAS.  Replaces \n with m and " with q
 * and & with a to support writing out to html pages in hidden fields.
 * Note: Currently doesn't support blank line before end.  Use encode and decode together and there shouldn't be a problem.
 * <p>Title: </p> <p>Description: </p> <p>Copyright: Copyright (c) 2003</p> <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class UU
{
    private static final char offset =
            (char) 32;    // all characters put in range 32-95
    private static final int maxLineLength = 45;    // must be a multiple of 3
    private static final String padding = "   ";

    /**
     * This method performs the modified uuencode on a string. All characters will be mapped to the range 32-95, with
     * double quotes mapped to 'q' and carriage returns (standard for uuencode) are replaced with 'm'.
     * & now also maps to a, this is to prevent the random pattern &# with an int value.
     * @param       string to encode
     * @return      encoded string
     * @exception   $none
     */
    public static String encode(String source)
    {
        StringBuffer encoded = new StringBuffer("beginm");
        int numFullLines = source.length() / maxLineLength;
        int remainder = source.length() % maxLineLength;
        int paddingSize = ((remainder % 3 == 0) ? 0
                : (3 - remainder % 3));
        int srcPos, encodePos;
        char[] sourceLine, encodedLine;

        for (int i = 0; i < numFullLines; i++)
        {
            sourceLine = source.substring(i * maxLineLength,
                    i * maxLineLength
                    + maxLineLength).toCharArray();
            encodedLine = new char[61];
            encodedLine[0] = maxLineLength + offset;
            for (int j = 0; j < maxLineLength / 3; j++)
            {
                srcPos = j * 3;
                encodePos = j * 4 + 1;
                encodedLine[encodePos] = (char) ((sourceLine[srcPos] >>> 2)
                        + offset);
                encodedLine[encodePos + 1] =
                        (char) (((sourceLine[srcPos] & 3) << 4)
                        + (sourceLine[srcPos + 1] >>> 4) + offset);
                encodedLine[encodePos + 2] =
                        (char) (((sourceLine[srcPos + 1] & 15) << 2)
                        + (sourceLine[srcPos + 2] >>> 6) + offset);
                encodedLine[encodePos + 3] =
                        (char) ((sourceLine[srcPos + 2] & 63) + offset);
            }
            encoded.append(new String(encodedLine));
            encoded.append('m');
        }
        sourceLine = source.substring(numFullLines * maxLineLength,
                numFullLines * maxLineLength
                + remainder).concat(padding.substring(0,
                        paddingSize)).toCharArray();
        encodedLine = new char[(remainder + paddingSize) / 3 * 4 + 1];
        encodedLine[0] = (char) (remainder + offset);
        for (int j = 0; j < sourceLine.length / 3; j++)
        {
            srcPos = j * 3;
            encodePos = j * 4 + 1;
            encodedLine[encodePos] = (char) ((sourceLine[srcPos] >>> 2)
                    + offset);
            encodedLine[encodePos + 1] =
                    (char) (((sourceLine[srcPos] & 3) << 4)
                    + (sourceLine[srcPos + 1] >>> 4) + offset);
            encodedLine[encodePos + 2] =
                    (char) (((sourceLine[srcPos + 1] & 15) << 2)
                    + (sourceLine[srcPos + 2] >>> 6) + offset);
            encodedLine[encodePos + 3] = (char) ((sourceLine[srcPos + 2] & 63)
                    + offset);
        }
        encoded.append(new String(encodedLine));
        encoded.append('m');
        encoded.append("end");
        return makeBrowserSafe(encoded.toString());
    }

    /**
     * This method performs the modified uudecode on a string encoded with the encode method.  To support the modifications
     * indicated above, 'q' will be mapped to double quotes and 'm' to a carriage returns(standard for uuencode).
     * @param       string to decode
     * @return      decoded string
     * @pre $none
     * @post $none
     */
    public static String decode(String encoded)
    {
        StringBuffer decoded = new StringBuffer();
        StringTokenizer lines =
                new StringTokenizer(removeBrowserProtection(encoded
                .replace('q', '"')), "m");

        if (!lines.hasMoreTokens() || !lines.nextToken().equals("begin"))
        {
            return null;
        }
        int decodedLineLength;

        while (lines.hasMoreElements())
        {
            String nextLine = lines.nextToken();

            if (!nextLine.equals("end"))
            {
                char[] encodedLine = nextLine.toCharArray();

                decodedLineLength = encodedLine[0] - offset;
                char[] decodedLine = new char[decodedLineLength];
                int encodePos, decodePos;

                for (int i = 0; i < encodedLine.length / 4; i++)
                {
                    decodePos = i * 3;
                    encodePos = i * 4 + 1;
                    encodedLine[encodePos] -= offset;
                    encodedLine[encodePos + 1] -= offset;
                    encodedLine[encodePos + 2] -= offset;
                    encodedLine[encodePos + 3] -= offset;
                    try
                    {
                        decodedLine[decodePos] =
                                (char) ((encodedLine[encodePos] << 2)
                                + (encodedLine[encodePos + 1] >>> 4));
                        decodedLine[decodePos + 1] =
                                (char) (((encodedLine[encodePos + 1] & 15) << 4)
                                + (encodedLine[encodePos + 2] >>> 2));
                        decodedLine[decodePos + 2] =
                                (char) (((encodedLine[encodePos + 2] & 3) << 6)
                                + encodedLine[encodePos + 3]);
                    }
                    catch (ArrayIndexOutOfBoundsException aioobe)
                    {
                        aioobe.printStackTrace();

                        // do nothing - padding is to be discarded
                    }
                }
                decoded.append(new String(decodedLine));
            }
        }
        return decoded.toString();
    }

    /**
     * undoes the replacement of & and " if the change did take place.
     */
    private static String removeBrowserProtection(String encoded)
    {
        encoded = encoded.replace('q', '"');
        encoded = encoded.replace('a', '&');
        encoded = encoded.replace('z', '\'');
        encoded = encoded.replace('c', '<');
        encoded = encoded.replace('d', '>');
        return encoded;
    }

    /**
     * replaces & and " the pattern &# as this pattern along with the following int
     * can be converted to a character of the following number by the web browser.
     */
    private static String makeBrowserSafe(String encoded)
    {
        encoded = encoded.replace('"', 'q');
        encoded = encoded.replace('&', 'a');
        encoded = encoded.replace('\'', 'z');
        encoded = encoded.replace('<', 'c');
        encoded = encoded.replace('>', 'd');
        return encoded;
    }

}
