package ru.job4j.design.srp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;

import java.util.function.Predicate;

public class ReportJSON implements Report {
    private final Gson gson;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    private final Store store;

    public ReportJSON(Store store) {
        this.store = store;
        this.gson = new GsonBuilder().create();
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        return gson.toJson(store.findBy(filter));
    }
}
