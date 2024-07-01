package com.chuwa.learn;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

     * Retrieving:
     * getFirst()
     * getLast()
     * get(int index)
     *
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedList<Integer> list2 = new LinkedList<Integer>();
        LinkedList<Integer> list100 = new LinkedList<Integer>();
        list.add(1);
        assertSame(1, list.get(0));
        list.addLast(2);
        assertSame(2, list.get(1));
        list.addFirst(0);
        assertSame(0, list.get(0));
        list.add(0,-1);
        assertSame(-1, list.get(0));
        list2.addAll(list);
        assertTrue(list.equals(list2));
        list100.add(100);
        list2.addAll(2,list100);
        assertSame(list2.get(2),100);

        assertSame(-1,list.getFirst());
        assertSame(2,list.getLast());
        assertSame(0,list.get(1));

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

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        list.removeFirst();
        assertSame(2,list.getFirst());
        list.removeLast();
        assertSame(3,list.getLast());
        list.remove(0);
        assertSame(3,list.getFirst());
        list.remove(Integer.valueOf(3));
        assertTrue(list.isEmpty());
        list.add(100);
        list.add(10);
        list.add(5);
        list.add(4);
        list.add(10);
        list.removeLastOccurrence(Integer.valueOf(10));
        assertSame(4,list.size());
        list.sort(Integer::compareTo);

        assertSame(4,list.getFirst());



    }
}
