package it.rz.algoritmi.binaryHeap;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class BinaryMinHeapTest {

	public BinaryMinHeapTest() {
	}

	@Test
	public void shouldCreateANewBinaryMinHeap() {
		BinaryMinHeap<Integer> bmh = new BinaryMinHeap<Integer>(Integer.class);
		
		assertNotNull(bmh);
	}
	

	/*
	@Test
	public void should() {
	}

	@Test
	public void should() {
	}

	@Test
	public void should() {
	}
	*/

}
