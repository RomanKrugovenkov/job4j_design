package ru.job4j.ood.lsp.food;

import java.time.LocalDate;
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

    public static void main(String[] args) {
        List<AbstractStore> storeList = List.of(
                new Warehouse(),
                new Trash(),
                new Shop()
        );
        List<Food> foodList = List.of(
                new Food("apple", LocalDate.of(2023, 8, 20), LocalDate.of(2023, 8, 1), 50, 20),
                new Food("meat", LocalDate.of(2023, 8, 26), LocalDate.of(2023, 8, 1), 100, 30),
                new Food("bread", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 8, 15), 150, 15),
                new Food("cheese", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 8, 15), 200, 10)
        );
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distributeList(foodList, storeList);
        AbstractStore wareHouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        System.out.println("wareHouse: " + wareHouse.getStore());
        System.out.println("shop: " + shop.getStore());
        System.out.println("trash: " + trash.getStore());
    }
}
