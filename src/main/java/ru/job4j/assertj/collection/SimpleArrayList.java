package ru.job4j.assertj.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public T[] increaseCont() {
        int length = container.length == 0 ? 2 : container.length * 2;
        return Arrays.copyOf(container, length);
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = increaseCont();
        }
        modCount++;
        container[size++] = value;
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        T returnV = container[index];
        container[index] = newValue;
        return returnV;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        modCount++;
        T returnV = container[index];
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        size--;
        return returnV;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            private int num = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return num < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return container[num++];
            }

        };
    }
}
