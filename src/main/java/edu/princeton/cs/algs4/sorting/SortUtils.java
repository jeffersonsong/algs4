package edu.princeton.cs.algs4.sorting;

import java.util.Comparator;

public class SortUtils {
    // is v < w ?
    public static <T extends Comparable<T>> boolean less(T v, T w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    public static <T extends Comparable<T>> boolean greater(T v, T w) {
        return v.compareTo(w) > 0;
    }

    // does v == w ?
    public static <T extends Comparable<T>> boolean eq(T v, T w) {
        if (v == w) return true;    // optimization when reference equal
        return v.compareTo(w) == 0;
    }

    public static <T> boolean less(T v, T w, Comparator<T> comparator) {
        return comparator.compare(v, w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    public static <T extends Comparable<T>> boolean isSorted(T[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static <T> boolean isSorted(T[] a, Comparator<T> comparator) {
        return isSorted(a, 0, a.length - 1, comparator);
    }

    // is the array a[lo..hi) sorted
    public static <T> boolean isSorted(T[] a, int lo, int hi, Comparator<T> comparator) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1], comparator)) return false;
        return true;
    }
}