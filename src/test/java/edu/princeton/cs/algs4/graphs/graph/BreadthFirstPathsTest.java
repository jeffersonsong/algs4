package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class BreadthFirstPathsTest {
    private Graph G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyCG.txt");
        G = new GraphImpl(in);
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
        assertThat(path.size(), is(3));
        assertThat(path, hasItems(0, 2, 3));
    }
}