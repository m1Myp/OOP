package ru.nsu.fit.oop.lab4;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Gradebook test class.
 */
public class GradeBookTests {
    Marks[] createmarks(int[][] marks) {
        Marks[] mks = new Marks[marks.length];
        for (int i = 0; i < marks.length; ++i) {
            mks[i] = new Marks(0, 0);
            mks[i].setMark(marks[i][0], marks[i][1]);
        }
        return mks;
    }

    GradeBook creategradebook(String name, int[][][] marks) {
        String[] lessons = {"matan", "matlog", "english", "pe",
            "platforms", "imperative", "declarative", "history", "russian"
        };
        Course[] coursesAndMarks = new Course[lessons.length];
        for (int i = 0; i < lessons.length; ++i) {
            coursesAndMarks[i] = new Course(lessons[i], createmarks(marks[i]));
        }
        return new GradeBook(name, coursesAndMarks);
    }

    /**
     * All tests for this task
     *
     * @param name student name
     * @param marks stident marks
     */
    @ParameterizedTest
    @MethodSource("allTests")
    public void allgradebooktests(String name, int[][][] marks) {
        GradeBook gb = creategradebook(name, marks);
        if (name.equals("Timur Ilinykh")) {
            assertFalse(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertFalse(gb.higherScholarship(1));
            assertTrue(gb.averageMark() > 4.6);
        }
        if (name.equals("lazyPerson")) {
            assertFalse(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertFalse(gb.higherScholarship(1));
            assertTrue(gb.averageMark() == 3.0);
        }
        if (name.equals("tryHardPerson")) {
            assertTrue(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertTrue(gb.higherScholarship(1));
            assertTrue(gb.averageMark() == 5.0);
        }
    }

    private static Stream<Arguments> allTests() {
        return Stream.of(
                Arguments.of("Timur Ilinykh", new int[][][]{
                        {{0, 5}, {1, 4}},
                        {{0, 5}, {1, 4}},
                        {{0, 5}, {1, 4}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 4}, {1, 4}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}},
                        {{0, 5}}
                }),
                Arguments.of("lazyPerson", new int[][][]{
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}, {1, 3}},
                        {{0, 3}},
                        {{0, 3}}
                }),
                Arguments.of("tryHardPerson", new int[][][]{
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}, {1, 5}},
                        {{0, 5}},
                        {{0, 5}}
                })
        );
    }
}
