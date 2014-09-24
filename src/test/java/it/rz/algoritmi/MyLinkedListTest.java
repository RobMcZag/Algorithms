package it.rz.algoritmi;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void aLinkedListCanBeCreated() {
		MyLinkedList sut = new MyLinkedList();
		
		assertNotNull(sut);
	}

}
