package ru.job4j.ood.lsp.parking;

import java.util.HashSet;
import java.util.Set;

public class ParkingMall implements Parking {
    private final Set<Car> pCars;
    private final Set<Car> truck;
    private final int passPlace;
    private final int truckPlace;
    private int countPass = 0;
    private int countTruck = 0;

    public ParkingMall(int passPlace, int truckPlace) {
        this.passPlace = passPlace;
        this.truckPlace = truckPlace;
        this.pCars = new HashSet<>(passPlace);
        this.truck = new HashSet<>(truckPlace);
    }

    @Override
    public boolean park(Car car) {
        boolean rsl = freePlace(car);
        if (rsl) {
            if (car.getSize() == PassengerCar.SIZE) {
                countPass++;
                pCars.add(car);
            } else if (car.getSize() > PassengerCar.SIZE) {
                countTruck++;
                truck.add(car);
            }
        }
        return rsl;
    }

    @Override
    public boolean remove(Car car) {
        boolean rsl = false;
        if (car.getSize() == PassengerCar.SIZE && pCars.contains(car)) {
            pCars.remove(car);
            countPass--;
            rsl = true;
        } else if (car.getSize() > PassengerCar.SIZE && truck.contains(car)) {
            truck.remove(car);
            countTruck--;
            rsl = true;
        }
        return rsl;
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
        if (car.getSize() == PassengerCar.SIZE && (freePass) > 0) {
            rsl = true;
        } else if (car.getSize() > PassengerCar.SIZE
                && (freeTruck > 0 || (freePass) >= car.getSize())) {
            rsl = true;
        }
        return rsl;
    }

    public Set<Car> getCars() {
        Set<Car> rsl = new HashSet<>(pCars);
        rsl.addAll(truck);
        return rsl;
    }

    public int getCountPass() {
        return countPass;
    }

    public int getCountTruck() {
        return countTruck;
    }
}
