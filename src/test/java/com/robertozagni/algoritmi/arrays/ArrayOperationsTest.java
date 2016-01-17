/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Roberto
 *
 */
public class ArrayOperationsTest {

    private String[] MESI = { "Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic" };
    private String[] mesi;
    private static int GIORNI = 31;
    int[] giorni;
    Integer[] giorniInteri;

    @Before
    public void setUp() throws Exception {
        mesi = Arrays.copyOf(MESI, MESI.length);

        giorni = new int[GIORNI]; // 31 days, values from 1 to 31
        giorniInteri = new Integer[GIORNI];
        for (int i = 0; i < GIORNI; i++) {
            giorni[i] = i + 1;
            giorniInteri[i] = Integer.valueOf(i + 1);
        }
    }

    @Test
    public void findDuplicatesSet() {

        assertTrue(ArrayOperations.hasDuplicatesSet("a", "b", "c", "d", "c", "a"));
        assertTrue(ArrayOperations.hasDuplicatesSet('a', 'b', 'c', 'd', 'c', 'a'));

        // Just setup, no dupes
        assertFalse(ArrayOperations.hasDuplicatesSet(mesi));
        assertFalse(ArrayOperations.hasDuplicatesSet(giorni));

        // Make dupes in te arrays
        mesi[3] = mesi[5];
        giorni[6] = giorni[3];
        giorniInteri[6] = giorniInteri[3];

        // Find out the duplicates
        assertTrue(ArrayOperations.hasDuplicatesSet(mesi));
        assertTrue(ArrayOperations.hasDuplicatesSet(giorniInteri));

        // int are not objects, so for the var args declaration the passed int[] is not an array of objects,
        // but just a single object (that happens to be an array).
        // A list with only one element can not have duplicates!
        assertFalse(ArrayOperations.hasDuplicatesSet(giorni));
        // if you pass the object multiple times, then you have duplicates!
        assertTrue(ArrayOperations.hasDuplicatesSet(giorni, giorni));

        // Autoboxing of int to Integer transforms them into objects!
        assertTrue(ArrayOperations.hasDuplicatesSet(1, 2, 3, 4, 5, 2, 7));

    }

    @Test
    public void findDuplicatesHashSet() {

        assertTrue(ArrayOperations.hasDuplicatesHashSet("a", "b", "c", "d", "c", "a"));
        assertTrue(ArrayOperations.hasDuplicatesHashSet('a', 'b', 'c', 'd', 'c', 'a'));

        // Just setup, no dupes
        assertFalse(ArrayOperations.hasDuplicatesHashSet(mesi));
        assertFalse(ArrayOperations.hasDuplicatesHashSet(giorni));

        // Make dupes in te arrays
        mesi[3] = mesi[5];
        giorni[6] = giorni[3];
        giorniInteri[6] = giorniInteri[3];

        // Find out the duplicates
        assertTrue(ArrayOperations.hasDuplicatesHashSet(mesi));
        assertTrue(ArrayOperations.hasDuplicatesHashSet(giorniInteri));

        // int are not objects, so for the var args declaration the passed int[] is not an array of objects,
        // but just a single object (that happens to be an array).
        // A list with only one element can not have duplicates!
        assertFalse(ArrayOperations.hasDuplicatesHashSet(giorni));
        // if you pass the object multiple times, then you have duplicates!
        assertTrue(ArrayOperations.hasDuplicatesHashSet(giorni, giorni));

        // Autoboxing of int to Integer transforms them into objects!
        assertTrue(ArrayOperations.hasDuplicatesHashSet(1, 2, 3, 4, 5, 2, 7));

    }

}
