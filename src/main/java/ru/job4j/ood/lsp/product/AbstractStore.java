package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private final static int DISCOUNT = 30;
    private final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = check(food);
        if (rsl) {
            double fresh = food.fresh();
            if (fresh < 25 && fresh > 0) {
                food.setDiscount(DISCOUNT);
            }
            list.add(food);
        }
        return rsl;
    }

    @Override
    public abstract boolean check(Food food);

    @Override
    public List<Food> findAll() {
        return new ArrayList<>(list);
    }
}
