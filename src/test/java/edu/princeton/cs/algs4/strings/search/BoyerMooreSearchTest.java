package edu.princeton.cs.algs4.strings.search;

public class BoyerMooreSearchTest extends SubstringSearchBaseTest {

    @Override
    protected SubstringSearch createSubstringSearch() {
        return new CompiledSubstringSearch(BoyerMoore::new);
    }
}