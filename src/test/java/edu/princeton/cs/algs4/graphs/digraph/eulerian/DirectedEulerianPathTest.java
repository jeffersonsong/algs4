package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.UnweightedEdgeNode;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedEulerianPathTest {
    @Test
    public void testEulerianCycle() {
        Graph<UnweightedEdgeNode> g = new GraphImpl<>(5, true);
        g.addEdge(1, new UnweightedEdgeNode(0));
        g.addEdge(0, new UnweightedEdgeNode(2));
        g.addEdge(2, new UnweightedEdgeNode(1));
        g.addEdge(0, new UnweightedEdgeNode(3));
        g.addEdge(3, new UnweightedEdgeNode(4));
//        g.addEdge(4, 0);

        DirectedEulerianPath<UnweightedEdgeNode> eulerian = new DirectedEulerianPath<>(g);
        assertTrue(eulerian.hasEulerianPath());
    }
}