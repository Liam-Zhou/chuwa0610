import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.IdentityHashMap;
import java.util.EnumMap;
import java.util.Map;
import java.time.DayOfWeek;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/9/24 4:48 PM
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
    @Test
    public void learn_ConcurrentHashMap() {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        map.put("One", 1);
        map.put("Two", 2);

        assertEquals(Integer.valueOf(1), map.get("One"));
        assertEquals(Integer.valueOf(2), map.get("Two"));

        map.putIfAbsent("Two", 22);
        map.putIfAbsent("Three", 3);

        assertEquals(Integer.valueOf(2), map.get("Two"));
        assertEquals(Integer.valueOf(3), map.get("Three"));

        Map<String, Integer> additionalMap = new HashMap<>();
        additionalMap.put("Four", 4);
        additionalMap.put("Five", 5);

        map.putAll(additionalMap);

        assertEquals(Integer.valueOf(4), map.get("Four"));
        assertEquals(Integer.valueOf(5), map.get("Five"));
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
        IdentityHashMap<DayOfWeek, Integer> map = new IdentityHashMap<>();

        map.put(DayOfWeek.MONDAY, 1);
        map.put(DayOfWeek.TUESDAY, 2);

        assertEquals(Integer.valueOf(1), map.get(DayOfWeek.MONDAY));
        assertEquals(Integer.valueOf(2), map.get(DayOfWeek.TUESDAY));

        map.putIfAbsent(DayOfWeek.TUESDAY, 22);
        map.putIfAbsent(DayOfWeek.WEDNESDAY, 3);

        assertEquals(Integer.valueOf(2), map.get(DayOfWeek.TUESDAY));
        assertEquals(Integer.valueOf(3), map.get(DayOfWeek.WEDNESDAY));
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

        assertEquals(Integer.valueOf(1), enumMap.get(DayOfWeek.MONDAY));
        assertEquals(Integer.valueOf(2), enumMap.get(DayOfWeek.TUESDAY));

        enumMap.putIfAbsent(DayOfWeek.TUESDAY, 22);
        enumMap.putIfAbsent(DayOfWeek.WEDNESDAY, 3);

        assertEquals(Integer.valueOf(2), enumMap.get(DayOfWeek.TUESDAY));
        assertEquals(Integer.valueOf(3), enumMap.get(DayOfWeek.WEDNESDAY));
    }
}