package com.testingcenter.model;

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
     * @param login     login of user
     * @param password  password of user
     */
    public User(String firstName, String lastName, String login, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
    }
}
