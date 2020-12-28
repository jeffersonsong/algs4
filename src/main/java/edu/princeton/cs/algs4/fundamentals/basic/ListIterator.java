package edu.princeton.cs.algs4.fundamentals.basic;

import java.util.Iterator;
import java.util.NoSuchElementException;

// an iterator, doesn't implement remove() since it's optional
public class ListIterator<Item> implements Iterator<Item> {
    private Node<Item> current;

    public ListIterator(Node<Item> first) {
        current = first;
    }

    // is there a next item in the iterator?
    public boolean hasNext() {
        return current != null;
    }

    // this method is optional in Iterator interface
    public void remove() {
        throw new UnsupportedOperationException();
    }

    // returns the next item in the iterator (and advances the iterator)
    public Item next() {
        if(!hasNext()) throw new NoSuchElementException();
        Item item = current.item;
        current = current.next;
        return item;
    }
}
