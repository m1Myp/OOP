package io.github.oop;

/**
 * Class for checking if a number is prime.
 */
public class PrimeNumberCheck {
    /**
     * Checks if there are no dividers of a given number n other than 1 or n.
     */
    public static boolean checkNumber(int n) {
        if (n <= 1) {
            return false;
        }
        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
