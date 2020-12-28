package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.fundamentals.bag.Bag;
import edu.princeton.cs.algs4.fundamentals.bag.LinkedBag;
import edu.princeton.cs.algs4.graphs.graph.Graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class NonDirectedEdgeWeightedGraphUtils {
    private NonDirectedEdgeWeightedGraphUtils() {
    }

    public static Iterable<Edge> edges(Graph<Edge> G) {
        checkArgument(!G.isDirected());
        Bag<Edge> list = new LinkedBag<>();
        for (int v = 0; v < G.V(); v++) {
            int selfLoops = 0;
            for (Edge e : G.adj(v)) {
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
