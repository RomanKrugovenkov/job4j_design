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

    @Override
    public boolean hasNext() {
        if (row == data.length) {
            return false;
        }
        while (row < data.length && data[row].length == 0) {
            if (column < data[row].length) {
                column++;
            } else if (row == data.length - 1) {
                return false;
            } else {
                row++;
                column = 0;
            }
        }
        return true;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (column < data[row].length - 1) {
            return data[row][column++];
        } else {
            return data[row++][column];
        }
    }
}
