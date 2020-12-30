package edu.princeton.cs.algs4.fundamentals.dataabstract;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.Validations.checkIndexInRange;

public class DenseMatrix<T> implements Matrix<T> {
    private final Object[][] matrix;
    private final int[] dimension;

    public DenseMatrix(int rows, int cols) {
        checkArgument(rows>=0, "Invalid row count.");
        checkArgument(cols>=0, "Invalid column count.");

        this.matrix = new Object[rows][cols];
        this.dimension = new int[]{rows, cols};
    }

    @Override
    public T get(int r, int c) {
        validate(r, c);
        return (T)matrix[r][c];
    }

    @Override
    public void set(int r, int c, T val) {
        validate(r, c);
        matrix[r][c] = val;
    }

    @Override
    public int[] dimension() {
        return dimension;
    }

    private void validate(int r, int c) {
        checkIndexInRange(r, 0, this.dimension[0]);
        checkIndexInRange(c, 0, this.dimension[1]);
    }
}
