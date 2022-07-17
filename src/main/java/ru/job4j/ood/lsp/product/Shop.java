package ru.job4j.ood.lsp.product;

public class Shop extends AbstractStore {
    private static final int TOP_PERCENT = 75;
    private static final int DISCOUNT_PERCENT = 25;
    private static final int BOT_PERCENT = 0;
    private static final int DISCOUNT = 30;

    @Override
    public boolean add(Food food) {
        if (check(food)) {
            double fresh = food.getFreshnessPercentage();
            if (fresh < DISCOUNT_PERCENT && fresh > BOT_PERCENT) {
                food.setDiscount(DISCOUNT);
            }
        }
        return super.add(food);
    }

    @Override
    public boolean check(Food food) {
        double rsl = food.getFreshnessPercentage();
        return rsl < TOP_PERCENT && rsl > BOT_PERCENT;
    }
}
