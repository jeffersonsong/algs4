package edu.princeton.cs.algs4.sorting.pq;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public interface BinaryHeap {
    default void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    default void sink(int k, int n) {
        checkArgument(k >= 1);
        checkArgument(n >= 0);

        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    boolean greater(int i, int j);

    void exch(int i, int j);
}
