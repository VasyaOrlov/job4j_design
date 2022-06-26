package ru.job4j.ood.lsp.parking;


public interface Parking {
    boolean park(Car car);
    boolean remove(Car car);
    boolean freePlace(Car car);
}