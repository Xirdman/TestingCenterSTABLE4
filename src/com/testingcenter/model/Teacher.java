package com.testingcenter.model;

import java.util.Calendar;

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
     * @param middleName middle name of user
     * @param dateOfBirth date of birth of user
     * @param login     login of teacher
     * @param password  password of teacher
     */
    public Teacher(String firstName, String lastName, String middleName, Calendar dateOfBirth, String login, String password) {

        super(firstName, lastName,middleName,dateOfBirth, login, password);
    }
}
