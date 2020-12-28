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
public interface Edge {
    int v();

    int w();

    int other(int vertex);

    Edge reverse();
}
