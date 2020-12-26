package edu.princeton.cs.algs4.sorting.pq;

public interface BinaryHeapInvariant {
    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/
    default void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    default void sink(int k) {
        int n = size();
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    boolean greater(int i, int k);

    void exch(int k, int j);

    int size();
}
