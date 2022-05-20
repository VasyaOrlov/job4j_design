package ru.job4j.find;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Pattern;

public class Find {

    public void find(ArgsValidate param) throws IOException {
        FileVisitor file = new FileVisitor(getCondition(param));
        Files.walkFileTree(Path.of("./"), file);
        try (PrintWriter out = new PrintWriter(new FileWriter(param.get("o")))) {
            for (Path path : file.getPath()) {
                out.println(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Predicate<Path> getCondition(ArgsValidate param) {
        String type = param.get("t");
        String temp = param.get("n");
        Predicate<Path> rsl = null;
        if ("name".equals(type)) {
            rsl = p -> p.toFile().getName().equals(temp);
        } else if ("mask".equals(type)) {
            rsl = p -> p.toFile().getName().endsWith(temp);
        } else if ("regex".equals(type)) {
            rsl = p -> Pattern.matches(temp, p.toFile().getName());
        }
        return rsl;
    }

    private void validate(ArgsValidate param) {
        if (!Files.isDirectory(Path.of(param.get("d")))) {
            throw new IllegalArgumentException("Некорректный путь директории");
        }
        param.get("n");
        String type = param.get("t");
        if (!"name".equals(type) && !"mask".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("Некорректный тип поиска");
        }
        param.get("o");
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Заполните параметры запуска.");
        }
        Find findFile = new Find();
        ArgsValidate param = ArgsValidate.of(args);
        findFile.validate(param);
        findFile.find(param);
    }
}
