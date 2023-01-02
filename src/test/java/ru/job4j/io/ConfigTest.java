package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithComment() {
        String path = "./data/pairWithNames.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Ivan")).isEqualTo("Ivanov");
    }

    @Test
    void whenPairWithEquals1() {
        String path = "./data/pairWithNamesEquals1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Petr")).isEqualTo("Petrov=1");
    }

    @Test
    void whenPairWithException() {
        String path = "./data/pairWithException.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load)
                .isInstanceOf(IllegalArgumentException.class);
    }
}