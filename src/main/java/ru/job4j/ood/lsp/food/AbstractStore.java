package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public abstract class AbstractStore implements Store {

    public static double periodRatio(Food food) {
        var periodUse = ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now());
        var periodExpiry = ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate());
        return (double) periodUse / periodExpiry;
    }

    public abstract boolean addStore(Food food);

    public abstract List<Food> getStore();
}
