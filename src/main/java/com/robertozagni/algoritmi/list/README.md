List like collections and data structures.
==

Pushdown Stack
--
A stack is a collection based on the LiFo (last in first out) policy: 
the push operation add the element on top of the stack and the pop operation takes the one on top out.

*   **linked list implementation** offers guaranteed constant time for all operations,
    but overall amortized time is higher than resizing array implementation.
    Memory usage is very well linear and in sync with N, being approximately 40 Bytes * N.
    
          first              8 B pointer to first node                          =  8 B x 1
          Node              16 B object OVERHEAD + 8 B inner class OVERHEAD     = 24 B x N
            T value;         8 B reference to value                             =  8 B x N
            Node next;       8 B reference to next node                         =  8 B x N
*   **resizing array implementation** has overall amortized constant time for all operations lower than linked list ones,
    but single pop and push operation may take way longer (proportional to N) when resizing the underlying array.
    Memory usage grows and shrinks in bunches and is lower than linked list, being ~8N when the array is full and
    ~32N when the array is only 1/4 full (and will be halved). 

          N                  4 B int                                            =  4 B x 1
          T[]               24 B array OVERHEAD + 8 B pointer to array          = 32 B x 1
            T value;         8 B reference to value FOR EVERY array cell        =  8 B x (1-4)*N

Global run time and memory occupation favor the use of a resizing array implementation, 
but if the problem does not allow for the occasional longer operation when the array is being resized 
then the linked list implementation is the one that guarantees constant run time for all operations. 
 
----

Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com 
The contents of this repository  are released under Apache 2.0 License unless stated differently; 
see LICENSE file for complete text. 

All external material, including dependencies, are copyright of the respective owners and available by their respective license.  
