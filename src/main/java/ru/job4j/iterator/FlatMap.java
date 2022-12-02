package ru.job4j.iterator;

import java.util.*;
import java.util.stream.StreamSupport;

public class FlatMap<T> implements Iterator<T> {
    private final Iterator<Iterator<T>> data;
    private Iterator<T> cursor = Collections.emptyIterator();

    public FlatMap(Iterator<Iterator<T>> data) {
        this.data = data;
        cursor = StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                data, Spliterator.ORDERED), false)
                .flatMap(itr -> StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                itr, Spliterator.ORDERED), false))
                .toList().iterator();
    }

    @Override
    public boolean hasNext() {
        return cursor.hasNext();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return cursor.next();
    }
}
