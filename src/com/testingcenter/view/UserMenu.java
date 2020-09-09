package com.testingcenter.view;

import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.view.searchingmenus.TestsSearchMenu;
import com.testingcenter.view.searchingmenus.UsersSearchMenu;

import java.util.Scanner;

/**
 * Parent class to all menu of application
 *
 * @author Matveev Alexander
 */
public class UserMenu {
    /**
     * Enum to help work with pages
     */
    protected enum PagingOptions {
        Exit,
        Back,
        NextPage,
        PrevPage,
        LogOut
    }

    protected static int PAGE_SIZE = 3;

    /**
     * Method to log out from application
     */
    protected static void logOut() {
        Menu.showWelcomeScreen();
    }

    /**
     * Method to exit from application
     */
    protected static void exit() {
        System.exit(0);
    }

    /**
     * Method to print search menu
     */
    protected static void printSearchMenu() {
        System.out.print("Choose where you want to search \n");
        System.out.print("1 - Search in tests\n");
        System.out.print("2 - Search in users \n");
        System.out.print("3 - Back to previous menu\n");
        System.out.print("4 - Log out\n");
        System.out.print("5 - Exit\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 6 \n");
        }
        switch (i) {
            case 1:
                TestsSearchMenu.printSearchMenu();
                break;
            case 2:
                UsersSearchMenu.printSearchMenu();
                break;
            case 3:
                break;
            case 4:
                logOut();
                break;
            case 5:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                printSearchMenu();
                break;
        }
    }

    /**
     * Method to get string from keyboard
     *
     * @return string from keyboard
     */
    protected static String getStringFromKeyboard() {
        return new Scanner(System.in).next();
    }

    /**
     * Method to get integer from keyboard
     *
     * @return integer from keyboard
     * @throws IncorrectInputException if input from keyboard wasn't integer value
     */
    protected static int getIntFromKeyboard() throws IncorrectInputException {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("incorrect input");
        }
    }

    /**
     * Method to get intent what to do with paged collection but without option to go to previous page
     *
     * @return intent what to do with paged collection
     */
    protected static PagingOptions getPagingOptionWithoutPrevPageOption() {
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Next page \n");
        System.out.print("2 - Back to previous menu \n");
        System.out.print("3 - Log out \n");
        System.out.print("4 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
        }
        switch (i) {
            case 1:
                return PagingOptions.NextPage;
            case 2:
                return PagingOptions.Back;
            case 3:
                return PagingOptions.LogOut;
            case 4:
                return PagingOptions.Exit;
            default:
                System.out.print("Waiting to choose an option from 1 to 4 \n");
                return getPagingOptionWithoutPrevPageOption();
        }
    }

    /**
     * Method to get intent what to do with paged collection
     *
     * @return intent what to do with paged collection
     */
    protected static PagingOptions getPagingOption() {
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Previous page \n");
        System.out.print("2 - Next page \n");
        System.out.print("3 - Back to previous menu \n");
        System.out.print("4 - Log out \n");
        System.out.print("5 - Exit \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
        }
        switch (i) {
            case 1:
                return PagingOptions.PrevPage;
            case 2:
                return PagingOptions.NextPage;
            case 3:
                return PagingOptions.Back;
            case 4:
                return PagingOptions.LogOut;
            case 5:
                return PagingOptions.Exit;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                return getPagingOptionWithoutPrevPageOption();
        }
    }

    /**
     * Method to print menu to sort users and choose an option among them
     *
     * @return option to sort users
     */
    protected static int getUsersSortingOption() {
        System.out.println("Choose sorting option:");
        System.out.print("1 - Sort by first name\n");
        System.out.print("2 - Sort by last name \n");
        System.out.print("3 - Sort by middle name \n");
        System.out.print("4 - Sort by date of birth name \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
        if ((i > 0) && (i < 5))
            return i;
        else {
            System.out.println("Please choose from options");
            return getUsersSortingOption();
        }
    }

    /**
     * Method to print menu to sort tests and choose an option among them
     *
     * @return option to sort tests
     */
    protected static int getTestsSortingOption() {
        System.out.println("Choose sorting option:");
        System.out.print("1 - Sort by test name\n");
        System.out.print("2 - Sort by coefficient name \n");
        System.out.print("3 - Sort by teachers last name \n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.print(e.getMessage());
        }
        if ((i > 0) && (i < 4))
            return i;
        else {
            System.out.println("Please choose from options");
            return getTestsSortingOption();
        }
    }

}
