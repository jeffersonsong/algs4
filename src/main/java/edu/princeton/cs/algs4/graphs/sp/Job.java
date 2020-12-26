package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.utils.io.In;

import static edu.princeton.cs.algs4.utils.ArrayUtils.newIntArray;

public class Job {
    final int id;
    final double duration;
    final int[] precedentTo;

    public Job(int id, double duration, int[] precedentTo) {
        this.id = id;
        this.duration = duration;
        this.precedentTo = precedentTo;
    }

    public static Job[] readJobs(In in) {
        int n = in.readInt();
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            double duration = in.readDouble();
            int m = in.readInt();
            int[] precedents = newIntArray(m, k -> in.readInt());

            jobs[i] = new Job(i, duration, precedents);
        }

        return jobs;
    }
}
