package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class CPMTest {
    private Job[] jobs;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/44sp/jobsPC.txt");
        jobs = Job.readJobs(in);
    }

    @Test
    public void test() {
        JobSchedule[] schedules = CPM.schedule(jobs);

        double max = 0.0;

        for (JobSchedule schedule : schedules) {
            if (schedule.end > max) {
                max = schedule.end;
            }
        }
        assertThat(max, is(closeTo(173.0, 1e-5)));

        CPM.print(jobs, schedules);
    }
}