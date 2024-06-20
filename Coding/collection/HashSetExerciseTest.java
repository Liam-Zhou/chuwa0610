package com.chuwa.exercise.collection;

import org.junit.Test;

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
        Set<Integer> set = new HashSet<>(Arrays.asList(10,11));
        set.add(1);

        List<Integer> list = Arrays.asList(1,2,3);
        set.addAll(list);

        System.out.println(set);

        System.out.println(set.contains(2));
        set.remove(1);
        System.out.println(set);

        set.clear();
        System.out.println(set);
        System.out.println(set.isEmpty());
    }
}
