package edu.princeton.cs.algs4.sorting.pq;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Transaction;
import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Test;

import java.util.List;

import static edu.princeton.cs.algs4.fundamentals.utils.ListUtils.toList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.OrderingComparison.greaterThan;

public class TopMTest {
    @Test
    public void test() {
        In in = new In("src/test/resources/24pq/tinyBatch.txt");
        int m = 7;
        Iterable<Transaction> topTransactions = TopM.topM(in, m);
        List<Transaction> list = toList(topTransactions);
        assertThat(list.size(), is(m));

        for (Transaction transaction : list) {
            assertThat(transaction.amount(), is(greaterThan(3300.00)));
        }
    }
}
