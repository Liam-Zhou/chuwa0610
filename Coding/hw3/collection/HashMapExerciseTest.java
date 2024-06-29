package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author b1go
 * @date 6/12/22 4:47 PM
 */
public class HashMapExerciseTest {

    /**
     * e.g.
     * Map<String, Integer> map = new HashMap<>();
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

    @Test
    public void learn_Inserting_And_Retrieving() {
        Map<String, Integer> map = new HashMap<>();


        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        map.putIfAbsent("Four", 4);
        map.putIfAbsent("Two", 22);

        Map<String, Integer> anotherMap = new HashMap<>();
        anotherMap.put("Five", 5);
        anotherMap.put("Six", 6);
        map.putAll(anotherMap);

        System.out.println("Value for 'Three': " + map.get("Three"));
        System.out.println("Value for 'Seven' (default 7): " + map.getOrDefault("Seven", 7));

        System.out.println("Contains key 'Two': " + map.containsKey("Two"));
        System.out.println("Contains value 4: " + map.containsValue(4));


        Set<String> keys = map.keySet();
        System.out.println("Keys: " + keys); // 输出 [One, Two, Three, Four, Five, Six]


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
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.replace("Two", 22);
        map.replace("Three", 3, 33);

        map.replaceAll((key, value) -> value * 2);
        map.remove("One");

        // 条件删除键
        map.remove("Two", 44);

        map.compute("Three", (key, value) -> value == null ? 1 : value + 1);

        map.computeIfAbsent("Four", key -> 4);
        // 如果键存在，则计算键的值
        map.computeIfPresent("Four", (key, value) -> value * 2);


    }
}
