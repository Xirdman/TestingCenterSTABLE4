package com.testingcenter.controller;

import com.testingcenter.controller.exceptions.LoggedUserNotFoundException;
import com.testingcenter.model.User;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    /**
     * Method to find user by login and password
     *
     * @param login    login of user
     * @param password password of user
     * @return user with such login and password
     * @throws LoggedUserNotFoundException throws if user not founded in database
     */
    public User logIn(String login, String password) throws LoggedUserNotFoundException {
        List<User> users = Repository.getUsers();
        User user;
        try {
            user = users.stream().filter(e -> e.getPassword().equals(password))
                    .filter(e -> e.getLogin().equals(login))
                    .findFirst().orElseThrow(NoSuchElementException::new);
            return user;
        } catch (NoSuchElementException e) {
            throw new LoggedUserNotFoundException("Invalid login or password");
        }
    }


}

