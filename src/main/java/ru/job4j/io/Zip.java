package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void main(String[] args) throws IOException {
        if (args.length < 3) {
            throw new IllegalArgumentException("Not enough arguments. Usage  ROOT_FOLDER.");
        }
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        Predicate<Path> predicate = path -> !path.toFile().getName().endsWith(getExtension(argsName));
        var paths = searchFilesByPredicate(getSource(argsName), predicate);
        packFiles(paths, getTarget(argsName));
    }

    private static void validation(ArgsName argsName) {
        if (!getSource(argsName).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", getSource(argsName)));
        }
        Pattern patternEx = Pattern.compile("\\.\\S{1,}");
        if (!patternEx.matcher(getExtension(argsName)).find()) {
            throw new IllegalArgumentException(String.format("Not file extension \"%s\"", getExtension(argsName)));
        }
        Pattern patternName = Pattern.compile("\\S{1,}\\.\\S{1,}");
        if (!patternName.matcher(getTarget(argsName).toString()).find()) {
            throw new IllegalArgumentException(String.format("Not file name \"%s\"", getTarget(argsName)));
        }
    }

    public static List<Path> searchFilesByPredicate(Path source, Predicate<Path> predicate) throws IOException {
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

    public static Path getSource(ArgsName argsName) {
        return Paths.get(argsName.get("d"));
    }

    public static File getTarget(ArgsName argsName) {
        return Paths.get(argsName.get("o")).toFile();
    }

    public static String getExtension(ArgsName argsName) {
        return argsName.get("e");
    }
}
