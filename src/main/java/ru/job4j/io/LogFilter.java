package ru.job4j.io;

import java.io.*;
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
                        return "404".equals(word[word.length - 2]);
                    })
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    /**
     * метод записывает строки из листа log в файл file
     * @param log - список строк
     * @param file - файл для записи
     */
    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(file)))) {
            for (String str : log) {
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        for (String str : log) {
            System.out.println(str);
        }
        save(log, "404.txt");
    }
}