package com.chuwa.learn;

import org.junit.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

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
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        assertSame("a", list.get(0));
        assertSame(1, list.size());
        ArrayList<String> list2 = new ArrayList<>();
        list2.addAll(list);
        assertSame(list.get(0), list2.get(0));
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
        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.remove(0);
        assertSame("b", list.get(0));
        list.add("a");
        list.remove("b");
        assertSame("a", list.get(0));
        list.add("b");
        list.add("c");
        //list.removeRange(0,1);  removeRange methods is protected
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("a");
        list2.add("b");
        list.removeAll(list2);
        assertSame("c", list.get(0));
        list2.clear();
        assertSame(0, list2.size());

        list.set(0,"z");
        assertSame("z", list.get(0));
        list.replaceAll(a->a.replace("z","a"));
        assertTrue(list.get(0).equals("a"));

        assertTrue(list.contains("a"));
        assertEquals(0, list.indexOf("a"));
        assertEquals(0,list.lastIndexOf("a"));
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

        ArrayList<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("c");

        Iterator<String> it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        it.remove();
        assertFalse(list.contains("b"));
        it.forEachRemaining((a)->assertTrue(a.equals("c"))); // two c
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        ArrayList<Integer> list2 = new ArrayList<Integer>();
        ArrayList<Integer> list3 = new ArrayList<Integer>();
        list.add(10);
        list.add(5);
        list.add(2);
        list.add(9);
        list2.addAll(list);
        list3.addAll(list2);


        list.sort(Integer::compareTo);
        assertEquals(2, list.get(0));

        Collections.sort(list2);
        assertEquals(2, list2.get(0));

        list3.sort(Comparator.reverseOrder());
        assertEquals(10, list3.get(0));
    }
}
