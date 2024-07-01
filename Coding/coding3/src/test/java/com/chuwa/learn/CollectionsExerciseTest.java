package com.chuwa.learn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CollectionsExerciseTest {
    /**
     * Collections.min(list))
     * min(Collection c, Comparator comp)
     *
     * Collections.max(list)
     * max(Collection c, Comparator comp)
     *
     * frequency(Collection c, object o)
     */

    @Test
    public void learn_common_collections_operations() {
        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(6);
        list.add(7);
        list.add(2);

        assertSame(2, Collections.min(list));
        assertSame(10, Collections.min(list,Collections.reverseOrder()));

        assertSame(10, Collections.max(list));
        assertSame(2, Collections.max(list,Collections.reverseOrder()));

        list.add(6);
        assertSame(2,Collections.frequency(list,6));
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {

        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");

        List<String> synList =  Collections.synchronizedList(list);
        // this list is thread safe
        assertTrue(list.equals(synList));
    }
}
