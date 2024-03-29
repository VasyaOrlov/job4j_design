package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVReader {
    private static StringBuilder print(ArrayList<Integer> listIndex, ArrayList<String> list) {
        StringBuilder write = null;
        for (int i = 0; i < listIndex.size(); i++) {
            if (i == 0) {
                write = new StringBuilder(list.get(listIndex.get(i)));
            } else {
                write.append(";").append(list.get(listIndex.get(i)));
            }
        }
        return write;
    }
    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        ArrayList<Integer> listIndex = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path));
            PrintStream outPath = "stdout".equals(out) ? System.out : new PrintStream(new FileOutputStream(out))) {
            Scanner scanner = new Scanner(in.readLine()).useDelimiter(delimiter);
            ArrayList<String> list = new ArrayList<>();
            while (scanner.hasNext()) {
                list.add(scanner.next());
            }
            for (String str : list) {
                for (String strFilter : filter) {
                    if (strFilter.equals(str)) {
                        listIndex.add(list.indexOf(str));
                    }
                }
            }
            StringBuilder write = print(listIndex, list);
            outPath.println(write);
            String line;
            while ((line = in.readLine()) != null) {
                Scanner scannerCycle = new Scanner(line).useDelimiter(delimiter);
                ArrayList<String> listCycle = new ArrayList<>();
                while (scannerCycle.hasNext()) {
                    listCycle.add(scannerCycle.next());
                }
                StringBuilder writeCycle = print(listIndex, listCycle);
                outPath.println(writeCycle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName argsName) {
        String path = argsName.get("path");
        if (Files.isDirectory(Paths.get(path)) || !(new File(path).exists())) {
            throw new IllegalArgumentException("Некорректный путь path");
        }
        String delimiter = argsName.get("delimiter");
        if (!";".equals(delimiter)) {
            throw new IllegalArgumentException("Некорректный формат делиметра");
        }
        argsName.get("out");
        argsName.get("filter");
    }

    public static void main(String[] args) throws FileNotFoundException {
        if (args.length < 4) {
            throw new IllegalArgumentException("Заполните параметры запуска.");
        }
        ArgsName argsName = ArgsName.of(args);
        CSVReader.validate(argsName);
        handle(argsName);
    }
}