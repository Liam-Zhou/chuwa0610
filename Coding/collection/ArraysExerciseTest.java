package com.chuwa.exercise.collection;

import org.junit.Test;

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

    @Test
    public void learn_Inserting_And_Retrieving() {
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        numbers[0] = 10;
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
        int[] numbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int idx = Arrays.binarySearch(numbers, 4);
        System.out.println(idx);

        int[] nums = {  5, 6, 7, 8, 9, 10, 1, 2, 3, 4, };
        Arrays.sort(nums);
        Arrays.stream(nums).forEach (e-> System.out.print(e + " "));
        System.out.println();

        int[] nums1 = {  5, 6, 7, 8, 9, 10, 1, 2, 3, 4, };
        Arrays.sort(nums1, 5, 7); //[from, to)
        Arrays.stream(nums1).forEach (e-> System.out.print(e + " "));
        System.out.println();
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
        int[] numbers = { 1, 2, 3, 4 ,5,6};

        int[] arr1 = Arrays.copyOf(numbers, 10);
        Arrays.stream(arr1).forEach(e -> System.out.print(e + " "));
        System.out.println();

        int[] arr2 = Arrays.copyOf(numbers, 2);
        Arrays.stream(arr2).forEach(e -> System.out.print(e + " "));
        System.out.println();

        int[] arr3 = Arrays.copyOfRange(numbers, 1, 4);
        Arrays.stream(arr3).forEach(e -> System.out.print(e + " "));
        System.out.println();
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
        Integer[] numbers = { 1, 2, 3, 4 ,5,6};

        List<Integer> list = Arrays.asList(numbers);
        list.stream().forEach(System.out::println);


        List<Integer> list1 = Arrays.asList(1,2);
        list1.stream().forEach(System.out::println);

        System.out.println(Arrays.equals(new int[]{1,2}, new int[]{1,2}));
        Arrays.fill(numbers, 20);
        Arrays.stream(numbers).forEach(System.out::println);
    }
}
