package edu.princeton.cs.algs4.graphs.graph.euler;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphImpl;
import edu.princeton.cs.algs4.graphs.graph.euler.Eulerian;
import edu.princeton.cs.algs4.graphs.graph.euler.EulerianType;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class EulerianTest {
    private Eulerian euler = new Eulerian();

    @Test
    public void testEulerianPath() {
        In in = new In("src/test/resources/41graph/eulerianPath.txt");
        Graph G = new GraphImpl(in);

        assertThat(euler.isEulerian(G), is(EulerianType.EULER_PATH));
    }

    @Test
    public void testEulerianCircle() {
        In in = new In("src/test/resources/41graph/eulerianCircle.txt");
        Graph G = new GraphImpl(in);
        assertThat(euler.isEulerian(G), is(EulerianType.EULER_CIRCLE));
    }

    @Test
    public void testNonEulerian() {
        In in = new In("src/test/resources/41graph/nonEulerian.txt");
        Graph G = new GraphImpl(in);
        assertThat(euler.isEulerian(G), is(EulerianType.NON_EULERIAN));
    }
}