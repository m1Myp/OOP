package ru.nsu.fit.oop.lab3;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class SubstringSearchTests {

    @BeforeAll
    public static void createFolder() {
        File theDir = new File("/temp");
        if (!theDir.exists()) {
            theDir.mkdirs();
        }
    }

    @ParameterizedTest
    @MethodSource("allTests")
    public void StringReaderTests(String fileName, String subStr, String str, int[] expectedResult) throws IOException {
        StringReader StringReader = new StringReader(str);
        List<Integer> fsm = SubstringSearch.zFunction(StringReader, subStr);
        int[] result = fsm.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expectedResult, result);
        StringReader.close();
    }

    @ParameterizedTest
    @MethodSource("allTests")
    public void FileReaderTests(String fileName, String subStr, String str, int[] expectedResult) throws IOException {
        createTextFile(fileName, str);
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
        List<Integer> fsm = SubstringSearch.zFunction(inputStreamReader, subStr);
        int[] result = fsm.stream().mapToInt(i -> i).toArray();
        assertArrayEquals(expectedResult, result);
        inputStreamReader.close();

        File file = new File(fileName);
        boolean wasDeleted = file.delete();
    }

    private static Stream<Arguments> allTests() {
        return Stream.of(
                Arguments.of("/temp/input.txt", "nnn", "nnnnn n n nn nnnn nnnn nnn", new int[]{0, 1, 2, 13, 14, 18, 19, 23}),
                Arguments.of("/temp/input.txt", "ara", "ararara arara araara", new int[]{0, 2, 4, 8, 10, 14, 17}),
                Arguments.of("/temp/input.txt", "qqqq", "ararara arara araara", new int[]{}),
                Arguments.of("/temp/input.txt", "b",
                        "a".repeat(10000) + 'b' + " a".repeat(10000), new int[]{10000})
        );
    }

    private static void createTextFile(String fileName, String str) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, StandardCharsets.UTF_8);
        fileWriter.write(str);
        fileWriter.close();
    }

    @AfterAll
    public static void deleteFolder() {
        File theDir = new File("/temp");
        if (theDir.exists()){
            theDir.delete();
        }
    }


}
