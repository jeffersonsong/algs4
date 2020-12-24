package edu.princeton.cs.algs4.strings.search;

public class BruteForceSubstringSearchXTest extends SubstringSearchBaseTest {

    @Override
    protected SubstringSearch createSubstringSearch() {
        return new BruteForceSubstringSearchX();
    }
}