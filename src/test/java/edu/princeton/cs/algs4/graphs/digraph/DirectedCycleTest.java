package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectedCycleTest {

    @Test
    public void testWithCycle() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        Digraph G = DigraphReader.readDigraph(in);
        DirectedCycle finder = new DirectedCycle(G);
        assertTrue(finder.hasCycle());

        List<Integer> cycle = toList(finder.cycle());
        assertThat(cycle, is(asList(3, 5, 4, 3)));
    }

    @Test
    public void testWithDAG() {
        In in = new In("src/test/resources/42digraph/tinyDAG.txt");
        Digraph G = DigraphReader.readDigraph(in);
        DirectedCycle finder = new DirectedCycle(G);
        assertFalse(finder.hasCycle());
    }
}