package com.testingcenter;

import com.testingcenter.controller.Repository;
import com.testingcenter.view.Menu;

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
        Menu.showWelcomeScreen();
    }
}
