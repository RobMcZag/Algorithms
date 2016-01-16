package com.robertozagni.algoritmi;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.robertozagni.algoritmi.Anagrammi;

public class AnagrammiTest {

	public AnagrammiTest() {
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldReturnEmptyMapForNullFile() {
		Map<String, List<String>> m = Anagrammi.readWords(null);
		
		assertNotNull(m);
		assertTrue(m.isEmpty());
		assertEquals(0, m.size());
	}
	
	@Test 
	public void shouldReturnEmptyMapForEmptyFile() {
		Map<String, List<String>> m = Anagrammi.readWords(new File(""));
		
		assertNotNull(m);
		assertTrue(m.isEmpty());
		assertEquals(0, m.size());
		
	}

	@Test
	public void shouldReturnEmptyMapForNotExistingFile() {
		Map<String, List<String>> m = Anagrammi.readWords(new File("ANotExistingFile.boh"));
		
		assertNotNull(m);
		assertTrue(m.isEmpty());
		assertEquals(0, m.size());
		
	}

	@Test
	public void shouldReadEmptyFiles() {
		int NUM_WORDS = 0;
		File f = createTextFileWithNWords(NUM_WORDS);
		Map<String, List<String>> m = Anagrammi.readWords(f);
		f.delete();
		
		assertNotNull(m);
		assertTrue(m.isEmpty());
		assertEquals(NUM_WORDS, m.size());
		
	}

	@Test
	public void shouldReadFromNotEmptyFiles() {
		int NUM_WORDS = 3;
		File f = createTextFileWithNWords(NUM_WORDS);
		Map<String, List<String>> m = Anagrammi.readWords(f);
		f.delete();
		
		assertNotNull(m);
		assertFalse(m.isEmpty());
		assertEquals(NUM_WORDS, m.size());
		
		assertTrue(m.containsKey("0_dorw"));
		assertTrue(m.get("0_dorw").contains("word_0") );
		
		assertTrue(m.containsKey("2_dorw"));
		assertTrue(m.get("2_dorw").contains("word_2") );
		
	}

	/*
	 *  Ancillary method to Tests to create a file with 3 words in it. 
	 */
	private File createTextFileWithNWords(int numberOfWords) {
		File f = null;
		try {
			f = File.createTempFile("Anagrammi", null);
			Writer writer = new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(f), "utf-8"));
			for (int i = 0; i < numberOfWords; i++) {
				writer.write("word_" + i + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	@Test(expected = java.lang.NullPointerException.class)
	public void alfabetizeShouldThrowExceptionForNullString() {
		String s = Anagrammi.alphabetize(null);
	}
	
	@Test
	public void alfabetizeShouldWorkForEmptyString() {
		String s = Anagrammi.alphabetize("");
		assertNotNull(s);
		assertEquals("", s);
	}

	@Test
	public void alfabetizeShouldWorkForRegularString() {
		String s = Anagrammi.alphabetize("ciao");
		assertNotNull(s);
		assertEquals("acio", s);
	}

	@Test
	public void alfabetizeShouldWorkForRegularStringWithSpaces() {
		String s = Anagrammi.alphabetize("Ella e laila");
		assertNotNull(s);
		assertEquals("  Eaaaeillll", s);
	}

	@Test
	public void testSelectLong() {
		List<String> list1 = new ArrayList<String>(); list1.add("uno");
		List<String> list4 = new ArrayList<String>(); 
			list4.add("uno"); list4.add("due"); list4.add("tre"); list4.add("quattro");
		HashSet<List<String>> values = new HashSet<List<String>>();
			values.add(list1); values.add(list4);
		
		List<List<String>> l = Anagrammi.selectLong(values, 2);
		
		assertEquals(1, l.size());
		assertEquals(4, l.get(0).size());
		
		
	}

}
