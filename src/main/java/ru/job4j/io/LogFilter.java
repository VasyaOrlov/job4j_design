package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * класс для чтения файла и возврата фильтрованых строк
 */
public class LogFilter {

    /**
     * метод читает файл и фильтрует строки
     * @param file - читаемый файл
     * @return - возвращает список строк
     */
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            rsl = in.lines()
                    .filter(x -> {
                        String[] word = x.split(" ");
                        return word[word.length - 2].equals("404");
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);
    }
}