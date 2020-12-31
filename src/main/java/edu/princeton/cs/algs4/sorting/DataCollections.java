package edu.princeton.cs.algs4.sorting;

import edu.princeton.cs.algs4.utils.ArrayUtils;

import java.util.Comparator;
import java.util.Objects;

public class DataCollections {
    private DataCollections() {
    }

    public static DataCollection intArray(int[] a) {
        return new DataCollection() {
            @Override
            public int compare(int i, int j) {
                return Integer.compare(a[i], a[j]);
            }

            @Override
            public void exch(int i, int j) {
                ArrayUtils.exch(a, i, j);
            }
        };
    }

    public static <T extends Comparable<T>> DataCollection array(T[] a) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return array(a, comparator);
    }

    public static <T> DataCollection array(T[] a, Comparator<T> comparator) {
        return new DataCollection() {
            @Override
            public int compare(int i, int j) {
                return Objects.compare(a[i], a[j], comparator);
            }

            @Override
            public void exch(int i, int j) {
                ArrayUtils.exch(a, i, j);
            }
        };
    }
}
