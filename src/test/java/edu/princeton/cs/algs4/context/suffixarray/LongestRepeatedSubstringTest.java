package edu.princeton.cs.algs4.context.suffixarray;

import org.junit.Before;
import org.junit.Test;

import static edu.princeton.cs.algs4.context.suffixarray.LongestRepeatedSubstring.lrs;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LongestRepeatedSubstringTest {
    @Test
    public void testLrs() {
        String text = "aacaagtttacaagc";
        String result = lrs(text);
        assertThat(result, is("acaag"));
    }

    @Test
    public void testLrs2() {
        String text = "twinstwins";
        String result = lrs(text);
        assertThat(result, is("twins"));
    }

    @Test
    public void testLrs3() {
        String text = "babaaaabcbabaaaa0";
        String result = lrs(text);
        assertThat(result, is("babaaaa"));
    }
}