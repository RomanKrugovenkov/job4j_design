package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    private static List<Food> store = new ArrayList<>();

    @Override
    public boolean addStore(Food food) {
        boolean rsl = false;
        if (periodRatio(food) >= 1) {
            store.add(food);
            rsl = true;
        }
        return rsl;
    }

    @Override
    public List<Food> getStore() {
        return store;
    }
}
