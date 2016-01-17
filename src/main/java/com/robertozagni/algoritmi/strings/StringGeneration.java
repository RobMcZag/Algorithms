package com.robertozagni.algoritmi.strings;

import java.math.BigInteger;

public class StringGeneration {

    private static final java.util.Random rnd = new java.util.Random();

    /**
     * Generate a random alphanumeric string of desired length.<br>
     * Every character in the returned string represents exactly 5 random bits.
     * 
     * @param len the desired length in characters
     * @return a randomly generated string of desired length.
     */
    public static String randomString(int len) {
        int bitsPerChar = 5; // With 32 = 2^5 chars every char represents 5 bits.

        String string = new BigInteger(len * bitsPerChar, rnd).toString(32);
        int slen = string.length();

        while (slen < len) {
            string += new BigInteger((len - slen) * bitsPerChar, rnd).toString(32);
            slen = string.length();
        }

        return string;
    }

}
