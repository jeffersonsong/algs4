/******************************************************************************
 *  Compilation: javac InplaceMSD.java
 *  Execution:   java InplaceMSD < input.txt
 *  Dependencies: StdIn.java StdOut.java 
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *                https://algs4.cs.princeton.edu/51radix/shells.txt
 *
 *  Sort an array of strings or integers using in-place MSD radix sort.
 *
 *  % java InplaceMSD < shells.txt 
 *  are
 *  by
 *  sea
 *  seashells
 *  seashells
 *  sells
 *  sells
 *  she
 *  she
 *  shells
 *  shore
 *  surely
 *  the
 *  the
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.strings.sort;

import edu.princeton.cs.algs4.sorting.SortUtils;
import edu.princeton.cs.algs4.sorting.elementary.Insertion;
import edu.princeton.cs.algs4.utils.io.StdIn;
import edu.princeton.cs.algs4.utils.io.StdOut;

import java.util.Comparator;

import static edu.princeton.cs.algs4.utils.ArrayUtils.exch;

/**
 *  The {@code InplaceMSD} class provides static methods for sorting an
 *  array of extended ASCII strings using in-place MSD radix sort.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/51radix">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Ivan Pesin
 */

public class InplaceMSD {
    private static final int R             = 256;   // extended ASCII alphabet size
    private static final int CUTOFF        =  15;   // cutoff to insertion sort

    // do not instantiate
    private InplaceMSD() { } 

   /**
     * Rearranges the array of extended ASCII strings in ascending order.
     * This is an unstable sorting algorithm.
     *
     * @param a the array to be sorted
     */
    public static void sort(String[] a) {
        int n = a.length;
        sort(a, 0, n-1, 0);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    private static void sort(String[] a, int lo, int hi, int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        // compute frequency counts
        int[] heads = new int[R+2];
        int[] tails = new int[R+1];
        for (int i = lo; i <= hi; i++) {
            int c = charAt(a[i], d);
            heads[c+2]++;
        }

        // transform counts to indices
        heads[0] = lo;
        for (int r = 0; r < R+1; r++) {
            heads[r+1] += heads[r];
            tails[r] = heads[r+1];
        }

        // sort by d-th character in-place
        for (int r = 0; r < R+1; r++) {
            while (heads[r] < tails[r]) {
                int c = charAt(a[heads[r]], d);
                while (c + 1 != r) {
                    exch(a, heads[r], heads[c+1]++);
                    c = charAt(a[heads[r]], d);
                }
                heads[r]++;
            }
        }
              
        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++)
            sort(a, tails[r], tails[r+1] - 1, d+1);
    }

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        Comparator<String> comparator = SortUtils.subStringComparator(d);
        Insertion.sort(a, lo, hi, comparator);
    }

    /**
     * Reads in a sequence of extended ASCII strings from standard input;
     * in-place MSD radix sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int n = a.length;
        sort(a);
        for (String s : a) StdOut.println(s);
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
