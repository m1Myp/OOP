package io.github.oop;

import java.util.concurrent.TimeUnit;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

/**
 * Class for testing ParallelNonPrimeCheck using different number of threads.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 3, time = 5)
@Measurement(iterations = 5, time = 5)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ThreadsBenchmarkTest {
    private static int[] testArray;

    @Param({"1", "2", "3", "4", "5", "6"})
    private int streams;

    @Setup(Level.Trial)
    public void setTestArray() {
        testArray = LoadTest.load();
    }

    @Benchmark
    public void testParallelChecker(Blackhole blackhole) {
        blackhole.consume(new ParallelNonPrimeCheck(testArray, streams).checkArray());
    }
}
