/**
 * RIPEMD160State
 */
package com.boa.eagls.government.encryption;

import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * An object of this class holds the state of an MD5 fingerprint still being calculated. <p>
 * MD5 fingerprints are often used in conjunction with the RSA public key
 * encryption algorithm to implement digital signatures.
 * @version
 * @invariant $none
 */
public class RIPEMD160State extends Object{
    private static final Logger logger= Logger.getLogger(RIPEMD160State.class);


    /**
     * This is a hash for as much of the data added to this object
     * as possible, given that they must be added in 64 byte chunks.
     * @pre $none
     * @post $none
     */
    public RIPEMD160State()
    {
    }

    /**
     * Encrypts an array of characters using the RIPEMD-160 hash function.
     * The generated hash result will be a char array of length 40.
     * @param	input	the characters to encrypt.
     * @return	the encrypted result.
     * @pre $none
     * @post $none
     */
    public char[] encrypt(char[] input)
    {
        byte hashcode[] = new byte[20];
        int[] mdBuf = new int[5];
        int X[] = new int[16];

        // Initialize
        mdInit(mdBuf);
        int position = 0;
        char[] stringByteArray = input;

        // Process message in a 16 word chunks
        for (int nBytes = input.length; nBytes > 63; nBytes -= 64)
        {
            for (int i = 0; i < 16; i++)
            {
                X[i] = charsToInteger(stringByteArray, position);
                position += 4;
            }
            compress(mdBuf, X);
        }

        // Finish of remaining partial 16-word chunk
        mdFinish(mdBuf, stringByteArray, position, 0);
        for (int i = 0; i < 20; i += 4)
        {
            hashcode[i] = (byte) (mdBuf[i >>> 2]);
            hashcode[i + 1] = (byte) (mdBuf[i >>> 2] >>> 8);
            hashcode[i + 2] = (byte) (mdBuf[i >>> 2] >>> 16);
            hashcode[i + 3] = (byte) (mdBuf[i >>> 2] >>> 24);
        }
        return hashToHexChars(hashcode);
    }

    /**
     * Hex String conversion table.
     */
    private static final char[] hexTable =
            {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'
            };

    /**
     * Converts a byte array into a Hex String (in char array form) without dropping leading zeros.
     * @param	hash	an array of bytes.
     * @return	an array of char containing the Hex representation of <code>hash</code>.
     * @pre $none
     * @post $none
     */
    private char[] hashToHexChars(byte[] hash)
    {
        char[] buf = new char[hash.length * 2];

        for (short i = 0; i < hash.length; i++)
        {
            byte b = hash[i];

            buf[2 * i] = hexTable[(b >>> 4) & 0xf];
            buf[2 * i + 1] = hexTable[b & 0x0f];
        }
        return buf;
    }

    /**
     * Method declaration
     *
     *
     * @param mdBuf
     */
    private void mdInit(int[] mdBuf)
    {
        mdBuf[0] = 0x67452301;
        mdBuf[1] = 0xefcdab89;
        mdBuf[2] = 0x98badcfe;
        mdBuf[3] = 0x10325476;
        mdBuf[4] = 0xc3d2e1f0;
    }

    /**
     * Method declaration
     *
     *
     * @param x
     * @param n
     *
     * @return
     */
    private static int rotateLeft(int x, int n)
    {
        return (x << n) | (x >>> (32 - n));
    }

    // Five basic Functions

    /**
     * Method declaration
     *
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    private static int F(int x, int y, int z)
    {
        return ((x) ^ (y) ^ (z));
    }

    /**
     * Method declaration
     *
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    private static int G(int x, int y, int z)
    {
        return (((x) & (y)) | (~(x) & (z)));
    }

    /**
     * Method declaration
     *
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    private static int H(int x, int y, int z)
    {
        return (((x) | ~(y)) ^ (z));
    }

    /**
     * Method declaration
     *
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    private static int I(int x, int y, int z)
    {
        return (((x) & (z)) | ((y) & ~(z)));
    }

    /**
     * Method declaration
     *
     *
     * @param x
     * @param y
     * @param z
     *
     * @return
     */
    private static int J(int x, int y, int z)
    {
        return ((x) ^ ((y) | ~(z)));
    }

