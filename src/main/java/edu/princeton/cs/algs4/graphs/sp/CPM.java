/******************************************************************************
 *  Compilation:  javac CPM.java
 *  Execution:    java CPM < input.txt
 *  Dependencies: EdgeWeightedDigraph.java AcyclicDigraphLP.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/44sp/jobsPC.txt
 *
 *  Critical path method.
 *
 *  % java CPM < jobsPC.txt
 *   job   start  finish
 *  --------------------
 *     0     0.0    41.0
 *     1    41.0    92.0
 *     2   123.0   173.0
 *     3    91.0   127.0
 *     4    70.0   108.0
 *     5     0.0    45.0
 *     6    70.0    91.0
 *     7    41.0    73.0
 *     8    91.0   123.0
 *     9    41.0    70.0
 *  Finish time:   173.0
 *
 ******************************************************************************/

package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.graphs.graph.Graph;
import edu.princeton.cs.algs4.graphs.graph.GraphImpl;
import edu.princeton.cs.algs4.graphs.mst.Edge;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;

import java.util.Arrays;

/**
 * The {@code CPM} class provides a client that solves the
 * parallel precedence-constrained job scheduling problem
 * via the <em>critical path method</em>. It reduces the problem
 * to the longest-paths problem in edge-weighted DAGs.
 * It builds an edge-weighted digraph (which must be a DAG)
 * from the job-scheduling problem specification,
 * finds the longest-paths tree, and computes the longest-paths
 * lengths (which are precisely the start times for each job).
 * <p>
 * This implementation uses {@link AcyclicLP} to find a longest
 * path in a DAG.
 * The program takes &Theta;(<em>V</em> + <em>E</em>) time in
 * the worst case, where <em>V</em> is the number of jobs and
 * <em>E</em> is the number of precedence constraints.
 * <p>
 * For additional documentation,
 * see <a href="https://algs4.cs.princeton.edu/44sp">Section 4.4</a> of
 * <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 * <p>
 * <p>
 * Parallel precedence-constrained scheduling. Given a set of jobs of specified duration
 * to be completed, with precedence constraints that specify that certain jobs
 * have to be completed before certain other jobs are begun, how can we schedule
 * the jobs on identical processors (as many as needed ) such that they are all completed
 * in the minimum amount of time while still respecting the constraints?
 * <p>
 * @author Robert Sedgewick
 * @author Kevin Wayne
 */
public class CPM {

    public static JobSchedule[] schedule(Job[] jobs) {
        // source and sink
        int source = 2 * jobs.length;
        int sink = 2 * jobs.length + 1;

        Graph<Edge> G = buildNetwork(jobs, source, sink);

        // compute longest path
        AcyclicLP lp = new AcyclicLP(G, source);

        JobSchedule[] schedules = getJobSchedules(jobs, lp);

        Arrays.sort(schedules);

        return schedules;
    }

    /**
     * The critical path method for parallel scheduling is to proceed as follows:
     * Create an edge-weighted DAG with a source s, a sink t, and two vertices for each
     * job (a start vertex and an end vertex). For each job, add an edge from its start vertex
     * to its end vertex with weight equal to its duration. For each precedence constraint
     * v->w, add a zero-weight edge from the end vertex corresponding tovs to the beginning
     * vertex corresponding to w. Also add zero-weight edges from the source to each
     * job’s start vertex and from each job’s end vertex to the sink. Now, schedule each job
     * at the time given by the length of its longest path from the source.
     *
     * @param jobs jobs.
     * @param source source.
     * @param sink sink.
     * @return job network.
     */
    private static Graph<Edge> buildNetwork(Job[] jobs, int source, int sink) {
        int n = jobs.length;

        Graph<Edge> G = new GraphImpl<>(2 * n + 2, true);
        for (int i = 0; i < n; i++) {
            Job job = jobs[i];
            G.addEdge(source,new Edge(source, i, 0.0));
            G.addEdge(i + n, new Edge(i + n, sink, 0.0));
            G.addEdge(i,new Edge(i, i + n, job.duration));

            // precedence constraints
            for (int precedent : job.precedentTo) {
                G.addEdge(n + i, new Edge(n + i, precedent, 0.0));
            }
        }
        return G;
    }

    private static JobSchedule[] getJobSchedules(Job[] jobs, AcyclicLP lp) {
        JobSchedule[] schedules = new JobSchedule[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            schedules[i] = new JobSchedule(i, lp.distTo(i), lp.distTo(i + jobs.length));
        }
        return schedules;
    }

    public static void print(Job[] jobs, JobSchedule[] schedules) {
        StdOut.println("Job#\t  start\t    end\t    precedent constraints");
        for (JobSchedule schedule : schedules) {
            StdOut.print(String.format("%4d\t%7.1f\t%7.1f\t", schedule.id, schedule.start, schedule.end));

            for (int i : jobs[schedule.id].precedentTo) {
                StdOut.print('\t');
                StdOut.print(i);
            }
            StdOut.println();
        }
    }

    /**
     * Reads the precedence constraints from standard input
     * and prints a feasible schedule to standard output.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        In in = new In(args[0]);
        Job[] jobs = Job.readJobs(in);
        JobSchedule[] schedules = CPM.schedule(jobs);
        CPM.print(jobs, schedules);
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
