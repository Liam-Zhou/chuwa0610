package com.chuwa.learn;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
public class HashSetExercise {
    /**
     * e.g.
     * Set<Integer> set= new HashSet<>();

     * add(E, e)
     * addAll(Collection<> c)

     * get()
     * contains()

     * remove(Object o)
     * clear()

     * isEmpty()
     *
     */

    @Test
    public void learn_Inserting_And_Retrieving_Removing() {
        HashSet<Integer> set = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        set.add(1);
        assertTrue(set.contains(1));
        set2.add(2);
        set.addAll(set2);
        assertTrue(set.contains(2));
        //set.get() does not exist
        assertFalse(set.contains(3));

        set.remove(2);
        assertFalse(set.contains(2));
        set.clear();
        assertTrue(set.isEmpty());
    }
}
