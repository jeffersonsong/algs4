package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class MaxPQBaseTest {
    private PQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMaxPQ();
    }

    protected abstract PQ<Character> createMaxPQ();

    @Test
    public void test() {
        pq.insert('P');
        pq.insert('Q');
        pq.insert('E');
        assertThat(pq.poll(), is('Q'));

        pq.insert('X');
        pq.insert('A');
        pq.insert('M');
        assertThat(pq.poll(), is('X'));

        pq.insert('P');
        pq.insert('L');
        pq.insert('E');
        assertThat(pq.poll(), is('P'));
    }
}