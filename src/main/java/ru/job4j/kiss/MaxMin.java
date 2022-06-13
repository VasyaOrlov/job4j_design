package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> pred = (e, k) ->  comparator.compare(e, k) > 0;
        return comparison(value, comparator, pred);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        BiPredicate<T, T> pred = (e, k) ->  comparator.compare(e, k) < 0;
        return comparison(value, comparator, pred);
    }

    private <T> T comparison(List<T> value, Comparator<T> comparator, BiPredicate<T, T> pred) {
        T rsl = null;
        if (value.size() > 0) {
            rsl = value.get(0);
            for (int i = 1; i < value.size(); i++) {
                T temp = value.get(i);
                rsl = pred.test(rsl, temp) ? rsl : temp;
            }
        }
        return rsl;
    }
}