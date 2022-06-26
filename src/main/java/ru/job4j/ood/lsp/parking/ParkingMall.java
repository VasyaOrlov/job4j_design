package ru.job4j.ood.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingMall implements Parking {
    private final List<Car> cars;
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
        boolean rsl = freePlace(car);
        if (rsl) {
            if (car.getSize() == 1) {
                countPass++;
                cars.add(car);
            } else if (car.getSize() > 1) {
                countTruck++;
                cars.add(car);
            }
        }
        return rsl;
    }

    @Override
    public void remove(Car car) {
        if (cars.contains(car)) {
            cars.remove(car);
            if (car.getSize() == 1) {
                countPass--;
            } else if (car.getSize() > 1) {
                countTruck--;
            }
        }
    }

    @Override
    public boolean freePlace(Car car) {
        boolean rsl = false;
        int delta = 0;
        int freeTruck = truckPlace - countTruck;
        if (freeTruck < 0) {
            delta = countTruck - truckPlace;
        }
        int freePass = passPlace - countPass - delta;
        if (car.getSize() == 1 && (freePass) > 0) {
            rsl = true;
        } else if (car.getSize() > 1
                && (freeTruck > 0 || (freePass) >= car.getSize())) {
            rsl = true;
        }
        return rsl;
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
