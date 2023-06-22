package io.github.oop;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for loading test array with numbers from a file.
 */
public class LoadTest {
    /**
     * Returns an array with big prime numbers.
     */
    public static int[] load() {
        String path = "/numbers.txt";
        InputStream in = SequentialNonPrimeCheck.class.getResourceAsStream(path);
        Scanner scanner = new Scanner(new InputStreamReader(in, StandardCharsets.UTF_8));
        ArrayList<Integer> numbers = new ArrayList<>();
        while (scanner.hasNextInt()) {
            numbers.add(scanner.nextInt());
        }
        return numbers.stream().mapToInt(Integer::intValue).toArray();
    }
}
