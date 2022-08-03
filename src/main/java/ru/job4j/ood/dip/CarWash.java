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
    private final Second time = new Second();
    private static final int PRICE1 = 5;
    private static final int PRICE2 = 4;
    private static final int AMOUNT1 = 0;
    private static final int AMOUNT2 = 20;

    /**
     * аргумент метода не является абстракцией
     * возвращаемое значение не является абстракцией
     */
    public Second getTimeWash(Rub money) {
        int cash = money.getMoney();
        if (cash <= AMOUNT1) {
            time.setTime(0);
            return time;
        }
        if (cash < AMOUNT2) {
            time.setTime(cash / PRICE1);
            return time;
        }
        time.setTime(cash / PRICE2);
        return time;
    }
}
