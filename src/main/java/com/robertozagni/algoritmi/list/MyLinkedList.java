/**
 * Copyright (c) 2014, 2016 Roberto Zagni, www.robertozagni.com
 * The contents of this repository  are released under Apache 2.0 License unless stated differently; 
 * see LICENSE file for complete text. 
 * 
 * @author roberto.zagni
 */
package com.robertozagni.algoritmi.list;

public class MyLinkedList {

  private static final boolean DEBUG = true;
  /**
   * Il primo elemento della lista.
   */
  private MyLinkedList.Node head = null;

  /**
   * L'ultimo elemento della lista.
   */
  private MyLinkedList.Node last = null;

  /**
   * Il numero di elementi della lista.
   */
  private int count = 0;

  /**
   * Crea una lista vuota.
   */
  public MyLinkedList() {
    super();
    this.count = 0;
    this.head = null;
    this.last = null;
  }

  /**
   * Crea una lista che ha come primo elemento il valore del nodo passato. La lista quindi contiene tutti i valori dei
   * nodi collegati al nodo passato. Per ogni nodo della lista passata il costruttore crea un nuovo nodo privato con la
   * referenza al valore del nodo passato. Questo esclude side effect sui puntatori tra nodi, ma non sui valori
   * contenuti nei nodi, che quindi sarebbe meglio se fossero valori immutabili.
   * 
   * @param head Il nodo iniziale di una lista
   */
  public MyLinkedList(Node head) {
    this();
    // *! side effect !*
    // addNodes imposta il conteggio ed il nodo corrente
    addNodesValue(head);
  }

