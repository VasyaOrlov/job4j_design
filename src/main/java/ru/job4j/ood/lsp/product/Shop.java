package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Store {
    private final static int DISCOUNT = 30;
    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = check(food);
        if (rsl) {
            if (food.fresh() < 25) {
                food.setDiscount(DISCOUNT);
            }
            list.add(food);
        }
        return rsl;
    }

    @Override
    public boolean check(Food food) {
        double rsl = food.fresh();
        return rsl < 75 && rsl > 0;
    }

    @Override
    public List<Food> findAll() {
        return list;
    }
}
