/**
 * Union-Find Abstract Data Type for Dynamic Connectivity problems.<br>
 * In this package we define an Union-Find ADT and several implementations.<br>
 * <br>
 * The Dynamic connectivity problem is the problem to determine if two objects in a set of N are connected, directly or
 * indirectly, after a serie of connect command between pairs of objects from the set has been issued.<br>
 * <br>
 * The input is a sequence of pairs of integers, where each integer represents an object and we interpret the pair "p q"
 * as meaning p is connected to q.<br>
 * We assume that "is connected to" is an equivalence relation:
 * <li>symmetric: If p is connected to q, then q is connected to p.
 * <li>transitive: If p is connected to q and q is connected to r, then p is connected to r.
 * <li>reflexive: p is connected to p. <br>
 * An equivalence relation partitions the objects into equivalence classes or connected components.
 */
package com.robertozagni.algoritmi.uf;
