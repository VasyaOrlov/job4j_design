package ru.job4j.ood.ocp;

/**
 * класс демонстрирует нарушение принципа OCP (открытости-закрытости)
 */
public class Dog {

    /**
     * Создаваемый объект должен зависеть от абстракции, а не от реализации
     */
    public void create() {
        Gav gav = new Gav();
    }

    /**
     *параметры должны быть абстракциями
     */
    public void buy(Gav gav) {
        System.out.println("ПОКУПКА");
    }

    /**
     * Поля должны представлять абстракцию
     */
    private static class Gav {
        private Name name;

    }

    private static class Name {
        String firstName;
        String secondName;
        Name(String first, String second) {
            this.firstName = first;
            this.secondName = second;
        }
    }
}
