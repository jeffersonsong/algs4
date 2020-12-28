package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class DepthFirstOrderTest {
    private Graph<Edge> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/42digraph/tinyDG3.txt");
        G = GraphReader.readDigraph(in);
    }

    @Test
    public void test() {
        DepthFirstOrder<Edge> dfs = new DepthFirstOrder<>(G);
        List<Integer> tasks = toList(dfs.reversePost());
        assertThat(tasks, is(asList(3, 6, 0, 5, 2, 1, 4)));
    }
}