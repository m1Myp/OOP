package ru.nsu.fit.oop.lab6;

import java.util.function.Function;
import org.apache.commons.math3.complex.Complex;

/**
 * Operation record class.
 */
public record Operation(String regex, int arity, Function<Complex[], Complex> evaluate) {
}
