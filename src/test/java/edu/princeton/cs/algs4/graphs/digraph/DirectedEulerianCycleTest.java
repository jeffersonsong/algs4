package edu.princeton.cs.algs4.graphs.digraph;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedEulerianCycleTest {

    @Test
    public void testEulerianCycle() {
        Digraph g = new DigraphImpl(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);

        DirectedEulerianCycle eulerian = new DirectedEulerianCycle(g);
        assertTrue(eulerian.hasEulerianCycle());
    }
}