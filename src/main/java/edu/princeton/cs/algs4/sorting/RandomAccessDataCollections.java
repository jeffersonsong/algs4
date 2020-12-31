package edu.princeton.cs.algs4.sorting;

import edu.princeton.cs.algs4.utils.ArrayUtils;

import java.util.Comparator;
import java.util.Objects;

public class RandomAccessDataCollections {
    private RandomAccessDataCollections() {
    }

    public static RandomAccessDataCollection<Integer> intArray(int[] a) {
        return new RandomAccessDataCollection<Integer>() {
            @Override
            public Integer a(int i) {
                return a[i];
            }

            @Override
            public int compareTo(Integer a, Integer b) {
                return a.compareTo(b);
            }

            @Override
            public int length() {
                return a.length;
            }

            @Override
            public void exch(int i, int j) {
                ArrayUtils.exch(a, i, j);
            }
        };
    }

    public static <T extends Comparable<T>> RandomAccessDataCollection<T> array(T[] a) {
        Comparator<T> comparator = Comparator.naturalOrder();
        return array(a, comparator);
    }

    public static <T> RandomAccessDataCollection<T> array(T[] a, Comparator<T> comparator) {
        return new RandomAccessDataCollection<T>() {
            @Override
            public T a(int i) {
                return a[i];
            }

            @Override
            public int compareTo(T a, T b) {
                return Objects.compare(a, b, comparator);
            }

            @Override
            public int length() {
                return a.length;
            }

            @Override
            public void exch(int i, int j) {
                ArrayUtils.exch(a, i, j);
            }
        };
    }
}
