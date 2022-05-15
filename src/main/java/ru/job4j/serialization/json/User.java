package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.serialization.java.Contact;

import java.util.Arrays;

public class User {
    boolean schoolMan = true;
    short growth = 180;
    String name = "Alex";
    Contact tel = new Contact(123456, "+7 (111) 111-11-11");
    int[] values = new int[] {1, 2, 3};

    @Override
    public String toString() {
        return "User{"
                + "schoolMan=" + schoolMan
                + ", growth=" + growth
                + ", name='" + name + '\''
                + ", tel=" + tel
                + ", values=" + Arrays.toString(values)
                + '}';
    }

    public static void main(String[] args) {
        User user = new User();
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(user));
        final String userJson =
                "{"
                    + "\"schoolMan\":true,"
                    + "\"growth\":180,"
                    + "\"name\":\"Alex\","
                    + "\"tel\":"
                    + "{"
                        + "\"zipCode\":123456,"
                        + "\"phone\":\"+7 (111) 111-11-11\""
                    + "},"
                    + "\"values\":[1,2,3]"
                + "}";
        final User userMod = gson.fromJson(userJson, User.class);
        System.out.println(userMod);
    }
}
