import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:48 PM
 */
public class CollectionsExerciseTest {

    /**
     * Collections.min(list))
     * min(Collection c, Comparator comp)
     *
     * Collections.max(list)
     * max(Collection c, Comparator comp)
     *
     * frequency(Collection c, object o)
     */

    @Test
    public void learn_common_collections_operations() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 3, 5, 7, 2, 8, 6, 3, 5);

        // Find minimum and maximum without a comparator
        int min = Collections.min(list);
        int max = Collections.max(list);
        assertEquals(2, min);
        assertEquals(8, max);

        // Find minimum and maximum with a comparator (reverse order)
        Comparator<Integer> reverseOrder = Comparator.reverseOrder();
        int minWithComparator = Collections.min(list, reverseOrder);
        int maxWithComparator = Collections.max(list, reverseOrder);
        assertEquals(8, minWithComparator);
        assertEquals(2, maxWithComparator);

        // Frequency of an element in the list
        int frequencyOf3 = Collections.frequency(list, 3);
        int frequencyOf5 = Collections.frequency(list, 5);
        assertEquals(2, frequencyOf3);
        assertEquals(2, frequencyOf5);
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list, 1, 2, 3, 4, 5);

        // Convert the ArrayList to a synchronized (thread-safe) list
        List<Integer> synchronizedList = Collections.synchronizedList(list);

        // Add and remove elements in a thread-safe manner
        synchronized (synchronizedList) {
            synchronizedList.add(6);
            synchronizedList.remove(Integer.valueOf(2));
        }

        // Check the contents of the synchronized list
        assertTrue(synchronizedList.contains(1));
        assertFalse(synchronizedList.contains(2));
        assertTrue(synchronizedList.contains(6));
    }
}