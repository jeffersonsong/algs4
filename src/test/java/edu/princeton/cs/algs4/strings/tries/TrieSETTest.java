package edu.princeton.cs.algs4.strings.tries;

import edu.princeton.cs.algs4.fundamentals.set.SET;
import edu.princeton.cs.algs4.fundamentals.set.SETBaseTest;

public class TrieSETTest extends SETBaseTest {
    @Override
    protected SET<String> createSET() {
        return new TrieSET();
    }

}