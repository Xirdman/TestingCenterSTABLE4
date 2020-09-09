package com.testingcenter.model;

import java.util.Calendar;

/**
 * Model class for user
 *
 * @author Matveev Alexander
 */
public class User {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Calendar dateOfBirth;
    private String middleName;

    /**
     * Method to get date of birth of user
     *
     * @return date of birth of user
     */
    public Calendar getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Method to get middle name of user
     *
     * @return Middle name of user
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Method to get first name of user
     *
     * @return First name of user
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Method to get last name of user
     *
     * @return Last name of user
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Method to get login of user
     *
     * @return Login of user
     */
    public String getLogin() {
        return login;
    }

    /**
     * Method to get password of user
     *
     * @return password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Constructor for model class
     *
     * @param firstName first name of user
     * @param lastName  last name of user
     * @param middleName middle name of user
     * @param dateOfBirth date of birth of user
     * @param login     login of user
     * @param password  password of user
     */
    public User(String firstName, String lastName, String middleName, Calendar dateOfBirth, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.dateOfBirth = dateOfBirth;
        this.login = login;
        this.password = password;
    }
}
