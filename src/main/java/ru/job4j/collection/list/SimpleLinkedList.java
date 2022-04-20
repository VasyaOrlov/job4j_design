package ru.job4j.collection.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс для реализации связанного списка
 * @param <E> - обобщеный тип принимаемого объекта в список
 */
public class SimpleLinkedList<E> implements List<E> {

    /**
     * size - количество хранимых объектов
     * first - первый узел хранящий первый объект списка
     * last - последний узел хранящий последний объект списка
     * modCount - количество операций со списком
     */
    private int size;
    private Node<E> first;
    private Node<E> last;
    private int modCount;

    /**
     * метод добавляет узел с объектом в конец списка
     * @param value - добавляемый объект
     */
    @Override
    public void add(E value) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (size == 0) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    /**
     * метод возвращает объект по индексу
     * @param index - индекс
     * @return - возвращаемый объект
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> node;
        if (index < size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        } else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = node.prev;
            }
        }
        return node.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final int expectedModCount = modCount;
            private Node<E> rsl = first;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return rsl != null;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> beforeRsl = rsl;
                rsl = rsl.next;
                return beforeRsl.item;
            }
        };
    }

    /**
     * вложенный класс - описывает узел
     * @param <E> - обобщеный тип принимаемого объекта в список
     */
    private static class Node<E> {

        /**
         * item - объект хранящийся в узле
         * next - ссылка на следующий узел
         * prev - ссылка на предыдущий узел
         */
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}