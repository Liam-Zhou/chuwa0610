package com.chuwa.learn;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
public class ArraysExerciseTest {
    /**
     * e.g.
     * int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
     * numbers[?]
     *
     * numbers[?] = #
     */

    @Test
    public void learn_Inserting_And_Retrieving() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        assertSame(2,numbers[1]);
        assertSame(6,numbers[5]);
    }

    /**
     * binarySearch()
     * e.g.
     * Arrays.binarySearch(numbers, 4);
     *
     * sort(array)
     * sort(array, fromIndex, toIndex)
     * e.g.
     * Arrays.sort(numbers);
     *
     * Arrays.parallelSort(numbers);
     */
    @Test
    public void learn_search_and_sort() {
        int[] numbers = { 100,20,27,33,49,44,25,98};
        assertSame(3, Arrays.binarySearch(numbers,33));

        Arrays.sort(numbers);
        assertSame(100,numbers[7]);

        int[] numbers2 = { 100,20,27,33,49,44,25,98};
        Arrays.sort(numbers2,0,3);
        assertSame(100,numbers2[2]);
        Arrays.parallelSort(numbers2);
        assertTrue(Arrays.equals(numbers, numbers2));
    }

    /**
     * copyOf()
     * e.g.
     * Arrays.copyOf(numbers, numbers.length);
     *
     * copyOfRange()
     * e.g.
     * Arrays.copyOfRange(numbers, 0, 5);
     */
    @Test
    public void learn_copy_of_array() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] numbers2 = Arrays.copyOf(numbers, numbers.length);
        assertTrue(Arrays.equals(numbers, numbers2));

        int[] numbers3 = Arrays.copyOfRange(numbers,3,6);
        assertSame(4,numbers3[0]);
    }

    /**
     * asList()
     * e.g.
     * List<Integer> list = Arrays.asList(numbers);
     *
     * equals()
     * e.g.
     * Arrays.equals(numbers1, numbers2);
     *
     * fill()
     * e.g.
     * Arrays.fill(numbers, 20);
     *
     */

    @Test
    public void learn_common_operations() {
        Integer[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] numbers2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int[] numbers3 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        List<Integer> list = Arrays.asList(numbers);
        assertTrue(list.get(0)==numbers2[0]);
        assertTrue(Arrays.equals(numbers3,numbers2));

        int[] numbers4 = new int[10];
        Arrays.fill(numbers4, 1);
        assertSame(1,numbers4[7]);

    }
}
