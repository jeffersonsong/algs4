package edu.princeton.cs.algs4.misc.numeric;

import edu.princeton.cs.algs4.fundamentals.dataabstract.Point2D;
import edu.princeton.cs.algs4.utils.io.In;

public class PointsFileReader {
    private PointsFileReader() {
    }

    public static Point2D[] readPoints(String filename) {
        try(In in = new In(filename)) {
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
}
