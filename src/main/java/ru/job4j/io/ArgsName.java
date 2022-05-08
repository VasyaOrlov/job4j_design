package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * класс принимает массив параметров, разбивает на пары ключ-значение, записывает эти пары в карту
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        validateKey(key);
        return values.get(key);
    }

    /**
     * метод принимает массив параметров, разбивает на пары ключ-значение, записывает эти пары в карту
     * @param args - массив параметров
     */
    private void parse(String[] args) {
        for (String str : args) {
            validateStr(str);
            String[] dump = str.split("=", 2);
            String key = dump[0].substring(1);
            String value = dump[1];
            validateValue(value);
            values.put(key, value);
        }
    }

    /**
     * метод производит валидацию значения
     * @param value - значение
     */
    private void validateValue(String value) {
        if (value.length() == 0) {
            throw new IllegalArgumentException("Нарушение шаблона значения. Значение не определено");
        }
    }

    /**
     * метод производит валидацию ключа
     * @param key - ключ
     */
    private void validateKey(String key) {
        if (key.length() == 0) {
            throw new IllegalArgumentException("Нарушение шаблона ключа. Ключ не определен");
        } else if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Данного ключа не существует");
        }
    }

    /**
     * метод производит валидацию строки параметра
     * @param str - параметр
     */
    private void validateStr(String str) {
        if (!str.startsWith("-") || !str.contains("=")) {
            throw new IllegalArgumentException("Нарушение шаблона -ключ=значение");
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}