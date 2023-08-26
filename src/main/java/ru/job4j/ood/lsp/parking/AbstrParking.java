package ru.job4j.ood.lsp.parking;

public abstract class AbstrParking implements Parking {
    public Car[] places;

    public AbstrParking(int numOfPlaces) {
        places = new Car[numOfPlaces];
    }

    public Car[] getPlaces() {
        return places;
    }

    public abstract boolean addParking(Car car);
}
