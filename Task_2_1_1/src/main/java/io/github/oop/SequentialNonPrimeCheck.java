package io.github.oop;

/**
 * Checks if there is a non-prime number in an array.
 */
public class SequentialNonPrimeCheck implements NonPrimeNumbersCheck {
    private final int[] numbers;

    SequentialNonPrimeCheck(int[] numbers) throws NullPointerException {
        if (numbers == null) {
            throw new NullPointerException();
        }
        this.numbers = numbers;
    }

    @Override
    public boolean checkArray() {
        for (int n : numbers) {
            if (!PrimeNumberCheck.checkNumber(n)) {
                return true;
            }
        }
        return false;
    }
}
