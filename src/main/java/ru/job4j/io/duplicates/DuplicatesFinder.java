package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        Path path = Path.of("./");
        printMap(duplicatesFinder(path));
    }

    public static Map<FileProperty, List<String>> duplicatesFinder(Path root) throws IOException {
        DuplicatesVisitor duplVisitor = new DuplicatesVisitor();
        Files.walkFileTree(root, duplVisitor);
        return duplVisitor.getMap();
    }

    public static void printMap(Map<FileProperty, List<String>> map) {
        for (var file : map.entrySet()) {
            if (file.getValue().size() > 1) {
                System.out.printf("%s - %dByte%n", file.getKey().getName(), file.getKey().getSize());
                file.getValue().forEach(System.out::println);
            }
        }
    }
}
