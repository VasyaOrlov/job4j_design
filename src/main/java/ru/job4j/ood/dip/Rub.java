package ru.job4j.ood.dip;

public class Rub implements Money {
    private final static int RUB = 60;
    private final int value;

    public Rub(int value) {
        this.value = value;
    }

    @Override
    public int getMoney() {
        return value / RUB;
    }
}
