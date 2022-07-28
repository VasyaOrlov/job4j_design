package ru.job4j.ood.lsp.parking;

import java.util.Objects;

public class TruckCar implements Car {
    private static int count = 1;
    private final int size;

    private final int number;

    public TruckCar(int size) {
        if (size <= PassengerCar.SIZE) {
            throw new IllegalArgumentException("размер грузовика должен быть не меньше 2");
        }
        this.size = size;
        this.number = count++;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TruckCar truckCar = (TruckCar) o;
        return size == truckCar.size && number == truckCar.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, number);
    }
}
