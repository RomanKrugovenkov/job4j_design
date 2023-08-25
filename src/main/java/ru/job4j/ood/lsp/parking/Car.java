package ru.job4j.ood.lsp.parking;

public class Car {
    private int sizeCar;
    public String model;

    public Car(int sizeCar, String model) {
        this.sizeCar = sizeCar;
        this.model = model;
    }

    public int getSizeCar() {
        return sizeCar;
    }
}
