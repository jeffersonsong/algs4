/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.UnweightedEdgeNode;

/**
 * Weighted edge node.
 *
 * @author csong2022
 */
public class WeightedEdgeNode extends UnweightedEdgeNode {
    private final double weight;            /* edge weight */

    public WeightedEdgeNode(int y, double weight) {
        super(y);
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public WeightedEdgeNode copy(int v) {
        return new WeightedEdgeNode(v, this.weight);
    }
}
