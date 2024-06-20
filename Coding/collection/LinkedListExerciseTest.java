package com.chuwa.exercise.collection;

import org.junit.Test;

/**
 * @author b1go
 * @date 6/12/22 4:45 PM
 */
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
     *
     * Retrieving:
     * getFirst()
     * getLast()
     * get(int index)
     *
     */
    @Test
    public void learn_Inserting_And_Retrieving() {
        // quite simialr to ArrayList
        LinkedList<Integer> list = new LinkedList<>();
        list.add(20);
        list.addFirst(10);
        list.addLast(30);

        System.out.println(list);
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
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
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(10,1,3,100,1,100,0,100,30));
        list.removeFirst();
        list.removeLast();
        list.removeLastOccurrence(100);
        System.out.println(list);
        list.sort(null);
        System.out.println(list);
        list.sort(new Comparator<Integer>(){
            @Override
            public int compare(Integer a, Integer b) {
                return Integer.compare(b,a);
            }
        });
        System.out.println(list);
    }
}
