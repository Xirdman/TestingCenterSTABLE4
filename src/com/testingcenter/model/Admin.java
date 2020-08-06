package com.testingcenter.model;

/**
 * Model class for Admin user
 *
 * @author Matveev Alexander
 */
public class Admin extends User {
    /**
     * Constructor for model class
     *
     * @param firstName first name of admin
     * @param lastName  last name of admin
     * @param login     login of admin
     * @param password  password of admin
     */
    public Admin(String firstName, String lastName, String login, String password) {
        super(firstName, lastName, login, password);
    }
}
