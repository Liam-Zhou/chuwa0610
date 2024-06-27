import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Jun Jiang
 * @date 6/19/24 4:48 PM
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

        // Retrieve element
        int valueAtIndex5 = numbers[5];
        assertEquals(6, valueAtIndex5);

        // Insert/Update element
        numbers[5] = 60;
        assertEquals(60, numbers[5]);
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
        int[] numbers = { 5, 3, 8, 1, 9, 4, 2, 7, 6 };

        // Sort array
        Arrays.sort(numbers);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, numbers);

        // Binary search (array must be sorted)
        int index = Arrays.binarySearch(numbers, 4);
        assertEquals(3, index);

        // Parallel sort (Java 8 and later)
        int[] numbersForParallelSort = { 5, 3, 8, 1, 9, 4, 2, 7, 6 };
        Arrays.parallelSort(numbersForParallelSort);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, numbersForParallelSort);
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
        int[] numbers = { 1, 2, 3, 4, 5 };

        // Copy entire array
        int[] copiedArray = Arrays.copyOf(numbers, numbers.length);
        assertArrayEquals(numbers, copiedArray);

        // Copy range of array
        int[] copiedArrayRange = Arrays.copyOfRange(numbers, 0, 3);
        assertArrayEquals(new int[]{1, 2, 3}, copiedArrayRange);
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
        int[] numbers = { 1, 2, 3, 4, 5 };

        // Convert array to List
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(Arrays.asList(numbers), list);

        // Check equality of arrays
        int[] numbers1 = { 1, 2, 3, 4, 5 };
        int[] numbers2 = { 1, 2, 3, 4, 5 };
        assertTrue(Arrays.equals(numbers1, numbers2));

        // Fill array with a specific value
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 20);
        assertArrayEquals(new int[]{20, 20, 20, 20, 20}, filledArray);
    }
}