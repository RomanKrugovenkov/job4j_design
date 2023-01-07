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
import java.util.regex.Pattern;

public class CSVReader {

    public static void handle(ArgsName argsName) throws Exception {
        validation(argsName);
        List<String[]> linesList = new ArrayList<>();
        var scanner = new Scanner(getPath(argsName)).useDelimiter(System.lineSeparator());
        while (scanner.hasNext()) {
            var strArray = scanner.next().split(getDelimiter(argsName));
            linesList.add(strArray);
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(getOut(argsName).toFile()))) {
            var indexList = getIndex(argsName, linesList.get(0));
            for (var line : linesList) {
                for (var i : indexList) {
                    String del = i.equals(indexList.get(indexList.size() - 1)) ? "" : getDelimiter(argsName);
                    out.write(line[i] + del);
                }
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validation(ArgsName argsName) {
        Pattern patternName = Pattern.compile("\\S{1,}\\.\\S{1,}");
        if (!patternName.matcher(getPath(argsName).toString()).find()) {
            throw new IllegalArgumentException(String.format("Not file name \"%s\"", getPath(argsName)));
        }
        if (!patternName.matcher(getOut(argsName).toString()).find()) {
            throw new IllegalArgumentException(String.format("Not file name \"%s\"", getOut(argsName)));
        }
        Pattern patternSim = Pattern.compile("\\D");
        if (!patternSim.matcher(getDelimiter(argsName)).find()) {
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
        return indexList;
    }
}
