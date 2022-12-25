package ru.nsu.fit.oop.lab6;

import org.apache.commons.math3.complex.Complex;

public record Token(Operation op, Complex val) {
    public boolean isOperation() {
        return op != null;
    }
    public boolean isValue() {
        return val != null;
    }
}
