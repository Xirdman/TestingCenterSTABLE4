package com.testingcenter.view;

import java.util.Scanner;

/**
 * View class for log in user
 *
 * @author Matveev Alexander
 */
public class Menu {
    /**
     * Display log in screen
     *
     * @return String of users login and password
     */
    public static String[] showWelcomeScreen() {
        String[] result = {"", ""};
        System.out.print("Welcome to testing center, please log in \n");
        System.out.print("Login: ");
        Scanner scanner = new Scanner(System.in);
        result[0] = scanner.next();
        System.out.print("Password: ");
        result[1] = scanner.next();
        return result;
    }
}
