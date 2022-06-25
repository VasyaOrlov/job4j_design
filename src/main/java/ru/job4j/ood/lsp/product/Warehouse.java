package ru.job4j.ood.lsp.product;

import ru.job4j.cache.AbstractCache;

import java.util.ArrayList;
import java.util.List;

public class Warehouse extends AbstractStore {

    @Override
    public boolean check(Food food) {
        return food.fresh() >= 75;
    }

}
