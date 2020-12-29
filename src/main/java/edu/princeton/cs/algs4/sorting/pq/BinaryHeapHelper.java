package edu.princeton.cs.algs4.sorting.pq;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/***************************************************************************
 * Helper functions to restore the heap invariant.
 ***************************************************************************/
public class BinaryHeapHelper {
    private BinaryHeapHelper() {
    }

    public static void swim(BinaryHeap heap, int k) {
        while (k > 1 && heap.greater(k / 2, k)) {
            heap.exch(k, k / 2);
            k = k / 2;
        }
    }

    public static void sink(BinaryHeap heap, int k, int n) {
        checkArgument(k >= 1);
        checkArgument(n >= 0);

        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && heap.greater(j, j + 1)) j++;
            if (!heap.greater(k, j)) break;
            heap.exch(k, j);
            k = j;
        }
    }
}
