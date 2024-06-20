package com.chuwa.exercise.collection;

import org.junit.Test;

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
        map.put("A",1);
        map.putIfAbsent("B",2);

        Map<String, Integer> tmp = new HashMap<>();
        tmp.put("Monday", 1);
        tmp.put("A",2);

        map.putAll(tmp);
        System.out.println(map);

        System.out.println(map.get("A"));
        System.out.println(map.getOrDefault("z", 26));
        System.out.println(map.containsKey("A"));
        System.out.println(map.containsKey("z"));

        for(String key : map.keySet()) {
            System.out.print( key + " : "+map.get(key) + " ");
        }
        System.out.println();

        for(Integer val : map.values()) {
            System.out.print( " value "+val +" ");
        }
        System.out.println();

        for(Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.print( entry.getKey() + " : "+ entry.getValue()+ " ");
        }
        System.out.println();

        System.out.println(map.isEmpty());
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
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 10);
        boolean replaced = map.replace("apple", 11, 15);

        System.out.println(replaced);
        System.out.println( map);

        map.replace("apple", 12);
        System.out.println( map);

        map.put("Monday", 1);
        map.put("Tuesday", 2);

        map.replaceAll((key, value) -> value + 100);
        System.out.println( map);

        map.remove("apple");
        map.remove("Monday", 1);
        System.out.println( map);
        map.remove("Monday", 101);
        System.out.println( map);

        map.put("apple", 10);
        Integer value = map.compute("apple", (key, oldValue) -> oldValue + 10);
        System.out.println(value);
        System.out.println(map);

        value = map.computeIfAbsent("banana", val ->   10);
        System.out.println(value);
        System.out.println(map);

        value = map.computeIfPresent("banana", (k,v) ->   v + 100);
        System.out.println(value);
        System.out.println(map);


    }
}
