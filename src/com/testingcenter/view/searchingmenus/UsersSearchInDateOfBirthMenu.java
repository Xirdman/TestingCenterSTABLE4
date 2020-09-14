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
 * Class to print menu to search in users by date of birth
 */
public class UsersSearchInDateOfBirthMenu extends UserMenu {
    /**
     * Method to search in users by date of birth
     */
    public static void printSearchMenu() {
        System.out.print("Choose option how to search users:\n");
        System.out.print("1 - Search by day of birth\n");
        System.out.print("2 - Search by month of birth \n");
        System.out.print("3 - Search by year of birth \n");
        System.out.print("4 - Back to previous menu\n");
        System.out.print("5 - Log out\n");
        System.out.print("6 - Exit\n");
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
                searchInDayOfBirth();
                break;
            case 2:
                searchInMonthOfBirth();
                break;
            case 3:
                searchInYearOfBirth();
                break;
            case 4:
                break;
            case 5:
                logOut();
                break;
            case 6:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 6 \n");
                printSearchMenu();
                break;
        }

    }

    private static int getIntToSearch() {
        try {
            return getIntFromKeyboard();
        } catch (IncorrectInputException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
            return getIntToSearch();
        }
    }

    private static void searchInDayOfBirth() {
        System.out.println("Enter day of birth to search");
        int dayToSearch = getIntToSearch();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByDayOfBirthPage(PAGE_SIZE, 0, dayToSearch, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchUsersByDayOfBirthPage(int limit, int offset, int dayOfBirth, UsersSortingOption usersSortingOption) throws IncorrectPageException {
        List<User> users = new UsersSearchController(usersSortingOption).searchByDayOfBirth(limit, offset, dayOfBirth);
        System.out.println("Users with day of birth " + dayOfBirth + " found:");
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
                    printSearchUsersByDayOfBirthPage(limit, offset - limit, dayOfBirth, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByDayOfBirthPage(limit, offset, dayOfBirth, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByDayOfBirthPage(limit, offset + limit, dayOfBirth, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByDayOfBirthPage(limit, offset, dayOfBirth, usersSortingOption);
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

    private static void searchInMonthOfBirth() {
        System.out.println("Enter month of birth to search");
        int monthToSearch = getIntToSearch();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByMonthOfBirthPage(PAGE_SIZE, 0, monthToSearch, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchUsersByMonthOfBirthPage(int limit, int offset, int monthToSearch, UsersSortingOption usersSortingOption) throws IncorrectPageException {
        List<User> users = new UsersSearchController(usersSortingOption).searchByMonthOfBirth(limit, offset, monthToSearch);
        System.out.println("Users with month of birth " + monthToSearch + " found:");
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
                    printSearchUsersByMonthOfBirthPage(limit, offset - limit, monthToSearch, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByMonthOfBirthPage(limit, offset, monthToSearch, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByMonthOfBirthPage(limit, offset + limit, monthToSearch, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByMonthOfBirthPage(limit, offset, monthToSearch, usersSortingOption);
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

    private static void searchInYearOfBirth() {
        System.out.println("Enter year of birth to search");
        int yearToSearch = getIntToSearch();
        UsersSortingOption optionCode = getUsersSortingOption();
        try {
            printSearchUsersByYearOfBirthPage(PAGE_SIZE, 0, yearToSearch, optionCode);
        } catch (NoElementsFoundException e) {
            System.out.print("Error: \n");
            System.out.println(e.getMessage());
        }
    }

    private static void printSearchUsersByYearOfBirthPage(int limit, int offset, int yearToSearch, UsersSortingOption usersSortingOption) {
        List<User> users = new UsersSearchController(usersSortingOption).searchByYearOfBirth(limit, offset, yearToSearch);
        System.out.println("Users with year of birth " + yearToSearch + " found:");
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
                    printSearchUsersByYearOfBirthPage(limit, offset - limit, yearToSearch, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant back to previous page\n");
                    printSearchUsersByYearOfBirthPage(limit, offset, yearToSearch, usersSortingOption);
                }
                break;
            case NextPage:
                try {
                    printSearchUsersByYearOfBirthPage(limit, offset + limit, yearToSearch, usersSortingOption);
                } catch (IncorrectPageException e) {
                    System.out.println("Cant go to next page\n");
                    printSearchUsersByYearOfBirthPage(limit, offset, yearToSearch, usersSortingOption);
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
