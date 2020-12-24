package edu.princeton.cs.algs4.strings.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StringQuickSort3WayTest {

    @Test
    public void testSort() {
        String[] a = {"input", "she", "sells", "seashells", "by", "the", "sea", "shore", "the", "shells", "the",
                "sells", "are", "surely", "seashells"};
        String[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        Quick3string.sort(a);

        assertThat(a, is(expected));
    }
}