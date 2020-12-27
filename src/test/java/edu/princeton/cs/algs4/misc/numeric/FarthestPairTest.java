package edu.princeton.cs.algs4.misc.numeric;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Point2D;
import edu.princeton.cs.algs4.utils.io.StdOut;
import org.junit.Test;

import static edu.princeton.cs.algs4.misc.numeric.PointsFileReader.readPoints;
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
}
