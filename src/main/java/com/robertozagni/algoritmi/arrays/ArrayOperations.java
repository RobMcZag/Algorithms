/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArrayOperations<T> {

  /**
   * Verifica se l'array passato ha dei duplicati. Implementato aggiungendo l'array ad un set e verificando che contenga
   * tanti elementi quanti la lunghezza dell'array
   * 
   * @param objs Gli elementi
   * @return
   */
  @SafeVarargs
  public static <T> boolean hasDuplicatesSet(T... objs) {
    int len = objs.length;
    Set<T> s = new HashSet<T>(Arrays.asList(objs));
    return len > s.size();
  }

  /**
   * Verifica se l'array passato ha dei duplicati. Implementato aggiungendo un elemento per volta ad un HashSet che
   * verifica se il set contiene gi� tale elemento.
   * 
   * @param objs L'array o gli oggetti da verificare.
   * @return true se ci sono duplicati, false altrimenti.
   */
  @SafeVarargs
  public static <T> boolean hasDuplicatesHashSet(T... objs) {
    HashSet<T> s = new HashSet<T>();
    for (T object : objs) {
      if (!s.add(object))
        return true;
    }
    return false;
  }
}
