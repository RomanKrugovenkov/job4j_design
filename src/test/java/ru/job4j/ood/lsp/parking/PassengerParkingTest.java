package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class PassengerParkingTest {

    @Test
    void distributePassengerCars() {
        Car car1 = new Car(1, "car1");
        Car car2 = new Car(3, "car2");
        Car car3 = new Car(2, "car3");
        Car car4 = new Car(1, "car4");
        var carList = List.of(car1, car2, car3, car4);
        AbstrParking passParking = new PassengerParking(4);
        for (Car car : carList) {
            passParking.addParking(car);
        }
        var arrayCars = new Car[]{car1, car4, null, null};
        assertThat(passParking.getPlaces()).isEqualTo(arrayCars);
    }
}