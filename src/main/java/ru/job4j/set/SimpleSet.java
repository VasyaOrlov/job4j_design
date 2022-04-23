package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;
import java.util.Iterator;

/**
 * класс реализует коллекцию которая не хранит дубликаты
 * @param <T> - тип хранимых объектов
 */
public class SimpleSet<T> implements Set<T> {

    /**
     * set - хранилище объектов
     */
    private final SimpleArrayList<T> set = new SimpleArrayList<>(0);

    /**
     * метод добавляет элементы в коллекцию если такого элемента в коллекции нет
     * @param value - добавляемое значение
     * @return - возвращает true если добавление выполнено
     */
    @Override
    public boolean add(T value) {
        boolean rsl = contains(value);
        if (!rsl) {
            set.add(value);
        }
        return !rsl;
    }

    /**
     * метод проверяет наличие элемента в коллекции
     * @param value - элемент
     * @return - возвращает true если элемент есть в коллекции
     */
    @Override
    public boolean contains(T value) {
        boolean rsl = false;
        for (T e: set) {
            if (e == null || e.equals(value)) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    /**
     * метод возвращает итератор коллекции
     * @return - итератор коллекции
     */
    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}