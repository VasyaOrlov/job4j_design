package ru.job4j.tree;

import java.util.*;

/**
 * класс реализует интерфейс Tree
 * @param <E> - обобщенный тип объектов
 */
public class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * метод добавляет в узел со значением parent дочерний узел со значением child
     * @param parent - значение узла в который добавляем
     * @param child - значение узла который добавляют
     * @return - выполнено ли добавление
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        if (findBy(parent).isPresent() && findBy(child).isEmpty()) {
            findBy(parent).get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}