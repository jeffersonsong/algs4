package edu.princeton.cs.algs4.fundamentals.unionfind;

import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Before;
import org.junit.Test;

import static edu.princeton.cs.algs4.fundamentals.unionfind.UFTestUtils.withinSameComponent;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public abstract class UFBaseTest {
    protected UF uf;

    @Before
    public void setUp() {
        final In in = new In("src/test/resources/tinyUF.txt");
        this.uf = this.createUF(in.readInt());

        while (!in.isEmpty()) {
            final int p = in.readInt();
            final int q = in.readInt();
            if (!this.uf.connected(p, q)) {
                this.uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }
    }

    protected abstract UF createUF(int N);

    @Test
    public void test() {
        assertThat(this.uf.count(), is(2));
        final int[] c1 = {0, 1, 2, 5, 6, 7};
        assertTrue(withinSameComponent(this.uf, c1));

        final int[] c2 = {3, 4, 8, 9};
        assertTrue(withinSameComponent(this.uf, c2));
    }
}