package ru.job4j.cache.menu;

import ru.job4j.cache.DirFileCache;

import java.io.IOException;
import java.util.Scanner;

public class Emulator {
        public static final String MENU = """
                Введите 1, для получения содержимого файла Address.txt.
                Введите 2, для получения содержимого файла Names.txt.
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        start(scanner);
    }

        private static void start(Scanner scanner) throws IOException {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println("Выберите пункт меню");
            String dir = "src/main/java/ru/job4j/cache/files";
            int userChoice = Integer.parseInt(scanner.nextLine());
            if (1 == userChoice) {
                System.out.println("Содержимое файла Address.txt:");
                DirFileCache dfc = new DirFileCache(dir);
                var rsl = dfc.get("Address.txt");
                System.out.println(rsl);
            } else if (2 == userChoice) {
                System.out.println("Содержимое файла Names.txt:");
                DirFileCache dfc = new DirFileCache(dir);
                var rsl = dfc.get("Names.txt");
                System.out.println(rsl);
            } else {
                run = false;
                System.out.println("Конец работы");
            }
        }
    }
}
