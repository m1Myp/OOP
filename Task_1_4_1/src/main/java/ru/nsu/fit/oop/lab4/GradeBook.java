package ru.nsu.fit.oop.lab4;

public class GradeBook {
    private final String studentName;
    private final int countOfSemesters = 8;
    private Course[] courses;

    public GradeBook(String name, Course[] crs) {
        studentName = name;
        courses = crs;
    }

    public String getStudentName(){
        return studentName;
    }

    public Course[] getCourses(){
        return courses;
    }

    /**
     * count the average mark for each semester
     * @return semesterAverage
     */
    private double[] semesterAverage(){
        double[] averageArray = new double[countOfSemesters];
        int[] amount = new int[countOfSemesters];
        int[] sum = new int[countOfSemesters];
        for(int i = 0 ; i < courses.length; ++i)
        {
            for(int j = 0 ; j < courses[i].getMarks().length; ++j)
            {
                ++amount[courses[i].getMarks()[j].getSemester()];
                sum[courses[i].getMarks()[j].getSemester()] += courses[i].getMarks()[j].getMark();
            }
        }
        for(int i = 0; i < countOfSemesters; ++i) {
            if(amount[i] == 0)
                averageArray[i] = 0;
            else
                averageArray[i] = (double) sum[i] / (double) amount[i];
        }
        return averageArray;
    }

    /**
     * @return the average mark for all the semesters student completed
     */
    public double averageMark()
    {
        double answer = 0;
        int amount = 0;
        double[] semAvrg = semesterAverage();
        for(int i = 0; i < countOfSemesters; ++i) {
            answer += semAvrg[i];
            if(semAvrg[i]>0)
                ++amount;
        }
        return answer/(double) amount;
    }


    /**
     * checks whether the student can have a red diploma
     * @return
     */
    public boolean redDiploma(){
        int exs = 0;
        int amount = 0;
        for(int i = 0; i < courses.length; ++i){
            for(int j = 0 ; j < courses[i].getMarks().length; ++j)
            {
                if(courses[i].getMarks()[j].getMark() == 3)
                    return false;
                if(courses[i].getCourseName() == "Дипломная работа" && courses[i].getMarks()[j].getMark() < 5)
                    return false;
                if(j == courses[i].getMarks().length-1) {
                    if (courses[i].getMarks()[j].getMark() == 5)
                        ++exs;
                    ++amount;
                }
            }
        }
        double average = (double) exs / (double) amount;
        if (average > 0.75)
            return true;
        return false;
    }

    /**
     * checks whether the student will have a higher scholarship in the selected semester
     * considering the results of last session
     * @param selectedSemester
     * @return
     */
    public boolean higherScholarship(int selectedSemester)
    {
        if(selectedSemester == 0)
            return false;
        if(semesterAverage()[selectedSemester-1] != 5)
            return false;
        return true;
    }
}
