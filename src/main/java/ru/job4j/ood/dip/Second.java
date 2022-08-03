package ru.job4j.ood.dip;

public class Second implements Time {
    private final static int SECOND = 60;
    private int time;

    @Override
    public int getTime() {
        return time * SECOND;
    }

    @Override
    public void setTime(int value) {
        this.time = value;
    }
}
