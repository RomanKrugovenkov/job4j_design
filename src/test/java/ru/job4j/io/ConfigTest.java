package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/pairWithNames.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("Ivan")).isEqualTo("Ivanov");
        assertThat(config.value("Roman")).isEqualTo("Romanov=1");
    }
}