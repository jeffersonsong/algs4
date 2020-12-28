/******************************************************************************
 *  Compilation:  javac FlowEdge.java
 *  Execution:    java FlowEdge
 *  Dependencies: StdOut.java
 *
 *  Capacitated edge with a flow in a flow network.
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.maxflow;

import edu.princeton.cs.algs4.graphs.graph.Edge;
import edu.princeton.cs.algs4.graphs.graph.EdgeNode;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;

/**
 *  The {@code FlowEdge} class represents a capacitated edge with a 
  * flow in a FlowNetwork. Each edge consists of two integers
 *  (naming the two vertices), a real-valued capacity, and a real-valued
 *  flow. The data type provides methods for accessing the two endpoints
 *  of the directed edge and the weight. It also provides methods for
 *  changing the amount of flow on the edge and determining the residual
 *  capacity of the edge.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/64maxflow">Section 6.4</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class FlowEdge extends Edge {
    // to deal with floating-point roundoff errors
    private static final double FLOATING_POINT_EPSILON = 1E-10;

    private final double capacity;   // capacity
    private double flow;             // flow

    /**
     * Initializes an edge from vertex {@code v} to vertex {@code w} with
     * the given {@code capacity} and zero flow.
     * @param v the tail vertex
     * @param w the head vertex
     * @param capacity the capacity of the edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code capacity < 0.0}
     */
    public FlowEdge(int v, int w, double capacity) {
        super(v, w);
        checkArgument(capacity >= 0.0, "Edge capacity must be non-negative");
        this.capacity  = capacity;
        this.flow      = 0.0;
    }

    /**
     * Initializes an edge from vertex {@code v} to vertex {@code w} with
     * the given {@code capacity} and {@code flow}.
     * @param v the tail vertex
     * @param w the head vertex
     * @param capacity the capacity of the edge
     * @param flow the flow on the edge
     * @throws IllegalArgumentException if either {@code v} or {@code w}
     *    is a negative integer
     * @throws IllegalArgumentException if {@code capacity} is negative
     * @throws IllegalArgumentException unless {@code flow} is between 
     *    {@code 0.0} and {@code capacity}.
     */
    public FlowEdge(int v, int w, double capacity, double flow) {
        super(v, w);
        checkArgument(capacity >= 0.0, "Edge capacity must be non-negative");

        checkArgument(flow <= capacity, "flow exceeds capacity");
        checkArgument(flow >= 0.0, "flow must be non-negative");
        this.capacity  = capacity;
        this.flow      = flow;
    }

    /**
     * Initializes a flow edge from another flow edge.
     * @param e the edge to copy
     */
    public FlowEdge(FlowEdge e) {
        super(e.v(), e.w());
        this.capacity  = e.capacity;
        this.flow      = e.flow;
    }

    /**
     * Returns the capacity of the edge.
     * @return the capacity of the edge
     */
    public double capacity() {
        return capacity;
    }

    /**
     * Returns the flow on the edge.
     * @return the flow on the edge
     */
    public double flow() {
        return flow;
    }

    /**
     * Returns the residual capacity of the edge in the direction
     *  to the given {@code vertex}.
     * @param vertex one endpoint of the edge
     * @return the residual capacity of the edge in the direction to the given vertex
     *   If {@code vertex} is the tail vertex, the residual capacity equals
     *   {@code capacity() - flow()}; if {@code vertex} is the head vertex, the
     *   residual capacity equals {@code flow()}.
     * @throws IllegalArgumentException if {@code vertex} is not one of the endpoints of the edge
     */
    public double residualCapacityTo(int vertex) {
        if      (vertex == v()) return flow;              // backward edge
        else if (vertex == w()) return capacity - flow;   // forward edge
        else throw new IllegalArgumentException("invalid endpoint");
    }

    /**
     * Increases the flow on the edge in the direction to the given vertex.
     *   If {@code vertex} is the tail vertex, this increases the flow on the edge by {@code delta};
     *   if {@code vertex} is the head vertex, this decreases the flow on the edge by {@code delta}.
     * @param vertex one endpoint of the edge
     * @param delta amount by which to increase flow
     * @throws IllegalArgumentException if {@code vertex} is not one of the endpoints
     *   of the edge
     * @throws IllegalArgumentException if {@code delta} makes the flow on
     *   on the edge either negative or larger than its capacity
     * @throws IllegalArgumentException if {@code delta} is {@code NaN}
     */
    public void addResidualFlowTo(int vertex, double delta) {
        checkArgument(delta >= 0.0, "Delta must be nonnegative");

        if      (vertex == v()) flow -= delta;           // backward edge
        else if (vertex == w()) flow += delta;           // forward edge
        else throw new IllegalArgumentException("invalid endpoint");

        // round flow to 0 or capacity if within floating-point precision
        if (Math.abs(flow) <= FLOATING_POINT_EPSILON)
            flow = 0;
        if (Math.abs(flow - capacity) <= FLOATING_POINT_EPSILON)
            flow = capacity;

        checkArgument (flow >= 0.0, "Flow is negative");
        checkArgument(flow <= capacity, "Flow exceeds capacity");
    }

    /**
     * Returns a string representation of the edge.
     * @return a string representation of the edge
     */
    public String toString() {
        return v() + "->" + w() + " " + flow + "/" + capacity;
    }

    @Override
    public EdgeNode copy(int v) {
        return this;
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
