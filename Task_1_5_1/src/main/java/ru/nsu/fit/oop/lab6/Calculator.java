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

    static class UnknownTokenException extends RuntimeException {
        public final String tokenText;

        UnknownTokenException(String message, String tokenText) {
            super(message);
            this.tokenText = tokenText;
        }
    }

    static class InvalidArityException extends RuntimeException{}

    /**
     * Main function of calculator.
     */
    public static void main() {
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
        MainFactory mf = new MainFactory();
        Token[] tokens = mf.createtoken(str);
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