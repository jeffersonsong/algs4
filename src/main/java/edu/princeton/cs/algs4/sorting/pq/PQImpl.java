package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;
import static edu.princeton.cs.algs4.utils.Validations.noSuchElement;

public class PQImpl<Key> implements PQ<Key> {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;  // comparator

    public static <T extends Comparable<T>> PQ<T> minPQ() {
        return minPQ(1);
    }

    public static <T extends Comparable<T>> PQ<T> minPQ(int initCapacity) {
        return new PQImpl<T>(initCapacity, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> PQ<T> maxPQ() {
        return maxPQ(1);
    }
    public static <T extends Comparable<T>> PQ<T> maxPQ(int initCapacity) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return new PQImpl<T>(initCapacity, comparator.reversed());
    }

    public static <T extends Comparable<T>> PQ<T> minPQ(T[] keys) {
        return new PQImpl<>(keys, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> PQ<T> maxPQ(T[] keys) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return new PQImpl<>(keys, comparator.reversed());
    }

    public static <T> PQ<T> newPQ(T[] keys, Comparator<T> comparator) {
        return new PQImpl<>(keys, comparator);
    }

    public static <T> PQ<T> newPQ(int initCapacity, Comparator<T> comparator) {
        return new PQImpl<>(initCapacity, comparator);
    }

    public PQImpl(int initCapacity, Comparator<Key> comparator) {
        checkArgument(initCapacity > 0, "Invalid initial capacity.");
        requiresNotNull(comparator);

        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public PQImpl(Key[] keys, Comparator<Key> comparator) {
        checkArgument(keys != null, "Array not set.");
        checkArgument(keys.length > 0, "Empty array.");
        requiresNotNull(comparator);

        this.comparator = comparator;
        n = keys.length;
        pq = Arrays.copyOf(keys, keys.length + 1);
        System.arraycopy(keys, 0, pq, 1, keys.length);
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMinHeap();
    }

    @Override
    public void insert(Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    @Override
    public Key poll() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void resize(int capacity) {
        assert capacity > n;
        pq = Arrays.copyOf(pq, capacity);
    }

    @Override
    public Key peek() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public int size() {
        return n;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    protected void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    protected void sink(int k) {
        int n = size();
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    protected boolean greater(int i, int j) {
        return comparator.compare(pq[i], pq[j]) > 0;
    }

    protected void exch(int i, int j) {
        ArrayUtils.exch(pq, i, j);
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    protected boolean isMinHeapOrdered(int k) {
        int n = size();
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
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
            copy = new PQImpl<>(size(), comparator);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            noSuchElement(!hasNext());
            return copy.poll();
        }
    }
}
