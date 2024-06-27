import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.UnaryOperator;

import static org.junit.Assert.*;
/**
 * @author Jun Jiang
 * @date 6/19/24 4:43 PM
 */
public class ArrayListExerciseTest {
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        assertEquals("Element1", list.get(0));
        assertEquals("Element2", list.get(1));
        assertEquals("Element3", list.get(2));

        assertEquals(3, list.size());

        List<String> anotherList = new ArrayList<>();
        anotherList.add("Element4");
        anotherList.add("Element5");

        list.addAll(anotherList);

        assertEquals(5, list.size());
        assertTrue(list.containsAll(anotherList));
    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex)
     * removeAll(Collection<?> c)
     * clear()
     *
     * Update:
     * set(int index, E e)
     * replaceAll(UnaryOperator<E> operator)
     *
     * check:
     * contains(Object o)
     * indexOf(Object o)
     * lastIndexOf(Object o)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");
        list.add("Element4");
        list.add("Element5");

        list.remove(0); // Remove by index
        assertFalse(list.contains("Element1"));

        list.remove("Element3"); // Remove by object
        assertFalse(list.contains("Element3"));

        list.set(0, "UpdatedElement"); // Update element
        assertEquals("UpdatedElement", list.get(0));

        list.replaceAll(String::toUpperCase); // Replace all elements
        assertEquals("UPDATEDELEMENT", list.get(0));

        assertTrue(list.contains("UPDATEDELEMENT"));
        assertEquals(3, list.indexOf("ELEMENT4"));
        assertEquals(3, list.lastIndexOf("ELEMENT4"));

        list.clear(); // Clear all elements
        assertTrue(list.isEmpty());
    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     * forEachRemaining(Consumer<? super E> action) -- from Java8
     */

    @Test
    public void learn_Iterator() {
        List<String> list = new ArrayList<>();
        list.add("Element1");
        list.add("Element2");
        list.add("Element3");

        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("Element2")) {
                iterator.remove(); // Remove current element
            }
        }

        assertFalse(list.contains("Element2"));
        assertEquals(2, list.size());

        iterator = list.iterator();
        iterator.forEachRemaining(element -> System.out.println(element)); // from Java 8
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(3);
        list.add(8);
        list.add(1);

        Collections.sort(list); // Default sort
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(8), list.get(3));

        // Step 1: Create the Comparator using a lambda expression
        Comparator<Integer> comparator = (a, b) -> b.compareTo(a);
        // Step 2: Sort the list using the comparator
        Collections.sort(list, comparator);
        assertEquals(Integer.valueOf(8), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(3));

    }
}
