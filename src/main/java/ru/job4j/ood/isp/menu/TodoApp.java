package ru.job4j.ood.isp.menu;

import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelete DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    public static final Integer ADD_TODO = 1;
    public static final Integer ADD_TODO_CHILD = 2;
    public static final Integer ACTION = 3;
    public static final Integer SHOW_MENU = 4;
    public static final String MENU = """
                Введите 1 для добавления задания в меню.
                Введите 2, для добавления подзадания в меню.
                Введите 3, для вызова действия меню.
                Введите 4, для вывода меню на экран.
                Введите любое другое число для выхода.
            """;
    public static final String TODO_NAME = "Введите название задания: ";
    public static final String ACTION_NAME = "Введите название пункта, действие которого хотите вызвать: ";
    public static final String CHILD_POINT_NAME = "Введите название задания, подпункт которого хотите внести: ";
    public static final String EXIT = "Конец работы";

    public static final ActionDelegate STUB_ACTION = () -> System.out.println("Some action");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Menu menu = new SimpleMenu();
        start(scanner, menu, STUB_ACTION);
    }

    private static void start(Scanner scanner, Menu menu, ActionDelegate actionDelegate) {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            int userChoice = Integer.parseInt(scanner.nextLine());
            System.out.println(userChoice);
            if (userChoice == ADD_TODO) {
                System.out.println(TODO_NAME);
                String toDoName = scanner.nextLine();
                menu.add(Menu.ROOT, toDoName, actionDelegate);
            } else if (userChoice == ADD_TODO_CHILD) {
                System.out.println(CHILD_POINT_NAME);
                String childName = scanner.nextLine();
                System.out.println(TODO_NAME);
                String childToDoName = scanner.nextLine();
                menu.add(childName, childToDoName, actionDelegate);
            } else if (userChoice == ACTION) {
                System.out.println(ACTION_NAME);
                String toDoName = scanner.nextLine();
                menu.select(toDoName).ifPresentOrElse(item -> item.getActionDelegate().delegate(),
                        () -> System.out.println("Такого задания нет в меню."));
            } else if (userChoice == SHOW_MENU) {
                MenuPrinter printer = new Printer();
                printer.print(menu);
            } else {
                System.out.println(EXIT);
                run = false;
            }
        }
    }
}
