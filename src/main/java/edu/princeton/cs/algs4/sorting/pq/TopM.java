/******************************************************************************
 *  Compilation:  javac TopM.java
 *  Execution:    java TopM m < input.txt
 *  Dependencies: MinPQ.java Transaction.java StdIn.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/24pq/tinyBatch.txt
 * 
 *  Given an integer m from the command line and an input stream where
 *  each line contains a String and a long value, this MinPQ client
 *  prints the m lines whose numbers are the highest.
 * 
 *  % java TopM 5 < tinyBatch.txt 
 *  Thompson    2/27/2000  4747.08
 *  vonNeumann  2/12/1994  4732.35
 *  vonNeumann  1/11/1999  4409.74
 *  Hoare       8/18/1992  4381.21
 *  vonNeumann  3/26/2002  4121.85
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Transaction;
import edu.princeton.cs.algs4.fundamentals.stack.LinkedStack;
import edu.princeton.cs.algs4.fundamentals.stack.Stack;
import edu.princeton.cs.algs4.utils.io.IOUtils;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;

/**
 *  The {@code TopM} class provides a client that reads a sequence of
 *  transactions from standard input and prints the <em>m</em> largest ones
 *  to standard output. This implementation uses a {@link PQIml} of size
 *  at most <em>m</em> + 1 to identify the <em>M</em> largest transactions
 *  and a {@link Stack} to output them in the proper order.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/24pq">Section 2.4</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class TopM {   

    // This class should not be instantiated.
    private TopM() { }

    public static Iterable<Transaction> topM(In in, int m){
        Iterable<Transaction> transactions = IOUtils.iterable(in, Transaction::new);

        return topM(transactions, m);
    }

    public static <T extends Comparable<T>> Iterable<T> topM(Iterable<T> coll, int m){
        PQ<T> pq = PQIml.minPQ(m + 1);

        for (T item : coll) {
            // Create an entry from the next line and put on the PQ.
            pq.add(item);

            // remove minimum if m+1 entries on the PQ
            if (pq.size() > m)
                pq.poll();
        }   // top m entries are on the PQ

        // print entries on PQ in reverse order
        Stack<T> stack = new LinkedStack<>();
        for (T item : pq) {
            stack.push(item);
        }

        return stack;
    }

    /**
     *  Reads a sequence of transactions from standard input; takes a
     *  command-line integer m; prints to standard output the m largest
     *  transactions in descending order.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int m = Integer.parseInt(args[0]);
        In in = new In(args[1]);

        Iterable<Transaction> transactions = TopM.topM(in, m);
        for (Transaction transaction : transactions)
            StdOut.println(transaction);
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
