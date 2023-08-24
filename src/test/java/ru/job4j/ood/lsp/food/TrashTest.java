package ru.job4j.ood.lsp.food;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrashTest {

    @Test
    void distributeFoodsToTrash() {
        Food apple = new Food("apple", LocalDate.of(2023, 8, 20), LocalDate.of(2023, 8, 1), 50, 20);
        Food meat = new Food("meat", LocalDate.of(2023, 8, 26), LocalDate.of(2023, 8, 1), 100, 30);
        Food bread = new Food("bread", LocalDate.of(2023, 9, 5), LocalDate.of(2023, 8, 15), 150, 15);
        Food cheese = new Food("cheese", LocalDate.of(2023, 10, 25), LocalDate.of(2023, 8, 15), 200, 10);
        var foodList = List.of(apple, meat, bread, cheese);
        AbstractStore trash = new Trash();
        for (Food food : foodList) {
            trash.addStore(food);
        }
        var listTrash = new ArrayList<>(List.of(apple));
        assertThat(trash.getStore()).isEqualTo(listTrash);
    }
}