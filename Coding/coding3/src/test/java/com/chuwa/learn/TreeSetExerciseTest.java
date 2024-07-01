package com.chuwa.learn;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

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
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        TreeSet<Integer> set= new TreeSet<>();
        Set<Integer> set2= new TreeSet<>();
        set.add(10);
        set.add(5);
        set.add(8);
        assertTrue(set.contains(5));

        set2.add(4);
        set.add(8);
        set2.add(6);
        set.addAll(set2);
        assertTrue(set.contains(6));
        assertFalse(set.contains(100));

        assertSame(4,set.first());
        assertSame(10,set.last());
        assertTrue(set.subSet(4,7).contains(5));  //5,6
        assertTrue(set.headSet(8).contains(6)); // 4,5,6
        assertTrue(set.tailSet(5).contains(8));//5,6,8,10
    }
}