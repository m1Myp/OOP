package ru.nsu.fit.oop.lab1;

import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


/**
 * HeapSort testing class.
 */
public class HeapSortTests {

    /**
     * Test with random numbers.
     */
    @ParameterizedTest
    @MethodSource ("allTests")
    public void simpleTests(int[] arr) {
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        HeapSort.heapSort(arr);
        assertArrayEquals(sortedArr, arr);
    }

    /**
     * Test with empty array.
     */
    @Test
    public void testEmptyArr() {
        int[] emptyArr = {};
        int[] emptyArrSorted = {};
        HeapSort.heapSort(emptyArr);
        assertArrayEquals(emptyArr, emptyArrSorted);
    }

    /**
     * Test array with one elements.
     */
    @Test
    public void testOneElements() {
        int[] OneElement = {1};
        int[] OneElementSorted = {1};
        HeapSort.heapSort(OneElement);
        assertArrayEquals(OneElement, OneElementSorted);
    }

    private static Stream<int[]> allTests() {
        return Stream.of(
            new int[]{4, 5, 1, 0, -5},
            new int[]{99, 23, -38, -93, 0, 0, -67, 8, 39, -34},
            new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}
            );
    }
}
