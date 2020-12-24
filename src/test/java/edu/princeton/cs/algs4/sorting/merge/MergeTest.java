package edu.princeton.cs.algs4.sorting.merge;

import edu.princeton.cs.algs4.sorting.elementary.Selection;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MergeTest {
    @Test
    public void test() {
        final String sample = "SORTEXAMPLE";
        final Character[] a = new Character[sample.length()];
        for (int i = 0; i < sample.length(); i++) {
            a[i] = sample.charAt(i);
        }

        Merge.sort(a);
        assertThat(a, is(new Character[]{'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X'}));
    }
}