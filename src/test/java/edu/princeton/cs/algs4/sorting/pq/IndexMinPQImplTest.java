package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class IndexMinPQImplTest {
    private IndexPQ<Character> pq;

    @Before
    public void setUp() {
        pq = IndexPQImpl.indexMinPQ(10);
    }

    @Test
    public void test() {
        assertTrue(pq.isEmpty());
        assertThat(pq.size(), is(0));

        String text = "ASORTING";
        for (int i = 0; i < text.length(); i++)
            pq.insert(i, text.charAt(i));

        assertTrue(pq.contains(0));
        assertThat(pq.peek(), is(0));
        assertThat(pq.poll(), is(0));

//        for (int i = 9; i < 12; i++)
//            assertFalse(pq.contains(i));

        assertThat(pq.peek(), is(7));
        pq.changeKey(6, 'B');
        assertThat(pq.peek(), is(6));
    }

    @Test
    public void test2() {
        assertTrue(pq.isEmpty());
        assertThat(pq.size(), is(0));

        String text = "ASORTING";
        for (int i = 0; i < text.length(); i++)
            pq.insert(i, text.charAt(i));

        assertTrue(pq.contains(0));
        assertThat(pq.peek(), is(0));

//        for (int i = 9; i < 12; i++)
//            assertFalse(pq.contains(i));

        pq.changeKey(0, 'X');
        assertThat(pq.peek(), is(7));
    }
}