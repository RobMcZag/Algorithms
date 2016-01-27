/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.list;

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
   * Cerca e ritorna l'elemento di mezzo della lista. Se la lista non ha elementi ritorna null.
   * 
   * @param lista
   * @return
   */
  public static Object findMiddle(LinkedList<Object> lista) {
    Object inMezzo = null;
    int contatore = 1;
    Iterator<Object> i = lista.iterator();
    Iterator<Object> dispari = lista.iterator();

    while (i.hasNext()) {
      i.next();
      if (isOdd(contatore)) {
        inMezzo = dispari.next();
      }
      contatore++;
    }
    return inMezzo;
  }

  static boolean isOdd(int p) {
    return (p % 2) == 1;
  }
}
