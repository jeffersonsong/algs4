package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.DepthFirstPaths;
import edu.princeton.cs.algs4.graphs.graph.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class DepthFirstDirectedPathsTest {
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/42digraph/tinyDG2.txt");
        G = GraphReader.readDigraph(in);
    }

    @Test
    public void test() {
        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
        assertTrue(paths.hasPathTo(1));

        List<Integer> path = toList(paths.pathTo(3));
        assertThat(path.size(), is(4));
        assertThat(path, hasItems(0, 2, 4, 3));
    }
}