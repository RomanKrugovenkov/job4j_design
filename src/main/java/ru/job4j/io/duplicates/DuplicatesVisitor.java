package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<String>> files = new HashMap<>();

    public Map<FileProperty, List<String>> getMap() {
        return files;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.getFileName().toString());
        files.putIfAbsent(fileProp, new ArrayList<>());
        files.get(fileProp).add(file.toAbsolutePath().toString());
        return super.visitFile(file, attrs);
    }
}
