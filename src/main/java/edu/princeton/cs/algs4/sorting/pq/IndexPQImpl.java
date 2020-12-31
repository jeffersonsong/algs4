/******************************************************************************
 *  Compilation:  javac IndexMinPQ.java
 *  Execution:    java IndexMinPQ
 *  Dependencies: StdOut.java
 *
 *  Minimum-oriented indexed PQ implementation using a binary heap.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.utils.ArrayUtils;
import edu.princeton.cs.algs4.utils.io.StdOut;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIntArray;
import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;
import static edu.princeton.cs.algs4.utils.Validations.checkIndexInRange;
import static edu.princeton.cs.algs4.utils.Validations.noSuchElement;

/**
 *  The {@code IndexMinPQ} class represents an indexed priority queue of generic keys.
 *  It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 *  operations, along with <em>delete</em> and <em>change-the-key</em> 
 *  methods. In order to let the client refer to keys on the priority queue,
 *  an integer between {@code 0} and {@code maxN - 1}
 *  is associated with each key—the client uses this integer to specify
 *  which key to delete or change.
 *  It also supports methods for peeking at the minimum key,
 *  testing if the priority queue is empty, and iterating through
 *  the keys.
 *  <p>
 *  This implementation uses a binary heap along with an array to associate
 *  keys with integers in the given range.
 *  The <em>insert</em>, <em>delete-the-minimum</em>, <em>delete</em>,
 *  <em>change-key</em>, <em>decrease-key</em>, and <em>increase-key</em>
 *  operations take &Theta;(log <em>n</em>) time in the worst case,
 *  where <em>n</em> is the number of elements in the priority queue.
 *  Construction takes time proportional to the specified capacity.
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
public class IndexPQImpl<Key> implements IndexPQ<Key>, BinaryHeapTrait {
    private final int maxN;        // maximum number of elements on PQ
    private int n;           // number of elements on PQ
    private final int[] pq;        // binary heap using 1-based indexing
    private final int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private final Key[] keys;      // keys[i] = priority of i
    private final Comparator<Key> comparator;  // comparator

    public static <T extends Comparable<T>> IndexPQ<T> indexMinPQ(int maxN) {
        return new IndexPQImpl<T>(maxN, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> IndexPQ<T> indexMaxPQ(int maxN) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return new IndexPQImpl<T>(maxN, comparator.reversed());
    }

    /**
     * Initializes an empty indexed priority queue with indices between {@code 0}
     * and {@code maxN - 1}.
     * @param  maxN the keys on this priority queue are index from {@code 0}
     *         {@code maxN - 1}
     * @param comparator define the order of priority queue.
     * @throws IllegalArgumentException if {@code maxN < 0} or comparator not set.
     */
    public IndexPQImpl(int maxN, Comparator<Key> comparator) {
        checkArgument(maxN >= 0);
        requiresNotNull(comparator);

        this.comparator = comparator;
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Object[maxN + 1];    // make this of length maxN??
        pq   = new int[maxN + 1];
        qp   = newIntArray(maxN + 1, -1); // make this of length maxN??
    }

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    public int size() {
        return n;
    }

    /**
     * Is {@code i} an index on this priority queue?
     *
     * @param  i an index
     * @return {@code true} if {@code i} is an index on this priority queue;
     *         {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     */
    public boolean contains(int i) {
        validateIndex(i);
        return qp[i] != -1;
    }

    /**
     * Associates key with index {@code i}.
     *
     * @param  i an index
     * @param  key the key to associate with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws IllegalArgumentException if there already is an item associated
     *         with index {@code i}
     */
    public void insert(int i, Key key) {
        validateIndex(i);
        checkArgument(!contains(i),"index is already in the priority queue");
        n++;
        addToLast(i, key);
        swim(n);
    }

    private void addToLast(int i, Key key) {
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int poll() {
        noSuchElement(n == 0, "Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1, n);
        assert min == pq[n+1];
        removeLast();
        return min;
    }

    private int removeLast() {
        int min = pq[n+1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[n+1] = -1;        // not needed
        return min;
    }

    /**
     * Remove the key associated with index {@code i}.
     *
     * @param  i the index of the key to remove
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public void delete(int i) {
        validateIndex(i);
        noSuchElement(!contains(i), "index is not in the priority queue");
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index, n);
        removeLast();
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public int peek() {
        noSuchElement(n == 0, "Priority queue underflow");
        return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * @throws NoSuchElementException if this priority queue is empty
     */
    public Key peekKey() {
        noSuchElement(n == 0, "Priority queue underflow");
        return keys[pq[1]];
    }

    /**
     * Returns the key associated with index {@code i}.
     *
     * @param  i the index of the key to return
     * @return the key associated with index {@code i}
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public Key key(int i) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        else return keys[i];
    }

    /**
     * Change the key associated with index {@code i} to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index {@code i} to this key
     * @throws IllegalArgumentException unless {@code 0 <= i < maxN}
     * @throws NoSuchElementException no key is associated with index {@code i}
     */
    public boolean update(int i, Key key) {
        validateIndex(i);
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        int cmp = comparator.compare(key, keys[i]);

        if (cmp < 0) {
            keys[i] = key;
            swim(qp[i]);
            return true;
        } else if (cmp > 0) {
            keys[i] = key;
            sink(qp[i], n);
            return true;
        } else {
            return false;
        }
    }

    // throw an IllegalArgumentException if i is an invalid index
    private void validateIndex(int i) {
        checkIndexInRange(i, 0, maxN);
    }

   /***************************************************************************
    * General helper functions.
    ***************************************************************************/
    public boolean greater(int i, int j) {
        return comparator.compare(keys[pq[i]], keys[pq[j]]) > 0;
    }

    public void exch(int i, int j) {
        ArrayUtils.exch(pq, i, j);
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

   /***************************************************************************
    * Iterators.
    ***************************************************************************/

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement {@code remove()} since it's optional.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private final IndexPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexPQImpl<>(pq.length - 1, comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.poll();
        }
    }

    /**
     * Unit tests the {@code IndexMinPQ} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // insert a bunch of strings
        String[] strings = { "it", "was", "the", "best", "of", "times", "it", "was", "the", "worst" };

        IndexPQ<String> pq = new IndexPQImpl<String>(strings.length, Comparator.naturalOrder());
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // delete and print each key
        while (!pq.isEmpty()) {
            int i = pq.poll();
            StdOut.println(i + " " + strings[i]);
        }
        StdOut.println();

        // reinsert the same strings
        for (int i = 0; i < strings.length; i++) {
            pq.insert(i, strings[i]);
        }

        // print each key using the iterator
        for (int i : pq) {
            StdOut.println(i + " " + strings[i]);
        }
        while (!pq.isEmpty()) {
            pq.poll();
        }
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
