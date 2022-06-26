package ru.job4j.ood.lsp.product;

public class Trash extends AbstractStore {
    private static final int TRASH_PERCENT = 0;
    @Override
    public boolean check(Food food) {
        return food.getFreshnessPercentage() <= TRASH_PERCENT;
    }
}