package com.chuwa.learn;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        List<Integer> list2 = new CopyOnWriteArrayList<>();

        list.add(1);
        list.add(2);
        assertSame(2,list.get(1));
        list2.add(1);
        list2.add(2);
        list.addAll(list2);
        assertSame(2,list.get(3));
        assertFalse(list.addIfAbsent(2));
        assertSame(0,list.addAllAbsent(list2));

    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     */

    @Test
    public void learn_Iterator() {
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Orange");

        //Created an iterator
        Iterator<String> itr = list.iterator();
        assertTrue(itr.hasNext());
        assertEquals("Apple",itr.next());
        //itr.remove();  Cannot use remove on CopyWriteArrayList use iterator
        list.remove("Apple");
        assertTrue(itr.next().equals("Banana"));
    }
}
