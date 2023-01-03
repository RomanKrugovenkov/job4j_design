package ru.job4j.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexExample {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("Я учусь на Job4j");

        String text1 = "Я учусь на Job4j";
        Matcher matcher1 = pattern.matcher(text1);
        boolean isPresent1 = matcher1.matches();
        System.out.println(isPresent1);

        String text2 = "Я учусь на курсе Job4j";
        Matcher matcher2 = pattern.matcher(text2);
        boolean isPresent2 = matcher2.matches();
        System.out.println(isPresent2);

        Pattern pattern2 = Pattern.compile("Job4j", Pattern.CASE_INSENSITIVE);
        String text3 = "jOB4J";
        Matcher matcher3 = pattern2.matcher(text3);
        boolean isPresent3 = matcher3.matches();
        System.out.println(isPresent3);

        String text4 = "Я учусь на курсе Job4j";
        Matcher matcher4 = pattern2.matcher(text4);
        boolean isPresent4 = matcher4.find();
        System.out.println(isPresent4);

        Pattern pattern3 = Pattern.compile("123");
        String text5 = "1231 и 1232 и 1233";
        Matcher matcher5 = pattern3.matcher(text5);
        String rsl = matcher5.replaceAll("Job4j");
        System.out.println(rsl);
    }
}
