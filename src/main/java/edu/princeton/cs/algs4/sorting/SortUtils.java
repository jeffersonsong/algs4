package edu.princeton.cs.algs4.sorting;

import java.util.Comparator;

import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

public class SortUtils {
    // is v < w ?
    public static <T extends Comparable<T>> boolean less(final T v, final T w) {
        return v.compareTo(w) < 0;
    }

    // is v < w ?
    public static <T extends Comparable<T>> boolean greater(final T v, final T w) {
        return v.compareTo(w) > 0;
    }

    // does v == w ?
    public static <T extends Comparable<T>> boolean eq(final T v, final T w) {
        if (v == w) return true;    // optimization when reference equal
        return v.compareTo(w) == 0;
    }

    public static <T> boolean less(final T v, final T w, final Comparator<T> comparator) {
        return comparator.compare(v, w) < 0;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/
    public static <T extends Comparable<T>> boolean isSorted(final T[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    public static <T extends Comparable<T>> boolean isSorted(final T[] a, final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    public static <T> boolean isSorted(final T[] a, final Comparator<T> comparator) {
        return isSorted(a, 0, a.length - 1, comparator);
    }

    // is the array a[lo..hi) sorted
    public static <T> boolean isSorted(final T[] a, final int lo, final int hi, final Comparator<T> comparator) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1], comparator)) return false;
        return true;
    }

    public static boolean isSorted(final int[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    public static boolean isSorted(final int[] a, final int lo, final int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    /**
     * Returns the number of keys in this symbol table strictly less than {@code key}.
     *
     * @param  value value.
     * @return the number of keys in the symbol table strictly less than {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public static <T extends Comparable<T>> int rank(final T[] array, final T value, final int _lo, final int _hi) {
        requiresNotNull(value,"argument to rank() is null");

        int lo = _lo, hi = _hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = value.compareTo(array[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }
        return lo;
    }

    /**
     * Returns the index of the specified key in the specified array.
     *
     * @param  a the array of integers, must be sorted in ascending order
     * @param  key the search key
     * @return index of key in array {@code a} if present; {@code -1} otherwise
     */
    public static int indexOf(final int[] a, final int key, final int _lo, final int _hi) {
        int lo = _lo, hi = _hi;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a[mid]) hi = mid - 1;
            else if (key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }

    public static Comparator<String> subStringComparator(final int d) {
        return new Comparator<String>() {
            @Override
            public int compare(String v, String w) {
                assert v.substring(0, d).equals(w.substring(0, d));
                for (int i = d; i < Math.min(v.length(), w.length()); i++) {
                    int cmp = Character.compare(v.charAt(i), w.charAt(i));
                    if (cmp != 0) {
                        return cmp;
                    }
                }
                return Integer.compare(v.length(), w.length());
            }
        };
    }
}