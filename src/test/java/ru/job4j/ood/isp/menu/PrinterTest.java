package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.*;
class PrinterTest {
    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenDisplayAllToDo() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Выйти на улицу", STUB_ACTION);
        menu.add(Menu.ROOT, "Вернуться домой", STUB_ACTION);
        menu.add("Выйти на улицу", "Открыть дверь на выход", STUB_ACTION);
        menu.add("Выйти на улицу", "Закрыть дверь на ключ", STUB_ACTION);
        menu.add("Выйти на улицу", "Мы на улице", STUB_ACTION);
        menu.add("Мы на улице", "Идем гулять", STUB_ACTION);
        menu.add("Вернуться домой", "Подойти к двери", STUB_ACTION);
        menu.add("Вернуться домой", "Открыть дверь ключом", STUB_ACTION);
        menu.add("Вернуться домой", "Открыть дверь", STUB_ACTION);
        menu.add("Вернуться домой", "Welcome to home", STUB_ACTION);
        menu.add("Вернуться домой", "Закрыть двери на все засовы", STUB_ACTION);
        String expected = "Выйти на улицу 1." + System.lineSeparator()
                + "---Открыть дверь на выход 1.1." + System.lineSeparator()
                + "---Закрыть дверь на ключ 1.2." + System.lineSeparator()
                + "---Мы на улице 1.3." + System.lineSeparator()
                + "------Идем гулять 1.3.1." + System.lineSeparator()
                + "Вернуться домой 2." + System.lineSeparator()
                + "---Подойти к двери 2.1." + System.lineSeparator()
                + "---Открыть дверь ключом 2.2." + System.lineSeparator()
                + "---Открыть дверь 2.3." + System.lineSeparator()
                + "---Welcome to home 2.4." + System.lineSeparator()
                + "---Закрыть двери на все засовы 2.5." + System.lineSeparator();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        MenuPrinter printer = new Printer();
        printer.print(menu);
        assertThat(expected).isEqualTo(out.toString());
    }
}