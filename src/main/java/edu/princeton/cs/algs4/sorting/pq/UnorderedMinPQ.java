package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedMinPQ<Key extends Comparable<Key>> implements PQ<Key> {
    private Key[] pq; // pq[i] = ith element on pq
    private int n; // number of elements on pq
    private final Comparator<Key> comparator;

    public UnorderedMinPQ(Comparator<Key> comparator) {
        this(1, comparator);
    }

    @SuppressWarnings("unchecked")
    public UnorderedMinPQ(int capacity, Comparator<Key> comparator) {
        pq = (Key[]) new Comparable[capacity];
        this.comparator = comparator;
    }

    @Override
    public void add(Key v) {
        if (n == pq.length) {
            resize(2 * pq.length);
        }
        pq[n++] = v;
    }

    @Override
    public Key poll() {
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
    public Key peek() {
        int max = maxIndex(0, n - 1);
        return pq[max];
    }

    private int maxIndex(int lo, int hi) {
        int max = lo;
        for (int i = lo + 1; i <= hi; i++) {
            if (greater(max, i)) max = i;
        }
        return max;
    }

    @Override
    public int size() {
        return n;
    }

    private void exch(int i, int j) {
        ArrayUtils.exch(pq, i, j);
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return pq[i].compareTo(pq[j]) > 0;
        }
        else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {

        // create a new pq
        private final PQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = PQIml.maxPQ(size());
            for (int i = 1; i <= n; i++)
                copy.add(pq[i]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.poll();
        }
    }
}
