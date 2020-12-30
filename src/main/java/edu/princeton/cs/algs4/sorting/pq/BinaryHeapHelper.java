package edu.princeton.cs.algs4.sorting.pq;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/***************************************************************************
 * Helper functions to restore the heap invariant.
 ***************************************************************************/
public class BinaryHeapHelper {
    private BinaryHeapHelper() {
    }

    public static void swim(BinaryHeap heap, int k) {
        while (k > 1 && heap.greater(parent(k), k)) {
            heap.exch(k, k / 2);
            k = k / 2;
        }
    }


    public static void sink(BinaryHeap heap, int k, int n) {
        checkArgument(k >= 1);
        checkArgument(n >= 0);

        while (2 * k <= n) {
            int j = left(k);
            if (j < n && heap.greater(j, j + 1)) j++;
            if (!heap.greater(k, j)) break;
            heap.exch(k, j);
            k = j;
        }
    }


    /**
     * parent (k), left (2k), right (2k + 1)
     * parent < left, right
     */
    private static int parent(int k) {
        return k / 2;
    }

    private static int left(int k) {
        return 2 * k;
    }
}
