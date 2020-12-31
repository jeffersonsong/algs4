package edu.princeton.cs.algs4.graphs.maxflow;

/**
 * Definition. A flow network is an edge-weighted digraph with positive edge weights (which we refer to as capacities).
 * An st-flow network has two identified vertices, a source s and a sink t.
 *
 * Definition. An st-flow in an st-flow network is a set of nonnegative values associ- ated with each edge,
 * which we refer to as edge flows. We say that a flow is feasible if it satisfies the condition that no edge’s flow
 * is greater than that edge’s capacity and the local equilibrium condition that the every vertex’s netflow is zero
 * (except s and t).
 *
 * Maximum st-flow. Given an st-flow network, find an st-flow such that no other flow from s to t has a larger value.
 */
