import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:46 PM
 */
public class CopyOnWriteArrayListExerciseTest {

    /**
     * e.g.
     * List list = new CopyOnWriteArrayList();
     *
     * add(E e)
     * add(int index, E element)
     * addAll(Collection c)
     * addIfAbsent(E e)
     * addAllAbsent(Collection c)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

        // Add elements
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        assertEquals(3, list.size());
        assertEquals("Apple", list.get(0));
        assertEquals("Banana", list.get(1));
        assertEquals("Orange", list.get(2));

        // Add element at specific index
        list.add(1, "Grapes");
        assertEquals("Grapes", list.get(1));
        assertEquals(4, list.size());

        // Add all elements from another collection
        List<String> anotherList = new ArrayList<>();
        anotherList.add("Pineapple");
        anotherList.add("Mango");
        list.addAll(anotherList);

        assertTrue(list.containsAll(anotherList));

        // Add element if absent
        list.addIfAbsent("Apple"); // Already exists
        list.addIfAbsent("Cherry"); // Does not exist
        assertTrue(list.contains("Cherry"));

        // Add all elements if absent
        List<String> moreFruits = new ArrayList<>();
        moreFruits.add("Banana"); // Already exists
        moreFruits.add("Kiwi"); // Does not exist
        list.addAllAbsent(moreFruits);

        assertTrue(list.contains("Kiwi"));
        assertEquals(8, list.size());
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     */

    @Test
    public void learn_Iterator() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        // Created an iterator
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            System.out.println(element);
        }

        // Demonstrate that modifications during iteration are safe
        itr = list.iterator();
        while (itr.hasNext()) {
            String element = itr.next();
            if (element.equals("Banana")) {
                list.remove("Banana"); // Removing "Banana" while iterating
            }
        }

        assertFalse(list.contains("Banana"));
        assertEquals(2, list.size());
    }
}