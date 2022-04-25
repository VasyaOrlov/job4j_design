package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * класс реализует карту (хэш таблицу)
 * @param <K> - обощенный тип данных ключа
 * @param <V> - обобщенный тип данных значения
 */
public class SimpleMap<K, V> implements MapTrial<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    /**
     * метод расширяет хранилище-массив при превышении фактора загрузки
     * и добавляет пару ключ-значение
     * @param key - ключ
     * @param value - значение
     * @return - выполнено ли добавление
     */
    @Override
    public boolean put(K key, V value) {
        if ((float) count / table.length  > LOAD_FACTOR) {
            expand();
        }
        boolean rsl = putVal(key, value, table);
        if (rsl) {
            count++;
            modCount++;
        }
        return rsl;
    }

    /**
     * метод добавляет пару ключ-значение в указанный массив
     * @param key - ключ
     * @param value - значение
     * @param table - массив
     * @return - выполнено ли добавление
     */
    private boolean putVal(K key, V value, MapEntry<K, V>[] table) {
        boolean rsl = table[indexFor(hash(key.hashCode()))] == null;
        if (rsl) {
            table[indexFor(hash(key.hashCode()))] = new MapEntry<>(key, value);
        }
        return rsl;
    }

    /**
     * метод вычисляет хэш значение
     * @param hashCode - хэш код
     * @return - хэш значение
     */
    private int hash(int hashCode) {
        return hashCode ^ (hashCode >>> 16);
    }

    /**
     * метод вычисляет индекс по которому присходит вставка в массив-хранилище
     * @param hash - хэш значение
     * @return - индекс
     */
    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    /**
     * метод выполняет увеличение массива-хранилища в 2 раза с рехэшированием
     */
    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> e : table) {
            if (e != null) {
                putVal(e.key, e.value, newTable);
            }
        }
        table = newTable;
    }

    /**
     * метод возвращает значение по ключу
     * @param key - ключ
     * @return - значение
     */
    @Override
    public V get(K key) {
        MapEntry<K, V> rsl = table[indexFor(hash(key.hashCode()))];
        return rsl == null ? null : rsl.value;
    }

    /**
     * метод удаляет пару ключ-значение по ключу
     * @param key - ключ
     * @return - выполнено ли удаление
     */
    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        boolean rsl = table[index] != null;
        table[index] = null;
        count--;
        modCount++;
        return rsl;
    }

    /**
     * метод возвращает итератор коллекции
     * @return итератор коллекции
     */
    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            final private int expectModCount = modCount;
            int countIt;
            @Override
            public boolean hasNext() {
                if (expectModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (countIt < capacity) {
                    if (table[countIt] != null) {
                        return true;
                    }
                    countIt++;
                }
                return false;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[countIt++].key;
            }
        };
    }

    /**
     * сложенный класс - описывает объект ключ-значение
     * @param <K> - обобщеный тип ключа
     * @param <V> - обощенный тип значения
     */
    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}