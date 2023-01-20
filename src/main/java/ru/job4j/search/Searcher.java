package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Searcher {

    private static List<Path> files = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        validation(args);
        ArgsName argsName = ArgsName.of(args);
        searchFiles(getSource(argsName), getFilter(argsName));
        writeInFile(getTarget(argsName));
    }

    public static void validation(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        if (args.length < 4) {
            throw new IllegalArgumentException("Root folder is nor enough. Usage  ROOT_FOLDER.");
        }
        if (!getSource(argsName).toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("Not exist %s", getSource(argsName).toString()));
        }
        if (!getTarget(argsName).isFile()) {
            throw new IllegalArgumentException(String.format("Not exist %s", getTarget(argsName).toString()));
        }
        if (getFilter(argsName).isEmpty()) {
            throw new IllegalArgumentException(String.format("Not exist Pattern of search"));
        }
    }

    public static void searchFiles(Path source, String filter) throws IOException {
        ru.job4j.search.SearchFiles searchFiles = new ru.job4j.search.SearchFiles(filter);
        Files.walkFileTree(source, searchFiles);
        files.addAll(searchFiles.getPaths());
    }

    public static void writeInFile(File target) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (var file : files) {
                out.write(file.toString());
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Path getSource(ru.job4j.io.ArgsName argsName) {
        return Paths.get(argsName.get("d"));
    }

    public static File getTarget(ru.job4j.io.ArgsName argsName) {
        return Paths.get(argsName.get("o")).toFile();
    }

    public static String getFilter(ArgsName argsName) {
        String type = argsName.get("t");
        String searchName = argsName.get("n");
        String pattern = null;
        pattern = switch (type) {
            case "name", "regex":
                yield searchName;
            case "mask":
                String[] chars = searchName.split("");
                StringBuilder regex = new StringBuilder();
                for (var ch : chars) {
                    if (ch.equals("*")) {
                        regex.append("\\S{1,}");
                    } else if (ch.equals("?")) {
                        regex.append("\\S");
                    } else {
                        regex.append(ch);
                    }
                }
                yield regex.toString();
            default:
                yield null;
        };
        return pattern;
    }
}
