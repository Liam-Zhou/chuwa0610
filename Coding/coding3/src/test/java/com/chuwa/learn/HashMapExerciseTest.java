package com.chuwa.learn;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
public class HashMapExerciseTest {
    /**
     * e.g.
     * Map<String, Integer> map = new HashMap<>();

     * put(K key, V value)
     * putIfAbsent(K key, V value)
     * putAll(Map<? extends K, ? extends V> m)

     * get(Object key)
     * getOrDefault(Object key, V defaultValue)

     * containsKey(Object key)
     * containsValue(Object value)

     * keySet()
     * values()
     * isEmpty()
     */

    @Test
    public void learn_Inserting_And_Retrieving() {
        Map<String, Integer> map = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertSame(1,map.get("a"));
        map.putIfAbsent("b", 3);
        assertSame(2,map.get("b"));

        map2.put("c", 1);
        map2.put("d", 2);
        map.putAll(map2);
        assertSame(1,map.get("c"));

        assertSame(null,map.getOrDefault("az",null));
        assertTrue(map.containsKey("a"));
        assertTrue(map.containsValue(1));

        assertSame(4,map.keySet().size());
        assertSame(4,map.values().size());
        assertFalse(map.isEmpty());
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
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        map.replace("a",10);
        assertSame(10,map.get("a"));
        map.replace("b",2,20);
        assertSame(20,map.get("b"));
        map.replaceAll((k,v)->v+1);
        assertSame(21,map.get("b"));

        map.remove("a");
        assertSame(null,map.get("a"));
        assertFalse(map.remove("b",10));

        map.compute("b",(k,v)->v+1);
        assertSame(22,map.get("b"));

        map.computeIfAbsent("a",(k)->10);
        assertSame(10,map.get("a"));

        map.computeIfPresent("a",(k,v)->v+1);
        assertSame(11,map.get("a"));
    }
}
