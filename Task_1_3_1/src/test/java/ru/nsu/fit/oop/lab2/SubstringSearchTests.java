package ru.nsu.fit.oop.lab2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class SubstringSearchTests {
    @ParameterizedTest
    @MethodSource("allTests")
    public void StringReaderTests(String subStr, String str, int[] expectedResult) throws IOException {
        StringReader StringReader = new StringReader(str);
        List<Integer> fsm = SubstringSearch.zFunction(StringReader, subStr);
        int[] result = fsm.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expectedResult, result);
        StringReader.close();
    }

    @ParameterizedTest
    @MethodSource("allTests")
    public void FileReaderTests(String subStr, String str, int[] expectedResult) throws IOException {
        File f = null;
        try {
            f = File.createTempFile("---", "---");
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(str);
            fileWriter.close();

        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(f));
        List<Integer> fsm = SubstringSearch.zFunction(inputStreamReader, subStr);
        int[] result = fsm.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expectedResult, result);
        inputStreamReader.close();
        } finally {
            if (f != null)
                f.delete();
        }
    }

    private static Stream<Arguments> allTests() {
        return Stream.of(
                Arguments.of("nnn", "nnnnn n n nn nnnn nnnn nnn", new int[]{0, 1, 2, 13, 14, 18, 19, 23}),
                Arguments.of("ara", "ararara arara araara", new int[]{0, 2, 4, 8, 10, 14, 17}),
                Arguments.of("qqqq", "ararara arara araara", new int[]{}),
                Arguments.of("b", "a".repeat(10000) + 'b' + " a".repeat(10000), new int[]{10000})
        );
    }
}
