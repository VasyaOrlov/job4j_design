package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс наследует класс SimpleFileVisitor и переопределяет метод visitFile для записи моделей данных в карту
 */
public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<Path>> map = new HashMap<>();

    /**
     * метод записывает в карту ключ (данные файла по file) - значение (список всех Path для соответствующего файла)
     * @param file - a reference to the file
     * @param attrs - the file's basic attribute
     * @return - возвращает указание на дальнейший обход
     * @throws IOException - исключение IOE
     */
    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.exists(file) && !Files.isDirectory(file)) {
            FileProperty temp = new FileProperty(Files.size(file), file.getFileName().toString());
            map.putIfAbsent(temp, new ArrayList<>());
            map.get(temp).add(file);
        }
        return super.visitFile(file, attrs);
    }

    /**
     * метод выводит на консоль полные пути к файлам которые имеют дубликаты
     */
    public void showDuplicate() {
        for (FileProperty file : map.keySet()) {
            List<Path> list = map.get(file);
            if (list.size() > 1) {
                for (Path path : list) {
                    System.out.println(path);
                }
                System.out.println();
            }
        }
    }
}