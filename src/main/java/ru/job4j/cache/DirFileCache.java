package ru.job4j.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        List<Path> files;
        try {
            files = Files.walk(Path.of(cachingDir))
                    .filter(Files::isRegularFile).toList();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (var file : files) {
            try {
                put(file.getFileName().toString(), Files.readString(file));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return cache.get(key).get();
    }
}
