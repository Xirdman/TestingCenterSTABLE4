package com.testingcenter.view;

import com.testingcenter.controller.AdminController;
import com.testingcenter.controller.exceptions.LoggedUserNotFoundException;
import com.testingcenter.model.Admin;
import com.testingcenter.model.Student;
import com.testingcenter.model.Teacher;
import com.testingcenter.model.User;

import java.util.Optional;
import java.util.Scanner;

/**
 * View class for log in user
 *
 * @author Matveev Alexander
 */
public class Menu {
    /**
     * Method for users to log in in system
     */
    public static void showWelcomeScreen() {
        String[] loginAndPassword = askLoginAndPassword();
        Optional<User> optionalUser = logIn(loginAndPassword[0], loginAndPassword[1]);
        if (!optionalUser.isEmpty()) {
            User loggedUser = optionalUser.get();
            if (loggedUser instanceof Student) {
                StudentMenu.showFirstScreen((Student) loggedUser);
            } else {
                if (loggedUser instanceof Teacher) {
                    TeacherMenu.showFirstScreen((Teacher) loggedUser);
                } else {
                    if (loggedUser instanceof Admin) {
                        AdminMenu.showFirstScreen((Admin) loggedUser);
                    }
                }
            }
        } else {
            showWelcomeScreen();
        }
    }

    private static String[] askLoginAndPassword() {
        String[] result = {"", ""};
        System.out.print("Welcome to testing center, please log in \n");
        System.out.print("Login: ");
        Scanner scanner = new Scanner(System.in);
        result[0] = scanner.next();
        System.out.print("Password: ");
        result[1] = scanner.next();
        return result;
    }

    private static Optional<User> logIn(String login, String password) {
        try {
            return Optional.of(new AdminController().logIn(login, password));
        } catch (LoggedUserNotFoundException e) {
            System.out.println("Error:\n" + e.getMessage());
        }
        return Optional.empty();
    }
}

