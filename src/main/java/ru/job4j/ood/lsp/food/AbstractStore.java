package ru.job4j.ood.lsp.food;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.pbkdf2.Arrays;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    protected List<Food> store = new ArrayList<>();

    public List<Food> getStore() {
        return store;
    }

    public static double periodRatio(Food food) {
        var periodUse = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        var periodExpiry = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return (double) periodUse / periodExpiry;
    }

    public List<Food> extractAll() {
        List<Food> rsl = new ArrayList<>(store);
        store.clear();
        return rsl;
    }

    public abstract boolean addStore(Food food);
}
