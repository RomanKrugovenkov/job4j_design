package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (LOAD_FACTOR * capacity <= count) {
            expand();
        }
        boolean rsl = false;
        int i = index(key);
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private int index(K key) {
        return key == null ? 0 : indexFor(hash(key.hashCode()));
    }

    private boolean keysEquals(K key) {
        int i = index(key);
        int hashCodeKey = Objects.hashCode(key);
        int hashCodeTable = table[i] == null ? 0 : Objects.hashCode(table[i].key);
        return Objects.equals(hashCodeKey, hashCodeTable)
                && Objects.equals(key, table[i].key);
    }

    private void expand() {
        MapEntry<K, V>[] tempTable = table;
        capacity = capacity * 2;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> cell : tempTable) {
            if (cell != null) {
                table[index(cell.key)] = new MapEntry<>(cell.key, cell.value);
                count--;
            }
        }
    }

    @Override
    public V get(K key) {
        return keysEquals(key) ? table[index(key)].value : null;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (keysEquals(key)) {
            table[index(key)] = null;
            rsl = true;
            count--;
            modCount++;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int num = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (num < capacity - 1 && table[num] == null) {
                    num++;
                }

                return num < capacity && table[num] != null;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[num++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key) && Objects.equals(value, mapEntry.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }

    }
}
