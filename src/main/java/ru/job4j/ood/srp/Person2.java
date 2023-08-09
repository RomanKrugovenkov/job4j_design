package ru.job4j.ood.srp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Person2 {
    private int age;
    private String name;

    public Person2(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void writeToFile(Person2 person2) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("path"))) {
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
