package io.github.oop;

import java.util.Arrays;

/**
 * Checks if there is a non-prime number in an array using Java parallel streams (= ParallelStream).
 */
public class ParallelStreamNonPrimeCheck implements NonPrimeNumbersCheck {
    private final int[] numbers;

    ParallelStreamNonPrimeCheck(int[] numbers) throws NullPointerException {
        if (numbers == null) {
            throw new NullPointerException();
        }
        this.numbers = numbers;
    }

    @Override
    public boolean checkArray() {
        return !(Arrays.stream(numbers).parallel().allMatch(PrimeNumberCheck::checkNumber));
    }
}
