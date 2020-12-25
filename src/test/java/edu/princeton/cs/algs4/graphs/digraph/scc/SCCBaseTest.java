package edu.princeton.cs.algs4.graphs.digraph.scc;

import edu.princeton.cs.algs4.graphs.digraph.Digraph;
import edu.princeton.cs.algs4.graphs.digraph.DigraphImpl;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class SCCBaseTest {
    private SCC scc;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/tinyDG.txt");
        Digraph G = new DigraphImpl(in);
        scc = createSCC(G);
    }

    protected abstract SCC createSCC(Digraph G);

    @Test
    public void test() {
        assertThat(scc.count(), is(5));
        assertThat(scc.id(1), is(0));
        assertTrue(scc.stronglyConnected(0, 4));
        assertTrue(scc.stronglyConnected(9, 12));
        assertFalse(scc.stronglyConnected(11, 4));
    }

}