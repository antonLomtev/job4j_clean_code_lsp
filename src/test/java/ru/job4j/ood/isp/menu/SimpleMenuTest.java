package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
        Printer printer = new Printer();
        printer.print(menu);
    }

    @Test
    public void whenSelectThenReturnSame() {
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
        assertThat(new Menu.MenuItemInfo("Выйти на улицу",
                List.of("Открыть дверь на выход", "Закрыть дверь на ключ", "Мы на улице"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Выйти на улицу").get());
        assertThat(new Menu.MenuItemInfo("Мы на улице",
                List.of("Идем гулять"), STUB_ACTION, "1.3.")).
                isEqualTo(menu.select("Мы на улице").get());
        assertThat(new Menu.MenuItemInfo("Вернуться домой",
                List.of("Подойти к двери", "Открыть дверь ключом", "Открыть дверь", "Welcome to home", "Закрыть двери на все засовы"), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Вернуться домой").get());
    }
}