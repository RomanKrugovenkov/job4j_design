package ru.job4j.ood.lsp.parking;

import java.util.List;

public class CargoParking extends AbstrParking {

    public CargoParking(int numOfPlaces) {
        super(numOfPlaces);
    }

    @Override
    public boolean addParking(Car car) {
        boolean rsl = false;
        if (car.getSizeCar() > 1) {
            for (int i = 0; i < places.length; i++) {
                if (places[i] == null) {
                    places[i] = car;
                    return true;
                }
            }
        }
        return rsl;
    }

    public static void main(String[] args) {
        Car car1 = new Car(1, "car1");
        Car car2 = new Car(3, "car2");
        Car car3 = new Car(2, "car3");
        Car car4 = new Car(2, "car4");
        Car car5 = new Car(1, "car5");
        var carList = List.of(car1, car2, car3, car4, car5);
        AbstrParking passParking = new PassengerParking(4);
        AbstrParking cargoParking = new CargoParking(2);
    }
}
