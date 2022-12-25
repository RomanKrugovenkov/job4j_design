package ru.job4j.tree;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    private Node<E> findByPred(Predicate<Node<E>> predicate) {
        Node<E> rsl = null;
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (predicate.test(el)) {
                rsl = el;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> nodeParent = findBy(parent);
        if (findBy(child).isEmpty() && nodeParent.isPresent()) {
            nodeParent.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public boolean isBinary() {
        Predicate<Node<E>> predicate = el -> el.children.size() > 2;
        return Optional.ofNullable(findByPred(predicate)).isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Predicate<Node<E>> predicate = el -> el.value.equals(value);
        return Optional.ofNullable(findByPred(predicate));
    }
}
