package ru.nsu.fit.oop.lab6;

import org.apache.commons.math3.complex.Complex;

/**
 * Token record class.
 */
public record Token(Operation op, Complex val) {

    /**
     * Checking token is operation.
     *
     * @return true/false
     */
    public boolean isOperation() {
        return op != null;
    }

    /**
     * Checking token is value.
     *
     * @return true/false
     */
    public boolean isValue() {
        return val != null;
    }
}
