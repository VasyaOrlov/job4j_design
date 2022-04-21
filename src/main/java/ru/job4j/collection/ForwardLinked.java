package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс реализует односвязный список
 * @param <T> - тип данных объектов в списке
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * head - первый узел в односвязном списке
     */
    private Node<T> head;

    /**
     * метод добавляет узел в конец списка
     * @param value - добавляемый объект
     */
    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    /**
     * метод добавляет узел с объектом в начало списка
     * @param value - добавляемый объект
     */
    public void addFirst(T value) {
        head = new Node<>(value, head);
    }

    /**
     * метод удаляет первый узел в списке
     * @return - возвращает удаленный объект списка
     */
    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> node = head;
        T rsl = node.value;
        head = head.next;
        node.next = null;
        node.value = null;
        return rsl;
    }

    /**
     * метод проверяет пуст ли список
     * @return - значение пуст ли список
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * метод выполняет переворот односвязного списка
     * @return - возвращает перевернут или нет
     */
    public boolean revert() {
        boolean rsl = false;
        if (!isEmpty() && head.next != null) {
            Node<T> node = head.next;
            head.next = null;
            Node<T> next;
            while (node != null) {
                next = node.next;
                node.next = head;
                head = node;
                node = next;
            }
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    /**
     * вложенный класс - описывает узел списка
     * @param <T>
     */
    private static class Node<T> {
        /**
         * value - объект хранящийся в узле
         * next - ссылка на следующий узел
         */
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}