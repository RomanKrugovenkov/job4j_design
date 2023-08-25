package ru.job4j.ood.lsp.parking;


public interface Parking {

    boolean addParking(Car car);

    Car[] getPlaces();
}
