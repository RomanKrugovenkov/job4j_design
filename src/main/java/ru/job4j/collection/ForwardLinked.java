package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public ForwardLinked() {
        this.head = new Node<>(null, null);
    }

    public static void main(String[] args) {
        var list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public void add(T value) {
        if (head.item != null) {
            Node<T> last = head;
            while (last.next != null) {
                last = last.next;
            }
            last.next = new Node<>(value, null);
        } else {
            head = new Node<>(value, null);
        }
        modCount++;
        size++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        int counter = 0;
        Node<T> rsl = head;
        while (counter < index) {
            rsl = rsl.next;
            counter++;
        }
        return rsl.item;
    }

    public T deleteFirst() {
        Node<T> first = head;
        if (head.item != null) {
            head = head.next;
            modCount++;
            size--;
        } else {
            throw new NoSuchElementException();
        }

        return first.item;
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
                return get(num++);
            }

        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}
