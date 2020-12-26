package edu.princeton.cs.algs4.sorting.merge;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InversionsTest {
    @Test
    public void testCountInversesIntArray() {
        int[] a = {3, 1, 2};

        assertThat(Inversions.count(a), is(2L));;
    }

    @Test
    public void testCountInversesComparableArray() {
        String[] a = {"3", "1", "2"};

        assertThat(Inversions.count(a), is(2L));;
    }
}