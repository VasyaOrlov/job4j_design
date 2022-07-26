package ru.job4j.ood.lsp.parking;

public class TruckCar implements Car {
    private final int size;

    public TruckCar(int size) {
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("размер грузовика должен быть не меньше 2");
        }
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }
}
