package edu.princeton.cs.algs4.sorting.qsort;

import edu.princeton.cs.algs4.sorting.pq.Heap;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class QuickTest {
    @Test
    public void test() {
        final String sample = "SORTEXAMPLE";
        final Character[] a = new Character[sample.length()];
        for (int i = 0; i < sample.length(); i++) {
            a[i] = sample.charAt(i);
        }

        Quick.sort(a);
        assertThat(a, is(new Character[]{'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X'}));
    }
}