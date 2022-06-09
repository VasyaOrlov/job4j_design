package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + "\\" + key))) {
            in.lines().forEach(e -> {
                        text.append(e);
                        text.append(System.lineSeparator());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        String rsl = text.toString();
        super.put(key, rsl);
        return rsl;
    }
}