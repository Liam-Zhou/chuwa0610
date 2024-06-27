import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:48 PM
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

    @Test
    public void learn_Inserting_And_Retrieving() {
        Map<String, Integer> map = new LinkedHashMap<>();

        // put(K key, V value)
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("One"));
        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(3), map.get("Three"));

        // putIfAbsent(K key, V value)
        map.putIfAbsent("Two", 22);
        map.putIfAbsent("Four", 4);
        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(4), map.get("Four"));

        // putAll(Map<? extends K, ? extends V> m)
        Map<String, Integer> anotherMap = new LinkedHashMap<>();
        anotherMap.put("Five", 5);
        anotherMap.put("Six", 6);
        map.putAll(anotherMap);
        assertEquals(Integer.valueOf(5), map.get("Five"));
        assertEquals(Integer.valueOf(6), map.get("Six"));

        // get(Object key)
        assertEquals(Integer.valueOf(1), map.get("One"));

        // getOrDefault(Object key, V defaultValue)
        assertEquals(Integer.valueOf(3), map.getOrDefault("Three", 0));
        assertEquals(Integer.valueOf(0), map.getOrDefault("Seven", 0));

        // containsKey(Object key)
        assertTrue(map.containsKey("One"));
        assertFalse(map.containsKey("Seven"));

        // containsValue(Object value)
        assertTrue(map.containsValue(1));
        assertFalse(map.containsValue(7));

        // keySet()
        assertEquals(6, map.keySet().size());

        // values()
        assertEquals(6, map.values().size());

        // isEmpty()
        assertFalse(map.isEmpty());
        map.clear();
        assertTrue(map.isEmpty());
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
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);

        // replace(K key, V oldValue, V newValue)
        assertTrue(map.replace("Two", 2, 22));
        assertEquals(Integer.valueOf(22), map.get("Two"));

        // replace(K key, V value)
        map.replace("Two", 222);
        assertEquals(Integer.valueOf(222), map.get("Two"));

        // replaceAll(BiFunction<? super K, ? super V, ? extends V> function)
        map.replaceAll((key, value) -> value + 10);
        assertEquals(Integer.valueOf(11), map.get("One"));
        assertEquals(Integer.valueOf(232), map.get("Two"));
        assertEquals(Integer.valueOf(13), map.get("Three"));

        // remove(Object key)
        map.remove("One");
        assertFalse(map.containsKey("One"));

        // remove(Object key, Object value)
        map.put("Four", 4);
        assertFalse(map.remove("Four", 5)); // Wrong value, so it won't be removed
        assertTrue(map.remove("Four", 4));  // Correct value, so it will be removed
        assertFalse(map.containsKey("Four"));

        // compute(Key, BiFunction)
        map.compute("Two", (key, value) -> value == null ? 1 : value * 2);
        assertEquals(Integer.valueOf(464), map.get("Two"));

        // computeIfAbsent(Key, Function)
        map.computeIfAbsent("Five", key -> 5);
        assertEquals(Integer.valueOf(5), map.get("Five"));

        // computeIfPresent(Key, BiFunction)
        map.computeIfPresent("Three", (key, value) -> value + 5);
        assertEquals(Integer.valueOf(18), map.get("Three"));
    }
}
