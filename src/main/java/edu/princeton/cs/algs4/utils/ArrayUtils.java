package edu.princeton.cs.algs4.utils;

import edu.princeton.cs.algs4.utils.io.StdOut;

import java.util.Arrays;
import java.util.function.IntFunction;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class ArrayUtils {
    private ArrayUtils() {
    }

    public static int[] newIntArray(int length, int defaultValue) {
        checkArgument(length >= 0, "Array dimension is non-negative");
        int[] array = new int[length];
        Arrays.fill(array, defaultValue);
        return array;
    }

    public static int[] newIndexArray(int length) {
        checkArgument(length >= 0, "Array dimension is non-negative");
        int[] array = new int[length];
        for (int i=0; i < length; i++) {
            array[i] = i;
        }
        return array;
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

    // print array to standard output
    public static <T> void show(T[] a) {
        for (T item : a) {
            StdOut.println(item);
        }
    }
}
