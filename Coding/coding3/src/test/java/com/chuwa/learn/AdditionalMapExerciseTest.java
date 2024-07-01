package com.chuwa.learn;

import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertSame;
import java.time.DayOfWeek;
import java.time.Month;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;

public class AdditionalMapExerciseTest {

    /**
     * e.g.
     * ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     */
    @Test
    public void learn_ConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertSame(1, map.get("a"));
        map.putIfAbsent("a", 10);
        assertSame(1, map.get("a"));

        ConcurrentHashMap<String, Integer> map2 = new ConcurrentHashMap<>();
        map2.put("c", 10);
        map2.put("d", 20);
        map.putAll(map2);
        assertSame(10, map.get("c"));
    }

    /**
     * e.g.
     * Map<DayOfWeek, Integer> map = new IdentityHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     */
    @Test
    public void learn_IdentityHashMap() {
        Map<String, Integer> map = new IdentityHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertSame(1, map.get("a"));
        map.putIfAbsent("a", 10);
        assertSame(1, map.get("a"));
    }

    /**
     * e.g.
     * EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     */
    @Test
    public void learn_EnumMap() {
        EnumMap<DayOfWeek, Integer> enumMap = new EnumMap<>(DayOfWeek.class);
        enumMap.put(MONDAY, 1);
        enumMap.put(TUESDAY,2);
        assertSame(1, enumMap.get(MONDAY));
        enumMap.putIfAbsent(MONDAY, 10);
        assertSame(1, enumMap.get(MONDAY));
    }
}