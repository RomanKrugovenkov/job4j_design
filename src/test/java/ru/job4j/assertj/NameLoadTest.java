package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseWhenEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("is empty");
    }

    @Test
    void checkValidateWhenNoEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Roman_Krug"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("symbol \"=\"", names[0]);
    }

    @Test
    void checkValidateWhenStartEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"=Krug"};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("contain a key", names[0]);
    }

    @Test
    void checkValidateWhenFinishEquals() {
        NameLoad nameLoad = new NameLoad();
        String[] names = {"Roman="};
        assertThatThrownBy(() -> nameLoad.parse(names))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("contain a value", names[0]);
    }
}