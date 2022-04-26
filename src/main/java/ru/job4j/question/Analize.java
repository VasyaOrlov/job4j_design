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
        for (User user : previous) {
            if (!current.contains(user)) {
                deleted++;
            }
            for (User curUser : current) {
                if (user.getId() == curUser.getId() && !user.getName().equals(curUser.getName())) {
                    changed++;
                }
            }
        }
        added = current.size() - previous.size() + deleted;
        return new Info(added, changed, deleted);
    }
}