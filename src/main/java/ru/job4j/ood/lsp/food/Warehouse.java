package ru.job4j.ood.lsp.food;

public class Warehouse extends AbstractStore {

    @Override
    public boolean addStore(Food food) {
        boolean rsl = false;
        if (periodRatio(food) < 0.25) {
            store.add(food);
            rsl = true;
        }
        return rsl;
    }
}
