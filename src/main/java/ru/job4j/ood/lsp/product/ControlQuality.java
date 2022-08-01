package ru.job4j.ood.lsp.product;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality {
    private final List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void control(List<Food> foods) {
        for (Food food : foods) {
            for (Store store : stores) {
                if (store.add(food)) {
                    break;
                }
            }
        }
    }

    public void resort() {
        List<Food> fd = new ArrayList<>();
        for (Store store : stores) {
            fd.addAll(store.findAll());
            store.deleteAll();
        }
        control(fd);
    }
}
