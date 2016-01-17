package com.robertozagni.algoritmi.strings;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringGenerationTest {

    @Test
    public void canGenerateRandomStrings() throws Exception {
        assertEquals(1, StringGeneration.randomString(1).length());
        assertEquals(26, StringGeneration.randomString(26).length());
        assertEquals(64, StringGeneration.randomString(64).length());
    }

    @Test
    public void generateRandomStrings() throws Exception {

        int numOfStrings = 1000;
        int stringLength = 64;
        for (int i = 0; i < numOfStrings; i++) {
            String randomString = StringGeneration.randomString(stringLength);
            assertEquals(stringLength, randomString.length());
        }

    }

}
