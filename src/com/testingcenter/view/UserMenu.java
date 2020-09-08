package com.testingcenter.view;

import com.testingcenter.controller.TestController;
import com.testingcenter.controller.exceptions.IncorrectInputException;
import com.testingcenter.controller.exceptions.IncorrectPageException;
import com.testingcenter.model.*;
import com.testingcenter.view.searchviews.SearchInTestQuestionsView;
import com.testingcenter.view.searchviews.SearchInTestsView;
import com.testingcenter.view.searchviews.SearchInUsersView;

import java.util.List;
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
        System.out.print("3 - Search in questions \n");
        System.out.print("4 - Back to previous menu\n");
        System.out.print("5 - Log out\n");
        System.out.print("6 - Exit\n");
        int i = 0;
        try {
            i = getIntFromKeyboard();
            System.out.print("\n");
        } catch (Exception e) {
            System.out.print("Waiting to choose an option from 1 to 6 \n");
        }
        switch (i) {
            case 1:
                SearchInTestsView.printSearchInTests();
                break;
            case 2:
                SearchInUsersView.printSearchInUsers();
                break;
            case 3:
                SearchInTestQuestionsView.printSearchInQuestions();
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
        }
    }

    /**
     * Method to get string to search in data
     *
     * @return string to search in data
     */
    protected static String getSearchedString() {
        System.out.println("Enter the word you want to search: ");
        return getStringFromKeyboard();
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
     * Method to get integer from keyboard to search in Tests by number of questions
     *
     * @return number of questions in test
     */
    protected static int getNumberOfQuestionToSearch() {
        System.out.println("Enter number of questions: ");
        try {
            return getIntFromKeyboard();
        } catch (Exception e) {
            System.out.println("Invalid input");
            return getNumberOfQuestionToSearch();
        }
    }

    /**
     * Method to get integer from keyboard
     *
     * @return integer from keyboard
     */
    protected static int getIntFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        String result = scanner.next();
        try {
            return Integer.parseInt(result);
        } catch (NumberFormatException e) {
            throw new IncorrectInputException("incorrect input");
        }
    }

    /**
     * Method to choose what do next: print next page, log out, exit or back to previous menu
     *
     * @return enum of next action
     */
    protected static PagingOptions chooseOptionWithoutPrevPage() {
        PagingOptions returnValue;
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
            chooseOptionWithoutPrevPage();
        }
        switch (i) {
            case 1:
                returnValue = PagingOptions.NextPage;
                break;
            case 2:
                returnValue = PagingOptions.Back;
                break;
            case 3:
                returnValue = PagingOptions.LogOut;
                break;
            case 4:
                returnValue = PagingOptions.Exit;
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 4 \n");
                returnValue = chooseOptionWithoutPrevPage();
        }
        return returnValue;
    }

    /**
     * Method to choose what do next: print previous page, log out, exit or back to previous menu
     *
     * @return enum of next action
     */
    protected static PagingOptions chooseOptionWithoutNextPage() {
        PagingOptions returnValue;
        System.out.print("Choose options you want to do \n");
        System.out.print("1 - Previous page \n");
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
            chooseOptionWithoutNextPage();
        }
        switch (i) {
            case 1:
                returnValue = PagingOptions.PrevPage;
                break;
            case 2:
                returnValue = PagingOptions.Back;
                break;
            case 3:
                returnValue = PagingOptions.LogOut;
                break;
            case 4:
                returnValue = PagingOptions.Exit;
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 4 \n");
                returnValue = chooseOptionWithoutNextPage();
        }
        return returnValue;
    }

    /**
     * Method to choose what do next: print next page, print previous page, log out, exit or back to previous menu
     *
     * @return enum of next action
     */
    protected static PagingOptions choosePagingOption() {
        PagingOptions returnValue;
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
            choosePagingOption();
        }
        switch (i) {
            case 1:
                returnValue = PagingOptions.PrevPage;
                break;
            case 2:
                returnValue = PagingOptions.NextPage;
                break;
            case 3:
                returnValue = PagingOptions.Back;
                break;
            case 4:
                returnValue = PagingOptions.LogOut;
                break;
            case 5:
                returnValue = PagingOptions.Exit;
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                returnValue = choosePagingOption();
        }
        return returnValue;
    }

    /**
     * Method to print current page of tests collection
     *
     * @param page  number of page to print
     * @param tests collection to retrieve page to print
     */
    protected static void printTestsListByPage(int page, List<List<Test>> tests) {
        if (page >= tests.size() || page < 0)
            throw new IncorrectPageException("Page " + (page + 1) + " not found");
        System.out.println("Tests page number " + (page + 1) + ":");
        List<Test> userPageToPrint = tests.get(page);
        for (Test test : userPageToPrint) {
            System.out.print("Test - \"" + test.getName() + "\" with  " + new TestController().getQuestionNumber(test) + " questions\n");
        }
        System.out.println();
        chooseTestsPageToPrint(page, tests);
    }

    /**
     * Method to choose which action will go next
     *
     * @param page  number of current page
     * @param tests collection of tests we printing now
     */
    protected static void chooseTestsPageToPrint(int page, List<List<Test>> tests) {
        PagingOptions chosenOption;
        if (page == 0) {
            if (page == tests.size() - 1) {
                System.out.println("It is the only page\n");
                chosenOption = PagingOptions.Back;
            } else {
                chosenOption = chooseOptionWithoutPrevPage();
            }
        } else {
            if (page == tests.size() - 1) {
                chosenOption = chooseOptionWithoutNextPage();
            } else {
                chosenOption = choosePagingOption();
            }
        }
        switch (chosenOption) {
            case PrevPage:
                printTestsListByPage(page - 1, tests);
                break;
            case NextPage:
                printTestsListByPage(page + 1, tests);
                break;
            case Back:
                break;
            case LogOut:
                logOut();
                break;
            case Exit:
                exit();
                break;
            default:
                System.out.print("Waiting to choose an option from 1 to 5 \n");
                chooseTestsPageToPrint(page, tests);
        }
    }


}
