package edu.princeton.cs.algs4.misc.numeric;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Point2D;
import edu.princeton.cs.algs4.utils.io.In;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class FarthestPairTest {

    @Test
    public void test() {
        Point2D[] points = readPoints("src/test/resources/99hull/rs1423.txt");
        FarthestPair farthest = new FarthestPair(points);
        StdOut.println(farthest.distance() + " from " + farthest.either() + " to " + farthest.other());
        assertThat(farthest.distance(), is(closeTo(38215.69, 0.01)));
    }

    private Point2D[] readPoints(String filename) {
        In in = new In(filename);
        int n = in.readInt();
        Point2D[] points = new Point2D[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point2D(x, y);
        }

        return points;
    }
}