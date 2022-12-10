package ru.job4j.iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.Predicate;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIf() {
        ListUtils.addAfter(input, 0, 2);
        Predicate<Integer> filter = x -> x % 2 != 0;
        ListUtils.removeIf(input, filter);
        assertThat(input).hasSize(1).containsSequence(2);
    }

    @Test
    void whenReplaceIf() {
        ListUtils.addAfter(input, 0, 2);
        Predicate<Integer> filter = x -> x % 2 == 0;
        ListUtils.replaceIf(input, filter, 99);
        assertThat(input).hasSize(3).containsSequence(1, 99, 3);
    }

    @Test
    void whenRemoveAll() {
        ListUtils.addAfter(input, 0, 2);
        List<Integer> elements = Arrays.asList(1, 2);
        ListUtils.removeAll(input, elements);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}