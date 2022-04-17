package ru.job4j.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
import java.util.Objects;

/**
 * Класс для реализации списка на основе динамического массива, аналог ArrayList.
 * @param <T> - обобщеный тип принимаемого объекта в список
 */
public class SimpleArrayList<T> implements List<T> {
    /**
     * container - массив хранимых объектов
     * size - колличество хранимых объектов
     * modCount - колличество операций со списком
     */
    private T[] container;
    private int size;
    private int modCount;

    /**
     * конструктор
     * @param capacity - начальный размер массива container
     */
    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    /**
     * метод добавляет объект value в список
     * @param value - добавляемый объект
     */
    @Override
    public void add(T value) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size += 1;
    }

    /**
     * Метод заменяет объект с индексом index на объект newValue
     * @param index - индекс
     * @param newValue - объект на который заменяют
     * @return - возвращает замененный объект
     */
    @Override
    public T set(int index, T newValue) {
        T rsl = get(index);
        modCount++;
        container[index] = newValue;
        return rsl;
    }

    /**
     * Метод удаляет значение по индексу
     * @param index - индекс
     * @return - возвращает удаленный объект
     */
    @Override
    public T remove(int index) {
        T rsl = get(index);
        modCount++;
        System.arraycopy(container, index + 1, container, index, container.length - index - 1);
        container[container.length - 1] = null;
        size -= 1;
        return rsl;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
    return size;
    }

    private T[] grow() {
    return container.length == 0
            ? Arrays.copyOf(container, 10)
            : Arrays.copyOf(container, container.length * 2);
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            final private int expectedModCount = modCount;
            private int count;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return count < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[count++];
            }

        };
    }

    public static void main(String[] args) {

    }
}