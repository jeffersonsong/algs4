package edu.princeton.cs.algs4.fundamentals.dataabstract;

import static edu.princeton.cs.algs4.utils.PreConditions.checkArgument;
import static edu.princeton.cs.algs4.utils.Validations.checkIndexInRange;

public class DenseMatrix<T> implements Matrix<T> {
    private final Object[][] matrix;
    private final int rows, cols;

    public DenseMatrix(int rows, int cols) {
        checkArgument(rows>=0, "Invalid row count.");
        checkArgument(cols>=0, "Invalid column count.");

        this.matrix = new Object[rows][cols];
        this.rows = rows;
        this.cols = cols;
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

    private void validate(int r, int c) {
        checkIndexInRange(r, 0, this.rows);
        checkIndexInRange(c, 0, this.cols);
    }
}
