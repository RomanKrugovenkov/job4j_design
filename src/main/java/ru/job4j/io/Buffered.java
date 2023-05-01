package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        /*побитовое чтение и добавление в файл*/
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/newData.txt"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/output.txt", true))) {
            out.write(in.readAllBytes());
            out.write('\n');
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*посимвольное чтение и добавление в файл*/
        try (BufferedReader br = new BufferedReader(new FileReader("data/input.txt"));
             BufferedWriter bw = new BufferedWriter(new FileWriter("data/output.txt", true))) {
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

