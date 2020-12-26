package edu.princeton.cs.algs4.strings.sort;

import edu.princeton.cs.algs4.sorting.SortUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MSDTest {
    @Test
    public void testSort() {
        String[] a = {"input", "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "the",
                "sells", "are", "surely", "seashells"};
        String[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        MSD.sort(a);

        assertThat(a, is(expected));
    }

    @Test
    public void tessSortIntArray() {
        int[] a = {4, 2, -1, 0, 5, -1, Integer.MAX_VALUE, Integer.MIN_VALUE};

        MSD.sort(a);

        SortUtils.isSorted(a);
    }
}