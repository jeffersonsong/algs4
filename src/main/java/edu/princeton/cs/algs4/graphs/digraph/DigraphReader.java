package edu.princeton.cs.algs4.graphs.digraph;

import edu.princeton.cs.algs4.utils.io.In;

import java.util.NoSuchElementException;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

public class DigraphReader {

    /**
     * Initializes a digraph from the specified input stream.
     * The format is the number of vertices <em>V</em>,
     * followed by the number of edges <em>E</em>,
     * followed by <em>E</em> pairs of vertices, with each entry separated by whitespace.
     *
     * @param  in the input stream
     * @throws IllegalArgumentException if {@code in} is {@code null}
     * @throws IllegalArgumentException if the endpoints of any edge are not in prescribed range
     * @throws IllegalArgumentException if the number of vertices or edges is negative
     * @throws IllegalArgumentException if the input stream is in the wrong format
     */
    public static Digraph read(In in) {
        checkArgument (in != null, "argument is null");
        try {
            int V = in.readInt();
            Digraph G = new DigraphImpl(V);
            checkArgument(V >= 0, "number of vertices in a Digraph must be nonnegative");
            int E = in.readInt();
            checkArgument(E >= 0, "number of edges in a Digraph must be nonnegative");
            for (int i = 0; i < E; i++) {
                int v = in.readInt();
                int w = in.readInt();
                G.addEdge(v, w);
            }
            return G;
        }
        catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }
    }

}
