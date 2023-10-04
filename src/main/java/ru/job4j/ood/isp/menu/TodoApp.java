package ru.job4j.ood.isp.menu;

import java.util.Scanner;

public class TodoApp {

    private void menu() {
        System.out.println("Добро пожаловать в программу создания меню");
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить элемент в корень меню");
        System.out.println("2. Добавить элемент к родительскому элементу");
        System.out.println("3. Вызвать действие, привязанное к пункту меню");
        System.out.println("4. Вывести меню в консоль");
        System.out.println("5. Завершить программу");
    }

    private void action() {
        Menu menu = new SimpleMenu();
        ActionDelegate actionSOP = new ActionSOP();
        Scanner in = new Scanner(System.in);
        boolean run = true;
        while (run) {
            System.out.println("Введите пункт меню...");
            switch (in.nextLine()) {
                case "1" -> {
                    System.out.println("Введите название корневого пункта меню...");
                    String menuName = in.nextLine();
                    menu.add(Menu.ROOT, menuName, actionSOP);
                }
                case "2" -> {
                    System.out.println("Введите название родительского элемента...");
                    String menuParentName = in.nextLine();
                    System.out.println("Введите название пункта меню...");
                    String menuChildName = in.nextLine();
                    menu.add(menuParentName, menuChildName, actionSOP);
                }
                case "3" -> {
                    System.out.println("Введите название пункта меню...");
                    String menuSelect = in.nextLine();
                    menu.select(menuSelect).get().getActionDelegate().delegate();
                }
                case "4" -> {
                    System.out.println("Меню:");
                    MenuPrinter menuPrinter = new MenuOutPrinter();
                    menuPrinter.print(menu);
                }
                case "5" -> {
                    System.out.println("Программа завершена.");
                    run = false;
                }
                default -> System.out.println("Неверно указан пункт меню");
            }
        }
    }

    public static void main(String[] args) {
        TodoApp tdApp = new TodoApp();
        tdApp.menu();
        tdApp.action();
    }
}
