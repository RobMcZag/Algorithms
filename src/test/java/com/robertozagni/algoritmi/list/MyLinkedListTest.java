package com.robertozagni.algoritmi.list;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.robertozagni.algoritmi.list.MyLinkedList;

public class MyLinkedListTest {

	// Nodo vuoto
	private MyLinkedList.Node<Integer> n0;
	
	// Nodi singoli
	private MyLinkedList.Node<Integer> n1;
	private MyLinkedList.Node<Integer> n2;
	private MyLinkedList.Node<Integer> n3;
	private MyLinkedList.Node<Integer> n4;
	
	// Nodi collegati: n5=>n6=>n7.
	private MyLinkedList.Node<Integer> n7;
	private MyLinkedList.Node<Integer> n6;
	private MyLinkedList.Node<Integer> n5;

	/**
	 * Crea i nodi da usare nei test.
	 * I nodo vengono ricreati "puliti" prima di ogni singolo test.
	 */
	@Before 
	public void setUp() throws Exception {
		// Crea un nodo vuoto
		n0 = new MyLinkedList.Node<Integer>();
		
		// Crea un nodo vuoto e lo riempie
		n1 = new MyLinkedList.Node<Integer>();
		n1.setValue(Integer.valueOf(1));
		
		// Crea alcuni nodi separati
		n2 = new MyLinkedList.Node<Integer>(Integer.valueOf(2));
		n3 = new MyLinkedList.Node<Integer>(Integer.valueOf(3));
		n4 = new MyLinkedList.Node<Integer>(Integer.valueOf(4));
		
		// Crea una serie di nodi collegati: n5=>n6=>n7.
		n7 = new MyLinkedList.Node<Integer>(Integer.valueOf(7));
		n6 = new MyLinkedList.Node<Integer>(Integer.valueOf(6), n7);
		n5 = new MyLinkedList.Node<Integer>(Integer.valueOf(5), n6);
	}
	
	/* *****************************************
	 * Test dei costruttori di MyLinkedList
	 */
	
	@Test
	public void aLinkedListCanBeCreated() {
		MyLinkedList sut = new MyLinkedList();
		
		assertNotNull(sut);
	}

	@Test
	public void anEmptyLinkedListCanBeCreated() {
		MyLinkedList lista = new MyLinkedList();
		assertEquals("Lista vuota = Zero elementi", 0, lista.getCount());
		assertEquals("Lista vuota => Head null", null, lista.getHead());
		assertEquals("Lista vuota => Last null", null, lista.getLast());
	}
		
	@Test
	public void aLinkedListWithOneElementCanBeCreated() {
		MyLinkedList lista = new MyLinkedList(n1);
		
		assertEquals("Lista con n1 = 1 elemento", 1, lista.getCount());
		assertEquals("Lista con n1 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertEquals("Lista con n1 => Last vale 1", 1, ((Integer)lista.getLast().getValue()).intValue());
	}

	@Test
	public void aLinkedListWithManyElementsCanBeCreated() {
		MyLinkedList lista = new MyLinkedList(n5);

		assertEquals("Lista con n5=>n7 = 3 elementi", 3, lista.getCount());
		assertEquals("Lista con n5=>n7 => Head vale 5", 5, ((Integer)lista.getHead().getValue()).intValue());
		assertEquals("Lista con n5=>n7 => Last vale 7", 7, ((Integer)lista.getLast().getValue()).intValue());
	}
	
	/* *****************************************
	 * Test dei costruttori di MyLinkedList.Node
	 */
	
	@Test
	public void shouldCreateAnEmptyLinkedListNode() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>();
		
		assertEquals(null, nodo.getValue());
		assertEquals(null, nodo.getNext());
	}
		
	@Test
	public void shouldCreateALinkedListNodeWithNullValueOnly() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(null);
		
