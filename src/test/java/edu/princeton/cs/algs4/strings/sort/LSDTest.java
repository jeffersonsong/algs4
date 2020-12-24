package edu.princeton.cs.algs4.strings.sort;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LSDTest {
    @Test
    public void testSort() {
        String[] a = {"dab", "add", "cab", "fad", "fee", "bad", "bee", "fed", "bed", "ebb", "ace"};
        String[] expected = Arrays.copyOf(a, a.length);
        Arrays.sort(expected);

        LSD.sort(a, a[0].length());

        assertThat(a, is(expected));
    }

}