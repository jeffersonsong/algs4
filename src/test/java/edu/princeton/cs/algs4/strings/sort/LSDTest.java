package edu.princeton.cs.algs4.strings.sort;

import edu.princeton.cs.algs4.sorting.SortUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LSDTest {
    @Test
    public void testSortString() {
        String[] a = {"dab", "add", "cab", "fad", "fee", "bad", "bee", "fed", "bed", "ebb", "ace"};
        String[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        LSD.sort(a, a[0].length());

        assertThat(a, is(expected));
    }

    @Test
    public void tessSortIntArray() {
        int[] a = {4, 2, -1, 0, 5, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};

        LSD.sort(a);

        SortUtils.isSorted(a);
    }

}