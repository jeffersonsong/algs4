package edu.princeton.cs.algs4.graphs.sp;

import java.util.Comparator;

public class JobSchedule implements Comparable<JobSchedule> {
    int id;
    double start, end;

    public JobSchedule(int id, double start, double end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(JobSchedule that) {
        int cmp = Double.compare(this.start, that.start);
        if (cmp != 0) return cmp;
        return Double.compare(this.end, that.end);
    }

    public String toString() {
        return String.format("%4d %7.1f %7.1f", id, start, end);
    }
}
