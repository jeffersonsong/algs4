package edu.princeton.cs.algs4.graphs.graph.hamiltonian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class HamiltonianCycle2Test {
    private Graph<UnWeightedEdge> G;

    @Before
    public void setUp() {
        G = GraphImpl.graph(5);
        addEdge(G, 0, 1);
        addEdge(G, 0, 3);
        addEdge(G, 1, 2);
        addEdge(G, 1, 3);
        addEdge(G, 1, 4);
        addEdge(G, 2, 4);
        addEdge(G, 3, 4);
    }

    private void addEdge(Graph<UnWeightedEdge> G, int v, int w) {
        G.addEdge(v, new UnWeightedEdge(v, w));
    }

    @Test
    public void test() {
        HamiltonianCycle2<UnWeightedEdge> hamiltonianCycle = new HamiltonianCycle2<>(G);
        assertTrue(hamiltonianCycle.isHamiltonianCycle());

        List<Integer> path = hamiltonianCycle.getPath();
        StdOut.println(path);
    }
}