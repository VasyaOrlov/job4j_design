package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

/**
 * класс реализует поиск файлов по определенному условию
 */
public class Search {
    public static void main(String[] args) throws IOException {
        validate(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    /**
     * метод проходит по директории указанного пути и возвращает список path удовлетворяющих условию
     * @param root - путь
     * @param condition - условие
     * @return - список path
     * @throws IOException - IO сключение
     */
    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    /**
     * метод проводит валидацию входных параметров
     * @param args - массив аргументов
     */
    public static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Заполните параметры запуска.");
        } else if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("Некорректный путь");
        } else if (!args[1].startsWith(".")) {
            throw new IllegalArgumentException("Некорректное разрешение файла");
        }
    }
}