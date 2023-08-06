package ru.job4j.template;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Disabled("Тесты отключены. Удалить аннотацию после реализации всех методов по заданию.")
class StringGenTest {

    @Test
    public void whenTemplateAndMapAreCorrect() {
        String template = "I am a ${name}, Who are ${subject}?";
        String name = "name";
        String subject = "subject";
        Map<String, String> map = new HashMap<>();
        map.put(name, name);
        map.put(subject, subject);
        Generator generator = new StringGen();
        String rsl = generator.produce(template, map);
        assertThat(rsl).isEqualTo("I am a name, Who are subject?");
    }

    @Test
    public void whenMapNotContainKey() {
        String template = "I am a ${surname}, Who are ${subject}?";
        String name = "name";
        String subject = "subject";
        Map<String, String> map = new HashMap<>();
        map.put(name, name);
        map.put(subject, subject);
        Generator generator = new StringGen();
        String rsl = generator.produce(template, map);
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the Map does not contain a key");
    }

    @Test
    public void whenMapContainIncorrectKey() {
        String template = "I am a ${surname}, Who are ${subject}?";
        String name = "name";
        String surname = "surname";
        String subject = "subject";
        Map<String, String> map = new HashMap<>();
        map.put(name, name);
        map.put(surname, surname);
        map.put(subject, subject);
        Generator generator = new StringGen();
        String rsl = generator.produce(template, map);
        assertThatThrownBy(() -> generator.produce(template, map))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the Map contains incorrect key");
    }
}