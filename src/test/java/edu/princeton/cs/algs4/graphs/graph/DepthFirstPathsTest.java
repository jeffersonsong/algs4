package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DepthFirstPathsTest {
    private Graph<UnweightedEdgeNode> G;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        G = GraphReader.readGraph(in, false);
    }

    @Test
    public void test() {
        DepthFirstPaths<UnweightedEdgeNode> paths = new DepthFirstPaths<>(G, 0);
        assertTrue(paths.hasPathTo(1));
        assertTrue(paths.hasPathTo(2));
        assertTrue(paths.hasPathTo(3));
        assertTrue(paths.hasPathTo(4));
        assertTrue(paths.hasPathTo(5));
        assertTrue(paths.hasPathTo(6));

        assertFalse(paths.hasPathTo(7));
        assertFalse(paths.hasPathTo(8));
        assertFalse(paths.hasPathTo(9));
        assertFalse(paths.hasPathTo(10));
        assertFalse(paths.hasPathTo(11));
        assertFalse(paths.hasPathTo(12));

        List<Integer> path = toList(paths.pathTo(3));
        assertThat(path.size(), is(5));
        assertThat(path, hasItems(0, 6, 4, 5, 3));
    }
}