package io.github.oop;

/**
 * Checks if there is a non-prime number in an array using parallel threads.
 */
public class ParallelNonPrimeCheck implements NonPrimeNumbersCheck {
    private final int[] numbers;
    private int streams;
    private boolean check;
    private final Object lock;

    ParallelNonPrimeCheck(int[] numbers, int streams)
            throws NullPointerException, IllegalStateException {
        if (numbers == null) {
            throw new NullPointerException();
        }
        if (streams < 1 || streams > 12) {
            throw new IllegalStateException();
        }
        this.numbers = numbers;
        this.streams = streams;
        lock = new Object();
    }

    public void setStreams(int streams) {
        this.streams = streams;
    }

    /**
     * Divides the given array of integers numbers into parts
     * and starts the specified number of threads.
     */
    @Override
    public boolean checkArray() {
        int divide = numbers.length / streams;
        Thread[] threads = new Thread[streams];
        check = false;
        for (int i = 0; i < streams; i++) {
            ParallelNonPrimeCheck.PrimeCheck primeCheck =
                    new ParallelNonPrimeCheck.PrimeCheck(i * divide, (i + 1) * divide);
            threads[i] = new Thread(primeCheck);
            threads[i].start();
        }
        for (Thread thread : threads) {
            if (thread.isAlive()) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return check;
    }

    /**
     * PrimeCheck class for running parallel threads.
     */
    private class PrimeCheck implements Runnable {
        private final int left;
        private final int right;

        PrimeCheck(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {
            for (int i = left; i < right; i++) {
                if (check) {
                    return;
                }
                if (!PrimeNumberCheck.checkNumber(numbers[i])) {
                    synchronized (lock) {
                        check = true;
                        return;
                    }
                }
            }
        }
    }
}
