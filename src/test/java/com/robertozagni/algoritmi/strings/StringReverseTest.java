package com.robertozagni.algoritmi.strings;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StringReverseTest {

    @Test
    public void reverseStrings() throws Exception {

        assertEquals("cba", StringReverse.reverse("abc"));
        assertEquals("ihggggfedcba", StringReverse.reverse("abcdefgggghi"));
        assertEquals("xz21cba", StringReverse.reverse("abc12zx"));

    }

    @Test
    public void reverseStringsRecursive() throws Exception {

        assertEquals("cba", StringReverse.reverseRecursive("abc"));
        assertEquals("ihggggfedcba", StringReverse.reverseRecursive("abcdefgggghi"));
        assertEquals("xz21cba", StringReverse.reverseRecursive("abc12zx"));

    }

    @Test
    public void reverseStringsTailRecursive() throws Exception {
        assertEquals("cba", StringReverse.reverseTailRecursive("abc"));
        assertEquals("ihggggfedcba", StringReverse.reverseTailRecursive("abcdefgggghi"));
        assertEquals("xz21cba", StringReverse.reverseTailRecursive("abc12zx"));
    }

    @Test
    public void reverseStringsTailRecursiveAppend() throws Exception {
        assertEquals("cba", StringReverse.reverseWithAccumulatorAppend("abc", null));
        assertEquals("ihggggfedcba", StringReverse.reverseWithAccumulatorAppend("abcdefgggghi", null));
        assertEquals("xz21cba", StringReverse.reverseWithAccumulatorAppend("abc12zx", null));

    }

    @Test
    public void reverseRandomStrings() throws Exception {

        int numOfStrings = 1000;
        int stringLength = 64; // len in chars
        List<String> strings = new ArrayList<String>(numOfStrings);
        for (int i = 0; i < numOfStrings; i++) {
            strings.add(StringGeneration.randomString(stringLength));
        }

        List<String> revInsert = new ArrayList<String>(numOfStrings);
        for (String string : strings) {
            revInsert.add(StringReverse.reverseWithAccumulatorInsert(string, null));
        }

        List<String> revAppend = new ArrayList<String>(numOfStrings);
        for (String string : strings) {
            revAppend.add(StringReverse.reverseWithAccumulatorInsert(string, null));
        }

        for (int i = 0; i < numOfStrings; i++) {
            assertEquals(revInsert.get(i), revAppend.get(i));
            ;
        }

    }

}
