package edu.princeton.cs.algs4.context.suffixarray;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LongestRepeatedSubstringTest {
    private LongestRepeatedSubstring lrs;

    @Before
    public void setUp() {
        lrs = new LongestRepeatedSubstring();
    }

    @Test
    public void testLrs() {
        String text = "aacaagtttacaagc";
        String result = lrs.lrs(text);
        assertThat(result, is("acaag"));
    }

    @Test
    public void testLrs2() {
        String text = "twinstwins";
        String result = lrs.lrs(text);
        assertThat(result, is("twins"));
    }

    @Test
    public void testLrs3() {
        String text = "babaaaabcbabaaaa0";
        String result = lrs.lrs(text);
        assertThat(result, is("babaaaa"));
    }
}