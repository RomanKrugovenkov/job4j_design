package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
    }

    @SuppressWarnings("checkstyle:SimplifyBooleanExpression")
    @Override
    public boolean hasNext() {
        while (column >= data[row].length) {
            if (row < data.length - 1) {
                row++;
                column = 0;
            } else {
                return false;
            }
        }
        return row < data.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[row][column++];
    }
}

