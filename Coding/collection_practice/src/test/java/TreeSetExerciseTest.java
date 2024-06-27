import org.junit.Test;

import java.util.TreeSet;
import java.util.Set;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:46 PM
 */
public class TreeSetExerciseTest {
    /**
     * e.g.
     * Set<Integer> set= new TreeSet<>();
     *
     * add(E e)
     * addAll(Collection<> c)
     *
     * contains(Object o)
     *
     * first()
     * last()
     * subSet(E fromElement, E toElement)
     * headSet(E toElement)
     * tailSet(E fromElement)
     *
     * remove(Object o)
     *
     * size()
     * isEmpty()
     *
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        Set<Integer> set = new TreeSet<>();

        // add(E e)
        set.add(5);
        set.add(1);
        set.add(3);
        set.add(2);
        set.add(4);

        assertEquals(5, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));

        // addAll(Collection<? extends E> c)
        Set<Integer> anotherSet = new TreeSet<>(Arrays.asList(6, 7, 8));
        set.addAll(anotherSet);
        assertEquals(8, set.size());
        assertTrue(set.containsAll(anotherSet));

        // contains(Object o)
        assertTrue(set.contains(4));
        assertFalse(set.contains(9));

        // first()
        assertEquals(Integer.valueOf(1), ((TreeSet<Integer>) set).first());

        // last()
        assertEquals(Integer.valueOf(8), ((TreeSet<Integer>) set).last());

        // subSet(E fromElement, E toElement)
        Set<Integer> subSet = ((TreeSet<Integer>) set).subSet(3, 7);
        assertEquals(4, subSet.size());
        assertTrue(subSet.contains(3));
        assertTrue(subSet.contains(4));
        assertTrue(subSet.contains(5));
        assertTrue(subSet.contains(6));
        assertFalse(subSet.contains(7)); // toElement is exclusive

        // headSet(E toElement)
        Set<Integer> headSet = ((TreeSet<Integer>) set).headSet(4);
        assertEquals(3, headSet.size());
        assertTrue(headSet.contains(1));
        assertTrue(headSet.contains(2));
        assertTrue(headSet.contains(3));

        // tailSet(E fromElement)
        Set<Integer> tailSet = ((TreeSet<Integer>) set).tailSet(4);
        assertEquals(5, tailSet.size());
        assertTrue(tailSet.contains(4));
        assertTrue(tailSet.contains(5));
        assertTrue(tailSet.contains(6));
        assertTrue(tailSet.contains(7));
        assertTrue(tailSet.contains(8));

        // remove(Object o)
        set.remove(4);
        assertEquals(7, set.size());
        assertFalse(set.contains(4));

        // size()
        assertEquals(7, set.size());

        // isEmpty()
        assertFalse(set.isEmpty());
        set.clear();
        assertTrue(set.isEmpty());
    }
}