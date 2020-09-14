package com.testingcenter.view.searchingmenus;

import com.testingcenter.controller.UsersSearchController;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.controller.exceptions.NoElementsFoundException;
import com.testingcenter.model.User;
import com.testingcenter.model.sortingoptions.UsersSortingOption;
import com.testingcenter.view.UserMenu;

import java.util.Calendar;
import java.util.List;

/**
 * Class to search users by first name, last name, middle name
 */
public class UsersSearchMenu extends UserMenu {
    /**
     * Method to print menu to search users by first name, last name, middle name
     */
    public static void printSearchMenu() {
        System.out.print("Choose option how to search users:\n");
        System.out.print("1 - Search by first name\n");
        System.out.print("2 - Search by last name \n");
        System.out.print("3 - Search by middle name \n");
        System.out.print("4 - Search by date of birth name \n");
        System.out.print("5 - Back to previous menu\n");
        System.out.print("6 - Log out\n");
        System.out.print("7 - Exit\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
        switch (i) {
            case 1:
                startSearchUsersByFirstName();
                break;
            case 2:
                startSearchUsersByLastName();
                break;
            case 3:
                startSearchUsersByMiddleName();
                break;
            case 4:
                UsersSearchInDateOfBirthMenu.printSearchMenu();
                break;
            case 5:
                break;
            case 6:
                logOut();
                break;
            case 7:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 7 \n");
                printSearchMenu();
                break;
        }

    }


    private static void startSearchUsersByFirstName() {
        System.out.println("Enter first name to search");
        String searchedFirstName = getStringFromKeyboard();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByFirstNamePage(PAGE_SIZE, 0, searchedFirstName, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchUsersByFirstNamePage(int limit, int offset, String firstName, UsersSortingOption usersSortingOption) throws IncorrectPageException {
        List<User> users = new UsersSearchController(usersSortingOption).searchByFirstName(limit, offset, firstName);
        System.out.println("Users with first name " + firstName + " found:");
        users.forEach((a) -> System.out.println(a.getFirstName() + " "
                + a.getLastName() + " "
                + a.getMiddleName() + " "
                + a.getDateOfBirth().get(Calendar.DAY_OF_MONTH) + " of " +
                a.getDateOfBirth().get(Calendar.MONTH) + " month " + a.getDateOfBirth().get(Calendar.YEAR)));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchUsersByFirstNamePage(limit, offset - limit, firstName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByFirstNamePage(limit, offset, firstName, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByFirstNamePage(limit, offset + limit, firstName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByFirstNamePage(limit, offset, firstName, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }

    private static void startSearchUsersByLastName() {
        System.out.println("Enter last name to search");
        String searchedLastName = getStringFromKeyboard();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByLastNamePage(PAGE_SIZE, 0, searchedLastName, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }

    }

    private static void printSearchUsersByLastNamePage(int limit, int offset, String lastName, UsersSortingOption usersSortingOption) throws IncorrectPageException {
        List<User> users = new UsersSearchController(usersSortingOption).searchByLastName(limit, offset, lastName);
        System.out.println("Users with last name " + lastName + " found:");
        users.forEach((a) -> System.out.println(a.getFirstName() + " "
                + a.getLastName() + " "
                + a.getMiddleName() + " "
                + a.getDateOfBirth().get(Calendar.DAY_OF_MONTH) + " of " +
                a.getDateOfBirth().get(Calendar.MONTH) + " month " + a.getDateOfBirth().get(Calendar.YEAR)));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchUsersByLastNamePage(limit, offset - limit, lastName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByLastNamePage(limit, offset, lastName, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByLastNamePage(limit, offset + limit, lastName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByLastNamePage(limit, offset, lastName, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }

    private static void startSearchUsersByMiddleName() {
        System.out.println("Enter middle name to search");
        String searchedMiddleName = getStringFromKeyboard();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByMiddleNamePage(PAGE_SIZE, 0, searchedMiddleName, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchUsersByMiddleNamePage(int limit, int offset, String middleName, UsersSortingOption usersSortingOption) throws IncorrectPageException {
        List<User> users = new UsersSearchController(usersSortingOption).searchByMiddleName(limit, offset, middleName);
        System.out.println("Users with middle name " + middleName + " found:");
        users.forEach((a) -> System.out.println(a.getFirstName() + " "
                + a.getLastName() + " "
                + a.getMiddleName() + " "
                + a.getDateOfBirth().get(Calendar.DAY_OF_MONTH) + " of " +
                a.getDateOfBirth().get(Calendar.MONTH) + " month " + a.getDateOfBirth().get(Calendar.YEAR)));
        System.out.println("page - " + ((offset / limit) + 1) + "\n");
        PagingOptions pagingOption;
        if (offset == 0)
            pagingOption = getPagingOptionWithoutPrevPageOption();
        else pagingOption = getPagingOption();
        switch (pagingOption) {
            case PrevPage:
                try {
                    printSearchUsersByMiddleNamePage(limit, offset - limit, middleName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByMiddleNamePage(limit, offset, middleName, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByMiddleNamePage(limit, offset + limit, middleName, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByMiddleNamePage(limit, offset, middleName, usersSortingOption);
                }
                break;
            case LogOut:
                logOut();
                break;
            case Back:
                break;
            case Exit:
                exit();
                break;
        }
    }

}
