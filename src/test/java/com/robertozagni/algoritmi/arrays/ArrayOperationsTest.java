/**
 * 
 */
package com.robertozagni.algoritmi.arrays;

import java.util.Arrays;

import junit.framework.TestCase;

/**
 * @author Roberto
 *
 */
public class ArrayOperationsTest extends TestCase {

	private String[] MESI = {"Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic"};
	private String[] mesi;
	private static int GIORNI = 31;
	int[] giorni;
	Integer[] giorniInteri;
	
	/**
	 * @param name
	 */
	public ArrayOperationsTest(String name) {
		super(name);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		mesi = Arrays.copyOf(MESI, MESI.length);
		
		giorni = new int[GIORNI]; // 31 elementi, da 0 a 30
		giorniInteri = new Integer[GIORNI];
		for (int i = 0, len = giorni.length; i < len; i++){
			giorni[i] = i+1;
			giorniInteri[i] = Integer.valueOf(i+1); 
		}
	}

	/*
	 * Test 
	 */
	public void testHasDuplicatesSet() {
		// Tutto ok, nessun duplicato
		assertFalse(ArrayOperations.hasDuplicatesSet(mesi));
		assertFalse(ArrayOperations.hasDuplicatesSet(giorni));
		
		// Creazione duplicati
		mesi[3] = mesi [5];		
		giorni[6] = giorni[3];
		giorniInteri[6] = giorniInteri[3];
		
		// Rilevazione duplicati esisitenti
		assertTrue(ArrayOperations.hasDuplicatesSet(mesi));
		assertTrue(ArrayOperations.hasDuplicatesSet(giorniInteri));
		// l' int[] � un solo oggetto, non un array di oggetti
		assertFalse(ArrayOperations.hasDuplicatesSet(giorni));
	}

	/*
	 * Test 
	 */
	public void testHasDuplicatesHashSet() {
		// Tutto ok, nessun duplicato
		assertFalse(ArrayOperations.hasDuplicatesHashSet(mesi));
		assertFalse(ArrayOperations.hasDuplicatesHashSet(giorni));
		
		// Creazione duplicati
		mesi[3] = mesi [5];		
		giorni[6] = giorni[3];
		giorniInteri[6] = giorniInteri[3];
		
		// Rilevazione duplicati esisitenti
		assertTrue(ArrayOperations.hasDuplicatesHashSet(mesi));
		assertTrue(ArrayOperations.hasDuplicatesHashSet(giorniInteri));
		// l' int[] � un solo oggetto, non un array di oggetti
		assertFalse(ArrayOperations.hasDuplicatesHashSet(giorni));
	}

}
