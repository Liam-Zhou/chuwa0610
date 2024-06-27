package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class LinkedHashMapExerciseTest {
    /**
     * e.g.
     * HashMap<String, Integer> map = new LinkedHashMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     *
     * get(Object key)
     * getOrDefault(Object key, V defaultValue)
     *
     * containsKey(Object key)
     * containsValue(Object value)
     *
     * keySet()
     * values()
     * isEmpty()
     */
    HashMap<String, Integer> map = new LinkedHashMap<>();
    @Test
    public void learn_Inserting_And_Retrieving() {

        map.put("somethinTest", 1200);
        map.putIfAbsent("absent",1300);
        map.get("absent");

        map.getOrDefault("Grape", 0);
        map.containsKey("Banana");
        map.containsValue(4);

        Set<String> keys = map.keySet();
        System.out.println("Keys: " + keys);

        Collection<Integer> values = map.values();
        System.out.println("Values: " + values);

        System.out.println("Is map empty: " + map.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     * replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
     *
     * remove(Object key)
     * remove(Object key, Object value)
     *
     * compute(Key, BiFunction)
     * computeIfAbsent(Key, Function)
     * computeIfPresent(Key, BiFunction)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        map.replace("Banana", 22, 2);
        map.replace("Cherry", 33);
        map.remove("Cherry");
        map.remove("CHery", 6);

    }
}
