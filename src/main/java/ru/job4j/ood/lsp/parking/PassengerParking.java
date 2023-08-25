package ru.job4j.ood.lsp.parking;

public class PassengerParking extends AbstrParking {

    public PassengerParking(int numOfPlaces) {
        super(numOfPlaces);
    }

    @Override
    public boolean addParking(Car car) {
        return false;
    }

    @Override
    public Car[] getPlaces() {
        return places;
    }
}
