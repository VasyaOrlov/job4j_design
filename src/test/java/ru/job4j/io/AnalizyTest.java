package ru.job4j.io;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import java.io.*;

public class AnalizyTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void unavailableTest() throws IOException {
        File source = folder.newFile("source.txt");
        File target = folder.newFile("target.txt");
        try (PrintWriter rsl = new PrintWriter(source)) {
            rsl.write("200 10:56:01\n");
            rsl.write("500 10:57:01\n");
            rsl.write("400 10:58:01\n");
            rsl.write("200 10:59:01\n");
            rsl.write("500 11:01:02\n");
            rsl.write("200 11:02:02\n");
        }
        Analizy analysis = new Analizy();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        List<String> rsl = new ArrayList<>();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(rsl::add);
        }
        String expect1 = "10:57:01;10:59:01;";
        String expect2 = "11:01:02;11:02:02;";
        assertThat(rsl.get(0), is(expect1));
        assertThat(rsl.get(1), is(expect2));
    }
}