package ru.job4j.ood.isp.menu;

public class ActionSOP implements ActionDelegate {

    @Override
    public void delegate() {
        System.out.println("Действие - что-то происходит...");
    }
}
