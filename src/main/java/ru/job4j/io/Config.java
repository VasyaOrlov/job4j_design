package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * класс считывает конфигурационный файл и добавляет значения в карту
 */
public class Config {

    /**
     * path - путь к файлу
     * values - карта
     */
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    /**
     * метод считывает конфигурационный файл и добавляет ключ-значение в карту
     */
    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines()
                    .filter(e -> e.length() > 0)
                    .filter(e -> !e.startsWith("#"))
                    .forEach(e -> {
                        if (!e.contains("=")) {
                            throw new IllegalArgumentException("Нарушение шаблона ключ=значение. Отсутствует знак '='");
                        }
                        String[] word = e.split("=", 2);
                        if (word[0].length() == 0 || word[1].length() == 0) {
                            throw new IllegalArgumentException(
                                    "Нарушение шаблона ключ=значение. Отсутсвуют значения ключа и/или значения");
                        } else {
                            values.put(word[0], word[1]);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * метод возвращает значение карты по ключу
     * @param key - ключ
     * @return - значение в карте соответствующее данному ключу
     */
    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}