package com.chuwa.exercise.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author b1go
 * @date 6/12/22 4:48 PM
 */
public class ArraysExerciseTest {

    /**
     * e.g.
     * int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
     * numbers[?]
     *
     * numbers[?] = #
     */
    int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    @Test
    public void learn_Inserting_And_Retrieving() {

        numbers[1] = 0;
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
        Arrays.binarySearch(numbers, 4);
        Arrays.sort(numbers);

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
        int[] numbers = {1, 2, 3, 4, 5};

        int[] copiedNumbers = Arrays.copyOf(numbers, numbers.length);

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
        Integer[] numbersArray = {1, 2, 3, 4, 5};
        List<Integer> list = Arrays.asList(numbersArray);
        System.out.println("List: " + list);

        int[] numbers1 = {1, 2, 3, 4, 5};
        int[] numbers2 = {1, 2, 3, 4, 5};
        int[] numbers3 = {1, 2, 3, 4, 6};
        System.out.println("Arrays.equals(numbers1, numbers2): " + Arrays.equals(numbers1, numbers2)); //  true
        System.out.println("Arrays.equals(numbers1, numbers3): " + Arrays.equals(numbers1, numbers3)); // false

        int[] numbersToFill = new int[5];
        Arrays.fill(numbersToFill, 20);


    }
}
