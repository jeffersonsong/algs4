package edu.princeton.cs.algs4.strings.re;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class NFATest {
    private NFA nfa;

    @Before
    public void setUp() {
        String regexp = "((A*B|AC)D)";
        nfa = new NFA(regexp);
    }

    @Test
    public void test() {
        assertTrue(nfa.recognizes("AAAABD"));
        assertFalse(nfa.recognizes("AAA"));
        assertFalse(nfa.recognizes("AAAAC"));
    }
}
