package edu.princeton.cs.algs4.context.suffixarray;

import org.junit.Test;

import static edu.princeton.cs.algs4.context.suffixarray.LongestCommonSubstring.lcs;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class LongestCommonSubstringTest {
    @Test
    public void test() {
        String s= "it was the best of times it was the worst of times ";
        String t = "it was the season of light it was the season of darkness ";

        String result = lcs(s, t);
        assertThat(result, is(" it was the "));
    }
}