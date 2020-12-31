package edu.princeton.cs.algs4.context.suffixarray;

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

    @Test
    public void testTinyTale() {
        String text = "it was the best of times it was the worst of times " +
                "it was the age of wisdom it was the age of foolishness " +
                "it was the epoch of belief it was the epoch of incredulity " +
                "it was the season of light it was the season of darkness " +
                "it was the spring of hope it was the winter of despair ";

        String result = lrs(text);
        assertThat(result, is("st of times it was the "));
    }
}