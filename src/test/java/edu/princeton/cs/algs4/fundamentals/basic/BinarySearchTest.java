package edu.princeton.cs.algs4.fundamentals.basic;

import org.junit.Test;

import static edu.princeton.cs.algs4.fundamentals.basic.BinarySearch.indexOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class BinarySearchTest {

    @Test
    public void testindexOf() {
        final int[] a = {1, 2, 3, 4, 5, 7, 8, 9, 10};

        assertThat(indexOf(a, 0), is(-1));
        assertThat(indexOf(a, 1), is(0));
        assertThat(indexOf(a, 10), is(8));

        assertThat(indexOf(a, 5), is(4));
        assertThat(indexOf(a, 6), is(-1));
    }
}
