package io.github.oop;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Benchmark testing NonPrimeCheck implementations.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 5, time = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkTest {
    private static int[] testArray;

    /**
     * Initializes testArray with big prime numbers before benchmark tests.
     */
    @Setup(Level.Trial)
    public void setTestArray() {
        testArray = LoadTest.load();
    }

    @Benchmark
    public void testSequentialChecker(Blackhole blackhole) {
        blackhole.consume(new SequentialNonPrimeCheck(testArray).checkArray());
    }

    @Benchmark
    public void testParallelStreamChecker(Blackhole blackhole) {
        blackhole.consume(new ParallelStreamNonPrimeCheck(testArray).checkArray());
    }
}
