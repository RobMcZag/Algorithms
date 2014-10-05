package it.rz.algoritmi;

import java.util.Iterator;
import java.util.LinkedList;

public class MiddleOfLinkedList {

	private static final int NUMERO_ELEMENTI = 10;

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		for (int i = 1; i < NUMERO_ELEMENTI; i++) {
			// Crea la lista della lunghezza richiesta
			LinkedList<Object> lista = setupList(i);

			// Trova l'elemento centrale
			Object mezzo = findMiddle(lista);

			// Stampa risultato
			System.out.print("Numero elementi: " + i);
			System.out.println(" - Elemento intermedio: " + mezzo.toString());
			// System.out.println("");

		}
		
	}
	
	private static LinkedList<Object> setupList(int n) {
		LinkedList<Object> lista = new LinkedList<Object>();
		for (int i = 1; i <= n; i++) {
			lista.add(Integer.valueOf(i));
		}
		return lista;
	}

	/**
	 * Cerca e ritorna l'elemento di mezzo della lista.
	 * Se la lista non ha elementi ritorna null.
	 * @param lista
	 * @return
	 */
	public static Object findMiddle(LinkedList<Object> lista) {
		Object inMezzo = null;
		int p = 1;
		Iterator<Object> i = lista.iterator();
		Iterator<Object> dispari = lista.iterator();
		
		while (i.hasNext()) {
			i.next();
			if ( (p++ % 2) == 1 ) {  // se p è dispari
				inMezzo = (Object) dispari.next();;
			}			
		}
		return inMezzo;
	}
}
