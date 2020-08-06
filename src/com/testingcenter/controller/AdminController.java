package com.testingcenter.controller;

import com.testingcenter.model.User;

import java.util.List;

/**
 * Class to make business logic with Admin user
 *
 * @author Matveev Alexander
 */
public class AdminController {
    /**
     * Method to get all users in system
     *
     * @return List of users registered in system
     */
    public List<User> getUsers() {
        return Repository.getUsers();
    }
}
