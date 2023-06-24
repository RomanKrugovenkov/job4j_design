package ru.job4j.cache;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> {
    protected Map<K, SoftReference<V>> cache = new HashMap<>();

    public final void put(K key, V value) {
        SoftReference<V> sr = new SoftReference<>(value);
        cache.put(key, sr);
    }

    public final V get(K key) throws IOException {
        V content;
        if (cache.isEmpty()) {
            content = load(key);
        } else {
            content = cache.get(key).get();
        }
        return content;
    }

    protected abstract V load(K key) throws IOException;
}