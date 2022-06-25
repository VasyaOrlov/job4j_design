package ru.job4j.ood.lsp.parking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ParkingMallTest {

    @Test
    public void whenPassCarParking() {
        ParkingMall pk = new ParkingMall(1, 0);
        Car passCar = new PassengerCar();
        pk.parking(passCar);
        assertThat(pk.getCars().get(0), is(passCar));
    }

    @Test
    public void whenTruckCarParking() {
        ParkingMall pk = new ParkingMall(0, 1);
        Car truckCar = new TruckCar(2);
        pk.parking(truckCar);
        assertThat(pk.getCars().get(0), is(truckCar));
    }

    @Test
    public void whenPassCarWantParkingAndNoPlace() {
        ParkingMall pk = new ParkingMall(0, 1);
        Car passCar = new PassengerCar();
        assertFalse(pk.parking(passCar));
    }

    @Test
    public void whenTruckCarWantParkingAndNoPlace() {
        ParkingMall pk = new ParkingMall(1, 0);
        Car truckCar = new TruckCar(2);
        assertFalse(pk.parking(truckCar));
    }

    @Test
    public void whenTruckCarWantParkingAndNoPlaceButFreePassPlace() {
        ParkingMall pk = new ParkingMall(2, 0);
        Car truckCar = new TruckCar(2);
        pk.parking(truckCar);
        assertThat(pk.getCars().get(0), is(truckCar));
    }

    @Test
    public void whenRemovePassCar() {
        ParkingMall pk = new ParkingMall(2, 0);
        Car passCar = new PassengerCar();
        pk.parking(passCar);
        pk.parking(passCar);
        pk.remove(passCar);
        assertThat(pk.getCountPass(), is(1));
    }

    @Test
    public void whenRemoveTruckCar() {
        ParkingMall pk = new ParkingMall(0, 2);
        Car truckCar = new TruckCar(2);
        pk.parking(truckCar);
        pk.parking(truckCar);
        pk.remove(truckCar);
        assertThat(pk.getCountTruck(), is(1));
    }

    @Test
    public void whenRemoveTruckCarWithPassPlace() {
        ParkingMall pk = new ParkingMall(4, 0);
        Car truckCar = new TruckCar(2);
        pk.parking(truckCar);
        pk.parking(truckCar);
        pk.remove(truckCar);
        assertThat(pk.getCountTruck(), is(1));
    }
}