package edu.princeton.cs.algs4.sorting.pq;

public interface BinaryHeap {
    /**
     * Check if ith element is greater than jth element.
     * @param i position i.
     * @param j position j.
     * @return true if ith element is greater than jth element.
     */
    boolean greater(int i, int j);

    /**
     * Swap two elements.
     * @param i position i.
     * @param j position j.
     */
    void exch(int i, int j);
}
