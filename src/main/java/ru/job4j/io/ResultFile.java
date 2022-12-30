package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int size = 10;
            int[][] array = new int[size][size];
            for (int row = 0; row < size; row++) {
                for (int cell = 0; cell < size; cell++) {
                    array[row][cell] = (row + 1) * (cell + 1);
                    out.write(String.valueOf(array[row][cell]).concat(" ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}