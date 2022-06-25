package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public class Trash extends AbstractStore {
    @Override
    public boolean check(Food food) {
        return food.fresh() <= 0;
    }
}