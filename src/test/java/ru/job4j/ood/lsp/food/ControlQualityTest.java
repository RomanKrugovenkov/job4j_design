package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ControlQualityTest {

    @Test
    void distributeListOfFoods() {
        Food apple = new Food("apple", LocalDate.of(2023, 10, 4), LocalDate.of(2023, 9, 1), 50, 20);
        Food meat = new Food("meat", LocalDate.of(2023, 11, 7), LocalDate.of(2023, 7, 3), 100, 30);
        Food bread = new Food("bread", LocalDate.of(2023, 12, 15), LocalDate.of(2023, 8, 5), 150, 15);
        Food cheese = new Food("cheese", LocalDate.of(2024, 10, 25), LocalDate.of(2023, 10, 1), 200, 10);
        var foodList = List.of(apple, meat, bread, cheese);
        AbstractStore wareHouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<AbstractStore> storeList = List.of(
                wareHouse,
                shop,
                trash
        );
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distributeList(foodList, storeList);
        var listWare = new ArrayList<>(List.of(cheese));
        var listTrash = new ArrayList<>(List.of(apple));
        var listShop = new ArrayList<>(List.of(meat, bread));
        assertThat(wareHouse.getStore()).isEqualTo(listWare);
        assertThat(shop.getStore()).isEqualTo(listShop);
        assertThat(trash.getStore()).isEqualTo(listTrash);
    }

    @Test
    void resortListOfStore() {
        Food apple = new Food("apple", LocalDate.of(2023, 10, 4), LocalDate.of(2023, 9, 1), 50, 20);
        Food meat = new Food("meat", LocalDate.of(2023, 11, 7), LocalDate.of(2023, 7, 3), 100, 30);
        Food bread = new Food("bread", LocalDate.of(2023, 12, 15), LocalDate.of(2023, 8, 5), 150, 15);
        Food cheese = new Food("cheese", LocalDate.of(2024, 10, 25), LocalDate.of(2023, 10, 1), 200, 10);
        var foodList = List.of(apple, meat, bread, cheese);
        AbstractStore wareHouse = new Warehouse();
        AbstractStore shop = new Shop();
        AbstractStore trash = new Trash();
        List<AbstractStore> storeList = List.of(
                wareHouse,
                shop,
                trash
        );
        ControlQuality controlQuality = new ControlQuality();
        controlQuality.distributeList(foodList, storeList);
        controlQuality.resort(storeList);
        var listWare = new ArrayList<>(List.of(cheese));
        var listTrash = new ArrayList<>(List.of(apple));
        var listShop = new ArrayList<>(List.of(meat, bread));
        assertThat(wareHouse.getStore()).isEqualTo(listWare);
        assertThat(shop.getStore()).isEqualTo(listShop);
        assertThat(trash.getStore()).isEqualTo(listTrash);
    }
}