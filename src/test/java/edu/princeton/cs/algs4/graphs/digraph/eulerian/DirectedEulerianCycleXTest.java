package edu.princeton.cs.algs4.graphs.digraph.eulerian;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.digraph.DigraphImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectedEulerianCycleXTest {

    @Test
    public void testEulerianCycle() {
        Digraph g = new DigraphImpl(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0);

        DirectedEulerianCycleX eulerian = new DirectedEulerianCycleX();
        assertTrue(eulerian.isEulerianCycle(g));
    }
}