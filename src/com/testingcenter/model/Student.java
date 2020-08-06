package com.testingcenter.model;

/**
 * Model class for Admin user
 *
 * @author Matveev Alexander
 */
public class Student extends User {
    private int groupId;
    private double rating;

    /**
     * Constructor for model class
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     * @param login     login of student
     * @param password  password of student
     */
    public Student(String firstName, String lastName, String login, String password) {
        super(firstName, lastName, login, password);
    }

    /**
     * Constructor for model class
     *
     * @param firstName first name of student
     * @param lastName  last name of student
     * @param login     login of student
     * @param password  password of student
     * @param groupId   identificator of group
     */
    public Student(String firstName, String lastName, String login, String password, int groupId) {
        super(firstName, lastName, login, password);
        this.groupId = groupId;
    }

    /**
     * Getter for students group
     *
     * @return identificator of student group
     */
    public int getGroupId() {
        return groupId;
    }

    /**
     * Setter for student group
     *
     * @param groupId identificator of group to set
     */
    public void setGroup(int groupId) {
        this.groupId = groupId;
    }

    /**
     * Getter for rating of Student
     * @return rating of Student
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for student rating
     * @param rating rating of student
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
}
