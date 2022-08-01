package ru.job4j.ood.dip;

import java.util.HashMap;

/**
 * Класс демонстрирует нарушение принципа инверсии зависимостей DIP
 */
public class CarWash {
    /**
    Поле класса не является абстракцией.
     */
    private HashMap<Integer, String> cars = new HashMap<>();
    private static final int PRICE1 = 5;
    private static final int PRICE2 = 4;

    /**
     * аргумент метода не является абстракцией
     * возвращаемое значение не является абстракцией
     */
    public int getTimeWash(int money) {
        if (money <= 0) {
            return 0;
        }
        if (money < 20) {
            return money / PRICE1;
        }
        return money / PRICE2;
    }
}
