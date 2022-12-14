package ru.job4j.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleSetTest {

    @Test
    void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.contains(1)).isTrue();
        assertThat(set.add(1)).isFalse();
    }

    @Test
    void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(null)).isTrue();
        assertThat(set.contains(null)).isTrue();
        assertThat(set.add(null)).isFalse();
    }

    @Test
    void whenSize3() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        assertThat(set.add(2)).isTrue();
        assertThat(set.add(3)).isTrue();
        assertThat(set).hasSize(3);
    }

    @Test
    void whenIteratorHasNext() {
        Set<Integer> set = new SimpleSet<>();
        assertThat(set.add(1)).isTrue();
        var it = set.iterator();
        assertThat(it.hasNext()).isTrue();
        assertThat(it.next()).isEqualTo(1);
        assertThat(it.hasNext()).isFalse();
    }
}