package ru.nsu.fit.oop.lab4;

public class Course {
    private final String courseName;
    private final Marks[] marks;

    public Course(String courseName, Marks[] marks) {
        this.courseName = courseName;
        this.marks = marks;
    }

    public String getCourseName(){
        return courseName;
    }

    public Marks[] getMarks(){
        return marks;
    }
}
