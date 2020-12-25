package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UnorderedMaxPQTest {
    private MaxPQ<Character> pq;

    @Before
    public void setUp() {
        pq = createMaxPQ();
    }

    private MaxPQ<Character> createMaxPQ() {
        return new UnorderedMaxPQ<Character>(1, Comparator.naturalOrder());
    }

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