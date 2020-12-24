package edu.princeton.cs.algs4.strings.search;

public class KMPSearchTest extends SubstringSearchBaseTest {

    @Override
    protected SubstringSearch createSubstringSearch() {
        return new CompiledSubstringSearch(KMP::new);
    }
}