package ru.job4j.ood.dip.examples;

import java.util.HashMap;

public class EquipmentRentalByDIP {

    Store equipStore;

    public boolean addStore(Sportsman sportsman, Equipment equipment) {
        return equipStore.addStore(sportsman, equipment);
    }

    public abstract class Sportsman {
        String name;
    }

    public abstract class Equipment {
        String equipment;
    }

    interface Store {

        boolean addStore(Sportsman sportsman, Equipment equipment);

        HashMap<Sportsman, Equipment> getStore();
    }

}
