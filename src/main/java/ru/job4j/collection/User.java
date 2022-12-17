package ru.job4j.collection;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        int n = 16;
        Map<User, Object> map = new HashMap<>(n);
        Calendar date = new GregorianCalendar(1985, Calendar.AUGUST, 6);
        User user1 = new User("Vladimir", 13, date);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> n);
        int backet1 = hash1 & (n - 1);
        User user2 = new User("Vladimir", 13, date);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> n);
        int backet2 = hash2 & (n - 1);
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.printf("user1 - хэшкод: %s, хэш: %s, бакет1: %s \n", hashCode1, hash1, backet1);
        System.out.printf("user2 - хэшкод: %s, хэш: %s, бакет2: %s \n", hashCode2, hash2, backet2);
        for (var key : map.keySet()) {
            System.out.println(key);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
