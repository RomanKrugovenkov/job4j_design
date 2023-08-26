package ru.job4j.ood.lsp.parking;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class CargoParkingTest {

    @Test
    void distributeCargoCars() {
        Car car1 = new Car(1, "car1");
        Car car2 = new Car(3, "car2");
        Car car3 = new Car(2, "car3");
        Car car4 = new Car(1, "car4");
        var carList = List.of(car1, car2, car3, car4);
        AbstrParking cargoParking = new CargoParking(4);
        for (Car car : carList) {
            cargoParking.addParking(car);
        }
        var arrayCars = new Car[]{car2, car3, null, null};
        assertThat(cargoParking.getPlaces()).isEqualTo(arrayCars);
    }
}