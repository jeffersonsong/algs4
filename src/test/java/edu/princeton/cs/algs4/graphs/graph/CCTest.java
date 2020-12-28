package edu.princeton.cs.algs4.graphs.graph;

import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class CCTest {
    private CC<Edge> cc;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/41graph/tinyG.txt");
        Graph<Edge> g = GraphReader.readGraph(in);
        cc = new CC<>(g);
    }

    @Test
    public void test() {
        assertThat(cc.count(), is(3));

        int[] component1 = {0, 1, 2, 3, 4, 5, 6};
        int[] component2 = {7, 8};
        int[] component3 = {9, 10, 11, 12};

        validateId(component1, 0);
        validateId(component2, 1);
        validateId(component3, 2);

        validateConnected(component1);
        validateConnected(component2);
        validateConnected(component3);
    }

    private void validateId(int[] component, int id) {
        for (int v : component) {
            assertThat(cc.id(v), is(id));
        }
    }

    private void validateConnected(int[] component) {
        for (int v = 0; v < component.length - 1; v++) {
            for (int w = v + 1; w < component.length; w++) {
                assertTrue(cc.connected(v, w));
            }
        }
    }

}