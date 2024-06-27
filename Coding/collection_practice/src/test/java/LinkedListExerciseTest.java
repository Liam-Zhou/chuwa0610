import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Collections;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:45 PM
 */
public class LinkedListExerciseTest {

    /**
     * e.g.
     * List<Integer> list = new LinkedList<Integer>();
     * Inserting:
     * add(E e) or addLast(E e)
     * addFirst(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addAll(int index, Collection c)
     *
     * Retrieving:
     * getFirst()
     * getLast()
     * get(int index)
     *
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        List<Integer> list = new LinkedList<>();

        // Inserting elements
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));

        // addFirst(E e) and addLast(E e)
        ((LinkedList<Integer>) list).addFirst(0);
        ((LinkedList<Integer>) list).addLast(4);
        assertEquals(Integer.valueOf(0), list.get(0));
        assertEquals(Integer.valueOf(4), list.get(4));

        // add(int index, E element)
        list.add(2, 5);
        assertEquals(Integer.valueOf(5), list.get(2));

        // addAll(Collection c)
        List<Integer> anotherList = new LinkedList<>();
        anotherList.add(6);
        anotherList.add(7);
        list.addAll(anotherList);
        assertEquals(7, list.size());
        assertTrue(list.containsAll(anotherList));

        // addAll(int index, Collection c)
        List<Integer> yetAnotherList = new LinkedList<>();
        yetAnotherList.add(8);
        yetAnotherList.add(9);
        list.addAll(2, yetAnotherList);
        assertEquals(Integer.valueOf(8), list.get(2));
        assertEquals(Integer.valueOf(9), list.get(3));

        // Retrieving elements
        assertEquals(Integer.valueOf(0), ((LinkedList<Integer>) list).getFirst());
        assertEquals(Integer.valueOf(4), ((LinkedList<Integer>) list).getLast());
        assertEquals(Integer.valueOf(5), list.get(4));
    }

    /**
     * removeFirst()
     * removeLast()
     * remove(int index)
     * remove(Object o)
     * removeLastOccurrence()
     *
     * sort()
     */

    @Test
    public void learn_Remove_Sort() {
        List<Integer> list = new LinkedList<>();
        Collections.addAll(list, 3, 1, 4, 1, 5, 9, 2, 6, 5);

        // removeFirst() and removeLast()
        ((LinkedList<Integer>) list).removeFirst();
        ((LinkedList<Integer>) list).removeLast();
        assertEquals(7, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(5), list.get(6));

        // remove(int index)
        list.remove(2);
        assertEquals(6, list.size());
        assertFalse(list.contains(4));

        // remove(Object o)
        list.remove(Integer.valueOf(1));
        assertEquals(5, list.size());
        assertFalse(list.contains(1));

        // removeLastOccurrence()
        ((LinkedList<Integer>) list).addLast(5);
        ((LinkedList<Integer>) list).removeLastOccurrence(5);
        assertEquals(5, list.size());
        assertFalse(list.subList(1, list.size()).contains(5));

        // sort()
        Collections.sort(list);
        assertEquals("[2, 3, 5, 5, 6]", list.toString());
    }
}