package ru.job4j.ood.lsp.parking;


public interface Parking {
    boolean parking(Car car);
    void remove(Car car);
    boolean freePlace(Car car);
}