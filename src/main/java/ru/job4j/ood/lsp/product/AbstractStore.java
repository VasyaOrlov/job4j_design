package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStore implements Store {

    protected final List<Food> list = new ArrayList<>();

    @Override
    public boolean add(Food food) {
        boolean rsl = check(food);
        if (rsl) {
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
