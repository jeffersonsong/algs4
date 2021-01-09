package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CycleTest {
    @Test
    public void testCycle() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        Graph<Edge> G = GraphReader.readGraph(in);
        Cycle<Edge> finder = new Cycle<>(G);
        assertTrue(finder.hasCycle());
    }

    @Test
    public void testNoCycle() {
        Graph<Edge> G = Graph.graph(3);
        G.addEdge(0, new UnWeightedEdge(0, 1));
        G.addEdge(1, new UnWeightedEdge(1, 2));
        Cycle<Edge> finder = new Cycle<>(G);
        assertFalse(finder.hasCycle());
    }
}