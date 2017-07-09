/**
 * NonDestructiveEncryption
 */
package com.boa.eagls.government.encryption;

/**
 * The static methods in this class provide a means of encrypting and decripting
 * a string that is able to be passed in a URL without any further encoding.  The
 * algorithm is non-destructive but is not guarrenteed to yield identical encrypted
 * strings when called repeatedly with the same argument. However, the decryption of
 * those differring encrypted strings will always yield identical unencrypted results. <p>
 * @version1.0
 * @invariant $none
 */
public class NonDestructiveEncryption
{

    /**
     * Encrypts a string yielding an encrypted string with a length of (str.length() * 2) + 1. Used by ENCRYPT
     * @param	the string to encrypt.
     * @return	the encrypted result.
     * @pre $none
     * @post $none
     */
    public static String encrypt(String str)
    {
        String returnValue = null;

        if (str != null && str.length() > 0)
        {
            StringBuffer encryptedID = new StringBuffer();

            // put the ascii value - 32 of each char of str into string buffer
            encodeStringToBuffer(str, encryptedID);

            // reverse each group of 2 numbers i.e. 1234 ==> 2143
            reverseIntraStringCharacterGroups(encryptedID, 2);

            // get a random number between 3 and 9
            int randomInt = (int) (Math.round(Math.random() * 100) % 7) + 3;

            // reverse each group of numbers
            reverseIntraStringCharacterGroups(encryptedID, randomInt);

            // add the randon int to the front of the stringBuffer
            encryptedID.insert(0, randomInt);

            // convert each number to a letter
            convertNumericToRandomAlpha(encryptedID);
            returnValue = encryptedID.toString();
        }
        return returnValue;
    }

    /**
     * Iterates through the String taking each characters ascii value, subtacts 32,
     * and appends the result to the StringBuffer. (if the resulting int is only
     * a single digit then a "0" is first appended to the StringBuffer) Used by ENCRYPT
     * @param	String str			the string whose characters to encode.
     * @param	StringBuffer buf	an empty buffer to which the encoded string will be appended.
     * @return	void
     * @pre $none
     * @post $none
     */
    private static void encodeStringToBuffer(String str, StringBuffer buf)
    {
        if (str == null || buf == null)
        {
            return;
        }

        // place the ascii value of each letter -32 into the buffer
        for (int letter = 0; letter < str.length(); letter++)
        {
            int intLetter = str.charAt(letter) - 32;

            if (intLetter < 10)
            {
                buf.append("0");
            }
            buf.append(intLetter);
        }
    }

    /**
     * Iterates through the StringBuffer taking each number and converting it
     * to a letter such that the last digit of the letters alphabetic ordinal value matches the number being converted.
     * Example: 1 could be represented by a,k or u 2 could be represented by b,l or v etc. 0 is treated as 10 therefore
     * 0 could be represented by j or t The actual character used is randomly chosen from the possible candidates and
     * is then place back into the StringBuffer to replace the original number. Used by ENCRYPT
     * @param	StringBuffer buf	the buffer whose number will be converted to chars.
     * @return	void
     * @pre $none
     * @post $none
     */
    private static void convertNumericToRandomAlpha(StringBuffer buf)
    {
        byte byteLetter = 0;
        byte randomByte = 0;

        for (int letter = 0; letter < buf.length(); letter++)
        {

            // get the byte value of the number
            byteLetter = (byte) (buf.charAt(letter) - 48);
            if (byteLetter == 0)
            {
                byteLetter = 10;

                // generate a random number between 1 and 3 such that the number multiplied
                // by the byteLetter does not exceed 26. byteLetter of 0 is treated as 10.
            }
            randomByte = (byte) ((Math.round(Math.random() * 100)
                    % ((byteLetter > 6) ? 2 : 3)));
            byteLetter = (byte) (byteLetter + (randomByte * 10));
            buf.setCharAt(letter, (char) (byteLetter + 96));
        }
    }

