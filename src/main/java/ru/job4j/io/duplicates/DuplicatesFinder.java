package ru.job4j.io.duplicates;

import ru.job4j.io.SearchFiles;
import java.io.IOException;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        duplicatesFinder(Path.of("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\io\\duplicates"));
    }

    public static void duplicatesFinder(Path root) throws IOException {
        DuplicatesVisitor duplVisitor = new DuplicatesVisitor();
        Files.walkFileTree(root, duplVisitor);
        var map = duplVisitor.getMap();
        for (var file : map.entrySet()) {
            if (file.getValue().size() > 1) {
                System.out.printf("%s - %dByte%n", file.getKey().getName(), file.getKey().getSize());
                file.getValue().forEach(System.out::println);
            }
        }
    }
}
