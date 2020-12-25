package edu.princeton.cs.algs4.utils;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static <T> void exch(final T[] a, final int i, final int j) {
        final T swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void exch(final int[] a, final int i, final int j) {
        final int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}
