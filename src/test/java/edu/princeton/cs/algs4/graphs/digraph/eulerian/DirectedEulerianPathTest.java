package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedEulerianPathTest {
    @Test
    public void testEulerianCycle() {
        Graph<Edge> g = new GraphImpl<>(5, true);
        g.addEdge(1, new Edge(1,0));
        g.addEdge(0, new Edge(0,2));
        g.addEdge(2, new Edge(2,1));
        g.addEdge(0, new Edge(0,3));
        g.addEdge(3, new Edge(3,4));
//        g.addEdge(4, 0);

        DirectedEulerianPath<Edge> eulerian = new DirectedEulerianPath<>(g);
        assertTrue(eulerian.hasEulerianPath());
    }
}