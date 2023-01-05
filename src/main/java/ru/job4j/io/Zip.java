package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void main(String[] args) throws IOException {
        var files = searchFilesWithout(getSource(args), getExtension(args));
        packFiles(files, getTarget(args));
    }

    public static List<Path> searchFilesWithout(Path source, String extension) throws IOException {
        Predicate<Path> predicate = path -> path.toFile().getName().endsWith(extension);
        return Search.search(source, predicate);
    }

    public static void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (var source : sources) {
                zip.putNextEntry(new ZipEntry(source.toFile().getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path getSource(String[] args) {
        ArgsName parameters = ArgsName.of(args);
        return Paths.get(parameters.get("d"));
    }

    public static File getTarget(String[] args) {
        ArgsName parameters = ArgsName.of(args);
        return Paths.get(parameters.get("d")).toFile();
    }

    public static String getExtension(String[] args) {
        ArgsName parameters = ArgsName.of(args);
        return parameters.get("e");
    }
}
