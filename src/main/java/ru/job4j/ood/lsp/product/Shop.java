package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AbstractStore {
    @Override
    public boolean check(Food food) {
        double rsl = food.fresh();
        return rsl < 75 && rsl > 0;
    }
}
