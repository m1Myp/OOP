package ru.nsu.fit.oop.lab4;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;


public class GradeBookTests {
    Marks[] CreateMarks(int[][] marks){
        Marks[] mks = new Marks[marks.length];
        for(int i = 0; i < marks.length; ++i)
            mks[i] = new Marks(marks[i][0], marks[i][1]);
        return mks;
    }

    GradeBook CreateGradeBook(String name, int[][][] marks){
        String[] lessons = {
                "matan", "matlog", "english", "pe", "platforms", "imperative", "declarative", "history", "russian"
        };
        Course[] coursesAndMarks = new Course[lessons.length];
        for(int i = 0; i < lessons.length; ++i){
            coursesAndMarks[i] = new Course(lessons[i], CreateMarks(marks[i]));
        }
        return new GradeBook(name, coursesAndMarks);
    }

    @ParameterizedTest
    @MethodSource("allTests")
    public void GradeBookTests(String name, int[][][] marks) {
        GradeBook gb = CreateGradeBook(name, marks);
        if(name == "Timur Ilinykh"){
            assertFalse(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertFalse(gb.higherScholarship(1));
            assertTrue(gb.averageMark() > 4.6);
        }
        if(name == "lazyPerson"){
            assertFalse(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertFalse(gb.higherScholarship(1));
            assertTrue(gb.averageMark() == 3.0);
        }
        if(name == "tryHardPerson"){
            assertTrue(gb.redDiploma());
            assertFalse(gb.higherScholarship(0));
            assertTrue(gb.higherScholarship(1));
            assertTrue(gb.averageMark() == 5.0);
        }
    }

    private static Stream<Arguments> allTests() {
        return Stream.of(
                Arguments.of("Timur Ilinykh", new int[][][]{
                        {{0,5},{1,4}},
                        {{0,5},{1,4}},
                        {{0,5},{1,4}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,4},{1,4}},
                        {{0,5},{1,5}},
                        {{0,5}},
                        {{0,5}}
        }),
                Arguments.of("lazyPerson", new int[][][]{
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3},{1,3}},
                        {{0,3}},
                        {{0,3}}
                }),
                Arguments.of("tryHardPerson", new int[][][]{
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5},{1,5}},
                        {{0,5}},
                        {{0,5}}
                })
        );
    }

}