    // Ten basic Operation

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int FF(int a, int b, int c, int d, int e, int x, int s)
    {
        a += F((b), (c), (d)) + (x);
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int GG(int a, int b, int c, int d, int e, int x, int s)
    {
        a += G((b), (c), (d)) + (x) + 0x5a827999;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int HH(int a, int b, int c, int d, int e, int x, int s)
    {
        a += H((b), (c), (d)) + (x) + 0x6ed9eba1;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int II(int a, int b, int c, int d, int e, int x, int s)
    {
        a += I((b), (c), (d)) + (x) + 0x8f1bbcdc;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int JJ(int a, int b, int c, int d, int e, int x, int s)
    {
        a += J((b), (c), (d)) + (x) + 0xa953fd4e;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int FFF(int a, int b, int c, int d, int e, int x, int s)
    {
        a += F((b), (c), (d)) + (x);
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int GGG(int a, int b, int c, int d, int e, int x, int s)
    {
        a += G((b), (c), (d)) + (x) + 0x7a6d76e9;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int HHH(int a, int b, int c, int d, int e, int x, int s)
    {
        a += H((b), (c), (d)) + (x) + 0x6d703ef3;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int III(int a, int b, int c, int d, int e, int x, int s)
    {
        a += I((b), (c), (d)) + (x) + 0x5c4dd124;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Method declaration
     *
     *
     * @param a
     * @param b
     * @param c
     * @param d
     * @param e
     * @param x
     * @param s
     *
     * @return
     */
    private static int JJJ(int a, int b, int c, int d, int e, int x, int s)
    {
        a += J((b), (c), (d)) + (x) + 0x50a28be6;
        a = rotateLeft((a), (s)) + e;
        return a;
    }

    /**
     * Completes the encryption of a string when there are less than 16 words left in the string.
     * @param	mdBuf	the encrypted result.
     * @param	charArray	the source string as an array of characters.
     * @param	position	the first element in <code>stringByteArray</code> that remains to be encrypted.
     * @param	mswlen		set to 0.
     */
    private void mdFinish(int[] mdBuf, char[] charArray, int position,
                          int mswlen)
    {
        int lswlen = charArray.length;
        int X[] =
                {
                    0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
                };

        for (int i = 0; i < (lswlen & 63); i++)
        {

            // byte i  goes into word X[i div 4] at position 8*(i mod 4)
            X[i >>> 2] ^= (int) charArray[position++] << (8 * (i & 3));
        }
        X[(lswlen >>> 2) & 15] ^= (int) 1 << (8 * (lswlen & 3) + 7);
        if ((lswlen & 63) > 55)
        {
            logger.debug("\tin flush condition");
            compress(mdBuf, X);
            for (int i = 0; i < 16; i++)
            {
                X[i] = 0;
            }
        }

        // Append length in bits
        X[14] = lswlen << 3;
        X[15] = ((lswlen >>> 29) | (mswlen << 3));
        compress(mdBuf, X);
    }

    /**
     * Copies the nextColumn 4 characters from an array into an integer in reverse
     * order.  That is, the 1st character becomes the least significant byte
     * in the integer and the 4th character becomes the most significant byte.
     * @param	cArr	an array of characters.
     * @param	pos		the starting position in cArr
     * @return	an integer containing the nextColumn 4 values of cArr, starting at <code>pos</code> in reverse order.
     */
    private int charsToInteger(char[] cArr, int pos)
    {
        return ((int) cArr[pos + 3] << 24) | ((int) cArr[pos + 2] << 16)
                | ((int) cArr[pos + 2] << 8) | (int) cArr[pos];
    }

    /**
     * Compresses bytes in <code>x</code> into integers in <code>mdBuf</code> using the RIPEMD160 hashing algorithm.
     * @param	mdBuf	an <code>int</code> array of length 5.
     * @param	x		a <code>byte</code> array of length 20.
     */
    private void compress(int[] mdBuf, int[] x)
    {
        int a = mdBuf[0];
        int b = mdBuf[1];
        int c = mdBuf[2];
        int d = mdBuf[3];
        int e = mdBuf[4];
        int aa = mdBuf[0];
        int bb = mdBuf[1];
        int cc = mdBuf[2];
        int dd = mdBuf[3];
        int ee = mdBuf[4];

        // Round 1
        a = FF(a, b, c, d, e, x[0], 11);	    // 1
        c = rotateLeft((c), 10);
        e = FF(e, a, b, c, d, x[1], 14);	    // 2
        b = rotateLeft((b), 10);
        d = FF(d, e, a, b, c, x[2], 15);	    // 3
        a = rotateLeft((a), 10);
        c = FF(c, d, e, a, b, x[3], 12);	    // 4
        e = rotateLeft((e), 10);
        b = FF(b, c, d, e, a, x[4], 5);		    // 5
        d = rotateLeft((d), 10);
        a = FF(a, b, c, d, e, x[5], 8);		    // 6
        c = rotateLeft((c), 10);
        e = FF(e, a, b, c, d, x[6], 7);		    // 7
        b = rotateLeft((b), 10);
        d = FF(d, e, a, b, c, x[7], 9);		    // 8
        a = rotateLeft((a), 10);
        c = FF(c, d, e, a, b, x[8], 11);	    // 9
        e = rotateLeft((e), 10);
        b = FF(b, c, d, e, a, x[9], 13);	    // 10
        d = rotateLeft((d), 10);
        a = FF(a, b, c, d, e, x[10], 14);	    // 11
        c = rotateLeft((c), 10);
        e = FF(e, a, b, c, d, x[11], 15);	    // 12
        b = rotateLeft((b), 10);
        d = FF(d, e, a, b, c, x[12], 6);	    // 13
        a = rotateLeft((a), 10);
        c = FF(c, d, e, a, b, x[13], 7);	    // 14
        e = rotateLeft((e), 10);
        b = FF(b, c, d, e, a, x[14], 9);	    // 15
        d = rotateLeft((d), 10);
        a = FF(a, b, c, d, e, x[15], 8);	    // 16
        c = rotateLeft((c), 10);

        // Round 2
        e = GG(e, a, b, c, d, x[7], 7);		    // 1
        b = rotateLeft((b), 10);
        d = GG(d, e, a, b, c, x[4], 6);		    // 2
        a = rotateLeft((a), 10);
        c = GG(c, d, e, a, b, x[13], 8);	    // 3
        e = rotateLeft((e), 10);
        b = GG(b, c, d, e, a, x[1], 13);	    // 4
        d = rotateLeft((d), 10);
        a = GG(a, b, c, d, e, x[10], 11);	    // 5
        c = rotateLeft((c), 10);
        e = GG(e, a, b, c, d, x[6], 9);		    // 6
        b = rotateLeft((b), 10);
        d = GG(d, e, a, b, c, x[15], 7);	    // 7
        a = rotateLeft((a), 10);
        c = GG(c, d, e, a, b, x[3], 15);	    // 8
        e = rotateLeft((e), 10);
        b = GG(b, c, d, e, a, x[12], 7);	    // 9
        d = rotateLeft((d), 10);
        a = GG(a, b, c, d, e, x[0], 12);	    // 10
        c = rotateLeft((c), 10);
        e = GG(e, a, b, c, d, x[9], 15);	    // 11
        b = rotateLeft((b), 10);
        d = GG(d, e, a, b, c, x[5], 9);		    // 12
        a = rotateLeft((a), 10);
        c = GG(c, d, e, a, b, x[2], 11);	    // 13
        e = rotateLeft((e), 10);
        b = GG(b, c, d, e, a, x[14], 7);	    // 14
        d = rotateLeft((d), 10);
        a = GG(a, b, c, d, e, x[11], 13);	    // 15
        c = rotateLeft((c), 10);
        e = GG(e, a, b, c, d, x[8], 12);	    // 16
        b = rotateLeft((b), 10);

        // Round 3
        d = HH(d, e, a, b, c, x[3], 11);	    // 1
        a = rotateLeft((a), 10);
        c = HH(c, d, e, a, b, x[10], 13);	    // 2
        e = rotateLeft((e), 10);
        b = HH(b, c, d, e, a, x[14], 6);	    // 3
        d = rotateLeft((d), 10);
        a = HH(a, b, c, d, e, x[4], 7);		    // 4
        c = rotateLeft((c), 10);
        e = HH(e, a, b, c, d, x[9], 14);	    // 5
        b = rotateLeft((b), 10);
        d = HH(d, e, a, b, c, x[15], 9);	    // 6
        a = rotateLeft((a), 10);
        c = HH(c, d, e, a, b, x[8], 13);	    // 7
        e = rotateLeft((e), 10);
        b = HH(b, c, d, e, a, x[1], 15);	    // 8
        d = rotateLeft((d), 10);
        a = HH(a, b, c, d, e, x[2], 14);	    // 9
        c = rotateLeft((c), 10);
        e = HH(e, a, b, c, d, x[7], 8);		    // 10
        b = rotateLeft((b), 10);
        d = HH(d, e, a, b, c, x[0], 13);	    // 11
        a = rotateLeft((a), 10);
        c = HH(c, d, e, a, b, x[6], 6);		    // 12
        e = rotateLeft((e), 10);
        b = HH(b, c, d, e, a, x[13], 5);	    // 13
        d = rotateLeft((d), 10);
        a = HH(a, b, c, d, e, x[11], 12);	    // 14
        c = rotateLeft((c), 10);
        e = HH(e, a, b, c, d, x[5], 7);		    // 15
        b = rotateLeft((b), 10);
        d = HH(d, e, a, b, c, x[12], 5);	    // 16
        a = rotateLeft((a), 10);

        // Round 4
        c = II(c, d, e, a, b, x[1], 11);	    // 1
        e = rotateLeft((e), 10);
        b = II(b, c, d, e, a, x[9], 12);	    // 2
        d = rotateLeft((d), 10);
        a = II(a, b, c, d, e, x[11], 14);	    // 3
        c = rotateLeft((c), 10);
        e = II(e, a, b, c, d, x[10], 15);	    // 4
        b = rotateLeft((b), 10);
        d = II(d, e, a, b, c, x[0], 14);	    // 5
        a = rotateLeft((a), 10);
        c = II(c, d, e, a, b, x[8], 15);	    // 6
        e = rotateLeft((e), 10);
        b = II(b, c, d, e, a, x[12], 9);	    // 7
        d = rotateLeft((d), 10);
        a = II(a, b, c, d, e, x[4], 8);		    // 8
        c = rotateLeft((c), 10);
        e = II(e, a, b, c, d, x[13], 9);	    // 9
        b = rotateLeft((b), 10);
        d = II(d, e, a, b, c, x[3], 14);	    // 10
        a = rotateLeft((a), 10);
        c = II(c, d, e, a, b, x[7], 5);		    // 11
        e = rotateLeft((e), 10);
        b = II(b, c, d, e, a, x[15], 6);	    // 12
        d = rotateLeft((d), 10);
        a = II(a, b, c, d, e, x[14], 8);	    // 13
        c = rotateLeft((c), 10);
        e = II(e, a, b, c, d, x[5], 6);		    // 14
        b = rotateLeft((b), 10);
        d = II(d, e, a, b, c, x[6], 5);		    // 15
        a = rotateLeft((a), 10);
        c = II(c, d, e, a, b, x[2], 12);	    // 16
        e = rotateLeft((e), 10);

        // Round 5
        b = JJ(b, c, d, e, a, x[4], 9);		    // 1
        d = rotateLeft((d), 10);
        a = JJ(a, b, c, d, e, x[0], 15);	    // 2
        c = rotateLeft((c), 10);
        e = JJ(e, a, b, c, d, x[5], 5);		    // 3
        b = rotateLeft((b), 10);
        d = JJ(d, e, a, b, c, x[9], 11);	    // 4
        a = rotateLeft((a), 10);
        c = JJ(c, d, e, a, b, x[7], 6);		    // 5
        e = rotateLeft((e), 10);
        b = JJ(b, c, d, e, a, x[12], 8);	    // 6
        d = rotateLeft((d), 10);
        a = JJ(a, b, c, d, e, x[2], 13);	    // 7
        c = rotateLeft((c), 10);
        e = JJ(e, a, b, c, d, x[10], 12);	    // 8
        b = rotateLeft((b), 10);
        d = JJ(d, e, a, b, c, x[14], 5);	    // 9
        a = rotateLeft((a), 10);
        c = JJ(c, d, e, a, b, x[1], 12);	    // 10
        e = rotateLeft((e), 10);
        b = JJ(b, c, d, e, a, x[3], 13);	    // 11
        d = rotateLeft((d), 10);
        a = JJ(a, b, c, d, e, x[8], 14);	    // 12
        c = rotateLeft((c), 10);
        e = JJ(e, a, b, c, d, x[11], 11);	    // 13
        b = rotateLeft((b), 10);
        d = JJ(d, e, a, b, c, x[6], 8);		    // 14
        a = rotateLeft((a), 10);
        c = JJ(c, d, e, a, b, x[15], 5);	    // 15
        e = rotateLeft((e), 10);
        b = JJ(b, c, d, e, a, x[13], 6);	    // 16
        d = rotateLeft((d), 10);

        // Parallel round 1
        aa = JJJ(aa, bb, cc, dd, ee, x[5], 8);      // 1
        cc = rotateLeft((cc), 10);
        ee = JJJ(ee, aa, bb, cc, dd, x[14], 9);     // 2
        bb = rotateLeft((bb), 10);
        dd = JJJ(dd, ee, aa, bb, cc, x[7], 9);      // 3
        aa = rotateLeft((aa), 10);
        cc = JJJ(cc, dd, ee, aa, bb, x[0], 11);     // 4
        ee = rotateLeft((ee), 10);
        bb = JJJ(bb, cc, dd, ee, aa, x[9], 13);     // 5
        dd = rotateLeft((dd), 10);
        aa = JJJ(aa, bb, cc, dd, ee, x[2], 15);     // 6
        cc = rotateLeft((cc), 10);
        ee = JJJ(ee, aa, bb, cc, dd, x[11], 15);    // 7
        bb = rotateLeft((bb), 10);
        dd = JJJ(dd, ee, aa, bb, cc, x[4], 5);      // 8
        aa = rotateLeft((aa), 10);
        cc = JJJ(cc, dd, ee, aa, bb, x[13], 7);     // 9
        ee = rotateLeft((ee), 10);
        bb = JJJ(bb, cc, dd, ee, aa, x[6], 7);      // 10
        dd = rotateLeft((dd), 10);
        aa = JJJ(aa, bb, cc, dd, ee, x[15], 8);     // 11
        cc = rotateLeft((cc), 10);
        ee = JJJ(ee, aa, bb, cc, dd, x[8], 11);     // 12
        bb = rotateLeft((bb), 10);
        dd = JJJ(dd, ee, aa, bb, cc, x[1], 14);     // 13
        aa = rotateLeft((aa), 10);
        cc = JJJ(cc, dd, ee, aa, bb, x[10], 14);    // 14
        ee = rotateLeft((ee), 10);
        bb = JJJ(bb, cc, dd, ee, aa, x[3], 12);     // 15
        dd = rotateLeft((dd), 10);
        aa = JJJ(aa, bb, cc, dd, ee, x[12], 6);     // 16
        cc = rotateLeft((cc), 10);

        // Parallel round 2
        ee = III(ee, aa, bb, cc, dd, x[6], 9);      // 1
        bb = rotateLeft((bb), 10);
        dd = III(dd, ee, aa, bb, cc, x[11], 13);    // 2
        aa = rotateLeft((aa), 10);
        cc = III(cc, dd, ee, aa, bb, x[3], 15);     // 3
        ee = rotateLeft((ee), 10);
        bb = III(bb, cc, dd, ee, aa, x[7], 7);      // 4
        dd = rotateLeft((dd), 10);
        aa = III(aa, bb, cc, dd, ee, x[0], 12);     // 5
        cc = rotateLeft((cc), 10);
        ee = III(ee, aa, bb, cc, dd, x[13], 8);     // 6
        bb = rotateLeft((bb), 10);
        dd = III(dd, ee, aa, bb, cc, x[5], 9);      // 7
        aa = rotateLeft((aa), 10);
        cc = III(cc, dd, ee, aa, bb, x[10], 11);    // 8
        ee = rotateLeft((ee), 10);
        bb = III(bb, cc, dd, ee, aa, x[14], 7);     // 9
        dd = rotateLeft((dd), 10);
        aa = III(aa, bb, cc, dd, ee, x[15], 7);     // 10
        cc = rotateLeft((cc), 10);
        ee = III(ee, aa, bb, cc, dd, x[8], 12);     // 12
        bb = rotateLeft((bb), 10);
        dd = III(dd, ee, aa, bb, cc, x[12], 7);     // 7
        aa = rotateLeft((aa), 10);
        cc = III(cc, dd, ee, aa, bb, x[4], 6);      // 14
        ee = rotateLeft((ee), 10);
        bb = III(bb, cc, dd, ee, aa, x[9], 15);     // 15
        dd = rotateLeft((dd), 10);
        aa = III(aa, bb, cc, dd, ee, x[1], 13);     // 11
        cc = rotateLeft((cc), 10);
        ee = III(ee, aa, bb, cc, dd, x[2], 11);     // 12
        bb = rotateLeft((bb), 10);

        // Parallel round 3
        dd = HHH(dd, ee, aa, bb, cc, x[15], 9);     // 2
        aa = rotateLeft((aa), 10);
        cc = HHH(cc, dd, ee, aa, bb, x[5], 7);      // 3
        ee = rotateLeft((ee), 10);
        bb = HHH(bb, cc, dd, ee, aa, x[1], 15);     // 4
        dd = rotateLeft((dd), 10);
        aa = HHH(aa, bb, cc, dd, ee, x[3], 11);     // 5
        cc = rotateLeft((cc), 10);
        ee = HHH(ee, aa, bb, cc, dd, x[7], 8);      // 1
        bb = rotateLeft((bb), 10);
        dd = HHH(dd, ee, aa, bb, cc, x[14], 6);     // 2
        aa = rotateLeft((aa), 10);
        cc = HHH(cc, dd, ee, aa, bb, x[6], 6);      // 3
        ee = rotateLeft((ee), 10);
        bb = HHH(bb, cc, dd, ee, aa, x[9], 14);     // 4
        dd = rotateLeft((dd), 10);
        aa = HHH(aa, bb, cc, dd, ee, x[11], 12);    // 5
        cc = rotateLeft((cc), 10);
        ee = HHH(ee, aa, bb, cc, dd, x[8], 13);     // 6
        bb = rotateLeft((bb), 10);
        dd = HHH(dd, ee, aa, bb, cc, x[12], 5);     // 7
        aa = rotateLeft((aa), 10);
        cc = HHH(cc, dd, ee, aa, bb, x[2], 14);     // 8
        ee = rotateLeft((ee), 10);
        bb = HHH(bb, cc, dd, ee, aa, x[10], 13);    // 9
        dd = rotateLeft((dd), 10);
        aa = HHH(aa, bb, cc, dd, ee, x[0], 13);     // 10
        cc = rotateLeft((cc), 10);
        ee = HHH(ee, aa, bb, cc, dd, x[4], 7);      // 12
        bb = rotateLeft((bb), 10);
        dd = HHH(dd, ee, aa, bb, cc, x[13], 5);     // 7
        aa = rotateLeft((aa), 10);

        // Parallel Round 4
        cc = GGG(cc, dd, ee, aa, bb, x[8], 15);     // 3
        ee = rotateLeft((ee), 10);
        bb = GGG(bb, cc, dd, ee, aa, x[6], 5);      // 4
        dd = rotateLeft((dd), 10);
        aa = GGG(aa, bb, cc, dd, ee, x[4], 8);      // 5
        cc = rotateLeft((cc), 10);
        ee = GGG(ee, aa, bb, cc, dd, x[1], 11);     // 1
        bb = rotateLeft((bb), 10);
        dd = GGG(dd, ee, aa, bb, cc, x[3], 14);     // 2
        aa = rotateLeft((aa), 10);
        cc = GGG(cc, dd, ee, aa, bb, x[11], 14);    // 3
        ee = rotateLeft((ee), 10);
        bb = GGG(bb, cc, dd, ee, aa, x[15], 6);     // 4
        dd = rotateLeft((dd), 10);
        aa = GGG(aa, bb, cc, dd, ee, x[0], 14);     // 5
        cc = rotateLeft((cc), 10);
        ee = GGG(ee, aa, bb, cc, dd, x[5], 6);      // 6
        bb = rotateLeft((bb), 10);
        dd = GGG(dd, ee, aa, bb, cc, x[12], 9);     // 7
        aa = rotateLeft((aa), 10);
        cc = GGG(cc, dd, ee, aa, bb, x[2], 12);     // 8
        ee = rotateLeft((ee), 10);
        bb = GGG(bb, cc, dd, ee, aa, x[13], 9);     // 9
        dd = rotateLeft((dd), 10);
        aa = GGG(aa, bb, cc, dd, ee, x[9], 12);     // 10
        cc = rotateLeft((cc), 10);
        ee = GGG(ee, aa, bb, cc, dd, x[7], 5);      // 12
        bb = rotateLeft((bb), 10);
        dd = GGG(dd, ee, aa, bb, cc, x[10], 15);    // 7
        aa = rotateLeft((aa), 10);
        cc = GGG(cc, dd, ee, aa, bb, x[14], 8);     // 8
        ee = rotateLeft((ee), 10);

        // Parallel Round 5
        bb = FFF(bb, cc, dd, ee, aa, x[12], 8);     // 4
        dd = rotateLeft((dd), 10);
        aa = FFF(aa, bb, cc, dd, ee, x[15], 5);     // 5
        cc = rotateLeft((cc), 10);
        ee = FFF(ee, aa, bb, cc, dd, x[10], 12);    // 1
        bb = rotateLeft((bb), 10);
        dd = FFF(dd, ee, aa, bb, cc, x[4], 9);      // 2
        aa = rotateLeft((aa), 10);
        cc = FFF(cc, dd, ee, aa, bb, x[1], 12);     // 3
        ee = rotateLeft((ee), 10);
        bb = FFF(bb, cc, dd, ee, aa, x[5], 5);      // 4
        dd = rotateLeft((dd), 10);
        aa = FFF(aa, bb, cc, dd, ee, x[8], 14);     // 5
        cc = rotateLeft((cc), 10);
        ee = FFF(ee, aa, bb, cc, dd, x[7], 6);      // 6
        bb = rotateLeft((bb), 10);
        dd = FFF(dd, ee, aa, bb, cc, x[6], 8);      // 7
        aa = rotateLeft((aa), 10);
        cc = FFF(cc, dd, ee, aa, bb, x[2], 13);     // 8
        ee = rotateLeft((ee), 10);
        bb = FFF(bb, cc, dd, ee, aa, x[13], 6);     // 9
        dd = rotateLeft((dd), 10);
        aa = FFF(aa, bb, cc, dd, ee, x[14], 5);     // 10
        cc = rotateLeft((cc), 10);
        ee = FFF(ee, aa, bb, cc, dd, x[0], 15);     // 12
        bb = rotateLeft((bb), 10);
        dd = FFF(dd, ee, aa, bb, cc, x[3], 13);     // 7
        aa = rotateLeft((aa), 10);
        cc = FFF(cc, dd, ee, aa, bb, x[9], 11);     // 8
        ee = rotateLeft((ee), 10);
        bb = FFF(bb, cc, dd, ee, aa, x[11], 11);    // 9
        dd = rotateLeft((dd), 10);

        // Combined Results
        dd += c + mdBuf[1];
        mdBuf[1] = mdBuf[2] + d + ee;
        mdBuf[2] = mdBuf[3] + e + aa;
        mdBuf[3] = mdBuf[4] + a + bb;
        mdBuf[4] = mdBuf[0] + b + cc;
        mdBuf[0] = dd;
    }

    /**
     * @param argv
     * @pre $none
     * @post $none
     */

    /**
     * Use to test the RIPEMD-160 hash function.  Running this class with a single argument will encrypt that argument.
     */
    public static void main(String[] argv)
    {
        char[] input = argv.length > 0 ? argv[0].toCharArray() : new char[0];

        // char[] input = new char[1000000];
        // for (int i = 0; i < 1000000; i++) { input[i] = 'a'; }
        logger.debug("Input Length: " + input.length);
        RIPEMD160State encryptor = new RIPEMD160State();
        char[] hash = encryptor.encrypt(input);

        try
        {
            System.in.read();
        }
        catch (IOException e)
        {
            logger.error(e.getMessage(), e);
        }
    }

}    // End of class RIPEMD160State
