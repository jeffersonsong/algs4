package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class DirectedCycleXTest {

    @Test
    public void testWithCycle() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        Graph<Edge> G = GraphReader.readDigraph(in);
        DirectedCycleX<Edge> finder = new DirectedCycleX<>(G);
        assertTrue(finder.hasCycle());

        List<Integer> cycle = toList(finder.cycle());
        assertThat(cycle, is(asList(12, 9, 11, 12)));
    }

    @Test
    public void testWithDAG() {
        In in = new In("src/test/resources/42digraph/tinyDAG.txt");
        Graph<Edge> G = GraphReader.readDigraph(in);
        DirectedCycleX<Edge> finder = new DirectedCycleX<>(G);
        assertFalse(finder.hasCycle());
    }
}
