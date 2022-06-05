package ru.job4j.gc;

public class User {
    private int age;
    private String name;

    public User() {
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("сборщик");
        super.finalize();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void main(String[] args) {
        User us = null;
        System.out.println("теоритический размер us = \n"
                + "4 ссылка\n"
                + "4 выравнивание\n"
                + " = 8\n");
        User user = new User();
        System.out.println("теоритический размер user = \n"
                + "4 - ссылка на user\n"
                    + "12 - заголовок \n"
                    + "4 - поле инт \n"
                    + "4 - ссылка на string\n"
                    + "4 - выравнивание\n"
                + " = 28кб");

        User user1 = new User(5, "Ken");
        System.out.println("теоритический размер user1 = \n"
                + "4 - ссылка на user1\n"
                    + "12 - заголовок \n"
                    + "4 - поле инт \n"
                    + "4 - ссылка на string\n"
                        + "12 - заголовок string\n"
                        + "4*3 - поля string\n"
                        + "4 - ссылка на массив\n"
                            + "12 - заголовок массива\n"
                            + "4 - поле(длина) массива\n"
                            + "3*2 - char символыKen\n"
                            + "2 -выравнивание\n"
                        + "6 - выравнивание\n"
                    + "4 - выравнивание\n"
                + " = 76кб");
        for (int i = 1; i < 1000; i++) {
            new User(i, "user");
        }
    }
}