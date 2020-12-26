package edu.princeton.cs.algs4.graphs.sp;

import edu.princeton.cs.algs4.utils.io.In;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArbitrageTest {
    private Arbitrage arbitrage;

    @Before
    public void setUp() {
        In in = new In("src/test/resources/44sp/rates.txt");
        arbitrage = new Arbitrage(in);
    }

    @Test
    public void test() {
        assertTrue(arbitrage.hasOpportunity());

        arbitrage.print();
    }

}