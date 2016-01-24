/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.math;

/**
 * A class collecting different algorithms to perform multiplications and other related operations.
 * 
 * @author roberto.zagni
 */
public class Multiplication {

    /**
     * Performs "a x b" multiplication...<br>
     * ... with the standard Java language multiplication operation.<br>
     * !!! - No overflow checks are done.
     * 
     * @param a the first factor.
     * @param b the second factor.
     * @return the result of "a x b"
     */
    public long javaMult(long a, long b) {
        return a * b;
    }

    /**
     * Performs "a x b" multiplication...<br>
     * ... with the simple algorithm used in basic school: digit by digit multiplication and column summation.
     * 
     * @param a the first factor.
     * @param b the second factor.
     * @return the result of "a x b"
     */
    public long schoolMult(long a, long b) {
        long c = 0;
        // TODO - Implement operation :)

        return c;
    }

}
