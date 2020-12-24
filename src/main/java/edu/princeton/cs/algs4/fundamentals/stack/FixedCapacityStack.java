package edu.princeton.cs.algs4.fundamentals.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedCapacityStack<Item> implements Stack<Item> {
    private final Item[] s;
    private int n = 0;

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = n;

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return s[--i];
        }
    }

    @SuppressWarnings("unchecked")
    public FixedCapacityStack(final int capacity) {
        s = (Item[]) new Object[capacity];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public void push(final Item item) {
        if (n == s.length) {
            throw new IllegalStateException("Stack overflow");
        }
        s[n++] = item;
    }

    @Override
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        final Item item = s[--n];
        s[n] = null; // avoid loitering
        return item;
    }

    @Override
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return s[n - 1];
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }
}
