package it.rz.algoritmi.test;

import it.rz.algoritmi.MyLinkedList;
import junit.framework.TestCase;

public class MyLinkedListTest extends TestCase {

	// Nodi singoli
	private MyLinkedList.Node<Integer> n1;
	private MyLinkedList.Node<Integer> n2;
	private MyLinkedList.Node<Integer> n3;
	private MyLinkedList.Node<Integer> n4;
	
	// Nodi collegati: n5=>n6=>n7.
	private MyLinkedList.Node<Integer> n7;
	private MyLinkedList.Node<Integer> n6;
	private MyLinkedList.Node<Integer> n5;

	public MyLinkedListTest(String name) {
		super(name);
	}

	/**
	 * Crea i nodi da usare nei test.
	 * I nodo vengono ricreati "puliti" prima di ogni singolo test.
	 */
	protected void setUp() throws Exception {
		super.setUp();
		// Crea un nodo vuoto e lo riempie
		n1 = new MyLinkedList.Node<Integer>();
		n1.setValue(Integer.valueOf(1));
		
		// Crea una serie di nodi separati
		n2 = new MyLinkedList.Node<Integer>(Integer.valueOf(2));
		n3 = new MyLinkedList.Node<Integer>(Integer.valueOf(3));
		n4 = new MyLinkedList.Node<Integer>(Integer.valueOf(4));
		
		// Crea una serie di nodi collegati: n5=>n6=>n7.
		n7 = new MyLinkedList.Node<Integer>(Integer.valueOf(7));
		n6 = new MyLinkedList.Node<Integer>(Integer.valueOf(6), n7);
		n5 = new MyLinkedList.Node<Integer>(Integer.valueOf(5), n6);
		
	}

	/*
	 * Test dei costruttori di MyLinkedList
	 */
	public void testMyLinkedList() {

		// Lista vuota
		MyLinkedList lista = new MyLinkedList();
		assertEquals("Lista vuota = Zero elementi", 0, lista.getCount());
		assertEquals("Lista vuota => Head null", null, lista.getHead());
		assertEquals("Lista vuota => Last null", null, lista.getLast());
		
		// Lista con 1 elemento
		lista = new MyLinkedList(n1);
		assertEquals("Lista con n1 = 1 elemento", 1, lista.getCount());
		assertEquals("Lista con n1 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertEquals("Lista con n1 => Last vale 1", 1, ((Integer)lista.getLast().getValue()).intValue());

		// Lista con più elementi
		lista = new MyLinkedList(n5);
		assertEquals("Lista con n5=>n7 = 3 elementi", 3, lista.getCount());
		assertEquals("Lista con n5=>n7 => Head vale 5", 5, ((Integer)lista.getHead().getValue()).intValue());
		assertEquals("Lista con n5=>n7 => Last vale 7", 7, ((Integer)lista.getLast().getValue()).intValue());
	}

	/**
	 * Test del costruttore di MyLinkedList.Node
	 */
	public void testMyLinkedListNode() {
		// Nodo vuoto
		MyLinkedList.Node<Integer> nodo = new MyLinkedList.Node<Integer>();
		assertEquals(null, nodo.getValue());
		assertEquals(null, nodo.getNext());
		
		// Nodo con solo valore e no ref
		nodo = new MyLinkedList.Node<Integer>(n2.getValue());
		assertEquals(n2.getValue(), nodo.getValue());
		assertEquals(null, nodo.getNext());
		
		// Nodo con valore e ref singolo
		nodo = new MyLinkedList.Node<Integer>(n2.getValue(), n3);
		assertEquals(n2.getValue(), nodo.getValue());	// L'oggetto valore è lo stesso
		assertEquals(n3, nodo.getNext());	// Il next è lo stesso oggetto
		
		// Nodo con valore e ref multiplo
		nodo = new MyLinkedList.Node<Integer>(n2.getValue(), n5);
		assertEquals(n2.getValue(), nodo.getValue());	// L'oggetto valore è lo stesso
		assertEquals(n5, nodo.getNext());	// Il next è lo stesso oggetto
		assertEquals(4, MyLinkedList.countNodesRecursive(nodo));

	}

	@SuppressWarnings("deprecation")
	public void testAddNodes() {
		// Lista vuota
		MyLinkedList lista = new MyLinkedList();
		
		// Aggiungo 1 nodo
		lista.addNodes(n1);
		assertEquals(1, lista.getCount());	// 1 elemento
		assertEquals(n1, lista.getHead());	// punta all'oggetto passato
		assertEquals(n1, lista.getLast());	// punta all'oggetto passato
		assertNull(n1.getNext());	// Next di n1 è vuoto
		
		// Aggiungo 3 nodi concatenati
		lista.addNodes(n5);
		assertEquals(4, lista.getCount());	// 4 elementi
		assertEquals(n1, lista.getHead());	// punta all'oggetto n1 passato
		assertEquals(n7, lista.getLast());	// punta all'oggetto n7 in fondo alla catena passata
		// Side effect => modifica di n1
		assertNotNull(n1.getNext());	// Next di n1 NON è PIU' vuoto
		assertEquals(n5, n1.getNext()); // n1 punta all'oggetto che viene dopo di lui
		
		// Creo referenza circolare
		lista.addNodes(n5);
		assertEquals(n7, lista.getLast());	// punta all'oggetto n7 in fondo alla catena passata
		assertEquals(n5, n7.getNext());	// punta all'oggetto n5 che gli è stato aggiunto dopo!
		assertEquals(n6, n5.getNext());	// n5 => n6
		assertEquals(n7, n6.getNext());	// n6 => n7 che poi punta ad n5 >>> LOOP
		
	}

	public void testAddNodesValue() {
		// Lista vuota
		MyLinkedList lista = new MyLinkedList();
		
		// Aggiungo 1 nodo
		lista.addNodesValue(n1);
		assertEquals(1, lista.getCount());	// 1 elemento
		assertNotSame(n1, lista.getHead());	// NON punta all'oggetto passato
		assertEquals("Lista con n1 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertNotSame(n1, lista.getLast());	// NON punta all'oggetto passato
		assertEquals("Lista con n1 => Last vale 1", 1, ((Integer)lista.getLast().getValue()).intValue());		
		assertNull(n1.getNext());	// Next di n1 è vuoto
		
		// Aggiungo 3 nodi concatenati
		lista.addNodesValue(n5);
		assertEquals(4, lista.getCount());	// 4 elementi
		assertNotSame(n1, lista.getHead());	// NON punta all'oggetto n1 passato
		assertEquals("Lista con n1+n5=>n7 => Head vale 1", 1, ((Integer)lista.getHead().getValue()).intValue());
		assertNotSame(n7, lista.getLast());	// NON punta all'oggetto n7 in fondo alla catena passata
		assertEquals("Lista con n1+n5=>n7 => Last vale 7", 7, ((Integer)lista.getLast().getValue()).intValue());
		assertEquals("Lista con n1+n5=>n7 => Last vale n7.val", n7.getValue(), (Integer)lista.getLast().getValue());
		// NO Side effect => NESSUNA modifica ad n1
		assertNull(n1.getNext());	// Next di n1 è ANCORA vuoto
		assertNotSame(n5, n1.getNext()); // n1 NON punta all'oggetto che viene dopo di lui
	}

}
