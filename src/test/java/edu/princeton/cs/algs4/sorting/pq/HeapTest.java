package edu.princeton.cs.algs4.sorting.pq;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class HeapTest {
    @Test
    public void test() {
        final String sample = "SORTEXAMPLE";
        final Character[] a = new Character[sample.length()];
        for (int i = 0; i < sample.length(); i++) {
            a[i] = sample.charAt(i);
        }

        Heap.sort(a);
        assertThat(a, is(new Character[]{'A', 'E', 'E', 'L', 'M', 'O', 'P', 'R', 'S', 'T', 'X'}));
    }
}