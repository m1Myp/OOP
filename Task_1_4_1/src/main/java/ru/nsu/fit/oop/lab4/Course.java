package ru.nsu.fit.oop.lab4;

public class Course {
    private final String courseName;
    private final Marks[] marks;

    public Course(String cName, Marks[] mks) {
        courseName = cName;
        marks = mks;
    }

    public String getCourseName(){
        return courseName;
    }

    public Marks[] getMarks(){
        return marks;
    }


}
