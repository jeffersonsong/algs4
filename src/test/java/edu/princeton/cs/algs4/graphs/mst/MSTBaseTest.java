package edu.princeton.cs.algs4.graphs.mst;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class MSTBaseTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MSTBaseTest.class);
    private MST mst;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/43mst/tinyEWG.txt");
        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
        mst = createMST(G);
    }

    protected abstract MST createMST(EdgeWeightedGraph g);

    @Test
    public void test() {
        for (Edge edge : mst.edges()) {
            LOGGER.info(edge.toString());
        }

        assertThat(mst.weight(), is(closeTo(1.81, 1e-3)));
    }
}