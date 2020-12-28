package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.impl.GraphReader;
import edu.princeton.cs.algs4.graphs.graph.WeightedEdge;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public abstract class SPBaseTest {
    private SP sp;

    @Before
    public void setUp()  {
        In in = new In("src/test/resources/44sp/tinyEWD.txt");
        Graph<WeightedEdge> G = GraphReader.readEdgeWeightedDigraph(in);
        sp = createSP(G, 0);
    }

    protected abstract SP createSP(Graph<WeightedEdge> G, int s);

    @Test
    public void test() {
        verify(1, 1.05, 3);
        verify(2, 0.26, 1);
        verify(3, 0.99, 3);
        verify(4, 0.38, 1);
        verify(5, 0.73, 2);
        verify(6, 1.51, 4);
        verify(7, 0.60, 2);
    }

    private void verify(int v, double distTo, int size) {
        assertTrue(sp.hasPathTo(v));
        assertThat(sp.distTo(v), is(closeTo(distTo, 1e-4)));
        List<WeightedEdge> edgeList = toList(sp.pathTo(v));
        assertThat(edgeList.size(), is(size));
    }
}