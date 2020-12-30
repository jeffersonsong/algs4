package edu.princeton.cs.algs4.graphs.graph.hamiltonian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class HamiltonianTest {
    @Test
    public void testHamiltonCycle() {
        Graph<UnWeightedEdge> graph = hamiltonCycle();

        HamiltonianPath<UnWeightedEdge> hamiltonianPath = new HamiltonianPath<>(graph);
        assertTrue(hamiltonianPath.isHamiltonianPath());

        HamiltonianCycle<UnWeightedEdge> hamiltonianCycle = new HamiltonianCycle<>(graph);
        assertTrue(hamiltonianCycle.isHamiltonianCycle());

        List<Integer> path = hamiltonianCycle.getPath();
        StdOut.println(path);
    }

    @Test
    public void testHamiltonPath() {
        Graph<UnWeightedEdge> graph = hamiltonPath();

        HamiltonianPath<UnWeightedEdge> hamiltonianPath = new HamiltonianPath<>(graph);
        assertTrue(hamiltonianPath.isHamiltonianPath());

        List<Integer> path = hamiltonianPath.getPath();
        StdOut.println(path);

        HamiltonianCycle<UnWeightedEdge> hamiltonianCycle = new HamiltonianCycle<>(graph);
        assertFalse(hamiltonianCycle.isHamiltonianCycle());
    }

    private Graph<UnWeightedEdge> hamiltonCycle() {
        Graph<UnWeightedEdge> G = hamiltonPath();
        addEdge(G, 0, 1);
        return G;
    }

    private Graph<UnWeightedEdge> hamiltonPath() {
        Graph<UnWeightedEdge> G = GraphImpl.graph(5);

        addEdge(G, 0, 3);
        addEdge(G, 1, 2);
        addEdge(G, 1, 3);
        addEdge(G, 1, 4);
        addEdge(G, 2, 4);
        addEdge(G, 3, 4);
        return G;
    }

    private void addEdge(Graph<UnWeightedEdge> G, int v, int w) {
        G.addEdge(v, new UnWeightedEdge(v, w));
    }

}