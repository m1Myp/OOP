package ru.nsu.fit.oop.lab6;

import java.util.regex.Pattern;

/**
 * Operation factory class.
 */
public class OperationFactory {
    /**
     * Checking that our string have operation from op list.
     *
     * @param op operation from what we need regex
     * @param s string from what we take op
     *
     * @return Token we need
     *          Token(null, null) if it is not an operation
     */
    public Token getOp(Operation op, String s) {
        if (Pattern.matches("^" + op.regex() + "$", s)) {
            return new Token(op, null);
        }
        return new Token(null, null);
    }
}
