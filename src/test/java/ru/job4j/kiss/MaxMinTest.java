package ru.job4j.kiss;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.*;
import org.junit.Test;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest {

    @Test
    public void maxTest() {
        MaxMin mm = new MaxMin();
        Comparator<Integer> comp = Integer::compareTo;
        List<Integer> list = List.of(2, 3, 1, 7, 1, 6);
        assertThat(mm.max(list, comp), is(7));
    }

    @Test
    public void minTest() {
        MaxMin mm = new MaxMin();
        Comparator<Integer> comp = Integer::compareTo;
        List<Integer> list = List.of(2, 3, 1, 7, 1, 6);
        assertThat(mm.min(list, comp), is(1));
    }
}