  /**
   * Esegue un esempio di utilizzo / basic unit test&debug.
   * 
   * @param args
   */
  public static void main(String[] args) {

    // Crea un nodo vuoto e lo riempie
    MyLinkedList.Node<Integer> n1 = new MyLinkedList.Node<Integer>();
    n1.setValue(Integer.valueOf(1));

    // Crea una serie di nodi separati
    MyLinkedList.Node<Integer> n2 = new MyLinkedList.Node<Integer>(Integer.valueOf(2));
    MyLinkedList.Node<Integer> n3 = new MyLinkedList.Node<Integer>(Integer.valueOf(3));
    MyLinkedList.Node<Integer> n4 = new MyLinkedList.Node<Integer>(Integer.valueOf(4));

    // Crea una serie di nodi collegati: n5=>n6=>n7.
    MyLinkedList.Node<Integer> n7 = new MyLinkedList.Node<Integer>(Integer.valueOf(7));
    MyLinkedList.Node<Integer> n6 = new MyLinkedList.Node<Integer>(Integer.valueOf(6), n7);
    MyLinkedList.Node<Integer> n5 = new MyLinkedList.Node<Integer>(Integer.valueOf(5), n6);

    // Lista vuota
    System.out.println("****** Lista vuota *****");
    MyLinkedList lista = new MyLinkedList();
    System.out.println("Lista vuota! Count=" + lista.count);
    lista.addNodesValue(n1);
    System.out.println(
        "add n1! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    lista.addNodesValue(n5);
    System.out.println(
        "add n5=>n7! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    System.out.println(". Nodi attraversati = " + countNodesRecursive(lista.head));

    // Nuova lista inizializzata con un nodo
    System.out.println("\n****** Lista iniziale con n1 *****");
    lista = new MyLinkedList(n1);
    System.out.println(
        "Lista con n1! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    System.out.println("*** Non Dovrebbe esserci side effect x cui n1=>n1 e non n7!!! ***");
    lista.addNodesValue(n5);
    System.out.println(
        "add n5=>n7! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    // System.out.println("*** NON Dovrebbe esserci side effect x cui n1=>n7 e no loop infinito n5=>n7 !!! ***");
    System.out.println(". Nodi attraversati = " + countNodesRecursive(lista.head));
    lista.addNodesValue(n5);
    System.out.println(
        "add n5=>n7 ! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    System.out.println("*** NON Dovrebbe esserci side effect x cui n1=>n7 e no loop infinito n5=>n7 !!! ***");
    System.out.println(". Nodi attraversati = " + countNodesRecursive(lista.head));

    // Nuova lista inizializzata con una serie di nodi
    System.out.println("\n****** Lista iniziale con n5 *****");
    lista = new MyLinkedList(n5);
    System.out.println(
        "Lista con n5=>n7 ! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    // System.out.println("*** Non Dovrebbe esserci side effect x cui n1=>n7!!! ***");
    System.out.println(". Nodi attraversati = " + countNodesRecursive(lista.head));
    lista.addNodesValue(n5);
    lista.addNodesValue(n5);
    lista.addNodesValue(n5);
    System.out.println(
        "add n5=>n7 x 3 volte! Count=" + lista.count
            + " - head=" + lista.head.getValue()
            + " - last=" + lista.last.getValue());
    System.out.println("*** NON Dovrebbe esserci side effect x cui n1=>n7 e no loop infinito n5=>n7 !!! ***");
    System.out.println(". Nodi attraversati = " + countNodesRecursive(lista.head));

    System.out.println("****** FINE *****");

  }

  /**
   * @return the head
   */
  public MyLinkedList.Node getHead() {
    return head;
  }

  /**
   * @return the last
   */
  public MyLinkedList.Node getLast() {
    return last;
  }

  /**
   * @return the count
   */
  public int getCount() {
    return count;
  }

  /**
   * Aggiunge il nodo passato ed i nodi ad esso collegati in modo che alla fine la lista punti all'ultimo della catena.
   * Ritorna il numero di elementi aggiunti. !!! ATTENZIONE ai SIDE EFFECT !!! 1) Questo metodo aggiunge alla lista i
   * nodi di cui viene passata la referenza, per cui A - elementi della lista possono essere variati senza accedere alla
   * lista, rompendo il conteggio dei nodi; B - elementi di cui si hanno riferimenti al di fuori della lista possono
   * venir cambiati dalle operazioni sulla lista. 2) Questo metodo modifica la referenza all'oggetto successivo dei nodi
   * che man mano si trovano in fondo alla lista; 3) Se viene aggiunto un nodo che punta ad un nodo gi� nella lista si
   * crea una referenza circolare.
   * 
   * @param start Il nodo da cui iniziare ad aggiungere una catena.
   * @return Il numero di nodi della catena appena aggiunta, incluso quello passato. Se il nodo passato � null viene
   *         ritornato 0.
   * @deprecated This method has Many potential side effects. Use {@link addNodesValue()} instead.
   */
  @Deprecated
  public int addNodes(Node start) {
    // se la lista � vuota
    if (this.last == null) {
      this.head = start;
      // this.last = start; //viene fatto nel loop se != null, altrimenti � gi� null :)
      this.count = 0;
    }
    Node current = start;
    int counter = 0;
    while (current != null) {
      counter++; // Conta quanti nodi sto aggiungendo
      if (this.last != null) { // Fa puntare il vecchio ultimo al nuovo ultimo
        this.last.setNext(current);
      }
      this.last = current; // Sposta avanti l'ultimo
      current = current.getNext();
    }
    this.count += counter; // Aggiorno il totale dei nodi della lista
    return counter; // Ritorno il numeo di nodi aggiunti
  }

  /**
   * Aggiunge alla lista il valore del nodo passato e dei nodi ad esso collegati, in modo sicuro e senza side effects
   * (se non sui valori puntati dai nodi). Alla fine l'ultimo nodo della lista ha il valore dell'ultimo elemento della
   * catena passata. Ritorna il numero di elementi aggiunti.
   * 
   * @param start Il nodo da cui iniziare ad aggiungere una catena.
   * @return Il numero di nodi della catena appena aggiunta, incluso quello passato. Se il nodo passato � null viene
   *         ritornato 0.
   */
  public int addNodesValue(Node start) {
    Node current = start;
    int counter = 0;
    while (current != null) {
      counter++; // sto aggiungendo un nodo
      Node newNode = new Node(current.getValue());
      // se la lista � vuota
      if (this.head == null) {
        this.head = newNode;
        this.last = null; // viene settata dopo e non deve essere settato il next
        this.count = 0;
      } else { // se non siamo al 1o nodo
        this.last.setNext(newNode); // Fa puntare il vecchio ultimo al nuovo ultimo
      }
      this.last = newNode; // Sposta avanti l'ultimo
      current = current.getNext(); // Passa al successivo della catena passata
    }
    this.count += counter; // Aggiorno il totale dei nodi della lista
    return counter; // Ritorno il numeo di nodi aggiunti
  }

  /**
   * Attraversa i nodi di una lista e conta gli elementi attraversati.
   * 
   * @param start Il nodo da cui partire.
   * @return Il numero di nodi atraversati; 0 se il nodo passato era null.
   */
  public static int countNodesRecursive(Node start) {
    int counter = 0;
    // Node current = start;
    if (start != null) {
      if (DEBUG)
        System.out.print("=>" + start.getValue());
      counter = 1 + countNodesRecursive(start.getNext());
    }
    return counter;
  }

  /**
   * Una classe che rappresenta un Nodo della MyLinkedList. Ha un valore di tipo <T> ed un nodo seguente (next).
   * 
   * @author Roberto
   *
   */
  public static class Node<T> {

    /**
     * Il valore contenuto nel nodo, della classe T impostata sul nodo.
     */
    private T value = null;

    /**
     * Il prossimo nodo della lista. Vale null se non ci sono altri nodi, quindi quello corrente � l'ultimo nodo.
     */
    private Node next = null;

    /**
     * Crea un nodo vuoto, cio� senza valore e senza nodo successivo
     */
    public Node() {
      this(null);
    }

    /**
     * Crea un nodo con il solo valore e nessun nodo successivo
     * 
     * @param value Il valore contenuto nel nodo
     */
    public Node(T value) {
      this(value, null);
    }

    /**
     * Crea un nodo completo con valore e nodo successivo
     * 
     * @param value Il valore contenuto nel nodo
     * @param next Il nodo seguente. Pu� essere null se non si vuole collegare un nodo seguente.
     */
    public Node(T value, Node next) {
      super();
      this.value = value;
      this.next = next;
    }

    /**
     * Ritorna il nodo collegato al nodo attuale, cio� il prossimo nodo della lista. Non ci sono garanzie sul tipo di
     * valore collegato al nodo ritornato, ovvero il nodo successivo potrebbe avere un valore di tipo diverso dal nodo
     * corrente.
     * 
     * @return Il prossimo nodo della lista
     */
    public Node getNext() {
      return next;
    }

    /**
     * Connette il nodo corrente al prossimo nodo della lista. Non ci sono controlli sul tipo di valore collegato al
     * nodo passato.
     * 
     * @param Il prossimo nodo della lista
     */
    public void setNext(Node next) {
      this.next = next;
    }

    /**
     * @return Il valore corrente del nodo.
     */
    public T getValue() {
      return value;
    }

    /**
     * @param Imposta il valore del nodo.
     */
    public void setValue(T value) {
      this.value = value;
    }
  }
}
