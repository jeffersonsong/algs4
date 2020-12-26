/******************************************************************************
 *  Compilation:  javac QuickX.java
 *  Execution:    java QuickX < input.txt
 *  Dependencies: StdOut.java StdIn.java
 *  Data files:   https://algs4.cs.princeton.edu/23quicksort/tiny.txt
 *                https://algs4.cs.princeton.edu/23quicksort/words3.txt
 *  
 *  Uses the Hoare's 2-way partitioning scheme, chooses the partitioning
 *  element using median-of-3, and cuts off to insertion sort.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.sorting.qsort;

import edu.princeton.cs.algs4.sorting.elementary.Insertion;
import edu.princeton.cs.algs4.utils.io.StdIn;
import edu.princeton.cs.algs4.utils.io.StdOut;

import static edu.princeton.cs.algs4.sorting.SortUtils.isSorted;
import static edu.princeton.cs.algs4.sorting.SortUtils.less;
import static edu.princeton.cs.algs4.utils.ArrayUtils.exch;
import static edu.princeton.cs.algs4.utils.ArrayUtils.show;

/**
 *  The {@code QuickX} class provides static methods for sorting an array
 *  using an optimized version of quicksort (using Hoare's 2-way partitioning
 *  algorithm, median-of-3 to choose the partitioning element, and cutoff
 *  to insertion sort).
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/23quicksort">Section 2.3</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class QuickX {

    // cutoff to insertion sort, must be >= 1
    private static final int INSERTION_SORT_CUTOFF = 8;

    // This class should not be instantiated.
    private QuickX() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public static <T extends Comparable<T>> void sort(T[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1);
        assert isSorted(a);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static <T extends Comparable<T>> void sort(T[] a, int lo, int hi) {
        if (hi <= lo) return;

        // cutoff to insertion sort (Insertion.sort() uses half-open intervals)
        int n = hi - lo + 1;
        if (n <= INSERTION_SORT_CUTOFF) {
            Insertion.sort(a, lo, hi);
            return;
        }

        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static <T extends Comparable<T>> int partition(T[] a, int lo, int hi) {
        int n = hi - lo + 1;
        int m = median3(a, lo, lo + n/2, hi);
        exch(a, m, lo);

        int i = lo;
        int j = hi + 1;
        T v = a[lo];

        // a[lo] is unique largest element
        while (less(a[++i], v)) {
            if (i == hi) { exch(a, lo, hi); return hi; }
        }

        // a[lo] is unique smallest element
        while (less(v, a[--j])) {
            if (j == lo + 1) return lo;
        }

        // the main loop
        while (i < j) { 
            exch(a, i, j);
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
        }

        // put partitioning item v at a[j]
        exch(a, lo, j);

        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }

    // return the index of the median element among a[i], a[j], and a[k]
    private static <T extends Comparable<T>> int median3(T[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
               (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
               (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    /**
     * Reads in a sequence of strings from standard input; quicksorts them
     * (using an optimized version of 2-way quicksort); 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        QuickX.sort(a);
        assert isSorted(a);
        show(a);
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
