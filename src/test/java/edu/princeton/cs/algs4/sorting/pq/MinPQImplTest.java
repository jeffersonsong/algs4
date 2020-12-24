package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MinPQImplTest {
    private MinPQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMinPQ();
    }

    protected MinPQ<Character> createMinPQ() {
        return new MinPQImpl<>();
    }

    @Test
    public void test() {
        pq.insert('P');
        pq.insert('Q');
        pq.insert('E');
        assertThat(pq.delMin(), is('E'));

        pq.insert('X');
        pq.insert('A');
        pq.insert('M');
        assertThat(pq.delMin(), is('A'));

        pq.insert('P');
        pq.insert('L');
        pq.insert('E');
        assertThat(pq.delMin(), is('E'));
    }
}