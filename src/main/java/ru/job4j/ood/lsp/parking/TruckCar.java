package ru.job4j.ood.lsp.parking;

public class TruckCar implements Car {
    private final int size;

    public TruckCar(int size) {
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
