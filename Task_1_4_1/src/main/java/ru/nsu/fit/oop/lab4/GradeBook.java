package ru.nsu.fit.oop.lab4;


/**
 * GradeBook class.
 */
public class GradeBook {
    private final String studentName;
    private final int countOfSemesters = 8;
    private int currentSemesters;
    private Course[] courses;

    public GradeBook(String name, Course[] courses) {
        this.studentName = name;
        this.courses = courses;
    }

    public String getStudentName() {
        return studentName;
    }

    public Course[] getCourses() {
        return courses;
    }


    /**
     * Count the average mark for each semester.
     *
     * @return semesterAverage
     */
    private double semesterAverage(int currentSemesters) {
        double[] averageArray = new double[countOfSemesters];
        int[] amount = new int[countOfSemesters];
        int[] sum = new int[countOfSemesters];
        for (int i = 0; i < courses.length; ++i) {
            for (int j = 0; j < courses[i].getMarks().length; ++j) {
                ++amount[courses[i].getMarks()[j].getSemester()];
                sum[courses[i].getMarks()[j].getSemester()] += courses[i].getMarks()[j].getMark();
            }
        }
        for (int i = 0; i < countOfSemesters; ++i) {
            if (amount[i] == 0) {
                averageArray[i] = 0;
            }
            else {
                averageArray[i] = (double) sum[i] / (double) amount[i];
            }
        }
        return averageArray[currentSemesters];
    }

    /**
     * Find average mark for all semester.
     *
     * @return the average mark for all the semesters student completed
     */
    public double averageMark()
    {
        double answer = 0;
        int amount = 0;
        for (int i = 0; i < countOfSemesters; ++i) {
            answer += semesterAverage(i);
            if (semesterAverage(i) > 0) {
                ++amount;
            }
        }
        return answer / (double) amount;
    }


    /**
     * Checks whether the student can have a red diploma.
     *
     * @return true if students can have
     *         false otherwise
     */
    public boolean redDiploma() {
        int exs = 0;
        int amount = 0;
        for (int i = 0; i < courses.length; ++i) {
            for (int j = 0; j < courses[i].getMarks().length; ++j) {
                if (courses[i].getMarks()[j].getMark() == 3) {
                    return false;
                }
                if (courses[i].getCourseName().equals("Дипломная работа")
                        && courses[i].getMarks()[j].getMark() < 5) {
                    return false;
                }
                if (j == courses[i].getMarks().length - 1) {
                    if (courses[i].getMarks()[j].getMark() == 5) {
                        ++exs;
                    }
                    ++amount;
                }
            }
        }
        double average = (double) exs / (double) amount;
        if (average > 0.75) {
            return true;
        }
        return false;
    }

    /**
     * Checks whether the student will have a higher scholarship in the selected semester
     * considering the results of last session.
     *
     * @param selectedSemester semester we need to know about higher scholarship
     *
     * @return true if can
     *         false otherwise
     */
    public boolean higherScholarship(int selectedSemester) {
        if (selectedSemester == 0) {
            return false;
        }
        if (semesterAverage(selectedSemester - 1) != 5) {
            return false;
        }
        return true;
    }
}
