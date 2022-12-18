package ru.nsu.fit.oop.lab4;

public class Marks {
    private int semester;
    private int mark;
    public Marks(int semester, int mark){
        this.mark = mark;
        this.semester = semester;
    }

    public int getSemester(){
        return semester;
    }

    public int getMark(){
        return mark;
    }

    public void setMark(int sem, int mk){
        this.mark = mk;
        this.semester = sem;
    }
}
