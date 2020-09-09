package com.testingcenter.model;

import java.util.Calendar;

/**
 * Model class for Admin user
 *
 * @author Matveev Alexander
 */
public class Admin extends User {
    /**
     * Constructor for model class
     *
     * @param firstName   first name of admin
     * @param lastName    last name of admin
     * @param middleName  middle name of user
     * @param dateOfBirth date of birth of user
     * @param login       login of admin
     * @param password    password of admin
     */
    public Admin(String firstName, String lastName, String middleName, Calendar dateOfBirth, String login, String password) {
        super(firstName, lastName, middleName, dateOfBirth, login, password);
    }
}
