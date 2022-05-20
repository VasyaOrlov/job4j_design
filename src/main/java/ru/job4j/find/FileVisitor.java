package ru.job4j.find;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Класс наследует класс SimpleFileVisitor и переопределяет метод visitFile для записи файлов в список
 */
public class FileVisitor extends SimpleFileVisitor<Path> {

    private final Predicate<Path> condition;
    private final List<Path> list = new ArrayList<>();

    public FileVisitor(Predicate<Path> condition) {
        this.condition = condition;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (condition.test(file)) {
            list.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public List<Path> getPath() {
        return list;
    }
}
