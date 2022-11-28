package ru.nsu.fit.oop.lab2;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class SubstringSearchTests {
    /**
     * Finding substring in stringreader
     *
     * @param subStr         - substring we need to fing
     * @param str            - string in what we need find substring
     * @param expectedResult - result we wanna to check
     * @throws IOException if some problems with a reading of the reader
     */
    @ParameterizedTest
    @MethodSource("allTests")
    public void stringreadertests(String subStr, String str, int[] expectedResult)
            throws IOException {
        StringReader stringreader = new StringReader(str);
        List<Integer> fsm = SubstringSearch.zfunction(stringreader, subStr);
        int[] result = fsm.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expectedResult, result);
        stringreader.close();
    }

    /**
     * Finding substring in filereader using temporary creation new file
     *
     * @param subStr         - substring we need to find
     * @param str            - string in what we need find substring
     * @param expectedResult - result we wanna to check
     * @throws IOException if some problems with a reading of the reader
     */
    @ParameterizedTest
    @MethodSource("allTests")
    public void filereadertest(String subStr, String str, int[] expectedResult)
            throws IOException {
        File f = null;
        try {
            f = File.createTempFile("---", "---");
            FileWriter fileWriter = new FileWriter(f);
            fileWriter.write(str);
            fileWriter.close();

            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(f));
            List<Integer> fsm = SubstringSearch.zfunction(inputStreamReader, subStr);
            int[] result = fsm.stream().mapToInt(i -> i).toArray();
            assertArrayEquals(expectedResult, result);
            inputStreamReader.close();
        } finally {
            if (f != null) {
                f.delete();
            }
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
