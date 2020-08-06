package com.testingcenter.model;

/**
 * Model class for Teacher user
 *
 * @author Matveev Alexander
 */
public class Teacher extends User {
    /**
     * Constructor for model class
     *
     * @param firstName first name of teacher
     * @param lastName  last name of teacher
     * @param login     login of teacher
     * @param password  password of teacher
     */
    public Teacher(String firstName, String lastName, String login, String password) {
        super(firstName, lastName, login, password);
    }
}
