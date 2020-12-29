package edu.princeton.cs.algs4.graphs.sp;

public class JobSchedule {
    int id;
    double start, end;

    public JobSchedule(int id, double start, double end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    public double start() {
        return start;
    }

    public double end() {
        return end;
    }

    public String toString() {
        return String.format("%4d %7.1f %7.1f", id, start, end);
    }
}
