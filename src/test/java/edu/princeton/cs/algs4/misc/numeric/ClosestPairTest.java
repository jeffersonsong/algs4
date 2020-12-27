package edu.princeton.cs.algs4.misc.numeric;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Point2D;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Test;

import static edu.princeton.cs.algs4.misc.numeric.PointsFileReader.readPoints;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

public class ClosestPairTest {
    @Test
    public void test() {
        Point2D[] points = readPoints("src/test/resources/99hull/rs1423.txt");
        ClosestPair closest = new ClosestPair(points);
        StdOut.println(closest.distance() + " from " + closest.either() + " to " + closest.other());
        assertThat(closest.distance(), is(closeTo(15.00, 0.01)));
    }

    @Test
    public void test2() {
        int[][] coordinates = {{2, 3}, {12, 30}, {40, 50}, {5, 1}, {12, 10}, {3, 4}};
        Point2D[] points = new Point2D[coordinates.length];
        for (int i=0; i < coordinates.length; i++) {
            points[i] = new Point2D(coordinates[i][0], coordinates[i][1]);
        }
        ClosestPair closest = new ClosestPair(points);
        StdOut.println(closest.distance() + " from " + closest.either() + " to " + closest.other());
        assertThat(closest.distance(), is(closeTo(1.414214, 1e-5)));
    }
}