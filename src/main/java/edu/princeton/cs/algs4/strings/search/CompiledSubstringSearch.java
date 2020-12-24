package edu.princeton.cs.algs4.strings.search;

import java.util.function.Function;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.PreConditions.requiresNotNull;

public class CompiledSubstringSearch implements SubstringSearch {
    private final Function<String, CompiledPatternSearch> factoryMethod;

    public CompiledSubstringSearch(Function<String, CompiledPatternSearch> factoryMethod) {
        requiresNotNull(factoryMethod != null, "Factory method not set.");

        this.factoryMethod = factoryMethod;
    }

    @Override
    public int search(String pat, String txt) {
        checkArgument(pat != null && pat.length() > 0, "Empty pattern.");
        checkArgument(txt != null && txt.length() > 0, "Empty text.");

        CompiledPatternSearch search = factoryMethod.apply(pat);
        return search.search(txt);
    }
}
