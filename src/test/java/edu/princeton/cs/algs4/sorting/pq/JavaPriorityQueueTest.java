package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import java.util.PriorityQueue;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class JavaPriorityQueueTest {
    private PriorityQueue<Character> pq;

    @Before
    public void setUp() {
        pq = new PriorityQueue<>();
    }

    @Test
    public void test() {
        pq.add('P');
        pq.add('Q');
        pq.add('E');
        assertThat(pq.peek(), is('E'));
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
