package ru.job4j.io;

import java.io.*;

public class Analizy {
    public void unavailable(String source, String target) {
        boolean job = true;
        try (BufferedReader text = new BufferedReader(new FileReader(source));
            BufferedWriter writer = new BufferedWriter(new FileWriter(target))) {
            String line;
            while ((line = text.readLine()) != null) {
                if (line.contains("400") || line.contains("500")) {
                    if (job) {
                        job = false;
                        writer.write(line.substring(5) + ";");
                    }
                } else {
                    if (!job) {
                        writer.write(line.substring(5) + ";" + System.lineSeparator());
                        job = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}