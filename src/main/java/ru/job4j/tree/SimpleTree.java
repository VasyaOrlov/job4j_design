package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

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

    /**
     * Метод находит узел на основании условия
     * @param condition - условие
     * @return Optional узел
     */
    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    /**
     * метод определяет является ли объект бинарным деревом
     * @return  является ли объект бинарным деревом
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(eNode -> eNode.children.size() > 2).isEmpty();
    }

    /**
     * Метод находит узел со значением value
     * @param value - значение ноды
     * @return Optional узел
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }
}