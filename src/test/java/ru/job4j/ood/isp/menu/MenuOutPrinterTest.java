package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class MenuOutPrinterTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void whenMenuOutPrinterWorking() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter menuPrinter = new MenuOutPrinter();
        menuPrinter.print(menu);
        String expected = "1.Сходить в магазин"
                .concat(System.lineSeparator())
                .concat("  1.1.Купить продукты")
                .concat(System.lineSeparator())
                .concat("    1.1.1.Купить хлеб")
                .concat(System.lineSeparator())
                .concat("    1.1.2.Купить молоко")
                .concat(System.lineSeparator())
                .concat("2.Покормить собаку");
        assertThat(expected).isEqualTo(outputStreamCaptor.toString().trim());
    }
}