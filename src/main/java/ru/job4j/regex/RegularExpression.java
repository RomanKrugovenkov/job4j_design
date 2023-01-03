package ru.job4j.regex;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("11");
        String text = "111111";
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println("Найдено совпадение: " + matcher.group());
        }

        System.out.println("Разделяем по нецифровым символам:");
        String str = "123+=-456:/789";
        String[] rsl = str.split("\\D+");
        System.out.println(Arrays.toString(rsl));

        System.out.println("Поиск формата даты:");
        Pattern pattern2 = Pattern.compile("\\b\\d{2}\\.\\d{2}\\.\\d{4}\\b");
        String text2 = "24.04.1987 11.11.111111 99.99.99991 99.99.9999 99999999 0000.00.00";
        Matcher matcher2 = pattern2.matcher(text2);
        while (matcher2.find()) {
            System.out.println("Найдено совпадение: " + matcher2.group());
        }

        System.out.println("Поиск формата эл.почты:");
        Pattern pattern3 = Pattern.compile("\\S{1,}@\\S{1,}\\.\\S{1,}");
        String text3 = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
        Matcher matcher3 = pattern3.matcher(text3);
        while (matcher3.find()) {
            System.out.println("Найдено совпадение: " + matcher3.group());
        }
    }
}
