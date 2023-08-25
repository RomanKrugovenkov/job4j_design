package ru.job4j.ood.lsp.food;

public class Trash extends AbstractStore {

    @Override
    public boolean addStore(Food food) {
        boolean rsl = false;
        if (periodRatio(food) >= 1) {
            store.add(food);
            rsl = true;
        }
        return rsl;
    }
}
