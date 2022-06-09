package ru.job4j.cache;

import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    Scanner scanner;

    private String initDir() {
        boolean rsl = true;
        String dir = null;
        while (rsl) {
            System.out.println("введите директорию:");
            dir = scanner.nextLine();
            if (Path.of(dir).toFile().isDirectory()) {
                rsl = false;
            } else {
                System.out.println("некорректная директория");
            }
        }
        return dir;
    }

    public void init() {
        scanner = new Scanner(System.in);
        String dir = initDir();
        DirFileCache dfc = new DirFileCache(dir);
        while (true) {
            System.out.println("Меню:");
            System.out.println("1 - загрузить содержимое файла в кеш");
            System.out.println("2 - получить содержимое файла из кеша");
            System.out.println("Введите пункт меню:");
            int select = -1;
            try {
                select = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("некорректный ввод");
            }
            if (select == 1) {
                System.out.println("введите название файла для загрузки файла в кеш");
                dfc.load(scanner.nextLine());
            } else if (select == 2) {
                System.out.println("введите название файла для получения файла из кеша");
                System.out.println(dfc.get(scanner.nextLine()));
            } else {
                System.out.println("некорректный пунк меню");
            }
        }
    }

    public static void main(String[] args) {
        new Emulator().init();
    }
}
