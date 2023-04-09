package ru.job4j.jdbc;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {

    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines().forEach(str -> {
                        var temp = str.split(";");
                        if (temp.length == 2 && temp[0].length() > 1 && temp[1].length() > 6) {
                            users.add(new User(temp[0], temp[1]));
                        } else {
                            throw new IllegalArgumentException("argument is null");
                        }
                    }
            );
        }
        return users;
    }

    /*public void create(String tableName, String idName, String clm1Name, String clm2Name) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            try (PreparedStatement ps = cnt.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS ? (?, ?, ?)")) {
                ps.setString(1, tableName);
                ps.setString(2, idName);
                ps.setString(3, clm1Name);
                ps.setString(4, clm2Name);
                ps.execute();
            }
        }
    }*/

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement(
                        "INSERT INTO users(name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "src/main/java/ru/job4j/jdbc/dump.txt");
        /*db.create("users", "id SERIAL PRIMARY KEY", "name TEXT", "email TEXT");*/
        db.save(db.load());
    }
}
