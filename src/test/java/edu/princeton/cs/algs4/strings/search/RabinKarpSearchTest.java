package edu.princeton.cs.algs4.strings.search;

public class RabinKarpSearchTest extends SubstringSearchBaseTest {
    @Override
    protected SubstringSearch createSubstringSearch() {
        return new CompiledSubstringSearch(RabinKarp::new);
    }
}