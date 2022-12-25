package ru.nsu.fit.oop.lab6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import org.apache.commons.math3.complex.Complex;

/**
 * Calculator class.
 */
public class Calculator {
    private final List<Operation> operations = new ArrayList<>();
    private final List<ValueParser> valueParsers = new ArrayList<>();

    static class UnknownTokenException extends RuntimeException {
        public final String tokenText;

        UnknownTokenException(String message, String tokenText) {
            super(message);
            this.tokenText = tokenText;
        }
    }

    static class InvalidArityException extends RuntimeException{}

    public Calculator() {
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

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print('>');
            String str = sc.nextLine();
            if (str.equals(".quit")) {
                break;
            }
            try {
                Complex res = calc.evaluateString(str);
                System.out.println(res.getReal());
            } catch (UnknownTokenException e) {
                System.out.println("ERROR: " + e.getMessage());
            } catch (InvalidArityException e) {
                System.out.println("Invalid number of arguments");
            }
        }
    }

    /**
     * Take string, split, find token, use token to find answer.
     *
     * @param str string we need to evaluate
     * @return answer of calculation
     */
    public Complex evaluateString(String str) {
        Stack<Complex> stack = new Stack<>();
        Object[] SomeTokens = Arrays.stream(str.split("\\s+")).map((s) -> {
            for (Operation op : operations) {
                OperationFactory of = new OperationFactory();
                Token t = of.getOp(op, s);
                if (t.isOperation()) {
                    return t;
                }
            }
            for (ValueParser vp : valueParsers) {
                ValueFactory of = new ValueFactory();
                Token t = of.getValue(vp, s);
                if (t.isValue()) {
                    return t;
                }
            }
            throw new UnknownTokenException("Unknown token: " + s, s);
        }).toArray();
        Token[] tokens = Arrays.copyOf(SomeTokens, SomeTokens.length, Token[].class);


        for (int i = tokens.length - 1; i >= 0; i--) {
            Token token = tokens[i];
            if (token.isOperation()) {
                Operation op = token.op();
                if (stack.size() < op.arity()) {
                    throw new InvalidArityException();
                }
                Complex[] args = new Complex[op.arity()];
                for (int j = 0; j < op.arity(); j++) {
                    args[j] = stack.pop();
                }
                stack.push(op.evaluate().apply(args));
            } else {
                stack.push(token.val());
            }
        }

        if (stack.size() != 1) {
            throw new InvalidArityException();
        }
        return stack.pop();
    }
}