package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String INDENT = "-";
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int amount = item.getNumber().length();
            if (amount > 2) {
                System.out.println(INDENT.repeat(amount) + item.getNumber() + item.getName());
            } else {
                System.out.println(item.getNumber() + item.getName());
            }
        }
    }
}