    /**
     * Decrypts a string that was encrypted with this class. Used by DECRYPT
     * @param	the string encrypted with this classes encrypt().
     * @return	the decrypted result.
     * @pre $none
     * @post $none
     */
    public static String decrypt(String aStr)
    {
        String returnValue = null;

        if (aStr != null)
        {
            StringBuffer encryptedID = new StringBuffer(aStr);

            convertAlphaToNumeric(encryptedID);

            // get the group length from the first character in the string
            int groupLength = 0;    // --encryptedID.charAt(0) - 48;

            if (encryptedID.length() > 0)
            {
                groupLength = encryptedID.charAt(0) - 48;
            }

            // create a stringbuffer that contains all but the first character of the
            // current encrypted string.  deleteCharAt(0) could be used if we were
            // using jdk1.2 or higher
            String str = encryptedID.toString();

            if (str.length() > 0)
            {
                encryptedID = new StringBuffer(str.substring(1,
                        str.length()));
            }
            reverseIntraStringCharacterGroups(encryptedID, groupLength);
            reverseIntraStringCharacterGroups(encryptedID, 2);
            returnValue = decodeStringFromBuffer(encryptedID);
        }
        return returnValue;
    }

    /**
     * Iterates through the StringBuffer taking each pair of numbers, obtaining
     * the integer equvalent of the pair, adding 32 and then appending the
     * character representation of that number to the resulting String. Used by DECRYPT
     * @param	StringBuffer buf	the buffer whose characters will be decoded
     * @return	String
     * @pre $none
     * @post $none
     */
    private static String decodeStringFromBuffer(StringBuffer buf)
    {
        String returnValue = null;

        if (buf != null)
        {
            StringBuffer decodedBuffer = new StringBuffer();
            char[] pair = new char[2];

            // get the char equivelant of each number pair + 32
            for (int numberPair = 0; numberPair < buf.length() - 1;
                 numberPair += 2)
            {
                buf.getChars(numberPair, numberPair + 2, pair, 0);
                int ascii;

                try
                {
                    ascii = Integer.valueOf(new String(pair)).intValue() + 32;
                }
                catch (NumberFormatException e)
                {
                    return null;
                }
                decodedBuffer.append((char) ascii);
            }
            returnValue = decodedBuffer.toString();
        }
        return returnValue;
    }

    /**
     * Iterates through the StringBuffer taking each character and converting it
     * to a number by taking the last digit of the characters alphabetic ordinal value. Example: a,k and u are converted to 1
     * b,l and v are converted to 2 etc. 0 is treated as 10 therefore j and t are converted to 0 Used by DECRYPT
     * @param	StringBuffer buf	the buffer whose characters will be converted to numbers
     * @return	void
     * @pre $none
     * @post $none
     */
    private static void convertAlphaToNumeric(StringBuffer buf)
    {
        byte byteLetter = 0;

        for (int letter = 0; letter < buf.length(); letter++)
        {

            // get the index value of 1 to 26 of the letter and then take the modulo of 10
            byteLetter = (byte) (buf.charAt(letter) - 96);
            byteLetter = (byte) (byteLetter % 10);
            buf.setCharAt(letter, (char) (byteLetter + 48));
        }
    }

    /**
     * Iterates through the StringBuffer taking groups of characters, whose length is
     * determined by the argument groupLength, and reverses those characters. The
     * overall placement of the group within the StringBuffer is not affected. Used by ENCRYPT and DECRYPT Example:
     * StringBuffer sb = new StringBuffer("abcdefgh"); reverseIntraStringCharacterGroups(sb, 3);
     * @param	StringBuffer buf	the buffer whose characters to manipulate.
     * This buffer will also contain the resulting sequence.
     * @param	int groupLength 	the length of each character group to reverse.
     * @return	void
     * @pre $none
     * @post $none
     */
    private static void reverseIntraStringCharacterGroups(StringBuffer buf,
                                                          int groupLength)
    {
        if (buf == null || groupLength <= 0 || groupLength > buf.length())
        {
            return;
        }
        char[][] reverseBuffer =
                new char[(int) Math.ceil(buf.length() / (groupLength * 1.0f))][];

        for (int currentPosition = 0, currentGroup = 0, positionInGroup = 0;
             currentPosition < buf.length(); currentPosition++)
        {
            if (positionInGroup == 0)
            {
                reverseBuffer[currentGroup] =
                        new char[Math.min(groupLength, buf.length() - currentPosition)];
            }
            reverseBuffer[currentGroup][positionInGroup] =
                    buf.charAt(currentPosition);
            positionInGroup++;
            if (positionInGroup == groupLength)
            {
                positionInGroup = 0;
                currentGroup++;
            }
        }

        // clear the buffer so that the characters can be added back in.
        buf.setLength(0);
        for (int currentGroup = 0; currentGroup < reverseBuffer.length;
             currentGroup++)
        {
            for (int currentPosition = reverseBuffer[currentGroup].length - 1;
                 currentPosition >= 0; currentPosition--)
            {
                buf.append(reverseBuffer[currentGroup][currentPosition]);
            }
        }
    }

}
