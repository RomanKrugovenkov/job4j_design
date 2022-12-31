package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> inPutList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            inPutList = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> rsl = new ArrayList<>();
        for (var str : inPutList) {
            var temp = str.split(" ");
            if ("404".equals(temp[temp.length - 2])) {
                rsl.add(str);
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}
