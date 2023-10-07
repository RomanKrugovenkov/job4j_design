package ru.job4j.ood.dip.examples;

import java.util.HashMap;

public class EquipmentRental {

    HashMap<String, String> equipStore;

    public boolean addStore(String sportsmanName, String sportEquipment) {
        return equipStore.put(sportsmanName, sportEquipment) != null;
    }
}
