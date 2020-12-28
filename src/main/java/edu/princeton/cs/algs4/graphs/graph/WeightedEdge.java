/******************************************************************************
 *  Compilation:  javac DirectedEdge.java
 *  Execution:    java DirectedEdge
 *  Dependencies: StdOut.java
 *
 *  Immutable weighted directed edge.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.graph;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code WeightedEdge} class represents a weighted edge in an
 *  Edge Weighted Graph. Each edge consists of two integers
 *  (naming the two vertices) and a real-value weight. The data type
 *  provides methods for accessing the two endpoints of the directed edge and
 *  the weight.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class WeightedEdge extends UnWeightedEdge {
    private final double weight;            /* edge weight */

    /**
     * Initializes a directed edge from vertex {@code v} to vertex {@code w} with
     * the given {@code weight}.
     * @param x the tail vertex
     * @param y the head vertex
     * @param weight the weight of the directed edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code weight} is {@code NaN}
     */
    public WeightedEdge(int x, int y, double weight) {
        super(x, y);
        checkArgument(!Double.isNaN(weight), "Weight is NaN");
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    @Override
    public WeightedEdge reverse() {
        return new WeightedEdge(w(), v(), weight());
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return v() + "->" + w() + " " + String.format("%5.2f", weight());
    }
}

/******************************************************************************
 *  Copyright 2002-2020, Robert Sedgewick and Kevin Wayne.
 *
 *  This file is part of algs4.jar, which accompanies the textbook
 *
 *      Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 *      Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 *      http://algs4.cs.princeton.edu
 *
 *
 *  algs4.jar is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  algs4.jar is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with algs4.jar.  If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
