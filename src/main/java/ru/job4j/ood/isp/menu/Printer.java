package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        String dash = "---";
        for (Menu.MenuItemInfo item : menu) {
            String[] repeat = item.getNumber().split("\\.");
            System.out.printf("%s%s %s%n", dash.repeat(repeat.length - 1), item.getName(), item.getNumber());
        }
    }
}
