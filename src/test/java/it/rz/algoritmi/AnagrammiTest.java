package it.rz.algoritmi;

import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
	public void testReadWords() {
		File f = createTextFileWithThreeWords();
		Map<String, List<String>> m = Anagrammi.readWords(f);
		f.delete();
		
		assertNotNull(m);
		assertFalse(m.isEmpty());
		assertEquals(3, m.size());
		
	}

	/**
	 * 
	 */
	private File createTextFileWithThreeWords() {
		File f = null;
		try {
			f = File.createTempFile("Anagrammi", null);
			Writer writer = new BufferedWriter(
								new OutputStreamWriter(
										new FileOutputStream(f), "utf-8"));
			writer.write("one\n");
			writer.write("two\n");
			writer.write("three\n");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f;
	}

	@Ignore @Test
	public void testSelectLong() {
		fail("Not yet implemented");
	}

}
