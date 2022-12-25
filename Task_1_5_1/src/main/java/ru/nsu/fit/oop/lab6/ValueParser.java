package ru.nsu.fit.oop.lab6;

import java.util.function.Function;
import org.apache.commons.math3.complex.Complex;

/**
 * Value parser record class.
 */
public record ValueParser(String regex, Function<String, Complex> parse) {
}
