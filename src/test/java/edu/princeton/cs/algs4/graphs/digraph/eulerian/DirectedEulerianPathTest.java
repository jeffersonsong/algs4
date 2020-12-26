package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.digraph.DigraphImpl;
import edu.princeton.cs.algs4.graphs.digraph.eulerian.DirectedEulerianPath;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedEulerianPathTest {
    @Test
    public void testEulerianCycle() {
        Digraph g = new DigraphImpl(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
//        g.addEdge(4, 0);

        DirectedEulerianPath eulerian = new DirectedEulerianPath(g);
        assertTrue(eulerian.hasEulerianPath());
    }
}