		assertEquals(null, nodo.getValue());
		assertEquals(null, nodo.getNext());
	}
	
	@Test
	public void shouldCreateALinkedListNodeWithIntegerValueOnly() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(Integer.MAX_VALUE);
		
		assertEquals((Integer) Integer.MAX_VALUE, nodo.getValue());
		assertEquals(null, nodo.getNext());
	}
		
	@Test
	public void shouldCreateALinkedListNodeWithValueOnly() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(n2.getValue());
		
		assertEquals(n2.getValue(), nodo.getValue());
		assertEquals(null, nodo.getNext());
	}
		
	@Test
	public void shouldCreateALinkedListNodeWithValueAndNullNodeRef() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(n2.getValue(), null);
	
		assertEquals(n2.getValue(), nodo.getValue());
		assertEquals(null, nodo.getNext());	
	}
		
	@Test
	public void shouldCreateALinkedListNodeWithValueAndSingleNodeRef() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(n2.getValue(), n3);
	
		assertEquals(n2.getValue(), nodo.getValue());	
		assertEquals(n3, nodo.getNext());	
	}
		
	@Test
	public void shouldCreateALinkedListNodeWithValueAndMultipleNodeRef() {
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>(n2.getValue(), n5);
		
		assertEquals(n2.getValue(), nodo.getValue());	
		assertEquals(n5, nodo.getNext());	
		assertEquals(4, MyLinkedList.countNodesRecursive(nodo));

	}

	/* *****************************************
	 * Test di addNodesValue()
	 */
	
	@Test
	public void shouldAddValuesForOneNode() {
		MyLinkedList lista = new MyLinkedList();
		lista.addNodesValue(n1);
		
		assertEquals(1, lista.getCount());
		assertNotSame(n1, lista.getHead());	// Stesso valore, oggetto diverso da quello passato
		assertEquals("Lista con n1 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertNotSame(n1, lista.getLast());	// Stesso valore, oggetto diverso da quello passato
		assertEquals("Lista con n1 => Last vale 1", 1, ((Integer)lista.getLast().getValue()).intValue());		
		assertNull(n1.getNext());	// Next di n1 � vuoto
	}
		
	@Test
	public void shouldAddValuesForAChainOfNodes() {
		MyLinkedList lista = new MyLinkedList();
		lista.addNodesValue(n1);
		
		lista.addNodesValue(n5); // Aggiungo il primo di 3 nodi concatenati
		
		assertEquals(4, lista.getCount());	// 4 elementi
		assertNotSame(n1, lista.getHead());	// Stesso valore, oggetto diverso da quello passato
		assertEquals("Lista con n1+n5=>n7 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertNotSame(n7, lista.getLast());	// Stesso valore, oggetto diverso da quello in fondo alla catena passata
		assertEquals("Lista con n1+n5=>n7 => Last vale n7.val", n7.getValue(), (Integer)lista.getLast().getValue());
		assertEquals("Lista con n1+n5=>n7 => Last vale 7", 7, ((Integer)lista.getLast().getValue()).intValue());

		// NO Side effect => NESSUNA modifica ad n1 ed n5
		assertNull(n1.getNext());	// Next di n1 � ANCORA vuoto
		assertNotSame(n5, n1.getNext()); // n1 NON punta all'oggetto che viene dopo di lui
	}

	/* *****************************************
	 * Test di addNodes()
	 */
	
	@SuppressWarnings("deprecation")
	@Test
	public void shouldAddOneNode() {
		MyLinkedList lista = new MyLinkedList();
		lista.addNodes(n1);
		
		assertEquals(1, lista.getCount());	// 1 elemento
		assertEquals(n1, lista.getHead());	// punta all'oggetto passato
		assertEquals(n1, lista.getLast());	// punta all'oggetto passato
		assertNull(n1.getNext());	// Next di n1 � vuoto
	}
		
	@SuppressWarnings("deprecation")
	@Test
	public void shouldAddAllLinkedNodes() {
		MyLinkedList lista = new MyLinkedList();
		lista.addNodes(n1);
		
		lista.addNodes(n5);		// Aggiungo 3 nodi concatenati
		
		assertEquals(4, lista.getCount());	// 4 elementi
		assertEquals(n1, lista.getHead());	// punta all'oggetto n1 passato
		assertEquals(n7, lista.getLast());	// punta all'oggetto n7 in fondo alla catena passata

		// Side effect => modifica di n1
		assertNotNull(n1.getNext());	// Next di n1 NON � PIU' vuoto
		assertEquals(n5, n1.getNext()); // n1 punta all'oggetto che viene dopo di lui
	}
		
	@SuppressWarnings("deprecation")
	@Ignore		// if we run this test we end in an infinite loop.
	public void canNotAddNodesWithCirculareReference() {
		MyLinkedList lista = new MyLinkedList();
		lista.addNodes(n1);
		lista.addNodes(n5);		// Aggiungo 3 nodi concatenati
		
		lista.addNodes(n5);	// Creo referenza circolare
		
		assertEquals(n7, lista.getLast());	// punta all'oggetto n7 in fondo alla catena passata
		assertEquals(n5, n7.getNext());	// punta all'oggetto n5 che gli � stato aggiunto dopo!
		assertEquals(n6, n5.getNext());	// n5 => n6
		assertEquals(n7, n6.getNext());	// n6 => n7 che poi punta ad n5 >>> LOOP
		
	}

}
