/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.strings;

public class StringReverse {

    /**
     * Reverses the string with a StringBuilder.
     * 
     * @param str the string to reverse
     * @return the string with the characters in reversed orders.
     */
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    /**
     * Reverses the string with a recursive call.
     * 
     * @param str the string to reverse
     * @return the string with the characters in reversed orders.
     */
    public static String reverseRecursive(String str) {
        if (str == null)
            return null;
        if (str.length() == 1)
            return str;
        return reverseRecursive(str.substring(1)) + str.charAt(0);
    }

    /**
     * Reverses the string with a recursive call.
     * 
     * @param str the string to reverse
     * @return the string with the characters in reversed orders.
     */
    public static String reverseTailRecursive(String str) {
        if (str == null)
            return null;
        if (str.length() == 1)
            return str;
        return reverseWithAccumulatorInsert(str, null);
    }

    /**
     * Tail recursive string reversion, removing from the end of the original string and inserting at the beginning of
     * the destination string.
     * 
     * @param toReverse the original string to be reversed.
     * @param reversed the destination string as a StringBuilder; pass a null destination to have the original reversed.
     * @return the original string reversed in front of the initial destination string.
     */
    public static String reverseWithAccumulatorInsert(String toReverse, StringBuilder reversed) {
        if (reversed == null) {
            reversed = new StringBuilder();
        }
        int length = toReverse.length();
        if (length == 0) {
            return reversed.toString();
        }
        return reverseWithAccumulatorInsert(toReverse.substring(1), reversed.insert(0, toReverse.charAt(0)));

    }

    /**
     * Tail recursive string reversion, removing from the beginning of the original string and appending at the end of
     * the destination string.
     * 
     * @param toReverse the original string to be reversed.
     * @param reversed the destination string as a StringBuilder; pass a null destination to have the original reversed.
     * @return the original string reversed appended after the initial destination string.
     */
    public static String reverseWithAccumulatorAppend(String toReverse, StringBuilder reversed) {
        if (reversed == null) {
            reversed = new StringBuilder();
        }
        int length = toReverse.length();
        if (length == 0) {
            return reversed.toString();
        }
        return reverseWithAccumulatorAppend(toReverse.substring(0, length - 1),
                reversed.append(toReverse.charAt(length - 1)));

    }

}
