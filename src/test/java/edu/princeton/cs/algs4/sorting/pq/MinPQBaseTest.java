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
        pq.insert('P');
        pq.insert('Q');
        pq.insert('E');
        assertThat(pq.poll(), is('E'));

        pq.insert('X');
        pq.insert('A');
        pq.insert('M');
        assertThat(pq.poll(), is('A'));

        pq.insert('P');
        pq.insert('L');
        pq.insert('E');
        assertThat(pq.poll(), is('E'));
    }
}