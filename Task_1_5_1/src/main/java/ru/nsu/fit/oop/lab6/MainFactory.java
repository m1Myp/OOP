package ru.nsu.fit.oop.lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.complex.Complex;

/**
 * Main factory class.
 */
public class MainFactory {

    private final List<Operation> operations = new ArrayList<>();
    private final List<ValueParser> valueParsers = new ArrayList<>();

    /**
     * All operations and value we needed.
     */
    public MainFactory() {
        operations.add(new Operation("\\+", 2, (values) -> values[0].add(values[1])));
        operations.add(new Operation("-", 2, (values) -> values[0].subtract(values[1])));
        operations.add(new Operation("\\*", 2, (values) -> values[0].multiply(values[1])));
        operations.add(new Operation("/", 2, (values) -> values[0].divide(values[1])));
        operations.add(new Operation("cos", 1, (values) -> values[0].cos()));
        operations.add(new Operation("sin", 1, (values) -> values[0].sin()));
        operations.add(new Operation("log", 1, (values) -> values[0].log()));
        operations.add(new Operation("pow", 2, (values) -> values[0].pow(values[1])));
        operations.add(new Operation("sqrt", 1, (values) -> values[0].sqrt()));

        valueParsers.add(new ValueParser("-?\\d+(\\.\\d+)?", (s)
                -> new Complex(Double.parseDouble(s))));
        valueParsers.add(new ValueParser("PI", (s) -> new Complex(Math.PI)));
        valueParsers.add(new ValueParser("e", (s) -> new Complex(Math.E)));
        valueParsers.add(new ValueParser("-PI", (s) -> new Complex(-Math.PI)));
        valueParsers.add(new ValueParser("-e", (s) -> new Complex(-Math.E)));
    }

    /**
     * Create tokens using str we get.
     *
     * @param str string we need change to tokens.
     * @return tokens we get.
     */
    public Token[] createtoken(String str) {
        Object[] sometokens = Arrays.stream(str.split("\\s+")).map((s) -> {
            for (Operation op : operations) {
                OperationFactory of = new OperationFactory();
                Token t = of.getOp(op, s);
                if (t.isOperation()) {
                    return t;
                }
            }
            for (ValueParser vp : valueParsers) {
                ValueFactory vf = new ValueFactory();
                Token t = vf.getValue(vp, s);
                if (t.isValue()) {
                    return t;
                }
            }
            throw new Calculator.UnknownTokenException("Unknown token: " + s, s);
        }).toArray();
        return Arrays.copyOf(sometokens, sometokens.length, Token[].class);
    }
}
