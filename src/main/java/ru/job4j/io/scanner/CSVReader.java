package ru.job4j.io.scanner;

import ru.job4j.io.ArgsName;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        validation(argsName);
        handle(argsName);
    }

    public static void handle(ArgsName argsName) {
        List<String[]> linesList = new ArrayList<>();
        try (var scanner = new Scanner(getPath(argsName)).useDelimiter(System.lineSeparator());
             BufferedWriter out = new BufferedWriter(new FileWriter(getOut(argsName).toFile()))) {
            while (scanner.hasNext()) {
                var strArray = scanner.next().split(getDelimiter(argsName));
                linesList.add(strArray);
            }
            var indexList = getIndex(argsName, linesList.get(0));
            for (var line : linesList) {
                for (var i : indexList) {
                    String del = i.equals(indexList.get(indexList.size() - 1)) ? "" : getDelimiter(argsName);
                    if ("stdout".equals(getOut(argsName).toString())) {
                        System.out.print(line[i] + del);
                    } else {
                        out.write(line[i] + del);
                    }
                }
                if ("stdout".equals(getOut(argsName).toString())) {
                    System.out.println();
                } else {
                    out.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) {
        if (!getPath(argsName).toFile().exists()) {
            throw new IllegalArgumentException(String.format("Not path file \"%s\"", getPath(argsName)));
        }
        if (!getOut(argsName).toFile().isAbsolute() && !"stdout".equals(getOut(argsName).toString())) {
            throw new IllegalArgumentException(String.format("Out is incorrect \"%s\"", getOut(argsName)));
        }
        if (!";".equals(getDelimiter(argsName)) && !",".equals(getDelimiter(argsName))) {
            throw new IllegalArgumentException(String.format("Not delimiter \"%s\"", getDelimiter(argsName)));
        }
    }

    public static Path getPath(ArgsName argsName) {
        return Paths.get(argsName.get("path"));
    }

    public static Path getOut(ArgsName argsName) {
        return Paths.get(argsName.get("out"));
    }

    public static String getDelimiter(ArgsName argsName) {
        return argsName.get("delimiter");
    }

    public static List<Integer> getIndex(ArgsName argsName, String[] columns) {
        var filters = Arrays.stream(argsName.get("filter").split(",")).toList();
        var columnsList = Arrays.stream(columns).toList();
        var indexList = new ArrayList<Integer>();
        for (var filter : filters) {
            var i = columnsList.indexOf(filter);
            indexList.add(i);
        }
        if (indexList.contains(-1)) {
            throw new IllegalArgumentException(String.format("Filters is incorrect \"%s\"", argsName.get("filter")));
        }
        return indexList;
    }
}
