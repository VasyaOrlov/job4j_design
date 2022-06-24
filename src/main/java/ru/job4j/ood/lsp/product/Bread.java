package ru.job4j.ood.lsp.product;

import java.time.LocalDate;

public class Bread extends Food {
    public Bread(String name, LocalDate expiryDate, LocalDate createDate, int price, int discount) {
        super(name, expiryDate, createDate, price, discount);
    }
}
