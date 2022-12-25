package ru.nsu.fit.oop.lab6;

import java.util.regex.Pattern;

/**
 * Value factory class.
 */
public class ValueFactory {
    /**
     * Checking that our string have value from val list.
     *
     * @param vp value from what we need regex
     * @param s string from what we take op
     *
     * @return Token we need
     *          Token(null, null) if it is not a value
     */
    public Token getValue(ValueParser vp, String s) {
        if (Pattern.matches("^" + vp.regex() + "$", s)) {
            return new Token(null, vp.parse().apply(s));
        }
        return new Token(null, null);
    }
}
