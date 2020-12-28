package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.fundamentals.unionfind.UF;
import edu.princeton.cs.algs4.fundamentals.unionfind.UFImpl;
import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class MSTValidator {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    // check optimality conditions (takes time proportional to E V lg* V)
    public static boolean check(MST mst, Graph<WeightedEdge> G) {
        checkArgument(!G.isDirected());
        return checkWeight(mst) &&
                checkSpanningForest(mst, G) &&
                checkMinimalSpanningForest(mst, G);
    }

    private static boolean checkWeight(MST mst) {
        // check weight
        double totalWeight = 0.0;
        for (WeightedEdge e : mst.edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - mst.weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, mst.weight());
            return false;
        }
        return true;
    }

    private static boolean checkSpanningForest(MST mst, Graph<WeightedEdge> g) {
        // check that it is acyclic
        UF uf = new UFImpl(g.V());
        for (WeightedEdge e : mst.edges()) {
            int v = e.v(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (int i=0; i < g.V(); i++) {
            for (WeightedEdge e : g.adj(i)) {
                int v = e.v(), w = e.other(v);
                if (uf.find(v) != uf.find(w)) {
                    System.err.println("Not a spanning forest");
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean checkMinimalSpanningForest(MST mst, Graph<WeightedEdge> g) {
        // check that it is a minimal spanning forest (cut optimality conditions)
        for (WeightedEdge e : mst.edges()) {

            // all edges in MST except e
            UF uf2 = new UFImpl(g.V());
            for (WeightedEdge f : mst.edges()) {
                int x = f.v(), y = f.other(x);
                if (f != e) uf2.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (int i=0; i < g.V(); i++) {
                for (WeightedEdge f : g.adj(i)) {
                    int x = f.v(), y = f.other(x);
                    if (uf2.find(x) != uf2.find(y)) {
                        if (f.weight() < e.weight()) {
                            System.err.println("Edge " + f + " violates cut optimality conditions");
                            return false;
                        }
                    }
                }
            }

        }
        return true;
    }
}
