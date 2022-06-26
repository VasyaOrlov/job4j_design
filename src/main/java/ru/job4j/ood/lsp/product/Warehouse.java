package ru.job4j.ood.lsp.product;

public class Warehouse extends AbstractStore {
    private static final int WAREHOUSE_PERCENT = 75;

    @Override
    public boolean check(Food food) {
        return food.getFreshnessPercentage() >= WAREHOUSE_PERCENT;
    }

}
