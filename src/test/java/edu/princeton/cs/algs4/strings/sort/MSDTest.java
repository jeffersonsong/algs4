package edu.princeton.cs.algs4.strings.sort;

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
}