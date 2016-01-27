/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.binaryheap;

public class BinaryHeapException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BinaryHeapException(String message) {
        super(message);
    }

    public BinaryHeapException(String message, Throwable cause) {
        super(message, cause);
    }

}
