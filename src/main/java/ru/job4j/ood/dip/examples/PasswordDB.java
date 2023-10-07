package ru.job4j.ood.dip.examples;

import java.util.List;

public class PasswordDB {
    private SqlDB sqlDB;

    public void upload(String url, List<String> password) {
        sqlDB.connection(url);
        /*подключение к базе SQL и загрузка данных*/
    }

    public class SqlDB {

        public void connection(String url) {

        }
    }
}

