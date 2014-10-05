package it.rz.algoritmi;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArrayOperations<T> {

	public static void main(String[] args) {
		String s = "Hello World";
		System.out.println("* rec => "+reverseRecursive(s));
	}
	
	/**
	 * Verifica se l'array passato ha dei duplicati.
	 * Implementato aggiungendo l'array ad un set e 
	 * verificando che contenga tanti elementi quanti la lunghezza dell'array 
	 * @param objs Gli elementi 
	 * @return
	 */
	@SafeVarargs
	public static <T> boolean hasDuplicatesSet(T... objs) {
		int len = objs.length;
		Set<T> s = new HashSet<T>(Arrays.asList(objs));
		if (len > s.size())
			return true;
		else 
			return false;
	}
	
	/**
	 * Verifica se l'array passato ha dei duplicati.
	 * Implementato aggiungendo un elemento per volta ad un HashSet 
	 * che verifica se il set contiene già tale elemento. 
	 * @param objs L'array o gli oggetti da verificare.
	 * @return true se ci sono duplicati, false altrimenti.
	 */
	@SafeVarargs
	public static <T> boolean hasDuplicatesHashSet(T... objs) {
		HashSet<T> s = new HashSet<T>();
		for (T object : objs) {
			if (! s.add(object))	// Se non è possibile aggiungere...
				return true;		// Vuol dire che è duplicato
		}
		return false;
	}

	/*
	public static <T> boolean hasDuplicateRecursive(T... objs) {
		if (objs == null) return false;
		if (objs.length <= 1) return false;
		Arrays.
		return false;
	}
	 */

	public static String reverse(String str) {
		StringBuffer sb = new StringBuffer(str);
		return sb.reverse().toString();
	}
	
	public static String reverseRecursive(String str) {
		if (str == null) return null;
		if (str.length() == 1) return str;
		return reverseRecursive(str.substring(1))+str.charAt(0);
	}
	

}
