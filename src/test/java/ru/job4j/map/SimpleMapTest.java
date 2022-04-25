package ru.job4j.map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMapTest {

    @Test
    public void putTest1() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "one"));
        assertTrue(map.put(10, "one"));
        assertFalse(map.put(1, "three"));
    }

    @Test
    public void putTest2() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "one");
        map.put(3, "one");
        map.put(4, "one");
        map.put(5, "one");
        map.put(6, "one");
        map.put(7, "one");
        map.put(8, "one");
        assertTrue(map.put(9, "one"));
    }
    @Test
    public void getTest1() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertThat(map.get(1), is("one"));
    }

    @Test
    public void getTest2() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(2));
    }

    @Test
    public void removeTest1() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        assertTrue(map.remove(1));
    }

    @Test
    public void removeTest2() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(2));
    }

    @Test
    public void iteratorTest1() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "one");
        map.put(2, "one");
        Iterator<Integer> iterator = map.iterator();
        assertThat(iterator.next(), is(1));
        assertThat(iterator.next(), is(2));
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorTest2() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void iteratorTest3() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> iterator = map.iterator();
        map.put(2, "one");
        iterator.next();
    }
}