package ru.job4j.ood.isp.menu;

public class SimpleMenuPrinter implements MenuPrinter {
    private static final String INDENT = "-";
    @Override
    public void print(Menu menu) {
        for (Menu.MenuItemInfo item : menu) {
            int amount = item.getNumber().length();
            System.out.println(amount > 2
                    ? INDENT.repeat(amount) + item.getNumber() + item.getName()
                    : item.getNumber() + item.getName());
        }
    }
}
