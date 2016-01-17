/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.math;

/**
 * 
 * @author roberto.zagni
 *
 */
public class Digits {

    /**
     * Breaks up the provided number into an array with a single digit integer at every position.<br>
     * If the given number is negative all the digits in the returned array are negative.
     * 
     * @param number The number to break up.
     * @return an array with the digits of the number, one at every position of the array
     */
    public static int[] breakIntoDigits(long number) {

        // Symmetric positive value is out of range => - Long.MIN_VALUE == Long.MIN_VALUE !!
        if (number == Long.MIN_VALUE) { // -9223372036854775808, just return its representation.
            return new int[] { -9, -2, -2, -3, -3, -7, -2, -0, -3, -6, -8, -5, -4, -7, -7, -5, -8, -0, -8 };
        }
        if (number <= 9 && number >= -9) {
            return new int[] { (int) number };
        }

        int sgn = 1;
        if (number < 0) {
            sgn = -1;
        }

        int length = (int) Math.floor(Math.log10(number * sgn)) + 1;
        int[] digits = new int[length];

        for (int i = 0; i < length; i++) {
            digits[length - 1 - i] = (int) (number % 10);
            number = number / 10;
        }
        return digits;
    }

    /**
     * Joins the digits from the given positive single digit values array.<br>
     * The result is the number obtained by joining the digits from the array if the numbers in the array are single
     * digit numbers all with the same sign.<br>
     * If numbers in the array are not same sign single digit numbers the returned value is the base 10 weighted sum of
     * the values, instead of the simple joining of the digits.
     * 
     * @param digits the array with the digits to join, lower indices have higher positional weighting.
     * @return the number obtained by joining the digits if same sign single digit array is passed or the base 10
     *         positional weighted sum of the values from the array.
     */
    public static long joinDigits(int[] digits) {
        long number = 0;

        if (digits != null && digits.length > 0) {
            // int sgn = 1;
            // if (digits[0] < 0) {
            // sgn = -1;
            // digits[0] = digits[0] * sgn;
            // }

            long power = 1;
            for (int i = digits.length - 1; i >= 0; i--) {
                number = number + digits[i] * power;
                power *= 10;
            }

            // number = number * sgn;

        }

        return number;
    }

}
