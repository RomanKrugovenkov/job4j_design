package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
class ShopTest {

    @Test
    void distributeFoodsToShop() {
        Food apple = new Food("apple", LocalDate.of(2023, 8, 20), LocalDate.of(2023, 8, 1), 50, 20);
        Food meat = new Food("meat", LocalDate.of(2023, 8, 26), LocalDate.of(2023, 8, 1), 100, 30);
        Food bread = new Food("bread", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 8, 15), 150, 15);
        Food cheese = new Food("cheese", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 8, 15), 200, 10);
        var foodList = List.of(apple, meat, bread, cheese);
        AbstractStore shop = new Shop();
        for (Food food : foodList) {
            shop.addStore(food);
        }
        var listShop = new ArrayList<>(Arrays.asList(meat, bread));
        assertThat(shop.getStore()).isEqualTo(listShop);
    }
}