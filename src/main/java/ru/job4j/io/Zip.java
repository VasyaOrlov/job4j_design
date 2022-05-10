package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path temp : sources) {
                zip.putNextEntry(new ZipEntry(temp.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(temp.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void validate(String[] args) {
        ArgsName arg = ArgsName.of(args);
        if (!new File(arg.get("d")).exists()) {
            throw new IllegalArgumentException("Директория не существует");
        }
        arg.get("e");
        arg.get("o");
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.validate(args);
        ArgsName arg = ArgsName.of(args);
        String directory = arg.get("d");
        String exclude = arg.get("e").startsWith("*") ? arg.get("e").substring(1) : arg.get("e");
        System.out.println(exclude);
        String output = arg.get("o");
        Predicate<Path> predicate = p -> !p.getFileName().toString().endsWith(exclude);
        List<Path> list = Search.search(Paths.get(directory), predicate);
        zip.packFiles(list, new File(output));
    }
}