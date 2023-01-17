package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

public class User {
    private final boolean married;
    private final int age;
    private final String name;
    private final Contact contact;
    private final String[] skills;

    public User(boolean married, int age, String name, Contact contact, String[] skills) {
        this.married = married;
        this.age = age;
        this.name = name;
        this.contact = contact;
        this.skills = skills;
    }

    public static void main(String[] args) {
        final User user = new User(true, 22, "Roman", new Contact("11-111"),
                new String[]{"AutoCAD", "Excel"});
        System.out.println(user);
        final Gson gson = new GsonBuilder().create();
        String jsonTemp = gson.toJson(user);
        System.out.println(jsonTemp);
        final User userMod = gson.fromJson(jsonTemp, User.class);
        System.out.println(userMod);

        System.out.println("JSONObject:");
        JSONObject jsonContact = new JSONObject("{\"phone\":\"11-111\"}");
        JSONArray jsonSkills = new JSONArray(List.of("AutoCAD", "Excel"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("married", user.isMarried());
        jsonObject.put("age", user.getAge());
        jsonObject.put("name", user.getName());
        jsonObject.put("contact", jsonContact);
        jsonObject.put("skills", jsonSkills);

        System.out.println(jsonObject.toString());

        /* Преобразуем объект user в json-строку */
        System.out.println(new JSONObject(user).toString());

    }

    @Override
    public String toString() {
        return "User{"
                + "married=" + married
                + ", age=" + age
                + ", name=" + name
                + ", contact=" + contact
                + ", statuses=" + Arrays.toString(skills)
                + '}';
    }

    public boolean isMarried() {
        return married;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public Contact getContact() {
        return contact;
    }

    public String[] getSkills() {
        return skills;
    }
}
