package ru.job4j.collection;

/**
 * класс реализует структуру данных Stack
 * @param <T> - тип хранимых объектов
 */
public class SimpleStack<T> {

    /**
     * linked - односвязный список
     */
    private final ForwardLinked<T> linked = new ForwardLinked<>();

    /**
     * метод удаляет первый элемент стека
     * @return - возвращает значение элемента
     */
    public T pop() {
        return linked.deleteFirst();
    }

    /**
     * метод добавляет объект в начало стека
     * @param value - вставляемый объект
     */
    public void push(T value) {
        linked.addFirst(value);
    }

    /**
     * метод проверяет пуст ли список
     * @return - значение пуст ли список
     */
    public boolean isEmpty() {
        return linked.isEmpty();
    }
}