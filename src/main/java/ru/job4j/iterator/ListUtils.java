package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * класс реализует методы манипуляции списком во время итерирования с помощью ListIterator
 */
public class ListUtils {

    /**
     * метод добавляет элемент до индекса
     * @param list - начальный список
     * @param index - индекс
     * @param value - добавляемый элемент
     * @param <T> - тип объектов
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    /**
     * метод добавляет элемент после индекса
     * @param list - начальный список
     * @param index - индекс
     * @param value - добавляемый элемент
     * @param <T> - тип объектов
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.next();
        iterator.add(value);
    }

    /**
     * метод удаляет все элементы, которые удовлетворяют предикату
     * @param list - начальный список
     * @param filter - предикат, условие удаления
     * @param <T> - тип объектов
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    /**
     * метод заменяет все элементы, которые удовлетворяют предикату
     * @param list - начальный список
     * @param filter - предикат, условие для замены
     * @param value - значение на которое происходит замена
     * @param <T> - тип объектов
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    /**
     * метод удаляет из списка те элементы, которые есть в elements
     * @param list - начальный список
     * @param elements - список элементов которые подлежат удалению
     * @param <T> - тип объектов
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            for (T element : elements) {
                if (next.equals(element)) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}