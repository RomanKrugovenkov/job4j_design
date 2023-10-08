package ru.job4j.ood.lsp.food;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {

    public void distribute(Food food, List<AbstractStore> storeList) {
        for (AbstractStore as : storeList) {
            if (as.addStore(food)) {
                break;
            }
        }
    }

    public void distributeList(List<Food> foodList, List<AbstractStore> storeList) {
        for (Food food : foodList) {
            distribute(food, storeList);
        }
    }

    public void resort(List<AbstractStore> storeList) {
        List<Food> extractedFood = new ArrayList<>();
        for (AbstractStore store : storeList) {
            extractedFood.addAll(store.extractAll());
        }
        distributeList(extractedFood, storeList);
    }
}
