package br.edu.ifes.si.trabtpa;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Fila<Item> implements Iterable<Item> {
    private No<Item> primeiro;    // beginning of queue
    private No<Item> ultimo;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class No<Item> {
        private Item item;
        private No<Item> proximo;
    }

    /**
     * Initializes an empty queue.
     */
    public Fila() {
        primeiro = null;
        ultimo  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return <tt>true</tt> if this queue is empty; <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return primeiro == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int tamanho() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Item primeiro() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return primeiro.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enfileira(Item item) {
        No<Item> oldlast = ultimo;
        ultimo = new No<Item>();
        ultimo.item = item;
        ultimo.proximo = null;
        if (isEmpty()) primeiro = ultimo;
        else           oldlast.proximo = ultimo;
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Item desenfileira() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = primeiro.item;
        primeiro = primeiro.proximo;
        n--;
        if (isEmpty()) ultimo = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this)
            s.append(item + " ");
        return s.toString();
    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(primeiro);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<Item> implements Iterator<Item> {
        private No<Item> current;

        public ListIterator(No<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.proximo; 
            return item;
        }
    }


    /**
     * Unit tests the <tt>Fila</tt> data type.
     */
    public static void main(String[] args) {
        Fila<String> queue = new Fila<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-"))
                queue.enfileira(item);
            else if (!queue.isEmpty())
                System.out.print(queue.desenfileira() + " ");
        }
        System.out.println("(" + queue.tamanho() + " left on queue)");
    }
}
