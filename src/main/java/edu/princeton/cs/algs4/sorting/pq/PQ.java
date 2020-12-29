package edu.princeton.cs.algs4.sorting.pq;

/**
 * The elements of the priority queue are ordered according to their natural ordering, or by a Comparator provided at
 * queue construction time, depending on which constructor is used. A priority queue does not permit null elements.
 * A priority queue relying on natural ordering also does not permit insertion of non-comparable objects
 *
 * @param <Key> the generic type of key on this priority queue
 */
public interface PQ<Key> extends Iterable<Key> {
    /**
     * Inserts the specified element into this priority queue.
     * @param x item.
     */
    void add(Key x);

    /**
     * Retrieves and removes the head of this queue.
     * @return smallest item according to sorting.
     * @throws java.util.NoSuchElementException if queue is empty.
     */
    Key poll();

    /**
     * Retrieves the head of this queue.
     * @return smallest item according to sorting.
     * @throws java.util.NoSuchElementException if queue is empty.
     */
    Key peek();

    /**
     * Returns true if this priority queue is empty.
     *
     * @return {@code true} if this priority queue is empty;
     *         {@code false} otherwise
     */
    boolean isEmpty();

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     */
    int size();
}
