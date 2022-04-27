package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {

    public static int[][] multiple(int size) {
        int[][] result = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = (i + 1) * (j + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            int[][] table = multiple(10);
            for (int[] i : table) {
                for (int j : i) {
                    out.write((j + " ").getBytes());
                }
                out.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
