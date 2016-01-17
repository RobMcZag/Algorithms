/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.math;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DigitsTest {

    @Test
    public void canBreakUpNumberIntoDigits() {
        long number = 123456;
        int[] digitsExpected = { 1, 2, 3, 4, 5, 6 };

        int[] digitsActual = Digits.breakIntoDigits(number);
        assertEquals(digitsExpected.length, digitsActual.length);
        assertArrayEquals(digitsExpected, digitsActual);

        assertArrayEquals(new int[] { 0 }, Digits.breakIntoDigits(0));
        assertArrayEquals(new int[] { 1 }, Digits.breakIntoDigits(1));
        assertArrayEquals(new int[] { 9, 9, 9 }, Digits.breakIntoDigits(999));
        assertArrayEquals(new int[] { 1, 0, 0, 0 }, Digits.breakIntoDigits(1000));
        assertArrayEquals(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 }, Digits.breakIntoDigits(1234567890L));

        assertArrayEquals(new int[] { -1 }, Digits.breakIntoDigits(-1));
        assertArrayEquals(new int[] { -9, -9, -9 }, Digits.breakIntoDigits(-999));
        assertArrayEquals(new int[] { -1, -2, -3, -4, -5, -6, -7, -8, -9, -0 }, Digits.breakIntoDigits(-1234567890L));
    }

    @Test
    public void canBreakUpMaxAndMinLong() throws Exception {
        assertArrayEquals(new int[] { 9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 7 },
                Digits.breakIntoDigits(Long.MAX_VALUE)); // 9223372036854775807
        assertArrayEquals(new int[] { -9, -2, -2, -3, -3, -7, -2, -0, -3, -6, -8, -5, -4, -7, -7, -5, -8, -0, -8 },
                Digits.breakIntoDigits(Long.MIN_VALUE)); // -9223372036854775808

    }

    @Test
    public void canJoinBackMaxAndMinLong() throws Exception {
        assertEquals(Long.MAX_VALUE, // 9223372036854775807
                Digits.joinDigits(new int[] { 9, 2, 2, 3, 3, 7, 2, 0, 3, 6, 8, 5, 4, 7, 7, 5, 8, 0, 7 }));
        assertEquals(Long.MIN_VALUE, Digits // -9223372036854775808
                .joinDigits(new int[] { -9, -2, -2, -3, -3, -7, -2, -0, -3, -6, -8, -5, -4, -7, -7, -5, -8, -0, -8 }));

    }

    @Test
    public void canJoinUpSameSignSingleDigitsArrays() throws Exception {
        int[] digits = { 1, 2, 3, 4, 5, 6 };
        long numberExpected = 123456;

        long numberActual = Digits.joinDigits(digits);
        assertEquals(numberExpected, numberActual);

        assertEquals(0, Digits.joinDigits(new int[] {}));
        assertEquals(0, Digits.joinDigits(new int[] { 0 }));
        assertEquals(90 + 9, Digits.joinDigits(new int[] { 9, 9 }));
        assertEquals(1000, Digits.joinDigits(new int[] { 1, 0, 0, 0 }));

        assertEquals(-1, Digits.joinDigits(new int[] { -1 }));
        assertEquals(-99, Digits.joinDigits(new int[] { -9, -9 }));
        assertEquals(-1000, Digits.joinDigits(new int[] { -1, 0, 0, 0 }));

        // Positional weights keep working for multi digit values into array
        assertEquals(24, Digits.joinDigits(new int[] { 1, 14 }));
        assertEquals(1024, Digits.joinDigits(new int[] { 1, 1014 }));
    }

    @Test
    public void returnZeroOnJoinNullOrEmptyArray() throws Exception {
        assertEquals(0, Digits.joinDigits(null));
        assertEquals(0, Digits.joinDigits(new int[] {}));
    }

    @Test
    public void positionalWeightingWithMixedSignsInTheArray() throws Exception {

        assertEquals(90 - 9, Digits.joinDigits(new int[] { 9, -9 }));
        assertEquals(9000 - 900 - 20 + 3, Digits.joinDigits(new int[] { 9, -9, -2, 3 }));
        assertEquals(-9000 - 900 - 20 + 3, Digits.joinDigits(new int[] { -9, -9, -2, 3 }));
        assertEquals(-9000 + 0 + 50 - 3, Digits.joinDigits(new int[] { -9, 0, 5, -3 }));

    }

}
