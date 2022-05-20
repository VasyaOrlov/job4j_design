package ru.job4j.find;

import java.util.HashMap;
import java.util.Map;

/**
 * класс принимает массив параметров, разбивает на пары ключ-значение, записывает эти пары в карту
 */
public class ArgsValidate {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(key + " - данного ключа не существует");
        }
        return values.get(key);
    }

    /**
     * метод принимает массив параметров, выполняет валидацию, записывает пары ключ-значение в карту
     * @param args - массив параметров
     */
    private void parse(String[] args) {
        for (String str : args) {
            String[] dump = validate(str);
            values.put(dump[0].substring(1), dump[1]);
        }
    }

    /**
     * Метод производит валидацию входного параметра
     * @param str - строка параметра
     * @return - массив из двух элементов: ключ, значение
     */
    private String[] validate(String str) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Нарушение шаблона -ключ=значение");
            }
            String[] dump = str.split("=", 2);
            if (dump[0].isBlank()) {
                throw new IllegalArgumentException("Нарушение шаблона ключа. Ключ не определен");
            }
            if (dump[1].isBlank()) {
                throw new IllegalArgumentException("Нарушение шаблона значения. Значение не определено");
            }
            return dump;
    }

    public static ArgsValidate of(String[] args) {
        ArgsValidate names = new ArgsValidate();
        names.parse(args);
        return names;
    }
}