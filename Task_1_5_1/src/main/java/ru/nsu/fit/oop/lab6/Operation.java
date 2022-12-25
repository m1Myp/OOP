package ru.nsu.fit.oop.lab6;

import org.apache.commons.math3.complex.Complex;

import java.util.function.Function;

public record Operation(String regex, int arity, Function<Complex[], Complex> evaluate) {
}
