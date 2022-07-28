package ru.job4j.ood.lsp.parking;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ParkingMallTest {

    @Test
    public void whenPassCarParking() {
        ParkingMall pk = new ParkingMall(1, 0);
        Car passCar = new PassengerCar();
        pk.park(passCar);
        assertThat(pk.getCars().iterator().next(), is(passCar));
    }

    @Test
    public void whenTruckCarParking() {
        ParkingMall pk = new ParkingMall(0, 1);
        Car truckCar = new TruckCar(2);
        pk.park(truckCar);
        assertThat(pk.getCars().iterator().next(), is(truckCar));
    }

    @Test
    public void whenPassCarWantParkingAndNoPlace() {
        ParkingMall pk = new ParkingMall(0, 1);
        Car passCar = new PassengerCar();
        assertFalse(pk.park(passCar));
    }

    @Test
    public void whenTruckCarWantParkingAndNoPlace() {
        ParkingMall pk = new ParkingMall(1, 0);
        Car truckCar = new TruckCar(2);
        assertFalse(pk.park(truckCar));
    }

    @Test
    public void whenTruckCarWantParkingAndNoPlaceButFreePassPlace() {
        ParkingMall pk = new ParkingMall(2, 0);
        Car truckCar = new TruckCar(2);
        pk.park(truckCar);
        assertThat(pk.getCars().iterator().next(), is(truckCar));
    }

    @Test
    public void whenRemovePassCar() {
        ParkingMall pk = new ParkingMall(2, 0);
        Car passCar1 = new PassengerCar();
        Car passCar2 = new PassengerCar();
        pk.park(passCar1);
        pk.park(passCar2);
        pk.remove(passCar1);
        assertThat(pk.getCountPass(), is(1));
    }

    @Test
    public void whenRemoveTruckCar() {
        ParkingMall pk = new ParkingMall(0, 2);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        pk.park(truckCar1);
        pk.park(truckCar2);
        pk.remove(truckCar1);
        assertThat(pk.getCountTruck(), is(1));
    }

    @Test
    public void whenRemoveTruckCarWithPassPlace() {
        ParkingMall pk = new ParkingMall(4, 0);
        Car truckCar1 = new TruckCar(2);
        Car truckCar2 = new TruckCar(2);
        pk.park(truckCar1);
        pk.park(truckCar2);
        pk.remove(truckCar1);
        assertThat(pk.getCountTruck(), is(1));
    }
}