package ru.job4j.ood.isp;

/**
 * Интерфейс демонстрирует нарушение принципа разделения интерфейса ISP
 */
public interface Transport {
    /**
     * ошибка в неверном выделении абстракции.
     * каждый вид транспорта должен будет реализовать методы которые он не будет использовать
     */
    int maxSpeedFly();
    int maxSpeedMove();
    int maxSpeedSwim();
}
