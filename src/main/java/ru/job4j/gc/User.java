package ru.job4j.gc;

public class User {
    private int age;
    private String name;

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("сборщик");
        super.finalize();
    }

    public static void main(String[] args) {
        User user1 = new User(5, "Ken");
        System.out.println("теоритический размер user1 = 16 + 4 + (\"(24 + 3*2)\"=32) = (\"52\"=56)");
        User user2 = new User(25, "Denis Levshin");
        System.out.println("теоритический размер user2 = 16 + 4 + (\"(24 + 13*2)\"=56) = (\"76\"=80)");
        for (int i = 1; i < 1000; i++) {
            new User(i, "user");
        }
    }
}
