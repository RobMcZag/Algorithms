/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.junit.Test;

public class MiddleOfLinkedListTest {

    @Test
    public void theMiddleOfAnEmptyListIsNull() {
        LinkedList<Object> emptyList = new LinkedList<Object>();

        Object actual = MiddleOfLinkedList.findMiddle(emptyList);

        assertNull(actual);
    }

    @Test
    public void theMiddleOfAnListContainingJustAnItemIsTheItemItself() {
        LinkedList<Object> list = new LinkedList<Object>();
        Object anObject = new Object();
        list.add(anObject);

        Object actual = MiddleOfLinkedList.findMiddle(list);

        assertEquals(anObject, actual);
    }

    @Test
    public void getsTheMiddleOfAList() {
        Object middle = new Object();
        LinkedList<Object> list = new LinkedList<Object>();

        list.add(new Object());
        list.add(new Object());
        list.add(middle);
        list.add(new Object());
        list.add(new Object());

        Object actual = MiddleOfLinkedList.findMiddle(list);

        assertEquals(middle, actual);
    }

    @Test
    public void getsTheMiddleOfAListWithAnOddNumberOfItems() {
        Object middle = new Object();
        LinkedList<Object> list = new LinkedList<Object>();

        list.add(new Object());
        list.add(middle);
        list.add(new Object());
        list.add(new Object());

        Object actual = MiddleOfLinkedList.findMiddle(list);

        assertEquals(middle, actual);
    }

    @Test
    public void isOddIsTrueForOddNumbers() {
        boolean actual = MiddleOfLinkedList.isOdd(101);

        assertTrue(actual);
    }

    @Test
    public void isOddIsFalseForEvenNumbers() {
        boolean actual = MiddleOfLinkedList.isOdd(12);

        assertFalse(actual);
    }

}
