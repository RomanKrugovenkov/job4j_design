package ru.job4j.io.scanner;

import java.io.*;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) throws IOException {
        System.out.println("вычленение чисел из потока");
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 A 2",
                "3 4 B",
                "C 5 6"
        );
        var scanner = new Scanner(new CharArrayReader(data.toCharArray()));
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                System.out.print(scanner.nextInt());
                System.out.print(" ");
            } else {
                scanner.next();
            }
        }
        System.out.println(" ");
        System.out.println("вычленение емейлов из потока");
        var data2 = "empl1@mail.ru, empl2@mail.ru, empl3@mail.ru";
        var scanner2 = new Scanner(new ByteArrayInputStream(data2.getBytes()))
                .useDelimiter(", ");
        while (scanner2.hasNext()) {
            System.out.println(scanner2.next());
        }
        System.out.println(" ");
        System.out.println("создание врем файла и чтение в 16ричной системе счисления");
        var data3 = "A 1B FF 110";
        var file3 = File.createTempFile("data3", null);
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file3))) {
            out.write(data3.getBytes());
        }
        try (var scanner3 = new Scanner(file3).useRadix(16)) {
            while (scanner3.hasNextInt()) {
                System.out.print(scanner3.nextInt());
                System.out.print(" ");
            }
        }
    }
}
