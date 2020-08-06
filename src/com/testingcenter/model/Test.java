package com.testingcenter.model;

/**
 * Model class for Test
 *
 * @author Matveev Alexander
 */
public class Test {
    private String name;
    private Teacher teacher;
    private double coefficientToPass;

    /**
     * Constructor
     *
     * @param name    Name of test
     * @param teacher teacher who study discipline
     */
    public Test(String name, Teacher teacher) {
        this.name = name;
        this.teacher = teacher;
        coefficientToPass = 0.66;
    }

    /**
     * Get name of test
     *
     * @return name of test
     */
    public String getName() {
        return name;
    }

    /**
     * Get teacher who studies test discipline
     *
     * @return teacher who studies test discipline
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Method to get coefficient to pass the test
     * @return coefficient to pass test
     */
    public double getCoefficientToPass(){
        return coefficientToPass;
    }
}
