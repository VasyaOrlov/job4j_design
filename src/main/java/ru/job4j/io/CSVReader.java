package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String path = argsName.get("path");
        String out = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String[] filter = argsName.get("filter").split(",");
        ArrayList<Integer> listIndex = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path));
        PrintWriter outPath = new PrintWriter(new FileWriter(out))) {
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
            StringBuilder write = null;
            for (int i = 0; i < listIndex.size(); i++) {
                if (i == 0) {
                    write = new StringBuilder(list.get(listIndex.get(i)));
                } else {
                    write.append(";").append(list.get(listIndex.get(i)));
                }
            }
            System.out.println(write);
            if (write != null) {
                outPath.write(write.toString());
            }
            String line;
            while ((line = in.readLine()) != null) {
                Scanner scannerCycle = new Scanner(line).useDelimiter(delimiter);
                ArrayList<String> listCycle = new ArrayList<>();
                while (scannerCycle.hasNext()) {
                    listCycle.add(scannerCycle.next());
                }
                StringBuilder writeCycle = null;
                for (int i = 0; i < listIndex.size(); i++) {
                    if (i == 0) {
                        writeCycle = new StringBuilder(listCycle.get(listIndex.get(i)));
                    } else {
                        writeCycle.append(";").append(listCycle.get(listIndex.get(i)));
                    }
                }
                System.out.println(writeCycle);
                if (writeCycle != null) {
                    outPath.write(writeCycle.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        try {
            handle(argsName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}