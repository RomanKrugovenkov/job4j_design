package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    private static List<Food> store = new ArrayList<>();

    @Override
    public boolean addStore(Food food) {
        boolean rsl = false;
        if (periodRatio(food) >= 0.75 && periodRatio(food) < 1) {
            food.setPrice(food.getPrice() - food.getDiscount() * food.getPrice() / 100);
            store.add(food);
            rsl = true;
        } else if (periodRatio(food) >= 0.25 && periodRatio(food) < 0.75) {
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
