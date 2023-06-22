package io.github.oop;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NonPrimeCheckTest {
    @Test
    public void nullTest() {
        Assertions.assertThrows(NullPointerException.class,
                () -> new SequentialNonPrimeCheck(null).checkArray());

        Assertions.assertThrows(NullPointerException.class,
                () -> new ParallelStreamNonPrimeCheck(null));

        Assertions.assertThrows(NullPointerException.class,
                () -> new ParallelNonPrimeCheck(null, 2));
        Assertions.assertThrows(IllegalStateException.class,
                () -> new ParallelNonPrimeCheck(new int[] { 1, 2 }, 17));
    }

    @Test
    public void simpleTest() {
        int[] arr = { 11, 3, 5, 7, 10 };
        boolean result = new SequentialNonPrimeCheck(arr).checkArray();
        Assertions.assertTrue(result);

        arr = new int[] { 1, 3, 5, 7, 10 };
        result = new ParallelNonPrimeCheck(arr, 2).checkArray();
        Assertions.assertTrue(result);

        arr = new int[] {
                6997901, 6997927, 6997937, 6997967, 6998009,
                6998029, 6998039, 6998051, 6998053
        };
        result = new ParallelStreamNonPrimeCheck(arr).checkArray();
        Assertions.assertFalse(result);
    }
}