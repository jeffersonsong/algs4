package edu.princeton.cs.algs4.graphs.graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code Edge} class represents a weighted edge in an Graph. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the directed edge and
 *  the weight.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.3</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class UnWeightedEdge implements Edge, Comparable<UnWeightedEdge> {
    private final int v;             // from
    private final int w;             // to

    public UnWeightedEdge(int v, int w) {
        checkArgument(v >= 0, "Vertex names must be nonnegative integers");
        checkArgument(w >= 0, "Vertex names must be nonnegative integers");
        this.v = v;
        this.w = w;
    }

    public int v() {
        return v;
    }

    public int w() {
        return w;
    }

    public UnWeightedEdge reverse() {
        return new UnWeightedEdge(w(), v());
    }

    @Override
    public int compareTo(UnWeightedEdge that) {
        int cmp = Integer.compare(this.v(), that.v());
        return cmp != 0 ? cmp : Integer.compare(this.w(), that.w());
    }

    public String toString() {
        return v() + "->" + w();
    }
}
