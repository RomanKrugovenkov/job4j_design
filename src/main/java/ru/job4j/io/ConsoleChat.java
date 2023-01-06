package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("src\\main\\logChat.txt", "src\\main\\botAnswers.txt");
        cc.run();
    }

    public void run() {
        List<String> answers = readPhrases();
        List<String> stringsLog = new ArrayList<>();
        int strNum;
        Scanner in = new Scanner(System.in);
        menu();
        String strIn;
        String strOut;
        boolean run = true;
        while (run) {
            strIn = in.nextLine();
            strOut = switch (strIn) {
                case STOP:
                    yield "Чат остановлен.";
                case CONTINUE:
                    yield "Чат активен...";
                case OUT:
                    run = false;
                    yield "Чат завершен.";
                default:
                    strNum = new Random().nextInt(answers.size());
                    yield answers.get(strNum);
            };
            System.out.println(strOut);
            stringsLog.add(strIn);
            stringsLog.add(strOut);
        }
        saveLog(stringsLog);
    }

    public void menu() {
        System.out.println("Добро пожаловать в консольный чат!");
        System.out.println("Вы можете использовать команды стоп/продолжить/закончить");
        System.out.println("Чат активен...");
    }

    private List<String> readPhrases() {
        List<String> strings = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers))) {
            in.lines().forEach(strings::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strings;
    }

    private void saveLog(List<String> log) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path))) {
            for (String s : log) {
                out.write(s);
                out.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
