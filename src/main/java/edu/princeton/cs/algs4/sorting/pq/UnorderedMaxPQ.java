package edu.princeton.cs.algs4.sorting.pq;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedMaxPQ<Key> implements MaxPQ<Key> {
    private Key[] pq; // pq[i] = ith element on pq
    private int n; // number of elements on pq
    private final Comparator<Key> comparator;

    public UnorderedMaxPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    @SuppressWarnings("unchecked")
    public UnorderedMaxPQ(int capacity, Comparator<Key> comparator) {
        pq = (Key[]) new Comparable[capacity];
        this.comparator = comparator;
    }

    @Override
    public void insert(Key v) {
        if (n == pq.length) {
            resize(2 * pq.length);
        }
        pq[n++] = v;
    }

    @Override
    public Key delMax() {
        int max = maxIndex(0, n - 1);
        exch(max, n - 1);
        Key key = pq[--n];
        pq[n] = null;
        if (n > 0 && n == pq.length / 4) {
            resize(pq.length / 2);
        }
        return key;
    }

    private void resize(final int capacity) {
        pq = Arrays.copyOf(pq, capacity);
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Key maxKey() {
        int max = maxIndex(0, n - 1);
        return pq[max];
    }

    private int maxIndex(int lo, int hi) {
        int max = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (less(max, i)) max = i;
        }
        return max;
    }

    @Override
    public int size() {
        return n;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private MaxPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MaxPQImpl<>(size());
            else                    copy = new MaxPQImpl<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }
}
