package edu.princeton.cs.algs4.sorting;

import edu.princeton.cs.algs4.fundamentals.basic.Knuth;
import edu.princeton.cs.algs4.utils.ArrayUtils;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIndexArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

public class SortingTest {
    private int[] a;
    private RandomAccessDataCollection<Integer> data;

    @Before
    public void setUp() {
        a = newIndexArray(10);
        Knuth.shuffle(a);
        data = RandomAccessDataCollections.intArray(a);
    }

    @Test
    public void testSelectionSort() {
        Sorting.selectionSort(data, 0, a.length - 1);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testInsertionSort() {
        Sorting.insertionSort(data, 0, a.length - 1);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testShellSort() {
        Sorting.shellSort(data, a.length);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testQuickSort() {
        Sorting.quickSort(data, 0, a.length - 1);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testQuick3WaySort() {
        Sorting.quick3WaySort(data, 0, a.length - 1);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testHeapSort() {
        Sorting.heapSort(data, a.length);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
    }

    @Test
    public void testRank() {
        int[] a = {1, 3, 5, 7, 9};
        RandomAccessDataCollection<Integer> data = RandomAccessDataCollections.intArray(a);

        int rank = Sorting.rank(data, 6, 0, a.length - 1);
        assertThat(rank, is(3));

        rank = Sorting.rank(data, 5, 0, a.length - 1);
        assertThat(rank, is(2));
    }

    @Test
    public void testRank2() {
        int[] a = {1, 3, 5, 7, 9};

        int rank = Sorting.rank(a, 0, a.length - 1, 6);
        assertThat(rank, is(3));

        rank = Sorting.rank(a, 0, a.length - 1, 5);
        assertThat(rank, is(2));
    }

    @Test
    public void testBinarySearch() {
        int[] a = {1, 3, 5, 7, 9};
        RandomAccessDataCollection<Integer> data = RandomAccessDataCollections.intArray(a);

        int rank = Sorting.binarySearch(data, 6, 0, a.length - 1);
        assertThat(rank, is(-1));

        rank = Sorting.binarySearch(data, 5, 0, a.length - 1);
        assertThat(rank, is(2));
    }

    @Test
    public void testBinarySearch2() {
        int[] a = {1, 3, 5, 7, 9};
        int index = Sorting.binarySearch(a,  0, a.length - 1, 6);
        assertThat(index, is(-1));

        index = Sorting.binarySearch(a,  0, a.length - 1, 5);
        assertThat(index, is(2));
    }

    @Test
    public void testIndexSort() {
        int[] a = newIndexArray(10);
        Knuth.shuffle(a);
        StdOut.println("a: " + Arrays.toString(a));

        int[] index = newIndexArray(10);

        DataCollection data = new DataCollection() {
            @Override
            public int compareIndex(int i, int j) {
                return Integer.compare(a[index[i]], a[index[j]]);
            }

            @Override
            public void exch(int i, int j) {
                ArrayUtils.exch(index, i, j);
            }
        };

        Sorting.insertionSort(data, 0, a.length - 1);
        assertTrue(Sorting.isSorted(data, 0, a.length - 1));
        StdOut.println("a: " + Arrays.toString(a));
        StdOut.println("index: " + Arrays.toString(index));
    }
}