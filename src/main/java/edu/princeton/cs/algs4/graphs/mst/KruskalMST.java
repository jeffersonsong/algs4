/******************************************************************************
 *  Compilation:  javac KruskalMST.java
 *  Execution:    java  KruskalMST filename.txt
 *  Dependencies: EdgeWeightedGraph.java Edge.java Queue.java MinPQ.java
 *                UF.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/43mst/tinyEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/mediumEWG.txt
 *                https://algs4.cs.princeton.edu/43mst/largeEWG.txt
 *
 *  Compute a minimum spanning forest using Kruskal's algorithm.
 *
 *  %  java KruskalMST tinyEWG.txt 
 *  0-7 0.16000
 *  2-3 0.17000
 *  1-7 0.19000
 *  0-2 0.26000
 *  5-7 0.28000
 *  4-5 0.35000
 *  6-2 0.40000
 *  1.81000
 *
 *  % java KruskalMST mediumEWG.txt
 *  168-231 0.00268
 *  151-208 0.00391
 *  7-157   0.00516
 *  122-205 0.00647
 *  8-152   0.00702
 *  156-219 0.00745
 *  28-198  0.00775
 *  38-126  0.00845
 *  10-123  0.00886
 *  ...
 *  10.46351
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.fundamentals.queue.LinkedQueue;
import edu.princeton.cs.algs4.fundamentals.queue.Queue;
import edu.princeton.cs.algs4.fundamentals.unionfind.UF;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.sp.DirectedEdge;
import edu.princeton.cs.algs4.sorting.pq.PQ;
import edu.princeton.cs.algs4.sorting.pq.BinaryHeapImpl;
import edu.princeton.cs.algs4.utils.io.StdOut;
import edu.princeton.cs.algs4.fundamentals.unionfind.UFImpl;
import edu.princeton.cs.algs4.utils.io.In;

import java.util.Comparator;

import static edu.princeton.cs.algs4.graphs.mst.MSTValidator.check;

/**
 *  The {@code KruskalMST} class represents a data type for computing a
 *  <em>minimum spanning tree</em> in an edge-weighted graph.
 *  The edge weights can be positive, zero, or negative and need not
 *  be distinct. If the graph is not connected, it computes a <em>minimum
 *  spanning forest</em>, which is the union of minimum spanning trees
 *  in each connected component. The {@code weight()} method returns the 
 *  weight of a minimum spanning tree and the {@code edges()} method
 *  returns its edges.
 *  <p>
 *  This implementation uses <em>Krusal's algorithm</em> and the
 *  union-find data type.
 *  The constructor takes &Theta;(<em>E</em> log <em>E</em>) time in
 *  the worst case.
 *  Each instance method takes &Theta;(1) time.
 *  It uses &Theta;(<em>E</em>) extra space (not including the graph).
 *  <p>
 *  For additional documentation,
 *  see <a href="https://algs4.cs.princeton.edu/43mst">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *  For alternate implementations, see {@link LazyPrimMST}, {@link PrimMST},
 *  and {@link BoruvkaMST}.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class KruskalMST implements MST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private double weight;                        // weight of MST
    private final Queue<DirectedEdge> mst = new LinkedQueue<>();  // edges in MST

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public KruskalMST(Graph<DirectedEdge> G) {
        // more efficient to build heap by passing array of edges
        PQ<DirectedEdge> pq = BinaryHeapImpl.newPQ(Comparator.comparing(DirectedEdge::weight));
        for (DirectedEdge e : NonDirectedEdgeWeightedGraphUtils.edges(G)) {
            pq.insert(e);
        }

        // run greedy algorithm
        UF uf = new UFImpl(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            DirectedEdge e = pq.poll();
            int v = e.v();
            int w = e.w();
            if (uf.find(v) != uf.find(w)) { // v-w does not create a cycle
                uf.union(v, w);  // merge v and w components
                mst.enqueue(e);  // add edge e to mst
                weight += e.weight();
            }
        }

        // check optimality conditions
        assert check(this, G);
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     * @return the edges in a minimum spanning tree (or forest) as
     *    an iterable of edges
     */
    public Iterable<DirectedEdge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or forest).
     * @return the sum of the edge weights in a minimum spanning tree (or forest)
     */
    public double weight() {
        return weight;
    }

    /**
     * Unit tests the {@code KruskalMST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Graph<DirectedEdge> G = GraphReader.readEdgeWeightedGraph(in);
        KruskalMST mst = new KruskalMST(G);
        for (DirectedEdge e : mst.edges()) {
            StdOut.println(e);
        }
        StdOut.printf("%.5f\n", mst.weight());
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
