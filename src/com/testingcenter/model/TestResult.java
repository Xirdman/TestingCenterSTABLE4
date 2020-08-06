package com.testingcenter.model;

/**
 * Model class to storage student completed test and result for test
 *
 * @author Matveev Alexander
 */
public class TestResult {
    private Student student;
    private Test test;
    private int points;

    /**
     * Constructor for test result class
     *
     * @param student student who compleeted test
     * @param test    completed test
     * @param points  points student get for test completion
     */
    public TestResult(Student student, Test test, int points) {
        this.student = student;
        this.test = test;
        this.points = points;
    }

    /**
     * Method to get student for test result
     *
     * @return student for test result
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Method to get points for test
     *
     * @return points for test
     */
    public int getPoints() {
        return points;
    }

    /**
     * Method to get test which was completed by student
     *
     * @return test completed by student
     */
    public Test getTest() {
        return test;
    }
}
