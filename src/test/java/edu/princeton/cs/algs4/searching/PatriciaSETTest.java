package edu.princeton.cs.algs4.searching;

import edu.princeton.cs.algs4.fundamentals.set.SET;
import edu.princeton.cs.algs4.fundamentals.set.SETBaseTest;

public class PatriciaSETTest extends SETBaseTest {

    @Override
    protected SET<String> createSET() {
        return new PatriciaSET();
    }
}
