package ru.job4j.ood.dip.examples;

import java.util.List;

public class PasswordDbByDIP {
    private DB someDataBase;

    public void upload(String url, Password password) {
        someDataBase.connection(url);
        /*подключение к БД и загрузка данных*/
    }

    interface DB {

        void connection(String url);
    }

    abstract class Password {
        List<String> password;

        void addPass(String password) {

        }
    }
}
