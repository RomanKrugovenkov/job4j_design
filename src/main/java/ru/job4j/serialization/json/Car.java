package ru.job4j.serialization.json;

public class Car {
    private final boolean credit;
    private final int speed;
    private final String name;
    private final Contact contact;
    private final String[] options;

    public Car(boolean credit, int speed, String name, Contact contact, String[] options) {
        this.credit = credit;
        this.speed = speed;
        this.name = name;
        this.contact = contact;
        this.options = options;
    }

    public static void main(String[] args) {
        Car car = new Car(
                true,
                200,
                "Nissan",
                new Contact("22-222"),
                new String[] {"mats", "tyres"});
    }
}
