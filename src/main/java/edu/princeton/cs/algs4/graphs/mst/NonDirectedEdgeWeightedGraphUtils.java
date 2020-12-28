package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.fundamentals.bag.Bag;
import edu.princeton.cs.algs4.fundamentals.bag.LinkedBag;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.sp.DirectedEdge;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class NonDirectedEdgeWeightedGraphUtils {
    private NonDirectedEdgeWeightedGraphUtils() {
    }

    public static Iterable<DirectedEdge> edges(Graph<DirectedEdge> G) {
        checkArgument(!G.isDirected());
        Bag<DirectedEdge> list = new LinkedBag<>();
        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (DirectedEdge e : G.adj(v)) {
                int other = e.v() == v ? e.w() : e.v();
                if (other > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (other == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }
}
