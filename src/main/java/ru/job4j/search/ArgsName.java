package ru.job4j.search;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("args is null");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("key not present");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (var str : args) {
            Pattern pattern = Pattern.compile("-\\S{1,}=\\S{1,}");
            if (!pattern.matcher(str).find()) {
                throw new IllegalArgumentException("pattern -key=value does not match");
            }
            var pair = str.split("=", 2);
            values.putIfAbsent(pair[0].substring(1), pair[1]);
        }
    }
}
