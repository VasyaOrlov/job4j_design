package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Класс для определния статистики изменений в коллекции
 */
public class Analize {
    /**
     * Метод определяет колличество для каждого типа изменения коллекции
     * @param previous - исходная коллекция
     * @param current - конечное состояние коллекции
     * @return - объект Info с информацией по изменениям
     */
    public static Info diff(Set<User> previous, Set<User> current) {
        int added;
        int changed = 0;
        int deleted = 0;
        Map<User, String> mapCurrent = new HashMap<>();
        for (User user : current) {
            mapCurrent.put(user, user.getName());
        }
        for (User user : previous) {
            String name = mapCurrent.get(user);
            if (name == null) {
                deleted++;
            }
            if (name != null && !name.equals(user.getName())) {
                changed++;
            }
        }
        added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}