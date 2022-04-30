package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(config);
        assertThat(config.value("name"), is("Vasia"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenEmpty() {
        String path = "./data/empty_lines_and_comment.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(config);
        assertThat(config.value("colour"), is("orange"));
        assertThat(config.value("length"), is("five"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenViolation1() {
        String path = "./data/violation1.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenViolation2() {
        String path = "./data/violation2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenViolation3() {
        String path = "./data/violation3.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenViolation4() {
        String path = "./data/violation4.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenViolation5() {
        String path = "./data/violation5.properties";
        Config config = new Config(path);
        config.load();
        System.out.println(config);
        assertThat(config.value("key"), is("value=1"));
        assertThat(config.value("signal"), is("red="));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }
}