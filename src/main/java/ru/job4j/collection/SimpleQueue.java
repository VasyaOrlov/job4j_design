package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * класс реализует организацию данных - очередь
 * @param <T> - тип принимаемого значения
 */
public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * метод возвращает первое значение и удалять его из коллекции
     * @return - возвращает первое значение
     */
    public T poll() {
        if (in.isEmpty() && out.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        return out.pop();
    }

    /**
     * метод помещает значение в конец
     * @param value - значение
     */
    public void push(T value) {
        in.push(value);
    }
}