/******************************************************************************
 *  Compilation:  javac AmericanFlagX.java
 *  Execution:    java AmericanFlagX < input.txt
 *  Dependencies: StdIn.java StdOut.java Stack.java
 *  Data files:   https://algs4.cs.princeton.edu/51radix/words3.txt
 *                https://algs4.cs.princeton.edu/51radix/shells.txt
 *
 *  Sort an array of strings or integers in-place using American Flag sort.
 *
 *  % java AmericanFlagX < shells.txt 
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

import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.sorting.SortUtils;
import edu.princeton.cs.algs4.sorting.elementary.Insertion;
import edu.princeton.cs.algs4.utils.io.StdIn;
import edu.princeton.cs.algs4.utils.io.StdOut;

import java.util.Comparator;

import static edu.princeton.cs.algs4.utils.ArrayUtils.exch;

/**
 *  The {@code AmericanFlagX} class provides static methods for sorting an
 *  array of extended ASCII strings or integers in-place using 
 *  American Flag sort. This implementation is non-recursive and uses only 
 *  one auxiliary array.
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/51radix">Section 5.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne
 *  and <a href = "http://static.usenix.org/publications/compsystems/1993/win_mcilroy.pdf">
 *  Engineering Radix Sort</a> by McIlroy and Bostic.
 *  For a version that uses two auxilary arrays, see {@link AmericanFlag}.
 *
 *  @author Ivan Pesin
 */

public class AmericanFlagX {
    private static final int R      = 256;   // extend ASCII alphabet size
    private static final int CUTOFF =  15;   // cutoff to insertion sort

    // do not instantiate
    private AmericanFlagX() { } 

    // return dth character of s, -1 if d = length of string
    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    /**
     * Rearranges the array of extended ASCII strings in ascending order.
     * This is an unstable in-place sorting algorithm.
     *
     * @param a the array to be sorted
     */
    public static void sort(String[] a) {
        sort(a, 0, a.length - 1);
    }

    // sort from a[lo] to a[hi], starting at the dth character
    public static void sort(String[] a, int lo, int hi) {
        // one-time allocation of data structures
        Stack<Integer> st = new LinkedStack<>();
        int[] count = new int[R+1];
        int d = 0; // character index to sort by

        st.push(lo);
        st.push(hi);
        st.push(d);
        
        while (!st.isEmpty()) {
            d = st.pop();
            hi = st.pop();
            lo = st.pop();

            if (hi <= lo + CUTOFF) {
                insertion(a, lo, hi, d);
                continue;
            }

            // compute frequency counts
            for (int i = lo; i <= hi; i++) {
                int c = charAt(a[i], d) + 1; // account for -1 representing end-of-string
                count[c]++;
            }

            // accumulate counts relative to a[0], so that 
            // count[c] is the number of keys <= c
            count[0] += lo;
            for (int c = 0; c < R; c++) {
                count[c+1] += count[c];
            
                if (c > 0 && count[c+1]-1 > count[c]) { 
                    // add subproblem for character c (excludes sentinel c == 0)
                    st.push(count[c]);
                    st.push(count[c+1]-1);
                    st.push(d+1); 
                }
            }

            // permute data in place
            // for details and proof see Knuth Theorem 5.1.2B and ch 5.2 excercise 13.
            for (int r = hi; r >= lo; r--) {

                // locate element that must be shifted right of r
                int c = charAt(a[r], d) + 1;
                while (r >= lo && count[c]-1 <= r) {
                    if (count[c]-1 == r) count[c]--;
                    r--;
                    if (r >= lo) c = charAt(a[r], d) + 1;
                }

                // if r < lo the subarray is sorted.
                if (r < lo) break;
            
                // permute a[r] until correct element is in place
                while (--count[c] != r) {
                    exch(a, r, count[c]);
                    c = charAt(a[r], d) + 1;
                }
            }
          
            // clear count[] array
            for (int c = 0; c < R+1; c++)
                count[c] = 0;
        }
    }
    
    // insertion sort a[lo..hi], starting at dth character
    private static void insertion(String[] a, int lo, int hi, int d) {
        Comparator<String> comparator = SortUtils.subStringComparator(d);
        Insertion.sort(a, lo, hi, comparator);
    }

    /**
     * Reads in a sequence of extended ASCII strings or non-negative ints from standard input;
     * American flag sorts them;
     * and prints them to standard output in ascending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {      
        String[] a = StdIn.readAllStrings();
        sort(a);
        // print results
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
