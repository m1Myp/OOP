package ru.nsu.fit.oop.lab4;

public class Marks {
    private final int semester;
    private final int mark;
    public Marks(int sem, int mk){
        mark = mk;
        semester = sem;
    }

    public int getSemester(){
        return semester;
    }

    public int getMark(){
        return mark;
    }
}
