package ru.job4j.ood.lsp.product;

import java.util.List;

public interface Store {
    boolean add(Food food);
    boolean check(Food food);
    List<Food> findAll();
}
