package ru.job4j.assertj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleConvertTest {

    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkToList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var coll = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(coll).hasSize(5)
                .contains("second")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("zero", "three", "six");
        assertThat(coll).first().isEqualTo("first");
        assertThat(coll).element(0).isNotNull();
        assertThat(coll).filteredOn(e -> e.length() > 4).size().isEqualTo(3);
    }

    @Test
    void checkToSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var coll = simpleConvert.toSet("first", "first", "three", "four", "four");
        assertThat(coll).hasSize(3)
                .contains("three")
                .containsExactlyInAnyOrder("first", "three", "four")
                .doesNotContain("five");
    }

    @Test
    void checkToMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        var coll = simpleConvert.toMap("first", "first", "three", "four", "four");
        assertThat(coll).hasSize(3)
                .containsValues(0, 2, 3)
                .doesNotContainKey("second")
                .containsEntry("four", 3);
    }
}