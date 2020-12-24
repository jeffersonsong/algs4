package edu.princeton.cs.algs4.strings.sort;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class KeyIndexedCountingTest {

    @Test
    public void test() {
        char[] a = "dacffbdbfbea".toCharArray();
        String expected = "aabbbcddefff";
        KeyIndexedCounting.sort(a);
        assertThat(new String(a), is(expected));
    }

}