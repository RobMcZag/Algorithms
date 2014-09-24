package it.rz.algoritmi;

import static org.junit.Assert.*;

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
}
