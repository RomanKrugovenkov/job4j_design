package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public static void main(String[] args) {
        Config config = new Config("./data/pairWithException.properties");
        config.load();
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            String line;
            while ((line = in.readLine()) != null) {
                if (!line.startsWith("#")
                        && line.length() > 3
                        && line.contains("=")) {
                    var strArray = line.split("=", 2);
                    if (strArray[0].isEmpty() || strArray[1].isEmpty()) {
                        throw new IllegalArgumentException();
                    }
                    values.put(strArray[0], strArray[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }
}
