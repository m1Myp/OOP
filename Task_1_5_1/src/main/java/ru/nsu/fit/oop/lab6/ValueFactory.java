package ru.nsu.fit.oop.lab6;

import java.util.regex.Pattern;

public class ValueFactory {
    public Token getValue(ValueParser vp, String s) {
        if (Pattern.matches("^" + vp.regex() + "$", s)) {
            return new Token(null, vp.parse().apply(s));
        }
        return new Token(null, null);
    }
}
