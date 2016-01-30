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
   * Finds the element in the middle of a list.
   * 
   * @param list the list where to look for the central element.
   * @return The value in the middle of the list, if list has odd number of elements, or the left one of the central
   *         couple if the list has an even number of elements.
   * @throws IllegalArgumentException if the list is null or empty. This has been preferred over returning null to avoid
   *           unpredictable NPE on automatic unboxing to primitive types from list elements.
   */
  public static <T> T findMiddle(LinkedList<T> list) {
    if (list == null || list.isEmpty()) {
      throw new IllegalArgumentException("Can not find the value in the middle of an empty or null list.");
    }
    T inMezzo = null;
    int contatore = 1;
    Iterator<T> i = list.iterator();
    Iterator<T> dispari = list.iterator();

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

  /**
   * @param args
   */
  public static void main(String[] args) {
    for (int i = 1; i <= NUMERO_ELEMENTI; i++) {
      // Crea la lista della lunghezza richiesta
      LinkedList<Integer> lista = setupList(i);

      // Trova l'elemento centrale
      int mezzo = findMiddle(lista);

      // Stampa risultato
      System.out.print("Numero elementi: " + i);
      System.out.println(" - Elemento intermedio: " + mezzo);
      // System.out.println("");

    }
  }

  private static LinkedList<Integer> setupList(int n) {
    LinkedList<Integer> lista = new LinkedList<Integer>();
    for (int i = 1; i <= n; i++) {
      lista.add(Integer.valueOf(i));
    }
    return lista;
  }

}
