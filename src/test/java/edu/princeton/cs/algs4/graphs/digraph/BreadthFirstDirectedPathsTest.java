package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.graphs.graph.BreadthFirstPaths;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class BreadthFirstDirectedPathsTest {
    private Digraph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyDG2.txt");
        G = new DigraphImpl(in);
    }

    @Test
    public void test() {
        BreadthFirstPaths paths = new BreadthFirstPaths(G, 0);
        assertTrue(paths.hasPathTo(1));
        assertTrue(paths.hasPathTo(2));
        assertTrue(paths.hasPathTo(3));
        assertTrue(paths.hasPathTo(4));
        assertTrue(paths.hasPathTo(5));

        List<Integer> path = toList(paths.pathTo(3));
        assertThat(path.size(), is(4));
        assertThat(path, hasItems(0, 2, 4, 3));
    }
}