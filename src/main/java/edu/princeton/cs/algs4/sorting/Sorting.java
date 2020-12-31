package edu.princeton.cs.algs4.sorting;

import static edu.princeton.cs.algs4.sorting.pq.BinaryHeapHelper.sink;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

public class Sorting {

    public static void insertionSort(DataCollection data, final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j > lo && data.less(j, j-1); j--) {
                data.exch(j, j-1);
            }
        }
    }

    public static void selectionSort(DataCollection data, final int lo, final int hi) {
        for (int i = lo; i <= hi; i++) {
            int min = i;
            for (int j = i + 1; j <= hi; j++) {
                if (data.less(j, min)) {
                    min = j;
                }
            }
            data.exch(i, min);
        }
    }

    public static <T> void shellSort(DataCollection data, int n) {
        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ...
        int h = 1;
        while (h < n/3) h = 3*h + 1;

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && data.less(j, j-h); j -= h) {
                    data.exch(j, j-h);
                }
            }
            h /= 3;
        }
    }

    public static void quickSort(DataCollection data, final int lo, final int hi) {
        if (hi > lo) {
            final int p = partition(data, lo, hi);
            quickSort(data, lo, p - 1);
            quickSort(data, p + 1, hi);
        }
    }

    public static int partition(DataCollection data, final int lo, final int hi) {
        int p = lo;            /* pivot element index */
        int i = lo + 1, j = hi;    /* divider position for pivot element */
        while (i <= j) {
            while (i<=j && data.less(i, p)) i++;
            while (i<=j && data.less(p, j)) j--;

            if (i <= j) {
                data.exch(i++, j--);
            }
        }
        data.exch(p, j);

        return j;
    }

    public static <T> void quick3WaySort(RandomAccessDataCollection<T> data, final int lo, final int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        T v = data.a(lo);
        int i = lo + 1;
        while (i <= gt) {
            int cmp = data.compare(data.a(i), v);
            if      (cmp < 0) data.exch(lt++, i++);
            else if (cmp > 0) data.exch(i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        quick3WaySort(data, lo, lt-1);
        quick3WaySort(data, gt+1, hi);
    }

    public static void heapSort(DataCollection data, int n) {
        DataCollection maxPQ = new DataCollection() {
            @Override
            public int compareIndex(int i, int j) {
                return -data.compareIndex(i -1, j - 1);
            }

            @Override
            public void exch(int i, int j) {
                data.exch(i-1, j-1);
            }
        };

        // heapify phase
        for (int k = n/2; k >= 1; k--) {
            sink(maxPQ, k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {
            maxPQ.exch( 1, k--);
            sink(maxPQ, 1, k);
        }
    }

    public static <T> int rank(RandomAccessDataCollection<T> data, final T value, final int _lo, final int _hi) {
        requiresNotNull(value,"argument to rank() is null");

        int lo = _lo, hi = _hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = data.compare(value, data.a(mid));
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    public static <T> int binarySearch(RandomAccessDataCollection<T> data, final T value, final int _lo, final int _hi) {
        int lo = _lo, hi = _hi;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            int cmp = data.compare(value, data.a(mid));
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static boolean isSorted(DataCollection data, final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (data.less(i, i - 1)) return false;
        }
        return true;
    }
}
