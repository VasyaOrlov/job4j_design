package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingMall implements Parking {
    private List<Car> cars;
    private final int passPlace;
    private final int truckPlace;
    private int countPass = 0;
    private int countTruck = 0;

    public ParkingMall(int passPlace, int truckPlace) {
        this.passPlace = passPlace;
        this.truckPlace = truckPlace;
        this.cars = new ArrayList<>(passPlace + truckPlace);
    }

    @Override
    public boolean parking(Car car) {
        return true;
    }

    @Override
    public void remove(Car car) {

    }

    @Override
    public boolean freePlace(Car car) {
        return true;
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    public int getCountPass() {
        return countPass;
    }

    public int getCountTruck() {
        return countTruck;
    }
}
