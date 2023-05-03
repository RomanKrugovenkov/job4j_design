package ru.job4j.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccess {

    public static void main(String[] args)  {
        try {
            RandomAccessFile randomAccess = new RandomAccessFile("data/random.txt", "rw");
            /*запись в файл произвольного доступа*/
            randomAccess.writeInt(100);
            randomAccess.writeChar('A');
            randomAccess.writeBoolean(true);

            /*перенос указателя в начало и чтение из файла ПД*/
            randomAccess.seek(0);
            System.out.println(randomAccess.readInt());
            System.out.println(randomAccess.readChar());
            System.out.println(randomAccess.readBoolean());

            System.out.println("Положение указателя после boolean: " + randomAccess.getFilePointer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
