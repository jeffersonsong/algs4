package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class MaxPQBaseTest {
    private PQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMaxPQ();
    }

    protected abstract PQ<Character> createMaxPQ();

    @Test
    public void test() {
        pq.add('P');
        pq.add('Q');
        pq.add('E');
        assertThat(pq.poll(), is('Q'));

        pq.add('X');
        pq.add('A');
        pq.add('M');
        assertThat(pq.poll(), is('X'));

        pq.add('P');
        pq.add('L');
        pq.add('E');
        assertThat(pq.poll(), is('P'));
    }
}