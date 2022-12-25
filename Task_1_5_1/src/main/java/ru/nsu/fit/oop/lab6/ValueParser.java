package ru.nsu.fit.oop.lab6;

import org.apache.commons.math3.complex.Complex;

import java.util.function.Function;

public record
ValueParser(String regex, Function<String, Complex> parse) {
        }
