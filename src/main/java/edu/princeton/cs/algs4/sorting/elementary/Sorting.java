package edu.princeton.cs.algs4.sorting.elementary;

import edu.princeton.cs.algs4.sorting.pq.BinaryHeap;

public interface Sorting<T> {
    default void insertionSort(final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && less(j, j-1); j--) {
                exch(j, j-1);
            }
        }
    }

    default void selectionSort(final int lo, final int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++) {
                if (less(j, min)) {
                    min = j;
                }
            }
            exch(i, min);
        }
    }

    default void quickSort(final int lo, final int hi) {
        if (hi > lo) {
            final int p = partition(lo, hi);
            quickSort(lo, p - 1);
            quickSort(p + 1, hi);
        }
    }

    default int partition(final int lo, final int hi) {
        int p = lo;            /* pivot element index */
        int i = lo + 1, j = hi;    /* divider position for pivot element */
        while (i <= j) {
            while (i<=j && less(i, p)) i++;
            while (i<=j && less(p, j)) j--;

            if (i <= j) {
                exch(i, j);
                i++;
                j--;
            }
        }
        exch(p, j);

        return j;
    }

    default void quick3WaySort(final int lo, final int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        T v = a(lo);
        int i = lo + 1;
        while (i <= gt) {
            int cmp = compareTo(a(i), v);
            if      (cmp < 0) exch(lt++, i++);
            else if (cmp > 0) exch(i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        quick3WaySort(lo, lt-1);
        quick3WaySort(gt+1, hi);
    }

    default void heapSort(final int n) {
        BinaryHeap maxPQ = new BinaryHeap() {
            @Override
            public boolean greater(int i, int j) {
                return Sorting.this.less(i, j);
            }

            @Override
            public void exch(int i, int j) {
                Sorting.this.exch(i, j);
            }
        };

        // heapify phase
        for (int k = n/2; k >= 1; k--) {
            maxPQ.sink(k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {
            maxPQ.exch(1, k--);
            maxPQ.sink( 1, k);
        }
    }

    default boolean isSorted(final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(i, i - 1)) return false;
        }
        return true;
    }

    T a(int i);
    int compareTo(T a, T b);
    boolean less(int i, int j);
    void exch(int i, int j);
}
