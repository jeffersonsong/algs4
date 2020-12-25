package edu.princeton.cs.algs4.graphs.digraph.scc;

public interface SCC {
    /**
     * Are v and w strongly connected?
     * @param v vertice v.
     * @param w vertice w.
     * @return whether v, w is stringly connected.
     */
    boolean stronglyConnected(int v, int w);

    /**
     * @return number of strong components.
     */
    int count();

    /**
     * Component identifier for v ( between 0 and count()-1 )
     *
     * @param v vertices.
     * @return component id.
     */
    int id(int v);
}
