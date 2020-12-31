package edu.princeton.cs.algs4.utils.io;

import java.util.Iterator;
import java.util.function.Function;

public class IOUtils {
    private IOUtils() {
    }

    /**
     * Iterate through input line by line.
     * @param in input.
     * @param lineReader convert line into object.
     * @param <T> object type.
     * @return object iterator.
     */
    public static <T> Iterator<T> iterate(In in, Function<String, T> lineReader) {
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return in.hasNextLine();
            }

            @Override
            public T next() {
                String line = in.readLine();
                return lineReader.apply(line);
            }
        };
    }

    /**
     * Convert input into iterable.
     * @param in input.
     * @param lineReader convert line into object.
     * @param <T> object type.
     * @return iterable.
     */
    public static <T> Iterable<T> iterable(In in, Function<String, T> lineReader) {
        return () -> iterate(in, lineReader);
    }
}
