package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {

    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere")
                .startsWith("S")
                .doesNotContain("Cube")
                .isNotEmpty();
    }

    @Test
    void isArea43p3() {
        Box box = new Box(4, 5);
        double area = box.getArea();
        assertThat(area).isEqualTo(43.3, withPrecision(0.1d))
                .isGreaterThan(43.0d)
                .isLessThan(44.0d)
                .isNotZero();
    }

    @Test
    void isExistTrue() {
        Box box = new Box(8, 2);
        boolean rsl = box.isExist();
        assertThat(rsl).isTrue();
    }
}