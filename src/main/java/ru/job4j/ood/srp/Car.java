package ru.job4j.ood.srp;

import java.util.Date;

/**
 * Класс для демонстрации нарушений принципа SRP
 */
public class Car {
    private String name;
    private Date data;
    private final int age;

    public Car(String name, Date data) {
        this.name = name;
        this.data = data;
        this.age = age(data);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getAge() {
        return age;
    }

    /**
     * класс создает объект и инициализирует
     */
    private int age(Date data) {
        Date now = new Date();
        long diff =  Math.abs(data.getTime() - now.getTime());
        long year = 31_536_000_000L;
        return (int) (diff / year);
    }

    /**
     * при вычислении стоимости происходит операция без зависимости
     */
    public double carPrice() {
        return 100_000 * Math.pow(0.94, this.age);
    }

    /**
     * метод info следует вынести в отдельный класс т.к. нарушается одноцелевое назначение класса
     */
    public void info() {
        System.out.println(String.join(",",
                name,
                Integer.toString(getAge()),
                Double.toString(carPrice())));
    }
}
