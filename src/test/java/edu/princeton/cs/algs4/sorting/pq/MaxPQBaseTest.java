package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public abstract class MaxPQBaseTest {
    private MaxPQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMaxPQ();
    }

    protected abstract MaxPQ<Character> createMaxPQ();

    @Test
    public void test() {
        pq.insert('P');
        pq.insert('Q');
        pq.insert('E');
        assertThat(pq.delMax(), is('Q'));

        pq.insert('X');
        pq.insert('A');
        pq.insert('M');
        assertThat(pq.delMax(), is('X'));

        pq.insert('P');
        pq.insert('L');
        pq.insert('E');
        assertThat(pq.delMax(), is('P'));
    }
}