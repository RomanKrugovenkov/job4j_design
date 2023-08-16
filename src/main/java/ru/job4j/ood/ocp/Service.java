package ru.job4j.ood.ocp;

public class Service {

    private String cafeteriaAddress;

    public Service(String cafeteriaAddress) {
        this.cafeteriaAddress = cafeteriaAddress;
    }

    public boolean coffee() {
        return true;
    }
}
