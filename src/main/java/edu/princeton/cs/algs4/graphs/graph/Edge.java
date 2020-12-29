package edu.princeton.cs.algs4.graphs.graph;

/**
 *  The {@code Edge} class represents a edge in an Graph or digraph. Each edge consists of two integers
 *  (naming the two vertices). The data type provides methods for accessing the two endpoints of the edge.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public interface Edge {
    /**
     * @return from for directed edge or one vertice for undirected edge.
     */
    int v();

    /**
     * @return to for directed edge or another vertice for undirected edge.
     */
    int w();

    /**
     * @return Edge in reverse direction.
     */
    Edge reverse();
}
