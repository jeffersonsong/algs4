package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.sorting.DataCollection;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class BinaryHeap {
    public static void swim(DataCollection data, int k) {
        while (k > 1 && data.greater(k / 2, k)) {
            data.exch(k, k / 2);
            k = k / 2;
        }
    }

    public static void sink(DataCollection data, int k, int n) {
        checkArgument(k >= 1);
        checkArgument(n >= 0);

        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && data.greater(j, j + 1)) j++;
            if (!data.greater(k, j)) break;
            data.exch(k, j);
            k = j;
        }
    }
}
