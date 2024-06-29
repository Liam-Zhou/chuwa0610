package com.chuwa.exercise.collection;

import org.junit.Test;

import java.time.DayOfWeek;
import java.util.EnumMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class AdditionalMapExerciseTest {

    /**
     * e.g.
     * ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     */
    ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
    @Test
    public void learn_ConcurrentHashMap() {


        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        map.putIfAbsent("Date", 4);
        map.putIfAbsent("Banana", 22);

        Map<String, Integer> anotherMap = new ConcurrentHashMap<>();
        anotherMap.put("Elderberry", 5);
        anotherMap.put("Fig", 6);
        map.putAll(anotherMap);

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
        Map<DayOfWeek, Integer> map = new IdentityHashMap<>();

        map.put(DayOfWeek.MONDAY, 1);
        map.put(DayOfWeek.TUESDAY, 2);
        map.put(DayOfWeek.WEDNESDAY, 3);

        map.putIfAbsent(DayOfWeek.THURSDAY, 4);
        map.putIfAbsent(DayOfWeek.TUESDAY, 22);
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

        enumMap.put(DayOfWeek.MONDAY, 1);
        enumMap.put(DayOfWeek.TUESDAY, 2);
        enumMap.put(DayOfWeek.WEDNESDAY, 3);

        enumMap.putIfAbsent(DayOfWeek.THURSDAY, 4);
        enumMap.putIfAbsent(DayOfWeek.TUESDAY, 22); // 不会覆盖已有的 "TUESDAY"

        System.out.println("Value for 'MONDAY': " + enumMap.get(DayOfWeek.MONDAY));
        System.out.println("Value for 'TUESDAY': " + enumMap.get(DayOfWeek.TUESDAY));

    }
}
