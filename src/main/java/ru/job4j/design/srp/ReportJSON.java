package ru.job4j.design.srp;

import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import java.util.function.Predicate;

public class ReportJSON implements Report {

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return new GsonBuilder().create().toJson(store.findBy(filter));
    }
}
