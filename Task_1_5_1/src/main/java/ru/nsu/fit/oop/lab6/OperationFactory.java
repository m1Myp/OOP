package ru.nsu.fit.oop.lab6;

import java.util.regex.Pattern;

public class OperationFactory {
    public Token getOp(Operation op, String s) {
        if (Pattern.matches("^" + op.regex() + "$", s)) {
            return new Token(op, null);
        }
        return new Token(null, null);
    }
}
