package com.testingcenter.model;

/**
 * Model class for assignment test to student
 *
 * @author Matveev Alexander
 */
public class Assignment {
    private Student student;
    private Test test;
    private boolean isComlpeted;

    /**
     * Get student assigned to test
     *
     * @return student assigned to test
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Get test assigned to student
     *
     * @return test assigned to student
     */
    public Test getTest() {
        return test;
    }

    /**
     * Returns is test completed by student
     *
     * @return true if test completed false is test uncomplited
     */
    public boolean getIsCompleted() {
        return isComlpeted;
    }

    /**
     * Method sets isCompleted boolean value
     * @param isComlpeted is assignment completed
     */
    public void setIsCompleted(boolean isComlpeted){
        this.isComlpeted = isComlpeted;
    }

    /**
     * Construcor for model class. Test will be uncomplited bydefault
     *
     * @param student assigned to test
     * @param test    test assigned to student
     */
    public Assignment(Student student, Test test) {
        this.student = student;
        this.test = test;
        isComlpeted = false;
    }

    /**
     * Construcor for model class
     *
     * @param student   assigned to test
     * @param test      test assigned to student
     * @param comlpeted is test was completed allready or not
     */
    public Assignment(Student student, Test test, boolean comlpeted) {
        this.student = student;
        this.test = test;
        this.isComlpeted = comlpeted;
    }
}
