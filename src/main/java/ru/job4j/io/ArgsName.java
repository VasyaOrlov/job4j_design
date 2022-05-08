package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

/**
 * класс принимает массив параметров, разбивает на пары ключ-значение, записывает эти пары в карту
 */
public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Данного ключа не существует");
        }
        return values.get(key);
    }

    /**
     * метод принимает массив параметров, разбивает на пары ключ-значение, записывает эти пары в карту
     * @param args - массив параметров
     */
    private void parse(String[] args) {
        validate(args);
        for (String str : args) {
            String[] dump = str.split("=", 2);
            values.put(dump[0].substring(1), dump[1]);
        }
    }

    /**
     * Метод производит валидацию входных параметров
     * @param args - массив входных параметров
     */
    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Отсутствуют параметры запуска");
        }
        for (String str : args) {
            if (!str.startsWith("-") || !str.contains("=")) {
                throw new IllegalArgumentException("Нарушение шаблона -ключ=значение");
            }
            String[] dump = str.split("=", 2);
            if (dump[0].length() == 0) {
                throw new IllegalArgumentException("Нарушение шаблона ключа. Ключ не определен");
            }
            if (dump[1].length() == 0) {
                throw new IllegalArgumentException("Нарушение шаблона значения. Значение не определено");
            }
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