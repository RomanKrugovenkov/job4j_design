package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements LinkedList<E> {

    private int size = 0;
    private int modCount = 0;
    private Node<E> head;

    public SimpleLinkedList() {
    }

    public static void main(String[] args) {
        var list = new SimpleLinkedList<>();
        list.add(1);
        list.add(2);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }

    @Override
    public void add(E value) {
        if (head != null) {
            Node<E> last = head;
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

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        int counter = 0;
        Node<E> rsl = head;
        while (counter < index) {
            rsl = rsl.next;
            counter++;
        }
        return rsl.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int expectedModCount = modCount;
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return current != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = current.item;
                current = current.next;
                return rsl;
            }

        };
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }
}
