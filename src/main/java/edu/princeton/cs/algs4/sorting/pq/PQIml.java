/******************************************************************************
 *  Compilation:  javac PQImpl.java
 *  Execution:    java PQImpl < input.txt
 *  Dependencies: StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyPQ.txt
 *
 *  Generic min priority queue implementation with a binary heap.
 *  Can be used with a comparator instead of the natural order.
 *
 *  % java MinPQ < tinyPQ.txt
 *  E A E (6 left on pq)
 *
 *  We use a one-based array to simplify parent and child calculations.
 *
 *  Can be optimized by replacing full exchanges with half exchanges
 *  (ala insertion sort).
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.sorting.DataCollection;
import edu.princeton.cs.algs4.utils.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.sorting.pq.BinaryHeapHelper.sink;
import static edu.princeton.cs.algs4.sorting.pq.BinaryHeapHelper.swim;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;
import static edu.princeton.cs.algs4.utils.Validations.noSuchElement;

/**
 *  The {@code PQImpl} class represents a priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 *  operations, along with methods for peeking at the minimum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  This implementation uses a <em>binary heap</em>.
 *  The <em>insert</em> and <em>delete-the-minimum</em> operations take
 *  &Theta;(log <em>n</em>) amortized time, where <em>n</em> is the number
 *  of elements in the priority queue. This is an amortized bound
 *  (and not a worst-case bound) because of array resizing operations.
 *  The <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take
 *  &Theta;(1) time in the worst case.
 *  Construction takes time proportional to the specified capacity or the
 *  number of items used to initialize the data structure.
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *
 *  @param <Key> the generic type of key on this priority queue
 */
public class PQIml<Key> implements PQ<Key>, DataCollection {
    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private final Comparator<Key> comparator;  // comparator

    public static <T extends Comparable<T>> PQ<T> minPQ() {
        return minPQ(1);
    }

    public static <T extends Comparable<T>> PQ<T> minPQ(int initCapacity) {
        return new PQIml<T>(initCapacity, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> PQ<T> maxPQ() {
        return maxPQ(1);
    }
    public static <T extends Comparable<T>> PQ<T> maxPQ(int initCapacity) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return new PQIml<T>(initCapacity, comparator.reversed());
    }

    public static <T extends Comparable<T>> PQ<T> minPQ(T[] keys) {
        return new PQIml<>(keys, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> PQ<T> maxPQ(T[] keys) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return new PQIml<>(keys, comparator.reversed());
    }

    public static <T> PQ<T> newPQ(T[] keys, Comparator<T> comparator) {
        return new PQIml<>(keys, comparator);
    }

    public static <T> PQ<T> newPQ(Comparator<T> comparator) {
        return new PQIml<>(1, comparator);
    }
    public static <T> PQ<T> newPQ(int initCapacity, Comparator<T> comparator) {
        return new PQIml<>(initCapacity, comparator);
    }

    /**
     * Initializes an empty priority queue with the given initial capacity,
     * using the given comparator.
     *
     * @param  initCapacity the initial capacity of this priority queue
     * @param  comparator the order in which to compare the keys
     */
    @SuppressWarnings("unchecked")
    public PQIml(int initCapacity, Comparator<Key> comparator) {
        checkArgument(initCapacity > 0, "Invalid initial capacity.");
        requiresNotNull(comparator);

        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
    }

    /**
     * Initializes a priority queue from the array of keys.
     * <p>
     * Takes time proportional to the number of keys, using sink-based heap construction.
     *
     * @param  keys the array of keys
     * @param  comparator the order in which to compare the keys
     */
    public PQIml(Key[] keys, Comparator<Key> comparator) {
        checkArgument(keys != null, "Array not set.");
        checkArgument(keys.length > 0, "Empty array.");
        requiresNotNull(comparator);

        this.comparator = comparator;
        n = keys.length;
        pq = Arrays.copyOf(keys, keys.length + 1);
        System.arraycopy(keys, 0, pq, 1, keys.length);
        for (int k = n / 2; k >= 1; k--) {
            sink(this, k, n);
        }
        assert isMinHeap();
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    @Override
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    @Override
    public int size() {
        return n;
    }

    /**
     * Adds a new key to this priority queue.
     *
     * @param  x the key to add to this priority queue
     */
    @Override
    public void add(Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);

        // add x, and percolate it up to maintain heap invariant
        n++;
        pq[n] = x;
        swim(this, n);
        assert isMinHeap();
    }

    /**
     * Removes and returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public Key poll() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n);
        n = removeLast();
        sink(this, 1, n);
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private int removeLast() {
        pq[n] = null;     // to avoid loitering and help with garbage collection
        n--;
        return n;
    }

    // resize the underlying array to have the given capacity
    private void resize(int capacity) {
        assert capacity > n;
        pq = Arrays.copyOf(pq, capacity);
    }

    /**
     * Returns a smallest key on this priority queue.
     *
     * @return a smallest key on this priority queue
     * @throws NoSuchElementException if this priority queue is empty
     */
    @Override
    public Key peek() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1];
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/
    @Override
    public int compareIndex(int i, int j) {
        return comparator.compare(pq[i], pq[j]);
    }

    public void exch(int i, int j) {
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
            copy = new PQIml<>(size(), comparator);
            for (int i = 1; i <= n; i++) {
                copy.add(pq[i]);
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
