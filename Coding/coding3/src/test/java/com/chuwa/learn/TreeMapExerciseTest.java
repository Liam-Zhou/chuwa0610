package com.chuwa.learn;
import org.junit.Test;

import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void learn_Inserting_And_Retrieving() {
        TreeMap<String, Integer> map = new TreeMap<>();
        TreeMap<String, Integer> map2 = new TreeMap<>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("b", 2);
        assertSame(1,map.get("a"));
        map.putIfAbsent("z",100);
        assertSame(100,map.get("z"));
        map2.put("e", 30);
        map2.put("d", 10);
        map2.put("f", 20);
        map.putAll(map2);
        assertSame(30,map.get("e"));

        assertSame(10,map.get("d"));
        assertTrue(map.firstKey().equals("a"));
        assertTrue(map.lastKey().equals("z"));

        assertTrue(map.containsKey("a"));
        assertTrue(map.containsValue(100));

        assertSame(7,map.keySet().size());
        assertSame(7,map.values().size());
        assertFalse(map.isEmpty());
    }

    /**
     * replace(K key, V oldValue, V newValue)
     * replace(K key, V value)
     *
     * remove(Object key)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        TreeMap<String, Integer> map = new TreeMap<>();
        map.put("c", 3);
        map.put("a", 1);
        map.put("b", 2);

        map.replace("a",10);
        assertSame(10,map.get("a"));

        assertFalse(map.replace("a",22,200));
        map.remove("a");
        assertFalse(map.containsKey("a"));
    }
}