package ru.nsu.fit.oop.lab6

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstCalculatorPyTest {
    static Stream<Arguments> getExpressions(){
        return Stream.of(
                Arguments.of("+ 1 1", 2d),
                Arguments.of("* + 2 3 5", 25d),
                Arguments.of("- * + 1 5 / 5 2 1", 14d),
                Arguments.of("cos 0", 1d),
                Arguments.of("sin 0", 0d),
                Arguments.of("pow 2 10", 1024d),
                Arguments.of("sqrt 9", "3"),
                Arguments.of("log e", 1d),
                Arguments.of("sin / PI 2", 1d)
        );
    }


    @Test
    @DisplayName("Test invalid token")
    void evaluateString_InvalidToken(){
        assertThrows(MyFirstCalculatorPy.UnknownTokenException.class, () -> new MyFirstCalculatorPy().evaluateString("aboba"));
    }
}
