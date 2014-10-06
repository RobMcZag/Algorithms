package it.rz.algoritmi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Anagrammi {

	private static final String FILE_NAME = "dictionary.txt";
	private static final int MIN_GROUP_SIZE = 8;
	
	/**
	 * Costruisce gli anagrammi delle parole di un dizionario in formato testo.
	 * @param args Il primo parametro è il nome del file con le parole.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = FILE_NAME;
		if (args.length > 0)
			fileName = args[0];
		File file = new File(fileName);
		
		// Carica le parole in liste di anagrammi
		Map<String, List<String>> m = readWords(file);
		System.out.println("*** Lette " + m.size() +" chiavi diverse.");
		
		// Estrai le liste con almeno MIN_GROUP_SIZE elementi
		List<List<String>> winners = selectLong(m.values(), MIN_GROUP_SIZE);
		System.out.println("*** Lette " + winners.size() +" collezioni di almeno " + MIN_GROUP_SIZE + " elementi.");
		
		// Crea un comparator che ordina in base a size() della lista
		Comparator<List<String>> c = new Comparator<List<String>>(){
			/* (non-Javadoc)
			 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
			 */
			@Override
			public int compare(List<String> arg0, List<String> arg1) {
				return (arg1.size() - arg0.size());
			}			
		};	// FINE di Comparator
		// Ordina in base alla dimensione della lista
		Collections.sort(winners, c);
		
		// Stampa risultato
		for (List<String> l : winners){
			System.out.println("* (" + l.size() +") =>" + l);
		}
		
	} // FINE di MAIN

	public static Map<String, List<String>> readWords(File f) {
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		Scanner s = null;
		try {			
			s = new Scanner(f);
			while (s.hasNext()){
				String word = s.next();	// Legge prox parola
				String alpha = alphabetize(word);	// ordina le lettere in modo alfabetico
				List<String> l = m.get(alpha);	// Cerca la lista corrispondente
				if (l == null) {
					l = new ArrayList<String>();
					m.put(alpha, l);
				}
				l.add(word);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
            System.exit(1);
		} finally {
			s.close();
		}
		return m;
	}

	private static String alphabetize(String word) {
		// TODO Auto-generated method stub
		char[] chars = word.toCharArray();
		Arrays.sort(chars); 
		return new String(chars);
	}

	// Estrai le liste con almeno MIN_GROUP_SIZE elementi
	public static List<List<String>> selectLong(Collection<List<String>> values, int minSize) {
		List<List<String>> winners = new ArrayList<List<String>>();
		for (List<String> list : values) {
			if (list.size() >= minSize)
				winners.add(list);
		}
		return winners;
	}

}
