package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinPQBaseTest {
    private PQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMinPQ();
    }

    protected PQ<Character> createMinPQ() {
        return PQIml.minPQ();
    }

    @Test
    public void test() {
        pq.add('P');
        pq.add('Q');
        pq.add('E');
        assertThat(pq.poll(), is('E'));

        pq.add('X');
        pq.add('A');
        pq.add('M');
        assertThat(pq.poll(), is('A'));

        pq.add('P');
        pq.add('L');
        pq.add('E');
        assertThat(pq.poll(), is('E'));
    }
}