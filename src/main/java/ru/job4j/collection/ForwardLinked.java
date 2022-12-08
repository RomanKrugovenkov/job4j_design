package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public static void main(String[] args) {
        var list = new ForwardLinked<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    public void add(T value) {
        if (head != null) {
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

    public void addFirst(T value) {
        Node<T> second = head == null ? null : head;
        head = new Node<>(value, second);
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
        if (head == null) {
            throw new NoSuchElementException();
        }
        T firstItem = head.item;
        Node<T> second = head.next;
        head.next = null;
        head.item = null;
        head = second;
        modCount++;
        size--;
        return firstItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> current = head;
            private int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T rsl = current.item;
                current = current.next;
                return rsl;
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
