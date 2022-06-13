package ru.job4j.cache.io;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;

import java.nio.file.Path;
import java.util.Scanner;

public class Emulator {
    private static final int PUT_ACTION = 1;
    private static final int GET_ACTION = 2;
    private Scanner scanner;

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
        AbstractCache<String, String> dfc = new DirFileCache(dir);
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
            if (select == PUT_ACTION) {
                System.out.println("введите название файла для загрузки файла в кеш");
                String str = scanner.nextLine();
                dfc.put(str, dfc.get(str));
            } else if (select == GET_ACTION) {
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
