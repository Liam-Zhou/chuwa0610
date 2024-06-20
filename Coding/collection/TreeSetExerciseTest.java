package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 4:46 PM
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
        TreeSet<Integer> set = new TreeSet<>();
        set.add(5);
        set.add(10);
        set.add(3);
        set.add(8);
        System.out.println(set);
        System.out.println(set.first());
        System.out.println(set.last());

        Set<Integer> subset = set.subSet(5, 10);
        Set<Integer> headSet = set.headSet(8); // [ ,8)
        Set<Integer> tailSet = set.tailSet(8); // [8, ]

        System.out.println(subset);
        System.out.println(headSet);
        System.out.println(tailSet);

    }
}
