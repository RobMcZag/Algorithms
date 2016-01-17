/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Finds all the words with the same anagram from a text file.
 * 
 * @author roberto.zagni
 */
public class Anagrammi {

    private static final String FILE_NAME = "dictionary.txt";
    private static final int MIN_GROUP_SIZE = 8;

    /**
     * Costruisce gli anagrammi delle parole di un dizionario in formato testo.
     * 
     * @param args Il primo parametro è il nome del file con le parole.
     */
    public static void main(String[] args) {

        String fileName = FILE_NAME;
        if (args.length > 0)
            fileName = args[0];
        File file = new File(fileName);

        Map<String, List<String>> m = readWords(file);
        System.out.println("*** Lette " + m.size() + " chiavi diverse.");

        List<List<String>> winners = selectLong(m.values(), MIN_GROUP_SIZE);
        System.out.println("*** Lette " + winners.size() + " collezioni di almeno " + MIN_GROUP_SIZE + " elementi.");

        Comparator<List<String>> c = new Comparator<List<String>>() {
            @Override
            public int compare(List<String> arg0, List<String> arg1) {
                return (arg1.size() - arg0.size());
            }
        };
        Collections.sort(winners, c);

        for (List<String> l : winners) {
            System.out.println("* (" + l.size() + ") =>" + l);
        }

    }

    /**
     * Scans with the given file and returns a map associating all the groups of alphabetically sorted letters with a
     * list of the words of the file that produce that sorted group.
     * 
     * @param f the file to open and read.
     * @return the map associating groups of sorted letters with the corresponding words.
     */
    public static Map<String, List<String>> readWords(File f) {
        Map<String, List<String>> m = new HashMap<String, List<String>>();
        Scanner s = null;
        try {
            s = new Scanner(f);
            while (s.hasNext()) {
                String word = s.next();
                String alpha = sortLetters(word);
                List<String> l = getWordsList(m, alpha);
                l.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        } finally {
            if (s != null) {
                s.close();
            }
        }
        return m;
    }

    /**
     * Gets or creates the list holding the words corresponding to a string of alphabetically ordered letters.<br>
     * Newly created lists are inserted into the map with the given group of letters as key.
     * 
     * @param m the map holding the list for every group of letters
     * @param alpha the group of letters we want the corresponding list of words
     * @return the list of words associated to the given the group of letters
     */
    private static List<String> getWordsList(Map<String, List<String>> m, String alpha) {
        List<String> l = m.get(alpha);
        if (l == null) {
            l = new ArrayList<String>();
            m.put(alpha, l);
        }
        return l;
    }

    /**
     * Order alphabetically the characters in the passed String
     * 
     * @param word The string to order. If it contains spaces they will be at the start of the ordered result.
     * @return The passed string's letters ordered alphabetically
     */
    protected static String sortLetters(String word) {

        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * Selects from the given collection the lists with at least the required number of elements.
     * 
     * @param values the collection holding the lists.
     * @param minSize the minimum number of elements a list must have to be selected.
     * @return A list of the selected lists.
     */
    public static <T> List<List<T>> selectLong(Collection<List<T>> values, int minSize) {
        List<List<T>> winners = new ArrayList<List<T>>();
        for (List<T> list : values) {
            if (list.size() >= minSize)
                winners.add(list);
        }
        return winners;
    }

}
