package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.UnWeightedEdge;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.graphs.graph.GraphTestUtils.toIdList;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.core.Is.is;
import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DirectedCycleTest {

    @Test
    public void testWithSmallCycle() {
        Graph<Edge> G = new GraphImpl<>(3, true);
        G.addEdge(0, new UnWeightedEdge(0, 1));
        G.addEdge(1, new UnWeightedEdge(1, 2));
        G.addEdge(2, new UnWeightedEdge(2, 0));

        DirectedCycle<Edge> finder = new DirectedCycle<>(G);
        assertTrue(finder.hasCycle());
    }

    @Test
    public void testWithCycle() {
        In in = new In("src/test/resources/42digraph/tinyDG.txt");
        Graph<Edge> G = GraphReader.readDigraph(in);
        DirectedCycle<Edge> finder = new DirectedCycle<>(G);
        assertTrue(finder.hasCycle());

        List<Edge> cycle = toList(finder.cycle());
        List<Integer> idList = toIdList(cycle);
        assertThat(idList, hasItems(5, 4, 3));
        for (int i=0; i < cycle.size() - 1; i++) {
            assertThat(cycle.get(i).w(), is(cycle.get(i + 1).v()));
        }
        assertThat(cycle.get(0).v(), is(cycle.get(cycle.size() - 1).w()));
    }

    @Test
    public void testWithDAG() {
        In in = new In("src/test/resources/42digraph/tinyDAG.txt");
        Graph<Edge> G = GraphReader.readDigraph(in);
        DirectedCycle<Edge> finder = new DirectedCycle<>(G);
        assertFalse(finder.hasCycle());
    }
}