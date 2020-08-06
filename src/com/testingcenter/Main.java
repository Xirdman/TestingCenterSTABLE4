package com.testingcenter;

import com.testingcenter.controller.Repository;
import com.testingcenter.model.Admin;
import com.testingcenter.model.Student;
import com.testingcenter.model.Teacher;
import com.testingcenter.model.User;
import com.testingcenter.view.AdminMenu;
import com.testingcenter.view.Menu;
import com.testingcenter.view.StudentMenu;
import com.testingcenter.view.TeacherMenu;

import java.util.List;
import java.util.Optional;

/**
 * Entry point for program
 *
 * @author Matveev Alexander
 */
public class Main {

    /**
     * Entry point of application
     *
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        Repository.initialize();
        showWelcomeScreen();
    }

    /**
     * Method for users to log in in system
     */
    public static void showWelcomeScreen() {
        String[] loginAndPassword = Menu.showWelcomeScreen();
        Optional<User> optionalUser;
        optionalUser = logIn(loginAndPassword[0], loginAndPassword[1]);
        if (!optionalUser.isEmpty()) {
            User loggedUser = optionalUser.get();
            System.out.println("Welcome " + loggedUser.getFirstName() + " " + loggedUser.getLastName());
            if (loggedUser instanceof Student) {
                StudentMenu.showFirstScreen((Student) loggedUser);
            } else {
                if (loggedUser instanceof Teacher) {
                    TeacherMenu.showFirstScreen((Teacher) loggedUser);
                } else {
                    if (loggedUser instanceof Admin) {
                        new AdminMenu().showFirstScreen((Admin) loggedUser);
                    }
                }
            }
        } else {
            showWelcomeScreen();
        }
    }

    private static Optional<User> logIn(String login, String password) {
        List<User> users = Repository.getUsers();
        for (User user : users)
            if (user.getLogin().equals(login) && (user.getPassword().equals(password)))
                return Optional.of(user);
        return Optional.ofNullable(null);
    }
}
