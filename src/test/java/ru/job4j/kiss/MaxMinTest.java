package ru.job4j.kiss;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MaxMinTest {

    private Comparator comparator;
    private List<Integer> value;

    @BeforeEach
    public void initData() {
        comparator = (o1, o2) -> {
            int i1 = (int) o1;
            int i2 = (int) o2;
            return i1 - i2;
        };
        value = List.of(5, 5, 3, 1, 7, 2, 4);
    }

    @Test
    void maxOfList() {
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.max(value, comparator);
        assertThat(rsl).isEqualTo(7);
    }

    @Test
    void minOfList() {
        MaxMin maxMin = new MaxMin();
        int rsl = maxMin.min(value, comparator);
        assertThat(rsl).isEqualTo(1);
    }

}