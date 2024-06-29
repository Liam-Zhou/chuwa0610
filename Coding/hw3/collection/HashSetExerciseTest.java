package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author b1go
 * @date 6/12/22 4:46 PM
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
        HashSet<String> set = new HashSet<>();


        set.add("Element 1");
        set.add("Element 2");
        set.add("Element 3");
        set.add("Element 4");

        HashSet<String> set2 = new HashSet<>();


        set2.add("Element 1");
        set2.add("Element 99");
        set.addAll(set2);
        set.contains("Element 2");
        set.size();
        set.remove("Element 1");
        set.clear();
        set.isEmpty();

    }
}
