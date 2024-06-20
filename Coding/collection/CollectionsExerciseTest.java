package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
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
        List<Integer> list = Arrays.asList(2,-5, 1,10,2,1);
        System.out.println(Collections.min(list));
        System.out.println(Collections.max(list));

        Comparator<Integer> cmp = new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(a * -1, b* -1);
            }

        };
        System.out.println(Collections.min(list, cmp));
        System.out.println(Collections.frequency(list, 1));
    }

    /**
     * synchronizedList()
     */

    @Test
    public void learn_thread_safe_ArrayList() {
        List<Integer> list = new ArrayList<>();
        // Wrapping the ArrayList with a synchronized list
        List<Integer> syncList = Collections.synchronizedList(list);
        syncList.add(1);
    }
}
