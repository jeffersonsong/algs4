package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.fundamentals.unionfind.UF;
import edu.princeton.cs.algs4.fundamentals.unionfind.UFImpl;

public class MSTValidator {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    // check optimality conditions (takes time proportional to E V lg* V)
    public static boolean check(MST mst, EdgeWeightedGraph G) {

        return checkWeight(mst) &&
                checkSpanningForest(mst, G) &&
                checkMinimalSpanningForest(mst, G);
    }

    private static boolean checkWeight(MST mst) {
        // check weight
        double totalWeight = 0.0;
        for (Edge e : mst.edges()) {
            totalWeight += e.weight();
        }
        if (Math.abs(totalWeight - mst.weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %f vs. %f\n", totalWeight, mst.weight());
            return false;
        }
        return true;
    }

    private static boolean checkSpanningForest(MST mst, EdgeWeightedGraph g) {
        // check that it is acyclic
        UF uf = new UFImpl(g.V());
        for (Edge e : mst.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) == uf.find(w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        // check that it is a spanning forest
        for (Edge e : g.edges()) {
            int v = e.either(), w = e.other(v);
            if (uf.find(v) != uf.find(w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }
        return true;
    }

    private static boolean checkMinimalSpanningForest(MST mst, EdgeWeightedGraph g) {
        // check that it is a minimal spanning forest (cut optimality conditions)
        for (Edge e : mst.edges()) {

            // all edges in MST except e
            UF uf2 = new UFImpl(g.V());
            for (Edge f : mst.edges()) {
                int x = f.either(), y = f.other(x);
                if (f != e) uf2.union(x, y);
            }

            // check that e is min weight edge in crossing cut
            for (Edge f : g.edges()) {
                int x = f.either(), y = f.other(x);
                if (uf2.find(x) != uf2.find(y)) {
                    if (f.weight() < e.weight()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }
        return true;
    }
}
