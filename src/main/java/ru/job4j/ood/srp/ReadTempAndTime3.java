package ru.job4j.ood.srp;

import java.io.*;

public class ReadTempAndTime3 {

    public void readTemperture() {
        try (BufferedReader in = new BufferedReader(new FileReader("path"))) {
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readDateTime() {
        try (BufferedReader in = new BufferedReader(new FileReader("path"))) {
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
