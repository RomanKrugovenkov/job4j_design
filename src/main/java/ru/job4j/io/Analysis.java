package ru.job4j.io;

import java.io.*;

public class Analysis {

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("./data/server.log", "./data/target.csv");
    }

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            String line;
            String offTime = null;
            while ((line = in.readLine()) != null) {
                var strArray = line.split(" ", 2);
                if (offTime == null && ("400".equals(strArray[0]) || "500".equals(strArray[0]))) {
                    offTime = strArray[1];
                } else if (offTime != null && "200".equals(strArray[0]) || "300".equals(strArray[0])) {
                    out.append(offTime);
                    out.append(";");
                    out.append(strArray[1]);
                    out.append(";");
                    out.newLine();
                    offTime = null;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
