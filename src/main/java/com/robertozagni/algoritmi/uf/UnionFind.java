package com.robertozagni.algoritmi.uf;

/**
 * The API definition for the Union-Find ADT.<br>
 * <br>
 * We deal with N objects, identified by their id being integers in the range 0 to N-1.<br>
 * Objects that are connected belong to the same connected component, identified by an integer id in the range 0 to N-1.
 * 
 * @author roberto.zagni
 */
public interface UnionFind {

    /**
     * Connects objects p and q.
     * 
     * @param p The id of object p, must be between 0 an N-1;
     * @param q The id of object q, must be between 0 an N-1;
     * @throws IndexOutOfBoundsException if the provided ids are out of the 0 - N-1 interval.
     */
    void union(int p, int q);

    /**
     * Find the component identifier where p belongs.
     * 
     * @param p The id of object p, must be between 0 an N-1;
     * @return the id of the connected component the object is in.
     * @throws IndexOutOfBoundsException if the provided id is out of the 0 - N-1 interval.
     */
    int find(int p);

    /**
     * Checks if two objects are connected.
     * 
     * @param p The id of object p, must be between 0 an N-1;
     * @param q The id of object q, must be between 0 an N-1;
     * @return <code>true</code> if the give objects are connected, <code>false</code> otherwise.
     * @throws IndexOutOfBoundsException if the provided ids are out of the 0 - N-1 interval.
     */
    boolean connected(int p, int q);

    /**
     * Counts the number of different connected component.
     * 
     * @return the number of different connected component.
     */
    int count();
}
