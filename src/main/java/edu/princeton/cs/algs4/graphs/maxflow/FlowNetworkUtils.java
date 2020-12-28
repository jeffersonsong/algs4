package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.fundamentals.bag.Bag;
import edu.princeton.cs.algs4.fundamentals.bag.LinkedBag;
import edu.princeton.cs.algs4.graphs.graph.Graph;

public class FlowNetworkUtils {
    private FlowNetworkUtils() {
    }

    // return list of all edges - excludes self loops
    public static Iterable<FlowEdge> edges(Graph<FlowEdge> G) {
        Bag<FlowEdge> list = new LinkedBag<>();
        for (int v = 0; v < G.V(); v++) {
            for (FlowEdge e : G.adj(v)) {
                if (e.w() != v)
                    list.add(e);
            }
        }
        return list;
    }
}
