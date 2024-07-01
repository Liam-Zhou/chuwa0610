package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author b1go
 * @date 6/12/22 4:47 PM
 */
public class TreeMapExerciseTest {

    /**
     * e.g.
     * TreeMap<String, Integer> map = new TreeMap<>();
     *
     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)
     *
     * get(Object key)
     * firstKey()
     * lastKey()
     *
     * containsKey(Object key)
     * containsValue(Object value)
     *
     * keySet()
     * values()
     * isEmpty()
     */
    TreeMap<String, Integer> map = new TreeMap<>();
    @Test
    public void learn_Inserting_And_Retrieving() {


        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Cherry", 3);

        map.putIfAbsent("Date", 4);
        map.putIfAbsent("Banana", 22);

        Map<String, Integer> anotherMap = new TreeMap<>();
        anotherMap.put("Elderberry", 5);
        anotherMap.put("Fig", 6);
        map.putAll(anotherMap);

        map.get("Cherry");
        map.firstKey();
        map.lastKey();
        map.containsKey("Apple");
        map.containsValue(4);
        Set<String> keys = map.keySet();
        System.out.println("Keys: " + keys);

        Collection<Integer> values = map.values();
        System.out.println("Values: " + values);
        map.isEmpty();



    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     *
     * remove(Object key)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        map.replace("Banana", 22, 2);
        map.replace("Cherry", 33);
        map.remove("Date");

    }
}
