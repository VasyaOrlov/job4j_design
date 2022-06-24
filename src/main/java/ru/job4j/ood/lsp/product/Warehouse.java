package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Store {
    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = check(food);
        if (rsl) {
            list.add(food);
        }
        return rsl;
    }

    @Override
    public boolean check(Food food) {
        return food.fresh() >= 75;
    }

    @Override
    public List<Food> findAll() {
        return list;
    }
}
