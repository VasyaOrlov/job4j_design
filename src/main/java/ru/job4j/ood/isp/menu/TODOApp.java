package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TODOApp {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        Menu menu = new SimpleMenu();
        MenuPrinter printer = new SimpleMenuPrinter();
        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Нажмите 1 для добавления нового пункта списка" + System.lineSeparator()
                    + "Нажмите 2 для просмотра всего списка дел" + System.lineSeparator()
                    + "Нажмите 3 для выхода из программы");
            int input = scanner.nextInt();
            scanner.nextLine();
            if (input == 1) {
                System.out.println("Нужно ли создать главный раздел меню: введите да или нет");
                String mainMenu = scanner.nextLine();
                String parentName = Menu.ROOT;
                if ("нет".equals(mainMenu)) {
                    System.out.println("Введите название родительского пункта меню");
                    parentName = scanner.nextLine();
                }
                System.out.println("Введите название самого пункта меню");
                String childName = scanner.nextLine();
                boolean result = menu.add(parentName, childName, STUB_ACTION);
                if (!result) {
                    throw new IllegalArgumentException("Неправильно введено название родительского пункта");
                }
            } else if (input == 2) {
                printer.print(menu);
            } else if (input == 3) {
                run = false;
            } else {
                System.out.println("Некорректный ввод");
            }
        }
    }
}
