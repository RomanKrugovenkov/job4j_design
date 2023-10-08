package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DistributorCarTest {

    @Test
    void distributeAllCars() {
        Car car1 = new Car(1, "car1");
        Car car2 = new Car(3, "car2");
        Car car3 = new Car(2, "car3");
        Car car4 = new Car(2, "car4");
        Car car5 = new Car(1, "car5");
        var carList = List.of(car1, car2, car3, car4, car5);
        AbstrParking passParking = new PassengerParking(4);
        AbstrParking cargoParking = new CargoParking(2);
        var parkingList = List.of(cargoParking, passParking);
        DistributorCar distributorCar = new DistributorCar();
        distributorCar.distList(carList, parkingList);
        var arrayPassCars = new Car[]{car1, car4, car4, car5};
        var arrayCargoCars = new Car[]{car2, car3};
        assertThat(passParking.getPlaces()).isEqualTo(arrayPassCars);
        assertThat(cargoParking.getPlaces()).isEqualTo(arrayCargoCars);
    }
}