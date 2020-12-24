package edu.princeton.cs.algs4.strings.search;

public class BruteForceSubstringSearchTest extends SubstringSearchBaseTest {

    @Override
    protected SubstringSearch createSubstringSearch() {
        return new BruteForceSubstringSearch();
    }
}