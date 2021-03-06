package ru.job4j.io;

import java.io.*;

/**
 * класс для вывода статистики работы сервера
 */
public class Analizy {

    /**
     * метод по данным файла лога определяет диапозон времени когда сервер не работал и записывает в файл
     * @param source - лог файл
     * @param target - файл для записи
     */
    public void unavailable(String source, String target) {
        boolean job = true;
        try (BufferedReader text = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            while ((line = text.readLine()) != null) {
                if (line.contains("400") || line.contains("500")) {
                    if (job) {
                        job = false;
                        writer.write(line.substring(4) + ";");
                    }
                } else {
                    if (!job) {
                        writer.write(line.substring(4) + ";" + System.lineSeparator());
                        job = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("./data/test.txt", "./data/rsl.txt");
    }
}