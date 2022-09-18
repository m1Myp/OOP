package ru.nsu.fit.oop.lab1;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


import java.util.Arrays;
import java.util.stream.Stream;



    public class HeapSortTests {
        @ParameterizedTest
        @MethodSource ("allTests")
        public void simpleTests(int[] arr) {
           int[] sortedArr = arr.clone();
            Arrays.sort(sortedArr);
            HeapSort.heapSort(arr);
            assertArrayEquals(sortedArr, arr);
        }

        private static Stream<int[]> allTests() {
            return Stream.of(
                    new int[]{4, 5, 1, 0, -5},
                    new int[]{99, 23, -38, -93, 0, 0, -67, 8, 39, -34},
                    new int[]{3, 1, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE},
                    new int[]{},
                    new int[]{1}
            );
        }
    }
