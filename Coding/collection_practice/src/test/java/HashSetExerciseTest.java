import org.junit.Test;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.*;


/**
 * @author Jun Jiang
 * @date 6/19/24 4:46 PM
 */
public class HashSetExerciseTest {
    /**
     * e.g.
     * Set<Integer> set= new HashSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * get()
     * contains()
     *
     * remove(Object o)
     * clear()
     *
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        Set<Integer> set = new HashSet<>();

        // add(E e)
        set.add(1);
        set.add(2);
        set.add(3);

        assertEquals(3, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));

        // addAll(Collection<? extends E> c)
        Set<Integer> anotherSet = new HashSet<>(Arrays.asList(4, 5, 6));
        set.addAll(anotherSet);
        assertEquals(6, set.size());
        assertTrue(set.containsAll(anotherSet));

        // contains(Object o)
        assertTrue(set.contains(4));
        assertFalse(set.contains(7));

        // remove(Object o)
        set.remove(2);
        assertEquals(5, set.size());
        assertFalse(set.contains(2));

        // clear()
        set.clear();
        assertTrue(set.isEmpty());
    }
}