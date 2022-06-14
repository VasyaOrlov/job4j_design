package ru.job4j.template;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Ignore;
import org.junit.Test;
import java.util.Map;
import java.util.NoSuchElementException;

@Ignore
public class GeneratorTest {

    @Test
    public void workMethodProduce() {
        Generator gen = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject", "you");
        assertThat(gen.produce(template, args), is("I am a Petr, Who are you? "));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenNoKeyInMap() {
        Generator gen = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr");
        gen.produce(template, args);
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenExtraKey() {
        Generator gen = new TextGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> args = Map.of("name", "Petr", "subject", "you", "face", "smile");
        gen.produce(template, args);
    }
}