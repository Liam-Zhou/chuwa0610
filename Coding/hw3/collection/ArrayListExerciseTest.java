package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * @author b1go
 * @date 6/12/22 4:43 PM
 */
public class ArrayListExerciseTest {
    /**
     * new ArrayList()
     * add elements
     * get element
     * get Size
     * list.addAll(anotherList)
     */

    List<Integer> list = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    @Test
    public void learn_Inserting_And_Retrieving() {

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.get(0);
        list.size();

        list2.addAll(list);


    }

    /**
     * remove(int index)
     * remove(Object o)
     * removeRange(int fromIndex, int toIndex)
     * removeAll(Collection<?> c)
     * clear()
     *
     * Update:
     * set(int index, E e)
     * replaceAll(UnaryOperator<E> operator)
     *
     * check:
     * contains(Object o)
     * indexOf(Object o)
     * lastIndexOf(Object o)
     */
    @Test
    public void learn_Remove_Replacing_Updating() {
        list2.remove(0);
        list2.removeAll(list);
        list.clear();

        list2.set(0,999);

        List<String> list3 = new ArrayList<>(Arrays.asList("apple", "banana", "cherry"));

        // 定义一个 UnaryOperator，将字符串转换为大写
        UnaryOperator<String> toUpperCase = String::toUpperCase;

        // 应用 replaceAll 方法，将每个元素转换为大写
        list.replaceAll(toUpperCase);

        list3.contains("apple");
        list3.indexOf("banana");
        list3.lastIndexOf("cherry");

    }

    /**
     * iterator()
     * hasNext()
     * next()
     * remove()
     * forEachRemaining(Consumer<? super E> action) -- from Java8
     */

    @Test
    public void learn_Iterator() {
        for(int x : list2) {
            System.out.println(x);
        }
    }

    /**
     * sort(List<T> list)
     * Collections.sort(List<T> t)
     * Comparator.reverseOrder()
     */

    @Test
    public void learn_Sorting() {
        Collections.sort(list);

    }
}
